package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipe {
    // --- CAMPOS DO JSON (Dados Carregados) ---
    private String id;          // Ex: "redbull"
    private String nome;        // Ex: "Red Bull Racing"
    private String sede;        // Ex: "Milton Keynes, UK"
    private String motor;       // Ex: "Honda RBPT"
    private int fundacao;       // Ex: 2005
    private double saldoFinanceiro;
    private int reputacao;      // 0 a 100
    
    // Objeto aninhado para guardar os caminhos das imagens
    private Arquivos arquivos;
    
    // Lista de IDs dos pilotos (usado apenas durante o carregamento do JSON)
    private List<String> pilotosContratadosIDs = new ArrayList<>();

    // --- CAMPOS DE LÓGICA DO JOGO ---
    private int anosConsecutivosNoVermelho = 0;
    
    // As listas reais de objetos (Gson ignora isso na leitura inicial, a gente popula depois)
    private transient List<Piloto> pilotosTitulares = new ArrayList<>();
    private transient List<Piloto> pilotosReservas = new ArrayList<>();
    private transient List<Patrocinador> patrocinadoresAtivos = new ArrayList<>();
    
    private transient Categoria categoriaAtual; // Referência para saber as regras

    // Construtor vazio (Necessário para o Gson)
    public Equipe() {
        // Inicializa listas para evitar NullPointerException
        this.arquivos = new Arquivos(); 
    }

    public Equipe(String nome, double saldoInicial, int reputacao) {
        this.nome = nome;
        this.saldoFinanceiro = saldoInicial;
        this.reputacao = reputacao;
        this.arquivos = new Arquivos();
    }

    // --- CLASSE INTERNA PARA MAPEAMENTO DO JSON ---
    public static class Arquivos {
        public String logo;
        public String logoSvg;
        public String bandeiraSede;
        public String bandeiraSedeSvg;
        public String bandeiraMotor;
        public String logoMotor; // Logo Pequena do motor
    }

    // --- MÉTODOS DE CONTRATAÇÃO (Lógica do Jogo) ---

    public boolean contratarPiloto(Piloto piloto, double salario, double custoAssinatura, int meses, TipoContrato tipo) {
        // 1. Verifica Limites da Categoria
        if (categoriaAtual != null) {
            if (tipo == TipoContrato.TITULAR && pilotosTitulares.size() >= categoriaAtual.getMaxTitulares()) {
                System.out.println("Erro: Equipe já tem o máximo de titulares.");
                return false;
            }
            if (tipo == TipoContrato.RESERVA && pilotosReservas.size() >= categoriaAtual.getMaxReservas()) {
                System.out.println("Erro: Equipe já tem o máximo de reservas.");
                return false;
            }
        }

        // 2. Aplica Desconto de Promoção (Regra dos 50%)
        double custoFinalAssinatura = custoAssinatura;
        if (tipo == TipoContrato.TITULAR && piloto.isReservaDaEquipe(this)) {
            custoFinalAssinatura = custoAssinatura * 0.5; // 50% de desconto
            pilotosReservas.remove(piloto);
        }

        // 3. Pagamento e Assinatura
        if (saldoFinanceiro >= custoFinalAssinatura) {
            this.saldoFinanceiro -= custoFinalAssinatura;
            
            Contrato novoContrato = new Contrato(salario, meses, this, tipo);
            piloto.assinarContrato(novoContrato);
            
            if (tipo == TipoContrato.TITULAR) {
                pilotosTitulares.add(piloto);
            } else {
                pilotosReservas.add(piloto);
            }
            return true;
        } else {
            System.out.println("Erro: Saldo insuficiente.");
            return false;
        }
    }
    
    // Método usado pelo CarregadorJSON para forçar a entrada de pilotos (sem custos)
    public void adicionarPilotoDoLoad(Piloto p, TipoContrato tipo) {
        if (tipo == TipoContrato.TITULAR) pilotosTitulares.add(p);
        else pilotosReservas.add(p);
        
        // Cria um contrato padrão para o piloto carregado (ex: 1 ano, salário base)
        // Futuramente isso pode vir do JSON de pilotos
        Contrato c = new Contrato(0.5, 12, this, tipo); 
        p.assinarContrato(c);
    }

    // --- MÉTODOS FINANCEIROS ---

    public void adicionarPatrocinador(Patrocinador p) {
        patrocinadoresAtivos.add(p);
        this.saldoFinanceiro += p.getValorAssinatura();
    }

    public void limparPatrocinadoresVencidos() {
        Iterator<Patrocinador> it = patrocinadoresAtivos.iterator();
        while (it.hasNext()) {
            Patrocinador p = it.next();
            if (p.expirou()) {
                it.remove();
            }
        }
    }
    
    public void receberReceita(double valor) { this.saldoFinanceiro += valor; }
    public void pagarDespesa(double valor) { this.saldoFinanceiro -= valor; }
    
    public void incrementarAnosNoVermelho() { this.anosConsecutivosNoVermelho++; }
    public void zerarAnosNoVermelho() { this.anosConsecutivosNoVermelho = 0; }
    
    public boolean verificarGameOver() {
        if (saldoFinanceiro < 0) {
            anosConsecutivosNoVermelho++;
            if (anosConsecutivosNoVermelho >= 2) return true;
        } else {
            anosConsecutivosNoVermelho = 0;
        }
        return false;
    }

    // --- GETTERS E SETTERS ---

    public String getNome() { return nome; }
    public String getId() { return id; }
    public String getSede() { return sede; }
    public String getMotor() { return motor; }
    public int getFundacao() { return fundacao; }
    public int getReputacao() { return reputacao; }
    public double getSaldoFinanceiro() { return saldoFinanceiro; }
    public int getAnosConsecutivosNoVermelho() { return anosConsecutivosNoVermelho; }
    
    public List<Patrocinador> getPatrocinadoresAtivos() { return patrocinadoresAtivos; }
    public List<Piloto> getPilotosTitulares() { return pilotosTitulares; }
    public List<Piloto> getPilotosReservas() { return pilotosReservas; }
    public List<String> getPilotosContratadosIDs() { return pilotosContratadosIDs; } // Usado pelo Carregador
    
    public void setCategoriaAtual(Categoria categoriaAtual) {
        this.categoriaAtual = categoriaAtual;
    }
    
    // --- HELPER METHODS PARA IMAGENS (Evita NullPointer) ---
    
    public String getCaminhoLogo() {
        if (arquivos != null && arquivos.logo != null) return arquivos.logo;
        return "/resource/Icone64pxErro.png";
    }
    
    public String getCaminhoBandeiraSede() {
        if (arquivos != null && arquivos.bandeiraSede != null) return arquivos.bandeiraSede;
        return "/resource/Bandeira BRANCA.png";
    }
    
    public String getCaminhoBandeiraMotor() {
        if (arquivos != null && arquivos.bandeiraMotor != null) return arquivos.bandeiraMotor;
        return "/resource/Bandeira BRANCA.png";
    }
    
    public String getCaminhoLogoMotor() {
        if (arquivos != null && arquivos.logoMotor != null) return arquivos.logoMotor;
        return "/resource/Icone16pxErro.png";
    }
}
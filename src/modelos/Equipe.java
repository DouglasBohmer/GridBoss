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
    
    // --- ESTATÍSTICAS DA TEMPORADA (Adicionadas) ---
    private int pontos;
    private int vitorias;
    private int podios;
    private int poles;
    
    // Objeto aninhado para guardar os caminhos das imagens
    private Arquivos arquivos;
    
    // Lista de IDs dos pilotos (usado apenas durante o carregamento do JSON)
    private List<String> pilotosContratadosIDs = new ArrayList<>();

    // --- CAMPOS DE LÓGICA DO JOGO ---
    private int anosConsecutivosNoVermelho = 0;
    
    // As listas reais de objetos
    private transient List<Piloto> pilotosTitulares = new ArrayList<>();
    private transient List<Piloto> pilotosReservas = new ArrayList<>();
    private transient List<Patrocinador> patrocinadoresAtivos = new ArrayList<>();
    
    private transient Categoria categoriaAtual; // Referência para saber as regras

    // Construtor vazio (Necessário para o Gson)
    public Equipe() {
        this.arquivos = new Arquivos(); 
    }

    public Equipe(String nome, double saldoInicial, int reputacao) {
        this.nome = nome;
        this.saldoFinanceiro = saldoInicial;
        this.reputacao = reputacao;
        this.arquivos = new Arquivos();
    }

    public static class Arquivos {
        public String logo;
        public String logoSvg;
        public String bandeiraSede;
        public String bandeiraSedeSvg;
        public String bandeiraMotor;
        public String bandeiraMotorSvg;
        public String logoMotor;
        public String logoMotorSvg;
    }

    // --- MÉTODOS DE CONTRATAÇÃO ---
    public boolean contratarPiloto(Piloto piloto, double salario, double custoAssinatura, int meses, TipoContrato tipo) {
        if (categoriaAtual != null) {
            if (tipo == TipoContrato.TITULAR && pilotosTitulares.size() >= categoriaAtual.getMaxTitulares()) return false;
            if (tipo == TipoContrato.RESERVA && pilotosReservas.size() >= categoriaAtual.getMaxReservas()) return false;
        }

        double custoFinalAssinatura = custoAssinatura;
        if (tipo == TipoContrato.TITULAR && piloto.isReservaDaEquipe(this)) {
            custoFinalAssinatura = custoAssinatura * 0.5;
            pilotosReservas.remove(piloto);
        }

        if (saldoFinanceiro >= custoFinalAssinatura) {
            this.saldoFinanceiro -= custoFinalAssinatura;
            Contrato novoContrato = new Contrato(salario, meses, this, tipo);
            piloto.assinarContrato(novoContrato);
            
            if (tipo == TipoContrato.TITULAR) pilotosTitulares.add(piloto);
            else pilotosReservas.add(piloto);
            return true;
        }
        return false;
    }
    
    public void adicionarPilotoDoLoad(Piloto p, TipoContrato tipo) {
        if (tipo == TipoContrato.TITULAR) pilotosTitulares.add(p);
        else pilotosReservas.add(p);
        
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
            if (it.next().expirou()) it.remove();
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
    
    // Stats da Temporada
    public int getPontos() { return pontos; }
    public void setPontos(int pontos) { this.pontos = pontos; }
    public void adicionarPontos(int pontos) { this.pontos += pontos; }

    public int getVitorias() { return vitorias; }
    public void setVitorias(int vitorias) { this.vitorias = vitorias; }
    public void adicionarVitoria() { this.vitorias++; }

    public int getPodios() { return podios; }
    public void setPodios(int podios) { this.podios = podios; }
    public void adicionarPodio() { this.podios++; }

    public int getPoles() { return poles; }
    public void setPoles(int poles) { this.poles = poles; }
    public void adicionarPole() { this.poles++; }
    
    public List<Patrocinador> getPatrocinadoresAtivos() { return patrocinadoresAtivos; }
    public List<Piloto> getPilotosTitulares() { return pilotosTitulares; }
    public List<Piloto> getPilotosReservas() { return pilotosReservas; }
    public List<String> getPilotosContratadosIDs() { return pilotosContratadosIDs; }
    
    public void setCategoriaAtual(Categoria categoriaAtual) {
        this.categoriaAtual = categoriaAtual;
    }
    
    // --- HELPER METHODS IMAGENS ---
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
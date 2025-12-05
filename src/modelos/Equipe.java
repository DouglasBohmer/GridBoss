package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipe {
    // --- CAMPOS DO JSON (Dados Carregados) ---
    private String id;
    private String nome;
    private String sede;
    private String motor;
    private int fundacao;
    private double saldoFinanceiro;
    private int reputacao;      // 0 a 100
    
    // --- ESTATÍSTICAS DA TEMPORADA ---
    private int pontos;
    private int vitorias;
    private int podios;
    private int poles;
    
    // --- FÁBRICA & DESENVOLVIMENTO ---
    // Níveis de Estrutura (1 a 5)
    private int nivelMotor = 1;
    private int nivelAero = 1;
    private int nivelChassi = 1;
    private int nivelConfiabilidade = 1;

    // Staff (Funcionários) (1 a 10)
    private int staffMotor = 1;
    private int staffAero = 1;
    private int staffChassi = 1;
    private int staffConfiabilidade = 1;

    private Arquivos arquivos;
    private List<String> pilotosContratadosIDs = new ArrayList<>();
    
    // Campos de Lógica
    private int anosConsecutivosNoVermelho = 0;
    
    private transient List<Piloto> pilotosTitulares = new ArrayList<>();
    private transient List<Piloto> pilotosReservas = new ArrayList<>();
    private transient List<Patrocinador> patrocinadoresAtivos = new ArrayList<>();
    private transient Categoria categoriaAtual;
    
    // --- NOVO: VÍNCULO COM OBJETO MOTOR ---
    private transient Motor motorObjeto; 

    public Equipe() {
        this.arquivos = new Arquivos(); 
    }

    public Equipe(String nome, double saldoInicial, int reputacao) {
        this.nome = nome;
        this.saldoFinanceiro = saldoInicial;
        this.reputacao = reputacao;
        this.arquivos = new Arquivos();
    }
    
    // --- MÉTODOS DO MOTOR (Passo 3) ---
    public void setMotorObjeto(Motor m) {
        this.motorObjeto = m;
    }
    
    public Motor getMotorObjeto() { return motorObjeto; }
    
    // --- INICIALIZAÇÃO DA FÁBRICA ---
    public void inicializarFabricaInteligente() {
        if (reputacao >= 90) definirNiveis(5);      
        else if (reputacao >= 75) definirNiveis(4); 
        else if (reputacao >= 50) definirNiveis(3); 
        else if (reputacao >= 30) definirNiveis(2); 
        else definirNiveis(1);                      
        
        this.staffMotor = 1;
        this.staffAero = 1;
        this.staffChassi = 1;
        this.staffConfiabilidade = 1;
    }

    private void definirNiveis(int n) {
        this.nivelMotor = n;
        this.nivelAero = n;
        this.nivelChassi = n;
        this.nivelConfiabilidade = n;
    }

    // --- MÉTODOS DE EVOLUÇÃO ---
    public boolean contratarStaffMotor() {
        if (staffMotor < 10) { staffMotor++; return true; } return false;
    }
    public boolean contratarStaffAero() {
        if (staffAero < 10) { staffAero++; return true; } return false;
    }
    public boolean contratarStaffChassi() {
        if (staffChassi < 10) { staffChassi++; return true; } return false;
    }
    public boolean contratarStaffConfiabilidade() {
        if (staffConfiabilidade < 10) { staffConfiabilidade++; return true; } return false;
    }

    public boolean subirNivelMotor() {
        if (nivelMotor < 5) { nivelMotor++; staffMotor = 1; return true; } return false;
    }
    public boolean subirNivelAero() {
        if (nivelAero < 5) { nivelAero++; staffAero = 1; return true; } return false;
    }
    public boolean subirNivelChassi() {
        if (nivelChassi < 5) { nivelChassi++; staffChassi = 1; return true; } return false;
    }
    public boolean subirNivelConfiabilidade() {
        if (nivelConfiabilidade < 5) { nivelConfiabilidade++; staffConfiabilidade = 1; return true; } return false;
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
    
    public int getNivelMotor() { return nivelMotor; }
    public int getNivelAero() { return nivelAero; }
    public int getNivelChassi() { return nivelChassi; }
    public int getNivelConfiabilidade() { return nivelConfiabilidade; }
    
    public int getStaffMotor() { return staffMotor; }
    public int getStaffAero() { return staffAero; }
    public int getStaffChassi() { return staffChassi; }
    public int getStaffConfiabilidade() { return staffConfiabilidade; }

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
    
    // --- HELPER METHODS IMAGENS (ATUALIZADO) ---
    
    public String getCaminhoLogo() {
        if (arquivos != null && arquivos.logo != null) return arquivos.logo;
        return "/resource/Icone64pxErro.png";
    }
    
    public String getCaminhoBandeiraSede() {
        if (arquivos != null && arquivos.bandeiraSede != null) return arquivos.bandeiraSede;
        return "/resource/Bandeira BRANCA.png";
    }
    
    public String getCaminhoBandeiraMotor() {
        // PRIORIDADE 1: Se tivermos o objeto Motor vinculado, usamos a bandeira dele
        if (motorObjeto != null) {
            return motorObjeto.getCaminhoBandeira();
        }
        // PRIORIDADE 2: Fallback para o arquivo equipes.json (comportamento antigo)
        if (arquivos != null && arquivos.bandeiraMotor != null) return arquivos.bandeiraMotor;
        return "/resource/Bandeira BRANCA.png";
    }
    
    public String getCaminhoLogoMotor() {
        // PRIORIDADE 1: Objeto Motor
        if (motorObjeto != null) {
            return motorObjeto.getCaminhoLogo();
        }
        // PRIORIDADE 2: Fallback
        if (arquivos != null && arquivos.logoMotor != null) return arquivos.logoMotor;
        return "/resource/Icone16pxErro.png";
    }
}
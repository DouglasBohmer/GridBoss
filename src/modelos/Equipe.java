package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipe {
    // --- CAMPOS DO JSON ---
    private String id;
    private String nome;
    private String sede;
    private String motor;
    private int fundacao;
    private double saldoFinanceiro;
    private int reputacao;      
    
    // --- ESTATÍSTICAS ---
    private int pontos;
    private int vitorias;
    private int podios;
    private int poles;
    
    // --- FÁBRICA & DESENVOLVIMENTO ---
    private int nivelMotor = 1;
    private int nivelAero = 1;
    private int nivelChassi = 1;
    private int nivelConfiabilidade = 1;

    private int staffMotor = 1;
    private int staffAero = 1;
    private int staffChassi = 1;
    private int staffConfiabilidade = 1;

    private Arquivos arquivos;
    private List<String> pilotosContratadosIDs = new ArrayList<>();
    
    // Lógica
    private int anosConsecutivosNoVermelho = 0;
    
    private transient List<Piloto> pilotosTitulares = new ArrayList<>();
    private transient List<Piloto> pilotosReservas = new ArrayList<>();
    private List<Patrocinador> patrocinadoresAtivos = new ArrayList<>();
    private transient Categoria categoriaAtual;
    private transient Motor motorObjeto; 

    public Equipe() { this.arquivos = new Arquivos(); }

    public Equipe(String nome, double saldoInicial, int reputacao) {
        this.nome = nome;
        this.saldoFinanceiro = saldoInicial;
        this.reputacao = reputacao;
        this.arquivos = new Arquivos();
    }
    
    // --- PREPARAÇÃO PARA SAVE ---
    public void sincronizarDadosParaSalvar() {
        this.pilotosContratadosIDs.clear();
        for (Piloto p : pilotosTitulares) this.pilotosContratadosIDs.add(p.getNome());
        for (Piloto p : pilotosReservas) this.pilotosContratadosIDs.add(p.getNome());
        if (this.motorObjeto != null) this.motor = this.motorObjeto.getId();
    }
    
    public void setMotorObjeto(Motor m) { this.motorObjeto = m; }
    public Motor getMotorObjeto() { return motorObjeto; }
    
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

    // --- REGRAS DE STAFF ---
    public int getLimiteStaff(int nivel) {
        switch (nivel) {
            case 1: return 5;
            case 2: return 10;
            case 3: return 15;
            case 4: return 20;
            case 5: return 30;
            default: return 5;
        }
    }

    private int calcularStaffAposUpgrade(int nivelNovo, int staffAtual) {
        if (nivelNovo == 2) return 5;
        return Math.max(staffAtual, 10);
    }

    // --- EVOLUIR NÍVEL ---
    public boolean subirNivelMotor() { 
        if (nivelMotor < 5) { 
            nivelMotor++; 
            staffMotor = calcularStaffAposUpgrade(nivelMotor, staffMotor);
            return true; 
        } 
        return false; 
    }
    public boolean subirNivelAero() { 
        if (nivelAero < 5) { 
            nivelAero++; 
            staffAero = calcularStaffAposUpgrade(nivelAero, staffAero);
            return true; 
        } 
        return false; 
    }
    public boolean subirNivelChassi() { 
        if (nivelChassi < 5) { 
            nivelChassi++; 
            staffChassi = calcularStaffAposUpgrade(nivelChassi, staffChassi);
            return true; 
        } 
        return false; 
    }
    public boolean subirNivelConfiabilidade() { 
        if (nivelConfiabilidade < 5) { 
            nivelConfiabilidade++; 
            staffConfiabilidade = calcularStaffAposUpgrade(nivelConfiabilidade, staffConfiabilidade);
            return true; 
        } 
        return false; 
    }

    // --- CONTRATAR STAFF ---
    public boolean contratarStaffMotor() { if (staffMotor < getLimiteStaff(nivelMotor)) { staffMotor++; return true; } return false; }
    public boolean contratarStaffAero() { if (staffAero < getLimiteStaff(nivelAero)) { staffAero++; return true; } return false; }
    public boolean contratarStaffChassi() { if (staffChassi < getLimiteStaff(nivelChassi)) { staffChassi++; return true; } return false; }
    public boolean contratarStaffConfiabilidade() { if (staffConfiabilidade < getLimiteStaff(nivelConfiabilidade)) { staffConfiabilidade++; return true; } return false; }

    // --- DEMITIR STAFF ---
    public boolean demitirStaffMotor() { if (staffMotor > 1) { staffMotor--; return true; } return false; }
    public boolean demitirStaffAero() { if (staffAero > 1) { staffAero--; return true; } return false; }
    public boolean demitirStaffChassi() { if (staffChassi > 1) { staffChassi--; return true; } return false; }
    public boolean demitirStaffConfiabilidade() { if (staffConfiabilidade > 1) { staffConfiabilidade--; return true; } return false; }

    // --- DOWNGRADE DE NÍVEL ---
    public double downgradeNivelMotor() {
        if (nivelMotor > 1) { nivelMotor--; return ajustarStaffAposDowngrade(1); } return 0.0;
    }
    public double downgradeNivelAero() {
        if (nivelAero > 1) { nivelAero--; return ajustarStaffAposDowngrade(2); } return 0.0;
    }
    public double downgradeNivelChassi() {
        if (nivelChassi > 1) { nivelChassi--; return ajustarStaffAposDowngrade(3); } return 0.0;
    }
    public double downgradeNivelConfiabilidade() {
        if (nivelConfiabilidade > 1) { nivelConfiabilidade--; return ajustarStaffAposDowngrade(4); } return 0.0;
    }

    private double ajustarStaffAposDowngrade(int tipo) {
        int nivelAtual = 0;
        int staffAtual = 0;
        switch(tipo) {
            case 1: nivelAtual = nivelMotor; staffAtual = staffMotor; break;
            case 2: nivelAtual = nivelAero; staffAtual = staffAero; break;
            case 3: nivelAtual = nivelChassi; staffAtual = staffChassi; break;
            case 4: nivelAtual = nivelConfiabilidade; staffAtual = staffConfiabilidade; break;
        }
        
        int tetoNovo = getLimiteStaff(nivelAtual);
        double reembolso = 0.0;
        
        if (staffAtual > tetoNovo) {
            int excedente = staffAtual - tetoNovo;
            reembolso = excedente * 0.25; 
            staffAtual = tetoNovo;
        }
        
        switch(tipo) {
            case 1: staffMotor = staffAtual; break;
            case 2: staffAero = staffAtual; break;
            case 3: staffChassi = staffAtual; break;
            case 4: staffConfiabilidade = staffAtual; break;
        }
        return reembolso;
    }

    // --- PERFORMANCE ---
    public double getForcaMotorCalculada() { return (nivelMotor * 20) + (staffMotor * 2); }
    public double getForcaAeroCalculada() { return (nivelAero * 20) + (staffAero * 2); }
    public double getForcaChassiCalculada() { return (nivelChassi * 20) + (staffChassi * 2); }
    public double getForcaConfiabilidadeCalculada() { return (nivelConfiabilidade * 20) + (staffConfiabilidade * 2); }
    
    public double getCustoMensalFabrica() {
        double custoStaff = (staffMotor + staffAero + staffChassi + staffConfiabilidade) * 0.01;
        double custoNiveis = (nivelMotor + nivelAero + nivelChassi + nivelConfiabilidade) * 0.1;
        return custoStaff + custoNiveis;
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

    // --- MÉTODOS DE CONTRATAÇÃO / FINANCEIRO ---
    public boolean contratarPiloto(Piloto piloto, double salario, double custoAssinatura, int meses, TipoContrato tipo) {
        if (categoriaAtual != null) {
            if (tipo == TipoContrato.TITULAR && pilotosTitulares.size() >= categoriaAtual.getMaxTitulares()) return false;
            if (tipo == TipoContrato.RESERVA && pilotosReservas.size() >= categoriaAtual.getMaxReservas()) return false;
        }
        
        double custoFinalAssinatura = custoAssinatura;
        
        // CORREÇÃO: Verifica se o piloto já está na lista de reservas da PRÓPRIA equipe
        if (tipo == TipoContrato.TITULAR && pilotosReservas.contains(piloto)) {
            custoFinalAssinatura = custoAssinatura * 0.5; // Desconto na "taxa" se promovido
            pilotosReservas.remove(piloto);
        }
        
        if (saldoFinanceiro >= custoFinalAssinatura) {
            this.saldoFinanceiro -= custoFinalAssinatura;
            
            // CORREÇÃO: Ordem dos parâmetros do Contrato (Equipe, Salario, Meses, Tipo)
            Contrato novoContrato = new Contrato(this, salario, meses, tipo);
            
            // CORREÇÃO: Método correto para associar contrato ao piloto
            piloto.setContrato(novoContrato);
            
            if (tipo == TipoContrato.TITULAR) pilotosTitulares.add(piloto);
            else pilotosReservas.add(piloto);
            
            return true;
        }
        return false;
    }
    
    public void adicionarPilotoDoLoad(Piloto p, TipoContrato tipo) {
        if (tipo == TipoContrato.TITULAR) pilotosTitulares.add(p);
        else pilotosReservas.add(p);
        
        // CORREÇÃO: Ordem dos parâmetros
        Contrato c = new Contrato(this, 0.5, 12, tipo); 
        
        // CORREÇÃO: Método correto
        p.setContrato(c);
    }

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
    
    public void setCategoriaAtual(Categoria categoriaAtual) { this.categoriaAtual = categoriaAtual; }
    
    public String getCaminhoLogo() {
        if (arquivos != null && arquivos.logo != null) return arquivos.logo;
        return "/resource/Icone64pxErro.png";
    }
    public String getCaminhoBandeiraSede() {
        if (arquivos != null && arquivos.bandeiraSede != null) return arquivos.bandeiraSede;
        return "/resource/Bandeira BRANCA.png";
    }
    public String getCaminhoBandeiraMotor() {
        if (motorObjeto != null) return motorObjeto.getCaminhoBandeira();
        if (arquivos != null && arquivos.bandeiraMotor != null) return arquivos.bandeiraMotor;
        return "/resource/Bandeira BRANCA.png";
    }
    public String getCaminhoLogoMotor() {
        if (motorObjeto != null) return motorObjeto.getCaminhoLogo();
        if (arquivos != null && arquivos.logoMotor != null) return arquivos.logoMotor;
        return "/resource/Icone16pxErro.png";
    }
}
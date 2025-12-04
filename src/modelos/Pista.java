package modelos;

public class Pista {
    // --- DADOS DO JSON ---
    private int rodada;           
    private String nome;          
    private String etapa;         
    private String pais;          
    private TipoPista tipo;       
    private int qtdVoltas;        
    private double comprimentoKm; 
    
    // --- JOGABILIDADE ---
    private int dificuldadeUltrapassagem; 
    private double relevanciaMotor;       
    private double relevanciaAerodinamica; 
    
    private double fatorDesgastePneu;
    private double fatorConsumoCombustivel;
    private double chanceSafetyCar;
    private double tempoPerdidoPitLane;
    
    // Objeto aninhado para imagens
    private Arquivos arquivos;

    public static class Arquivos {
        public String traçado;
        public String traçadoSvg;
        public String bandeira;
        public String bandeiraSvg;
    }

    public Pista() {
        this.arquivos = new Arquivos();
    }

    // Construtor completo atualizado (sem fasePlayoff, pois vai no nome)
    public Pista(int rodada, String nome, String etapa, String pais, TipoPista tipo, int voltas, double km) {
        this();
        this.rodada = rodada;
        this.nome = nome;
        this.etapa = etapa;
        this.pais = pais;
        this.tipo = tipo;
        this.qtdVoltas = voltas;
        this.comprimentoKm = km;
    }

    // --- GETTERS ---
    public int getRodada() { return rodada; }
    public String getNome() { return nome; }
    public String getEtapa() { return etapa; } // Novo Getter
    public String getPais() { return pais; }
    public TipoPista getTipo() { return tipo; }
    public int getQtdVoltas() { return qtdVoltas; }
    public double getComprimentoKm() { return comprimentoKm; }
    
    public int getDificuldadeUltrapassagem() { return dificuldadeUltrapassagem; }
    public double getRelevanciaMotor() { return relevanciaMotor; }
    public double getRelevanciaAerodinamica() { return relevanciaAerodinamica; }
    
    public double getFatorDesgastePneu() { return fatorDesgastePneu; }
    public double getFatorConsumoCombustivel() { return fatorConsumoCombustivel; }
    public double getChanceSafetyCar() { return chanceSafetyCar; }
    public double getTempoPerdidoPitLane() { return tempoPerdidoPitLane; }

    public String getCaminhoTracado() {
        if (arquivos != null && arquivos.traçado != null) return arquivos.traçado;
        return "/resource/Icone64pxErro.png";
    }
    
    public String getCaminhoBandeira() {
        if (arquivos != null && arquivos.bandeira != null) return arquivos.bandeira;
        return "/resource/Bandeira BRANCA.png";
    }
    
    public boolean isOval() {
        if (tipo == null) return false;
        return tipo.isOval();
    }
    
    public boolean isRua() {
        if (tipo == null) return false;
        return tipo.isRua();
    }
}
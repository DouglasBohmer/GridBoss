package modelos;

public class Pista {
    // --- DADOS DO JSON ---
    private int rodada;           // Ex: 1
    private String nome;          // Ex: "Grande Prêmio do Bahrein"
    private String pais;          // Ex: "Bahrain"
    private TipoPista tipo;       // OVAL, MISTO, RUA...
    private int qtdVoltas;        // Ex: 57
    private double comprimentoKm; // Ex: 5.412
    
    // --- JOGABILIDADE ---
    private int dificuldadeUltrapassagem; // 0 a 100
    private double relevanciaMotor;       // 0.0 a 1.0
    private double relevanciaAerodinamica; // 0.0 a 1.0
    
    private double fatorDesgastePneu;
    private double fatorConsumoCombustivel;
    private double chanceSafetyCar;
    private double tempoPerdidoPitLane;
    
    // Objeto aninhado para imagens
    private Arquivos arquivos;

    // --- CLASSE INTERNA PARA JSON ---
    public static class Arquivos {
        public String traçado;
        public String traçadoSvg;
        public String bandeira;
        public String bandeiraSvg;
    }

    // Construtor vazio para o Gson
    public Pista() {
        this.arquivos = new Arquivos();
    }

    // Construtor completo (para testes manuais)
    public Pista(String nome, String pais, TipoPista tipo, int voltas, double km,
                 int dificuldadeUltra, double relevanciaMotor, 
                 double desgastePneu, double consumoComb, 
                 double chanceSC, double tempoPit) {
        this();
        this.nome = nome;
        this.pais = pais;
        this.tipo = tipo;
        this.qtdVoltas = voltas;
        this.comprimentoKm = km;
        this.dificuldadeUltrapassagem = dificuldadeUltra;
        this.relevanciaMotor = relevanciaMotor;
        this.relevanciaAerodinamica = 1.0 - relevanciaMotor; // Cálculo automático padrão
        this.fatorDesgastePneu = desgastePneu;
        this.fatorConsumoCombustivel = consumoComb;
        this.chanceSafetyCar = chanceSC;
        this.tempoPerdidoPitLane = tempoPit;
    }

    // --- MÉTODOS ÚTEIS ---

    public boolean isOval() {
        if (tipo == null) return false;
        return tipo.isOval(); // Usa o método do Enum TipoPista
    }
    
    public boolean isRua() {
        if (tipo == null) return false;
        return tipo.isRua();
    }

    // --- GETTERS DE IMAGEM (Seguros) ---
    
    public String getCaminhoTracado() {
        if (arquivos != null && arquivos.traçado != null) return arquivos.traçado;
        return "/resource/Icone64pxErro.png";
    }
    
    public String getCaminhoBandeira() {
        if (arquivos != null && arquivos.bandeira != null) return arquivos.bandeira;
        return "/resource/Bandeira BRANCA.png";
    }

    // --- GETTERS PADRÃO ---
    public int getRodada() { return rodada; }
    public String getNome() { return nome; }
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
}
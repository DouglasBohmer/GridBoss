package modelos;

public class Pista {
    // --- DADOS BÁSICOS ---
    private String nome;          // Ex: "Autódromo de Interlagos"
    private String pais;          // Ex: "Brasil" (para a bandeira)
    private TipoPista tipo;       // OVAL, MISTO, RUA...
    private int qtdVoltas;        // Ex: 71
    private double comprimentoKm; // Ex: 4.309 km
    
    // --- DADOS DE JOGABILIDADE (O "DNA" da Pista) ---
    
    /**
     * Define o quão difícil é passar nesta pista (0 a 100).
     * 0 = Muito Fácil (Daytona/Talladega - Ovais gigantes)
     * 50 = Médio (Interlagos)
     * 95 = Impossível (Mônaco)
     */
    private int dificuldadeUltrapassagem;
    
    /**
     * Define se a pista favorece MOTOR ou AERODINÂMICA.
     * Valor de 0.0 a 1.0.
     * 1.0 = 100% Motor (Monza, Ovais rápidos).
     * 0.0 = 100% Aerodinâmica (Mônaco, Hungaroring).
     * 0.5 = Equilibrado.
     */
    private double relevanciaMotor; 
    
    /**
     * Multiplicadores de consumo (Base = 1.0).
     * Pistas abrasivas (Bahrein) podem ter 1.5.
     * Pistas lisas (Sochi) podem ter 0.8.
     */
    private double fatorDesgastePneu;
    private double fatorConsumoCombustivel;
    
    /**
     * Chance base de acontecer um acidente por volta (0 a 100).
     * Pistas de rua (paredes próximas) têm chance maior.
     * Ovais curtos (tráfego intenso) também.
     */
    private double chanceSafetyCar;
    
    /**
     * Tempo médio perdido ao entrar e sair do box (sem contar a parada).
     * Ex: 20 segundos.
     */
    private double tempoPerdidoPitLane;

    // Construtor Completo
    public Pista(String nome, String pais, TipoPista tipo, int voltas, double km,
                 int dificuldadeUltra, double relevanciaMotor, 
                 double desgastePneu, double consumoComb, 
                 double chanceSC, double tempoPit) {
        this.nome = nome;
        this.pais = pais;
        this.tipo = tipo;
        this.qtdVoltas = voltas;
        this.comprimentoKm = km;
        this.dificuldadeUltrapassagem = dificuldadeUltra;
        this.relevanciaMotor = relevanciaMotor;
        this.fatorDesgastePneu = desgastePneu;
        this.fatorConsumoCombustivel = consumoComb;
        this.chanceSafetyCar = chanceSC;
        this.tempoPerdidoPitLane = tempoPit;
    }

    // --- MÉTODOS ÚTEIS PARA A CORRIDA ---

    public double getRelevanciaAerodinamica() {
        // Se motor é 0.7 (70%), Aero é o que sobra (0.3 ou 30%)
        return 1.0 - relevanciaMotor;
    }
    
    public boolean isOval() {
        return tipo == TipoPista.OVAL_CURTO || tipo == TipoPista.OVAL_SPEEDWAY;
    }

    // --- GETTERS ---
    public String getNome() { return nome; }
    public String getPais() { return pais; }
    public int getQtdVoltas() { return qtdVoltas; }
    public int getDificuldadeUltrapassagem() { return dificuldadeUltrapassagem; }
    public double getRelevanciaMotor() { return relevanciaMotor; }
    public double getFatorDesgastePneu() { return fatorDesgastePneu; }
    public double getFatorConsumoCombustivel() { return fatorConsumoCombustivel; }
    public double getChanceSafetyCar() { return chanceSafetyCar; }
    public double getTempoPerdidoPitLane() { return tempoPerdidoPitLane; }
}
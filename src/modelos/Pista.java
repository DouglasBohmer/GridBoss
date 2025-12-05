package modelos;

public class Pista {
    // Campos carregados do JSON
    private int rodada;
    private String nome;
    private String pais;
    private TipoPista tipo;
    private int qtdVoltas;
    private double comprimentoKm;
    
    // NOVO CAMPO: Tempo de Volta Base (em segundos)
    // Ex: Bahrein = 91.5 (1m31s)
    private double tempoBase; 
    
    private int dificuldadeUltrapassagem; // 0 a 100
    private double relevanciaMotor;       // 0.0 a 1.0 (ex: Monza = 0.9)
    private double relevanciaAerodinamica;// 0.0 a 1.0 (ex: Mônaco = 0.9)
    
    private double fatorDesgastePneu;        // 1.0 = Normal
    private double fatorConsumoCombustivel;  // 1.0 = Normal
    
    private double chanceSafetyCar;      // Porcentagem (0 a 100)
    private double tempoPerdidoPitLane;  // Ex: 20.0 segundos
    
    private Arquivos arquivos;

    // Classe interna para caminhos de imagem
    public static class Arquivos {
        public String traçado;
        public String traçadoSvg;
        public String bandeira;
        public String bandeiraSvg;
    }

    // --- GETTERS ---
    public int getRodada() { return rodada; }
    public String getNome() { return nome; }
    public String getPais() { return pais; }
    public TipoPista getTipo() { return tipo; }
    public int getQtdVoltas() { return qtdVoltas; }
    public double getComprimentoKm() { return comprimentoKm; }
    
    // Getter do novo campo
    public double getTempoBase() { 
        // Fallback de segurança: Se esquecer de por no JSON, calcula aproximado
        if (tempoBase <= 0) {
            return comprimentoKm * 18.0; 
        }
        return tempoBase; 
    }
    
    public int getDificuldadeUltrapassagem() { return dificuldadeUltrapassagem; }
    public double getRelevanciaMotor() { return relevanciaMotor; }
    public double getRelevanciaAerodinamica() { return relevanciaAerodinamica; }
    public double getFatorDesgastePneu() { return fatorDesgastePneu; }
    public double getFatorConsumoCombustivel() { return fatorConsumoCombustivel; }
    public double getChanceSafetyCar() { return chanceSafetyCar; }
    public double getTempoPerdidoPitLane() { return tempoPerdidoPitLane; }

    // Helpers de Imagem
    public String getCaminhoTracado() {
        if (arquivos != null && arquivos.traçado != null) return arquivos.traçado;
        return null;
    }
    
    public String getCaminhoBandeira() {
        if (arquivos != null && arquivos.bandeira != null) return arquivos.bandeira;
        return null;
    }
    
    // Auxiliar para Interface (ex: "57 voltas")
    public String getEtapa() {
        return "Etapa " + rodada;
    }

    public boolean isOval() {
        return tipo == TipoPista.OVAL_CURTO || tipo == TipoPista.OVAL_SPEEDWAY || tipo == TipoPista.SUPERSPEEDWAY;
    }
}
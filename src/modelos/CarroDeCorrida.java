package modelos;

public class CarroDeCorrida {
    private Piloto piloto;
    private Equipe equipe;
    
    // Estado Dinâmico (Muda toda volta)
    private double tempoTotalDeProva = 0.0;
    private double tempoDaUltimaVolta = 0.0;
    
    // Física
    private double desgastePneus = 100.0; // 100% = Novo, 0% = Estourado
    private double combustivel = 0.0; // Em litros/kg
    private String compostoPneuAtual = "MÉDIO"; // Padrão inicial
    
    // Estratégia
    private ModoPilotagem modoAtual = ModoPilotagem.NEUTRO;
    private boolean parouNoBoxEstaVolta = false;
    private int qtdParadas = 0;

    public CarroDeCorrida(Piloto piloto) {
        this.piloto = piloto;
        // Pega a equipe do contrato atual do piloto
        if (piloto.getContrato() != null) {
            this.equipe = piloto.getContrato().getEquipeAtual();
        }
    }

    // --- MÉTODOS DE SIMULAÇÃO ---

    public void adicionarTempo(double segundos) {
        this.tempoDaUltimaVolta = segundos;
        this.tempoTotalDeProva += segundos;
    }

    public void desgastarPneu(double fatorPista) {
        // Base de desgaste x Modo de Pilotagem x Abrasividade da Pista
        double desgasteBase = 1.5; // ex: 1.5% por volta
        double desgasteReal = desgasteBase * modoAtual.getFatorDesgaste() * fatorPista;
        
        this.desgastePneus -= desgasteReal;
        if (this.desgastePneus < 0) this.desgastePneus = 0;
    }

    public void consumirCombustivel(double fatorPista) {
        double consumoBase = 2.0; // Litros por volta
        double consumoReal = consumoBase * modoAtual.getFatorConsumo() * fatorPista;
        
        this.combustivel -= consumoReal;
        if (this.combustivel < 0) this.combustivel = 0;
    }

    // --- COMANDOS DO JOGADOR ---
    
    public void setModoPilotagem(ModoPilotagem modo) {
        this.modoAtual = modo;
    }

    // --- GETTERS ---
    public Piloto getPiloto() { return piloto; }
    public Equipe getEquipe() { return equipe; }
    public double getTempoTotal() { return tempoTotalDeProva; }
    public double getTempoUltimaVolta() { return tempoDaUltimaVolta; }
    public double getDesgastePneus() { return desgastePneus; }
    public double getCombustivel() { return combustivel; }
    public ModoPilotagem getModo() { return modoAtual; }
    public void adicionarCombustivel(double qtd) {
        this.combustivel += qtd;
    }
    
    // Auxiliar para ordenação (quem tem menor tempo está na frente)
    public Double getTempoParaOrdenacao() {
        return tempoTotalDeProva;
    }
}
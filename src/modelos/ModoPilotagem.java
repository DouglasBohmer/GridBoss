package modelos;

public enum ModoPilotagem {
    POUPAR_PNEU(0.8, 0.9, 1.2),   // Lento, economiza pneu/gasolina
    CONSERVADOR(0.9, 0.95, 1.1),
    NEUTRO(1.0, 1.0, 1.0),        // Padrão
    AGRESSIVO(1.1, 1.2, 0.9),     // Rápido, gasta mais
    ATAQUE_TOTAL(1.2, 1.5, 0.8);  // Muito rápido, derrete o pneu

    // Multiplicadores
    private final double fatorVelocidade;
    private final double fatorDesgaste;
    private final double fatorConsumo;

    ModoPilotagem(double velocidade, double desgaste, double consumo) {
        this.fatorVelocidade = velocidade;
        this.fatorDesgaste = desgaste;
        this.fatorConsumo = consumo;
    }

    public double getFatorVelocidade() { return fatorVelocidade; }
    public double getFatorDesgaste() { return fatorDesgaste; }
    public double getFatorConsumo() { return fatorConsumo; }
}
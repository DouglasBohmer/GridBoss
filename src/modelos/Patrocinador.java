package modelos;

public class Patrocinador {
    // ... (Atributos anteriores: nome, valores, bonus) ...
    private String nome;
    private int mesesRestantes; // Duração dinâmica
    private double valorMensal;
    private double valorAssinatura;
    
    // Bônus
    private double bonusVitoria;
    private double bonusPodio;
    private double bonusTop10;

    public Patrocinador(String nome, int mesesDuracao, double valorAssinatura, double valorMensal) {
        this.nome = nome;
        this.mesesRestantes = mesesDuracao;
        this.valorAssinatura = valorAssinatura;
        this.valorMensal = valorMensal;
    }

    public void setBonus(double vitoria, double podio, double top10) {
        this.bonusVitoria = vitoria;
        this.bonusPodio = podio;
        this.bonusTop10 = top10;
    }

    public double calcularPremioCorrida(int pos) {
        if (pos == 1) return bonusVitoria;
        if (pos <= 3) return bonusPodio;
        if (pos <= 10) return bonusTop10;
        return 0.0;
    }

    public void reduzirMesRestante() {
        if (mesesRestantes > 0) {
            mesesRestantes--;
        }
    }

    public boolean expirou() {
        return mesesRestantes <= 0;
    }

    // Getters
    public String getNome() { return nome; }
    public double getValorMensal() { return valorMensal; }
    public double getValorAssinatura() { return valorAssinatura; }
    public int getMesesRestantes() { return mesesRestantes; }
}
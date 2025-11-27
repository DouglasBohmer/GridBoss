package modelos;

public class Contrato {
    private double salarioMensal;
    private int mesesRestantes; // Duração do contrato
    private Equipe equipeAtual;

    public Contrato(double salarioMensal, int mesesDeDuracao, Equipe equipe) {
        this.salarioMensal = salarioMensal;
        this.mesesRestantes = mesesDeDuracao;
        this.equipeAtual = equipe;
    }

    // Regra: Multa = Salário x Tempo Restante
    public double calcularMultaRescisoria() {
        return salarioMensal * mesesRestantes;
    }

    public void passarMes() {
        if (mesesRestantes > 0) {
            mesesRestantes--;
        }
    }

    public boolean expirou() {
        return mesesRestantes <= 0;
    }

    public double getSalarioMensal() { return salarioMensal; }
    public Equipe getEquipeAtual() { return equipeAtual; }
}
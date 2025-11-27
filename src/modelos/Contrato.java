package modelos;

public class Contrato {
    private double salarioMensal;
    private int mesesRestantes;
    private Equipe equipeAtual;
    private TipoContrato tipo; // TITULAR ou RESERVA

    public Contrato(double salarioMensal, int mesesDeDuracao, Equipe equipe, TipoContrato tipo) {
        this.salarioMensal = salarioMensal;
        this.mesesRestantes = mesesDeDuracao;
        this.equipeAtual = equipe;
        this.tipo = tipo;
    }

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
    public TipoContrato getTipo() { return tipo; }
}
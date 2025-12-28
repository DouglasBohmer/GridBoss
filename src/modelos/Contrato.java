package modelos;

public class Contrato {

    private Equipe equipeAtual;
    private double salarioMensal;
    private int mesesRestantes;
    private TipoContrato tipo; // Enum: TITULAR, RESERVA

    public Contrato() {
    }

    // --- ESSE É O CONSTRUTOR QUE ESTAVA FALTANDO ---
    public Contrato(Equipe equipeAtual, double salarioMensal, int mesesRestantes, TipoContrato tipo) {
        this.equipeAtual = equipeAtual;
        this.salarioMensal = salarioMensal;
        this.mesesRestantes = mesesRestantes;
        this.tipo = tipo;
    }

    // Método usado na tela para calcular o valor da multa
    public double calcularMultaRescisoria() {
        if (mesesRestantes <= 0) return 0;
        // Regra: Multa é 50% do valor total restante do contrato
        return (salarioMensal * mesesRestantes) * 0.5;
    }

    // --- Getters e Setters ---

    public Equipe getEquipeAtual() {
        return equipeAtual;
    }

    public void setEquipeAtual(Equipe equipeAtual) {
        this.equipeAtual = equipeAtual;
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(double salarioMensal) {
        this.salarioMensal = salarioMensal;
    }

    public int getMesesRestantes() {
        return mesesRestantes;
    }

    public void setMesesRestantes(int mesesRestantes) {
        this.mesesRestantes = mesesRestantes;
    }

    public TipoContrato getTipo() {
        return tipo;
    }

    public void setTipo(TipoContrato tipo) {
        this.tipo = tipo;
    }
}
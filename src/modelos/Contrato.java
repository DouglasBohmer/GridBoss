package modelos;

import java.util.List;

public class Contrato {

    // TRANSIENT: O Java IGNORA este campo ao salvar o JSON (evita erro de loop infinito)
    private transient Equipe equipeAtual;
    
    // Este campo será salvo no lugar do objeto Equipe
    private String nomeEquipeSalva;
    
    private double salarioMensal;
    private int mesesRestantes;
    private TipoContrato tipo;

    public Contrato() {}

    public Contrato(Equipe equipeAtual, double salarioMensal, int mesesRestantes, TipoContrato tipo) {
        this.equipeAtual = equipeAtual;
        this.salarioMensal = salarioMensal;
        this.mesesRestantes = mesesRestantes;
        this.tipo = tipo;
        // Já preenche o ID por garantia
        if (equipeAtual != null) {
            this.nomeEquipeSalva = equipeAtual.getNome();
        }
    }

    // --- LÓGICA DE SAVE/LOAD ---

    // Chamado ANTES de salvar o jogo
    public void prepararParaSalvar() {
        if (equipeAtual != null) {
            this.nomeEquipeSalva = equipeAtual.getNome();
        }
    }

    // Chamado DEPOIS de carregar o jogo
    public void reconectarEquipe(List<Equipe> listaTodasEquipes) {
        if (nomeEquipeSalva != null && !nomeEquipeSalva.isEmpty()) {
            for (Equipe e : listaTodasEquipes) {
                if (e.getNome().equalsIgnoreCase(nomeEquipeSalva)) {
                    this.equipeAtual = e;
                    break;
                }
            }
        }
    }

    public double calcularMultaRescisoria() {
        if (mesesRestantes <= 0) return 0;
        return (salarioMensal * mesesRestantes) * 0.5;
    }

    // --- Getters e Setters ---

    public Equipe getEquipeAtual() { return equipeAtual; }
    public void setEquipeAtual(Equipe equipeAtual) { 
        this.equipeAtual = equipeAtual;
        if(equipeAtual != null) this.nomeEquipeSalva = equipeAtual.getNome();
    }

    public double getSalarioMensal() { return salarioMensal; }
    public void setSalarioMensal(double salarioMensal) { this.salarioMensal = salarioMensal; }

    public int getMesesRestantes() { return mesesRestantes; }
    public void setMesesRestantes(int mesesRestantes) { this.mesesRestantes = mesesRestantes; }

    public TipoContrato getTipo() { return tipo; }
    public void setTipo(TipoContrato tipo) { this.tipo = tipo; }
    
    public String getNomeEquipeSalva() { return nomeEquipeSalva; }
}
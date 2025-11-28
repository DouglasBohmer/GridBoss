package modelos;

import java.util.ArrayList;
import java.util.List;

public class Piloto {
    private String nome;
    private String nacionalidade;
    private int numero; // Novo campo
    private int idade;
    private double overall; 
    private int exigenciaMinimaDeEquipe; 
    
    // Lista de contratos (Inicializada no construtor para evitar o NullPointerException)
    private List<Contrato> contratosAtivos;

    // --- CONSTRUTOR PADRÃO (Necessário para o Gson) ---
    public Piloto() {
        this.contratosAtivos = new ArrayList<>();
    }

    // Construtor completo (Atualizado com número)
    public Piloto(String nome, String pais, int numero, int idade, double overall, int exigenciaMinima) {
        this(); 
        this.nome = nome;
        this.nacionalidade = pais;
        this.numero = numero;
        this.idade = idade;
        this.overall = overall;
        this.exigenciaMinimaDeEquipe = exigenciaMinima;
    }

    /**
     * Tenta contratar o piloto.
     */
    public String receberProposta(Equipe equipeInteressada, double ofertaSalarial, TipoContrato tipoVaga) {
        if (contratosAtivos == null) contratosAtivos = new ArrayList<>();

        // 1. Verifica Reputação
        if (equipeInteressada.getReputacao() < this.exigenciaMinimaDeEquipe) {
            return "RECUSADO: Reputação baixa.";
        }

        // 2. Verifica Conflitos de Contrato
        if (tipoVaga == TipoContrato.TITULAR) {
            if (isTitular()) {
                Contrato atual = getContratoTitular();
                double multa = atual.calcularMultaRescisoria();
                
                if (isReservaDaEquipe(equipeInteressada)) {
                    return "ACEITO_PROMOCAO"; 
                }

                if (equipeInteressada.getSaldoFinanceiro() < multa) {
                    return "RECUSADO: Sem dinheiro para multa (" + multa + ")";
                }
                return "ACEITO_COM_MULTA: " + multa;
            }
        } else {
            if (isTitular()) return "RECUSADO: Já sou titular em outra equipe.";
            if (contratosAtivos.size() >= 3) return "RECUSADO: Já sou reserva de 3 equipes.";
        }

        return "ACEITO";
    }

    public void assinarContrato(Contrato novoContrato) {
        if (contratosAtivos == null) contratosAtivos = new ArrayList<>(); 

        if (novoContrato.getTipo() == TipoContrato.TITULAR) {
            contratosAtivos.clear(); 
        }
        contratosAtivos.add(novoContrato);
    }

    // --- Métodos Auxiliares ---

    public boolean isTitular() {
        if (contratosAtivos == null) return false;
        for (Contrato c : contratosAtivos) {
            if (c.getTipo() == TipoContrato.TITULAR) return true;
        }
        return false;
    }
    
    public Contrato getContratoTitular() {
        if (contratosAtivos == null) return null;
        for (Contrato c : contratosAtivos) {
            if (c.getTipo() == TipoContrato.TITULAR) return c;
        }
        return null;
    }

    public Contrato getContrato() {
        if (contratosAtivos == null || contratosAtivos.isEmpty()) return null;
        Contrato titular = getContratoTitular();
        if (titular != null) return titular;
        return contratosAtivos.get(0);
    }

    public boolean isReservaDaEquipe(Equipe e) {
        if (contratosAtivos == null) return false;
        for (Contrato c : contratosAtivos) {
            if (c.getTipo() == TipoContrato.RESERVA && c.getEquipeAtual() == e) {
                return true;
            }
        }
        return false;
    }

    // Getters
    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }
    public int getNumero() { return numero; } // Novo Getter
    public int getIdade() { return idade; }
    public double getOverall() { return overall; }
    public void setOverall(double over) { this.overall = over; }
    public List<Contrato> getContratos() { return contratosAtivos; }
}
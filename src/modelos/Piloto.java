package modelos;

import java.util.ArrayList;
import java.util.List;

public class Piloto {
    private String nome;
    private String nacionalidade;
    private int idade;
    private double overall; 
    private int exigenciaMinimaDeEquipe; 
    
    // Lista de contratos (Pode ter 1 titular OU até 3 reservas)
    private List<Contrato> contratosAtivos = new ArrayList<>();

    public Piloto(String nome, String pais, int idade, double overall, int exigenciaMinima) {
        this.nome = nome;
        this.nacionalidade = pais;
        this.idade = idade;
        this.overall = overall;
        this.exigenciaMinimaDeEquipe = exigenciaMinima;
    }

    /**
     * Tenta contratar o piloto.
     * @param ofertaSalarial Valor oferecido
     * @param tipoVaga TITULAR ou RESERVA
     */
    public String receberProposta(Equipe equipeInteressada, double ofertaSalarial, TipoContrato tipoVaga) {
        // 1. Verifica Reputação
        if (equipeInteressada.getReputacao() < this.exigenciaMinimaDeEquipe) {
            return "RECUSADO: Reputação baixa.";
        }

        // 2. Verifica Conflitos de Contrato
        if (tipoVaga == TipoContrato.TITULAR) {
            if (isTitular()) {
                // Se já é titular, tem que pagar multa para sair do atual
                Contrato atual = getContratoTitular();
                double multa = atual.calcularMultaRescisoria();
                
                // Regra: Se a equipe interessada for a mesma que ele é reserva (PROMOÇÃO)
                if (isReservaDaEquipe(equipeInteressada)) {
                    return "ACEITO_PROMOCAO"; 
                }

                if (equipeInteressada.getSaldoFinanceiro() < multa) {
                    return "RECUSADO: Sem dinheiro para multa (" + multa + ")";
                }
                return "ACEITO_COM_MULTA: " + multa;
            }
        } else {
            // Se for vaga de RESERVA
            if (isTitular()) return "RECUSADO: Já sou titular em outra equipe.";
            if (contratosAtivos.size() >= 3) return "RECUSADO: Já sou reserva de 3 equipes.";
        }

        return "ACEITO";
    }

    public void assinarContrato(Contrato novoContrato) {
        // Se for titular, limpa contratos anteriores (demissão do time antigo)
        if (novoContrato.getTipo() == TipoContrato.TITULAR) {
            contratosAtivos.clear(); 
        }
        contratosAtivos.add(novoContrato);
    }

    // --- Métodos Auxiliares ---

    public boolean isTitular() {
        for (Contrato c : contratosAtivos) {
            if (c.getTipo() == TipoContrato.TITULAR) return true;
        }
        return false;
    }
    
    public Contrato getContratoTitular() {
        for (Contrato c : contratosAtivos) {
            if (c.getTipo() == TipoContrato.TITULAR) return c;
        }
        return null;
    }

    // Método principal para pegar o contrato vigente (prioriza titular)
    // Esse é o método que estava faltando e gerando erro no Service
    public Contrato getContrato() {
        if (contratosAtivos.isEmpty()) return null;
        // Retorna o titular se tiver, senão o primeiro da lista
        Contrato titular = getContratoTitular();
        if (titular != null) return titular;
        return contratosAtivos.get(0);
    }

    public boolean isReservaDaEquipe(Equipe e) {
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
    public int getIdade() { return idade; }
    public double getOverall() { return overall; }
    public void setOverall(double over) { this.overall = over; }
    public List<Contrato> getContratos() { return contratosAtivos; }
}
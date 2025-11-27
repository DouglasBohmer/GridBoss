package modelos;

public class Piloto {
    private String nome;
    private String nacionalidade;
    private int idade;
    
    // Atributos de Habilidade
    private double overall; 
    
    // Contrato (Pode ser null se estiver desempregado)
    private Contrato contratoAtual;
    
    // Exigência do piloto (0 a 100). 
    // Ex: Verstappen exige equipe nota 90+. Drugovich aceita nota 40+.
    private int exigenciaMinimaDeEquipe; 

    public Piloto(String nome, String pais, int idade, double overall, int exigenciaMinima) {
        this.nome = nome;
        this.nacionalidade = pais;
        this.idade = idade;
        this.overall = overall;
        this.exigenciaMinimaDeEquipe = exigenciaMinima;
    }

    /**
     * Simula a negociação com uma equipe.
     * Retorna uma String com o resultado ("ACEITO" ou o motivo da recusa).
     */
    public String receberProposta(Equipe equipeInteressada, double ofertaSalarial) {
        // 1. Verifica se a equipe tem reputação suficiente
        // (Agora o método getReputacao() existe na classe Equipe)
        if (equipeInteressada.getReputacao() < this.exigenciaMinimaDeEquipe) {
            return "RECUSADO: " + nome + " acha que a equipe " + equipeInteressada.getNome() + " não é competitiva o suficiente.";
        }
        
        // 2. Se tiver contrato vigente, verifica se a equipe paga a multa
        if (contratoAtual != null && !contratoAtual.expirou()) {
            double multa = contratoAtual.calcularMultaRescisoria();
            
            // (Agora o método getSaldoFinanceiro() existe na classe Equipe)
            if (equipeInteressada.getSaldoFinanceiro() < multa) {
                return "RECUSADO: A equipe não tem dinheiro para pagar a multa de " + multa;
            }
            
            // Se passar daqui, o jogador terá que confirmar o pagamento da multa na tela
            return "ACEITO_CONDICIONAL: Requer pagamento de multa de " + multa;
        }

        // Se estiver livre e a equipe for boa
        return "ACEITO";
    }

    public void assinarContrato(Contrato novoContrato) {
        this.contratoAtual = novoContrato;
    }

    // --- GETTERS ---

    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }
    public int getIdade() { return idade; }
    public double getOverall() { return overall; }
    public Contrato getContrato() { return contratoAtual; }
    
    public void setOverall(double over) { this.overall = over; }
}
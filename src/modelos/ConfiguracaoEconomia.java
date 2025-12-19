package modelos;

public class ConfiguracaoEconomia {
    // Valores em Milhões
    private double custoContratacao;      // ex: 0.5
    private double reembolsoDemissao;     // ex: 0.25
    private double salarioMensalPorPessoa;// ex: 0.01
    private double custoBaseEvolucao;     // ex: 5.0
    private double manutencaoPorNivel;    // ex: 0.1 (Custo fixo predial)

    // Construtor vazio para o CarregadorJSON (Gson/Jackson)
    public ConfiguracaoEconomia() {}

    // Getters
    public double getCustoContratacao() { return custoContratacao; }
    public double getReembolsoDemissao() { return reembolsoDemissao; }
    public double getSalarioMensalPorPessoa() { return salarioMensalPorPessoa; }
    public double getCustoBaseEvolucao() { return custoBaseEvolucao; }
    public double getManutencaoPorNivel() { return manutencaoPorNivel; }
    
    // Setters (se necessário para o carregador)
    public void setCustoContratacao(double v) { this.custoContratacao = v; }
    public void setReembolsoDemissao(double v) { this.reembolsoDemissao = v; }
    public void setSalarioMensalPorPessoa(double v) { this.salarioMensalPorPessoa = v; }
    public void setCustoBaseEvolucao(double v) { this.custoBaseEvolucao = v; }
    public void setManutencaoPorNivel(double v) { this.manutencaoPorNivel = v; }
}
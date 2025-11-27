package modelos;

public class Departamento {
    private String nome; // Ex: "Aerodinâmica", "Motor"
    private int nivel; // 1 a 5
    private int funcionariosAtuais; // 0 a 10
    
    // Custos (Configurável por dificuldade)
    private double custoPorFuncionarioMensal = 0.5; // 500k por mês
    private double custoUpgradeNivelBase = 10.0; // 10 Milhões para upar pro nível 2

    public Departamento(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.funcionariosAtuais = 1; // Começa com 1
    }

    /**
     * Calcula a porcentagem de desempenho atual (0 a 100).
     * Fórmula: Base do Nível + (Funcionários * 1%)
     */
    public double getDesempenhoAtual() {
        double base = 50.0;
        
        // Cada nível adiciona 10% na base (Nível 1=50, Nível 2=60...)
        if (nivel > 1) {
            base += (nivel - 1) * 10.0;
        }
        
        // Cada funcionário dá +1%
        double bonusStaff = funcionariosAtuais * 1.0;
        
        return base + bonusStaff;
    }

    public void contratarFuncionario(Equipe equipe) {
        if (funcionariosAtuais >= 10) {
            System.out.println("Departamento lotado! Faça upgrade do nível.");
            return;
        }
        
        // Custo de contratação (Luvas/Agenciamento) - Opcional
        // equipe.pagarDespesa(0.1); 
        
        funcionariosAtuais++;
    }

    public void demitirFuncionario() {
        if (funcionariosAtuais > 1) {
            funcionariosAtuais--;
        }
    }

    public void fazerUpgradeNivel(Equipe equipe) {
        if (nivel >= 5) {
            System.out.println("Nível máximo atingido!");
            return;
        }

        // Custo sobe conforme o nível
        double custoUpgrade = custoUpgradeNivelBase * nivel; 
        
        if (equipe.getSaldoFinanceiro() >= custoUpgrade) {
            equipe.pagarDespesa(custoUpgrade);
            nivel++;
            funcionariosAtuais = 1; // RESET DA EQUIPE (Regra do jogo)
            System.out.println("Upgrade concluído! " + nome + " agora é Nível " + nivel);
        } else {
            System.out.println("Saldo insuficiente para upgrade.");
        }
    }
    
    public double getCustoMensalTotal() {
        return funcionariosAtuais * custoPorFuncionarioMensal;
    }

    // Getters
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getFuncionariosAtuais() { return funcionariosAtuais; }
}
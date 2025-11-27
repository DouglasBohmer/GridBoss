package servicos;

import modelos.Equipe;
import modelos.Patrocinador;
import modelos.Piloto;
import java.util.List;

public class GestaoFinanceiraService {

    /**
     * Processa todas as finanças mensais da equipe.
     * Deve ser chamado sempre que o jogo virar o mês (Dia 1 ou 30).
     */
    public void processarFechamentoMensal(Equipe equipe) {
        double despesasTotais = 0;
        double receitasTotais = 0;

        // 1. Pagar Salários (Titulares)
        for (Piloto p : equipe.getPilotosTitulares()) {
             // Só paga se tiver contrato ativo
             if (p.getContrato() != null) {
                 despesasTotais += p.getContrato().getSalarioMensal();
             }
        }
        
        // 2. Pagar Salários (Reservas)
        for (Piloto p : equipe.getPilotosReservas()) {
             if (p.getContrato() != null) {
                 despesasTotais += p.getContrato().getSalarioMensal();
             }
        }

        // 3. Receber Mensalidade dos Patrocinadores Ativos
        for (Patrocinador patroc : equipe.getPatrocinadoresAtivos()) {
            receitasTotais += patroc.getValorMensal();
            // Lógica para reduzir duração do contrato
            patroc.reduzirMesRestante(); 
        }
        
        // Remove patrocinadores que acabaram de vencer
        equipe.limparPatrocinadoresVencidos();

        // 4. Aplicar no Saldo
        equipe.pagarDespesa(despesasTotais);
        equipe.receberReceita(receitasTotais);

        System.out.println("Mês processado para " + equipe.getNome());
        System.out.println("Receitas: " + receitasTotais + " | Despesas: " + despesasTotais);
        System.out.println("Saldo Atual: " + equipe.getSaldoFinanceiro());
    }

    /**
     * Processa prêmios de corrida (Bônus de patrocinadores).
     * @param posicoesChegada Lista com a posição final de CADA carro da equipe na corrida.
     * Ex: Se tem 2 carros e chegaram em 1º e 5º, a lista deve conter [1, 5].
     */
    public void processarPremiosDeCorrida(Equipe equipe, List<Integer> posicoesChegada) {
        double premios = 0;

        // Para cada carro que correu...
        for (int posicao : posicoesChegada) {
            
            // 1. Verifica Bônus de Patrocinadores (Paga por carro que atingiu a meta)
            for (Patrocinador patroc : equipe.getPatrocinadoresAtivos()) {
                premios += patroc.calcularPremioCorrida(posicao);
            }

            // 2. Adiciona prêmio da Organização da Corrida (Valor por chegada)
            premios += calcularPremioPosicao(posicao);
        }

        if (premios > 0) {
            equipe.receberReceita(premios);
            System.out.println("Prêmios de Corrida recebidos: " + premios);
        }
    }

    /**
     * Lógica simples de prêmio por posição (pode ser ajustada por categoria depois)
     */
    private double calcularPremioPosicao(int pos) {
        if (pos == 1) return 1.0; // 1 Milhão por vitória
        if (pos == 2) return 0.75;
        if (pos == 3) return 0.5;
        if (pos <= 10) return 0.1;
        return 0.0;
    }

    /**
     * Verifica se o jogo acabou (Game Over) no final do ano.
     * Retorna TRUE se a equipe faliu.
     */
    public boolean processarFechamentoAnual(Equipe equipe, int posicaoNoCampeonatoConstrutores) {
        // 1. Receber prêmio da TV/Campeonato
        double premioCampeonato = calcularPremioCampeonato(posicaoNoCampeonatoConstrutores); 
        equipe.receberReceita(premioCampeonato);
        System.out.println("Prêmio de Campeonato recebido: " + premioCampeonato);

        // 2. Checagem de Falência (Regra de 1 ano de tolerância)
        if (equipe.getSaldoFinanceiro() < 0) {
            equipe.incrementarAnosNoVermelho();
            System.out.println("ALERTA CRÍTICO: A equipe fechou o ano no vermelho!");
            
            if (equipe.getAnosConsecutivosNoVermelho() >= 2) {
                return true; // GAME OVER
            }
        } else {
            equipe.zerarAnosNoVermelho(); // Recuperou!
        }

        return false; // Jogo segue
    }

    private double calcularPremioCampeonato(int posicaoEquipe) {
        // Exemplo: Campeão ganha 100M, decai 5M por posição
        if (posicaoEquipe == 1) return 100.0;
        double valor = 100.0 - (posicaoEquipe * 5);
        return (valor > 0) ? valor : 0; // Não pode ser negativo
    }
}
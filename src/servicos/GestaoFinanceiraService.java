package servicos;

import modelos.Equipe;
import modelos.Patrocinador;
import modelos.Piloto;
import modelos.Contrato;

import java.util.List;

public class GestaoFinanceiraService {

    /**
     * Processa todas as finanças mensais da equipe.
     * Deve ser chamado sempre que o jogo virar o mês (Dia 1 ou 30).
     */
    public void processarFechamentoMensal(Equipe equipe) {
        double despesasTotais = 0;
        double receitasTotais = 0;

        // 1. Pagar Salários de Pilotos
        if (equipe.getPiloto1() != null) {
            despesasTotais += equipe.getPiloto1().getContrato().getSalarioMensal();
        }
        if (equipe.getPiloto2() != null) {
            despesasTotais += equipe.getPiloto2().getContrato().getSalarioMensal();
        }

        // 2. Receber Mensalidade dos Patrocinadores Ativos
        for (Patrocinador patroc : equipe.getPatrocinadoresAtivos()) {
            receitasTotais += patroc.getValorMensal();
            // Lógica para reduzir duração do contrato
            patroc.reduzirMesRestante(); 
        }
        
        // Remove patrocinadores que acabaram de vencer
        equipe.limparPatrocinadoresVencidos();

        // 3. Aplicar no Saldo
        equipe.pagarDespesa(despesasTotais);
        equipe.receberReceita(receitasTotais);

        System.out.println("Mês processado. Receitas: " + receitasTotais + " | Despesas: " + despesasTotais);
    }

    /**
     * Processa prêmios de corrida (Bônus de patrocinadores).
     * Deve ser chamado logo após a bandeirada final.
     */
    public void processarPremiosDeCorrida(Equipe equipe, int posicaoPiloto1, int posicaoPiloto2) {
        double premios = 0;

        for (Patrocinador patroc : equipe.getPatrocinadoresAtivos()) {
            // Verifica bônus para Piloto 1
            premios += patroc.calcularPremioCorrida(posicaoPiloto1);
            // Verifica bônus para Piloto 2
            premios += patroc.calcularPremioCorrida(posicaoPiloto2);
        }

        // Adiciona prêmio extra por posição (Regra do jogo: valor por chegada)
        premios += calcularPremioPosicao(posicaoPiloto1);
        premios += calcularPremioPosicao(posicaoPiloto2);

        if (premios > 0) {
            equipe.receberReceita(premios);
            System.out.println("Prêmios de Corrida recebidos: €" + premios);
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
    public boolean processarFechamentoAnual(Equipe equipe) {
        // 1. Receber prêmio da TV/Campeonato baseado na posição final da equipe
        // (Aqui você passaria a posição real, coloquei 5º lugar como exemplo fixo)
        double premioCampeonato = calcularPremioCampeonato(5); 
        equipe.receberReceita(premioCampeonato);

        // 2. Checagem de Falência (Regra de 1 ano de tolerância)
        if (equipe.getSaldoFinanceiro() < 0) {
            equipe.incrementarAnosNoVermelho();
            System.out.println("ALERTA: A equipe fechou o ano no vermelho!");
            
            if (equipe.getAnosConsecutivosNoVermelho() >= 2) {
                return true; // GAME OVER
            }
        } else {
            equipe.zerarAnosNoVermelho(); // Recuperou!
        }

        return false; // Jogo segue
    }

    private double calcularPremioCampeonato(int posicaoEquipe) {
        // Exemplo: Campeão ganha 100M, último ganha 10M
        // Pode ser lido de um arquivo JSON de regras depois
        if (posicaoEquipe == 1) return 100.0;
        return 100.0 - (posicaoEquipe * 5); 
    }
}
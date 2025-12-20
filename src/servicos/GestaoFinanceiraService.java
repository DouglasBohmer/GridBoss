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

        // 1. Pagar Salários (Pilotos Titulares)
        if (equipe.getPilotosTitulares() != null) {
            for (Piloto p : equipe.getPilotosTitulares()) {
                 // Só paga se tiver contrato ativo
                 if (p.getContrato() != null) {
                     despesasTotais += p.getContrato().getSalarioMensal();
                 }
            }
        }
        
        // 2. Pagar Salários (Pilotos Reservas)
        if (equipe.getPilotosReservas() != null) {
            for (Piloto p : equipe.getPilotosReservas()) {
                 if (p.getContrato() != null) {
                     despesasTotais += p.getContrato().getSalarioMensal();
                 }
            }
        }

        // 3. Receber Mensalidade dos Patrocinadores Ativos
        // O valor mensal agora é igual ao Valor Base, conforme sua regra.
        if (equipe.getPatrocinadoresAtivos() != null) {
            for (Patrocinador patroc : equipe.getPatrocinadoresAtivos()) {
                receitasTotais += patroc.getValorMensal(); 
                
                // Reduz 1 mês do contrato
                patroc.reduzirMesRestante(); 
            }
            
            // Remove automaticamente patrocinadores cujo contrato chegou a 0
            equipe.limparPatrocinadoresVencidos();
        }

        // 4. Aplicar no Saldo da Equipe
        equipe.pagarDespesa(despesasTotais);
        equipe.receberReceita(receitasTotais);

        // Logs para debug no console
        System.out.println("=== FECHAMENTO MENSAL: " + equipe.getNome() + " ===");
        System.out.println("Receitas (Patrocínios): € " + receitasTotais + " mi");
        System.out.println("Despesas (Salários): € " + despesasTotais + " mi");
        System.out.println("Saldo Atual: € " + equipe.getSaldoFinanceiro() + " mi");
        System.out.println("===========================================");
    }

    /**
     * Processa prêmios de corrida (Bônus de patrocinadores e da FIA/Organização).
     * @param posicoesChegada Lista com a posição final de CADA carro da equipe na corrida.
     * Ex: Se tem 2 carros e chegaram em 1º e 5º, a lista deve conter [1, 5].
     */
    public void processarPremiosDeCorrida(Equipe equipe, List<Integer> posicoesChegada) {
        double premios = 0;

        // Para cada carro que correu...
        for (int posicao : posicoesChegada) {
            
            // 1. Verifica Bônus de Patrocinadores (Paga por carro que atingiu a meta)
            if (equipe.getPatrocinadoresAtivos() != null) {
                for (Patrocinador patroc : equipe.getPatrocinadoresAtivos()) {
                    premios += patroc.calcularPremioCorrida(posicao);
                }
            }

            // 2. Adiciona prêmio da Organização da Corrida (Valor por chegada)
            premios += calcularPremioPosicao(posicao);
        }

        if (premios > 0) {
            equipe.receberReceita(premios);
            System.out.println("Prêmios de Corrida recebidos: € " + premios + " mi");
        }
    }

    /**
     * Lógica simples de prêmio pago pela organização por posição na corrida.
     * (Pode ser ajustada futuramente por categoria)
     */
    private double calcularPremioPosicao(int pos) {
        if (pos == 1) return 1.0; // 1 Milhão por vitória
        if (pos == 2) return 0.75;
        if (pos == 3) return 0.5;
        if (pos <= 10) return 0.1; // Pontos menores ganham 100k
        return 0.0;
    }

    /**
     * Verifica se o jogo acabou (Game Over) no final do ano.
     * Retorna TRUE se a equipe faliu.
     */
    public boolean processarFechamentoAnual(Equipe equipe, int posicaoNoCampeonatoConstrutores) {
        // 1. Receber prêmio da TV/Campeonato (Prize Money)
        double premioCampeonato = calcularPremioCampeonato(posicaoNoCampeonatoConstrutores); 
        equipe.receberReceita(premioCampeonato);
        System.out.println("Prêmio de Campeonato (Construtores P" + posicaoNoCampeonatoConstrutores + ") recebido: € " + premioCampeonato + " mi");

        // 2. Checagem de Falência (Regra de tolerância de 2 anos no vermelho)
        if (equipe.getSaldoFinanceiro() < 0) {
            equipe.incrementarAnosNoVermelho();
            System.out.println("ALERTA CRÍTICO: A equipe fechou o ano no vermelho! (" + equipe.getAnosConsecutivosNoVermelho() + "/2)");
            
            if (equipe.getAnosConsecutivosNoVermelho() >= 2) {
                return true; // GAME OVER
            }
        } else {
            equipe.zerarAnosNoVermelho(); // Recuperou a saúde financeira
        }

        return false; // Jogo segue
    }

    private double calcularPremioCampeonato(int posicaoEquipe) {
        // Exemplo: Campeão ganha 100M, decai 5M por posição
        // P1 = 100, P2 = 90, P3 = 85...
        if (posicaoEquipe == 1) return 100.0;
        
        double valor = 100.0 - (posicaoEquipe * 5);
        return (valor > 0) ? valor : 5.0; // Garante um mínimo de 5M para os últimos
    }
}
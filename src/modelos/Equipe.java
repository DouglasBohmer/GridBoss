package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipe {
    private String nome;
    private double saldoFinanceiro;
    private int reputacao; // 0 a 100 (Define quem quer correr aqui)
    
    // Controle de Game Over
    private int anosConsecutivosNoVermelho = 0;
    
    private Piloto piloto1;
    private Piloto piloto2;
    
    // Lista de patrocinadores ativos no carro
    private List<Patrocinador> patrocinadoresAtivos = new ArrayList<>();

    public Equipe(String nome, double saldoInicial, int reputacao) {
        this.nome = nome;
        this.saldoFinanceiro = saldoInicial;
        this.reputacao = reputacao;
    }

    // --- MÉTODOS DE PATROCÍNIO ---

    public void adicionarPatrocinador(Patrocinador p) {
        // Regra de negócio: verificar se cabe mais (opcional)
        patrocinadoresAtivos.add(p);
        // Recebe o valor de assinatura ("Luvas") imediatamente
        this.saldoFinanceiro += p.getValorAssinatura();
    }

    public List<Patrocinador> getPatrocinadoresAtivos() {
        return patrocinadoresAtivos;
    }

    // Remove quem já expirou (chamado pelo Service)
    public void limparPatrocinadoresVencidos() {
        // Iterator é o jeito seguro de remover itens de uma lista enquanto percorre ela
        Iterator<Patrocinador> it = patrocinadoresAtivos.iterator();
        while (it.hasNext()) {
            Patrocinador p = it.next();
            if (p.expirou()) {
                System.out.println("Contrato com " + p.getNome() + " encerrou.");
                it.remove();
            }
        }
    }
    
    // --- MÉTODOS FINANCEIROS ---

    public void receberReceita(double valor) {
        this.saldoFinanceiro += valor;
    }

    public void pagarDespesa(double valor) {
        this.saldoFinanceiro -= valor;
    }

    public void incrementarAnosNoVermelho() {
        this.anosConsecutivosNoVermelho++;
    }
    
    public void zerarAnosNoVermelho() {
        this.anosConsecutivosNoVermelho = 0;
    }
    
    public boolean verificarGameOver() {
        if (saldoFinanceiro < 0) {
            anosConsecutivosNoVermelho++;
            if (anosConsecutivosNoVermelho >= 2) {
                return true; // GAME OVER: Faliu de vez
            }
        } else {
            anosConsecutivosNoVermelho = 0; // Recuperou, zera a contagem
        }
        return false; // Jogo segue
    }

    // --- GETTERS E SETTERS (Essenciais para o Piloto funcionar) ---

    public String getNome() {
        return nome;
    }

    public int getReputacao() {
        return reputacao;
    }

    public double getSaldoFinanceiro() {
        return saldoFinanceiro;
    }

    public int getAnosConsecutivosNoVermelho() {
        return anosConsecutivosNoVermelho;
    }
    
    public Piloto getPiloto1() {
        return piloto1;
    }

    public void setPiloto1(Piloto piloto1) {
        this.piloto1 = piloto1;
    }

    public Piloto getPiloto2() {
        return piloto2;
    }

    public void setPiloto2(Piloto piloto2) {
        this.piloto2 = piloto2;
    }
}
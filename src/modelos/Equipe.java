package modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Equipe {
    private String nome;
    private double saldoFinanceiro;
    private int reputacao;
    private int anosConsecutivosNoVermelho = 0;
    private Categoria categoriaAtual; // Referência para saber as regras
    
    private List<Piloto> pilotosTitulares = new ArrayList<>();
    private List<Piloto> pilotosReservas = new ArrayList<>();
    
    private List<Patrocinador> patrocinadoresAtivos = new ArrayList<>();

    public Equipe(String nome, double saldoInicial, int reputacao) {
        this.nome = nome;
        this.saldoFinanceiro = saldoInicial;
        this.reputacao = reputacao;
    }

    // --- CONTRATAÇÃO DE PILOTOS ---

    public boolean contratarPiloto(Piloto piloto, double salario, double custoAssinatura, int meses, TipoContrato tipo) {
        // 1. Verifica Limites da Categoria
        if (categoriaAtual != null) {
            if (tipo == TipoContrato.TITULAR && pilotosTitulares.size() >= categoriaAtual.getMaxTitulares()) {
                System.out.println("Erro: Equipe já tem o máximo de titulares.");
                return false;
            }
            if (tipo == TipoContrato.RESERVA && pilotosReservas.size() >= categoriaAtual.getMaxReservas()) {
                System.out.println("Erro: Equipe já tem o máximo de reservas.");
                return false;
            }
        }

        // 2. Aplica Desconto de Promoção (Regra dos 50%)
        double custoFinalAssinatura = custoAssinatura;
        if (tipo == TipoContrato.TITULAR && piloto.isReservaDaEquipe(this)) {
            custoFinalAssinatura = custoAssinatura * 0.5; // 50% de desconto
            pilotosReservas.remove(piloto);
        }

        // 3. Pagamento e Assinatura
        if (saldoFinanceiro >= custoFinalAssinatura) {
            this.saldoFinanceiro -= custoFinalAssinatura;
            
            Contrato novoContrato = new Contrato(salario, meses, this, tipo);
            piloto.assinarContrato(novoContrato);
            
            if (tipo == TipoContrato.TITULAR) {
                pilotosTitulares.add(piloto);
            } else {
                pilotosReservas.add(piloto);
            }
            return true;
        } else {
            System.out.println("Erro: Saldo insuficiente.");
            return false;
        }
    }

    // --- MÉTODOS FINANCEIROS E PATROCÍNIO ---

    public void adicionarPatrocinador(Patrocinador p) {
        patrocinadoresAtivos.add(p);
        this.saldoFinanceiro += p.getValorAssinatura();
    }

    public void limparPatrocinadoresVencidos() {
        Iterator<Patrocinador> it = patrocinadoresAtivos.iterator();
        while (it.hasNext()) {
            Patrocinador p = it.next();
            if (p.expirou()) {
                it.remove();
            }
        }
    }
    
    public void receberReceita(double valor) { 
        this.saldoFinanceiro += valor; 
    }
    
    public void pagarDespesa(double valor) { 
        this.saldoFinanceiro -= valor; 
    }
    
    // Métodos exigidos pelo GestaoFinanceiraService
    public void incrementarAnosNoVermelho() {
        this.anosConsecutivosNoVermelho++;
    }
    
    public void zerarAnosNoVermelho() {
        this.anosConsecutivosNoVermelho = 0;
    }
    
    public boolean verificarGameOver() {
        if (saldoFinanceiro < 0) {
            anosConsecutivosNoVermelho++;
            if (anosConsecutivosNoVermelho >= 2) return true;
        } else {
            anosConsecutivosNoVermelho = 0;
        }
        return false;
    }

    // --- GETTERS E SETTERS ---

    public String getNome() { return nome; }
    public int getReputacao() { return reputacao; }
    public double getSaldoFinanceiro() { return saldoFinanceiro; }
    public int getAnosConsecutivosNoVermelho() { return anosConsecutivosNoVermelho; }
    public List<Patrocinador> getPatrocinadoresAtivos() { return patrocinadoresAtivos; }
    
    public List<Piloto> getPilotosTitulares() { return pilotosTitulares; }
    public List<Piloto> getPilotosReservas() { return pilotosReservas; }
    
    public void setCategoriaAtual(Categoria categoriaAtual) {
        this.categoriaAtual = categoriaAtual;
    }
}
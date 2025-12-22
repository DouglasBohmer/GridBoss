package modelos;

import java.io.Serializable;

public class Patrocinador implements Serializable { 
    private static final long serialVersionUID = 1L;
    // --- CAMPOS VINDOS DO JSON ---
    private String nome;
    private String segmento;
    private String pais;
    private int reputacaoMinima;
    private double valorBase;       // Valor fixo por corrida/mês
    private double bonusAssinatura; // Luvas (Upfront)
    private double bonusVitoria;
    private double bonusPodio;
    private double bonusTop10;
    private Arquivos arquivos;

    // --- CAMPOS DE CONTROLE (Lógica do Jogo) ---
    private int duracaoRestante; // Em meses ou corridas

    public static class Arquivos implements Serializable {
        private String logo;
        private String bandeira;
        public String getLogo() { return logo; }
        public String getBandeira() { return bandeira; }
    }

    // --- CONSTRUTOR PADRÃO ---
    public Patrocinador() {}

    // --- MÉTODO FÁBRICA PARA ASSINAR CONTRATO ---
    // Cria uma cópia deste patrocinador para a equipe, definindo a duração
    public Patrocinador criarContrato(int mesesDuracao) {
        Patrocinador contrato = new Patrocinador();
        contrato.nome = this.nome;
        contrato.segmento = this.segmento;
        contrato.pais = this.pais;
        contrato.reputacaoMinima = this.reputacaoMinima;
        contrato.valorBase = this.valorBase;
        contrato.bonusAssinatura = this.bonusAssinatura;
        contrato.bonusVitoria = this.bonusVitoria;
        contrato.bonusPodio = this.bonusPodio;
        contrato.bonusTop10 = this.bonusTop10;
        contrato.arquivos = this.arquivos;
        
        contrato.duracaoRestante = mesesDuracao;
        return contrato;
    }

    // --- MÉTODOS EXIGIDOS PELO FINANCEIRO ---
    
    // Regra solicitada: Custo Mensal = Custo Base
    public double getValorMensal() {
        return this.valorBase;
    }
    
    public double getValorAssinatura() {
        return this.bonusAssinatura;
    }

    public void reduzirMesRestante() {
        if (this.duracaoRestante > 0) {
            this.duracaoRestante--;
        }
    }

    public boolean expirou() {
        return this.duracaoRestante <= 0;
    }

    public double calcularPremioCorrida(int posicao) {
        double premio = 0.0;
        if (posicao == 1) premio += bonusVitoria;
        if (posicao <= 3) premio += bonusPodio;
        if (posicao <= 10) premio += bonusTop10;
        return premio;
    }

    // --- GETTERS ---
    public String getNome() { return nome; }
    public String getSegmento() { return segmento; }
    public String getPais() { return pais; }
    public int getReputacaoMinima() { return reputacaoMinima; }
    public double getValorBase() { return valorBase; }
    public double getBonusAssinatura() { return bonusAssinatura; }
    public double getBonusVitoria() { return bonusVitoria; }
    public double getBonusPodio() { return bonusPodio; }
    public double getBonusTop10() { return bonusTop10; }
    public int getDuracaoRestante() { return duracaoRestante; }
    
    public String getCaminhoLogo() { return (arquivos != null) ? arquivos.getLogo() : null; }
    public String getCaminhoBandeira() { return (arquivos != null) ? arquivos.getBandeira() : null; }
    
    @Override
    public String toString() { return nome; }
}
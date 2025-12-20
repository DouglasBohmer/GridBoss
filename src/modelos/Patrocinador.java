package modelos;

public class Patrocinador {
    private String nome;
    private String segmento;
    private String pais;
    private int reputacaoMinima;
    private double valorBase;
    private double bonusAssinatura;
    private double bonusVitoria;
    private double bonusPodio;
    private double bonusTop10;
    private Arquivos arquivos;

    public static class Arquivos {
        private String logo;
        private String bandeira;
        
        public String getLogo() { return logo; }
        public String getBandeira() { return bandeira; }
    }

    // Getters
    public String getNome() { return nome; }
    public String getSegmento() { return segmento; }
    public String getPais() { return pais; }
    public int getReputacaoMinima() { return reputacaoMinima; }
    public double getValorBase() { return valorBase; }
    public double getBonusAssinatura() { return bonusAssinatura; }
    public double getBonusVitoria() { return bonusVitoria; }
    public double getBonusPodio() { return bonusPodio; }
    public double getBonusTop10() { return bonusTop10; }
    
    // Helpers para caminhos
    public String getCaminhoLogo() { return (arquivos != null) ? arquivos.getLogo() : null; }
    public String getCaminhoBandeira() { return (arquivos != null) ? arquivos.getBandeira() : null; }
    
    @Override
    public String toString() {
        return nome;
    }
}
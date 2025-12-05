package modelos;

public class Motor {
    private String id;
    private String nome;
    private int potencia;       // 1 a 5
    private int dirigibilidade; // 1 a 5
    private int durabilidade;   // 1 a 5
    private double preco;
    
    private Arquivos arquivos;

    public static class Arquivos {
        public String logo;
        public String bandeira; // Vamos precisar adicionar isso no JSON!
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public int getPotencia() { return potencia; }
    public int getDirigibilidade() { return dirigibilidade; }
    public int getDurabilidade() { return durabilidade; }
    public double getPreco() { return preco; }
    
    public String getCaminhoLogo() {
        if (arquivos != null && arquivos.logo != null) return arquivos.logo;
        return "/resource/Icone16pxErro.png";
    }
    
    public String getCaminhoBandeira() {
        if (arquivos != null && arquivos.bandeira != null) return arquivos.bandeira;
        return "/resource/Bandeira BRANCA.png";
    }
}
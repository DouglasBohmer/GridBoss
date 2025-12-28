package modelos;

import java.util.Objects;

public class Piloto {
    private String nome;
    private String nacionalidade;
    private int numero;
    private int idade;
    private double overall; 
    private int exigenciaMinimaDeEquipe; 
    
    // --- ESTATÍSTICAS DA TEMPORADA ---
    private int pontos;
    private int vitorias;
    private int podios;
    private int poles;
    
    // --- ESTATÍSTICAS DETALHADAS (0 a 100) ---
    private double ritmo;          
    private double experiencia;    
    private double agressividade;  
    private double fisico;         
    private double largada;        
    private double ultrapassagem;  
    private double defesa;         
    
    // Habilidades Específicas de Terreno
    private double habilidadeChuva; 
    private double habilidadeRua;   
    private double habilidadeMisto; 
    private double habilidadeOval;  
    
    // Campo auxiliar para exibição em tabelas
    private transient String nomeEquipeAtual;

    // --- SISTEMA DE CONTRATOS (Atualizado) ---
    // Substituímos a lista genérica por campos específicos para facilitar a lógica de "Ano que vem"
    private Contrato contrato;       // Contrato VIGENTE (Ano atual)
    private Contrato contratoFuturo; // PRÉ-CONTRATO (Assinado para o próximo ano)

    // --- CONSTRUTORES ---
    public Piloto() {}

    public Piloto(String nome, String nacionalidade, int idade, double overall) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.overall = overall;
    }

    // --- LÓGICA DE NEGOCIAÇÃO (Usada pelo Mercado) ---
    
    public String receberProposta(Equipe equipe, double salarioOferecido, TipoContrato tipoVaga) {
        // 1. Verifica Reputação
        if (equipe.getReputacao() < exigenciaMinimaDeEquipe) {
            return "RECUSADO: Reputação da equipe muito baixa para mim.";
        }
        
        // 2. Verifica Salário (Lógica simplificada baseada no Overall)
        // Ex: Over 80 pede ~3.2mi, Over 90 pede ~4.0mi
        double salarioMinimoEsperado = (overall * overall) / 2000.0; 
        
        if (tipoVaga == TipoContrato.RESERVA) {
            salarioMinimoEsperado *= 0.7; // Aceita ganhar 30% menos para ser reserva
        }
        
        if (salarioOferecido < salarioMinimoEsperado) {
            return "RECUSADO: Proposta financeira insatisfatória (Pede: " + String.format("%.2f", salarioMinimoEsperado) + "mi).";
        }
        
        return "ACEITO";
    }

    // --- GETTERS E SETTERS ---

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public double getOverall() { return overall; }
    public void setOverall(double overall) { this.overall = overall; }

    public int getExigenciaMinimaDeEquipe() { return exigenciaMinimaDeEquipe; }
    public void setExigenciaMinimaDeEquipe(int exigenciaMinimaDeEquipe) { this.exigenciaMinimaDeEquipe = exigenciaMinimaDeEquipe; }

    // Stats Temporada
    public int getPontos() { return pontos; }
    public void setPontos(int pontos) { this.pontos = pontos; }
    public void adicionarPontos(int pontos) { this.pontos += pontos; }

    public int getVitorias() { return vitorias; }
    public void setVitorias(int vitorias) { this.vitorias = vitorias; }
    public void adicionarVitoria() { this.vitorias++; }

    public int getPodios() { return podios; }
    public void setPodios(int podios) { this.podios = podios; }
    public void adicionarPodio() { this.podios++; }

    public int getPoles() { return poles; }
    public void setPoles(int poles) { this.poles = poles; }
    public void adicionarPole() { this.poles++; }

    // Stats Habilidades
    public double getRitmo() { return ritmo; }
    public void setRitmo(double ritmo) { this.ritmo = ritmo; }

    public double getExperiencia() { return experiencia; }
    public void setExperiencia(double experiencia) { this.experiencia = experiencia; }

    public double getAgressividade() { return agressividade; }
    public void setAgressividade(double agressividade) { this.agressividade = agressividade; }

    public double getFisico() { return fisico; }
    public void setFisico(double fisico) { this.fisico = fisico; }

    public double getLargada() { return largada; }
    public void setLargada(double largada) { this.largada = largada; }

    public double getUltrapassagem() { return ultrapassagem; }
    public void setUltrapassagem(double ultrapassagem) { this.ultrapassagem = ultrapassagem; }

    public double getDefesa() { return defesa; }
    public void setDefesa(double defesa) { this.defesa = defesa; }

    public double getHabilidadeChuva() { return habilidadeChuva; }
    public void setHabilidadeChuva(double habilidadeChuva) { this.habilidadeChuva = habilidadeChuva; }

    public double getHabilidadeRua() { return habilidadeRua; }
    public void setHabilidadeRua(double habilidadeRua) { this.habilidadeRua = habilidadeRua; }

    public double getHabilidadeMisto() { return habilidadeMisto; }
    public void setHabilidadeMisto(double habilidadeMisto) { this.habilidadeMisto = habilidadeMisto; }

    public double getHabilidadeOval() { return habilidadeOval; }
    public void setHabilidadeOval(double habilidadeOval) { this.habilidadeOval = habilidadeOval; }

    public String getNomeEquipeAtual() { return nomeEquipeAtual; }
    public void setNomeEquipeAtual(String nomeEquipeAtual) { this.nomeEquipeAtual = nomeEquipeAtual; }

    // --- CONTRATOS ---

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public Contrato getContratoFuturo() {
        return contratoFuturo;
    }

    public void setContratoFuturo(Contrato contratoFuturo) {
        this.contratoFuturo = contratoFuturo;
    }
    
    // Método auxiliar antigo (mantido para compatibilidade, se necessário)
    public Contrato getContratoAtual() {
        return contrato;
    }

    @Override
    public String toString() {
        return nome + " (" + (int)overall + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloto piloto = (Piloto) o;
        return Objects.equals(nome, piloto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
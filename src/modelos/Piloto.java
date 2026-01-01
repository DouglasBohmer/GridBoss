package modelos;

import java.util.Objects;

public class Piloto {

    // --- DADOS PESSOAIS ---
    private String nome;
    private String nacionalidade;
    private int numero;
    private int idade;
    
    // --- ATRIBUTOS GERAIS ---
    private double overall; 
    private int exigenciaMinimaDeEquipe; 
    
    // --- ESTATÍSTICAS DA TEMPORADA ---
    private int pontos;
    private int vitorias;
    private int podios;
    private int poles;
    
    // --- ATRIBUTOS DE PILOTAGEM (0 - 100) ---
    private double ritmo;          
    private double experiencia;    
    private double agressividade;  
    private double fisico;         
    private double largada;        
    private double ultrapassagem;  
    private double defesa;         
    
    // --- HABILIDADES ESPECÍFICAS (Fatores Ocultos) ---
    private double habilidadeChuva; 
    private double habilidadeRua;   
    private double habilidadeMisto; 
    private double habilidadeOval;  
    
    // --- VÍNCULOS ---
    private transient String nomeEquipeAtual; // Apenas para visualização em tabelas, não salvar no JSON do piloto
    
    private Contrato contrato;       // Contrato VIGENTE
    private Contrato contratoFuturo; // Contrato para a PRÓXIMA TEMPORADA

    public Piloto() {
    }

    public Piloto(String nome, String nacionalidade, int idade, double overall) {
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.idade = idade;
        this.overall = overall;
    }

    // ==================================================================================
    //                  NOVA LÓGICA DE MERCADO E SALÁRIOS
    // ==================================================================================

    /**
     * Calcula o salário base anual desejado (em Milhões)
     * Fórmula: Base 1M + Curva Quadrática baseada no Overall acima de 60.
     * Ajustes: Idade (Jovens < 22 desconto, Veteranos > 33 acréscimo).
     */
    public double calcularSalarioDesejado() {
        double fatorCategoria = 1.0; // Preparado para ler de regras.json futuramente
        
        // Se for muito fraco (< 60), salário base mínimo (0.5M)
        if (overall < 60) return 0.5 * fatorCategoria;

        // FÓRMULA: 
        // Overall 60 -> 1.0M
        // Overall 70 -> 2.0M
        // Overall 80 -> 5.0M
        // Overall 90 -> 10.0M
        // Overall 99 -> 16.2M
        double diferenca = overall - 60;
        double salarioBase = 1.0 + (Math.pow(diferenca, 2) * 0.01);
        
        // Ajuste de Idade
        double multiplicadorIdade = 1.0;
        if (idade < 22) {
            multiplicadorIdade = 0.75; // Jovens aceitam ganhar menos para entrar na categoria
        } else if (idade > 33) {
            multiplicadorIdade = 1.10; // Veteranos cobram pela experiência/marketing
        }
        
        return salarioBase * multiplicadorIdade * fatorCategoria;
    }

    /**
     * Analisa uma proposta de contrato.
     * Retorna "ACEITO" ou a razão da recusa.
     */
    public String receberProposta(Equipe equipe, double salarioOferecido, TipoContrato tipoVaga) {
        double valorDeMercado = calcularSalarioDesejado();
        
        // --- 1. Fator de Interesse na Equipe ---
        // Se a equipe é de ponta (Rep > 80), o piloto aceita receber 90% do valor de mercado (desconto para vencer)
        // Se a equipe é pequena (Rep < 40), cobra 20% a mais (adicional de risco)
        double fatorInteresse = 1.0;
        if (equipe.getReputacao() > 80) fatorInteresse = 0.9;
        else if (equipe.getReputacao() < 40) fatorInteresse = 1.2; 
        
        double salarioMinimoAceitavel = valorDeMercado * fatorInteresse;

        // --- 2. Validação Financeira ---
        if (salarioOferecido < salarioMinimoAceitavel) {
            return "RECUSADO: Proposta financeira abaixo do esperado (" + String.format("%.2f", salarioMinimoAceitavel) + " mi).";
        }
        
        // --- 3. Validação de Prestígio (Exigência Mínima) ---
        // Se a exigência estiver zerada, calculamos uma dinâmica baseada no overall
        int exigenciaEfetiva = this.exigenciaMinimaDeEquipe;
        if (exigenciaEfetiva == 0) {
            if (overall >= 90) exigenciaEfetiva = 80;      // Só aceita Top Teams
            else if (overall >= 80) exigenciaEfetiva = 60; // Aceita meio de grid pra cima
            else if (overall >= 70) exigenciaEfetiva = 40; // Aceita quase tudo
        }

        if (equipe.getReputacao() < exigenciaEfetiva) {
            // Se for muito abaixo da exigência (>15 pontos), recusa direto
            if (equipe.getReputacao() < exigenciaEfetiva - 15) {
                return "RECUSADO: A equipe não tem competitividade para minhas ambições.";
            }
            // Se for um pouco abaixo, só aceita se pagar MUITO bem (50% a mais)
            if (salarioOferecido < salarioMinimoAceitavel * 1.5) {
                return "RECUSADO: Para correr numa equipe deste nível, preciso de um salário muito maior.";
            }
        }
        
        // --- 4. Validação de Função (Reserva vs Titular) ---
        // Se a vaga for RESERVA e o piloto for muito bom (>80), ele recusa
        if (tipoVaga == TipoContrato.RESERVA && overall > 80) {
            return "RECUSADO: Sou um piloto de elite, não aceito ser reserva.";
        }

        return "ACEITO";
    }

    // ==================================================================================
    //                            GETTERS E SETTERS
    // ==================================================================================

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

    // --- Stats Temporada ---
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

    // --- Stats Pilotagem ---
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
    
    // --- Habilidades ---
    public double getHabilidadeChuva() { return habilidadeChuva; }
    public void setHabilidadeChuva(double habilidadeChuva) { this.habilidadeChuva = habilidadeChuva; }

    public double getHabilidadeRua() { return habilidadeRua; }
    public void setHabilidadeRua(double habilidadeRua) { this.habilidadeRua = habilidadeRua; }

    public double getHabilidadeMisto() { return habilidadeMisto; }
    public void setHabilidadeMisto(double habilidadeMisto) { this.habilidadeMisto = habilidadeMisto; }

    public double getHabilidadeOval() { return habilidadeOval; }
    public void setHabilidadeOval(double habilidadeOval) { this.habilidadeOval = habilidadeOval; }

    // --- Vínculos ---
    public String getNomeEquipeAtual() { return nomeEquipeAtual; }
    public void setNomeEquipeAtual(String nomeEquipeAtual) { this.nomeEquipeAtual = nomeEquipeAtual; }

    public Contrato getContrato() { return contrato; }
    public void setContrato(Contrato contrato) { this.contrato = contrato; }
    
    public Contrato getContratoFuturo() { return contratoFuturo; }
    public void setContratoFuturo(Contrato contratoFuturo) { this.contratoFuturo = contratoFuturo; }

    @Override
    public String toString() {
        return nome + " (Over: " + (int)overall + ")";
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
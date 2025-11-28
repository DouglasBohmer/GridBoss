package modelos;

import java.util.ArrayList;
import java.util.List;

public class Piloto {
    private String nome;
    private String nacionalidade;
    private int numero;
    private int idade;
    private double overall; 
    private int exigenciaMinimaDeEquipe; 
    
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
    private double habilidadeRua;   // Mônaco, Long Beach, Rovals
    private double habilidadeMisto; // Interlagos, Spa (Padrão F1)
    private double habilidadeOval;  // Indianápolis, Daytona (Padrão Indy/Nascar)
    
    private List<Contrato> contratosAtivos;

    // --- CONSTRUTOR PADRÃO (Necessário para o Gson) ---
    public Piloto() {
        this.contratosAtivos = new ArrayList<>();
    }

    // Construtor completo atualizado
    public Piloto(String nome, String pais, int numero, int idade, double overall, int exigencia) {
        this(); 
        this.nome = nome;
        this.nacionalidade = pais;
        this.numero = numero;
        this.idade = idade;
        this.overall = overall;
        this.exigenciaMinimaDeEquipe = exigencia;
    }

    // --- LÓGICA DE CONTRATO ---
    public String receberProposta(Equipe equipeInteressada, double ofertaSalarial, TipoContrato tipoVaga) {
        if (contratosAtivos == null) contratosAtivos = new ArrayList<>();

        if (equipeInteressada.getReputacao() < this.exigenciaMinimaDeEquipe) {
            return "RECUSADO: Reputação baixa.";
        }

        if (tipoVaga == TipoContrato.TITULAR) {
            if (isTitular()) {
                Contrato atual = getContratoTitular();
                double multa = atual.calcularMultaRescisoria();
                
                if (isReservaDaEquipe(equipeInteressada)) return "ACEITO_PROMOCAO"; 

                if (equipeInteressada.getSaldoFinanceiro() < multa) {
                    return "RECUSADO: Sem dinheiro para multa (" + multa + ")";
                }
                return "ACEITO_COM_MULTA: " + multa;
            }
        } else {
            if (isTitular()) return "RECUSADO: Já sou titular.";
            if (contratosAtivos.size() >= 3) return "RECUSADO: Já sou reserva de 3 equipes.";
        }
        return "ACEITO";
    }

    public void assinarContrato(Contrato novoContrato) {
        if (contratosAtivos == null) contratosAtivos = new ArrayList<>(); 
        if (novoContrato.getTipo() == TipoContrato.TITULAR) contratosAtivos.clear(); 
        contratosAtivos.add(novoContrato);
    }

    // --- MÉTODOS AUXILIARES ---
    public boolean isTitular() {
        if (contratosAtivos == null) return false;
        for (Contrato c : contratosAtivos) if (c.getTipo() == TipoContrato.TITULAR) return true;
        return false;
    }
    
    public Contrato getContratoTitular() {
        if (contratosAtivos == null) return null;
        for (Contrato c : contratosAtivos) if (c.getTipo() == TipoContrato.TITULAR) return c;
        return null;
    }

    public Contrato getContrato() {
        if (contratosAtivos == null || contratosAtivos.isEmpty()) return null;
        Contrato titular = getContratoTitular();
        return (titular != null) ? titular : contratosAtivos.get(0);
    }

    public boolean isReservaDaEquipe(Equipe e) {
        if (contratosAtivos == null) return false;
        for (Contrato c : contratosAtivos) {
            if (c.getTipo() == TipoContrato.RESERVA && c.getEquipeAtual() == e) return true;
        }
        return false;
    }

    // --- GETTERS ---
    public String getNome() { return nome; }
    public String getNacionalidade() { return nacionalidade; }
    public int getNumero() { return numero; }
    public int getIdade() { return idade; }
    public double getOverall() { return overall; }
    public void setOverall(double over) { this.overall = over; }
    public List<Contrato> getContratos() { return contratosAtivos; }
    
    // Stats de Corrida
    public double getRitmo() { return ritmo; }
    public double getExperiencia() { return experiencia; }
    public double getAgressividade() { return agressividade; }
    public double getFisico() { return fisico; }
    public double getLargada() { return largada; }
    public double getUltrapassagem() { return ultrapassagem; }
    public double getDefesa() { return defesa; }
    public double getHabilidadeChuva() { return habilidadeChuva; }
    public double getHabilidadeRua() { return habilidadeRua; }
    public double getHabilidadeMisto() { return habilidadeMisto; }
    public double getHabilidadeOval() { return habilidadeOval; }
}
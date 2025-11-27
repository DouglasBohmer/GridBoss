package modelos;

import java.util.ArrayList;
import java.util.List;

public class Categoria {
    private String nome;
    private String nomeCurto;
    private boolean temPlayoffs;
    
    // Regras de Limites de Pilotos
    private int maxTitulares;
    private int minReservas; // 0 para Indy/Nascar, 1 para F1
    private int maxReservas;

    private List<Equipe> equipesInscritas;
    private List<Piloto> pilotosInscritos;

    public Categoria(String nome, String nomeCurto, boolean temPlayoffs, int maxTitulares, int minReservas, int maxReservas) {
        this.nome = nome;
        this.nomeCurto = nomeCurto;
        this.temPlayoffs = temPlayoffs;
        this.maxTitulares = maxTitulares;
        this.minReservas = minReservas;
        this.maxReservas = maxReservas;
        
        this.equipesInscritas = new ArrayList<>();
        this.pilotosInscritos = new ArrayList<>();
    }

    public void adicionarEquipe(Equipe equipe) {
        equipesInscritas.add(equipe);
        // Vincula a equipe à categoria para ela saber as regras
        equipe.setCategoriaAtual(this); 
    }

    public void adicionarPiloto(Piloto piloto) {
        pilotosInscritos.add(piloto);
    }

    // Getters
    public String getNome() { return nome; }
    public String getNomeCurto() { return nomeCurto; }
    public int getMaxTitulares() { return maxTitulares; }
    public int getMinReservas() { return minReservas; }
    public int getMaxReservas() { return maxReservas; }
    public List<Equipe> getEquipes() { return equipesInscritas; }
    public List<Piloto> getPilotos() { return pilotosInscritos; }
}
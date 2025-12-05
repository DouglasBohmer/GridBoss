package servicos;

import dados.CarregadorJSON;
import modelos.Pista;

import java.util.ArrayList;
import java.util.List;

public class CampeonatoService {
    
    private List<Pista> calendario;
    private int etapaAtualIndex = 0; // 0 = Primeira corrida (Bahrein)
    private int anoAtual;
    private String categoriaAtual;

    public CampeonatoService(String categoria, int ano) {
        this.categoriaAtual = categoria;
        this.anoAtual = ano;
        this.calendario = CarregadorJSON.carregarCalendario(categoria, ano);
        
        if (calendario.isEmpty()) {
            System.err.println("ERRO CRÍTICO: Calendário vazio ou não encontrado!");
        } else {
            System.out.println("Campeonato iniciado. " + calendario.size() + " etapas carregadas.");
        }
    }

    public Pista getPistaAtual() {
        if (calendario == null || calendario.isEmpty()) return null;
        if (etapaAtualIndex >= calendario.size()) return null; // Fim da temporada
        return calendario.get(etapaAtualIndex);
    }

    public void avancarEtapa() {
        if (etapaAtualIndex < calendario.size()) {
            etapaAtualIndex++;
        }
    }
    
    public boolean isTemporadaFinalizada() {
        return etapaAtualIndex >= calendario.size();
    }

    public int getNumeroEtapaAtual() {
        return etapaAtualIndex + 1;
    }
    
    public int getTotalEtapas() {
        return calendario.size();
    }

    // --- NOVO MÉTODO PARA ACESSO EXTERNO ---
    public List<Pista> getCalendario() {
        return calendario;
    }
}
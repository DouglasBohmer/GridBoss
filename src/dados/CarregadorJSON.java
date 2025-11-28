package dados;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelos.Equipe;
import modelos.Piloto;

import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CarregadorJSON {

    // Caminho base (Isso vai facilitar quando exportar o jogo)
    // Por enquanto, vamos apontar para a pasta do projeto
    private static final String CAMINHO_MODS = "mods/";

    /**
     * Carrega a lista de Equipes de um determinado Mod/Ano
     * Ex: carregarEquipes("f1", 2024);
     */
    public static List<Equipe> carregarEquipes(String categoria, int ano) {
        String caminhoArquivo = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/equipes.json";
        
        try (Reader reader = new FileReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            
            // Define que queremos uma LISTA de Equipes
            Type tipoLista = new TypeToken<ArrayList<Equipe>>(){}.getType();
            
            List<Equipe> lista = gson.fromJson(reader, tipoLista);
            System.out.println("Sucesso! Carregadas " + lista.size() + " equipes de " + caminhoArquivo);
            return lista;
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar JSON de equipes: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>(); // Retorna lista vazia para não travar
        }
    }

    /**
     * Carrega a lista de Pilotos
     */
    public static List<Piloto> carregarPilotos(String categoria, int ano) {
        String caminhoArquivo = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/pilotos.json";
        
        try (Reader reader = new FileReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Piloto>>(){}.getType();
            return gson.fromJson(reader, tipoLista);
        } catch (Exception e) {
            System.err.println("Erro ao carregar JSON de pilotos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
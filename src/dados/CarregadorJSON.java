package dados;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelos.Equipe;
import modelos.Piloto;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CarregadorJSON {

    // Define onde a pasta mods está. "." significa a raiz do projeto.
    private static final String CAMINHO_MODS = "mods/";

    /**
     * 1. ESCANEAR MODS
     * Olha a pasta /mods e retorna um Mapa: "f1" -> ["2024", "2023"]
     */
    public static Map<String, List<String>> escanearModsInstalados() {
        Map<String, List<String>> modsEncontrados = new HashMap<>();
        
        // Inicializa chaves para evitar erro na tela
        modsEncontrados.put("f1", new ArrayList<>());
        modsEncontrados.put("indy", new ArrayList<>());
        modsEncontrados.put("nascar", new ArrayList<>());

        File pastaMods = new File(CAMINHO_MODS);
        
        if (!pastaMods.exists()) {
            pastaMods.mkdirs(); // Cria a pasta se não existir
            System.out.println("Pasta 'mods' criada em: " + pastaMods.getAbsolutePath());
            return modsEncontrados;
        }

        File[] arquivos = pastaMods.listFiles();
        if (arquivos == null) return modsEncontrados;

        for (File arquivo : arquivos) {
            if (arquivo.isDirectory()) {
                String nomePasta = arquivo.getName().toLowerCase(); // ex: "f1_2024"
                
                if (nomePasta.contains("_")) {
                    String[] partes = nomePasta.split("_");
                    if (partes.length >= 2) {
                        String categoria = partes[0]; // "f1"
                        String ano = partes[1];       // "2024"
                        
                        if (modsEncontrados.containsKey(categoria)) {
                            modsEncontrados.get(categoria).add(ano);
                        }
                    }
                }
            }
        }
        
        // Ordena os anos (Do mais novo para o mais velho)
        for (List<String> anos : modsEncontrados.values()) {
            anos.sort(Collections.reverseOrder());
        }

        return modsEncontrados;
    }

    /**
     * 2. CARREGAR EQUIPES
     * Lê o arquivo equipes.json
     */
    public static List<Equipe> carregarEquipes(String categoria, int ano) {
        String caminhoArquivo = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/equipes.json";
        
        try (Reader reader = new FileReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Equipe>>(){}.getType();
            List<Equipe> lista = gson.fromJson(reader, tipoLista);
            return lista;
        } catch (Exception e) {
            System.err.println("Erro ao carregar equipes (" + caminhoArquivo + "): " + e.getMessage());
            return new ArrayList<>(); // Retorna lista vazia para não travar
        }
    }

    /**
     * 3. CARREGAR PILOTOS (O método que estava faltando!)
     * Lê o arquivo pilotos.json
     */
    public static List<Piloto> carregarPilotos(String categoria, int ano) {
        String caminhoArquivo = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/pilotos.json";
        
        try (Reader reader = new FileReader(caminhoArquivo, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<Piloto>>(){}.getType();
            List<Piloto> lista = gson.fromJson(reader, tipoLista);
            return lista;
        } catch (Exception e) {
            System.err.println("Erro ao carregar pilotos (" + caminhoArquivo + "): " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
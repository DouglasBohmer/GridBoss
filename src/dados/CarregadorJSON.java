package dados;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import modelos.Motor;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CarregadorJSON {

    private static final String CAMINHO_MODS = "mods/";
    
    public static Map<String, List<String>> escanearModsInstalados() {
        Map<String, List<String>> modsEncontrados = new HashMap<>();
        modsEncontrados.put("f1", new ArrayList<>());
        modsEncontrados.put("indy", new ArrayList<>());
        modsEncontrados.put("nascar", new ArrayList<>());

        File pastaMods = new File(CAMINHO_MODS);
        if (!pastaMods.exists()) {
            pastaMods.mkdirs();
            return modsEncontrados;
        }

        File[] arquivos = pastaMods.listFiles();
        if (arquivos == null) return modsEncontrados;

        for (File arquivo : arquivos) {
            if (arquivo.isDirectory()) {
                String nomePasta = arquivo.getName().toLowerCase();
                if (nomePasta.contains("_")) {
                    String[] partes = nomePasta.split("_");
                    if (partes.length >= 2) {
                        String categoria = partes[0];
                        String ano = partes[1];
                        if (modsEncontrados.containsKey(categoria)) {
                            modsEncontrados.get(categoria).add(ano);
                        }
                    }
                }
            }
        }
        for (List<String> anos : modsEncontrados.values()) anos.sort(Collections.reverseOrder());
        return modsEncontrados;
    }

    public static List<Equipe> carregarEquipes(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/equipes.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Equipe>>(){}.getType());
    }

    public static List<Piloto> carregarPilotos(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/pilotos.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Piloto>>(){}.getType());
    }

    // NOVO MÉTODO: Carregar Calendário
    public static List<Pista> carregarCalendario(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/calendario.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Pista>>(){}.getType());
    }

    // Método genérico para evitar repetição de código
    private static <T> List<T> carregarLista(String caminho, Type tipo) {
        try (Reader reader = new FileReader(caminho, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, tipo);
        } catch (Exception e) {
            System.err.println("Erro ao carregar JSON (" + caminho + "): " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static List<Motor> carregarMotores(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/motores.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Motor>>(){}.getType());
    }


}
package dados;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import modelos.Motor;
import modelos.Patrocinador;

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
        
        // Inicializa os padrões
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
                        
                        if (!modsEncontrados.containsKey(categoria)) {
                            modsEncontrados.put(categoria, new ArrayList<>());
                        }
                        modsEncontrados.get(categoria).add(ano);
                    }
                }
            }
        }
        
        for (List<String> anos : modsEncontrados.values()) {
            anos.sort(Collections.reverseOrder());
        }
        
        return modsEncontrados;
    }

    public static List<Equipe> carregarEquipes(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/equipes.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Equipe>>(){}.getType());
    }

    public static List<Piloto> carregarPilotos(String categoria, int ano) {
        String pastaMod = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/";
        String caminhoPrincipal = pastaMod + "pilotos.json";
        String caminhoExtra = pastaMod + "pilotos_extra.json";
        
        // 1. Carrega a lista principal
        List<Piloto> listaPrincipal = carregarLista(caminhoPrincipal, new TypeToken<ArrayList<Piloto>>(){}.getType());
        if (listaPrincipal == null) listaPrincipal = new ArrayList<>();

        // 2. Carrega a lista extra (se existir)
        File fExtra = new File(caminhoExtra);
        if (fExtra.exists()) {
            List<Piloto> listaExtra = carregarLista(caminhoExtra, new TypeToken<ArrayList<Piloto>>(){}.getType());
            
            if (listaExtra != null) {
                // Cria um conjunto com os nomes já existentes para verificação rápida (Case Insensitive)
                Set<String> nomesExistentes = new HashSet<>();
                for (Piloto p : listaPrincipal) {
                    nomesExistentes.add(p.getNome().toLowerCase().trim());
                }

                // 3. Adiciona apenas os não duplicados
                for (Piloto pExtra : listaExtra) {
                    if (pExtra.getNome() != null && !nomesExistentes.contains(pExtra.getNome().toLowerCase().trim())) {
                        listaPrincipal.add(pExtra);
                    }
                }
            }
        }

        return listaPrincipal;
    }

    public static List<Pista> carregarCalendario(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/calendario.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Pista>>(){}.getType());
    }

    public static List<Motor> carregarMotores(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/motores.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Motor>>(){}.getType());
    }
    
    public static List<Patrocinador> carregarPatrocinadores(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/patrocinadores.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Patrocinador>>(){}.getType());
    }
    
    public static List<Motor> carregarMotoresPorArquivo(String caminhoArquivo) {
        return carregarLista(caminhoArquivo, new TypeToken<ArrayList<Motor>>(){}.getType());
    }

    private static <T> List<T> carregarLista(String caminho, Type tipo) {
        try (Reader reader = new FileReader(caminho, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, tipo);
        } catch (Exception e) {
            return null; 
        }
    }
}
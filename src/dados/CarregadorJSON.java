package dados;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import modelos.Motor;
import modelos.Patrocinador; // Importação da nova classe

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
        
        // Inicializa os padrões para garantir que a UI não quebre
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
                
                // Valida se a pasta tem o formato "nome_ano"
                if (nomePasta.contains("_")) {
                    String[] partes = nomePasta.split("_");
                    
                    // Garante que a divisão funcionou e tem pelo menos 2 partes
                    if (partes.length >= 2) {
                        String categoria = partes[0]; // ex: "wec"
                        String ano = partes[1];       // ex: "2023"
                        
                        // Cria a nova categoria na hora se não existir
                        if (!modsEncontrados.containsKey(categoria)) {
                            modsEncontrados.put(categoria, new ArrayList<>());
                        }
                        
                        modsEncontrados.get(categoria).add(ano);
                    }
                }
            }
        }
        
        // Ordena os anos de forma decrescente
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
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/pilotos.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Piloto>>(){}.getType());
    }

    public static List<Pista> carregarCalendario(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/calendario.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Pista>>(){}.getType());
    }

    public static List<Motor> carregarMotores(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/motores.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Motor>>(){}.getType());
    }
    
    // --- NOVO MÉTODO PARA PATROCINADORES ---
    public static List<Patrocinador> carregarPatrocinadores(String categoria, int ano) {
        String caminho = CAMINHO_MODS + categoria.toLowerCase() + "_" + ano + "/patrocinadores.json";
        return carregarLista(caminho, new TypeToken<ArrayList<Patrocinador>>(){}.getType());
    }
    
    // Permite carregar um arquivo específico pelo nome/caminho (Útil para o motores_extra.json)
    public static List<Motor> carregarMotoresPorArquivo(String caminhoArquivo) {
        // Se o arquivo estiver dentro da pasta mods, o usuário deve passar o caminho completo no TelaMotor.
        // Se estiver na raiz do projeto (como teste), basta passar o nome.
        return carregarLista(caminhoArquivo, new TypeToken<ArrayList<Motor>>(){}.getType());
    }

    // Método genérico privado
    private static <T> List<T> carregarLista(String caminho, Type tipo) {
        try (Reader reader = new FileReader(caminho, StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, tipo);
        } catch (Exception e) {
            // System.err.println("Arquivo não encontrado ou erro de leitura: " + caminho);
            return null; // Retorna null para tratarmos na tela se falhar
        }
    }
}
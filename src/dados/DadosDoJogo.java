package dados;

import modelos.Equipe;
import modelos.Motor; 
import modelos.Piloto;
import modelos.TipoContrato;
import servicos.CampeonatoService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DadosDoJogo {
    
    // --- O MUNDO DO JOGO ---
    private List<Equipe> todasAsEquipes;
    private List<Piloto> todosOsPilotos;
    private CampeonatoService campeonato;
    
    // --- O JOGADOR ---
    private Equipe equipeDoJogador;
    private String nomeDoDirigente;
    
    // --- METADADOS ---
    private String categoriaKey;
    private int anoAtual;
    
    // --- CONTROLE DE ARQUIVO ---
    private transient String arquivoAtual = null; 
    
    public DadosDoJogo(String categoriaKey, int ano) {
        this.categoriaKey = categoriaKey;
        this.anoAtual = ano;
        carregarEstadoInicial();
    }

    private void carregarEstadoInicial() {
        this.todasAsEquipes = CarregadorJSON.carregarEquipes(categoriaKey, anoAtual);
        this.todosOsPilotos = CarregadorJSON.carregarPilotos(categoriaKey, anoAtual);
        
        List<Motor> motores = CarregadorJSON.carregarMotores(categoriaKey, anoAtual);
        vincularMotores(motores);
        
        this.campeonato = new CampeonatoService(categoriaKey, anoAtual);
        vincularPilotosAsEquipes();
        inicializarFabricas();
    }
    
    // --- MÉTODOS DE VÍNCULO (Usados no Novo Jogo e no Load) ---
    
    private void vincularMotores(List<Motor> motores) {
        if (motores == null || todasAsEquipes == null) return;
        for (Equipe eq : todasAsEquipes) {
            if (eq.getMotor() == null) continue;
            for (Motor m : motores) {
                if (eq.getMotor().equalsIgnoreCase(m.getId())) {
                    eq.setMotorObjeto(m);
                    break;
                }
            }
        }
    }
    
    private void vincularPilotosAsEquipes() {
        if (todasAsEquipes == null || todosOsPilotos == null) return;

        for (Equipe eq : todasAsEquipes) {
            List<String> idsContratados = eq.getPilotosContratadosIDs();
            
            // Limpa as listas transientes para evitar duplicatas ao recarregar
            eq.getPilotosTitulares().clear();
            eq.getPilotosReservas().clear();
            
            if (idsContratados != null) {
                for (String idAlvo : idsContratados) {
                    Piloto pilotoEncontrado = buscarPilotoPorNome(idAlvo);
                    if (pilotoEncontrado != null) {
                        eq.adicionarPilotoDoLoad(pilotoEncontrado, TipoContrato.TITULAR);
                        pilotoEncontrado.setNomeEquipeAtual(eq.getNome());
                    }
                }
            }
        }
    }
    
    private void inicializarFabricas() {
        if (todasAsEquipes == null) return;
        for (Equipe eq : todasAsEquipes) {
            eq.inicializarFabricaInteligente();
        }
    }

    private Piloto buscarPilotoPorNome(String nome) {
        for (Piloto p : todosOsPilotos) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    
    // --- LÓGICA DE SALVAMENTO ---
    
    public boolean verificarSeSaveExiste(String nomeArquivo) {
        File f = new File("saves/" + nomeArquivo + ".save");
        return f.exists();
    }
    
    public boolean salvarJogo(String nomeArquivo) {
        try {
            // Sincroniza
            for (Equipe eq : todasAsEquipes) {
                eq.sincronizarDadosParaSalvar();
            }
            
            File folder = new File("saves");
            if (!folder.exists()) folder.mkdirs();
            
            String caminhoCompleto = "saves/" + nomeArquivo + ".save";
            
            try (Writer writer = new FileWriter(caminhoCompleto, StandardCharsets.UTF_8)) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                gson.toJson(this, writer);
            }
            
            this.arquivoAtual = nomeArquivo;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // --- LÓGICA DE CARREGAMENTO (NOVO) ---
    
    public static DadosDoJogo carregarJogo(String nomeArquivoCompleto) {
        try (Reader reader = new FileReader("saves/" + nomeArquivoCompleto)) {
            Gson gson = new Gson();
            DadosDoJogo dadosCarregados = gson.fromJson(reader, DadosDoJogo.class);
            
            // Define o nome do arquivo atual para QuickSave funcionar
            String nomeSemExtensao = nomeArquivoCompleto.replace(".save", "");
            dadosCarregados.setArquivoAtual(nomeSemExtensao);
            
            // Reconstrói as conexões que o JSON não salvou
            dadosCarregados.reconstruirPosLoad();
            
            return dadosCarregados;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void reconstruirPosLoad() {
        // 1. Recarregar Motores (Eles são dados estáticos do Mod, não salvos no save)
        List<Motor> motores = CarregadorJSON.carregarMotores(categoriaKey, anoAtual);
        vincularMotores(motores);
        
        // 2. Reconectar Pilotos às Equipes (Usando os IDs salvos)
        vincularPilotosAsEquipes();
        
        // 3. Corrigir referência da Equipe do Jogador
        // O JSON cria objetos novos. Precisamos apontar 'equipeDoJogador' 
        // para a instância que está dentro da lista 'todasAsEquipes'.
        if (this.equipeDoJogador != null) {
            for (Equipe e : todasAsEquipes) {
                if (e.getNome().equals(this.equipeDoJogador.getNome())) {
                    this.equipeDoJogador = e;
                    break;
                }
            }
        }
        
        // NOTA: Não chamamos inicializarFabricas() aqui para não resetar o progresso salvo!
    }
    
    // --- GETTERS E SETTERS ---
    
    public String getArquivoAtual() { return arquivoAtual; }
    public void setArquivoAtual(String arq) { this.arquivoAtual = arq; }
    
    public void definirEquipeDoJogador(String nomeEquipe, String nomeDirigente) {
        this.nomeDoDirigente = nomeDirigente;
        for (Equipe e : todasAsEquipes) {
            if (e.getNome().equals(nomeEquipe)) {
                this.equipeDoJogador = e;
                break;
            }
        }
    }

    public List<Equipe> getTodasAsEquipes() { return todasAsEquipes; }
    public List<Piloto> getTodosOsPilotos() { return todosOsPilotos; }
    public CampeonatoService getCampeonato() { return campeonato; }
    public Equipe getEquipeDoJogador() { return equipeDoJogador; }
    public String getNomeDoDirigente() { return nomeDoDirigente; }
    public int getAnoAtual() { return anoAtual; }
    public String getCategoriaKey() { return categoriaKey; }
}
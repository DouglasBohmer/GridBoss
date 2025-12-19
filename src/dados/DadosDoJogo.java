package dados;

import modelos.ConfiguracaoEconomia;
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
    
    // --- CONFIGURAÇÃO (DATA-DRIVEN) ---
    private transient ConfiguracaoEconomia configEconomia;

    // Construtor Vazio
    public DadosDoJogo() {
        this.configEconomia = new ConfiguracaoEconomia(); 
        this.configEconomia.setCustoContratacao(0.5);
        this.configEconomia.setSalarioMensalPorPessoa(0.01);
    }
    
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
        carregarConfiguracaoEconomia(); // Carrega o JSON da fábrica do MOD
    }
    
    // --- CARREGAMENTO DE ECONOMIA (JSON DO MOD) ---
    private void carregarConfiguracaoEconomia() {
        try {
            // Constrói o caminho específico do Mod: mods/CATEGORIA_ANO/fabrica.json
            // Ex: mods/f1_2024/fabrica.json
            String nomePastaMod = categoriaKey + "_" + anoAtual;
            String caminhoArquivoMod = "mods/" + nomePastaMod + "/fabrica.json";
            
            File f = new File(caminhoArquivoMod);
            
            if (f.exists()) {
                try (Reader reader = new FileReader(f)) {
                    this.configEconomia = new Gson().fromJson(reader, ConfiguracaoEconomia.class);
                  //  System.out.println("Configuração de fábrica carregada de: " + caminhoArquivoMod);
                }
            } else {
                // Tenta fallback genérico se não achar no mod
                File fGenerico = new File("json/fabrica.json");
                if (fGenerico.exists()) {
                    try (Reader reader = new FileReader(fGenerico)) {
                        this.configEconomia = new Gson().fromJson(reader, ConfiguracaoEconomia.class);
                        System.out.println("Aviso: fabrica.json não encontrado no mod. Usando genérico.");
                    }
                } else {
                    System.err.println("AVISO: Nenhum 'fabrica.json' encontrado. Usando valores padrão hardcoded.");
                    criarConfiguracaoPadrao();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            criarConfiguracaoPadrao();
        }
    }

    private void criarConfiguracaoPadrao() {
        this.configEconomia = new ConfiguracaoEconomia();
        this.configEconomia.setCustoContratacao(0.5);
        this.configEconomia.setReembolsoDemissao(0.25);
        this.configEconomia.setSalarioMensalPorPessoa(0.01);
        this.configEconomia.setCustoBaseEvolucao(5.0);
        this.configEconomia.setManutencaoPorNivel(0.1);
    }

    // --- MÉTODOS DE VÍNCULO ---
    
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
    
    // --- SALVAMENTO ---
    
    public boolean verificarSeSaveExiste(String nomeArquivo) {
        File f = new File("saves/" + nomeArquivo + ".save");
        return f.exists();
    }
    
    public boolean salvarJogo(String nomeArquivo) {
        try {
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
    
    // --- CARREGAMENTO ---
    
    public static DadosDoJogo carregarJogo(String nomeArquivoCompleto) {
        try (Reader reader = new FileReader("saves/" + nomeArquivoCompleto)) {
            Gson gson = new Gson();
            DadosDoJogo dadosCarregados = gson.fromJson(reader, DadosDoJogo.class);
            
            String nomeSemExtensao = nomeArquivoCompleto.replace(".save", "");
            dadosCarregados.setArquivoAtual(nomeSemExtensao);
            
            dadosCarregados.reconstruirPosLoad();
            
            return dadosCarregados;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void reconstruirPosLoad() {
        List<Motor> motores = CarregadorJSON.carregarMotores(categoriaKey, anoAtual);
        vincularMotores(motores);
        vincularPilotosAsEquipes();
        
        if (this.equipeDoJogador != null) {
            for (Equipe e : todasAsEquipes) {
                if (e.getNome().equals(this.equipeDoJogador.getNome())) {
                    this.equipeDoJogador = e;
                    break;
                }
            }
        }
        
        // Recarrega a economia baseado na categoria/ano salvos
        carregarConfiguracaoEconomia();
    }
    
    // --- GETTERS E SETTERS ---
    
    public ConfiguracaoEconomia getConfigEconomia() {
        if (configEconomia == null) carregarConfiguracaoEconomia();
        return configEconomia;
    }

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
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    private transient String arquivoAtual = null; 
    private transient ConfiguracaoEconomia configEconomia;

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
        
        // 1. CORREÇÃO CRÍTICA: Remove pilotos duplicados vindo dos JSONs (ex: Kyle Larson em dois arquivos)
        sanitizarListaMestraDePilotos();

        List<Motor> motores = CarregadorJSON.carregarMotores(categoriaKey, anoAtual);
        vincularMotores(motores);
        
        this.campeonato = new CampeonatoService(categoriaKey, anoAtual);
        vincularPilotosAsEquipes();
        inicializarFabricas();
        carregarConfiguracaoEconomia();
    }
    
    /**
     * Remove duplicatas da lista principal (todosOsPilotos).
     * Se houver dois pilotos com o mesmo nome, mantém o que tiver maior Overall.
     */
    private void sanitizarListaMestraDePilotos() {
        if (todosOsPilotos == null) return;

        Map<String, Piloto> mapaUnico = new HashMap<>();
        
        for (Piloto p : todosOsPilotos) {
            if (p == null || p.getNome() == null) continue;
            
            String chave = p.getNome().trim().toLowerCase();
            
            if (mapaUnico.containsKey(chave)) {
                // Se já existe, comparamos o Overall para manter o melhor (ou mais atual)
                Piloto existente = mapaUnico.get(chave);
                if (p.getOverall() > existente.getOverall()) {
                    mapaUnico.put(chave, p); // Substitui pelo melhor
                }
            } else {
                mapaUnico.put(chave, p);
            }
        }
        
        // Reconstrói a lista apenas com os únicos
        this.todosOsPilotos = new ArrayList<>(mapaUnico.values());
    }

    private void carregarConfiguracaoEconomia() {
        try {
            String nomePastaMod = categoriaKey + "_" + anoAtual;
            String caminhoArquivoMod = "mods/" + nomePastaMod + "/fabrica.json";
            File f = new File(caminhoArquivoMod);
            
            if (f.exists()) {
                try (Reader reader = new FileReader(f)) {
                    this.configEconomia = new Gson().fromJson(reader, ConfiguracaoEconomia.class);
                }
            } else {
                File fGenerico = new File("json/fabrica.json");
                if (fGenerico.exists()) {
                    try (Reader reader = new FileReader(fGenerico)) {
                        this.configEconomia = new Gson().fromJson(reader, ConfiguracaoEconomia.class);
                    }
                } else {
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
        
        boolean isF1 = categoriaKey != null && categoriaKey.toLowerCase().contains("f1");

        for (Equipe eq : todasAsEquipes) {
            List<String> idsContratados = eq.getPilotosContratadosIDs();
            
            eq.getPilotosTitulares().clear();
            eq.getPilotosReservas().clear();
            
            if (idsContratados != null) {
                int contador = 0;
                
                // Set para evitar adicionar o mesmo piloto (pelo nome) duas vezes na mesma equipe
                Set<String> nomesProcessadosNestaEquipe = new HashSet<>();
                
                for (String idAlvo : idsContratados) {
                    Piloto pilotoEncontrado = buscarPilotoPorNome(idAlvo);
                    
                    if (pilotoEncontrado != null) {
                        // Verifica duplicidade pelo NOME (resolve problemas de objetos diferentes mas mesma pessoa)
                        if (nomesProcessadosNestaEquipe.contains(pilotoEncontrado.getNome().toLowerCase())) {
                            continue;
                        }

                        // Verifica se o objeto já está nas listas (segurança extra)
                        if (eq.getPilotosTitulares().contains(pilotoEncontrado) || 
                            eq.getPilotosReservas().contains(pilotoEncontrado)) {
                            continue;
                        }

                        nomesProcessadosNestaEquipe.add(pilotoEncontrado.getNome().toLowerCase());
                        
                        TipoContrato tipo = TipoContrato.TITULAR;
                        if (isF1 && contador >= 2) {
                            tipo = TipoContrato.RESERVA;
                        }
                        
                        eq.adicionarPilotoDoLoad(pilotoEncontrado, tipo);
                        pilotoEncontrado.setNomeEquipeAtual(eq.getNome());
                        contador++;
                    }
                }
            }
            
            // 2. CORREÇÃO FINAL: Garante que um piloto não seja Titular e Reserva ao mesmo tempo
            sanitizarEquipe(eq);
        }
    }
    
    /**
     * Remove inconsistências dentro da equipe (ex: piloto duplicado ou piloto que é titular e reserva ao mesmo tempo).
     */
    private void sanitizarEquipe(Equipe eq) {
        // A. Remove duplicatas exatas dentro da lista de Titulares
        List<Piloto> titularesUnicos = new ArrayList<>();
        for (Piloto p : eq.getPilotosTitulares()) {
            if (!titularesUnicos.contains(p)) titularesUnicos.add(p);
        }
        eq.getPilotosTitulares().clear();
        eq.getPilotosTitulares().addAll(titularesUnicos);

        // B. Remove da lista de Reservas se já estiver nos Titulares
        List<Piloto> reservasValidos = new ArrayList<>();
        for (Piloto p : eq.getPilotosReservas()) {
            boolean jaEhTitular = eq.getPilotosTitulares().contains(p); // Checa objeto
            // Checa nome também para garantir
            for (Piloto tit : eq.getPilotosTitulares()) {
                if (tit.getNome().equalsIgnoreCase(p.getNome())) {
                    jaEhTitular = true; break;
                }
            }
            
            if (!jaEhTitular && !reservasValidos.contains(p)) {
                reservasValidos.add(p);
            }
        }
        eq.getPilotosReservas().clear();
        eq.getPilotosReservas().addAll(reservasValidos);
    }
    
    private void inicializarFabricas() {
        if (todasAsEquipes == null) return;
        for (Equipe eq : todasAsEquipes) {
            eq.inicializarFabricaInteligente();
        }
    }

    private Piloto buscarPilotoPorNome(String nome) {
        if (nome == null) return null;
        String nomeNormalizado = nome.trim();
        for (Piloto p : todosOsPilotos) {
            if (p.getNome().equalsIgnoreCase(nomeNormalizado)) {
                return p;
            }
        }
        return null;
    }
    
    // --- SALVAMENTO E CARREGAMENTO ---
    
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
            try (Writer writer = new FileWriter("saves/" + nomeArquivo + ".save", StandardCharsets.UTF_8)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(this, writer);
            }
            this.arquivoAtual = nomeArquivo;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static DadosDoJogo carregarJogo(String nomeArquivoCompleto) {
        try (Reader reader = new FileReader("saves/" + nomeArquivoCompleto)) {
            DadosDoJogo dados = new Gson().fromJson(reader, DadosDoJogo.class);
            dados.setArquivoAtual(nomeArquivoCompleto.replace(".save", ""));
            dados.reconstruirPosLoad();
            return dados;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void reconstruirPosLoad() {
        // Ao carregar save, também precisamos sanitizar a lista mestra de pilotos
        this.todosOsPilotos = CarregadorJSON.carregarPilotos(categoriaKey, anoAtual);
        sanitizarListaMestraDePilotos();
        
        List<Motor> motores = CarregadorJSON.carregarMotores(categoriaKey, anoAtual);
        vincularMotores(motores);
        
        // Recalcula os vínculos para garantir integridade
        vincularPilotosAsEquipes();
        
        if (this.equipeDoJogador != null) {
            for (Equipe e : todasAsEquipes) {
                if (e.getNome().equals(this.equipeDoJogador.getNome())) {
                    this.equipeDoJogador = e;
                    break;
                }
            }
        }
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
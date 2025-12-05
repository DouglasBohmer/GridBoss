package dados;

import modelos.Equipe;
import modelos.Piloto;
import modelos.TipoContrato;
import servicos.CampeonatoService;

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

    public DadosDoJogo(String categoriaKey, int ano) {
        this.categoriaKey = categoriaKey;
        this.anoAtual = ano;
        carregarEstadoInicial();
    }

    private void carregarEstadoInicial() {
        // 1. Carrega as listas cruas do JSON
        this.todasAsEquipes = CarregadorJSON.carregarEquipes(categoriaKey, anoAtual);
        this.todosOsPilotos = CarregadorJSON.carregarPilotos(categoriaKey, anoAtual);
        
        // 2. Inicia o serviço de campeonato (Calendário)
        this.campeonato = new CampeonatoService(categoriaKey, anoAtual);
        
        // 3. Faz o vínculo lógico (Coloca os objetos Piloto dentro dos objetos Equipe)
        vincularPilotosAsEquipes();
    }

    private void vincularPilotosAsEquipes() {
        if (todasAsEquipes == null || todosOsPilotos == null) return;

        for (Equipe eq : todasAsEquipes) {
            List<String> idsContratados = eq.getPilotosContratadosIDs();
            
            if (idsContratados != null) {
                for (String idAlvo : idsContratados) {
                    Piloto pilotoEncontrado = buscarPilotoPorNome(idAlvo);
                    
                    if (pilotoEncontrado != null) {
                        // Adiciona na equipe e atualiza o status do piloto
                        eq.adicionarPilotoDoLoad(pilotoEncontrado, TipoContrato.TITULAR);
                        pilotoEncontrado.setNomeEquipeAtual(eq.getNome());
                    }
                }
            }
        }
    }

    private Piloto buscarPilotoPorNome(String nome) {
        for (Piloto p : todosOsPilotos) {
            // Comparação simples ignorando maiúsculas/minúsculas
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    
    // --- GETTERS E SETTERS ---
    
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
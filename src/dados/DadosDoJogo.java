package dados;

import modelos.Equipe;
import modelos.Motor; // IMPORTANTE: Importar Motor
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
        
        // --- 1.1 CARREGAR E VINCULAR MOTORES ---
        List<Motor> motores = CarregadorJSON.carregarMotores(categoriaKey, anoAtual);
        vincularMotores(motores);
        // ---------------------------------------
        
        // 2. Inicia o serviço de campeonato (Calendário)
        this.campeonato = new CampeonatoService(categoriaKey, anoAtual);
        
        // 3. Faz o vínculo lógico (Coloca os objetos Piloto dentro dos objetos Equipe)
        vincularPilotosAsEquipes();
        
        // 4. Inicializa as Fábricas (Com base na reputação)
        inicializarFabricas();
    }
    
    private void vincularMotores(List<Motor> motores) {
        if (motores == null || todasAsEquipes == null) return;
        
        for (Equipe eq : todasAsEquipes) {
            if (eq.getMotor() == null) continue;
            
            for (Motor m : motores) {
                // Compara o ID na Equipe ("Renault") com o ID do Motor ("Renault")
                if (eq.getMotor().equalsIgnoreCase(m.getId())) {
                    eq.setMotorObjeto(m);
                    break;
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

    private void vincularPilotosAsEquipes() {
        if (todasAsEquipes == null || todosOsPilotos == null) return;

        for (Equipe eq : todasAsEquipes) {
            List<String> idsContratados = eq.getPilotosContratadosIDs();
            
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

    private Piloto buscarPilotoPorNome(String nome) {
        for (Piloto p : todosOsPilotos) {
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
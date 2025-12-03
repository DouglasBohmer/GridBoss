package telas;

import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import servicos.CampeonatoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JPanel contentPane;

    // --- DADOS DO JOGO ---
    private Equipe equipeJogavel;
    private CampeonatoService campeonato;
    
    // Controle de Navegação Visual (separado da etapa real do save)
    private int indiceEtapaVisual = 0; 

    // --- COMPONENTES VISUAIS ---
    
    // Topo Esquerdo (Equipe)
    private JLabel LB_LogoEquipe;
    private JLabel LB_LogoMotorPQ;
    private JLabel LB_BandeiraMotor;
    private JLabel LB_Motor;
    private JLabel LB_BandeiraSede;
    private JLabel LB_SedeEquipe;

    // Topo Direito (Info Jogo)
    private JLabel LB_CategoriaEscolhida;
    private JLabel LB_NomeDirigente;
    private JLabel LB_Ano;
    private JLabel LB_Temporada;
    private JLabel LB_Orc;
    private JLabel LB_IconeDinheiro;

    // Pilotos
    private JLabel lblTituloPilotos;
    // P1
    private JLabel LB_BandeiraP1, LB_NumP1, LB_NomeP1, LB_IdadeP1, LB_TempoContratoP1, LB_StatusP1;
    // P2
    private JLabel LB_BandeiraP2, LB_NumP2, LB_NomeP2, LB_IdadeP2, LB_TempoContratoP2, LB_StatusP2;
    // P3
    private JLabel LB_BandeiraP3, LB_NumP3, LB_NomeP3, LB_IdadeP3, LB_TempoContratoP3, LB_StatusP3;
    // P4
    private JLabel LB_BandeiraP4, LB_NumP4, LB_NomeP4, LB_IdadeP4, LB_TempoContratoP4, LB_StatusP4;
    // P5
    private JLabel LB_BandeiraP5, LB_NumP5, LB_NomeP5, LB_IdadeP5, LB_TempoContratoP5, LB_StatusP5;

    // Centro/Direita (Pista e Calendário)
    private JLabel LB_PistaEtapas;
    private JLabel LB_ImagemPista;
    private JLabel LB_BandeiraPista;
    private JLabel LB_LocalPista; // Agora mostra País
    
    // Dados Técnicos da Pista
    private JLabel LB_TipoPista;
    private JLabel LB_NomeEtapa; // Novo: Mostra o nome do GP
    private JLabel LB_DesgastePneu; // Novo: Regra de Desgaste
    private JLabel LB_ComprimentoPista;
    private JLabel LB_VoltaPista;

    // Botões e Abas
    private JButton BT_EtapaAnt;
    private JButton BT_EtapaProxima;
    private JButton BT_DetalhesPista;
    private JButton BT_VoltarAtual;
    private JButton BT_IrParaCorrida;
    
    private JLabel LB_Classificacoes;
    private JTabbedPane tabbedPane;
    private JLabel LB_CarrosCat;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaPrincipal frame = new TelaPrincipal(new Equipe("Teste", 100, 50));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaPrincipal(Equipe equipe) {
        this.equipeJogavel = equipe;
        this.campeonato = new CampeonatoService(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
        
        // Inicializa o índice visual com a etapa atual do jogo
        this.indiceEtapaVisual = this.campeonato.getNumeroEtapaAtual() - 1; 
        if (this.indiceEtapaVisual < 0) this.indiceEtapaVisual = 0;

        setTitle("Motorsport Manager - Grid Boss");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/resource/Icone16px.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1135, 674);
        setResizable(false);

        // MENU BAR
        criarMenu();

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initComponents();
        atualizarDados();
    }
    
    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        setJMenuBar(menuBar);

        JMenu Menu_Geral = new JMenu("Geral");
        menuBar.add(Menu_Geral);
        
        JMenuItem MenuItem_NovoJogo = new JMenuItem("Iniciar Novo Jogo");
        MenuItem_NovoJogo.addActionListener(e -> {
            TelaSelecionarCategoria nova = new TelaSelecionarCategoria();
            nova.setVisible(true);
            nova.setLocationRelativeTo(null);
            dispose();
        });
        Menu_Geral.add(MenuItem_NovoJogo);
        Menu_Geral.add(new JMenuItem("Salvar Jogo"));
        Menu_Geral.add(new JMenuItem("Carregar Jogo"));

        JMenu Menu_Equipe = new JMenu("Equipe");
        menuBar.add(Menu_Equipe);
        Menu_Equipe.add(new JMenuItem("Fábrica"));
        Menu_Equipe.add(new JMenuItem("Patrocínios"));

        JMenu Menu_Piloto = new JMenu("Pilotos");
        menuBar.add(Menu_Piloto);
        Menu_Piloto.add(new JMenuItem("Meus Pilotos"));
        Menu_Piloto.add(new JMenuItem("Mercado de Pilotos"));

        JMenu Menu_Financeiro = new JMenu("Financeiro");
        menuBar.add(Menu_Financeiro);
        Menu_Financeiro.add(new JMenuItem("Contas da Equipe"));

        JMenu Menu_Historia = new JMenu("História");
        menuBar.add(Menu_Historia);
    }

    private void initComponents() {
        // === EQUIPE ===
        LB_LogoEquipe = new JLabel("");
        LB_LogoEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        LB_LogoEquipe.setBounds(10, 0, 200, 96);
        contentPane.add(LB_LogoEquipe);

        LB_LogoMotorPQ = new JLabel("");
        LB_LogoMotorPQ.setHorizontalAlignment(SwingConstants.CENTER);
        LB_LogoMotorPQ.setBounds(461, 11, 45, 33);
        contentPane.add(LB_LogoMotorPQ);

        LB_BandeiraMotor = new JLabel("");
        LB_BandeiraMotor.setHorizontalAlignment(SwingConstants.CENTER);
        LB_BandeiraMotor.setBounds(415, 11, 45, 33);
        contentPane.add(LB_BandeiraMotor);
        
        LB_Motor = new JLabel("Motor");
        LB_Motor.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Motor.setBounds(285, 11, 120, 33);
        contentPane.add(LB_Motor);

        LB_BandeiraSede = new JLabel("");
        LB_BandeiraSede.setHorizontalAlignment(SwingConstants.CENTER);
        LB_BandeiraSede.setBounds(415, 55, 45, 33);
        contentPane.add(LB_BandeiraSede);

        LB_SedeEquipe = new JLabel("Sede");
        LB_SedeEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        LB_SedeEquipe.setBounds(285, 55, 118, 33);
        contentPane.add(LB_SedeEquipe);

        // === INFO JOGO ===
        LB_CategoriaEscolhida = new JLabel("");
        LB_CategoriaEscolhida.setHorizontalAlignment(SwingConstants.CENTER);
        LB_CategoriaEscolhida.setBounds(640, 0, 228, 100);
        contentPane.add(LB_CategoriaEscolhida);

        LB_NomeDirigente = new JLabel("Nome Dirigente");
        LB_NomeDirigente.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
        LB_NomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        LB_NomeDirigente.setBounds(878, 0, 239, 49);
        contentPane.add(LB_NomeDirigente);

        LB_Ano = new JLabel("Ano 2024");
        LB_Ano.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_Ano.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Ano.setBounds(866, 50, 100, 25);
        contentPane.add(LB_Ano);

        LB_Temporada = new JLabel("Temporada 1");
        LB_Temporada.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_Temporada.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Temporada.setBounds(866, 75, 250, 25);
        contentPane.add(LB_Temporada);

        LB_Orc = new JLabel("€ -- milhões");
        LB_Orc.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Orc.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_Orc.setBounds(970, 50, 100, 25);
        contentPane.add(LB_Orc);

        LB_IconeDinheiro = new JLabel("");
        LB_IconeDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
        LB_IconeDinheiro.setBounds(1072, 50, 45, 25);
        carregarImagem(LB_IconeDinheiro, "/resource/IconeEuro24px.png");
        contentPane.add(LB_IconeDinheiro);

        // === PILOTOS ===
        lblTituloPilotos = new JLabel("MEUS PILOTOS");
        lblTituloPilotos.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        lblTituloPilotos.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloPilotos.setBounds(12, 99, 618, 25);
        contentPane.add(lblTituloPilotos);

        // P1
        LB_BandeiraP1 = new JLabel(""); LB_BandeiraP1.setHorizontalAlignment(SwingConstants.CENTER); LB_BandeiraP1.setBounds(10, 135, 25, 25); contentPane.add(LB_BandeiraP1);
        LB_NumP1 = new JLabel("#1"); LB_NumP1.setHorizontalAlignment(SwingConstants.CENTER); LB_NumP1.setBounds(35, 135, 35, 25); contentPane.add(LB_NumP1);
        LB_NomeP1 = new JLabel("Piloto 1"); LB_NomeP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14)); LB_NomeP1.setBounds(70, 135, 205, 25); contentPane.add(LB_NomeP1);
        LB_IdadeP1 = new JLabel("Anos"); LB_IdadeP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_IdadeP1.setHorizontalAlignment(SwingConstants.CENTER); LB_IdadeP1.setBounds(275, 135, 50, 25); contentPane.add(LB_IdadeP1);
        LB_TempoContratoP1 = new JLabel("Contrato"); LB_TempoContratoP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_TempoContratoP1.setHorizontalAlignment(SwingConstants.CENTER); LB_TempoContratoP1.setBounds(330, 135, 145, 25); contentPane.add(LB_TempoContratoP1);
        LB_StatusP1 = new JLabel("Piloto 1"); LB_StatusP1.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_StatusP1.setHorizontalAlignment(SwingConstants.CENTER); LB_StatusP1.setBounds(485, 135, 145, 25); contentPane.add(LB_StatusP1);

        // P2
        LB_BandeiraP2 = new JLabel(""); LB_BandeiraP2.setHorizontalAlignment(SwingConstants.CENTER); LB_BandeiraP2.setBounds(10, 160, 25, 25); contentPane.add(LB_BandeiraP2);
        LB_NumP2 = new JLabel("#2"); LB_NumP2.setHorizontalAlignment(SwingConstants.CENTER); LB_NumP2.setBounds(35, 160, 35, 25); contentPane.add(LB_NumP2);
        LB_NomeP2 = new JLabel("Piloto 2"); LB_NomeP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14)); LB_NomeP2.setBounds(70, 160, 205, 25); contentPane.add(LB_NomeP2);
        LB_IdadeP2 = new JLabel("Anos"); LB_IdadeP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_IdadeP2.setHorizontalAlignment(SwingConstants.CENTER); LB_IdadeP2.setBounds(275, 160, 50, 25); contentPane.add(LB_IdadeP2);
        LB_TempoContratoP2 = new JLabel("Contrato"); LB_TempoContratoP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_TempoContratoP2.setHorizontalAlignment(SwingConstants.CENTER); LB_TempoContratoP2.setBounds(330, 160, 145, 25); contentPane.add(LB_TempoContratoP2);
        LB_StatusP2 = new JLabel("Piloto 2"); LB_StatusP2.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_StatusP2.setHorizontalAlignment(SwingConstants.CENTER); LB_StatusP2.setBounds(485, 160, 145, 25); contentPane.add(LB_StatusP2);

        // P3
        LB_BandeiraP3 = new JLabel(""); LB_BandeiraP3.setHorizontalAlignment(SwingConstants.CENTER); LB_BandeiraP3.setBounds(10, 185, 25, 25); contentPane.add(LB_BandeiraP3);
        LB_NumP3 = new JLabel("#3"); LB_NumP3.setHorizontalAlignment(SwingConstants.CENTER); LB_NumP3.setBounds(35, 185, 35, 25); contentPane.add(LB_NumP3);
        LB_NomeP3 = new JLabel("Piloto 3"); LB_NomeP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14)); LB_NomeP3.setBounds(70, 185, 205, 25); contentPane.add(LB_NomeP3);
        LB_IdadeP3 = new JLabel("Anos"); LB_IdadeP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_IdadeP3.setHorizontalAlignment(SwingConstants.CENTER); LB_IdadeP3.setBounds(275, 185, 50, 25); contentPane.add(LB_IdadeP3);
        LB_TempoContratoP3 = new JLabel("Contrato"); LB_TempoContratoP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_TempoContratoP3.setHorizontalAlignment(SwingConstants.CENTER); LB_TempoContratoP3.setBounds(330, 185, 145, 25); contentPane.add(LB_TempoContratoP3);
        LB_StatusP3 = new JLabel("Piloto 3"); LB_StatusP3.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_StatusP3.setHorizontalAlignment(SwingConstants.CENTER); LB_StatusP3.setBounds(485, 185, 145, 25); contentPane.add(LB_StatusP3);

        // P4
        LB_BandeiraP4 = new JLabel(""); LB_BandeiraP4.setHorizontalAlignment(SwingConstants.CENTER); LB_BandeiraP4.setBounds(10, 210, 25, 25); contentPane.add(LB_BandeiraP4);
        LB_NumP4 = new JLabel("#4"); LB_NumP4.setHorizontalAlignment(SwingConstants.CENTER); LB_NumP4.setBounds(35, 210, 35, 25); contentPane.add(LB_NumP4);
        LB_NomeP4 = new JLabel("Piloto 4"); LB_NomeP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14)); LB_NomeP4.setBounds(70, 210, 205, 25); contentPane.add(LB_NomeP4);
        LB_IdadeP4 = new JLabel("Anos"); LB_IdadeP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_IdadeP4.setHorizontalAlignment(SwingConstants.CENTER); LB_IdadeP4.setBounds(275, 210, 50, 25); contentPane.add(LB_IdadeP4);
        LB_TempoContratoP4 = new JLabel("Contrato"); LB_TempoContratoP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_TempoContratoP4.setHorizontalAlignment(SwingConstants.CENTER); LB_TempoContratoP4.setBounds(330, 210, 145, 25); contentPane.add(LB_TempoContratoP4);
        LB_StatusP4 = new JLabel("Piloto 4"); LB_StatusP4.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_StatusP4.setHorizontalAlignment(SwingConstants.CENTER); LB_StatusP4.setBounds(485, 210, 145, 25); contentPane.add(LB_StatusP4);

        // P5
        LB_BandeiraP5 = new JLabel(""); LB_BandeiraP5.setHorizontalAlignment(SwingConstants.CENTER); LB_BandeiraP5.setBounds(10, 235, 25, 25); contentPane.add(LB_BandeiraP5);
        LB_NumP5 = new JLabel("#5"); LB_NumP5.setHorizontalAlignment(SwingConstants.CENTER); LB_NumP5.setBounds(35, 235, 35, 25); contentPane.add(LB_NumP5);
        LB_NomeP5 = new JLabel("Piloto 5"); LB_NomeP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 14)); LB_NomeP5.setBounds(70, 235, 205, 25); contentPane.add(LB_NomeP5);
        LB_IdadeP5 = new JLabel("Anos"); LB_IdadeP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_IdadeP5.setHorizontalAlignment(SwingConstants.CENTER); LB_IdadeP5.setBounds(275, 235, 50, 25); contentPane.add(LB_IdadeP5);
        LB_TempoContratoP5 = new JLabel("Contrato"); LB_TempoContratoP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_TempoContratoP5.setHorizontalAlignment(SwingConstants.CENTER); LB_TempoContratoP5.setBounds(330, 235, 145, 25); contentPane.add(LB_TempoContratoP5);
        LB_StatusP5 = new JLabel("Piloto 5"); LB_StatusP5.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12)); LB_StatusP5.setHorizontalAlignment(SwingConstants.CENTER); LB_StatusP5.setBounds(485, 235, 145, 25); contentPane.add(LB_StatusP5);

        // === CALENDÁRIO / PISTA ===
        LB_PistaEtapas = new JLabel("CALENDÁRIO, ETAPA 1/1");
        LB_PistaEtapas.setHorizontalAlignment(SwingConstants.CENTER);
        LB_PistaEtapas.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        LB_PistaEtapas.setBounds(640, 107, 465, 25);
        contentPane.add(LB_PistaEtapas);

        LB_ImagemPista = new JLabel("");
        LB_ImagemPista.setHorizontalAlignment(SwingConstants.CENTER);
        LB_ImagemPista.setBounds(640, 143, 250, 203);
        LB_ImagemPista.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contentPane.add(LB_ImagemPista);

        LB_LocalPista = new JLabel("País");
        LB_LocalPista.setForeground(new Color(0, 0, 0));
        LB_LocalPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_LocalPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_LocalPista.setBounds(900, 143, 180, 30);
        contentPane.add(LB_LocalPista);
        
        LB_BandeiraPista = new JLabel("");
        LB_BandeiraPista.setHorizontalAlignment(SwingConstants.CENTER);
        LB_BandeiraPista.setBounds(1072, 143, 45, 33);
        contentPane.add(LB_BandeiraPista);

        LB_TipoPista = new JLabel("Tipo Pista");
        LB_TipoPista.setForeground(new Color(0, 0, 0));
        LB_TipoPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_TipoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_TipoPista.setBounds(900, 173, 205, 30);
        contentPane.add(LB_TipoPista);

        LB_NomeEtapa = new JLabel("Nome Etapa");
        LB_NomeEtapa.setForeground(new Color(0, 0, 0));
        LB_NomeEtapa.setHorizontalAlignment(SwingConstants.LEFT);
        LB_NomeEtapa.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_NomeEtapa.setBounds(900, 203, 205, 30);
        contentPane.add(LB_NomeEtapa);

        LB_DesgastePneu = new JLabel("Desgaste Pneu");
        LB_DesgastePneu.setForeground(new Color(0, 0, 0));
        LB_DesgastePneu.setHorizontalAlignment(SwingConstants.LEFT);
        LB_DesgastePneu.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_DesgastePneu.setBounds(900, 233, 205, 30);
        contentPane.add(LB_DesgastePneu);

        LB_ComprimentoPista = new JLabel("Comprimento");
        LB_ComprimentoPista.setForeground(new Color(0, 0, 0));
        LB_ComprimentoPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_ComprimentoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_ComprimentoPista.setBounds(900, 263, 205, 30);
        contentPane.add(LB_ComprimentoPista);

        LB_VoltaPista = new JLabel("Voltas");
        LB_VoltaPista.setForeground(new Color(0, 0, 0));
        LB_VoltaPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_VoltaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_VoltaPista.setBounds(900, 293, 205, 30);
        contentPane.add(LB_VoltaPista);

        // === BOTÕES DE NAVEGAÇÃO ===
        BT_EtapaAnt = new JButton("Etapa Anterior");
        BT_EtapaAnt.setBounds(643, 364, 225, 23);
        BT_EtapaAnt.addActionListener(e -> navegarCalendarioVisual(-1));
        contentPane.add(BT_EtapaAnt);

        BT_EtapaProxima = new JButton("Próxima Etapa");
        BT_EtapaProxima.setBounds(878, 364, 225, 23);
        BT_EtapaProxima.addActionListener(e -> navegarCalendarioVisual(1));
        contentPane.add(BT_EtapaProxima);

        BT_DetalhesPista = new JButton("Ver Traçado Detalhado");
        BT_DetalhesPista.setEnabled(false);
        BT_DetalhesPista.setBounds(643, 398, 225, 23);
        contentPane.add(BT_DetalhesPista);

        BT_VoltarAtual = new JButton("Voltar Etapa Atual");
        BT_VoltarAtual.setBounds(878, 398, 225, 23);
        BT_VoltarAtual.addActionListener(e -> {
            this.indiceEtapaVisual = campeonato.getNumeroEtapaAtual() - 1;
            atualizarCalendarioUI();
        });
        contentPane.add(BT_VoltarAtual);

        // === CLASSIFICAÇÃO E ABAS ===
        LB_Classificacoes = new JLabel("CLASSIFICAÇÃO DOS CAMPEONATOS");
        LB_Classificacoes.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Classificacoes.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        LB_Classificacoes.setBounds(10, 271, 620, 25);
        contentPane.add(LB_Classificacoes);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 307, 620, 299);
        contentPane.add(tabbedPane);
        
        JPanel pnlPilotos = new JPanel(null);
        pnlPilotos.add(new JLabel("Tabela de Pilotos aqui..."));
        tabbedPane.addTab("Classificação Pilotos", new ImageIcon(getClass().getResource("/resource/Icone24pxPiloto.png")), pnlPilotos);
        
        JPanel pnlEquipes = new JPanel(null);
        tabbedPane.addTab("Classificação Equipes", new ImageIcon(getClass().getResource("/resource/Icone24pxEquipe.png")), pnlEquipes);
        
        JPanel pnlResultados = new JPanel(null);
        tabbedPane.addTab("Resultados", new ImageIcon(getClass().getResource("/resource/Icone24pxTrofeu.png")), pnlResultados);

        // === RODAPÉ ===
        LB_CarrosCat = new JLabel("");
        LB_CarrosCat.setHorizontalAlignment(SwingConstants.CENTER);
        LB_CarrosCat.setVerticalAlignment(SwingConstants.TOP);
        LB_CarrosCat.setBounds(642, 432, 464, 126);
        contentPane.add(LB_CarrosCat);

        BT_IrParaCorrida = new JButton("IR PARA A CORRIDA!");
        BT_IrParaCorrida.setFont(new Font("Arial", Font.BOLD, 16));
        BT_IrParaCorrida.setIcon(new ImageIcon(getClass().getResource("/resource/Icone24pxBandeiraDeChegada.png")));
        BT_IrParaCorrida.setBounds(643, 569, 460, 33);
        BT_IrParaCorrida.addActionListener(e -> irParaCorrida());
        contentPane.add(BT_IrParaCorrida);
    }

    private void atualizarDados() {
        if (equipeJogavel == null) return;

        // Equipe
        LB_SedeEquipe.setText(equipeJogavel.getSede());
        LB_Motor.setText("Motor " + equipeJogavel.getMotor());
        LB_Orc.setText(String.format("€ %.1f milhões", equipeJogavel.getSaldoFinanceiro()));
        LB_NomeDirigente.setText(TelaSelecionarEquipe.nomeDirigente);
        LB_Ano.setText("Ano " + SessaoJogo.anoSelecionado);
        
        carregarImagem(LB_LogoEquipe, equipeJogavel.getCaminhoLogo());
        carregarImagem(LB_BandeiraMotor, equipeJogavel.getCaminhoBandeiraMotor());
        carregarImagem(LB_LogoMotorPQ, equipeJogavel.getCaminhoLogoMotor());
        carregarImagem(LB_BandeiraSede, equipeJogavel.getCaminhoBandeiraSede());

        if (SessaoJogo.categoriaKey.contains("f1")) {
            carregarImagem(LB_CategoriaEscolhida, "/resource/Logo Novo_F1_OKPQ.png");
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.png");
            LB_TipoPista.setForeground(Color.BLACK);
        } else if (SessaoJogo.categoriaKey.contains("indy")) {
            carregarImagem(LB_CategoriaEscolhida, "/resource/Logo Indy_OKPQ.png");
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.png");
            LB_TipoPista.setForeground(Color.BLUE);
        } else {
            carregarImagem(LB_CategoriaEscolhida, "/resource/Logo Nascar_OKPQ.png");
            carregarImagem(LB_CarrosCat, "/resource/Cup Series.png");
            LB_TipoPista.setForeground(Color.BLUE);
        }

        // Pilotos
        List<Piloto> pilotos = equipeJogavel.getPilotosTitulares();
        pilotos.addAll(equipeJogavel.getPilotosReservas());

        atualizarSlotPiloto(0, pilotos, LB_NomeP1, LB_NumP1, LB_IdadeP1, LB_TempoContratoP1, LB_BandeiraP1, LB_StatusP1);
        atualizarSlotPiloto(1, pilotos, LB_NomeP2, LB_NumP2, LB_IdadeP2, LB_TempoContratoP2, LB_BandeiraP2, LB_StatusP2);
        atualizarSlotPiloto(2, pilotos, LB_NomeP3, LB_NumP3, LB_IdadeP3, LB_TempoContratoP3, LB_BandeiraP3, LB_StatusP3);
        atualizarSlotPiloto(3, pilotos, LB_NomeP4, LB_NumP4, LB_IdadeP4, LB_TempoContratoP4, LB_BandeiraP4, LB_StatusP4);
        atualizarSlotPiloto(4, pilotos, LB_NomeP5, LB_NumP5, LB_IdadeP5, LB_TempoContratoP5, LB_BandeiraP5, LB_StatusP5);

        atualizarCalendarioUI();
    }

    private void atualizarSlotPiloto(int index, List<Piloto> lista, JLabel lbNome, JLabel lbNum, JLabel lbIdade, JLabel lbContrato, JLabel lbFlag, JLabel lbStatus) {
        if (index < lista.size()) {
            Piloto p = lista.get(index);
            lbNome.setText(p.getNome());
            lbNum.setText("#" + p.getNumero());
            lbIdade.setText(p.getIdade() + " anos");
            lbContrato.setText("Contrato Ativo");
            carregarImagem(lbFlag, "/resource/Bandeira " + p.getNacionalidade() + ".png");
            
            // Força visibilidade
            lbNome.setVisible(true);
            lbNum.setVisible(true);
            lbIdade.setVisible(true);
            lbContrato.setVisible(true);
            lbFlag.setVisible(true);
            lbStatus.setVisible(true);

            // Regra de Cor e Texto: F1 tem Reserva a partir do 3º (index 2). Indy/Nascar todos titulares.
            boolean isF1 = SessaoJogo.categoriaKey.toLowerCase().contains("f1");
            boolean isReserva = isF1 && index >= 2;

            if (isReserva) {
                Color corReserva = Color.GRAY;
                lbNome.setForeground(corReserva);
                lbNum.setForeground(corReserva);
                lbIdade.setForeground(corReserva);
                lbContrato.setForeground(corReserva);
                lbStatus.setForeground(corReserva);
                // Ex: index 2 vira "Reserva 1"
                lbStatus.setText("Reserva " + (index - 1));
            } else {
                Color corTitular = Color.BLACK;
                lbNome.setForeground(corTitular);
                lbNum.setForeground(corTitular);
                lbIdade.setForeground(corTitular);
                lbContrato.setForeground(corTitular);
                lbStatus.setForeground(corTitular);
                // Ex: index 0 vira "Piloto 1"
                lbStatus.setText("Piloto " + (index + 1));
            }
        } else {
            // Sem piloto nessa vaga: Esconde a linha toda
            lbNome.setVisible(false);
            lbNum.setVisible(false);
            lbIdade.setVisible(false);
            lbContrato.setVisible(false);
            lbFlag.setVisible(false);
            lbStatus.setVisible(false);
        }
    }

    private void atualizarCalendarioUI() {
        Pista pistaVisual = buscarPistaPorIndice(indiceEtapaVisual);
        
        int etapaReal = campeonato.getNumeroEtapaAtual();
        int total = campeonato.getTotalEtapas();
        int etapaVisualNumero = indiceEtapaVisual + 1;
        
        LB_PistaEtapas.setText("CALENDÁRIO, ETAPA " + etapaVisualNumero + "/" + total);
        LB_Temporada.setText("Temporada 1, Etapa Atual: " + etapaReal + "/" + total);

        if (pistaVisual != null) {
            LB_LocalPista.setText(pistaVisual.getPais());
            carregarImagem(LB_BandeiraPista, "/resource/Bandeira " + pistaVisual.getPais() + ".png");

            LB_NomeEtapa.setText(pistaVisual.getNome());
            LB_TipoPista.setText(pistaVisual.getTipo() != null ? pistaVisual.getTipo().toString() : "Misto");
            LB_VoltaPista.setText(pistaVisual.getQtdVoltas() + " voltas");

            String unidade = "km";
            if (SessaoJogo.categoriaKey.contains("indy") || SessaoJogo.categoriaKey.contains("nascar")) {
                unidade = "milhas";
            }
            LB_ComprimentoPista.setText(pistaVisual.getComprimentoKm() + " " + unidade);

            double desgaste = pistaVisual.getFatorDesgastePneu();
            String desgasteTexto;
            if (desgaste < 1.0) desgasteTexto = "Baixo";
            else if (desgaste <= 1.5) desgasteTexto = "Médio";
            else desgasteTexto = "Alto";
            LB_DesgastePneu.setText("Desgaste Pneu: " + desgasteTexto);

            String caminhoImagem = "/resource/Icone64pxErro.png";
            String nomeOriginal = pistaVisual.getCaminhoTracado();
            if (nomeOriginal != null && nomeOriginal.contains(".png")) {
                caminhoImagem = nomeOriginal.replace(".png", "PQ.png");
            }
            carregarImagem(LB_ImagemPista, caminhoImagem);
        }

        BT_EtapaAnt.setEnabled(indiceEtapaVisual > 0);
        BT_EtapaProxima.setEnabled(indiceEtapaVisual < total - 1);
    }
    
    private Pista buscarPistaPorIndice(int index) {
        try {
            List<Pista> todas = dados.CarregadorJSON.carregarCalendario(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
            if (index >= 0 && index < todas.size()) {
                return todas.get(index);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private void navegarCalendarioVisual(int direcao) {
        this.indiceEtapaVisual += direcao;
        atualizarCalendarioUI();
    }

    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path != null && !path.isEmpty()) {
                if (!path.startsWith("/")) path = "/" + path;
                if (!path.startsWith("/resource")) path = "/resource" + path;
                path = path.replace("//", "/");
                
                java.net.URL imgUrl = getClass().getResource(path);
                if (imgUrl != null) {
                    lbl.setIcon(new ImageIcon(imgUrl));
                } else {
                    lbl.setIcon(null);
                }
            }
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    private void irParaCorrida() {
        JOptionPane.showMessageDialog(this, "Carregando fim de semana de corrida...");
    }
}
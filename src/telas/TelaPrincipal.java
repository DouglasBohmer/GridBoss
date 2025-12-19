package telas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import dados.CarregadorJSON;
import dados.DadosDoJogo;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import servicos.CampeonatoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TelaPrincipal extends JFrame {

    private JPanel contentPane;

    // --- DADOS DO JOGO ---
    private DadosDoJogo dadosDoJogo;
    private Equipe equipeJogavel;
    private CampeonatoService campeonato;
    
    private int indiceEtapaVisual = 0; 

    // --- COMPONENTES VISUAIS ---
    private JLabel LB_LogoEquipe, LB_LogoMotorPQ, LB_BandeiraMotor, LB_Motor, LB_BandeiraSede, LB_SedeEquipe;
    private JLabel LB_CategoriaEscolhida, LB_NomeDirigente, LB_Ano, LB_Temporada, LB_Orc, LB_IconeDinheiro;

    private JLabel lblTituloPilotos;
    private JLabel LB_BandeiraP1, LB_NumP1, LB_NomeP1, LB_IdadeP1, LB_TempoContratoP1, LB_StatusP1, LB_Over_P1;
    private JLabel LB_BandeiraP2, LB_NumP2, LB_NomeP2, LB_IdadeP2, LB_TempoContratoP2, LB_StatusP2, LB_Over_P2;
    private JLabel LB_BandeiraP3, LB_NumP3, LB_NomeP3, LB_IdadeP3, LB_TempoContratoP3, LB_StatusP3, LB_Over_P3;
    private JLabel LB_BandeiraP4, LB_NumP4, LB_NomeP4, LB_IdadeP4, LB_TempoContratoP4, LB_StatusP4, LB_Over_P4;
    private JLabel LB_BandeiraP5, LB_NumP5, LB_NomeP5, LB_IdadeP5, LB_TempoContratoP5, LB_StatusP5, LB_Over_P5;

    private JLabel LB_PistaEtapas, LB_ImagemPista, LB_BandeiraPista, LB_LocalPista; 
    private JLabel LB_TipoPista, LB_NomeEtapa, LB_DesgastePneu, LB_ComprimentoPista, LB_VoltaPista;

    private JButton BT_EtapaAnt, BT_EtapaProxima, BT_DetalhesPista, BT_VoltarAtual, BT_IrParaCorrida;
    
    private JLabel LB_Classificacoes;
    private JTabbedPane tabbedPane;
    
    // --- CONTAINERS DAS LISTAS ---
    private JPanel pnlListaPilotosContainer; 
    private JPanel pnlListaConstrutoresContainer;
    private JPanel pnlListaResultadosContainer;
    private JComboBox<String> cbFiltroEtapaResultados;
    
    private JLabel LB_CarrosCat;

    // =========================================================================================
    // CONFIGURAÇÃO DE COLUNAS
    // =========================================================================================
    
    // PILOTOS
    private final int[]    W_PILOTOS = {30, 40, 30, 160, 150, 40, 40, 35, 35, 35}; 
    private final String[] H_PILOTOS = {"Pos", "País", "Nº", "Piloto", "Equipe", "Pts", "Dif", "Vit", "Pód", "Pol"};
    private final int[]    A_PILOTOS = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, 
                                        SwingConstants.LEFT, SwingConstants.LEFT, 
                                        SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};
    
    // EQUIPES
    private final int[]    W_EQUIPES = {40, 230, 60, 70, 70, 60, 60}; 
    private final String[] H_EQUIPES = {"Pos", "Equipe", "Pontos", "Diferença", "Vitórias", "Pódios", "Poles"};
    private final int[]    A_EQUIPES = {SwingConstants.CENTER, SwingConstants.LEFT, 
                                        SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};

    // RESULTADOS
    private final int[]    W_RESULTADOS = {30, 30, 35, 40, 150, 130, 105, 40, 40}; 
    private final String[] H_RESULTADOS = {"Pos", "Grid", "+/-", "País", "Piloto", "Equipe", "Tempo", "M.V", "Pts"};
    private final int[]    A_RESULTADOS = {SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER, 
                                           SwingConstants.LEFT, SwingConstants.LEFT, 
                                           SwingConstants.CENTER, SwingConstants.CENTER, SwingConstants.CENTER};

    /**
     * Launch for WindowBuilder (Testing)
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPrincipal frame = new TelaPrincipal(null);
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPrincipal(DadosDoJogo dados) {
        this.dadosDoJogo = dados;
        
        if (this.dadosDoJogo != null) {
            this.equipeJogavel = this.dadosDoJogo.getEquipeDoJogador();
            this.campeonato = this.dadosDoJogo.getCampeonato();
            
            if (this.campeonato != null) {
                this.indiceEtapaVisual = this.campeonato.getNumeroEtapaAtual() - 1; 
                if (this.indiceEtapaVisual < 0) this.indiceEtapaVisual = 0;
            }
        }

        setTitle("Grid Boss");
        configurarIconeJanela();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1235, 700); 
        setResizable(false);

        criarMenu();

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initComponents();
        
        if (equipeJogavel != null) {
            atualizarDados();
        }
    }
    
    private void criarMenu() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        setJMenuBar(menuBar);

        JMenu Menu_Geral = new JMenu("Geral");
        menuBar.add(Menu_Geral);
        
        JMenuItem mntmNovoJogo = new JMenuItem("Novo Jogo");
        mntmNovoJogo.addActionListener(e -> {
            TelaSelecionarCategoria nova = new TelaSelecionarCategoria();
            nova.setVisible(true);
            nova.setLocationRelativeTo(null);
            dispose();
        });
        Menu_Geral.add(mntmNovoJogo);
        
        JMenuItem menuSalvarRapido = new JMenuItem("Salvar");
        menuSalvarRapido.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
        menuSalvarRapido.addActionListener(e -> {
            if (dadosDoJogo.getArquivoAtual() != null) {
                boolean sucesso = dadosDoJogo.salvarJogo(dadosDoJogo.getArquivoAtual());
                if (sucesso) JOptionPane.showMessageDialog(this, "Progresso salvo em '" + dadosDoJogo.getArquivoAtual() + ".save'!");
                else JOptionPane.showMessageDialog(this, "Erro ao salvar!", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                TelaSalvarJogo tela = new TelaSalvarJogo(dadosDoJogo);
                tela.setLocationRelativeTo(this);
                tela.setVisible(true);
            }
        });
        Menu_Geral.add(menuSalvarRapido);
        
        JMenuItem menuSalvarComo = new JMenuItem("Salvar Como...");
        menuSalvarComo.addActionListener(e -> {
            TelaSalvarJogo tela = new TelaSalvarJogo(dadosDoJogo);
            tela.setLocationRelativeTo(this);
            tela.setVisible(true);
        });
        Menu_Geral.add(menuSalvarComo);
        
        JMenuItem menuCarregar = new JMenuItem("Carregar Jogo");
        menuCarregar.addActionListener(e -> {
            TelaCarregarJogo tela = new TelaCarregarJogo(this);
            tela.setLocationRelativeTo(this);
            tela.setVisible(true);
        });
        Menu_Geral.add(menuCarregar);

        JMenu Menu_Equipe = new JMenu("Equipe");
        menuBar.add(Menu_Equipe);
        
        JMenuItem mntmFabrica = new JMenuItem("Fábrica & Desenvolvimento");
        mntmFabrica.addActionListener(e -> {
            TelaFabrica tela = new TelaFabrica(dadosDoJogo);
            tela.setLocationRelativeTo(this); 
            tela.setVisible(true); 
            tela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    atualizarDados();
                }
            });
        });
        Menu_Equipe.add(mntmFabrica);
        
        JMenuItem mntmMotor = new JMenuItem("Motor");
        mntmMotor.addActionListener(e -> {
            TelaMotor tela = new TelaMotor(dadosDoJogo);
            tela.setVisible(true);
            tela.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    atualizarDados();
                }
            });
        });
        Menu_Equipe.add(mntmMotor);
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
        LB_LogoEquipe.setBounds(10, 11, 200, 85);
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
        LB_Motor.setBounds(220, 11, 185, 33);
        contentPane.add(LB_Motor);

        LB_BandeiraSede = new JLabel("");
        LB_BandeiraSede.setHorizontalAlignment(SwingConstants.CENTER);
        LB_BandeiraSede.setBounds(415, 55, 45, 33);
        contentPane.add(LB_BandeiraSede);

        LB_SedeEquipe = new JLabel("Sede");
        LB_SedeEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        LB_SedeEquipe.setBounds(220, 55, 183, 33);
        contentPane.add(LB_SedeEquipe);

        // === INFO JOGO ===
        LB_CategoriaEscolhida = new JLabel("");
        LB_CategoriaEscolhida.setHorizontalAlignment(SwingConstants.CENTER);
        LB_CategoriaEscolhida.setBounds(640, 0, 320, 100);
        contentPane.add(LB_CategoriaEscolhida);

        LB_NomeDirigente = new JLabel("Nome Dirigente");
        LB_NomeDirigente.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
        LB_NomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        LB_NomeDirigente.setBounds(970, 0, 239, 49);
        contentPane.add(LB_NomeDirigente);

        LB_Ano = new JLabel("Ano 2024");
        LB_Ano.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_Ano.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Ano.setBounds(958, 50, 100, 25);
        contentPane.add(LB_Ano);

        LB_Temporada = new JLabel("Temporada 1");
        LB_Temporada.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_Temporada.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Temporada.setBounds(958, 75, 250, 25);
        contentPane.add(LB_Temporada);

        LB_Orc = new JLabel("€ -- milhões");
        LB_Orc.setHorizontalAlignment(SwingConstants.CENTER);
        LB_Orc.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_Orc.setBounds(1062, 50, 100, 25);
        contentPane.add(LB_Orc);

        LB_IconeDinheiro = new JLabel("");
        LB_IconeDinheiro.setHorizontalAlignment(SwingConstants.CENTER);
        LB_IconeDinheiro.setBounds(1164, 50, 45, 25);
        carregarImagem(LB_IconeDinheiro, "/resource/Icone Euro.svg");
        contentPane.add(LB_IconeDinheiro);

        // === PILOTOS ===
        lblTituloPilotos = new JLabel("MEUS PILOTOS");
        lblTituloPilotos.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        lblTituloPilotos.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloPilotos.setBounds(12, 99, 618, 25);
        contentPane.add(lblTituloPilotos);

        // Labels dos Slots de Pilotos
        LB_BandeiraP1 = criarLabel(10, 135, 25, 25); LB_NumP1 = criarLabel(35, 135, 35, 25); LB_NomeP1 = criarLabel(70, 135, 205, 25); LB_IdadeP1 = criarLabel(275, 135, 50, 25); LB_Over_P1 = criarLabel(335, 135, 80, 25); LB_TempoContratoP1 = criarLabel(415, 135, 134, 25); LB_StatusP1 = criarLabel(550, 135, 80, 25);
        LB_BandeiraP2 = criarLabel(10, 160, 25, 25); LB_NumP2 = criarLabel(35, 160, 35, 25); LB_NomeP2 = criarLabel(70, 160, 205, 25); LB_IdadeP2 = criarLabel(275, 160, 50, 25); LB_Over_P2 = criarLabel(335, 160, 80, 25); LB_TempoContratoP2 = criarLabel(415, 160, 134, 25); LB_StatusP2 = criarLabel(550, 160, 80, 25);
        LB_BandeiraP3 = criarLabel(10, 185, 25, 25); LB_NumP3 = criarLabel(35, 185, 35, 25); LB_NomeP3 = criarLabel(70, 185, 205, 25); LB_IdadeP3 = criarLabel(275, 185, 50, 25); LB_Over_P3 = criarLabel(335, 185, 80, 25); LB_TempoContratoP3 = criarLabel(415, 185, 134, 25); LB_StatusP3 = criarLabel(550, 185, 80, 25);
        LB_BandeiraP4 = criarLabel(10, 210, 25, 25); LB_NumP4 = criarLabel(35, 210, 35, 25); LB_NomeP4 = criarLabel(70, 210, 205, 25); LB_IdadeP4 = criarLabel(275, 210, 50, 25); LB_Over_P4 = criarLabel(335, 210, 80, 25); LB_TempoContratoP4 = criarLabel(415, 210, 134, 25); LB_StatusP4 = criarLabel(550, 210, 80, 25);
        LB_BandeiraP5 = criarLabel(10, 235, 25, 25); LB_NumP5 = criarLabel(35, 235, 35, 25); LB_NomeP5 = criarLabel(70, 235, 205, 25); LB_IdadeP5 = criarLabel(275, 235, 50, 25); LB_Over_P5 = criarLabel(335, 235, 80, 25); LB_TempoContratoP5 = criarLabel(415, 235, 134, 25); LB_StatusP5 = criarLabel(550, 235, 80, 25);
        
        configurarFontesPilotos();

        // === CALENDÁRIO / PISTA ===
        LB_PistaEtapas = new JLabel("CALENDÁRIO, ETAPA 1/1");
        LB_PistaEtapas.setHorizontalAlignment(SwingConstants.CENTER);
        LB_PistaEtapas.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        LB_PistaEtapas.setBounds(640, 107, 569, 25);
        contentPane.add(LB_PistaEtapas);

        LB_ImagemPista = new JLabel("");
        LB_ImagemPista.setHorizontalAlignment(SwingConstants.CENTER);
        LB_ImagemPista.setBounds(640, 143, 250, 203);
        LB_ImagemPista.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        contentPane.add(LB_ImagemPista);

        LB_LocalPista = new JLabel("Local");
        LB_LocalPista.setForeground(new Color(0, 0, 0));
        LB_LocalPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_LocalPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_LocalPista.setBounds(900, 146, 262, 30);
        contentPane.add(LB_LocalPista);
        
        LB_BandeiraPista = new JLabel("");
        LB_BandeiraPista.setHorizontalAlignment(SwingConstants.CENTER);
        LB_BandeiraPista.setBounds(1164, 143, 45, 33);
        contentPane.add(LB_BandeiraPista);

        LB_TipoPista = new JLabel("Tipo Pista");
        LB_TipoPista.setForeground(new Color(0, 0, 0));
        LB_TipoPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_TipoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_TipoPista.setBounds(900, 173, 309, 30);
        contentPane.add(LB_TipoPista);

        LB_NomeEtapa = new JLabel("Nome Etapa");
        LB_NomeEtapa.setForeground(new Color(0, 0, 0));
        LB_NomeEtapa.setHorizontalAlignment(SwingConstants.LEFT);
        LB_NomeEtapa.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_NomeEtapa.setBounds(900, 203, 309, 30);
        contentPane.add(LB_NomeEtapa);

        LB_DesgastePneu = new JLabel("Desgaste Pneu");
        LB_DesgastePneu.setForeground(new Color(0, 0, 0));
        LB_DesgastePneu.setHorizontalAlignment(SwingConstants.LEFT);
        LB_DesgastePneu.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_DesgastePneu.setBounds(900, 233, 309, 30);
        contentPane.add(LB_DesgastePneu);

        LB_ComprimentoPista = new JLabel("Comprimento");
        LB_ComprimentoPista.setForeground(new Color(0, 0, 0));
        LB_ComprimentoPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_ComprimentoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_ComprimentoPista.setBounds(900, 263, 309, 30);
        contentPane.add(LB_ComprimentoPista);

        LB_VoltaPista = new JLabel("Voltas");
        LB_VoltaPista.setForeground(new Color(0, 0, 0));
        LB_VoltaPista.setHorizontalAlignment(SwingConstants.LEFT);
        LB_VoltaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
        LB_VoltaPista.setBounds(900, 293, 309, 30);
        contentPane.add(LB_VoltaPista);

        // === BOTÕES DE NAVEGAÇÃO ===
        BT_EtapaAnt = new JButton("Etapa Anterior");
        BT_EtapaAnt.setBounds(643, 364, 275, 23);
        BT_EtapaAnt.addActionListener(e -> navegarCalendarioVisual(-1));
        contentPane.add(BT_EtapaAnt);

        BT_EtapaProxima = new JButton("Próxima Etapa");
        BT_EtapaProxima.setBounds(934, 364, 275, 23);
        BT_EtapaProxima.addActionListener(e -> navegarCalendarioVisual(1));
        contentPane.add(BT_EtapaProxima);

        BT_DetalhesPista = new JButton("Ver Traçado Detalhado");
        BT_DetalhesPista.setEnabled(false);
        BT_DetalhesPista.setBounds(643, 398, 275, 23);
        contentPane.add(BT_DetalhesPista);

        BT_VoltarAtual = new JButton("Voltar Etapa Atual");
        BT_VoltarAtual.setBounds(934, 398, 275, 23);
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
        tabbedPane.setBounds(10, 307, 620, 332);
        contentPane.add(tabbedPane);
        
        // ==============================================================
        // CRIAÇÃO EXPLÍCITA DAS ABAS (PARA CORRIGIR ERRO NO WINDOW BUILDER)
        // ==============================================================
        
        // --- 1. Aba Pilotos ---
        JPanel pnlAbaPilotos = new JPanel(new BorderLayout());
        pnlAbaPilotos.setBackground(Color.WHITE);
        
        // Header
        JPanel pnlHeaderPilotos = new JPanel(null);
        int wTotalP = 0; for(int w : W_PILOTOS) wTotalP += w;
        pnlHeaderPilotos.setPreferredSize(new Dimension(wTotalP, 30));
        pnlHeaderPilotos.setBackground(new Color(230, 230, 230)); 
        pnlHeaderPilotos.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        
        int xP = 0;
        for (int i = 0; i < H_PILOTOS.length; i++) {
            JLabel lb = new JLabel(H_PILOTOS[i]);
            lb.setBounds(xP, 0, W_PILOTOS[i], 30);
            int align = (i < A_PILOTOS.length) ? A_PILOTOS[i] : SwingConstants.CENTER;
            lb.setHorizontalAlignment(align);
            lb.setFont(new Font("Segoe UI", Font.BOLD, 12));
            pnlHeaderPilotos.add(lb);
            xP += W_PILOTOS[i];
        }
        pnlAbaPilotos.add(pnlHeaderPilotos, BorderLayout.NORTH);
        
        // Lista
        JPanel pnlContainerPilotos = new JPanel();
        pnlContainerPilotos.setLayout(new BoxLayout(pnlContainerPilotos, BoxLayout.Y_AXIS));
        pnlContainerPilotos.setBackground(Color.WHITE);
        JScrollPane scrollPilotos = new JScrollPane(pnlContainerPilotos);
        scrollPilotos.getVerticalScrollBar().setUnitIncrement(16);
        scrollPilotos.setBorder(null);
        pnlAbaPilotos.add(scrollPilotos, BorderLayout.CENTER);
        
        pnlListaPilotosContainer = pnlContainerPilotos;
        tabbedPane.addTab("Classificação Pilotos", new FlatSVGIcon("resource/Icone24pxPiloto.svg"), pnlAbaPilotos);
        
        // --- 2. Aba Equipes ---
        JPanel pnlAbaEquipes = new JPanel(new BorderLayout());
        pnlAbaEquipes.setBackground(Color.WHITE);
        
        // Header
        JPanel pnlHeaderEquipes = new JPanel(null);
        int wTotalE = 0; for(int w : W_EQUIPES) wTotalE += w;
        pnlHeaderEquipes.setPreferredSize(new Dimension(wTotalE, 30));
        pnlHeaderEquipes.setBackground(new Color(230, 230, 230)); 
        pnlHeaderEquipes.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        
        int xE = 0;
        for (int i = 0; i < H_EQUIPES.length; i++) {
            JLabel lb = new JLabel(H_EQUIPES[i]);
            lb.setBounds(xE, 0, W_EQUIPES[i], 30);
            int align = (i < A_EQUIPES.length) ? A_EQUIPES[i] : SwingConstants.CENTER;
            lb.setHorizontalAlignment(align);
            lb.setFont(new Font("Segoe UI", Font.BOLD, 12));
            pnlHeaderEquipes.add(lb);
            xE += W_EQUIPES[i];
        }
        pnlAbaEquipes.add(pnlHeaderEquipes, BorderLayout.NORTH);
        
        // Lista
        JPanel pnlContainerEquipes = new JPanel();
        pnlContainerEquipes.setLayout(new BoxLayout(pnlContainerEquipes, BoxLayout.Y_AXIS));
        pnlContainerEquipes.setBackground(Color.WHITE);
        JScrollPane scrollEquipes = new JScrollPane(pnlContainerEquipes);
        scrollEquipes.getVerticalScrollBar().setUnitIncrement(16);
        scrollEquipes.setBorder(null);
        pnlAbaEquipes.add(scrollEquipes, BorderLayout.CENTER);
        
        pnlListaConstrutoresContainer = pnlContainerEquipes;
        tabbedPane.addTab("Classificação Equipes", new FlatSVGIcon("resource/Icone24pxEquipe.svg"), pnlAbaEquipes);
        
        // --- 3. Aba Resultados ---
        JPanel pnlAbaResultados = new JPanel(new BorderLayout());
        pnlAbaResultados.setBackground(Color.WHITE);
        
        // Topo: Filtro
        JPanel pnlTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlTopo.setBackground(Color.WHITE);
        cbFiltroEtapaResultados = new JComboBox<>();
        cbFiltroEtapaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        cbFiltroEtapaResultados.addActionListener(e -> atualizarListaResultados(cbFiltroEtapaResultados.getSelectedIndex()));
        pnlTopo.add(new JLabel("Selecionar Etapa:"));
        pnlTopo.add(cbFiltroEtapaResultados);
        pnlAbaResultados.add(pnlTopo, BorderLayout.NORTH);
        
        // Centro: Header + Tabela
        JPanel pnlCentroResultados = new JPanel(new BorderLayout());
        
        // Header
        JPanel pnlHeaderRes = new JPanel(null);
        int wTotalR = 0; for(int w : W_RESULTADOS) wTotalR += w;
        pnlHeaderRes.setPreferredSize(new Dimension(wTotalR, 30));
        pnlHeaderRes.setBackground(new Color(230, 230, 230)); 
        pnlHeaderRes.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
        
        int xR = 0;
        for (int i = 0; i < H_RESULTADOS.length; i++) {
            JLabel lb = new JLabel(H_RESULTADOS[i]);
            lb.setBounds(xR, 0, W_RESULTADOS[i], 30);
            int align = (i < A_RESULTADOS.length) ? A_RESULTADOS[i] : SwingConstants.CENTER;
            lb.setHorizontalAlignment(align);
            lb.setFont(new Font("Segoe UI", Font.BOLD, 12));
            pnlHeaderRes.add(lb);
            xR += W_RESULTADOS[i];
        }
        pnlCentroResultados.add(pnlHeaderRes, BorderLayout.NORTH);
        
        // Lista
        JPanel pnlContainerRes = new JPanel();
        pnlContainerRes.setLayout(new BoxLayout(pnlContainerRes, BoxLayout.Y_AXIS));
        pnlContainerRes.setBackground(Color.WHITE);
        JScrollPane scrollRes = new JScrollPane(pnlContainerRes);
        scrollRes.getVerticalScrollBar().setUnitIncrement(16);
        scrollRes.setBorder(null);
        pnlCentroResultados.add(scrollRes, BorderLayout.CENTER);
        
        pnlAbaResultados.add(pnlCentroResultados, BorderLayout.CENTER);
        
        pnlListaResultadosContainer = pnlContainerRes;
        tabbedPane.addTab("Resultados", new FlatSVGIcon("resource/Icone24pxTrofeu.svg"), pnlAbaResultados);

        // === RODAPÉ ===
        LB_CarrosCat = new JLabel("");
        LB_CarrosCat.setVerticalAlignment(SwingConstants.BOTTOM);
        carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.svg");
        LB_CarrosCat.setHorizontalAlignment(SwingConstants.CENTER);
        LB_CarrosCat.setBounds(642, 432, 567, 163);
        contentPane.add(LB_CarrosCat);

        BT_IrParaCorrida = new JButton("IR PARA A CORRIDA!");
        BT_IrParaCorrida.setFont(new Font("Arial", Font.BOLD, 16));
        BT_IrParaCorrida.setIcon(new FlatSVGIcon("resource/Icone24pxBandeiraDeChegada.svg"));
        BT_IrParaCorrida.setBounds(643, 606, 566, 33);
        BT_IrParaCorrida.addActionListener(e -> irParaCorrida());
        contentPane.add(BT_IrParaCorrida);
    }
    
    // --- LINHA PADRÃO ---
    private void adicionarLinhaGrid(JPanel container, int pos, String pathBandeira, String[] dados, int[] larguras, int[] aligns, boolean[] bolds) {
        int larguraTotal = 0;
        for(int w : larguras) larguraTotal += w;
        
        JPanel row = new JPanel(null); 
        row.setPreferredSize(new Dimension(larguraTotal, 35));
        row.setMaximumSize(new Dimension(larguraTotal, 35)); 
        row.setMinimumSize(new Dimension(larguraTotal, 35));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (pos % 2 == 0) row.setBackground(new Color(245, 245, 245));
        else row.setBackground(Color.WHITE);
        
        int xAtual = 0;
        
        // 1. Pos
        adicionarLabel(row, dados[0], xAtual, larguras[0], aligns[0], bolds[0]);
        xAtual += larguras[0];
        
        // 2. Bandeira (se houver)
        if (pathBandeira != null) {
            JLabel lblFlag = new JLabel();
            int slotW = larguras[1];
            int iconW = 28; 
            int offset = (slotW - iconW) / 2; 
            lblFlag.setBounds(xAtual + offset, 7, iconW, 20); 
            carregarImagem(lblFlag, pathBandeira);
            row.add(lblFlag);
            xAtual += larguras[1];
        } 
        
        // 3. Demais
        for (int i = 1; i < dados.length; i++) {
            int offsetIdx = (pathBandeira != null) ? 1 : 0;
            if (i + offsetIdx >= larguras.length) break;
            
            int larguraCol = larguras[i + offsetIdx];
            int alinhamento = aligns[i + offsetIdx];
            
            adicionarLabel(row, dados[i], xAtual, larguraCol, alinhamento, bolds[i]);
            xAtual += larguraCol;
        }
        
        container.add(row);
    }
    
    // --- LINHA RESULTADO ---
    private void adicionarLinhaResultado(JPanel container, int pos, int largada, String pathBandeira, String nomePiloto, String nomeEquipe, String tempo, boolean melhorVolta, String pontos) {
        int larguraTotal = 0;
        for(int w : W_RESULTADOS) larguraTotal += w;
        
        JPanel row = new JPanel(null); 
        row.setPreferredSize(new Dimension(larguraTotal, 35));
        row.setMaximumSize(new Dimension(larguraTotal, 35)); 
        row.setMinimumSize(new Dimension(larguraTotal, 35));
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        if (pos % 2 == 0) row.setBackground(new Color(245, 245, 245));
        else row.setBackground(Color.WHITE);
        
        int x = 0;
        int i = 0; 
        
        // 1. Pos
        adicionarLabel(row, String.valueOf(pos), x, W_RESULTADOS[i], SwingConstants.CENTER, true);
        x += W_RESULTADOS[i++];
        
        // 2. Grid
        adicionarLabel(row, String.valueOf(largada), x, W_RESULTADOS[i], SwingConstants.CENTER, false);
        x += W_RESULTADOS[i++];
        
        // 3. Variação
        int diff = largada - pos; 
        JLabel lblVar = new JLabel();
        lblVar.setBounds(x, 0, W_RESULTADOS[i], 35);
        lblVar.setHorizontalAlignment(SwingConstants.CENTER);
        lblVar.setFont(new Font("Segoe UI Symbol", Font.BOLD, 12)); 
        if (diff > 0) {
            lblVar.setText("▲ " + Math.abs(diff));
            lblVar.setForeground(new Color(0, 180, 0)); 
        } else if (diff < 0) {
            lblVar.setText("▼ " + Math.abs(diff));
            lblVar.setForeground(Color.RED);
        } else {
            lblVar.setText("-");
            lblVar.setForeground(Color.GRAY);
        }
        row.add(lblVar);
        x += W_RESULTADOS[i++];
        
        // 4. Bandeira
        JLabel lblFlag = new JLabel();
        int flagW = W_RESULTADOS[i];
        lblFlag.setBounds(x + (flagW-28)/2, 7, 28, 20);
        carregarImagem(lblFlag, pathBandeira);
        row.add(lblFlag);
        x += W_RESULTADOS[i++];
        
        // 5. Piloto
        adicionarLabel(row, nomePiloto, x, W_RESULTADOS[i], SwingConstants.LEFT, true);
        x += W_RESULTADOS[i++];
        
        // 6. Equipe
        adicionarLabel(row, nomeEquipe, x, W_RESULTADOS[i], SwingConstants.LEFT, false);
        x += W_RESULTADOS[i++];
        
        // 7. Tempo
        JLabel lblTempo = new JLabel(tempo);
        lblTempo.setBounds(x, 0, W_RESULTADOS[i], 35);
        lblTempo.setHorizontalAlignment(SwingConstants.CENTER);
        if (tempo.contains("DNF") || tempo.contains("Batida")) lblTempo.setForeground(Color.RED);
        else lblTempo.setForeground(Color.BLACK);
        lblTempo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        row.add(lblTempo);
        x += W_RESULTADOS[i++];
        
        // 8. MV
        JLabel lblMV = new JLabel(melhorVolta ? "★" : ""); 
        lblMV.setBounds(x, 0, W_RESULTADOS[i], 35);
        lblMV.setHorizontalAlignment(SwingConstants.CENTER);
        lblMV.setForeground(new Color(128, 0, 128)); 
        lblMV.setFont(new Font("Segoe UI Symbol", Font.BOLD, 14));
        row.add(lblMV);
        x += W_RESULTADOS[i++];
        
        // 9. Pts
        if (!pontos.equals("0")) pontos = "+" + pontos;
        adicionarLabel(row, pontos, x, W_RESULTADOS[i], SwingConstants.CENTER, true);
        
        container.add(row);
    }
    
    private void adicionarLabel(JPanel pnl, String txt, int x, int w, int align, boolean bold) {
        JLabel lb = new JLabel(txt);
        lb.setBounds(x, 0, w, 35);
        lb.setHorizontalAlignment(align);
        lb.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, 12));
        pnl.add(lb);
    }
    
    private JLabel criarLabel(int x, int y, int w, int h) {
        JLabel lb = new JLabel("");
        lb.setBounds(x, y, w, h);
        contentPane.add(lb);
        return lb;
    }
    
    private void configurarFontesPilotos() {
        Font fontNome = new Font("Arial Rounded MT Bold", Font.ITALIC, 14);
        LB_NomeP1.setFont(fontNome); LB_NomeP2.setFont(fontNome); LB_NomeP3.setFont(fontNome); LB_NomeP4.setFont(fontNome); LB_NomeP5.setFont(fontNome);
    }
 
    private void atualizarDados() {
        if (equipeJogavel == null) return;

        LB_SedeEquipe.setText(equipeJogavel.getSede());
        LB_Motor.setText("Motor " + equipeJogavel.getMotor());
        LB_Orc.setText(String.format("€ %.2f milhões", equipeJogavel.getSaldoFinanceiro()));
        LB_NomeDirigente.setText(dadosDoJogo.getNomeDoDirigente());
        LB_Ano.setText("Ano " + dadosDoJogo.getAnoAtual()); 
        
        carregarImagem(LB_LogoEquipe, equipeJogavel.getCaminhoLogo());
        carregarImagem(LB_BandeiraMotor, equipeJogavel.getCaminhoBandeiraMotor());
        carregarImagem(LB_LogoMotorPQ, equipeJogavel.getCaminhoLogoMotor());
        carregarImagem(LB_BandeiraSede, equipeJogavel.getCaminhoBandeiraSede());

        String catKey = (dadosDoJogo.getCategoriaKey() != null) ? dadosDoJogo.getCategoriaKey().toLowerCase() : "";
        String logoPath = "/resource/Logo F1 Novo.svg"; 
        if (catKey.contains("indy")) logoPath = "/resource/Logo Indy.svg";
        else if (catKey.contains("nascar")) logoPath = "/resource/Logo Nascar.svg";
        carregarImagem(LB_CategoriaEscolhida, logoPath);

        if (catKey.contains("f1")) {
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.svg");
            LB_TipoPista.setForeground(Color.BLACK);
        } else if (catKey.contains("indy")) {
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.svg"); 
            LB_TipoPista.setForeground(Color.BLUE);
        } else if (catKey.contains("nascar")) {
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.svg"); 
            LB_TipoPista.setForeground(Color.BLUE);
        } else {
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.svg");
            LB_TipoPista.setForeground(Color.BLACK);
        }

        List<Piloto> pilotosEquipe = equipeJogavel.getPilotosTitulares();
        pilotosEquipe.addAll(equipeJogavel.getPilotosReservas());

        atualizarSlotPiloto(0, pilotosEquipe, LB_NomeP1, LB_NumP1, LB_IdadeP1, LB_TempoContratoP1, LB_BandeiraP1, LB_StatusP1, LB_Over_P1);
        atualizarSlotPiloto(1, pilotosEquipe, LB_NomeP2, LB_NumP2, LB_IdadeP2, LB_TempoContratoP2, LB_BandeiraP2, LB_StatusP2, LB_Over_P2);
        atualizarSlotPiloto(2, pilotosEquipe, LB_NomeP3, LB_NumP3, LB_IdadeP3, LB_TempoContratoP3, LB_BandeiraP3, LB_StatusP3, LB_Over_P3);
        atualizarSlotPiloto(3, pilotosEquipe, LB_NomeP4, LB_NumP4, LB_IdadeP4, LB_TempoContratoP4, LB_BandeiraP4, LB_StatusP4, LB_Over_P4);
        atualizarSlotPiloto(4, pilotosEquipe, LB_NomeP5, LB_NumP5, LB_IdadeP5, LB_TempoContratoP5, LB_BandeiraP5, LB_StatusP5, LB_Over_P5);

        atualizarCalendarioUI();
        preencherTabelas(); 
        
        cbFiltroEtapaResultados.removeAllItems();
        if (campeonato.getCalendario() != null) {
            for (int i = 0; i < campeonato.getCalendario().size(); i++) {
                cbFiltroEtapaResultados.addItem("Etapa " + (i+1) + " - " + campeonato.getCalendario().get(i).getNome());
            }
        }
        int etapaParaMostrar = Math.max(0, campeonato.getNumeroEtapaAtual() - 1);
        if (cbFiltroEtapaResultados.getItemCount() > etapaParaMostrar) {
            cbFiltroEtapaResultados.setSelectedIndex(etapaParaMostrar);
        }
        atualizarListaResultados(etapaParaMostrar);
    }
    
    private void atualizarListaResultados(int indiceEtapa) {
        pnlListaResultadosContainer.removeAll();
        
        List<Equipe> equipes = dadosDoJogo.getTodasAsEquipes();
        List<Piloto> pilotos = new ArrayList<>();
        boolean isF1 = SessaoJogo.categoriaKey.toLowerCase().contains("f1");
        
        for(Equipe eq : equipes) {
            List<Piloto> tits = eq.getPilotosTitulares();
            for(int i=0; i<tits.size(); i++) {
                if(isF1 && i>=2) continue;
                tits.get(i).setNomeEquipeAtual(eq.getNome());
                pilotos.add(tits.get(i));
            }
        }
        Collections.shuffle(pilotos, new Random(indiceEtapa * 1234L)); 
        
        for (int i = 0; i < pilotos.size(); i++) {
            Piloto p = pilotos.get(i);
            int posChegada = i + 1;
            int posLargada = (i + 3) % pilotos.size() + 1; 
            
            String tempo = (i == 0) ? "1:35:20.102" : "+" + (i * 2.5) + "s";
            if (i > 18) tempo = "DNF (Motor)";
            
            String pts = (i < 10) ? String.valueOf(25 - (i*2)) : "0";
            boolean mv = (i == 2); 
            
            adicionarLinhaResultado(
                pnlListaResultadosContainer, 
                posChegada, 
                posLargada, 
                "/resource/Bandeira " + p.getNacionalidade() + ".svg", 
                p.getNome(), 
                p.getNomeEquipeAtual(), 
                tempo, 
                mv, 
                pts
            );
        }
        pnlListaResultadosContainer.revalidate();
        pnlListaResultadosContainer.repaint();
    }
    
    private void atualizarSlotPiloto(int index, List<Piloto> lista, JLabel lbNome, JLabel lbNum, JLabel lbIdade, JLabel lbContrato, JLabel lbFlag, JLabel lbStatus, JLabel lbOver) {
        if (index < lista.size()) {
            Piloto p = lista.get(index);
            lbNome.setText(p.getNome());
            lbNum.setText("#" + p.getNumero());
            lbIdade.setText(p.getIdade() + " anos");
            lbContrato.setText("Contrato Ativo");
            lbOver.setText("Ovr " + (int)p.getOverall());
            
            carregarImagem(lbFlag, "/resource/Bandeira " + p.getNacionalidade() + ".svg");
            
            lbNome.setVisible(true); lbNum.setVisible(true); lbIdade.setVisible(true);
            lbContrato.setVisible(true); lbFlag.setVisible(true); lbStatus.setVisible(true); lbOver.setVisible(true);

            boolean isF1 = SessaoJogo.categoriaKey.toLowerCase().contains("f1");
            boolean isReserva = isF1 && index >= 2;

            if (isReserva) {
                Color corReserva = Color.GRAY;
                lbNome.setForeground(corReserva); lbNum.setForeground(corReserva);
                lbIdade.setForeground(corReserva); lbContrato.setForeground(corReserva);
                lbStatus.setForeground(corReserva); lbOver.setForeground(corReserva);
                lbStatus.setText("Reserva " + (index - 1));
            } else {
                Color corTitular = Color.BLACK;
                lbNome.setForeground(corTitular); lbNum.setForeground(corTitular);
                lbIdade.setForeground(corTitular); lbContrato.setForeground(corTitular);
                lbStatus.setForeground(corTitular); lbOver.setForeground(corTitular);
                lbStatus.setText("Piloto " + (index + 1));
            }
        } else {
            lbNome.setVisible(false); lbNum.setVisible(false); lbIdade.setVisible(false);
            lbContrato.setVisible(false); lbFlag.setVisible(false); lbStatus.setVisible(false); lbOver.setVisible(false);
        }
    }

    private void preencherTabelas() {
        pnlListaPilotosContainer.removeAll();
        
        List<Equipe> todasEquipes = dadosDoJogo.getTodasAsEquipes();
        java.util.List<Piloto> pilotosGrid = new java.util.ArrayList<>();
        boolean isF1 = SessaoJogo.categoriaKey.toLowerCase().contains("f1");

        for (Equipe eq : todasEquipes) {
            List<Piloto> titulares = eq.getPilotosTitulares();
            for (int i = 0; i < titulares.size(); i++) {
                if (isF1 && i >= 2) continue; 
                Piloto p = titulares.get(i);
                p.setNomeEquipeAtual(eq.getNome()); 
                pilotosGrid.add(p);
            }
        }
        
        Collections.sort(pilotosGrid, new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                if (p1.getPontos() != p2.getPontos()) return Integer.compare(p2.getPontos(), p1.getPontos());
                if (p1.getVitorias() != p2.getVitorias()) return Integer.compare(p2.getVitorias(), p1.getVitorias());
                if (p1.getPodios() != p2.getPodios()) return Integer.compare(p2.getPodios(), p1.getPodios());
                return p1.getNome().compareToIgnoreCase(p2.getNome());
            }
        });

        int posP = 1;
        int pontosLiderP = (pilotosGrid.isEmpty()) ? 0 : pilotosGrid.get(0).getPontos();

        for (Piloto p : pilotosGrid) {
            int diff = pontosLiderP - p.getPontos();
            String[] dadosLinha = {
                String.valueOf(posP),
                String.valueOf(p.getNumero()),
                p.getNome(),
                p.getNomeEquipeAtual(),
                String.valueOf(p.getPontos()),
                String.valueOf(diff),
                String.valueOf(p.getVitorias()),
                String.valueOf(p.getPodios()),
                String.valueOf(p.getPoles())
            };
            boolean[] bolds = {true, false, true, false, true, false, false, false, false};
            
            String pathBandeira = "/resource/Bandeira " + p.getNacionalidade() + ".svg";
            adicionarLinhaGrid(pnlListaPilotosContainer, posP, pathBandeira, dadosLinha, W_PILOTOS, A_PILOTOS, bolds);
            posP++;
        }
        
        pnlListaPilotosContainer.revalidate();
        pnlListaPilotosContainer.repaint();

        pnlListaConstrutoresContainer.removeAll();

        Collections.sort(todasEquipes, new Comparator<Equipe>() {
            @Override
            public int compare(Equipe e1, Equipe e2) {
                if (e1.getPontos() != e2.getPontos()) return Integer.compare(e2.getPontos(), e1.getPontos());
                if (e1.getVitorias() != e2.getVitorias()) return Integer.compare(e2.getVitorias(), e1.getVitorias());
                if (e1.getPodios() != e2.getPodios()) return Integer.compare(e2.getPodios(), e1.getPodios());
                return e1.getNome().compareToIgnoreCase(e2.getNome());
            }
        });

        int posE = 1;
        int pontosLiderE = (todasEquipes.isEmpty()) ? 0 : todasEquipes.get(0).getPontos();

        for (Equipe eq : todasEquipes) {
            int diff = pontosLiderE - eq.getPontos();
            String[] dadosLinha = {
                String.valueOf(posE),
                eq.getNome(),
                String.valueOf(eq.getPontos()),
                String.valueOf(diff),
                String.valueOf(eq.getVitorias()),
                String.valueOf(eq.getPodios()),
                String.valueOf(eq.getPoles())
            };
            boolean[] bolds = {true, true, true, false, false, false, false};
            
            adicionarLinhaGrid(pnlListaConstrutoresContainer, posE, null, dadosLinha, W_EQUIPES, A_EQUIPES, bolds);
            posE++;
        }
        
        pnlListaConstrutoresContainer.revalidate();
        pnlListaConstrutoresContainer.repaint();
    }
    
    private void atualizarCalendarioUI() {
        Pista pistaVisual = buscarPistaPorIndice(indiceEtapaVisual);
        int etapaReal = campeonato.getNumeroEtapaAtual();
        int total = campeonato.getTotalEtapas();
        int etapaVisualNumero = indiceEtapaVisual + 1;
        
        LB_PistaEtapas.setText("CALENDÁRIO, ETAPA " + etapaVisualNumero + "/" + total);
        LB_Temporada.setText("Temporada 1, Etapa Atual: " + etapaReal + "/" + total);

        if (pistaVisual != null) {
            boolean isIndyOrNascar = SessaoJogo.categoriaKey.contains("indy") || SessaoJogo.categoriaKey.contains("nascar");
            
            if (isIndyOrNascar) {
                LB_LocalPista.setText(pistaVisual.getNome());
            } else {
                LB_LocalPista.setText(pistaVisual.getPais());
            }
            
            carregarImagem(LB_BandeiraPista, "/resource/Bandeira " + pistaVisual.getPais() + ".svg");

            String tipoTexto = "Misto";
            if (pistaVisual.getTipo() != null) {
                switch (pistaVisual.getTipo()) {
                    case MISTO_PERMANENTE: tipoTexto = "Autódromo Permanente"; break;
                    case RUA_TEMPORARIO:   tipoTexto = "Circuito de Rua"; break;
                    case ROVAL:            tipoTexto = "Roval"; break;
                    case OVAL_CURTO:       tipoTexto = "Short Track"; break;
                    case OVAL_SPEEDWAY:    tipoTexto = "Intermediate Track"; break;
                    case SUPERSPEEDWAY:    tipoTexto = "Superspeedway"; break;
                    default:               tipoTexto = pistaVisual.getTipo().toString();
                }
            }
            LB_TipoPista.setText(tipoTexto);

            if (isIndyOrNascar) {
                LB_NomeEtapa.setText(pistaVisual.getEtapa());
            } else {
                LB_NomeEtapa.setText(pistaVisual.getNome());
            }

            LB_VoltaPista.setText(pistaVisual.getQtdVoltas() + " voltas");
            String unidade = isIndyOrNascar ? "milhas" : "km";
            LB_ComprimentoPista.setText(pistaVisual.getComprimentoKm() + " " + unidade);

            double desgaste = pistaVisual.getFatorDesgastePneu();
            String desgasteTexto = (desgaste < 1.0) ? "Baixo" : (desgaste <= 1.5) ? "Médio" : "Alto";
            LB_DesgastePneu.setText("Desgaste Pneu: " + desgasteTexto);

            String caminhoImagem = "/resource/Icone64pxErro.svg";
            String nomeOriginal = pistaVisual.getCaminhoTracado();
            if (nomeOriginal != null) {
                caminhoImagem = nomeOriginal.replace(".png", "PQ.svg");
            }
            carregarImagem(LB_ImagemPista, caminhoImagem);
        }

        BT_EtapaAnt.setEnabled(indiceEtapaVisual > 0);
        BT_EtapaProxima.setEnabled(indiceEtapaVisual < total - 1);
    }
    
    private Pista buscarPistaPorIndice(int index) {
        try {
            List<Pista> todas = campeonato.getCalendario(); 
            if (todas != null && index >= 0 && index < todas.size()) {
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
            if (path == null || path.isEmpty()) {
                lbl.setIcon(null);
                return;
            }

            // Normaliza o caminho
            if (!path.startsWith("/")) path = "/" + path;
            if (!path.startsWith("/resource")) path = "/resource" + path;
            path = path.replace("//", "/");

            // Força extensão .svg
            if (path.toLowerCase().endsWith(".png")) {
                path = path.substring(0, path.length() - 4) + ".svg";
            }
            if (!path.toLowerCase().endsWith(".svg")) {
                path = path + ".svg";
            }

            String svgPath = path.startsWith("/") ? path.substring(1) : path;

            // Pega tamanho do componente (ou usa 0 se ainda não desenhado)
            int labelW = lbl.getWidth();
            int labelH = lbl.getHeight();

            // Proteção para Window Builder ou labels não renderizados
            if (labelW == 0 || labelH == 0) {
                // Define um tamanho padrão apenas para evitar erro visual no editor
                labelW = 100;
                labelH = 100;
            }

            // Carrega o ícone original para pegar as dimensões reais do SVG
            FlatSVGIcon iconOriginal = new FlatSVGIcon(svgPath);
            
            if (iconOriginal.getIconWidth() <= 0) {
                lbl.setIcon(null);
                return;
            }

            float origW = iconOriginal.getIconWidth();
            float origH = iconOriginal.getIconHeight();

            // --- CÁLCULO DA MARGEM (Respiro) ---
            // Define a margem ANTES de calcular a escala para não distorcer
            int margem = 2; // Padrão
            
            if (lbl == LB_BandeiraMotor || lbl == LB_BandeiraSede || lbl == LB_BandeiraPista) {
                margem = 8; 
            } else if (lbl == LB_CategoriaEscolhida || lbl == LB_LogoEquipe) { 
                margem = 20; // 10px de cada lado (20px total)
            } else if (labelW <= 40) { 
                margem = 4; // Tabelas pequenas
            }

            // Calcula o espaço útil disponível
            float spaceW = Math.max(1, labelW - margem);
            float spaceH = Math.max(1, labelH - margem);

            // Calcula a escala baseada no espaço útil (Scale to Fit)
            float ratioW = spaceW / origW;
            float ratioH = spaceH / origH;
            float scale = Math.min(ratioW, ratioH);

            // Aplica a escala para obter o tamanho final proporcional
            int finalW = Math.round(origW * scale);
            int finalH = Math.round(origH * scale);

            // Define o ícone redimensionado
            lbl.setIcon(new FlatSVGIcon(svgPath, finalW, finalH));

        } catch (Exception e) {
            // Em caso de erro (arquivo não encontrado), limpa o ícone ou põe um padrão
            lbl.setIcon(null); 
            // e.printStackTrace(); // Opcional para debug
        }
    }
    
    private void configurarIconeJanela() {
        try {
            String path = "/resource/Icone.svg";
            java.net.URL url = getClass().getResource(path);
            
            if (url == null) {
                System.err.println("ERRO: O Java não encontrou o arquivo: " + path);
                return; 
            }

            java.awt.Image icon = FlatSVGUtils.svg2image(path, 32, 32);
            if (icon != null) {
                setIconImage(icon);
            }
            
        } catch (Exception e) {
            System.err.println("Falha ao definir ícone da janela: " + e.getMessage());
        }
    }

    private void irParaCorrida() {
        JOptionPane.showMessageDialog(this, "Carregando fim de semana de corrida...");
    }

    private void carregarJogoAction() {
        File diretorioSaves = new File("saves");
        if (!diretorioSaves.exists()) {
            diretorioSaves.mkdir();
        }

        JFileChooser fileChooser = new JFileChooser(diretorioSaves);
        fileChooser.setDialogTitle("Carregar Jogo Salvo");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Save Game (.save)", "save"));
        
        int retorno = fileChooser.showOpenDialog(this);
        
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = fileChooser.getSelectedFile();
            
            DadosDoJogo jogoCarregado = DadosDoJogo.carregarJogo(arquivoSelecionado.getName());
            
            if (jogoCarregado != null) {
                TelaPrincipal novaTela = new TelaPrincipal(jogoCarregado);
                novaTela.setVisible(true);
                novaTela.setLocationRelativeTo(null);
                
                this.dispose(); 
                
                JOptionPane.showMessageDialog(novaTela, "Jogo carregado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Erro ao carregar o save.\nArquivo corrompido ou versão incompatível.", 
                    "Erro", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
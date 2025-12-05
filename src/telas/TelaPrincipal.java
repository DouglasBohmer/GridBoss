package telas;

import dados.CarregadorJSON;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import servicos.CampeonatoService;
import modelos.TipoPista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.text.Normalizer;

public class TelaPrincipal extends JFrame {

    private JPanel contentPane;

    // --- DADOS DO JOGO ---
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
    
    // Tabelas
    private JTable tabelaPilotos;
    private JTable tabelaConstrutores;
    
    private JLabel LB_CarrosCat;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                TelaPrincipal frame = new TelaPrincipal(new Equipe("Teste", 100, 50));
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaPrincipal(Equipe equipe) {
        this.equipeJogavel = equipe;
        this.campeonato = new CampeonatoService(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
        
        this.indiceEtapaVisual = this.campeonato.getNumeroEtapaAtual() - 1; 
        if (this.indiceEtapaVisual < 0) this.indiceEtapaVisual = 0;

        setTitle("Grid Boss");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaPrincipal.class.getResource("/resource/Icone16px.png")));
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
        carregarImagem(LB_IconeDinheiro, "/resource/IconeEuro24px.png");
        contentPane.add(LB_IconeDinheiro);

        // === PILOTOS ===
        lblTituloPilotos = new JLabel("MEUS PILOTOS");
        lblTituloPilotos.setFont(new Font("Arial Narrow", Font.BOLD, 14));
        lblTituloPilotos.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloPilotos.setBounds(12, 99, 618, 25);
        contentPane.add(lblTituloPilotos);

        // P1 a P5
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
        
        // --- TABELA DE PILOTOS ---
        tabelaPilotos = new JTable();
        tabelaPilotos.setRowHeight(35);
        JScrollPane scrollPilotos = new JScrollPane(tabelaPilotos);
        tabbedPane.addTab("Classificação Pilotos", new ImageIcon(getClass().getResource("/resource/Icone24pxPiloto.png")), scrollPilotos);
        
        // --- TABELA DE CONSTRUTORES ---
        tabelaConstrutores = new JTable();
        tabelaConstrutores.setRowHeight(40);
        JScrollPane scrollConstrutores = new JScrollPane(tabelaConstrutores);
        tabbedPane.addTab("Classificação Equipes", new ImageIcon(getClass().getResource("/resource/Icone24pxEquipe.png")), scrollConstrutores);
        
        JPanel pnlResultados = new JPanel(null);
        tabbedPane.addTab("Resultados", new ImageIcon(getClass().getResource("/resource/Icone24pxTrofeu.png")), pnlResultados);

        // === RODAPÉ ===
        LB_CarrosCat = new JLabel("");
        LB_CarrosCat.setVerticalAlignment(SwingConstants.BOTTOM);
        LB_CarrosCat.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/resource/Banner F1_OK.png")));
        LB_CarrosCat.setHorizontalAlignment(SwingConstants.CENTER);
        LB_CarrosCat.setBounds(642, 432, 567, 163);
        contentPane.add(LB_CarrosCat);

        BT_IrParaCorrida = new JButton("IR PARA A CORRIDA!");
        BT_IrParaCorrida.setFont(new Font("Arial", Font.BOLD, 16));
        BT_IrParaCorrida.setIcon(new ImageIcon(getClass().getResource("/resource/Icone24pxBandeiraDeChegada.png")));
        BT_IrParaCorrida.setBounds(643, 606, 566, 33);
        BT_IrParaCorrida.addActionListener(e -> irParaCorrida());
        contentPane.add(BT_IrParaCorrida);
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

        // Equipe Info
        LB_SedeEquipe.setText(equipeJogavel.getSede());
        LB_Motor.setText("Motor " + equipeJogavel.getMotor());
        LB_Orc.setText(String.format("€ %.1f milhões", equipeJogavel.getSaldoFinanceiro()));
        LB_NomeDirigente.setText(TelaSelecionarEquipe.nomeDirigente);
        LB_Ano.setText("Ano " + SessaoJogo.anoSelecionado);
        
        carregarImagem(LB_LogoEquipe, equipeJogavel.getCaminhoLogo());
        carregarImagem(LB_BandeiraMotor, equipeJogavel.getCaminhoBandeiraMotor());
        carregarImagem(LB_LogoMotorPQ, equipeJogavel.getCaminhoLogoMotor());
        carregarImagem(LB_BandeiraSede, equipeJogavel.getCaminhoBandeiraSede());

        // Banners
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
            carregarImagem(LB_CarrosCat, "/resource/Banner F1_OK.png");
            LB_TipoPista.setForeground(Color.BLUE);
        }

        // Labels dos Pilotos (Canto Esquerdo)
        List<Piloto> pilotosEquipe = equipeJogavel.getPilotosTitulares();
        pilotosEquipe.addAll(equipeJogavel.getPilotosReservas());

        atualizarSlotPiloto(0, pilotosEquipe, LB_NomeP1, LB_NumP1, LB_IdadeP1, LB_TempoContratoP1, LB_BandeiraP1, LB_StatusP1, LB_Over_P1);
        atualizarSlotPiloto(1, pilotosEquipe, LB_NomeP2, LB_NumP2, LB_IdadeP2, LB_TempoContratoP2, LB_BandeiraP2, LB_StatusP2, LB_Over_P2);
        atualizarSlotPiloto(2, pilotosEquipe, LB_NomeP3, LB_NumP3, LB_IdadeP3, LB_TempoContratoP3, LB_BandeiraP3, LB_StatusP3, LB_Over_P3);
        atualizarSlotPiloto(3, pilotosEquipe, LB_NomeP4, LB_NumP4, LB_IdadeP4, LB_TempoContratoP4, LB_BandeiraP4, LB_StatusP4, LB_Over_P4);
        atualizarSlotPiloto(4, pilotosEquipe, LB_NomeP5, LB_NumP5, LB_IdadeP5, LB_TempoContratoP5, LB_BandeiraP5, LB_StatusP5, LB_Over_P5);

        atualizarCalendarioUI();
        
        // Popula as tabelas com TODOS os dados
        preencherTabelas();
    }

    private void atualizarSlotPiloto(int index, List<Piloto> lista, JLabel lbNome, JLabel lbNum, JLabel lbIdade, JLabel lbContrato, JLabel lbFlag, JLabel lbStatus, JLabel lbOver) {
        if (index < lista.size()) {
            Piloto p = lista.get(index);
            lbNome.setText(p.getNome());
            lbNum.setText("#" + p.getNumero());
            lbIdade.setText(p.getIdade() + " anos");
            lbContrato.setText("Contrato Ativo");
            lbOver.setText("Ovr " + (int)p.getOverall());
            
            carregarImagem(lbFlag, "/resource/Bandeira " + p.getNacionalidade() + ".png");
            
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
        // ------------------------------------------------------------------------
        // 1. TABELA DE PILOTOS
        // Colunas: Pos, País, Nº, Piloto, Equipe, Pts, Diff, Vit, Pód, Poles
        // ------------------------------------------------------------------------
        String[] colunasPilotos = {"Pos", "País", "Nº", "Piloto", "Equipe", "Pts", "Diff", "Vit", "Pód", "Poles"};
        
        DefaultTableModel modelPilotos = new DefaultTableModel(colunasPilotos, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return ImageIcon.class; // Apenas País é imagem
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // Carregar TODOS os dados do Mod/Temporada
        List<Equipe> todasEquipes = CarregadorJSON.carregarEquipes(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
        List<Piloto> todosPilotos = CarregadorJSON.carregarPilotos(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
        
        boolean isF1 = SessaoJogo.categoriaKey.toLowerCase().contains("f1");
        List<Piloto> pilotosGrid = new ArrayList<>();

        // Cruzamento de Dados: Equipe -> IDs -> Objetos Piloto
        for (Equipe eq : todasEquipes) {
            List<String> ids = eq.getPilotosContratadosIDs();
            if (ids == null) continue;

            for (int i = 0; i < ids.size(); i++) {
                // REGRA F1: Apenas os 2 primeiros (titulares) aparecem na tabela
                if (isF1 && i >= 2) continue; 

                String idProcurado = ids.get(i);
                Piloto pilotoEncontrado = buscarPilotoNaLista(todosPilotos, idProcurado);

                if (pilotoEncontrado != null) {
                    // Vincula o nome da equipe ao piloto para exibição
                    pilotoEncontrado.setNomeEquipeAtual(eq.getNome());
                    pilotosGrid.add(pilotoEncontrado);
                }
            }
        }
        
        // ORDENAÇÃO: Pontos > Vitórias > Pódios > Nome
        Collections.sort(pilotosGrid, new Comparator<Piloto>() {
            @Override
            public int compare(Piloto p1, Piloto p2) {
                if (p1.getPontos() != p2.getPontos()) return Integer.compare(p2.getPontos(), p1.getPontos());
                if (p1.getVitorias() != p2.getVitorias()) return Integer.compare(p2.getVitorias(), p1.getVitorias());
                if (p1.getPodios() != p2.getPodios()) return Integer.compare(p2.getPodios(), p1.getPodios());
                return p1.getNome().compareToIgnoreCase(p2.getNome());
            }
        });

        // Preenchimento das Linhas
        int posP = 1;
        int pontosLiderP = (pilotosGrid.isEmpty()) ? 0 : pilotosGrid.get(0).getPontos();

        for (Piloto p : pilotosGrid) {
            ImageIcon flag = obterIcone("/resource/Bandeira " + p.getNacionalidade() + ".png");
            int diff = pontosLiderP - p.getPontos();
            
            modelPilotos.addRow(new Object[]{
                posP++, 
                flag, 
                p.getNumero(),          // Coluna Nº isolada
                p.getNome(),            // Apenas o nome
                p.getNomeEquipeAtual(), // Nome da Equipe
                p.getPontos(), 
                diff, 
                p.getVitorias(), 
                p.getPodios(), 
                p.getPoles()
            });
        }

        tabelaPilotos.setModel(modelPilotos);
        
        // Configuração de Renderers (Alinhamento)
        CentralizadoRenderer centerRenderer = new CentralizadoRenderer();
        EsquerdaRenderer leftRenderer = new EsquerdaRenderer();
        
        for (int i = 0; i < tabelaPilotos.getColumnCount(); i++) {
            // Piloto (3) e Equipe (4) alinhados à Esquerda. O resto Centralizado.
            if (i == 3 || i == 4) tabelaPilotos.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
            else tabelaPilotos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Configuração de Larguras (Pixels)
        tabelaPilotos.getColumnModel().getColumn(0).setPreferredWidth(30);  // Pos
        tabelaPilotos.getColumnModel().getColumn(1).setPreferredWidth(40);  // País
        tabelaPilotos.getColumnModel().getColumn(2).setPreferredWidth(30);  // Nº (Compacto)
        tabelaPilotos.getColumnModel().getColumn(3).setPreferredWidth(170); // Piloto (Largo)
        tabelaPilotos.getColumnModel().getColumn(4).setPreferredWidth(150); // Equipe
        tabelaPilotos.getColumnModel().getColumn(5).setPreferredWidth(50);  // Pts
        tabelaPilotos.getColumnModel().getColumn(6).setPreferredWidth(50);  // Diff
        tabelaPilotos.getColumnModel().getColumn(7).setPreferredWidth(50);  // Vit
        tabelaPilotos.getColumnModel().getColumn(8).setPreferredWidth(50);  // Pod
        tabelaPilotos.getColumnModel().getColumn(9).setPreferredWidth(50);  // Poles

        // ------------------------------------------------------------------------
        // 2. TABELA DE CONSTRUTORES
        // Colunas: Pos, País, Equipe, Pts, Diff, Vit, Pód, Poles (Sem Logo)
        // ------------------------------------------------------------------------
        String[] colunasEquipes = {"Pos", "País", "Equipe", "Pts", "Diff", "Vit", "Pód", "Poles"};
        
        DefaultTableModel modelEquipes = new DefaultTableModel(colunasEquipes, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 1) return ImageIcon.class; // País
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        // Ordenação
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
            ImageIcon flag = obterIcone(eq.getCaminhoBandeiraSede());
            int diff = pontosLiderE - eq.getPontos();

            modelEquipes.addRow(new Object[]{
                posE++, 
                flag, 
                eq.getNome(),
                eq.getPontos(), 
                diff, 
                eq.getVitorias(), 
                eq.getPodios(), 
                eq.getPoles()
            });
        }

        tabelaConstrutores.setModel(modelEquipes);
        
        for (int i = 0; i < tabelaConstrutores.getColumnCount(); i++) {
            // Equipe (2) alinhada à Esquerda
            if (i == 2) tabelaConstrutores.getColumnModel().getColumn(i).setCellRenderer(leftRenderer);
            else tabelaConstrutores.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        // Larguras Equipes
        tabelaConstrutores.getColumnModel().getColumn(0).setPreferredWidth(30); // Pos
        tabelaConstrutores.getColumnModel().getColumn(1).setPreferredWidth(40); // País
        tabelaConstrutores.getColumnModel().getColumn(2).setPreferredWidth(200); // Equipe
        tabelaConstrutores.getColumnModel().getColumn(3).setPreferredWidth(50); // Pts
        tabelaConstrutores.getColumnModel().getColumn(4).setPreferredWidth(50); // Diff
        tabelaConstrutores.getColumnModel().getColumn(5).setPreferredWidth(50); // Vit
        tabelaConstrutores.getColumnModel().getColumn(6).setPreferredWidth(50); // Pod
        tabelaConstrutores.getColumnModel().getColumn(7).setPreferredWidth(50); // Poles
    }
    
    private Piloto buscarPilotoNaLista(List<Piloto> lista, String idDaEquipe) {
        String idBusca = normalizarTexto(idDaEquipe);
        for (Piloto p : lista) {
            String nomeNormalizado = normalizarTexto(p.getNome());
            // Tenta casar ID com Nome (ex: "verstappen" com "Max Verstappen")
            if (idBusca.equals(nomeNormalizado) || nomeNormalizado.contains(idBusca) || idBusca.contains(nomeNormalizado)) {
                return p;
            }
        }
        return null;
    }
    
    private String normalizarTexto(String texto) {
        if (texto == null) return "";
        String n = Normalizer.normalize(texto, Normalizer.Form.NFD);
        n = n.replaceAll("[^\\p{ASCII}]", "");
        return n.toLowerCase().replace(" ", "").trim();
    }
    
    static class CentralizadoRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.CENTER);
            setVerticalAlignment(SwingConstants.CENTER);
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText(""); 
            } else {
                setIcon(null);
                setText(value != null ? value.toString() : "");
            }
            return this;
        }
    }

    static class EsquerdaRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setHorizontalAlignment(SwingConstants.LEFT);
            setVerticalAlignment(SwingConstants.CENTER);
            if (value instanceof ImageIcon) {
                setIcon((ImageIcon) value);
                setText("");
            } else {
                setIcon(null);
                setText(value != null ? "  " + value.toString() : ""); // Espaçamento para não colar na borda
            }
            return this;
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
            boolean isIndyOrNascar = SessaoJogo.categoriaKey.contains("indy") || SessaoJogo.categoriaKey.contains("nascar");
            
            if (isIndyOrNascar) {
                LB_LocalPista.setText(pistaVisual.getNome());
            } else {
                LB_LocalPista.setText(pistaVisual.getPais());
            }
            carregarImagem(LB_BandeiraPista, "/resource/Bandeira " + pistaVisual.getPais() + ".png");

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
            List<Pista> todas = CarregadorJSON.carregarCalendario(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
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
        ImageIcon icon = obterIcone(path);
        if (icon != null) lbl.setIcon(icon);
        else lbl.setIcon(null);
    }
    
    private ImageIcon obterIcone(String path) {
        try {
            if (path != null && !path.isEmpty()) {
                if (!path.startsWith("/")) path = "/" + path;
                if (!path.startsWith("/resource")) path = "/resource" + path;
                path = path.replace("//", "/");
                java.net.URL imgUrl = getClass().getResource(path);
                if (imgUrl != null) return new ImageIcon(imgUrl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void irParaCorrida() {
        JOptionPane.showMessageDialog(this, "Carregando fim de semana de corrida...");
    }
}
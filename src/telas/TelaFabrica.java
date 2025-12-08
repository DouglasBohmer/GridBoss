package telas;

import dados.DadosDoJogo;
import modelos.Equipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TelaFabrica extends JDialog {

    private final JPanel contentPane;
    private DadosDoJogo dadosDoJogo;
    private Equipe equipe;

    // Custos
    private final double CUSTO_CONTRATACAO = 0.5; // 500k
    private final double REEMBOLSO_DEMISSAO = 0.25; // 250k
    private final double CUSTO_BASE_EVOLUCAO = 5.0; // 5M base

    // --- Componentes Globais ---
    private JLabel lblSaldo;
    private JTable tabelaResumo;
    
    // --- MOTOR ---
    private JLabel lblNivelMotor;
    private JLabel lblPerfMotor;
    private JProgressBar barStaffMotor;
    private JLabel lblValorStaffMotor;
    private JButton btnContratarMotor;
    private JButton btnDemitirMotor;
    private JButton btnEvoluirMotor;
    private JButton btnDowngradeMotor;

    // --- AERO ---
    private JLabel lblNivelAero;
    private JLabel lblPerfAero;
    private JProgressBar barStaffAero;
    private JLabel lblValorStaffAero;
    private JButton btnContratarAero;
    private JButton btnDemitirAero;
    private JButton btnEvoluirAero;
    private JButton btnDowngradeAero;

    // --- CHASSI ---
    private JLabel lblNivelChassi;
    private JLabel lblPerfChassi;
    private JProgressBar barStaffChassi;
    private JLabel lblValorStaffChassi;
    private JButton btnContratarChassi;
    private JButton btnDemitirChassi;
    private JButton btnEvoluirChassi;
    private JButton btnDowngradeChassi;

    // --- CONFIABILIDADE ---
    private JLabel lblNivelConf;
    private JLabel lblPerfConf;
    private JProgressBar barStaffConf;
    private JLabel lblValorStaffConf;
    private JButton btnContratarConf;
    private JButton btnDemitirConf;
    private JButton btnEvoluirConf;
    private JButton btnDowngradeConf;

    public TelaFabrica(DadosDoJogo dados) {
        this.dadosDoJogo = dados;
        this.equipe = dados.getEquipeDoJogador();

        setModal(true);
        setTitle("Grid Boss - Fábrica");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFabrica.class.getResource("/resource/Icone16px.png")));
        setBounds(100, 100, 1000, 614);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- CABEÇALHO ---
        JLabel lblTitulo = new JLabel("DESENVOLVIMENTO & FÁBRICA");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 24));
        lblTitulo.setBounds(0, 11, 970, 40);
        contentPane.add(lblTitulo);

        JLabel lblNomeEquipe = new JLabel(equipe.getNome());
        lblNomeEquipe.setHorizontalAlignment(SwingConstants.LEFT);
        lblNomeEquipe.setFont(new Font("Berlin Sans FB", Font.PLAIN, 21));
        lblNomeEquipe.setBounds(180, 544, 480, 25);
        contentPane.add(lblNomeEquipe);

        // =================================================================
        // --- PAINEL MOTOR ---
        // =================================================================
        JPanel pnlMotor = new JPanel();
        pnlMotor.setLayout(null);
        pnlMotor.setBackground(new Color(250, 250, 250));
        pnlMotor.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlMotor.setBounds(10, 62, 470, 160);
        contentPane.add(pnlMotor);
        
        JLabel lblTitMotor = new JLabel("DEPARTAMENTO DE MOTOR");
        lblTitMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitMotor.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitMotor.setBounds(20, 10, 440, 25);
        pnlMotor.add(lblTitMotor);
        
        lblNivelMotor = new JLabel("Nível: 1");
        lblNivelMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblNivelMotor.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelMotor.setBounds(320, 46, 140, 20);
        pnlMotor.add(lblNivelMotor);
        
        lblPerfMotor = new JLabel("Performance: 22 pts");
        lblPerfMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblPerfMotor.setForeground(new Color(0, 100, 0));
        lblPerfMotor.setFont(new Font("Arial", Font.BOLD, 12));
        lblPerfMotor.setBounds(320, 132, 140, 25);
        pnlMotor.add(lblPerfMotor);
        
        JLabel lblStaffM = new JLabel("Equipe Técnica:");
        lblStaffM.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffM.setBounds(10, 77, 105, 20);
        pnlMotor.add(lblStaffM);
        
        barStaffMotor = new JProgressBar();
        barStaffMotor.setMaximum(10);
        barStaffMotor.setForeground(new Color(50, 205, 50));
        barStaffMotor.setBounds(125, 77, 185, 20);
        pnlMotor.add(barStaffMotor);
        
        lblValorStaffMotor = new JLabel("1/10");
        lblValorStaffMotor.setHorizontalAlignment(SwingConstants.LEFT);
        lblValorStaffMotor.setBounds(420, 77, 40, 20);
        pnlMotor.add(lblValorStaffMotor);
        
        btnDemitirMotor = new JButton("Demitir (+€250k)");
        btnDemitirMotor.setForeground(new Color(255, 255, 255));
        btnDemitirMotor.setBackground(new Color(255, 55, 55));
        btnDemitirMotor.setToolTipText("Demitir (-€250k Reembolso)");
        btnDemitirMotor.setMargin(new Insets(2, 2, 2, 2));
        btnDemitirMotor.setBounds(10, 108, 140, 20);
        btnDemitirMotor.addActionListener(e -> acaoDemitir("motor"));
        pnlMotor.add(btnDemitirMotor);
        
        JLabel lblSalarioM = new JLabel("€10k/mês por pessoa");
        lblSalarioM.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalarioM.setForeground(Color.DARK_GRAY);
        lblSalarioM.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSalarioM.setBounds(320, 108, 140, 15);
        pnlMotor.add(lblSalarioM);
        
        btnContratarMotor = new JButton("Contratar (-€500k)");
        btnContratarMotor.setForeground(new Color(255, 255, 255));
        btnContratarMotor.setBackground(new Color(0, 168, 0));
        btnContratarMotor.setBounds(170, 108, 140, 20);
        btnContratarMotor.addActionListener(e -> acaoContratarStaff("motor"));
        pnlMotor.add(btnContratarMotor);
        
        btnDowngradeMotor = new JButton("Descer Nível");
        btnDowngradeMotor.setForeground(new Color(255, 255, 255));
        btnDowngradeMotor.setToolTipText("Reduzir Nível (Economiza manutenção)");
        btnDowngradeMotor.setBackground(new Color(255, 55, 55)); 
        btnDowngradeMotor.setBounds(10, 46, 140, 20);
        btnDowngradeMotor.addActionListener(e -> acaoDowngradeNivel("motor"));
        pnlMotor.add(btnDowngradeMotor);
        
        btnEvoluirMotor = new JButton("Evoluir Nível");
        btnEvoluirMotor.setForeground(new Color(255, 255, 255));
        btnEvoluirMotor.setBackground(new Color(0, 168, 0));
        btnEvoluirMotor.setBounds(170, 46, 140, 20);
        btnEvoluirMotor.addActionListener(e -> acaoEvoluirNivel("motor"));
        pnlMotor.add(btnEvoluirMotor);
        
        JLabel lblFuncionrios = new JLabel("Funcionários");
        lblFuncionrios.setHorizontalAlignment(SwingConstants.CENTER);
        lblFuncionrios.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblFuncionrios.setBounds(320, 77, 90, 20);
        pnlMotor.add(lblFuncionrios);

        // =================================================================
        // --- PAINEL AERO ---
        // =================================================================
        JPanel pnlAero = new JPanel();
        pnlAero.setLayout(null);
        pnlAero.setBackground(new Color(250, 250, 250));
        pnlAero.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlAero.setBounds(500, 62, 470, 160); // X = 500
        contentPane.add(pnlAero);
        
        JLabel lblTitAero = new JLabel("AERODINÂMICA");
        lblTitAero.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitAero.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitAero.setBounds(20, 10, 440, 25);
        pnlAero.add(lblTitAero);
        
        lblNivelAero = new JLabel("Nível: 1");
        lblNivelAero.setHorizontalAlignment(SwingConstants.CENTER);
        lblNivelAero.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelAero.setBounds(320, 46, 140, 20);
        pnlAero.add(lblNivelAero);
        
        lblPerfAero = new JLabel("Performance: 22 pts");
        lblPerfAero.setHorizontalAlignment(SwingConstants.CENTER);
        lblPerfAero.setForeground(new Color(0, 100, 0));
        lblPerfAero.setFont(new Font("Arial", Font.BOLD, 12));
        lblPerfAero.setBounds(320, 132, 140, 25);
        pnlAero.add(lblPerfAero);
        
        JLabel lblStaffA = new JLabel("Equipe Técnica:");
        lblStaffA.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffA.setBounds(10, 77, 105, 20);
        pnlAero.add(lblStaffA);
        
        barStaffAero = new JProgressBar();
        barStaffAero.setMaximum(10);
        barStaffAero.setForeground(new Color(30, 144, 255)); // Azul
        barStaffAero.setBounds(125, 77, 185, 20);
        pnlAero.add(barStaffAero);
        
        lblValorStaffAero = new JLabel("1/10");
        lblValorStaffAero.setHorizontalAlignment(SwingConstants.LEFT);
        lblValorStaffAero.setBounds(420, 77, 40, 20);
        pnlAero.add(lblValorStaffAero);
        
        btnDemitirAero = new JButton("Demitir (+€250k)");
        btnDemitirAero.setForeground(new Color(255, 255, 255));
        btnDemitirAero.setBackground(new Color(255, 55, 55));
        btnDemitirAero.setToolTipText("Demitir (-€250k Reembolso)");
        btnDemitirAero.setMargin(new Insets(2, 2, 2, 2));
        btnDemitirAero.setBounds(10, 108, 140, 20);
        btnDemitirAero.addActionListener(e -> acaoDemitir("aero"));
        pnlAero.add(btnDemitirAero);
        
        JLabel lblSalarioA = new JLabel("€10k/mês por pessoa");
        lblSalarioA.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalarioA.setForeground(Color.DARK_GRAY);
        lblSalarioA.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSalarioA.setBounds(320, 108, 140, 15);
        pnlAero.add(lblSalarioA);
        
        btnContratarAero = new JButton("Contratar (-€500k)");
        btnContratarAero.setForeground(new Color(255, 255, 255));
        btnContratarAero.setBackground(new Color(0, 168, 0));
        btnContratarAero.setBounds(170, 108, 140, 20);
        btnContratarAero.addActionListener(e -> acaoContratarStaff("aero"));
        pnlAero.add(btnContratarAero);
        
        btnDowngradeAero = new JButton("Descer Nível");
        btnDowngradeAero.setForeground(new Color(255, 255, 255));
        btnDowngradeAero.setToolTipText("Reduzir Nível");
        btnDowngradeAero.setBackground(new Color(255, 55, 55)); 
        btnDowngradeAero.setBounds(10, 46, 140, 20);
        btnDowngradeAero.addActionListener(e -> acaoDowngradeNivel("aero"));
        pnlAero.add(btnDowngradeAero);
        
        btnEvoluirAero = new JButton("Evoluir Nível");
        btnEvoluirAero.setForeground(new Color(255, 255, 255));
        btnEvoluirAero.setBackground(new Color(0, 168, 0));
        btnEvoluirAero.setBounds(170, 46, 140, 20);
        btnEvoluirAero.addActionListener(e -> acaoEvoluirNivel("aero"));
        pnlAero.add(btnEvoluirAero);
        
        JLabel lblFuncionriosA = new JLabel("Funcionários");
        lblFuncionriosA.setHorizontalAlignment(SwingConstants.CENTER);
        lblFuncionriosA.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblFuncionriosA.setBounds(320, 77, 90, 20);
        pnlAero.add(lblFuncionriosA);

        // =================================================================
        // --- PAINEL CHASSI ---
        // =================================================================
        JPanel pnlChassi = new JPanel();
        pnlChassi.setLayout(null);
        pnlChassi.setBackground(new Color(250, 250, 250));
        pnlChassi.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlChassi.setBounds(10, 233, 470, 160); // Y = 233
        contentPane.add(pnlChassi);
        
        JLabel lblTitChassi = new JLabel("CHASSI & SUSPENSÃO");
        lblTitChassi.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitChassi.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitChassi.setBounds(20, 10, 440, 25);
        pnlChassi.add(lblTitChassi);
        
        lblNivelChassi = new JLabel("Nível: 1");
        lblNivelChassi.setHorizontalAlignment(SwingConstants.CENTER);
        lblNivelChassi.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelChassi.setBounds(320, 46, 140, 20);
        pnlChassi.add(lblNivelChassi);
        
        lblPerfChassi = new JLabel("Performance: 22 pts");
        lblPerfChassi.setHorizontalAlignment(SwingConstants.CENTER);
        lblPerfChassi.setForeground(new Color(0, 100, 0));
        lblPerfChassi.setFont(new Font("Arial", Font.BOLD, 12));
        lblPerfChassi.setBounds(320, 132, 140, 25);
        pnlChassi.add(lblPerfChassi);
        
        JLabel lblStaffC = new JLabel("Equipe Técnica:");
        lblStaffC.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffC.setBounds(10, 77, 105, 20);
        pnlChassi.add(lblStaffC);
        
        barStaffChassi = new JProgressBar();
        barStaffChassi.setMaximum(10);
        barStaffChassi.setForeground(new Color(255, 140, 0)); // Laranja
        barStaffChassi.setBounds(125, 77, 185, 20);
        pnlChassi.add(barStaffChassi);
        
        lblValorStaffChassi = new JLabel("1/10");
        lblValorStaffChassi.setHorizontalAlignment(SwingConstants.LEFT);
        lblValorStaffChassi.setBounds(420, 77, 40, 20);
        pnlChassi.add(lblValorStaffChassi);
        
        btnDemitirChassi = new JButton("Demitir (+€250k)");
        btnDemitirChassi.setForeground(new Color(255, 255, 255));
        btnDemitirChassi.setBackground(new Color(255, 55, 55));
        btnDemitirChassi.setToolTipText("Demitir (-€250k Reembolso)");
        btnDemitirChassi.setMargin(new Insets(2, 2, 2, 2));
        btnDemitirChassi.setBounds(10, 108, 140, 20);
        btnDemitirChassi.addActionListener(e -> acaoDemitir("chassi"));
        pnlChassi.add(btnDemitirChassi);
        
        JLabel lblSalarioC = new JLabel("€10k/mês por pessoa");
        lblSalarioC.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalarioC.setForeground(Color.DARK_GRAY);
        lblSalarioC.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSalarioC.setBounds(320, 108, 140, 15);
        pnlChassi.add(lblSalarioC);
        
        btnContratarChassi = new JButton("Contratar (-€500k)");
        btnContratarChassi.setForeground(new Color(255, 255, 255));
        btnContratarChassi.setBackground(new Color(0, 168, 0));
        btnContratarChassi.setBounds(170, 108, 140, 20);
        btnContratarChassi.addActionListener(e -> acaoContratarStaff("chassi"));
        pnlChassi.add(btnContratarChassi);
        
        btnDowngradeChassi = new JButton("Descer Nível");
        btnDowngradeChassi.setForeground(new Color(255, 255, 255));
        btnDowngradeChassi.setToolTipText("Reduzir Nível");
        btnDowngradeChassi.setBackground(new Color(255, 55, 55)); 
        btnDowngradeChassi.setBounds(10, 46, 140, 20);
        btnDowngradeChassi.addActionListener(e -> acaoDowngradeNivel("chassi"));
        pnlChassi.add(btnDowngradeChassi);
        
        btnEvoluirChassi = new JButton("Evoluir Nível");
        btnEvoluirChassi.setForeground(new Color(255, 255, 255));
        btnEvoluirChassi.setBackground(new Color(0, 168, 0));
        btnEvoluirChassi.setBounds(170, 46, 140, 20);
        btnEvoluirChassi.addActionListener(e -> acaoEvoluirNivel("chassi"));
        pnlChassi.add(btnEvoluirChassi);
        
        JLabel lblFuncionriosC = new JLabel("Funcionários");
        lblFuncionriosC.setHorizontalAlignment(SwingConstants.CENTER);
        lblFuncionriosC.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblFuncionriosC.setBounds(320, 77, 90, 20);
        pnlChassi.add(lblFuncionriosC);

        // =================================================================
        // --- PAINEL CONFIABILIDADE ---
        // =================================================================
        JPanel pnlConf = new JPanel();
        pnlConf.setLayout(null);
        pnlConf.setBackground(new Color(250, 250, 250));
        pnlConf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlConf.setBounds(500, 233, 470, 160); // X = 500
        contentPane.add(pnlConf);
        
        JLabel lblTitConf = new JLabel("CONFIABILIDADE");
        lblTitConf.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitConf.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitConf.setBounds(20, 10, 440, 25);
        pnlConf.add(lblTitConf);
        
        lblNivelConf = new JLabel("Nível: 1");
        lblNivelConf.setHorizontalAlignment(SwingConstants.CENTER);
        lblNivelConf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelConf.setBounds(320, 46, 140, 20);
        pnlConf.add(lblNivelConf);
        
        lblPerfConf = new JLabel("Performance: 22 pts");
        lblPerfConf.setHorizontalAlignment(SwingConstants.CENTER);
        lblPerfConf.setForeground(new Color(0, 100, 0));
        lblPerfConf.setFont(new Font("Arial", Font.BOLD, 12));
        lblPerfConf.setBounds(320, 132, 140, 25);
        pnlConf.add(lblPerfConf);
        
        JLabel lblStaffCo = new JLabel("Equipe Técnica:");
        lblStaffCo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffCo.setBounds(10, 77, 105, 20);
        pnlConf.add(lblStaffCo);
        
        barStaffConf = new JProgressBar();
        barStaffConf.setMaximum(10);
        barStaffConf.setForeground(new Color(128, 0, 128)); // Roxo
        barStaffConf.setBounds(125, 77, 185, 20);
        pnlConf.add(barStaffConf);
        
        lblValorStaffConf = new JLabel("1/10");
        lblValorStaffConf.setHorizontalAlignment(SwingConstants.LEFT);
        lblValorStaffConf.setBounds(420, 77, 40, 20);
        pnlConf.add(lblValorStaffConf);
        
        btnDemitirConf = new JButton("Demitir (+€250k)");
        btnDemitirConf.setForeground(new Color(255, 255, 255));
        btnDemitirConf.setBackground(new Color(255, 55, 55));
        btnDemitirConf.setToolTipText("Demitir (-€250k Reembolso)");
        btnDemitirConf.setMargin(new Insets(2, 2, 2, 2));
        btnDemitirConf.setBounds(10, 108, 140, 20);
        btnDemitirConf.addActionListener(e -> acaoDemitir("confiabilidade"));
        pnlConf.add(btnDemitirConf);
        
        JLabel lblSalarioCo = new JLabel("€10k/mês por pessoa");
        lblSalarioCo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSalarioCo.setForeground(Color.DARK_GRAY);
        lblSalarioCo.setFont(new Font("Arial", Font.PLAIN, 11));
        lblSalarioCo.setBounds(320, 108, 140, 15);
        pnlConf.add(lblSalarioCo);
        
        btnContratarConf = new JButton("Contratar (-€500k)");
        btnContratarConf.setForeground(new Color(255, 255, 255));
        btnContratarConf.setBackground(new Color(0, 168, 0));
        btnContratarConf.setBounds(170, 108, 140, 20);
        btnContratarConf.addActionListener(e -> acaoContratarStaff("confiabilidade"));
        pnlConf.add(btnContratarConf);
        
        btnDowngradeConf = new JButton("Descer Nível");
        btnDowngradeConf.setForeground(new Color(255, 255, 255));
        btnDowngradeConf.setToolTipText("Reduzir Nível");
        btnDowngradeConf.setBackground(new Color(255, 55, 55)); 
        btnDowngradeConf.setBounds(10, 46, 140, 20);
        btnDowngradeConf.addActionListener(e -> acaoDowngradeNivel("confiabilidade"));
        pnlConf.add(btnDowngradeConf);
        
        btnEvoluirConf = new JButton("Evoluir Nível");
        btnEvoluirConf.setForeground(new Color(255, 255, 255));
        btnEvoluirConf.setBackground(new Color(0, 168, 0));
        btnEvoluirConf.setBounds(170, 46, 140, 20);
        btnEvoluirConf.addActionListener(e -> acaoEvoluirNivel("confiabilidade"));
        pnlConf.add(btnEvoluirConf);
        
        JLabel lblFuncionriosCo = new JLabel("Funcionários");
        lblFuncionriosCo.setHorizontalAlignment(SwingConstants.CENTER);
        lblFuncionriosCo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblFuncionriosCo.setBounds(320, 77, 90, 20);
        pnlConf.add(lblFuncionriosCo);

        // --- TABELA DE RESUMO (RODAPÉ) ---
        JScrollPane scrollTabela = new JScrollPane();
        scrollTabela.setBounds(10, 404, 960, 130);
        contentPane.add(scrollTabela);
        
        tabelaResumo = new JTable();
        tabelaResumo.setRowHeight(20);
        tabelaResumo.setFont(new Font("Arial", Font.PLAIN, 12));
        tabelaResumo.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        
        // Modelo da tabela
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Departamento", "Nível (Força)", "Staff (Bônus)", "Performance Real", "Custo Mensal"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaResumo.setModel(model);
        
        // Centraliza colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i<tabelaResumo.getColumnCount(); i++) tabelaResumo.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        
        scrollTabela.setViewportView(tabelaResumo);

        // --- BOTÃO VOLTAR ---
        JButton btnVoltar = new JButton("VOLTAR PARA O MENU");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        btnVoltar.setBounds(670, 545, 300, 25);
        btnVoltar.addActionListener(e -> dispose()); 
        contentPane.add(btnVoltar);
        
                lblSaldo = new JLabel("€ 0.0 M");
                lblSaldo.setBounds(10, 545, 115, 25);
                contentPane.add(lblSaldo);
                lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
                lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
                
                JLabel lblIconeMoney = new JLabel("");
                lblIconeMoney.setHorizontalAlignment(SwingConstants.CENTER);
                lblIconeMoney.setBounds(135, 544, 35, 25);
                contentPane.add(lblIconeMoney);
                lblIconeMoney.setIcon(new ImageIcon(TelaFabrica.class.getResource("/resource/IconeEuro24px.png")));

        // Atualiza os dados iniciais
        atualizarInterface();
    }

    // --- LÓGICA DO JOGO ---

    private void acaoContratarStaff(String setor) {
        if (equipe.getSaldoFinanceiro() < CUSTO_CONTRATACAO) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean sucesso = false;
        switch (setor) {
            case "motor": sucesso = equipe.contratarStaffMotor(); break;
            case "aero": sucesso = equipe.contratarStaffAero(); break;
            case "chassi": sucesso = equipe.contratarStaffChassi(); break;
            case "confiabilidade": sucesso = equipe.contratarStaffConfiabilidade(); break;
        }
        if (sucesso) {
            equipe.pagarDespesa(CUSTO_CONTRATACAO);
            atualizarInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Staff máximo atingido para este nível!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private void acaoDemitir(String setor) {
        boolean sucesso = false;
        switch (setor) {
            case "motor": sucesso = equipe.demitirStaffMotor(); break;
            case "aero": sucesso = equipe.demitirStaffAero(); break;
            case "chassi": sucesso = equipe.demitirStaffChassi(); break;
            case "confiabilidade": sucesso = equipe.demitirStaffConfiabilidade(); break;
        }
        if (sucesso) {
            equipe.receberReceita(REEMBOLSO_DEMISSAO);
            atualizarInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Mínimo de 1 funcionário exigido!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void acaoEvoluirNivel(String setor) {
        int nivel = getNivelSetor(setor);
        // FIX: Garante que custo nunca seja 0, mesmo se nivel for 0 por erro
        int nivelParaCalculo = (nivel < 1) ? 1 : nivel;
        double custo = nivelParaCalculo * CUSTO_BASE_EVOLUCAO;
        
        if (equipe.getSaldoFinanceiro() < custo) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int resp = JOptionPane.showConfirmDialog(this, 
            "Evoluir " + setor.toUpperCase() + " para Nível " + (nivel+1) + "?\nCusto: €" + custo + "M\nAVISO: O Staff atual será ajustado para o mínimo do novo nível.", 
            "Confirmar", JOptionPane.YES_NO_OPTION);
            
        if (resp == JOptionPane.YES_OPTION) {
            boolean ok = false;
            switch(setor) {
                case "motor": ok = equipe.subirNivelMotor(); break;
                case "aero": ok = equipe.subirNivelAero(); break;
                case "chassi": ok = equipe.subirNivelChassi(); break;
                case "confiabilidade": ok = equipe.subirNivelConfiabilidade(); break;
            }
            if(ok) {
                equipe.pagarDespesa(custo);
                JOptionPane.showMessageDialog(this, "Nível Aumentado!");
                atualizarInterface();
            }
        }
    }
    
    private void acaoDowngradeNivel(String setor) {
        int nivel = getNivelSetor(setor);
        if (nivel <= 1) return;
        
        int resp = JOptionPane.showConfirmDialog(this, 
            "Reduzir " + setor.toUpperCase() + " para Nível " + (nivel-1) + "?\n" +
            "Economia mensal: € 100k\n" +
            "AVISO: O limite de staff cairá. Excedentes serão demitidos (com reembolso).", 
            "Confirmar Downgrade", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
        if (resp == JOptionPane.YES_OPTION) {
            double reembolso = 0.0;
            switch(setor) {
                case "motor": reembolso = equipe.downgradeNivelMotor(); break;
                case "aero": reembolso = equipe.downgradeNivelAero(); break;
                case "chassi": reembolso = equipe.downgradeNivelChassi(); break;
                case "confiabilidade": reembolso = equipe.downgradeNivelConfiabilidade(); break;
            }
            
            if (reembolso > 0) {
                equipe.receberReceita(reembolso);
                JOptionPane.showMessageDialog(this, "Nível reduzido.\nReembolso de € " + reembolso + "M por staff excedente.");
            } else {
                JOptionPane.showMessageDialog(this, "Nível reduzido.");
            }
            atualizarInterface();
        }
    }
    
    private int getNivelSetor(String setor) {
        switch(setor) {
            case "motor": return equipe.getNivelMotor();
            case "aero": return equipe.getNivelAero();
            case "chassi": return equipe.getNivelChassi();
            case "confiabilidade": return equipe.getNivelConfiabilidade();
            default: return 1;
        }
    }

    private void atualizarInterface() {
        lblSaldo.setText(String.format("€ %.1f M", equipe.getSaldoFinanceiro()));
        
        atualizarPainel(lblNivelMotor, lblPerfMotor, barStaffMotor, lblValorStaffMotor, btnEvoluirMotor, btnDowngradeMotor, btnContratarMotor, btnDemitirMotor,
                equipe.getNivelMotor(), equipe.getStaffMotor(), equipe.getForcaMotorCalculada());
                
        atualizarPainel(lblNivelAero, lblPerfAero, barStaffAero, lblValorStaffAero, btnEvoluirAero, btnDowngradeAero, btnContratarAero, btnDemitirAero,
                equipe.getNivelAero(), equipe.getStaffAero(), equipe.getForcaAeroCalculada());
                
        atualizarPainel(lblNivelChassi, lblPerfChassi, barStaffChassi, lblValorStaffChassi, btnEvoluirChassi, btnDowngradeChassi, btnContratarChassi, btnDemitirChassi,
                equipe.getNivelChassi(), equipe.getStaffChassi(), equipe.getForcaChassiCalculada());
                
        atualizarPainel(lblNivelConf, lblPerfConf, barStaffConf, lblValorStaffConf, btnEvoluirConf, btnDowngradeConf, btnContratarConf, btnDemitirConf,
                equipe.getNivelConfiabilidade(), equipe.getStaffConfiabilidade(), equipe.getForcaConfiabilidadeCalculada());
                
        // Atualizar Tabela
        DefaultTableModel model = (DefaultTableModel) tabelaResumo.getModel();
        model.setRowCount(0);
        
        adicionarLinhaTabela(model, "Motor", equipe.getNivelMotor(), equipe.getStaffMotor(), equipe.getForcaMotorCalculada());
        adicionarLinhaTabela(model, "Aerodinâmica", equipe.getNivelAero(), equipe.getStaffAero(), equipe.getForcaAeroCalculada());
        adicionarLinhaTabela(model, "Chassi", equipe.getNivelChassi(), equipe.getStaffChassi(), equipe.getForcaChassiCalculada());
        adicionarLinhaTabela(model, "Confiabilidade", equipe.getNivelConfiabilidade(), equipe.getStaffConfiabilidade(), equipe.getForcaConfiabilidadeCalculada());
        
        // Linha Total
        double totalPerf = (equipe.getForcaMotorCalculada() + equipe.getForcaAeroCalculada() + equipe.getForcaChassiCalculada() + equipe.getForcaConfiabilidadeCalculada()) / 4;
        double custoMensal = equipe.getCustoMensalFabrica();
        model.addRow(new Object[] {
            "MÉDIA / TOTAL",
            "-",
            "-",
            String.format("%.1f pts", totalPerf),
            String.format("€ %.2f M/mês", custoMensal)
        });
    }
    
    private void atualizarPainel(JLabel lblNivel, JLabel lblPerf, JProgressBar bar, JLabel lblVal, JButton btnUp, JButton btnDown, JButton btnHire, JButton btnFire, int nivel, int staff, double forca) {
        lblNivel.setText("Nível: " + nivel + "/5");
        lblPerf.setText("Performance: " + (int)forca + " pts");
        
        int limite = equipe.getLimiteStaff(nivel);
        bar.setMaximum(limite);
        bar.setValue(staff);
        lblVal.setText(staff + "/" + limite);
        
        int nivelParaCalculo = (nivel < 1) ? 1 : nivel;
        double custo = nivelParaCalculo * CUSTO_BASE_EVOLUCAO;
        
        btnUp.setText("Evoluir Nível (€" + (int)custo + "M)");
        btnUp.setEnabled(nivel < 5);
        btnDown.setEnabled(nivel > 1);
        
        // Regras de bloqueio solicitadas
        btnHire.setEnabled(staff < limite); // Bloqueia contratar se estiver cheio
        btnFire.setEnabled(staff > 1);      // Bloqueia demitir se tiver só 1
    }
    
    private void adicionarLinhaTabela(DefaultTableModel model, String nome, int nivel, int staff, double forca) {
        double custoSetor = (nivel * 0.1) + (staff * 0.01);
        model.addRow(new Object[] {
            nome,
            nivel + " (" + (nivel*20) + " pts)",
            staff + " (+" + (staff*2) + " pts)",
            (int)forca + " pts",
            String.format("€ %.2f M", custoSetor)
        });
    }
}
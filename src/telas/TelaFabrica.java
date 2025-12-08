package telas;

import dados.DadosDoJogo;
import modelos.Equipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TelaFabrica extends JDialog { // Agora estende JDialog

    private final JPanel contentPane;
    private DadosDoJogo dadosDoJogo;
    private Equipe equipe;

    // Custos
    private final double CUSTO_STAFF = 0.5; // 500k
    private final double CUSTO_BASE_EVOLUCAO = 5.0; // 5M base

    // --- Componentes Globais ---
    private JLabel lblSaldo;
    
    // --- MOTOR ---
    private JLabel lblNivelMotor;
    private JProgressBar barStaffMotor;
    private JLabel lblValorStaffMotor;
    private JButton btnContratarMotor;
    private JButton btnEvoluirMotor;

    // --- AERO ---
    private JLabel lblNivelAero;
    private JProgressBar barStaffAero;
    private JLabel lblValorStaffAero;
    private JButton btnContratarAero;
    private JButton btnEvoluirAero;

    // --- CHASSI ---
    private JLabel lblNivelChassi;
    private JProgressBar barStaffChassi;
    private JLabel lblValorStaffChassi;
    private JButton btnContratarChassi;
    private JButton btnEvoluirChassi;

    // --- CONFIABILIDADE ---
    private JLabel lblNivelConf;
    private JProgressBar barStaffConf;
    private JLabel lblValorStaffConf;
    private JButton btnContratarConf;
    private JButton btnEvoluirConf;

    public TelaFabrica(DadosDoJogo dados) {
        this.dadosDoJogo = dados;
        this.equipe = dados.getEquipeDoJogador();

        // Configuração de Janela Modal (Bloqueia a tela de trás)
        setModal(true); 
        setTitle("Grid Boss - Fábrica");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaFabrica.class.getResource("/resource/Icone16px.png")));
        setBounds(100, 100, 1000, 700);
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
        lblTitulo.setBounds(0, 11, 984, 40);
        contentPane.add(lblTitulo);

        JLabel lblNomeEquipe = new JLabel(equipe.getNome());
        lblNomeEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        lblNomeEquipe.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        lblNomeEquipe.setBounds(0, 50, 984, 30);
        contentPane.add(lblNomeEquipe);

        JPanel panelSaldo = new JPanel();
        panelSaldo.setBackground(new Color(240, 248, 255));
        panelSaldo.setBounds(780, 20, 180, 40);
        contentPane.add(panelSaldo);
        panelSaldo.setLayout(null);

        lblSaldo = new JLabel("€ 0.0 M");
        lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        lblSaldo.setBounds(10, 0, 120, 40);
        panelSaldo.add(lblSaldo);
        
        JLabel lblIconeMoney = new JLabel("");
        lblIconeMoney.setIcon(new ImageIcon(TelaFabrica.class.getResource("/resource/IconeEuro24px.png")));
        lblIconeMoney.setBounds(140, 8, 24, 24);
        panelSaldo.add(lblIconeMoney);

        // =================================================================
        // --- PAINEL MOTOR (Explícito para o Editor) ---
        // =================================================================
        JPanel pnlMotor = new JPanel();
        pnlMotor.setLayout(null);
        pnlMotor.setBackground(new Color(250, 250, 250));
        pnlMotor.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlMotor.setBounds(40, 100, 420, 180);
        contentPane.add(pnlMotor);

        JLabel lblTitMotor = new JLabel("DEPARTAMENTO DE MOTOR");
        lblTitMotor.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitMotor.setBounds(10, 10, 300, 25);
        pnlMotor.add(lblTitMotor);

        lblNivelMotor = new JLabel("Nível da Estrutura: 1");
        lblNivelMotor.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelMotor.setBounds(20, 60, 200, 20);
        pnlMotor.add(lblNivelMotor);

        JLabel lblStaffM = new JLabel("Eficiência do Staff:");
        lblStaffM.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffM.setBounds(20, 90, 150, 20);
        pnlMotor.add(lblStaffM);

        barStaffMotor = new JProgressBar();
        barStaffMotor.setMaximum(10);
        barStaffMotor.setForeground(new Color(50, 205, 50));
        barStaffMotor.setBounds(150, 90, 200, 20);
        pnlMotor.add(barStaffMotor);

        lblValorStaffMotor = new JLabel("1/10");
        lblValorStaffMotor.setBounds(360, 90, 50, 20);
        pnlMotor.add(lblValorStaffMotor);

        btnContratarMotor = new JButton("Contratar (+€0.5M)");
        btnContratarMotor.setBackground(new Color(173, 216, 230));
        btnContratarMotor.setBounds(20, 130, 180, 30);
        btnContratarMotor.addActionListener(e -> acaoContratarStaff("motor"));
        pnlMotor.add(btnContratarMotor);

        btnEvoluirMotor = new JButton("Evoluir Nível");
        btnEvoluirMotor.setBackground(new Color(255, 215, 0));
        btnEvoluirMotor.setBounds(210, 130, 180, 30);
        btnEvoluirMotor.addActionListener(e -> acaoEvoluirNivel("motor"));
        pnlMotor.add(btnEvoluirMotor);

        // =================================================================
        // --- PAINEL AERO (Explícito para o Editor) ---
        // =================================================================
        JPanel pnlAero = new JPanel();
        pnlAero.setLayout(null);
        pnlAero.setBackground(new Color(250, 250, 250));
        pnlAero.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlAero.setBounds(500, 100, 420, 180);
        contentPane.add(pnlAero);

        JLabel lblTitAero = new JLabel("AERODINÂMICA");
        lblTitAero.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitAero.setBounds(10, 10, 300, 25);
        pnlAero.add(lblTitAero);

        lblNivelAero = new JLabel("Nível da Estrutura: 1");
        lblNivelAero.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelAero.setBounds(20, 60, 200, 20);
        pnlAero.add(lblNivelAero);

        JLabel lblStaffA = new JLabel("Eficiência do Staff:");
        lblStaffA.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffA.setBounds(20, 90, 150, 20);
        pnlAero.add(lblStaffA);

        barStaffAero = new JProgressBar();
        barStaffAero.setMaximum(10);
        barStaffAero.setForeground(new Color(30, 144, 255));
        barStaffAero.setBounds(150, 90, 200, 20);
        pnlAero.add(barStaffAero);

        lblValorStaffAero = new JLabel("1/10");
        lblValorStaffAero.setBounds(360, 90, 50, 20);
        pnlAero.add(lblValorStaffAero);

        btnContratarAero = new JButton("Contratar (+€0.5M)");
        btnContratarAero.setBackground(new Color(173, 216, 230));
        btnContratarAero.setBounds(20, 130, 180, 30);
        btnContratarAero.addActionListener(e -> acaoContratarStaff("aero"));
        pnlAero.add(btnContratarAero);

        btnEvoluirAero = new JButton("Evoluir Nível");
        btnEvoluirAero.setBackground(new Color(255, 215, 0));
        btnEvoluirAero.setBounds(210, 130, 180, 30);
        btnEvoluirAero.addActionListener(e -> acaoEvoluirNivel("aero"));
        pnlAero.add(btnEvoluirAero);

        // =================================================================
        // --- PAINEL CHASSI (Explícito para o Editor) ---
        // =================================================================
        JPanel pnlChassi = new JPanel();
        pnlChassi.setLayout(null);
        pnlChassi.setBackground(new Color(250, 250, 250));
        pnlChassi.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlChassi.setBounds(40, 300, 420, 180);
        contentPane.add(pnlChassi);

        JLabel lblTitChassi = new JLabel("CHASSI & SUSPENSÃO");
        lblTitChassi.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitChassi.setBounds(10, 10, 300, 25);
        pnlChassi.add(lblTitChassi);

        lblNivelChassi = new JLabel("Nível da Estrutura: 1");
        lblNivelChassi.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelChassi.setBounds(20, 60, 200, 20);
        pnlChassi.add(lblNivelChassi);

        JLabel lblStaffC = new JLabel("Eficiência do Staff:");
        lblStaffC.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffC.setBounds(20, 90, 150, 20);
        pnlChassi.add(lblStaffC);

        barStaffChassi = new JProgressBar();
        barStaffChassi.setMaximum(10);
        barStaffChassi.setForeground(new Color(255, 140, 0));
        barStaffChassi.setBounds(150, 90, 200, 20);
        pnlChassi.add(barStaffChassi);

        lblValorStaffChassi = new JLabel("1/10");
        lblValorStaffChassi.setBounds(360, 90, 50, 20);
        pnlChassi.add(lblValorStaffChassi);

        btnContratarChassi = new JButton("Contratar (+€0.5M)");
        btnContratarChassi.setBackground(new Color(173, 216, 230));
        btnContratarChassi.setBounds(20, 130, 180, 30);
        btnContratarChassi.addActionListener(e -> acaoContratarStaff("chassi"));
        pnlChassi.add(btnContratarChassi);

        btnEvoluirChassi = new JButton("Evoluir Nível");
        btnEvoluirChassi.setBackground(new Color(255, 215, 0));
        btnEvoluirChassi.setBounds(210, 130, 180, 30);
        btnEvoluirChassi.addActionListener(e -> acaoEvoluirNivel("chassi"));
        pnlChassi.add(btnEvoluirChassi);

        // =================================================================
        // --- PAINEL CONFIABILIDADE (Explícito para o Editor) ---
        // =================================================================
        JPanel pnlConf = new JPanel();
        pnlConf.setLayout(null);
        pnlConf.setBackground(new Color(250, 250, 250));
        pnlConf.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnlConf.setBounds(500, 300, 420, 180);
        contentPane.add(pnlConf);

        JLabel lblTitConf = new JLabel("CONFIABILIDADE");
        lblTitConf.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lblTitConf.setBounds(10, 10, 300, 25);
        pnlConf.add(lblTitConf);

        lblNivelConf = new JLabel("Nível da Estrutura: 1");
        lblNivelConf.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblNivelConf.setBounds(20, 60, 200, 20);
        pnlConf.add(lblNivelConf);

        JLabel lblStaffCo = new JLabel("Eficiência do Staff:");
        lblStaffCo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblStaffCo.setBounds(20, 90, 150, 20);
        pnlConf.add(lblStaffCo);

        barStaffConf = new JProgressBar();
        barStaffConf.setMaximum(10);
        barStaffConf.setForeground(new Color(128, 0, 128));
        barStaffConf.setBounds(150, 90, 200, 20);
        pnlConf.add(barStaffConf);

        lblValorStaffConf = new JLabel("1/10");
        lblValorStaffConf.setBounds(360, 90, 50, 20);
        pnlConf.add(lblValorStaffConf);

        btnContratarConf = new JButton("Contratar (+€0.5M)");
        btnContratarConf.setBackground(new Color(173, 216, 230));
        btnContratarConf.setBounds(20, 130, 180, 30);
        btnContratarConf.addActionListener(e -> acaoContratarStaff("confiabilidade"));
        pnlConf.add(btnContratarConf);

        btnEvoluirConf = new JButton("Evoluir Nível");
        btnEvoluirConf.setBackground(new Color(255, 215, 0));
        btnEvoluirConf.setBounds(210, 130, 180, 30);
        btnEvoluirConf.addActionListener(e -> acaoEvoluirNivel("confiabilidade"));
        pnlConf.add(btnEvoluirConf);

        // --- RODAPÉ ---
        JButton btnVoltar = new JButton("VOLTAR PARA O MENU");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        btnVoltar.setBounds(350, 600, 300, 40);
        // Como é JDialog, dispose() apenas fecha a janela da fábrica, revelando a principal que estava atrás
        btnVoltar.addActionListener(e -> dispose()); 
        contentPane.add(btnVoltar);

        // Atualiza os dados iniciais
        atualizarInterface();
    }

    // --- LÓGICA DO JOGO ---

    private void acaoContratarStaff(String setor) {
        if (equipe.getSaldoFinanceiro() < CUSTO_STAFF) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente! Custo: €" + CUSTO_STAFF + "M", "Erro", JOptionPane.ERROR_MESSAGE);
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
            equipe.pagarDespesa(CUSTO_STAFF);
            atualizarInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Staff já está no máximo para este nível (10/10)!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void acaoEvoluirNivel(String setor) {
        int nivelAtual = 0;
        switch (setor) {
            case "motor": nivelAtual = equipe.getNivelMotor(); break;
            case "aero": nivelAtual = equipe.getNivelAero(); break;
            case "chassi": nivelAtual = equipe.getNivelChassi(); break;
            case "confiabilidade": nivelAtual = equipe.getNivelConfiabilidade(); break;
        }

        double custoEvolucao = nivelAtual * CUSTO_BASE_EVOLUCAO; 

        if (equipe.getSaldoFinanceiro() < custoEvolucao) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente! Custo: €" + custoEvolucao + "M", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int resposta = JOptionPane.showConfirmDialog(this, 
            "Evoluir " + setor.toUpperCase() + " para o Nível " + (nivelAtual + 1) + "?\n" +
            "Custo: €" + custoEvolucao + "M\n" +
            "AVISO: O Staff será resetado para 1 para aprender a nova tecnologia.",
            "Confirmar Evolução", JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            boolean sucesso = false;
            switch (setor) {
                case "motor": sucesso = equipe.subirNivelMotor(); break;
                case "aero": sucesso = equipe.subirNivelAero(); break;
                case "chassi": sucesso = equipe.subirNivelChassi(); break;
                case "confiabilidade": sucesso = equipe.subirNivelConfiabilidade(); break;
            }

            if (sucesso) {
                equipe.pagarDespesa(custoEvolucao);
                JOptionPane.showMessageDialog(this, "Evolução Concluída! Fábrica aprimorada.");
                atualizarInterface();
            } else {
                JOptionPane.showMessageDialog(this, "A Fábrica já está no nível máximo (5)!", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    private void atualizarInterface() {
        lblSaldo.setText(String.format("€ %.1f M", equipe.getSaldoFinanceiro()));
        
        // Motor
        lblNivelMotor.setText("Nível da Estrutura: " + equipe.getNivelMotor() + "/5");
        barStaffMotor.setValue(equipe.getStaffMotor());
        lblValorStaffMotor.setText(equipe.getStaffMotor() + "/10");
        double custoUpMotor = equipe.getNivelMotor() * CUSTO_BASE_EVOLUCAO;
        btnEvoluirMotor.setText("Evoluir (€" + custoUpMotor + "M)");
        if(equipe.getNivelMotor() >= 5) btnEvoluirMotor.setEnabled(false);

        // Aero
        lblNivelAero.setText("Nível da Estrutura: " + equipe.getNivelAero() + "/5");
        barStaffAero.setValue(equipe.getStaffAero());
        lblValorStaffAero.setText(equipe.getStaffAero() + "/10");
        double custoUpAero = equipe.getNivelAero() * CUSTO_BASE_EVOLUCAO;
        btnEvoluirAero.setText("Evoluir (€" + custoUpAero + "M)");
        if(equipe.getNivelAero() >= 5) btnEvoluirAero.setEnabled(false);

        // Chassi
        lblNivelChassi.setText("Nível da Estrutura: " + equipe.getNivelChassi() + "/5");
        barStaffChassi.setValue(equipe.getStaffChassi());
        lblValorStaffChassi.setText(equipe.getStaffChassi() + "/10");
        double custoUpChassi = equipe.getNivelChassi() * CUSTO_BASE_EVOLUCAO;
        btnEvoluirChassi.setText("Evoluir (€" + custoUpChassi + "M)");
        if(equipe.getNivelChassi() >= 5) btnEvoluirChassi.setEnabled(false);

        // Confiabilidade
        lblNivelConf.setText("Nível da Estrutura: " + equipe.getNivelConfiabilidade() + "/5");
        barStaffConf.setValue(equipe.getStaffConfiabilidade());
        lblValorStaffConf.setText(equipe.getStaffConfiabilidade() + "/10");
        double custoUpConf = equipe.getNivelConfiabilidade() * CUSTO_BASE_EVOLUCAO;
        btnEvoluirConf.setText("Evoluir (€" + custoUpConf + "M)");
        if(equipe.getNivelConfiabilidade() >= 5) btnEvoluirConf.setEnabled(false);
    }
}
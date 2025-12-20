package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils; // Import necessário para o ícone da janela
import dados.DadosDoJogo;
import modelos.ConfiguracaoEconomia;
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
    private ConfiguracaoEconomia config; 

    // --- Componentes Globais ---
    private JLabel lblSaldo;
    private JTable tabelaResumo;
    
    // --- MOTOR ---
    private JLabel lblNivelMotor, lblPerfMotor, lblValorStaffMotor;
    private JProgressBar barStaffMotor;
    private JButton btnContratarMotor, btnDemitirMotor, btnEvoluirMotor, btnDowngradeMotor;

    // --- AERO ---
    private JLabel lblNivelAero, lblPerfAero, lblValorStaffAero;
    private JProgressBar barStaffAero;
    private JButton btnContratarAero, btnDemitirAero, btnEvoluirAero, btnDowngradeAero;

    // --- CHASSI ---
    private JLabel lblNivelChassi, lblPerfChassi, lblValorStaffChassi;
    private JProgressBar barStaffChassi;
    private JButton btnContratarChassi, btnDemitirChassi, btnEvoluirChassi, btnDowngradeChassi;

    // --- CONFIABILIDADE ---
    private JLabel lblNivelConf, lblPerfConf, lblValorStaffConf;
    private JProgressBar barStaffConf;
    private JButton btnContratarConf, btnDemitirConf, btnEvoluirConf, btnDowngradeConf;

    public TelaFabrica(DadosDoJogo dados) {
        this.dadosDoJogo = dados;
        this.equipe = dados.getEquipeDoJogador();
        
        if (dados.getConfigEconomia() != null) {
            this.config = dados.getConfigEconomia();
        } else {
            this.config = new ConfiguracaoEconomia();
            this.config.setCustoContratacao(0.5);
            this.config.setReembolsoDemissao(0.25);
            this.config.setSalarioMensalPorPessoa(0.01);
            this.config.setCustoBaseEvolucao(5.0);
            this.config.setManutencaoPorNivel(0.1);
        }

        setModal(true);
        setTitle("Grid Boss - Fábrica");
        
        try {
            java.awt.Image icon = FlatSVGUtils.svg2image("/resource/Icone.svg", 32, 32);
            if (icon != null) setIconImage(icon);
        } catch (Exception e) {
        }
        
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
        lblNomeEquipe.setBounds(156, 545, 504, 25);
        contentPane.add(lblNomeEquipe);

        // Strings Dinâmicas de Preço
        String txtSalario = String.format("€%.0fk/mês por pessoa", config.getSalarioMensalPorPessoa() * 1000);
        String txtContratar = String.format("Contratar (-€%.0fk)", config.getCustoContratacao() * 1000);
        String txtDemitir = String.format("Demitir (+€%.0fk)", config.getReembolsoDemissao() * 1000);

        // =================================================================
        // --- PAINEL MOTOR ---
        // =================================================================
        JPanel pnlMotor = criarPainelBase(10, 62, "DEPARTAMENTO DE MOTOR");
        contentPane.add(pnlMotor);
        
        lblNivelMotor = criarLabelNivel(pnlMotor);
        lblPerfMotor = criarLabelPerf(pnlMotor);
        criarLabelsStaff(pnlMotor);
        
        barStaffMotor = criarProgressBar(pnlMotor, new Color(50, 205, 50));
        lblValorStaffMotor = criarLabelValorStaff(pnlMotor);
        
        btnDemitirMotor = criarBotaoDemitir(pnlMotor, txtDemitir, e -> acaoDemitir("motor"));
        criarLabelSalario(pnlMotor, txtSalario);
        btnContratarMotor = criarBotaoContratar(pnlMotor, txtContratar, e -> acaoContratarStaff("motor"));
        
        btnDowngradeMotor = criarBotaoDowngrade(pnlMotor, e -> acaoDowngradeNivel("motor"));
        btnEvoluirMotor = criarBotaoEvoluir(pnlMotor, e -> acaoEvoluirNivel("motor"));

        // =================================================================
        // --- PAINEL AERO ---
        // =================================================================
        JPanel pnlAero = criarPainelBase(500, 62, "AERODINÂMICA");
        contentPane.add(pnlAero);
        
        lblNivelAero = criarLabelNivel(pnlAero);
        lblPerfAero = criarLabelPerf(pnlAero);
        criarLabelsStaff(pnlAero);
        
        barStaffAero = criarProgressBar(pnlAero, new Color(30, 144, 255));
        lblValorStaffAero = criarLabelValorStaff(pnlAero);
        
        btnDemitirAero = criarBotaoDemitir(pnlAero, txtDemitir, e -> acaoDemitir("aero"));
        criarLabelSalario(pnlAero, txtSalario);
        btnContratarAero = criarBotaoContratar(pnlAero, txtContratar, e -> acaoContratarStaff("aero"));
        
        btnDowngradeAero = criarBotaoDowngrade(pnlAero, e -> acaoDowngradeNivel("aero"));
        btnEvoluirAero = criarBotaoEvoluir(pnlAero, e -> acaoEvoluirNivel("aero"));

        // =================================================================
        // --- PAINEL CHASSI ---
        // =================================================================
        JPanel pnlChassi = criarPainelBase(10, 233, "CHASSI & SUSPENSÃO");
        contentPane.add(pnlChassi);
        
        lblNivelChassi = criarLabelNivel(pnlChassi);
        lblPerfChassi = criarLabelPerf(pnlChassi);
        criarLabelsStaff(pnlChassi);
        
        barStaffChassi = criarProgressBar(pnlChassi, new Color(255, 140, 0));
        lblValorStaffChassi = criarLabelValorStaff(pnlChassi);
        
        btnDemitirChassi = criarBotaoDemitir(pnlChassi, txtDemitir, e -> acaoDemitir("chassi"));
        criarLabelSalario(pnlChassi, txtSalario);
        btnContratarChassi = criarBotaoContratar(pnlChassi, txtContratar, e -> acaoContratarStaff("chassi"));
        
        btnDowngradeChassi = criarBotaoDowngrade(pnlChassi, e -> acaoDowngradeNivel("chassi"));
        btnEvoluirChassi = criarBotaoEvoluir(pnlChassi, e -> acaoEvoluirNivel("chassi"));

        // =================================================================
        // --- PAINEL CONFIABILIDADE ---
        // =================================================================
        JPanel pnlConf = criarPainelBase(500, 233, "CONFIABILIDADE");
        contentPane.add(pnlConf);
        
        lblNivelConf = criarLabelNivel(pnlConf);
        lblPerfConf = criarLabelPerf(pnlConf);
        criarLabelsStaff(pnlConf);
        
        barStaffConf = criarProgressBar(pnlConf, new Color(128, 0, 128));
        lblValorStaffConf = criarLabelValorStaff(pnlConf);
        
        btnDemitirConf = criarBotaoDemitir(pnlConf, txtDemitir, e -> acaoDemitir("confiabilidade"));
        criarLabelSalario(pnlConf, txtSalario);
        btnContratarConf = criarBotaoContratar(pnlConf, txtContratar, e -> acaoContratarStaff("confiabilidade"));
        
        btnDowngradeConf = criarBotaoDowngrade(pnlConf, e -> acaoDowngradeNivel("confiabilidade"));
        btnEvoluirConf = criarBotaoEvoluir(pnlConf, e -> acaoEvoluirNivel("confiabilidade"));

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
        lblSaldo.setBounds(51, 545, 95, 25);
        contentPane.add(lblSaldo);
        lblSaldo.setHorizontalAlignment(SwingConstants.LEFT);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 16));
        
        JLabel lblIconeMoney = new JLabel("");
        lblIconeMoney.setHorizontalAlignment(SwingConstants.CENTER);
        lblIconeMoney.setBounds(10, 544, 35, 25);
        contentPane.add(lblIconeMoney);
        
        // --- ALTERAÇÃO 2: ÍCONE DO EURO (CORRIGIDO) ---
        lblIconeMoney.setIcon(new FlatSVGIcon("resource/Icone Euro.svg", 24, 24)); 

        atualizarInterface();
    }

    // --- MÉTODOS AUXILIARES DE CONSTRUÇÃO DE UI (Para reduzir repetição) ---
    
    private JPanel criarPainelBase(int x, int y, String titulo) {
        JPanel pnl = new JPanel();
        pnl.setLayout(null);
        pnl.setBackground(new Color(250, 250, 250));
        pnl.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
        pnl.setBounds(x, y, 470, 160);
        
        JLabel lbl = new JLabel(titulo);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        lbl.setBounds(20, 10, 440, 25);
        pnl.add(lbl);
        return pnl;
    }
    
    private JLabel criarLabelNivel(JPanel pnl) {
        JLabel lbl = new JLabel("Nível: 1");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lbl.setBounds(320, 46, 140, 20);
        pnl.add(lbl);
        return lbl;
    }
    
    private JLabel criarLabelPerf(JPanel pnl) {
        JLabel lbl = new JLabel("Performance: --");
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setForeground(new Color(0, 100, 0));
        lbl.setFont(new Font("Arial", Font.BOLD, 12));
        lbl.setBounds(320, 132, 140, 25);
        pnl.add(lbl);
        return lbl;
    }
    
    private void criarLabelsStaff(JPanel pnl) {
        JLabel lbl1 = new JLabel("Equipe Técnica:");
        lbl1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lbl1.setBounds(10, 77, 105, 20);
        pnl.add(lbl1);
        
        JLabel lbl2 = new JLabel("Funcionários");
        lbl2.setHorizontalAlignment(SwingConstants.CENTER);
        lbl2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lbl2.setBounds(320, 77, 90, 20);
        pnl.add(lbl2);
    }
    
    private JProgressBar criarProgressBar(JPanel pnl, Color cor) {
        JProgressBar bar = new JProgressBar();
        bar.setMaximum(10);
        bar.setForeground(cor);
        bar.setBounds(125, 77, 185, 20);
        pnl.add(bar);
        return bar;
    }
    
    private JLabel criarLabelValorStaff(JPanel pnl) {
        JLabel lbl = new JLabel("1/10");
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl.setBounds(420, 77, 40, 20);
        pnl.add(lbl);
        return lbl;
    }
    
    private JButton criarBotaoDemitir(JPanel pnl, String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(255, 55, 55));
        btn.setMargin(new Insets(2, 2, 2, 2));
        btn.setBounds(10, 108, 140, 20);
        btn.addActionListener(action);
        pnl.add(btn);
        return btn;
    }
    
    private JButton criarBotaoContratar(JPanel pnl, String text, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 168, 0));
        btn.setBounds(170, 108, 140, 20);
        btn.addActionListener(action);
        pnl.add(btn);
        return btn;
    }
    
    private void criarLabelSalario(JPanel pnl, String text) {
        JLabel lbl = new JLabel(text);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        lbl.setForeground(Color.DARK_GRAY);
        lbl.setFont(new Font("Arial", Font.PLAIN, 11));
        lbl.setBounds(320, 108, 140, 15);
        pnl.add(lbl);
    }
    
    private JButton criarBotaoDowngrade(JPanel pnl, java.awt.event.ActionListener action) {
        JButton btn = new JButton("Descer Nível");
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(255, 55, 55)); 
        btn.setBounds(10, 46, 140, 20);
        btn.addActionListener(action);
        pnl.add(btn);
        return btn;
    }
    
    private JButton criarBotaoEvoluir(JPanel pnl, java.awt.event.ActionListener action) {
        JButton btn = new JButton("Evoluir Nível");
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(0, 168, 0));
        btn.setBounds(170, 46, 140, 20);
        btn.addActionListener(action);
        pnl.add(btn);
        return btn;
    }

    // --- LÓGICA DO JOGO ---

    private void acaoContratarStaff(String setor) {
        if (equipe.getSaldoFinanceiro() < config.getCustoContratacao()) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente! Custo: €" + config.getCustoContratacao() + "M", "Erro", JOptionPane.ERROR_MESSAGE);
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
            equipe.pagarDespesa(config.getCustoContratacao());
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
            equipe.receberReceita(config.getReembolsoDemissao());
            atualizarInterface();
        } else {
            JOptionPane.showMessageDialog(this, "Mínimo de 1 funcionário exigido!", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void acaoEvoluirNivel(String setor) {
        int nivel = getNivelSetor(setor);
        int nivelParaCalculo = (nivel < 1) ? 1 : nivel;
        double custo = nivelParaCalculo * config.getCustoBaseEvolucao();
        
        if (equipe.getSaldoFinanceiro() < custo) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente! Custo: €" + custo + "M", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int resp = JOptionPane.showConfirmDialog(this, 
            "Evoluir " + setor.toUpperCase() + " para Nível " + (nivel+1) + "?\nCusto: €" + custo + "M", 
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
            "Economia mensal na manutenção.\n" +
            "AVISO: O limite de staff cairá.", 
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
        lblSaldo.setText(String.format("€ %.2f M", equipe.getSaldoFinanceiro()));
        
        atualizarPainel(lblNivelMotor, lblPerfMotor, barStaffMotor, lblValorStaffMotor, btnEvoluirMotor, btnDowngradeMotor, btnContratarMotor, btnDemitirMotor,
                equipe.getNivelMotor(), equipe.getStaffMotor(), equipe.getForcaMotorCalculada());
                
        atualizarPainel(lblNivelAero, lblPerfAero, barStaffAero, lblValorStaffAero, btnEvoluirAero, btnDowngradeAero, btnContratarAero, btnDemitirAero,
                equipe.getNivelAero(), equipe.getStaffAero(), equipe.getForcaAeroCalculada());
                
        atualizarPainel(lblNivelChassi, lblPerfChassi, barStaffChassi, lblValorStaffChassi, btnEvoluirChassi, btnDowngradeChassi, btnContratarChassi, btnDemitirChassi,
                equipe.getNivelChassi(), equipe.getStaffChassi(), equipe.getForcaChassiCalculada());
                
        atualizarPainel(lblNivelConf, lblPerfConf, barStaffConf, lblValorStaffConf, btnEvoluirConf, btnDowngradeConf, btnContratarConf, btnDemitirConf,
                equipe.getNivelConfiabilidade(), equipe.getStaffConfiabilidade(), equipe.getForcaConfiabilidadeCalculada());
                
        // Atualizar Tabela Resumo
        DefaultTableModel model = (DefaultTableModel) tabelaResumo.getModel();
        model.setRowCount(0);
        
        double totalMensal = 0;
        totalMensal += adicionarLinhaTabela(model, "Motor", equipe.getNivelMotor(), equipe.getStaffMotor(), equipe.getForcaMotorCalculada());
        totalMensal += adicionarLinhaTabela(model, "Aerodinâmica", equipe.getNivelAero(), equipe.getStaffAero(), equipe.getForcaAeroCalculada());
        totalMensal += adicionarLinhaTabela(model, "Chassi", equipe.getNivelChassi(), equipe.getStaffChassi(), equipe.getForcaChassiCalculada());
        totalMensal += adicionarLinhaTabela(model, "Confiabilidade", equipe.getNivelConfiabilidade(), equipe.getStaffConfiabilidade(), equipe.getForcaConfiabilidadeCalculada());
        
        // Linha Total
        double totalPerf = (equipe.getForcaMotorCalculada() + equipe.getForcaAeroCalculada() + equipe.getForcaChassiCalculada() + equipe.getForcaConfiabilidadeCalculada()) / 4;
        
        model.addRow(new Object[] {
            "MÉDIA / TOTAL",
            "-",
            "-",
            String.format("%.1f pts", totalPerf),
            String.format("€ %.2f M/mês", totalMensal)
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
        double custo = nivelParaCalculo * config.getCustoBaseEvolucao();
        
        btnUp.setText("Evoluir Nível (€" + (int)custo + "M)");
        btnUp.setEnabled(nivel < 5);
        btnDown.setEnabled(nivel > 1);
        
        btnHire.setEnabled(staff < limite); 
        btnFire.setEnabled(staff > 1);
    }
    
    private double adicionarLinhaTabela(DefaultTableModel model, String nome, int nivel, int staff, double forca) {
        // Cálculo Dinâmico baseado no JSON
        double custoManutencao = (nivel * config.getManutencaoPorNivel());
        double custoSalarios = (staff * config.getSalarioMensalPorPessoa());
        double custoTotal = custoManutencao + custoSalarios;
        
        model.addRow(new Object[] {
            nome,
            nivel + " (" + (nivel*20) + " pts)",
            staff + " (+" + (staff*2) + " pts)",
            (int)forca + " pts",
            String.format("€ %.2f M", custoTotal)
        });
        
        return custoTotal;
    }
}
package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import dados.CarregadorJSON;
import dados.DadosDoJogo;
import modelos.Equipe;
import modelos.Motor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// ALTERAÇÃO 1: Estende JDialog em vez de JFrame
public class TelaMotor extends JDialog {

    private JPanel contentPane;
    private DadosDoJogo dados;
    private Equipe equipeJogador;
    
    // --- Componentes de Dados ---
    private List<Motor> listaDeMotoresDisponiveis;
    private Motor motorSelecionado;
    private boolean isEquipeFabrica = false; // Controle da regra de 50%
    
    // --- Componentes Visuais (Esquerda) ---
    private JList<Motor> listMotores;
    private DefaultListModel<Motor> listModel;
    
    // --- Componentes Visuais (Direita - Detalhes) ---
    private JLabel lblLogoFabricante;
    private JLabel lblBandeiraPais;
    private JLabel lblNomeMotor;
    private JLabel lblPrecoBase;
    
    // Stats
    private JProgressBar barPotencia;
    private JProgressBar barDirigibilidade;
    private JProgressBar barConfiabilidade;
    
    // Novos Labels de Valor (Ex: "8/10")
    private JLabel lblValPot;
    private JLabel lblValDir;
    private JLabel lblValConf;
    
    private CircularOverallPanel panelOverall; 
    
    // Contrato
    private JRadioButton rd1Ano, rd2Anos, rd3Anos;
    private ButtonGroup grupoAnos;
    private JLabel lblCustoFinal;
    private JLabel lblMultaRescisao;
    
    // Ações
    private JButton btnAssinarProxima;
    private JButton btnTrocarAgora;
    private JLabel lblAvisoFabrica; 

    public TelaMotor(DadosDoJogo dados) {
        this.dados = dados;
        this.equipeJogador = dados.getEquipeDoJogador();
        
        setTitle("Fornecedores de Unidade de Potência");
        
        // ALTERAÇÃO 2: Configuração Modal e Utilitária
        setModal(true);
       // setType(Type.UTILITY);
        
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        
        // Ícone da janela (Padrão)
        try {
            java.awt.Image icon = FlatSVGUtils.svg2image("/resource/Icone.svg", 32, 32);
            if (icon != null) setIconImage(icon);
        } catch (Exception e) {
        }
        
        // --- CARREGAMENTO DOS ARQUIVOS ---
        this.listaDeMotoresDisponiveis = new ArrayList<>();
        
        try {
            // 1. Carrega o arquivo OFICIAL (motores.json)
            List<Motor> listaPrincipal = CarregadorJSON.carregarMotores(dados.getCategoriaKey(), dados.getAnoAtual());
            if (listaPrincipal != null) {
                this.listaDeMotoresDisponiveis.addAll(listaPrincipal);
            }
            
            // 2. Monta o caminho para o arquivo EXTRA na mesma pasta do mod
            String caminhoMod = "mods/" + dados.getCategoriaKey().toLowerCase() + "_" + dados.getAnoAtual() + "/";
            String caminhoExtra = caminhoMod + "motores_extra.json";
            
            // Carrega o arquivo EXTRA
            List<Motor> listaExtra = CarregadorJSON.carregarMotoresPorArquivo(caminhoExtra);
            if (listaExtra != null) {
                this.listaDeMotoresDisponiveis.addAll(listaExtra);
            }
            
        } catch (Exception e) {
            System.err.println("Erro crítico ao carregar motores: " + e.getMessage());
        }

        // --- ORDENAÇÃO POR OVERALL ---
        this.listaDeMotoresDisponiveis.sort((m1, m2) -> {
            double overall1 = (m1.getPotencia() + m1.getDirigibilidade() + m1.getDurabilidade());
            double overall2 = (m2.getPotencia() + m2.getDirigibilidade() + m2.getDurabilidade());
            return Double.compare(overall2, overall1); // Decrescente (maior para menor)
        });

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        inicializarComponentes();
        verificarRegrasDeFabrica();
    }

    private void inicializarComponentes() {
        // === PAINEL ESQUERDO (LISTA) ===
        JPanel panelLista = new JPanel();
        panelLista.setBorder(new TitledBorder(null, "Fornecedores Disponíveis", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLista.setBounds(10, 11, 250, 539);
        contentPane.add(panelLista);
        panelLista.setLayout(new BorderLayout(0, 0));
        
        listModel = new DefaultListModel<>();
        for (Motor m : listaDeMotoresDisponiveis) {
            listModel.addElement(m);
        }
        
        listMotores = new JList<>(listModel);
        listMotores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listMotores.setCellRenderer(new MotorListRenderer());
        listMotores.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarMotor(listMotores.getSelectedValue());
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(listMotores);
        panelLista.add(scrollPane, BorderLayout.CENTER);
        
        // === PAINEL DIREITO (DETALHES) ===
        JPanel panelDetalhes = new JPanel();
        panelDetalhes.setBackground(Color.WHITE);
        panelDetalhes.setBorder(new TitledBorder(null, "Dados Técnicos & Contrato", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDetalhes.setBounds(270, 11, 604, 539);
        contentPane.add(panelDetalhes);
        panelDetalhes.setLayout(null);
        
        // Cabeçalho
        lblLogoFabricante = new JLabel("");
        lblLogoFabricante.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoFabricante.setBounds(20, 30, 100, 100);
        lblLogoFabricante.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        panelDetalhes.add(lblLogoFabricante);
        
        lblNomeMotor = new JLabel("Selecione um Motor");
        lblNomeMotor.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 22));
        lblNomeMotor.setBounds(140, 40, 350, 30);
        panelDetalhes.add(lblNomeMotor);
        
        lblBandeiraPais = new JLabel("");
        lblBandeiraPais.setBounds(140, 80, 50, 30);
        panelDetalhes.add(lblBandeiraPais);
        
        // --- STATUS (BARRAS + VALORES) ---
        
        // 1. Potência
        JLabel lblPot = new JLabel("Potência");
        lblPot.setBounds(20, 160, 100, 20);
        panelDetalhes.add(lblPot);
        
        barPotencia = criarProgressBar(20, 180);
        panelDetalhes.add(barPotencia);
        
        lblValPot = new JLabel("0/10");
        lblValPot.setFont(new Font("Arial", Font.PLAIN, 10));
        lblValPot.setHorizontalAlignment(SwingConstants.CENTER);
        lblValPot.setBounds(20, 196, 140, 14);
        panelDetalhes.add(lblValPot);
        
        // 2. Dirigibilidade
        JLabel lblDir = new JLabel("Dirigibilidade");
        lblDir.setBounds(180, 160, 100, 20);
        panelDetalhes.add(lblDir);
        
        barDirigibilidade = criarProgressBar(180, 180);
        panelDetalhes.add(barDirigibilidade);
        
        lblValDir = new JLabel("0/10");
        lblValDir.setFont(new Font("Arial", Font.PLAIN, 10));
        lblValDir.setHorizontalAlignment(SwingConstants.CENTER);
        lblValDir.setBounds(180, 196, 140, 14);
        panelDetalhes.add(lblValDir);
        
        // 3. Confiabilidade
        JLabel lblConf = new JLabel("Confiabilidade");
        lblConf.setBounds(340, 160, 100, 20);
        panelDetalhes.add(lblConf);
        
        barConfiabilidade = criarProgressBar(340, 180);
        panelDetalhes.add(barConfiabilidade);
        
        lblValConf = new JLabel("0/10");
        lblValConf.setFont(new Font("Arial", Font.PLAIN, 10));
        lblValConf.setHorizontalAlignment(SwingConstants.CENTER);
        lblValConf.setBounds(340, 196, 140, 14);
        panelDetalhes.add(lblValConf);
        
        // Overall
        panelOverall = new CircularOverallPanel();
        panelOverall.setBounds(500, 150, 80, 80);
        panelOverall.setBackground(Color.WHITE);
        panelDetalhes.add(panelOverall);
        
        JLabel lblTxtOver = new JLabel("OVERALL");
        lblTxtOver.setHorizontalAlignment(SwingConstants.CENTER);
        lblTxtOver.setFont(new Font("Arial", Font.BOLD, 10));
        lblTxtOver.setBounds(500, 235, 80, 14);
        panelDetalhes.add(lblTxtOver);
        
        // === ÁREA DE CONTRATO ===
        JSeparator separator = new JSeparator();
        separator.setBounds(20, 260, 560, 2);
        panelDetalhes.add(separator);
        
        JLabel lblDuracao = new JLabel("Duração do Contrato:");
        lblDuracao.setFont(new Font("Arial", Font.BOLD, 14));
        lblDuracao.setBounds(20, 280, 200, 20);
        panelDetalhes.add(lblDuracao);
        
        rd1Ano = new JRadioButton("1 Ano (Padrão)");
        rd1Ano.setBackground(Color.WHITE);
        rd1Ano.setBounds(20, 310, 120, 23);
        rd1Ano.setSelected(true);
        rd1Ano.addActionListener(e -> atualizarPrecos());
        
        rd2Anos = new JRadioButton("2 Anos (-10%)");
        rd2Anos.setBackground(Color.WHITE);
        rd2Anos.setBounds(150, 310, 120, 23);
        rd2Anos.addActionListener(e -> atualizarPrecos());
        
        rd3Anos = new JRadioButton("3 Anos (-20%)");
        rd3Anos.setBackground(Color.WHITE);
        rd3Anos.setBounds(280, 310, 120, 23);
        rd3Anos.addActionListener(e -> atualizarPrecos());
        
        grupoAnos = new ButtonGroup();
        grupoAnos.add(rd1Ano); grupoAnos.add(rd2Anos); grupoAnos.add(rd3Anos);
        
        panelDetalhes.add(rd1Ano); panelDetalhes.add(rd2Anos); panelDetalhes.add(rd3Anos);
        
        lblPrecoBase = new JLabel("Preço Base: € -- mi");
        lblPrecoBase.setForeground(Color.GRAY);
        lblPrecoBase.setBounds(420, 314, 200, 14); 
        panelDetalhes.add(lblPrecoBase);
        
        // Painel Financeiro Final
        JPanel panelFinanceiro = new JPanel();
        panelFinanceiro.setBackground(new Color(245, 245, 245));
        panelFinanceiro.setBounds(20, 350, 560, 100);
        panelFinanceiro.setLayout(null);
        panelDetalhes.add(panelFinanceiro);
        
        JLabel lblCustoTitulo = new JLabel("CUSTO DO NOVO CONTRATO (ANUAL):");
        lblCustoTitulo.setFont(new Font("Arial", Font.BOLD, 12));
        lblCustoTitulo.setBounds(10, 11, 300, 14);
        panelFinanceiro.add(lblCustoTitulo);
        
        lblCustoFinal = new JLabel("€ 0.0 mi");
        lblCustoFinal.setForeground(new Color(0, 128, 0));
        lblCustoFinal.setFont(new Font("Arial", Font.BOLD, 24));
        lblCustoFinal.setBounds(10, 30, 300, 30);
        panelFinanceiro.add(lblCustoFinal);
        
        lblMultaRescisao = new JLabel("Multa Rescisória (Troca Imediata): € 0.0 mi");
        lblMultaRescisao.setForeground(Color.RED);
        lblMultaRescisao.setFont(new Font("Arial", Font.ITALIC, 12));
        lblMultaRescisao.setBounds(10, 70, 400, 20);
        panelFinanceiro.add(lblMultaRescisao);
        
        // Botões de Ação
        btnAssinarProxima = new JButton("Assinar Próxima Temporada");
        btnAssinarProxima.setBounds(20, 470, 270, 40);
        btnAssinarProxima.addActionListener(e -> acaoAssinarProxima());
        panelDetalhes.add(btnAssinarProxima);
        
        btnTrocarAgora = new JButton("Trocar Agora e Pagar Multa");
        btnTrocarAgora.setBounds(310, 470, 270, 40);
        btnTrocarAgora.setForeground(Color.RED);
        btnTrocarAgora.addActionListener(e -> acaoTrocarAgora());
        panelDetalhes.add(btnTrocarAgora);
        
        lblAvisoFabrica = new JLabel("EQUIPE DE FÁBRICA: CONTRATO VITALÍCIO");
        lblAvisoFabrica.setFont(new Font("Arial", Font.BOLD, 16));
        lblAvisoFabrica.setForeground(Color.RED);
        lblAvisoFabrica.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvisoFabrica.setBounds(20, 470, 560, 40);
        lblAvisoFabrica.setVisible(false);
        lblAvisoFabrica.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        panelDetalhes.add(lblAvisoFabrica);
    }
    
    private JProgressBar criarProgressBar(int x, int y) {
        JProgressBar bar = new JProgressBar(0, 10);
        bar.setBounds(x, y, 140, 15);
        bar.setForeground(new Color(0, 120, 215)); 
        return bar;
    }
    
    // --- LÓGICA ---

    private void verificarRegrasDeFabrica() {
        String nomeEquipe = equipeJogador.getNome().toLowerCase();
        
        // Regra de fábrica
        this.isEquipeFabrica = nomeEquipe.contains("ferrari") || 
                               nomeEquipe.contains("mercedes") || 
                               nomeEquipe.contains("honda") ||
                               nomeEquipe.contains("rbpt") ||
                               nomeEquipe.contains("alpine") || 
                               nomeEquipe.contains("audi");     
        
        if (isEquipeFabrica) {
            listMotores.setEnabled(false);
            btnAssinarProxima.setVisible(false);
            btnTrocarAgora.setVisible(false);
            
            rd1Ano.setEnabled(false); rd2Anos.setEnabled(false); rd3Anos.setEnabled(false);
            rd1Ano.setSelected(true); // Força 1 ano visualmente
            
            lblAvisoFabrica.setVisible(true);
            lblAvisoFabrica.setText("EQUIPE " + equipeJogador.getNome().toUpperCase() + " (FABRICANTE)");
            
            if (equipeJogador.getMotorObjeto() != null) {
                selecionarVisualmenteMotorAtual(equipeJogador.getMotorObjeto().getId());
            }
        }
    }
    
    private void selecionarVisualmenteMotorAtual(String idMotor) {
        for (int i = 0; i < listModel.size(); i++) {
            if (listModel.get(i).getId().equalsIgnoreCase(idMotor)) {
                listMotores.setSelectedIndex(i);
                break;
            }
        }
    }

    private void selecionarMotor(Motor m) {
        if (m == null) return;
        
        this.motorSelecionado = m;
        
        lblNomeMotor.setText(m.getNome());
        
        if (isEquipeFabrica) {
            lblPrecoBase.setText(String.format("Base: € %.1f mi (Subsídio)", m.getPreco()));
        } else {
            lblPrecoBase.setText(String.format("Preço Base: € %.1f mi", m.getPreco()));
        }
        
        carregarImagem(lblLogoFabricante, m.getCaminhoLogo());
        carregarImagem(lblBandeiraPais, m.getCaminhoBandeira());
        
        // Atualiza Barras
        barPotencia.setValue((int)m.getPotencia());
        barDirigibilidade.setValue((int)m.getDirigibilidade());
        barConfiabilidade.setValue((int)m.getDurabilidade());
        
        // Atualiza Textos
        lblValPot.setText((int)m.getPotencia() + "/10");
        lblValDir.setText((int)m.getDirigibilidade() + "/10");
        lblValConf.setText((int)m.getDurabilidade() + "/10");
        
        int overall = calcularOverall(m);
        panelOverall.setScore(overall);
        
        double valorMultaEstimado = 5.0; 
        if (equipeJogador.getMotorObjeto() != null) {
            valorMultaEstimado = equipeJogador.getMotorObjeto().getPreco() * 0.5;
        }
        lblMultaRescisao.setText(String.format("Multa Rescisória (Contrato Atual): € %.1f mi", valorMultaEstimado));
        
        atualizarPrecos();
    }
    
    private int calcularOverall(Motor m) {
        double media = (m.getPotencia() + m.getDirigibilidade() + m.getDurabilidade()) / 3.0;
        return (int) (media * 10.0);
    }
    
    private void atualizarPrecos() {
        if (motorSelecionado == null) return;
        
        double precoBase = motorSelecionado.getPreco();
        double precoFinal;

        if (isEquipeFabrica) {
            precoFinal = precoBase * 0.5;
            lblCustoFinal.setText(String.format("€ %.1f mi (Fixo -50%%)", precoFinal));
        } else {
            double desconto = 0.0;
            if (rd2Anos.isSelected()) desconto = 0.10; 
            else if (rd3Anos.isSelected()) desconto = 0.20; 
            
            precoFinal = precoBase * (1.0 - desconto);
            lblCustoFinal.setText(String.format("€ %.1f mi", precoFinal));
        }
    }
    
    private void acaoTrocarAgora() {
        if (motorSelecionado == null) return;
        
        int confirm = JOptionPane.showConfirmDialog(this, 
                "ATENÇÃO: Isso quebrará seu contrato atual!\n" +
                "Você pagará a multa e o valor do novo motor agora.\n\n" +
                "Deseja prosseguir?", 
                "Troca Imediata", JOptionPane.YES_NO_OPTION);
                
        if (confirm == JOptionPane.YES_OPTION) {
            double multa = (equipeJogador.getMotorObjeto() != null) ? equipeJogador.getMotorObjeto().getPreco() * 0.5 : 0;
            double custoNovo = motorSelecionado.getPreco(); 
            double custoTotal = multa + custoNovo;
            
            if (equipeJogador.getSaldoFinanceiro() >= custoTotal) {
                equipeJogador.pagarDespesa(custoTotal);
                equipeJogador.setMotorObjeto(motorSelecionado);
                equipeJogador.sincronizarDadosParaSalvar();
                
                JOptionPane.showMessageDialog(this, "Motor Trocado com Sucesso!\nCusto Total: € " + custoTotal + " mi");
                dispose(); 
            } else {
                JOptionPane.showMessageDialog(this, "Saldo Insuficiente! Você precisa de € " + custoTotal + " mi.");
            }
        }
    }
    
    private void acaoAssinarProxima() {
        if (motorSelecionado == null) return;
        JOptionPane.showMessageDialog(this, 
                "Pré-Contrato Assinado com " + motorSelecionado.getNome() + "!\n" +
                "Este motor será instalado automaticamente no início da próxima temporada.\n" +
                "(Nenhum valor debitado hoje).");
        dispose();
    }

    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path == null || path.isEmpty()) {
                lbl.setIcon(null);
                return;
            }

            if (!path.startsWith("/")) path = "/" + path;
            if (!path.startsWith("/resource")) path = "/resource" + path;
            path = path.replace("//", "/");

            if (path.toLowerCase().endsWith(".png")) {
                path = path.substring(0, path.length() - 4) + ".svg";
            }
            if (!path.toLowerCase().endsWith(".svg")) {
                path = path + ".svg";
            }

            String svgPath = path.startsWith("/") ? path.substring(1) : path;

            int labelW = lbl.getWidth();
            int labelH = lbl.getHeight();

            if (labelW > 0 && labelH > 0) {
                FlatSVGIcon iconOriginal = new FlatSVGIcon(svgPath);
                
                if (iconOriginal.getIconWidth() <= 0) {
                    lbl.setIcon(null);
                    return;
                }

                float origW = iconOriginal.getIconWidth();
                float origH = iconOriginal.getIconHeight();

                float ratioW = (float) labelW / origW;
                float ratioH = (float) labelH / origH;
                float scale = Math.min(ratioW, ratioH);

                int finalW = Math.round(origW * scale);
                int finalH = Math.round(origH * scale);

                int margem = 2; 
                if (lbl == lblLogoFabricante) margem = 6; 

                finalW = Math.max(1, finalW - margem);
                finalH = Math.max(1, finalH - margem);

                lbl.setIcon(new FlatSVGIcon(svgPath, finalW, finalH));
                
            } else {
                lbl.setIcon(new FlatSVGIcon(svgPath));
            }

        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    // --- RENDERER DA LISTA ---
    class MotorListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Motor) {
                Motor m = (Motor) value;
                
                int ov = calcularOverall(m);
                label.setText(m.getNome() + " (" + ov + ")");
                
                label.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
            return label;
        }
    }
    
    // --- PAINEL CIRCULAR (Gráfico de Overall) ---
    class CircularOverallPanel extends JPanel {
        private int score = 0;
        public void setScore(int s) { 
            this.score = s; 
            repaint(); 
        }
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            
            int w = getWidth();
            int h = getHeight();
            int diametro = Math.min(w, h) - 10;
            int x = (w - diametro) / 2;
            int y = (h - diametro) / 2;
            
            g2.setColor(new Color(230, 230, 230));
            g2.fillOval(x, y, diametro, diametro);
            
            if (score >= 90) g2.setColor(new Color(0, 180, 0));       
            else if (score >= 75) g2.setColor(new Color(100, 200, 0));
            else if (score >= 50) g2.setColor(Color.ORANGE);          
            else g2.setColor(Color.RED);                              
            
            int angulo = (int) ((score / 100.0) * 360);
            g2.fillArc(x, y, diametro, diametro, 90, -angulo); 
            
            int innerD = diametro - 15;
            int innerX = x + (diametro - innerD) / 2;
            int innerY = y + (diametro - innerD) / 2;
            g2.setColor(Color.WHITE);
            g2.fillOval(innerX, innerY, innerD, innerD);
            
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.BOLD, 24));
            
            String texto = String.valueOf(score);
            FontMetrics fm = g2.getFontMetrics();
            int textX = x + (diametro - fm.stringWidth(texto)) / 2;
            int textY = y + ((diametro - fm.getHeight()) / 2) + fm.getAscent();
            
            g2.drawString(texto, textX, textY);
        }
    }
}
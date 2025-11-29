package telas;

import com.formdev.flatlaf.FlatLightLaf;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import servicos.CampeonatoService;
import modelos.TipoPista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JPanel contentPane;
    
    // Dados do Jogo
    private Equipe equipeJogavel;
    private CampeonatoService campeonato; // Controla o calendário

    // --- COMPONENTES DA UI ---
    
    // Cabeçalho
    private JLabel lblNomeEquipe;
    private JLabel lblLogoEquipe;
    private JLabel lblSaldo;
    private JLabel lblDirigente;
    private JLabel lblTemporadaInfo;

    // Painel de Pilotos (Arrays para os 5 slots)
    private JPanel[] panelSlots = new JPanel[5];
    private JLabel[] lblPilotoFuncao = new JLabel[5]; // "Titular" ou "Reserva"
    private JLabel[] lblPilotoNome = new JLabel[5];
    private JLabel[] lblPilotoNumero = new JLabel[5];
    private JLabel[] lblPilotoBandeira = new JLabel[5];
    private JLabel[] lblPilotoOverall = new JLabel[5];

    // Painel da Próxima Corrida
    private JLabel lblNomeGP;
    private JLabel lblImagemPista; // Traçado
    private JLabel lblBandeiraPaisPista;
    private JLabel lblLocalPista;
    private JLabel lblDadosPista1; // Voltas / Distância
    private JLabel lblDadosPista2; // Tipo / Dificuldade
    
    // Botões
    private JButton btnIrParaCorrida;

    /**
     * Construtor Principal
     */
    public TelaPrincipal(Equipe equipe) {
        this.equipeJogavel = equipe;
        
        // Inicializa o Campeonato (Lê o calendário do JSON)
        this.campeonato = new CampeonatoService(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);

        setTitle("Grid Boss - " + equipe.getNome());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 800); // Um pouco maior para caber tudo no responsivo
        setMinimumSize(new Dimension(1024, 768));
        
        // Configuração do Menu
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mnJogo = new JMenu("Jogo");
        menuBar.add(mnJogo);
        JMenuItem mntmSalvar = new JMenuItem("Salvar Progresso");
        mnJogo.add(mntmSalvar);
        JMenuItem mntmSair = new JMenuItem("Sair para Menu");
        mnJogo.add(mntmSair);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(contentPane);
        
        // Layout Responsivo Principal (GridBag)
        contentPane.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // --- 1. CABEÇALHO (Topo) ---
        JPanel panelHeader = criarPainelCabecalho();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Ocupa toda a largura
        gbc.weightx = 1.0;
        gbc.weighty = 0.1; // 10% da altura
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 10, 0);
        contentPane.add(panelHeader, gbc);

        // --- 2. PAINEL ESQUERDO (Pilotos) ---
        JPanel panelPilotos = criarPainelPilotos();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0.4; // 40% da largura
        gbc.weighty = 0.8; // 80% da altura
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 0, 0, 10);
        contentPane.add(panelPilotos, gbc);

        // --- 3. PAINEL DIREITO (Próxima Corrida + Gestão) ---
        JPanel panelDireito = new JPanel(new BorderLayout(0, 10));
        panelDireito.setOpaque(false);
        
        // 3.1 Próxima Corrida
        JPanel panelCorrida = criarPainelProximaCorrida();
        panelDireito.add(panelCorrida, BorderLayout.CENTER);
        
        // 3.2 Botões de Ação (Embaixo)
        JPanel panelBotoes = criarPainelBotoes();
        panelDireito.add(panelBotoes, BorderLayout.SOUTH);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.6; // 60% da largura
        gbc.weighty = 0.8;
        gbc.insets = new Insets(0, 0, 0, 0);
        contentPane.add(panelDireito, gbc);
        
        // --- PREENCHER DADOS ---
        atualizarDados();
    }

    private JPanel criarPainelCabecalho() {
        JPanel panel = new JPanel(new BorderLayout(20, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY));

        // Logo
        lblLogoEquipe = new JLabel();
        lblLogoEquipe.setPreferredSize(new Dimension(120, 80));
        lblLogoEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoEquipe.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel.add(lblLogoEquipe, BorderLayout.WEST);

        // Infos Centrais
        JPanel pCenter = new JPanel(new GridLayout(2, 1));
        pCenter.setOpaque(false);
        
        lblNomeEquipe = new JLabel("Nome da Equipe");
        lblNomeEquipe.setFont(new Font("Berlin Sans FB", Font.BOLD, 28));
        pCenter.add(lblNomeEquipe);
        
        lblDirigente = new JLabel("Chefe de Equipe: " + TelaSelecionarEquipe.nomeDirigente);
        lblDirigente.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        pCenter.add(lblDirigente);
        
        panel.add(pCenter, BorderLayout.CENTER);

        // Infos Financeiras e Data (Direita)
        JPanel pRight = new JPanel(new GridLayout(2, 1));
        pRight.setOpaque(false);
        
        lblSaldo = new JLabel("€ 0.0 M");
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblSaldo.setForeground(new Color(34, 139, 34)); // Verde
        lblSaldo.setHorizontalAlignment(SwingConstants.RIGHT);
        pRight.add(lblSaldo);
        
        lblTemporadaInfo = new JLabel("Temporada " + SessaoJogo.anoSelecionado + " - Etapa 1/24");
        lblTemporadaInfo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblTemporadaInfo.setHorizontalAlignment(SwingConstants.RIGHT);
        pRight.add(lblTemporadaInfo);
        
        panel.add(pRight, BorderLayout.EAST);
        
        return panel;
    }

    private JPanel criarPainelPilotos() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createTitledBorder(null, "PILOTOS & CONTRATOS", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Berlin Sans FB", Font.BOLD, 16)));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Cria os 5 slots fixos
        for (int i = 0; i < 5; i++) {
            JPanel slot = new JPanel(new BorderLayout(10, 5));
            slot.setBackground(Color.WHITE);
            slot.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createEmptyBorder(5, 5, 5, 5),
                    BorderFactory.createLineBorder(Color.LIGHT_GRAY)
            ));
            slot.setMaximumSize(new Dimension(Integer.MAX_VALUE, 85)); // Altura fixa

            // Esquerda: Foto/Bandeira
            lblPilotoBandeira[i] = new JLabel();
            lblPilotoBandeira[i].setPreferredSize(new Dimension(50, 30));
            lblPilotoBandeira[i].setHorizontalAlignment(SwingConstants.CENTER);
            slot.add(lblPilotoBandeira[i], BorderLayout.WEST);

            // Centro: Nome e Info
            JPanel pInfo = new JPanel(new GridLayout(2, 1));
            pInfo.setOpaque(false);
            
            lblPilotoNome[i] = new JLabel("Nome do Piloto");
            lblPilotoNome[i].setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
            
            lblPilotoFuncao[i] = new JLabel("Titular");
            lblPilotoFuncao[i].setFont(new Font("Segoe UI", Font.PLAIN, 12));
            lblPilotoFuncao[i].setForeground(Color.GRAY);
            
            pInfo.add(lblPilotoNome[i]);
            pInfo.add(lblPilotoFuncao[i]);
            slot.add(pInfo, BorderLayout.CENTER);

            // Direita: Número e Overall
            JPanel pStats = new JPanel(new GridLayout(2, 1));
            pStats.setOpaque(false);
            
            lblPilotoNumero[i] = new JLabel("#00");
            lblPilotoNumero[i].setFont(new Font("Impact", Font.PLAIN, 20));
            lblPilotoNumero[i].setHorizontalAlignment(SwingConstants.RIGHT);
            
            lblPilotoOverall[i] = new JLabel("OVR: 00");
            lblPilotoOverall[i].setFont(new Font("Segoe UI", Font.BOLD, 12));
            lblPilotoOverall[i].setHorizontalAlignment(SwingConstants.RIGHT);
            
            pStats.add(lblPilotoNumero[i]);
            pStats.add(lblPilotoOverall[i]);
            slot.add(pStats, BorderLayout.EAST);

            panelSlots[i] = slot;
            panel.add(slot);
            panel.add(Box.createVerticalStrut(10)); // Espaço entre slots
        }

        // Botão Mercado (Opcional)
        JButton btnMercado = new JButton("MERCADO DE PILOTOS");
        btnMercado.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        btnMercado.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue()); // Empurra para baixo
        panel.add(btnMercado);
        panel.add(Box.createVerticalStrut(10));

        return panel;
    }

    private JPanel criarPainelProximaCorrida() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createTitledBorder(null, "PRÓXIMO GRANDE PRÊMIO", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Berlin Sans FB", Font.BOLD, 16)));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);

        // 1. Nome e Bandeira do GP
        JPanel pTitulo = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 0));
        pTitulo.setOpaque(false);
        lblBandeiraPaisPista = new JLabel();
        lblNomeGP = new JLabel("GP NOME");
        lblNomeGP.setFont(new Font("Berlin Sans FB", Font.BOLD, 24));
        pTitulo.add(lblBandeiraPaisPista);
        pTitulo.add(lblNomeGP);

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 1.0;
        panel.add(pTitulo, gbc);

        // 2. Imagem do Traçado (Grande)
        lblImagemPista = new JLabel();
        lblImagemPista.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagemPista.setPreferredSize(new Dimension(400, 250));
        lblImagemPista.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
        
        gbc.gridy = 1; gbc.weighty = 1.0; gbc.fill = GridBagConstraints.BOTH;
        panel.add(lblImagemPista, gbc);

        // 3. Informações Técnicas
        JPanel pDados = new JPanel(new GridLayout(3, 1, 5, 5));
        pDados.setOpaque(false);
        
        lblLocalPista = new JLabel("Local: --");
        lblLocalPista.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblLocalPista.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblDadosPista1 = new JLabel("Voltas: 00 | Distância: 0.0 km");
        lblDadosPista1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDadosPista1.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblDadosPista2 = new JLabel("Tipo: Misto | Dificuldade: 50/100");
        lblDadosPista2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblDadosPista2.setHorizontalAlignment(SwingConstants.CENTER);
        
        pDados.add(lblLocalPista);
        pDados.add(lblDadosPista1);
        pDados.add(lblDadosPista2);

        gbc.gridy = 2; gbc.weighty = 0.0; gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(pDados, gbc);

        return panel;
    }
    
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 20, 0));
        panel.setOpaque(false);
        panel.setPreferredSize(new Dimension(0, 60));

        JButton btnFabrica = new JButton("FÁBRICA & DESENVOLVIMENTO");
        btnFabrica.setFont(new Font("Berlin Sans FB", Font.BOLD, 14));
        
        btnIrParaCorrida = new JButton("IR PARA O FIM DE SEMANA");
        btnIrParaCorrida.setFont(new Font("Berlin Sans FB", Font.BOLD, 16));
        btnIrParaCorrida.setBackground(new Color(34, 139, 34)); // Verde
        btnIrParaCorrida.setForeground(Color.WHITE);
        
        btnIrParaCorrida.addActionListener(e -> irParaCorrida());

        try {
            btnFabrica.setIcon(new ImageIcon(getClass().getResource("/resource/Icone24pxEquipe.png")));
            btnIrParaCorrida.setIcon(new ImageIcon(getClass().getResource("/resource/Icone24pxBandeiraDeChegada.png")));
        } catch (Exception e) {}

        panel.add(btnFabrica);
        panel.add(btnIrParaCorrida);
        
        return panel;
    }

    // --- LÓGICA DE DADOS ---

    private void atualizarDados() {
        // 1. Equipe
        lblNomeEquipe.setText(equipeJogavel.getNome());
        lblSaldo.setText(String.format("€ %.1f M", equipeJogavel.getSaldoFinanceiro()));
        carregarImagem(lblLogoEquipe, equipeJogavel.getCaminhoLogo());

        // 2. Pilotos (Listas)
        List<Piloto> titulares = equipeJogavel.getPilotosTitulares();
        List<Piloto> reservas = equipeJogavel.getPilotosReservas();
        
        for (int i = 0; i < 5; i++) {
            Piloto p = null;
            String funcao = "";
            
            // Lógica de Exibição: Mostra Titulares, depois Reservas
            if (i < titulares.size()) {
                p = titulares.get(i);
                funcao = "Piloto Titular " + (i+1);
            } else if (i < titulares.size() + reservas.size()) {
                p = reservas.get(i - titulares.size());
                funcao = "Piloto Reserva";
            }
            
            if (p != null) {
                panelSlots[i].setVisible(true);
                lblPilotoNome[i].setText(p.getNome());
                lblPilotoFuncao[i].setText(funcao);
                lblPilotoNumero[i].setText("#" + p.getNumero());
                lblPilotoOverall[i].setText("OVR: " + (int)p.getOverall());
                carregarImagem(lblPilotoBandeira[i], "/resource/Bandeira " + p.getNacionalidade() + ".png");
                
                // Destaque para titular vs reserva
                if (funcao.contains("Reserva")) {
                    lblPilotoNome[i].setForeground(Color.GRAY);
                } else {
                    lblPilotoNome[i].setForeground(Color.BLACK);
                }
            } else {
                panelSlots[i].setVisible(false); // Esconde slot vazio
            }
        }

        // 3. Calendário (Próxima Corrida)
        Pista pista = campeonato.getPistaAtual();
        if (pista != null) {
            lblNomeGP.setText(pista.getNome());
            lblLocalPista.setText("Local: " + pista.getPais());
            
            String tipoStr = pista.getTipo().toString().replace("_", " ");
            lblDadosPista1.setText(pista.getQtdVoltas() + " Voltas  |  " + pista.getComprimentoKm() + " km");
            lblDadosPista2.setText(tipoStr + "  |  Dif. Ultrapassagem: " + pista.getDificuldadeUltrapassagem() + "/100");
            
            lblTemporadaInfo.setText("Temporada " + SessaoJogo.anoSelecionado + " - Etapa " + campeonato.getNumeroEtapaAtual() + "/" + campeonato.getTotalEtapas());
            
            carregarImagem(lblImagemPista, pista.getCaminhoTracado());
            carregarImagem(lblBandeiraPaisPista, pista.getCaminhoBandeira());
        } else {
            lblNomeGP.setText("TEMPORADA ENCERRADA");
            btnIrParaCorrida.setEnabled(false);
        }
    }

    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path != null && !path.isEmpty()) {
                if (!path.startsWith("/")) path = "/" + path;
                if (!path.startsWith("/resource")) path = "/resource" + path;
                path = path.replace("//", "/");
                
                ImageIcon icon = new ImageIcon(getClass().getResource(path));
                // Redimensiona bandeirinhas pequenas
                if (path.contains("Bandeira") && icon.getIconWidth() > 50) {
                    Image img = icon.getImage().getScaledInstance(40, 25, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(img);
                }
                // Redimensiona traçados se forem muito grandes
                if (path.contains("Circuito") && icon.getIconWidth() > 400) {
                    Image img = icon.getImage().getScaledInstance(380, 200, Image.SCALE_SMOOTH);
                    icon = new ImageIcon(img);
                }
                
                lbl.setIcon(icon);
            } else {
                lbl.setIcon(null);
            }
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }
    
    private void irParaCorrida() {
        // Aqui abriremos a TelaCorrida (que criaremos depois)
        JOptionPane.showMessageDialog(this, "Carregando simulação para " + lblNomeGP.getText() + "...");
        
        // Lógica futura:
        // TelaCorrida corrida = new TelaCorrida(equipeJogavel, campeonato.getPistaAtual());
        // corrida.setVisible(true);
        // dispose();
    }
}
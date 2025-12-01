package telas;

import com.formdev.flatlaf.FlatLightLaf;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.Pista;
import servicos.CampeonatoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;

public class TelaPrincipal extends JFrame {

    private JPanel contentPane;
    
    // Dados do Jogo
    private Equipe equipeJogavel;
    private CampeonatoService campeonato;

    // --- COMPONENTES GLOBAIS (Precisam ser acessados no atualizarDados) ---
    
    // Vermelho (Equipe)
    private JLabel lblNomeEquipe;
    private JLabel lblSedeText; // Texto da sede
    private JLabel lblMotorText; // Texto do motor
    private JLabel lblLogoEquipe;
    
    // Ícones pequenos do painel vermelho
    private JLabel lblIconePaisEquipe; // Bandeira linha 1
    private JLabel lblIconeBandeiraMotor; // Bandeira linha 2
    private JLabel lblIconeLogoMotor;     // Logo linha 2
    private JLabel lblIconeBandeiraSede;  // Bandeira linha 3
    
    // Cinza (Pilotos)
    private JPanel panelListaPilotos; 
    
    // Verde (Campeonato)
    private JTabbedPane tabCampeonato;
    
    // Azul (Jogador)
    private JLabel lblNomeDirigente, lblSaldo, lblAnoTemporada;
    
    // Preto (Corrida)
    private JLabel lblNomeGP, lblLocalPista, lblDadosPista, lblImagemPista;
    private JLabel lblBandeiraPaisPista;
    
    // Rosa (Ação)
    private JButton btnIrParaCorrida;

    public TelaPrincipal(Equipe equipe) {
        this.equipeJogavel = equipe;
        this.campeonato = new CampeonatoService(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);

        setTitle("Grid Boss - Painel Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(50, 50, 1280, 768);
        setMinimumSize(new Dimension(1024, 700));
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        // Layout Principal: 2 Colunas
        contentPane.setLayout(new GridLayout(1, 2, 10, 0));

        // --- COLUNA ESQUERDA ---
        JPanel colunaEsquerda = new JPanel(new GridBagLayout());
        colunaEsquerda.setOpaque(false);
        GridBagConstraints gbcE = new GridBagConstraints();
        gbcE.fill = GridBagConstraints.BOTH;
        gbcE.weightx = 1.0;
        gbcE.insets = new Insets(0, 0, 5, 0);

        // 1. Vermelho (Equipe)
        JPanel panelVermelho = criarPainelVermelho();
        gbcE.gridy = 0; gbcE.weighty = 0.25; 
        colunaEsquerda.add(panelVermelho, gbcE);

        // 2. Cinza (Pilotos)
        JPanel panelCinza = criarPainelCinza();
        gbcE.gridy = 1; gbcE.weighty = 0.45;
        colunaEsquerda.add(panelCinza, gbcE);

        // 3. Verde (Campeonato)
        JPanel panelVerde = criarPainelVerde();
        gbcE.gridy = 2; gbcE.weighty = 0.30;
        gbcE.insets = new Insets(0, 0, 0, 0);
        colunaEsquerda.add(panelVerde, gbcE);

        contentPane.add(colunaEsquerda);

        // --- COLUNA DIREITA ---
        JPanel colunaDireita = new JPanel(new GridBagLayout());
        colunaDireita.setOpaque(false);
        GridBagConstraints gbcD = new GridBagConstraints();
        gbcD.fill = GridBagConstraints.BOTH;
        gbcD.weightx = 1.0;
        gbcD.insets = new Insets(0, 0, 5, 0);

        // 4. Azul (Jogador)
        JPanel panelAzul = criarPainelAzul();
        gbcD.gridy = 0; gbcD.weighty = 0.15;
        colunaDireita.add(panelAzul, gbcD);

        // 5. Preto (Corrida)
        JPanel panelPreto = criarPainelPreto();
        gbcD.gridy = 1; gbcD.weighty = 0.70;
        colunaDireita.add(panelPreto, gbcD);

        // 6. Rosa (Ação)
        JPanel panelRosa = criarPainelRosa();
        gbcD.gridy = 2; gbcD.weighty = 0.15;
        gbcD.insets = new Insets(0, 0, 0, 0);
        colunaDireita.add(panelRosa, gbcD);

        contentPane.add(colunaDireita);

        // Preenche os dados
        atualizarDadosNaTela();
    }

    // =============================================================================================
    // MÉTODOS DE CRIAÇÃO DOS PAINÉIS
    // =============================================================================================

    /**
     * VERMELHO - Dados da Equipe (Corrigido para evitar NullPointer)
     */
    private JPanel criarPainelVermelho() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 230, 230)); 
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.RED, 1), "DADOS DA EQUIPE", TitledBorder.LEFT, TitledBorder.TOP
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // --- 1. INICIALIZAÇÃO DAS VARIÁVEIS GLOBAIS ---
        // (Isso evita o erro NullPointerException)
        lblLogoEquipe = new JLabel();
        lblLogoEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoEquipe.setPreferredSize(new Dimension(200, 100));
        lblLogoEquipe.setBorder(BorderFactory.createLineBorder(new Color(0,0,0,50)));

        lblNomeEquipe = new JLabel("Nome Equipe");
        lblNomeEquipe.setFont(new Font("Berlin Sans FB", Font.BOLD, 20));
        
        lblMotorText = new JLabel("Motor");
        lblMotorText.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        lblSedeText = new JLabel("Sede");
        lblSedeText.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        // Inicializa os ícones
        lblIconePaisEquipe = new JLabel();
        lblIconeBandeiraMotor = new JLabel();
        lblIconeLogoMotor = new JLabel();
        lblIconeBandeiraSede = new JLabel();

        // --- 2. MONTAGEM DO LAYOUT ---
        
        // Esquerda: Logo Grande
        gbc.gridx = 0; 
        gbc.gridy = 0; 
        gbc.gridheight = 3; 
        gbc.weightx = 0.4;
        panel.add(lblLogoEquipe, gbc);

        // Direita: Painel com 3 linhas
        JPanel pDireita = new JPanel(new GridLayout(3, 1, 0, 5));
        pDireita.setOpaque(false);
        
        // Linha 1: [Bandeira] Nome Equipe
        JPanel pLinha1 = montarLinhaVisual(lblIconePaisEquipe, null, lblNomeEquipe);
        
        // Linha 2: [BandeiraMotor] [LogoMotor] Texto Motor
        JPanel pLinha2 = montarLinhaVisual(lblIconeBandeiraMotor, lblIconeLogoMotor, lblMotorText);
        
        // Linha 3: [BandeiraSede] Texto Sede
        JPanel pLinha3 = montarLinhaVisual(lblIconeBandeiraSede, null, lblSedeText);

        pDireita.add(pLinha1);
        pDireita.add(pLinha2);
        pDireita.add(pLinha3);

        gbc.gridx = 1; 
        gbc.gridy = 0; 
        gbc.gridheight = 3;
        gbc.weightx = 0.6;
        panel.add(pDireita, gbc);

        return panel;
    }

    /**
     * Método Auxiliar para montar visualmente uma linha com ícones e texto
     */
    private JPanel montarLinhaVisual(JLabel icon1, JLabel icon2, JLabel textLabel) {
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        p.setOpaque(false);
        
        // Configura e adiciona Icone 1
        icon1.setPreferredSize(new Dimension(45, 30));
        icon1.setHorizontalAlignment(SwingConstants.CENTER);
        icon1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        p.add(icon1);
        
        // Configura e adiciona Icone 2 (Se existir)
        if (icon2 != null) {
            icon2.setPreferredSize(new Dimension(35, 30));
            icon2.setHorizontalAlignment(SwingConstants.CENTER);
            p.add(icon2);
        }
        
        // Adiciona Texto
        p.add(textLabel);
        
        return p;
    }

    private JPanel criarPainelCinza() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.GRAY, 1), "MEUS PILOTOS", TitledBorder.LEFT, TitledBorder.TOP
        ));

        panelListaPilotos = new JPanel();
        panelListaPilotos.setLayout(new BoxLayout(panelListaPilotos, BoxLayout.Y_AXIS));
        panelListaPilotos.setOpaque(false);

        JScrollPane scroll = new JScrollPane(panelListaPilotos);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.setBorder(null);
        
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarPainelVerde() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(230, 255, 230));
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(34, 139, 34), 1), "CLASSIFICAÇÃO", TitledBorder.LEFT, TitledBorder.TOP
        ));

        tabCampeonato = new JTabbedPane();
        tabCampeonato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        JPanel pPilotos = new JPanel(); pPilotos.setOpaque(false); pPilotos.add(new JLabel("Tabela de Pilotos (Em Breve)"));
        JPanel pEquipes = new JPanel(); pEquipes.setOpaque(false); pEquipes.add(new JLabel("Tabela de Construtores (Em Breve)"));
        
        tabCampeonato.addTab("Pilotos", pPilotos);
        tabCampeonato.addTab("Equipes", pEquipes);
        
        panel.add(tabCampeonato, BorderLayout.CENTER);
        return panel;
    }

    private JPanel criarPainelAzul() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBackground(new Color(230, 240, 255));
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.BLUE, 1), "DADOS DO JOGADOR", TitledBorder.LEFT, TitledBorder.TOP
        ));

        lblNomeDirigente = new JLabel("Dirigente");
        lblNomeDirigente.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        lblNomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblSaldo = new JLabel("€ 0.0 M");
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblSaldo.setForeground(new Color(0, 100, 0));
        lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblAnoTemporada = new JLabel("Temporada 2024");
        lblAnoTemporada.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblAnoTemporada.setHorizontalAlignment(SwingConstants.CENTER);
        
        panel.add(lblNomeDirigente);
        panel.add(lblSaldo);
        panel.add(lblAnoTemporada);
        panel.add(new JLabel("")); 

        return panel;
    }

    private JPanel criarPainelPreto() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        panel.setBackground(Color.WHITE); 
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.BLACK, 2), "PRÓXIMA ETAPA", TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), Color.BLACK
        ));

        JPanel pTopo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pTopo.setOpaque(false);
        lblBandeiraPaisPista = new JLabel();
        lblNomeGP = new JLabel("GP NOME");
        lblNomeGP.setFont(new Font("Berlin Sans FB", Font.BOLD, 22));
        pTopo.add(lblBandeiraPaisPista);
        pTopo.add(lblNomeGP);
        panel.add(pTopo, BorderLayout.NORTH);

        lblImagemPista = new JLabel();
        lblImagemPista.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblImagemPista, BorderLayout.CENTER);

        JPanel pBaixo = new JPanel(new GridLayout(2, 1));
        pBaixo.setOpaque(false);
        pBaixo.setBorder(new EmptyBorder(5, 10, 10, 10));
        
        lblLocalPista = new JLabel("Local: --");
        lblLocalPista.setHorizontalAlignment(SwingConstants.CENTER);
        lblDadosPista = new JLabel("Dados: --");
        lblDadosPista.setHorizontalAlignment(SwingConstants.CENTER);
        
        pBaixo.add(lblLocalPista);
        pBaixo.add(lblDadosPista);
        panel.add(pBaixo, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel criarPainelRosa() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        panel.setBackground(new Color(255, 230, 245));
        panel.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1));

        btnIrParaCorrida = new JButton("IR PARA O FIM DE SEMANA");
        btnIrParaCorrida.setFont(new Font("Berlin Sans FB", Font.BOLD, 18));
        btnIrParaCorrida.setBackground(new Color(34, 139, 34));
        btnIrParaCorrida.setForeground(Color.WHITE);
        btnIrParaCorrida.setFocusPainted(false);
        btnIrParaCorrida.setPreferredSize(new Dimension(300, 50));
        btnIrParaCorrida.addActionListener(e -> irParaCorrida());
        
        JLabel lblLogoGame = new JLabel("GRID BOSS");
        lblLogoGame.setFont(new Font("Impact", Font.ITALIC, 28));
        lblLogoGame.setForeground(Color.DARK_GRAY);

        panel.add(btnIrParaCorrida);
        panel.add(lblLogoGame);

        return panel;
    }

    // =============================================================================================
    // LÓGICA DE DADOS
    // =============================================================================================

    private void atualizarDadosNaTela() {
        // --- 1. VERMELHO (Equipe) ---
        // Atualiza Textos
        lblNomeEquipe.setText(equipeJogavel.getNome());
        lblSedeText.setText("Sede: " + equipeJogavel.getSede());
        lblMotorText.setText("Motor: " + equipeJogavel.getMotor());
        
        // Atualiza Imagens (Logo Grande)
        carregarImagem(lblLogoEquipe, equipeJogavel.getCaminhoLogo(), 180, 100);
        
        // Atualiza Ícones Pequenos (Linhas)
        // Linha 1: Usando bandeira da sede como país da equipe por enquanto
        carregarImagem(lblIconePaisEquipe, equipeJogavel.getCaminhoBandeiraSede(), 40, 25);
        
        // Linha 2: Bandeira e Logo do Motor
        carregarImagem(lblIconeBandeiraMotor, equipeJogavel.getCaminhoBandeiraMotor(), 40, 25);
        carregarImagem(lblIconeLogoMotor, equipeJogavel.getCaminhoLogoMotor(), 30, 30);
        
        // Linha 3: Bandeira Sede
        carregarImagem(lblIconeBandeiraSede, equipeJogavel.getCaminhoBandeiraSede(), 40, 25);

        // --- 2. CINZA (Pilotos) ---
        atualizarListaPilotos();

        // --- 4. AZUL (Jogador) ---
        lblNomeDirigente.setText("Chefe: " + TelaSelecionarEquipe.nomeDirigente);
        lblSaldo.setText(String.format("€ %.1f M", equipeJogavel.getSaldoFinanceiro()));
        lblAnoTemporada.setText("Temporada " + SessaoJogo.anoSelecionado);

        // --- 5. PRETO (Pista) ---
        Pista pista = campeonato.getPistaAtual();
        if (pista != null) {
            lblNomeGP.setText(pista.getNome());
            lblLocalPista.setText(pista.getPais());
            
            String dados = String.format("<html><center>%d Voltas • %.1f km<br>%s</center></html>", 
                    pista.getQtdVoltas(), pista.getComprimentoKm(), pista.getTipo());
            lblDadosPista.setText(dados);
            
            carregarImagem(lblBandeiraPaisPista, pista.getCaminhoBandeira(), 40, 25);
            carregarImagem(lblImagemPista, pista.getCaminhoTracado(), 350, 200);
        } else {
            lblNomeGP.setText("FIM DE TEMPORADA");
            btnIrParaCorrida.setEnabled(false);
        }
    }

    private void atualizarListaPilotos() {
        panelListaPilotos.removeAll(); 
        
        List<Piloto> titulares = equipeJogavel.getPilotosTitulares();
        List<Piloto> reservas = equipeJogavel.getPilotosReservas();
        
        for (Piloto p : titulares) {
            panelListaPilotos.add(criarCardPiloto(p, "Titular", new Color(255, 255, 255)));
            panelListaPilotos.add(Box.createVerticalStrut(5));
        }
        
        for (Piloto p : reservas) {
            panelListaPilotos.add(criarCardPiloto(p, "Reserva", new Color(240, 240, 240))); 
            panelListaPilotos.add(Box.createVerticalStrut(5));
        }
        
        panelListaPilotos.revalidate();
        panelListaPilotos.repaint();
    }

    private JPanel criarCardPiloto(Piloto p, String funcao, Color bg) {
        JPanel card = new JPanel(new BorderLayout(10, 0));
        card.setBackground(bg);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65)); 

        JLabel lblFlag = new JLabel();
        carregarImagem(lblFlag, "/resource/Bandeira " + p.getNacionalidade() + ".png", 30, 20);
        card.add(lblFlag, BorderLayout.WEST);

        JLabel lblNome = new JLabel("<html><b>" + p.getNome() + "</b><br><font size='2' color='#555555'>Contrato: " + (funcao) + "</font></html>");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card.add(lblNome, BorderLayout.CENTER);

        JLabel lblNum = new JLabel("#" + p.getNumero());
        lblNum.setFont(new Font("Impact", Font.PLAIN, 20));
        lblNum.setForeground(Color.DARK_GRAY);
        card.add(lblNum, BorderLayout.EAST);

        return card;
    }

    private void carregarImagem(JLabel lbl, String path, int w, int h) {
        try {
            if (path != null && !path.isEmpty()) {
                if (!path.startsWith("/")) path = "/" + path;
                if (!path.startsWith("/resource")) path = "/resource" + path;
                path = path.replace("//", "/");
                
                ImageIcon icon = new ImageIcon(getClass().getResource(path));
                if (icon.getIconWidth() > 0) { // Verifica se carregou
                    Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                    lbl.setIcon(new ImageIcon(img));
                } else {
                    lbl.setIcon(null); 
                }
            } else {
                lbl.setIcon(null);
            }
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    private void irParaCorrida() {
        JOptionPane.showMessageDialog(this, "Carregando corrida em " + lblLocalPista.getText() + "...");
    }
}
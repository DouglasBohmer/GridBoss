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

    // --- COMPONENTES GLOBAIS PARA ATUALIZAÇÃO ---
    
    // Vermelho (Equipe)
    private JLabel lblNomeEquipe, lblSede, lblMotor, lblLogoEquipe;
    private JLabel lblBandeiraSede, lblLogoMotor;
    
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
        
        // Layout Principal: 2 Colunas (Esquerda e Direita)
        contentPane.setLayout(new GridLayout(1, 2, 10, 0)); // 1 Linha, 2 Colunas, 10px gap

        // --- COLUNA ESQUERDA (Vermelho, Cinza, Verde) ---
        JPanel colunaEsquerda = new JPanel(new GridBagLayout());
        colunaEsquerda.setOpaque(false);
        GridBagConstraints gbcE = new GridBagConstraints();
        gbcE.fill = GridBagConstraints.BOTH;
        gbcE.weightx = 1.0;
        gbcE.insets = new Insets(0, 0, 5, 0);

        // 1. Vermelho (Equipe) - Topo
        JPanel panelVermelho = criarPainelVermelho();
        gbcE.gridy = 0; gbcE.weighty = 0.25; // 25% da altura
        colunaEsquerda.add(panelVermelho, gbcE);

        // 2. Cinza (Pilotos) - Meio
        JPanel panelCinza = criarPainelCinza();
        gbcE.gridy = 1; gbcE.weighty = 0.45; // 45% da altura (precisa de espaço pra 5 pilotos)
        colunaEsquerda.add(panelCinza, gbcE);

        // 3. Verde (Campeonato) - Baixo
        JPanel panelVerde = criarPainelVerde();
        gbcE.gridy = 2; gbcE.weighty = 0.30; // 30% da altura
        gbcE.insets = new Insets(0, 0, 0, 0); // Remove margem inferior
        colunaEsquerda.add(panelVerde, gbcE);

        contentPane.add(colunaEsquerda);

        // --- COLUNA DIREITA (Azul, Preto, Rosa) ---
        JPanel colunaDireita = new JPanel(new GridBagLayout());
        colunaDireita.setOpaque(false);
        GridBagConstraints gbcD = new GridBagConstraints();
        gbcD.fill = GridBagConstraints.BOTH;
        gbcD.weightx = 1.0;
        gbcD.insets = new Insets(0, 0, 5, 0);

        // 4. Azul (Jogador) - Topo
        JPanel panelAzul = criarPainelAzul();
        gbcD.gridy = 0; gbcD.weighty = 0.15; // 15% da altura
        colunaDireita.add(panelAzul, gbcD);

        // 5. Preto (Próxima Corrida) - Meio
        JPanel panelPreto = criarPainelPreto();
        gbcD.gridy = 1; gbcD.weighty = 0.70; // 70% da altura (Mapa grande)
        colunaDireita.add(panelPreto, gbcD);

        // 6. Rosa (Ação) - Baixo
        JPanel panelRosa = criarPainelRosa();
        gbcD.gridy = 2; gbcD.weighty = 0.15; // 15% da altura
        gbcD.insets = new Insets(0, 0, 0, 0);
        colunaDireita.add(panelRosa, gbcD);

        contentPane.add(colunaDireita);

        // Preenche os dados
        atualizarDadosNaTela();
    }

    // =============================================================================================
    // MÉTODOS DE CRIAÇÃO DOS PAINÉIS (CORES ESPECÍFICAS)
    // =============================================================================================

    /**
     * VERMELHO - Dados da Equipe (Sede, Logo, Motor)
     */
    private JPanel criarPainelVermelho() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        // Vermelho suave para fundo
        panel.setBackground(new Color(255, 230, 230)); 
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.RED, 1), "DADOS DA EQUIPE", TitledBorder.LEFT, TitledBorder.TOP
        ));

        // Esquerda: Logo
        lblLogoEquipe = new JLabel();
        lblLogoEquipe.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoEquipe.setPreferredSize(new Dimension(140, 100));
        panel.add(lblLogoEquipe, BorderLayout.WEST);

        // Centro: Infos em Grid
        JPanel pInfos = new JPanel(new GridLayout(4, 1));
        pInfos.setOpaque(false);
        
        lblNomeEquipe = new JLabel("Nome da Equipe");
        lblNomeEquipe.setFont(new Font("Berlin Sans FB", Font.BOLD, 20));
        
        lblSede = new JLabel("Sede: Local");
        lblSede.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        lblMotor = new JLabel("Motor: Marca");
        lblMotor.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        
        pInfos.add(lblNomeEquipe);
        pInfos.add(lblSede);
        pInfos.add(lblMotor);
        // pInfos.add(new JLabel("Fundação: " + equipeJogavel.getFundacao())); // Opcional

        panel.add(pInfos, BorderLayout.CENTER);
        
        // Direita: Bandeiras pequenas
        JPanel pFlags = new JPanel(new GridLayout(2, 1, 5, 5));
        pFlags.setOpaque(false);
        pFlags.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        lblBandeiraSede = new JLabel();
        lblLogoMotor = new JLabel(); // Usar como logo do motor pequeno
        
        pFlags.add(lblBandeiraSede);
        pFlags.add(lblLogoMotor);
        
        panel.add(pFlags, BorderLayout.EAST);

        return panel;
    }

    /**
     * CINZA - Meus Pilotos (Lista com slots)
     */
    private JPanel criarPainelCinza() {
        JPanel panel = new JPanel(new BorderLayout());
        // Cinza claro
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

    /**
     * VERDE - Dados do Campeonato (Abas com Tabelas)
     */
    private JPanel criarPainelVerde() {
        JPanel panel = new JPanel(new BorderLayout());
        // Verde suave
        panel.setBackground(new Color(230, 255, 230));
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(new Color(34, 139, 34), 1), "CLASSIFICAÇÃO", TitledBorder.LEFT, TitledBorder.TOP
        ));

        tabCampeonato = new JTabbedPane();
        tabCampeonato.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        // Placeholders para as tabelas (implementaremos a lógica de preencher depois)
        JPanel pPilotos = new JPanel(); pPilotos.setOpaque(false); pPilotos.add(new JLabel("Tabela de Pilotos (Em Breve)"));
        JPanel pEquipes = new JPanel(); pEquipes.setOpaque(false); pEquipes.add(new JLabel("Tabela de Construtores (Em Breve)"));
        
        tabCampeonato.addTab("Pilotos", pPilotos);
        tabCampeonato.addTab("Equipes", pEquipes);
        
        panel.add(tabCampeonato, BorderLayout.CENTER);
        return panel;
    }

    /**
     * AZUL - Dados do Jogador (Dinheiro, Temporada)
     */
    private JPanel criarPainelAzul() {
        JPanel panel = new JPanel(new GridLayout(2, 2)); // Grid 2x2
        // Azul suave
        panel.setBackground(new Color(230, 240, 255));
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.BLUE, 1), "DADOS DO JOGADOR", TitledBorder.LEFT, TitledBorder.TOP
        ));

        lblNomeDirigente = new JLabel("Dirigente");
        lblNomeDirigente.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        lblNomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblSaldo = new JLabel("€ 0.0 M");
        lblSaldo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblSaldo.setForeground(new Color(0, 100, 0)); // Verde escuro para dinheiro
        lblSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblAnoTemporada = new JLabel("Temporada 2024");
        lblAnoTemporada.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblAnoTemporada.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Adiciona ao grid
        panel.add(lblNomeDirigente);
        panel.add(lblSaldo);
        panel.add(lblAnoTemporada);
        panel.add(new JLabel("")); // Espaço vazio ou outra info

        return panel;
    }

    /**
     * PRETO - Dados da Próxima Corrida (Traçado, Info)
     */
    private JPanel criarPainelPreto() {
        JPanel panel = new JPanel(new BorderLayout(0, 10));
        // Cinza escuro/Preto para cabeçalho, fundo claro para conteúdo
        panel.setBackground(Color.WHITE); 
        panel.setBorder(BorderFactory.createTitledBorder(
            new LineBorder(Color.BLACK, 2), "PRÓXIMA ETAPA", TitledBorder.CENTER, TitledBorder.TOP,
            new Font("Segoe UI", Font.BOLD, 14), Color.BLACK
        ));

        // Topo: Bandeira e Nome
        JPanel pTopo = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pTopo.setOpaque(false);
        lblBandeiraPaisPista = new JLabel();
        lblNomeGP = new JLabel("GP NOME");
        lblNomeGP.setFont(new Font("Berlin Sans FB", Font.BOLD, 22));
        pTopo.add(lblBandeiraPaisPista);
        pTopo.add(lblNomeGP);
        panel.add(pTopo, BorderLayout.NORTH);

        // Centro: Imagem
        lblImagemPista = new JLabel();
        lblImagemPista.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblImagemPista, BorderLayout.CENTER);

        // Baixo: Dados Técnicos
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

    /**
     * ROSA - Botão de Ação + Logo
     */
    private JPanel criarPainelRosa() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 20));
        // Rosa suave
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
        lblNomeEquipe.setText(equipeJogavel.getNome());
        lblSede.setText("Sede: " + equipeJogavel.getSede());
        lblMotor.setText("Motor: " + equipeJogavel.getMotor());
        
        carregarImagem(lblLogoEquipe, equipeJogavel.getCaminhoLogo(), 120, 80);
        carregarImagem(lblBandeiraSede, equipeJogavel.getCaminhoBandeiraSede(), 40, 25);
        carregarImagem(lblLogoMotor, equipeJogavel.getCaminhoLogoMotor(), 40, 25);

        // --- 2. CINZA (Pilotos) ---
        atualizarListaPilotos();

        // --- 3. VERDE (Campeonato) ---
        // (Aqui atualizaríamos as tabelas no futuro)

        // --- 4. AZUL (Jogador) ---
        lblNomeDirigente.setText("Chefe: " + TelaSelecionarEquipe.nomeDirigente);
        lblSaldo.setText(String.format("€ %.1f M", equipeJogavel.getSaldoFinanceiro()));
        lblAnoTemporada.setText("Temporada " + SessaoJogo.anoSelecionado);

        // --- 5. PRETO (Pista) ---
        Pista pista = campeonato.getPistaAtual();
        if (pista != null) {
            lblNomeGP.setText(pista.getNome());
            lblLocalPista.setText(pista.getPais());
            
            // Texto formatado com HTML para quebra de linha se necessário
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
        
        // Adiciona Titulares
        for (Piloto p : titulares) {
            panelListaPilotos.add(criarCardPiloto(p, "Titular", new Color(255, 255, 255)));
            panelListaPilotos.add(Box.createVerticalStrut(5));
        }
        
        // Adiciona Reservas
        for (Piloto p : reservas) {
            panelListaPilotos.add(criarCardPiloto(p, "Reserva", new Color(240, 240, 240))); 
            panelListaPilotos.add(Box.createVerticalStrut(5));
        }
        
        panelListaPilotos.revalidate();
        panelListaPilotos.repaint();
    }

    /**
     * Cria um slot visual para o piloto no painel cinza
     */
    private JPanel criarCardPiloto(Piloto p, String funcao, Color bg) {
        JPanel card = new JPanel(new BorderLayout(10, 0));
        card.setBackground(bg);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65)); 

        // Esquerda: Bandeira e Foto (se tivesse)
        JLabel lblFlag = new JLabel();
        carregarImagem(lblFlag, "/resource/Bandeira " + p.getNacionalidade() + ".png", 30, 20);
        card.add(lblFlag, BorderLayout.WEST);

        // Centro: Nome e Contrato
        JLabel lblNome = new JLabel("<html><b>" + p.getNome() + "</b><br><font size='2' color='#555555'>Contrato: " + (funcao) + "</font></html>");
        lblNome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card.add(lblNome, BorderLayout.CENTER);

        // Direita: Número
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
                Image img = icon.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
                lbl.setIcon(new ImageIcon(img));
            }
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    private void irParaCorrida() {
        JOptionPane.showMessageDialog(this, "Carregando corrida em " + lblLocalPista.getText() + "...");
    }
}
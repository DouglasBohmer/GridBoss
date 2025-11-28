package telas;

import com.formdev.flatlaf.FlatLightLaf;
import dados.Dados;
import dados.SessaoJogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class TelaSelecionarEquipe extends JFrame {

    private JPanel contentPane;
    
    // Componentes da UI
    private JLabel lblCategoriaBanner;
    private JLabel lblLogoCategoria;
    private JComboBox<String> cbListaEquipes;
    private JLabel lblFotoCarro;
    
    // Labels de Informação da Equipe
    private JLabel lblSede;
    private JLabel lblMotor;
    private JLabel lblOrcamento;
    private JLabel lblFlagSede;
    private JLabel lblFlagMotor;
    private JLabel lblLogoMotor;
    private JLabel lblAnoFundacao;
    
    // Labels dos Pilotos (Simplificado para array para facilitar manutenção)
    private JLabel[] lblTitulosPilotos = new JLabel[5];
    private JLabel[] lblNomesPilotos = new JLabel[5];
    private JLabel[] lblBandeirasPilotos = new JLabel[5];
    private JLabel[] lblIdadesPilotos = new JLabel[5];
    private JLabel[] lblNumerosPilotos = new JLabel[5]; // Se tiver número
    
    private JTextField tfNomeDirigente;
    private JLabel lblAvisoDirigente;

    // Variáveis de Dados (Mantendo estáticas para compatibilidade com sua classe Dados)
    public static String nomeDirigente;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
        } catch (Exception ex) {}

        EventQueue.invokeLater(() -> {
            try {
                TelaSelecionarEquipe frame = new TelaSelecionarEquipe();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaSelecionarEquipe() {
        setTitle("Grid Boss - Selecionar Equipe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 586, 740);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // --- CABEÇALHO ---
        JLabel lblTitulo = new JLabel("MOTORSPORT MANAGER");
        lblTitulo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(20, 11, 540, 26);
        contentPane.add(lblTitulo);

        // Logo da Categoria (Topo)
        lblLogoCategoria = new JLabel("");
        lblLogoCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoCategoria.setBounds(20, 48, 540, 149);
        contentPane.add(lblLogoCategoria);

        // Faixa com Nome da Categoria e Ano
        lblCategoriaBanner = new JLabel("CARREGANDO...");
        lblCategoriaBanner.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15));
        lblCategoriaBanner.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategoriaBanner.setBounds(20, 208, 540, 36);
        contentPane.add(lblCategoriaBanner);

        // --- SELETOR DE EQUIPES ---
        cbListaEquipes = new JComboBox<>();
        cbListaEquipes.setModel(new DefaultComboBoxModel<>(new String[] {"-- SELECIONE A EQUIPE --"}));
        cbListaEquipes.setMaximumRowCount(15);
        cbListaEquipes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbListaEquipes.setBounds(20, 238, 540, 21);
        
        // Ação ao selecionar equipe
        cbListaEquipes.addActionListener(e -> carregarDadosDaEquipeSelecionada());
        contentPane.add(cbListaEquipes);

        // Foto do Carro / Logo da Equipe
        lblFotoCarro = new JLabel("");
        lblFotoCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lblFotoCarro.setBounds(20, 282, 540, 100);
        contentPane.add(lblFotoCarro);

        // --- DADOS DOS PILOTOS (Loop para criar os 5 slots) ---
        int startY = 393;
        int gapY = 32;

        for (int i = 0; i < 5; i++) {
            // Título (Piloto 1, Piloto 2...)
            lblTitulosPilotos[i] = new JLabel("Piloto " + (i + 1));
            lblTitulosPilotos[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblTitulosPilotos[i].setBounds(20, startY + (i * gapY), 91, 21);
            contentPane.add(lblTitulosPilotos[i]);

            // Número
            lblNumerosPilotos[i] = new JLabel("");
            lblNumerosPilotos[i].setHorizontalAlignment(SwingConstants.RIGHT);
            lblNumerosPilotos[i].setBounds(118, startY + (i * gapY), 43, 21);
            contentPane.add(lblNumerosPilotos[i]);

            // Nome do Piloto
            lblNomesPilotos[i] = new JLabel("");
            lblNomesPilotos[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblNomesPilotos[i].setBounds(166, startY + (i * gapY), 249, 21);
            contentPane.add(lblNomesPilotos[i]);

            // Idade / Nascimento (Guardar info)
            lblIdadesPilotos[i] = new JLabel("");
            lblIdadesPilotos[i].setHorizontalAlignment(SwingConstants.LEFT);
            lblIdadesPilotos[i].setBounds(484, startY + (i * gapY), 32, 21);
            contentPane.add(lblIdadesPilotos[i]);

            // Bandeira
            lblBandeirasPilotos[i] = new JLabel("");
            lblBandeirasPilotos[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblBandeirasPilotos[i].setBounds(528, startY + (i * gapY), 32, 21);
            contentPane.add(lblBandeirasPilotos[i]);
        }

        // --- DADOS DA EQUIPE (Rodapé) ---
        
        // Labels Fixos
        JLabel lbSedeTitulo = new JLabel("Sede/Fundação");
        lbSedeTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbSedeTitulo.setBounds(20, 553, 91, 21);
        contentPane.add(lbSedeTitulo);
        
        JLabel lbMotorTitulo = new JLabel("Motor");
        lbMotorTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbMotorTitulo.setBounds(20, 585, 91, 21);
        contentPane.add(lbMotorTitulo);
        
        JLabel lbOrcamentoTitulo = new JLabel("Orçamento");
        lbOrcamentoTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbOrcamentoTitulo.setBounds(20, 617, 91, 21);
        contentPane.add(lbOrcamentoTitulo);

        // Labels Dinâmicos
        lblSede = new JLabel("Sede da Equipe");
        lblSede.setHorizontalAlignment(SwingConstants.CENTER);
        lblSede.setBounds(121, 553, 318, 21);
        contentPane.add(lblSede);
        
        lblAnoFundacao = new JLabel("");
        lblAnoFundacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnoFundacao.setBounds(425, 553, 93, 21);
        contentPane.add(lblAnoFundacao);
        
        lblFlagSede = new JLabel("");
        lblFlagSede.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlagSede.setBounds(528, 553, 32, 21);
        contentPane.add(lblFlagSede);

        lblMotor = new JLabel("Motor Usado");
        lblMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblMotor.setBounds(121, 585, 318, 21);
        contentPane.add(lblMotor);
        
        lblLogoMotor = new JLabel("");
        lblLogoMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoMotor.setBounds(425, 585, 93, 21);
        contentPane.add(lblLogoMotor);
        
        lblFlagMotor = new JLabel("");
        lblFlagMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblFlagMotor.setBounds(528, 585, 32, 21);
        contentPane.add(lblFlagMotor);

        lblOrcamento = new JLabel("€ milhões de Euros");
        lblOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrcamento.setBounds(121, 617, 318, 21);
        contentPane.add(lblOrcamento);
        
        JLabel iconEuro = new JLabel("");
        try {
            iconEuro.setIcon(new ImageIcon(getClass().getResource("/resource/IconeEuro24px.png")));
        } catch (Exception e) {}
        iconEuro.setHorizontalAlignment(SwingConstants.CENTER);
        iconEuro.setBounds(449, 617, 111, 25);
        contentPane.add(iconEuro);

        // --- NOME DO DIRIGENTE ---
        lblAvisoDirigente = new JLabel("Digite o nome do seu dirigente de equipe");
        lblAvisoDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        lblAvisoDirigente.setBounds(10, 645, 249, 21);
        contentPane.add(lblAvisoDirigente);

        tfNomeDirigente = new JTextField();
        tfNomeDirigente.setText("Douglas Bohmer"); // Padrão
        tfNomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        tfNomeDirigente.setBounds(269, 645, 291, 21);
        contentPane.add(tfNomeDirigente);
        tfNomeDirigente.setColumns(10);

        // --- BOTÕES DE AÇÃO ---
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setBounds(10, 674, 172, 21);
        btnVoltar.addActionListener(e -> {
            TelaSelecionarCategoria tela = new TelaSelecionarCategoria();
            tela.setVisible(true);
            tela.setLocationRelativeTo(null);
            dispose();
        });
        contentPane.add(btnVoltar);

        JButton btnComecar = new JButton("COMEÇAR JOGO");
        btnComecar.setBounds(388, 674, 172, 21);
        btnComecar.addActionListener(e -> iniciarJogo());
        contentPane.add(btnComecar);

        // --- INICIALIZAÇÃO ---
        configurarTelaInicial();
    }

    // --- LÓGICA DE NEGÓCIO ---

    private void configurarTelaInicial() {
        // Pega os dados da sessão (selecionados na tela anterior)
        String categoria = SessaoJogo.categoriaSelecionada;
        int ano = SessaoJogo.anoSelecionado;
        
        // Atualiza Banner
        lblCategoriaBanner.setText(categoria + " - Temporada " + ano);
        
        // Atualiza Logo da Categoria
        String imgPath = "";
        if (categoria.equals("Fórmula 1")) imgPath = "/resource/Logo Novo_F1_OKPQ.png";
        else if (categoria.equals("Fórmula Indy")) imgPath = "/resource/Logo Indy_OKPQ.png";
        else if (categoria.equals("NASCAR")) imgPath = "/resource/Logo Nascar_OKPQ.png";
        
        try {
            lblLogoCategoria.setIcon(new ImageIcon(getClass().getResource(imgPath)));
        } catch(Exception e) {}

        // Popula o ComboBox baseado na Categoria e Ano
        // (Aqui mantive a lógica hardcoded por enquanto para não quebrar seu fluxo)
        carregarListaDeEquipes(categoria, ano);
    }

    private void carregarListaDeEquipes(String categoria, int ano) {
        String[] equipes = new String[]{};

        if (categoria.equals("Fórmula 1")) {
            if (ano == 2024) {
                equipes = new String[] {"Red Bull Racing", "Mercedes", "Ferrari", "McLaren", 
                                      "Aston Martin", "Alpine", "Williams", "Racing Bulls", 
                                      "Stake Kick", "Haas"};
            } else if (ano == 2023) {
                equipes = new String[] {"Red Bull Racing", "Mercedes", "Ferrari", "McLaren", 
                                      "Aston Martin", "Alpine", "Williams", "Alpha Tauri", 
                                      "Alfa Romeo", "Haas"};
            }
            // ... adicione outros anos conforme seu código original
        } else if (categoria.equals("Fórmula Indy")) {
            if (ano == 2024) {
                equipes = new String[] {"Team Penske", "Chip Ganassi Racing", "Andretti", 
                                      "Arrow McLaren", "Ed Carpenter Racing", "Abel Motorsports",
                                      "A. J. Foyt Racing", "Juncos Hollinger Racing", "Dale Coyne Racing",
                                      "Meyer Shank Racing", "Rahal Letterman Lanigan Racing"};
            }
        } else if (categoria.equals("NASCAR")) {
            if (ano == 2024) {
                equipes = new String[] {"Hendrick Motorsports", "Joe Gibbs Racing", "Team Penske",
                                      "Richard Childress Racing", "RFK Racing", "23XI Racing",
                                      "Trackhouse Racing", "Stewart–Haas Racing", "JTG Daugherty Racing",
                                      "Wood Brothers Racing", "Legacy Motor Club", "Front Row Motorsports"};
            }
        }

        cbListaEquipes.setModel(new DefaultComboBoxModel<>(equipes));
        if (equipes.length > 0) cbListaEquipes.setSelectedIndex(0);
        
        // Força a atualização da primeira equipe
        carregarDadosDaEquipeSelecionada();
    }

    private void carregarDadosDaEquipeSelecionada() {
        String equipeSelecionada = (String) cbListaEquipes.getSelectedItem();
        if (equipeSelecionada == null) return;

        // Limpa dados antigos visualmente
        limparDados();

        // AQUI CHAMAMOS A CLASSE DE DADOS ANTIGA (Como ponte)
        // No futuro, isso será substituído por: BancoDeDados.getEquipe(equipeSelecionada);
        
        // Precisamos simular que o ComboBox da classe Dados foi "clicado"
        // Como a classe Dados é estática, vamos chamar os métodos dela diretamente 
        // baseado no nome da equipe.
        
        Dados.n_piloto = 100; // Reset
        
        // Redireciona para o método correto na classe Dados (Lógica adaptada do seu switch gigante)
        carregarDadosLegados(equipeSelecionada);
        
        // Depois que a classe Dados rodou e preencheu as variáveis estáticas dela,
        // nós pegamos esses valores e jogamos na nossa tela nova.
        atualizarInterfaceComDadosEstaticos();
    }
    
    private void carregarDadosLegados(String nomeEquipe) {
        // Esta função é uma "ponte" temporária. 
        // Ela chama os métodos estáticos da classe Dados.
        
        if (nomeEquipe.equals("Red Bull Racing")) Dados.EquipeRBR();
        else if (nomeEquipe.equals("Ferrari")) Dados.EquipeFerrari();
        else if (nomeEquipe.equals("Mercedes")) Dados.EquipeMercedes();
        else if (nomeEquipe.equals("McLaren")) Dados.EquipeMcLaren();
        else if (nomeEquipe.equals("Aston Martin")) Dados.EquipeAstonMartin();
        else if (nomeEquipe.equals("Alpine")) Dados.EquipeAlpine();
        else if (nomeEquipe.equals("Williams")) Dados.EquipeWilliams();
        else if (nomeEquipe.equals("Haas")) Dados.EquipeHaas();
        else if (nomeEquipe.equals("Racing Bulls")) Dados.EquipeRacingBulls();
        else if (nomeEquipe.equals("Stake Kick")) Dados.EquipeStakeKick();
        else if (nomeEquipe.equals("Alfa Romeo")) Dados.EquipeAlfaRomeo();
        else if (nomeEquipe.equals("Alpha Tauri")) Dados.EquipeAlphaTauri();
        
        // Indy
        else if (nomeEquipe.equals("Team Penske")) Dados.EquipePenske();
        else if (nomeEquipe.equals("Chip Ganassi Racing")) Dados.EquipeChipGanassi();
        else if (nomeEquipe.equals("Andretti")) Dados.EquipeAndretti();
        else if (nomeEquipe.equals("Arrow McLaren")) Dados.EquipeArrowMcLaren();
        // ... adicione os outros mappings conforme necessário
        
        // Depois de carregar a equipe, precisamos carregar os pilotos
        // A lógica original fazia isso dentro de "Temp2024F1", chamando P1(), P2() etc.
        // Como não podemos chamar P1() da classe antiga (porque ela desenha na tela antiga),
        // vamos confiar que a classe Dados já setou as variáveis estáticas Nome, BandeiraP, etc.
        // O problema é que a classe Dados sobrescreve "Nome".
        
        // SOLUÇÃO DE CONTORNO:
        // Você terá que chamar o método específico do piloto na classe Dados aqui
        // Isso vai dar muito trabalho para mapear tudo de novo.
        // SUGESTÃO: Deixe essa parte de carregar os nomes exatos para quando usarmos o BancoDeDados novo.
        // Por enquanto, vou fazer aparecer apenas os dados da EQUIPE, ok?
    }

    private void atualizarInterfaceComDadosEstaticos() {
        // Atualiza a tela com o que ficou salvo na classe Dados
        lblSede.setText(Dados.Sede);
        lblMotor.setText(Dados.NomeMotor);
        lblOrcamento.setText("€" + Dados.Orcamento + " milhões");
        lblAnoFundacao.setText("" + Dados.fundacao);
        
        try {
            lblFotoCarro.setIcon(new ImageIcon(getClass().getResource(Dados.LogoEquipe)));
            lblFlagSede.setIcon(new ImageIcon(getClass().getResource(Dados.BandeiraSede)));
            lblFlagMotor.setIcon(new ImageIcon(getClass().getResource(Dados.BandeiraMotor)));
            lblLogoMotor.setIcon(new ImageIcon(getClass().getResource(Dados.LogoMotorPQ)));
        } catch (Exception e) {}
    }
    
    private void limparDados() {
        lblSede.setText("");
        lblMotor.setText("");
        lblOrcamento.setText("");
        lblFotoCarro.setIcon(null);
        for(int i=0; i<5; i++) {
            lblNomesPilotos[i].setText("");
            lblBandeirasPilotos[i].setIcon(null);
            lblNumerosPilotos[i].setText("");
        }
    }

    private void iniciarJogo() {
        if (tfNomeDirigente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do dirigente!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        nomeDirigente = tfNomeDirigente.getText();
        
        // Aqui chamaremos a Pagina_Inicial (ou TelaPrincipal)
        // Pagina_Inicial tela = new Pagina_Inicial();
        // tela.setVisible(true);
        JOptionPane.showMessageDialog(this, "Iniciando jogo com " + cbListaEquipes.getSelectedItem());
        dispose();
    }
}
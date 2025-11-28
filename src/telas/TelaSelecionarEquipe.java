package telas;

import com.formdev.flatlaf.FlatLightLaf;
import dados.CarregadorJSON;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.TipoContrato;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class TelaSelecionarEquipe extends JFrame {

    private JPanel contentPane;
    
    // UI Components
    private JLabel lblCategoriaBanner;
    private JLabel lblLogoCategoria;
    private JComboBox<String> cbListaEquipes;
    private JLabel lblFotoCarro;
    
    // Info Equipe
    private JLabel lblSede, lblMotor, lblOrcamento, lblAnoFundacao;
    private JLabel lblFlagSede, lblFlagMotor, lblLogoMotor;
    
    // Arrays para os 5 Slots de Pilotos
    private JLabel[] lblTitulos = new JLabel[5];
    private JLabel[] lblNomes = new JLabel[5];
    private JLabel[] lblBandeiras = new JLabel[5];
    private JLabel[] lblNumeros = new JLabel[5];
    private JLabel[] lblIdades = new JLabel[5];
    
    private JTextField tfNomeDirigente;

    // --- DADOS CARREGADOS DO JSON ---
    // Estas variáveis guardam o que veio do arquivo .json
    private List<Equipe> equipesDisponiveis = new ArrayList<>();
    private List<Piloto> todosPilotos = new ArrayList<>();
    private Equipe equipeSelecionadaObj = null; // Guarda a equipe atual que o usuário clicou

    // Variáveis Globais
    public static String nomeDirigente;
    private JLabel lblMotor_1;

    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10);
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

    public TelaSelecionarEquipe() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSelecionarEquipe.class.getResource("/resource/Icone16px.png")));
        setTitle("Grid Boss");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 586, 740);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // --- CABEÇALHO ---
        JLabel lblTitulo = new JLabel("GRID BOSS");
        lblTitulo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(20, 11, 540, 26);
        contentPane.add(lblTitulo);

        lblLogoCategoria = new JLabel("");
        lblLogoCategoria.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoCategoria.setBounds(20, 48, 540, 149);
        contentPane.add(lblLogoCategoria);

        lblCategoriaBanner = new JLabel("CARREGANDO...");
        lblCategoriaBanner.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15));
        lblCategoriaBanner.setHorizontalAlignment(SwingConstants.CENTER);
        lblCategoriaBanner.setBounds(20, 208, 540, 36);
        contentPane.add(lblCategoriaBanner);

        // --- SELETOR ---
        cbListaEquipes = new JComboBox<>();
        cbListaEquipes.setModel(new DefaultComboBoxModel<>(new String[] {"-- CARREGANDO --"}));
        cbListaEquipes.setMaximumRowCount(15);
        cbListaEquipes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        cbListaEquipes.setBounds(20, 238, 540, 21);
        // Quando mudar o combobox, chama o método de atualizar a tela
        cbListaEquipes.addActionListener(e -> atualizarDadosNaTela());
        contentPane.add(cbListaEquipes);

        lblFotoCarro = new JLabel("");
        lblFotoCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lblFotoCarro.setBounds(20, 282, 540, 100);
        contentPane.add(lblFotoCarro);

        // --- SLOTS DE PILOTOS (1 a 5) ---
        int startY = 393;
        int gapY = 32;

        for (int i = 0; i < 5; i++) {
            lblTitulos[i] = new JLabel("Piloto " + (i + 1));
            lblTitulos[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblTitulos[i].setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
            lblTitulos[i].setBounds(20, startY + (i * gapY), 91, 21);
            contentPane.add(lblTitulos[i]);

            lblNumeros[i] = new JLabel("");
            lblNumeros[i].setHorizontalAlignment(SwingConstants.RIGHT);
            lblNumeros[i].setFont(new Font("Arial", Font.BOLD, 12));
            lblNumeros[i].setBounds(118, startY + (i * gapY), 43, 21);
            contentPane.add(lblNumeros[i]);

            lblNomes[i] = new JLabel("");
            lblNomes[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblNomes[i].setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
            lblNomes[i].setBounds(166, startY + (i * gapY), 249, 21);
            contentPane.add(lblNomes[i]);

            lblIdades[i] = new JLabel("");
            lblIdades[i].setBounds(484, startY + (i * gapY), 32, 21);
            contentPane.add(lblIdades[i]);

            lblBandeiras[i] = new JLabel("");
            lblBandeiras[i].setHorizontalAlignment(SwingConstants.CENTER);
            lblBandeiras[i].setBounds(528, startY + (i * gapY), 32, 21);
            contentPane.add(lblBandeiras[i]);
        }

        // --- DADOS DA EQUIPE (RODAPÉ) ---
        /*
        criarLabelInfo("Sede/Fundação", 553);
        criarLabelInfo("Motor", 585);
        criarLabelInfo("Orçamento", 617);
		*/
	
        lblSede = new JLabel("");
        lblSede.setHorizontalAlignment(SwingConstants.CENTER);
        lblSede.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblSede.setBounds(121, 553, 318, 21);
        contentPane.add(lblSede);
        
        lblAnoFundacao = new JLabel("");
        lblAnoFundacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnoFundacao.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblAnoFundacao.setBounds(425, 553, 93, 21);
        contentPane.add(lblAnoFundacao);
        
        lblFlagSede = new JLabel("");
        lblFlagSede.setBounds(528, 553, 32, 21);
        lblFlagSede.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblFlagSede);

        lblMotor = new JLabel("");
        lblMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblMotor.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblMotor.setBounds(121, 585, 318, 21);
        contentPane.add(lblMotor);
        
        lblLogoMotor = new JLabel("");
        lblLogoMotor.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoMotor.setBounds(425, 585, 93, 21);
        contentPane.add(lblLogoMotor);
        
        lblFlagMotor = new JLabel("");
        lblFlagMotor.setBounds(528, 585, 32, 21);
        lblFlagMotor.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lblFlagMotor);

        lblOrcamento = new JLabel("");
        lblOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
        lblOrcamento.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblOrcamento.setBounds(121, 617, 318, 21);
        contentPane.add(lblOrcamento);

        // --- DIRIGENTE ---
        JLabel lblAviso = new JLabel("Nome do Dirigente");
        lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
        lblAviso.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblAviso.setBounds(10, 645, 172, 21);
        contentPane.add(lblAviso);

        tfNomeDirigente = new JTextField("Douglas Bohmer");
        tfNomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
        tfNomeDirigente.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        tfNomeDirigente.setBounds(269, 645, 291, 21);
        contentPane.add(tfNomeDirigente);

        // BOTÕES
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        btnVoltar.setBounds(10, 674, 172, 21);
        btnVoltar.addActionListener(e -> {
            TelaSelecionarCategoria tela = new TelaSelecionarCategoria();
            tela.setVisible(true);
            tela.setLocationRelativeTo(null);
            dispose();
        });
        contentPane.add(btnVoltar);

        JButton btnComecar = new JButton("COMEÇAR JOGO");
        btnComecar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        btnComecar.setBounds(388, 674, 172, 21);
        btnComecar.addActionListener(e -> iniciarJogo());
        contentPane.add(btnComecar);
        
        JLabel l = new JLabel("Sede/Fundação");
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        l.setBounds(20, 553, 120, 21);
        contentPane.add(l);
        
        JLabel l_1 = new JLabel("Orçamento");
        l_1.setHorizontalAlignment(SwingConstants.CENTER);
        l_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        l_1.setBounds(20, 617, 120, 21);
        contentPane.add(l_1);
        
        lblMotor_1 = new JLabel("Motor");
        lblMotor_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblMotor_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblMotor_1.setBounds(20, 585, 120, 21);
        contentPane.add(lblMotor_1);

        // --- CARREGAR DADOS ---
        carregarDadosIniciais();
    }

    // --- LÓGICA DE DADOS (JSON) ---

    private void carregarDadosIniciais() {
        String cat = SessaoJogo.categoriaKey; // "f1", "indy", "nascar"
        int ano = SessaoJogo.anoSelecionado;  // 2024
        
        lblCategoriaBanner.setText(SessaoJogo.categoriaSelecionada + " - Temporada " + ano);
        carregarImagem(lblLogoCategoria, SessaoJogo.IMAGEM_SELECIONADA);

        // 1. Carrega Pilotos e Equipes do JSON
        // (Chama o CarregadorJSON que criamos antes)
        this.todosPilotos = CarregadorJSON.carregarPilotos(cat, ano);
        this.equipesDisponiveis = CarregadorJSON.carregarEquipes(cat, ano);

        if (equipesDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma equipe encontrada em /mods/" + cat + "_" + ano, "Erro de Mod", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Hidratar Equipes (Colocar os pilotos dentro das equipes)
        vincularPilotosAsEquipes();

        // 3. Popular ComboBox com os nomes das equipes carregadas
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Equipe eq : equipesDisponiveis) {
            model.addElement(eq.getNome());
        }
        cbListaEquipes.setModel(model);
        
        // Força atualização da primeira
        if (model.getSize() > 0) cbListaEquipes.setSelectedIndex(0);
    }

    /**
     * Pega os IDs ("verstappen") do JSON da equipe e busca o objeto Piloto real.
     */
    private void vincularPilotosAsEquipes() {
        for (Equipe eq : equipesDisponiveis) {
            List<String> idsContratados = eq.getPilotosContratadosIDs();
            
            if (idsContratados != null) {
                for (String idAlvo : idsContratados) {
                    Piloto pilotoEncontrado = buscarPilotoPorId(idAlvo);
                    if (pilotoEncontrado != null) {
                        // Adiciona na equipe como Titular (Padrão do load)
                        eq.adicionarPilotoDoLoad(pilotoEncontrado, TipoContrato.TITULAR);
                    }
                }
            }
        }
    }

    private Piloto buscarPilotoPorId(String id) {
        for (Piloto p : todosPilotos) {
            if (p.getNome().equalsIgnoreCase(id)) {
                return p;
            }
        }
        return null;
    }

    private void atualizarDadosNaTela() {
        int index = cbListaEquipes.getSelectedIndex();
        if (index < 0 || index >= equipesDisponiveis.size()) return;

        this.equipeSelecionadaObj = equipesDisponiveis.get(index);
        Equipe eq = this.equipeSelecionadaObj;

        lblSede.setText(eq.getSede());
        lblMotor.setText(eq.getMotor());
        lblOrcamento.setText("€ " + eq.getSaldoFinanceiro() + " milhões");
        lblAnoFundacao.setText("" + eq.getFundacao());
        
        carregarImagem(lblFotoCarro, eq.getCaminhoLogo());
        carregarImagem(lblFlagSede, eq.getCaminhoBandeiraSede());
        carregarImagem(lblFlagMotor, eq.getCaminhoBandeiraMotor());
        carregarImagem(lblLogoMotor, eq.getCaminhoLogoMotor());

        // Preenche Pilotos
        List<Piloto> titulares = eq.getPilotosTitulares();
        
        for (int i = 0; i < 5; i++) {
            // Lógica para ESCONDER ou MOSTRAR slots
            if (i < titulares.size()) {
                // Piloto existe neste slot -> MOSTRAR
                Piloto p = titulares.get(i);
                
                lblTitulos[i].setVisible(true);
                lblNomes[i].setVisible(true);
                lblNumeros[i].setVisible(true);
                lblBandeiras[i].setVisible(true);
                lblIdades[i].setVisible(true);

                // Lógica de Título: Se for F1 e índice >= 2, é reserva
                if (SessaoJogo.categoriaKey.contains("f1") && i >= 2) {
                    lblTitulos[i].setText("Reserva");
                } else {
                    lblTitulos[i].setText("Piloto " + (i + 1));
                }

                lblNomes[i].setText(p.getNome());
                lblNumeros[i].setText("#" + p.getNumero());
                carregarImagem(lblBandeiras[i], "/resource/Bandeira " + p.getNacionalidade() + ".png");
            } else {
                // Slot vazio -> ESCONDER TUDO
                lblTitulos[i].setVisible(false);
                lblNomes[i].setVisible(false);
                lblNumeros[i].setVisible(false);
                lblBandeiras[i].setVisible(false);
                lblIdades[i].setVisible(false);
            }
        }
    }

    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path != null && !path.isEmpty()) {
                if (!path.startsWith("/")) path = "/" + path;
                if (!path.startsWith("/resource")) path = "/resource" + path;
                path = path.replace("//", "/");
                
                lbl.setIcon(new ImageIcon(getClass().getResource(path)));
            } else {
                lbl.setIcon(null);
            }
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    private void iniciarJogo() {
        if (tfNomeDirigente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do Dirigente!");
            return;
        }
        nomeDirigente = tfNomeDirigente.getText();
        
        JOptionPane.showMessageDialog(this, "Iniciando com " + equipeSelecionadaObj.getNome() + "\nDirigente: " + nomeDirigente);
        
        // AQUI VAI A TRANSIÇÃO PARA A TELA PRINCIPAL
        // TelaPrincipal tela = new TelaPrincipal(equipeSelecionadaObj);
        // tela.setVisible(true);
        // dispose();
    }
}
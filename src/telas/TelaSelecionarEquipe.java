package telas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
import dados.CarregadorJSON;
import dados.DadosDoJogo;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Motor;
import modelos.Piloto;
import modelos.TipoContrato;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Equipe> equipesDisponiveis = new ArrayList<>();
    private List<Piloto> todosPilotos = new ArrayList<>();
    private Equipe equipeSelecionadaObj = null; 

    public static String nomeDirigente;

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
        // Ícone da janela agora em SVG
        try {
            setIconImage(FlatSVGUtils.svg2image("resource/Icone16px.svg", 16, 16));
        } catch (Exception e) {
        }

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
        cbListaEquipes.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        cbListaEquipes.setBounds(20, 238, 540, 21);
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
        criarLabelInfo("Sede/Fundação", 553);
        criarLabelInfo("Motor", 585);
        criarLabelInfo("Orçamento", 617);

        lblSede = new JLabel("");
        lblSede.setHorizontalAlignment(SwingConstants.CENTER);
        lblSede.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        lblSede.setBounds(121, 553, 318, 21);
        contentPane.add(lblSede);
        
        lblAnoFundacao = new JLabel("");
        lblAnoFundacao.setBounds(425, 553, 93, 21);
        lblAnoFundacao.setHorizontalAlignment(SwingConstants.CENTER);
        lblAnoFundacao.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
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
        lblLogoMotor.setBounds(425, 585, 93, 21);
        lblLogoMotor.setHorizontalAlignment(SwingConstants.CENTER);
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

        // --- CARREGAR DADOS ---
        carregarDadosIniciais();
    }

    private void criarLabelInfo(String texto, int y) {
        JLabel l = new JLabel(texto);
        l.setHorizontalAlignment(SwingConstants.CENTER);
        l.setFont(new Font("Berlin Sans FB", Font.PLAIN, 15));
        l.setBounds(20, y, 120, 21);
        contentPane.add(l);
    }

    // --- LÓGICA DE DADOS ---

    private void carregarDadosIniciais() {
        String cat = SessaoJogo.categoriaKey;
        int ano = SessaoJogo.anoSelecionado;
        
        lblCategoriaBanner.setText(SessaoJogo.categoriaSelecionada + " - Temporada " + ano);
        carregarImagem(lblLogoCategoria, SessaoJogo.IMAGEM_SELECIONADA);

        this.todosPilotos = CarregadorJSON.carregarPilotos(cat, ano);
        this.equipesDisponiveis = CarregadorJSON.carregarEquipes(cat, ano);

        if (equipesDisponiveis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nenhuma equipe encontrada em /mods/" + cat + "_" + ano, "Erro de Mod", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // --- VÍNCULO DE MOTORES ---
        List<Motor> motores = CarregadorJSON.carregarMotores(cat, ano);
        if (motores != null && !motores.isEmpty()) {
            for (Equipe eq : equipesDisponiveis) {
                if (eq.getMotor() == null) continue;
                for (Motor m : motores) {
                    if (eq.getMotor().equalsIgnoreCase(m.getId())) {
                        eq.setMotorObjeto(m);
                        break;
                    }
                }
            }
        }

        vincularPilotosAsEquipesLocal();

        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        for (Equipe eq : equipesDisponiveis) {
            model.addElement(eq.getNome());
        }
        cbListaEquipes.setModel(model);
        
        if (model.getSize() > 0) cbListaEquipes.setSelectedIndex(0);
    }

    private void vincularPilotosAsEquipesLocal() {
        for (Equipe eq : equipesDisponiveis) {
            List<String> idsContratados = eq.getPilotosContratadosIDs();
            if (idsContratados != null) {
                for (String idAlvo : idsContratados) {
                    Piloto pilotoEncontrado = buscarPilotoPorIdLocal(idAlvo);
                    if (pilotoEncontrado != null) {
                        eq.adicionarPilotoDoLoad(pilotoEncontrado, TipoContrato.TITULAR);
                    }
                }
            }
        }
    }

    private Piloto buscarPilotoPorIdLocal(String id) {
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

        List<Piloto> titulares = eq.getPilotosTitulares();
        
        for (int i = 0; i < 5; i++) {
            if (i < titulares.size()) {
                Piloto p = titulares.get(i);
                lblTitulos[i].setVisible(true);
                lblNomes[i].setVisible(true);
                lblNumeros[i].setVisible(true);
                lblBandeiras[i].setVisible(true);
                lblIdades[i].setVisible(true);

                if (SessaoJogo.categoriaKey.contains("f1") && i >= 2) {
                    lblTitulos[i].setText("Reserva");
                    lblTitulos[i].setForeground(Color.GRAY);
                } else {
                    lblTitulos[i].setText("Piloto " + (i + 1));
                    lblTitulos[i].setForeground(Color.BLACK);
                }

                lblNomes[i].setText(p.getNome());
                lblNumeros[i].setText("#" + p.getNumero());
                
                carregarImagem(lblBandeiras[i], "/resource/Bandeira " + p.getNacionalidade() + ".svg");
            } else {
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

                // SE VIER COM .PNG, TROCA POR .SVG
                if (path.toLowerCase().endsWith(".png")) {
                    path = path.substring(0, path.length() - 4) + ".svg";
                }
                if (!path.toLowerCase().endsWith(".svg")) {
                    path = path + ".svg";
                }

                String svgPath = path.startsWith("/") ? path.substring(1) : path;
                
                // --- FIX: LÓGICA DE TAMANHO COM MARGEM DE 1PX ---
                int larguraLabel = lbl.getWidth();
                int alturaLabel = lbl.getHeight();

                if (larguraLabel > 0 && alturaLabel > 0) {
                    // Pega o menor lado para garantir quadrado
                    int menorLado = Math.min(larguraLabel, alturaLabel);
                    
                    // Subtrai 1 para garantir a folga solicitada (ex: 15x20 -> menor 15 -> 14x14)
                    int ladoFinal = Math.max(1, menorLado - 1); 
                    
                    lbl.setIcon(new FlatSVGIcon(svgPath, ladoFinal, ladoFinal));
                } else {
                    lbl.setIcon(new FlatSVGIcon(svgPath));
                }
                
            } else {
                lbl.setIcon(null);
            }
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    private void iniciarJogo() {
        if (tfNomeDirigente.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite o nome do Dirigente!", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (equipeSelecionadaObj == null) {
             JOptionPane.showMessageDialog(this, "Selecione uma equipe para continuar!", "Atenção", JOptionPane.WARNING_MESSAGE);
             return;
        }
        
        String nomeDirigenteInput = tfNomeDirigente.getText();
        String nomeEquipeEscolhida = equipeSelecionadaObj.getNome(); 
        
        DadosDoJogo dados = new DadosDoJogo(SessaoJogo.categoriaKey, SessaoJogo.anoSelecionado);
        dados.definirEquipeDoJogador(nomeEquipeEscolhida, nomeDirigenteInput);
        
        if (dados.getEquipeDoJogador() == null) {
            JOptionPane.showMessageDialog(this, "Erro Crítico: Equipe selecionada não encontrada no carregamento global.");
            return;
        }

        TelaPrincipal telaPrincipal = new TelaPrincipal(dados);
        telaPrincipal.setVisible(true);
        telaPrincipal.setLocationRelativeTo(null);
        
        this.dispose();
    }
}
package telas;

import com.formdev.flatlaf.FlatLightLaf;
import dados.CarregadorJSON;
import dados.SessaoJogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TelaSelecionarCategoria extends JFrame {

    private JPanel contentPane;
    
    // Variáveis Estáticas
    public static String CATEGORIA_SELECIONADA = "Fórmula 1";
    public static String TEMPORADA_SELECIONADA = "";
    public static String IMAGEM_SELECIONADA = "/resource/Logo F1 Novo_OK.png";

    // Mapa com os mods carregados do disco
    private Map<String, List<String>> modsDisponiveis;

    // Componentes
    private JRadioButton rbF1;
    private JRadioButton rbIndy;
    private JRadioButton rbNascar;
    private JRadioButton rbOutro;
    
    private JLabel lbCarro;
    private JLabel lbLogo;
    
    private JLabel lblSelecioneCat; 
    private JLabel lblSelecioneTemp; 
    
    private JComboBox<String> cbCategoriasOutras;
    private JComboBox<String> cbListaTemp;
    private JButton btnComecar;
    private JButton btnVoltar;

    // Constantes de Altura da Janela
    private static final int ALTURA_NORMAL = 645;
    private static final int ALTURA_EXPANDIDA = 680;

    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
        } catch (Exception ex) {}

        EventQueue.invokeLater(() -> {
            try {
                TelaSelecionarCategoria frame = new TelaSelecionarCategoria();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public TelaSelecionarCategoria() {
        modsDisponiveis = CarregadorJSON.escanearModsInstalados();

        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSelecionarCategoria.class.getResource("/resource/Icone16px.png")));
        setTitle("Grid Boss");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Inicia com o tamanho normal (sem o combo extra)
        setBounds(100, 100, 500, ALTURA_NORMAL);
        setResizable(false);
        
        // Listener para configuração inicial
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                selecionarPrimeiraCategoriaDisponivel();
            }
        });

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo (Topo)
        lbLogo = new JLabel("");
        lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lbLogo.setBounds(20, 11, 450, 148);
        lbLogo.setFont(new Font("Castellar", Font.BOLD, 48)); 
        contentPane.add(lbLogo);

        // Carro (Centro)
        lbCarro = new JLabel("");
        lbCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lbCarro.setBounds(20, 157, 450, 274);
        contentPane.add(lbCarro);

        lblSelecioneCat = new JLabel("SELECIONE A CATEGORIA");
        lblSelecioneCat.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneCat.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneCat.setBounds(20, 442, 450, 27);
        contentPane.add(lblSelecioneCat);

        // --- RADIO BUTTONS ---
        
        // F1 (x=10)
        rbF1 = new JRadioButton("FÓRMULA 1");
        configurarRadioButton(rbF1, 10, "f1", "Fórmula 1", "/resource/Logo F1 Novo_OK.png", "/resource/F1 ");
        
        // INDY (x=125)
        rbIndy = new JRadioButton("INDY");
        configurarRadioButton(rbIndy, 125, "indy", "Fórmula INDY", "/resource/Logo Indy_OK.png", "/resource/Indy ");
        
        // NASCAR (x=240)
        rbNascar = new JRadioButton("NASCAR");
        configurarRadioButton(rbNascar, 240, "nascar", "NASCAR", "/resource/Logo Nascar_OK.png", "/resource/Cup Series ");

        // OUTRO (x=355)
        rbOutro = new JRadioButton("OUTRO");
        rbOutro.setBackground(new Color(255, 255, 255));
        rbOutro.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rbOutro.setHorizontalAlignment(SwingConstants.CENTER);
        rbOutro.setBounds(355, 476, 103, 21);
        contentPane.add(rbOutro);

        ButtonGroup grupoBotoes = new ButtonGroup();
        grupoBotoes.add(rbF1);
        grupoBotoes.add(rbIndy);
        grupoBotoes.add(rbNascar);
        grupoBotoes.add(rbOutro);
        
        // --- COMBOBOX OUTROS ---
        cbCategoriasOutras = new JComboBox<>();
        cbCategoriasOutras.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbCategoriasOutras.setBounds(20, 505, 450, 21);
        cbCategoriasOutras.setVisible(false);
        contentPane.add(cbCategoriasOutras);

        // Lógica para popular e configurar o botão "Outro"
        configurarLogicaOutros();

        // --- TEMPORADA ---
        lblSelecioneTemp = new JLabel("SELECIONE A TEMPORADA");
        lblSelecioneTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneTemp.setBounds(20, 505, 450, 27); 
        contentPane.add(lblSelecioneTemp);

        cbListaTemp = new JComboBox<>();
        cbListaTemp.setMaximumRowCount(6);
        cbListaTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbListaTemp.setBounds(20, 535, 450, 21);
        contentPane.add(cbListaTemp);

        // --- BOTÕES NAVEGAÇÃO ---
        btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnVoltar.setBounds(20, 575, 172, 21);
        btnVoltar.addActionListener(e -> {
            TelaInicialCarregar telaInicial = new TelaInicialCarregar();
            telaInicial.setVisible(true);
            telaInicial.setLocationRelativeTo(null);
            dispose();
        });
        contentPane.add(btnVoltar);

        btnComecar = new JButton("ESCOLHER EQUIPE");
        btnComecar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnComecar.setBounds(298, 575, 172, 21);
        btnComecar.addActionListener(e -> irParaEquipes());
        contentPane.add(btnComecar);
    }

    private void ajustarLayout(boolean mostrarComboExtra) {
        cbCategoriasOutras.setVisible(mostrarComboExtra);

        if (mostrarComboExtra) {
            // Expande janela e desce componentes
            setSize(500, ALTURA_EXPANDIDA); 
            
            lblSelecioneTemp.setBounds(20, 535, 450, 27);
            cbListaTemp.setBounds(20, 565, 450, 21);
            btnVoltar.setBounds(20, 605, 172, 21);
            btnComecar.setBounds(298, 605, 172, 21);
        } else {
            // Contrai janela e sobe componentes
            setSize(500, ALTURA_NORMAL);
            
            lblSelecioneTemp.setBounds(20, 505, 450, 27);
            cbListaTemp.setBounds(20, 535, 450, 21);
            btnVoltar.setBounds(20, 575, 172, 21);
            btnComecar.setBounds(298, 575, 172, 21);
        }
    }

    private void configurarRadioButton(JRadioButton rb, int x, String keyMod, String nomeCategoria, String pathLogo, String pathCarroPrefix) {
        rb.setBackground(new Color(255, 255, 255));
        rb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rb.setHorizontalAlignment(SwingConstants.CENTER);
        rb.setBounds(x, 476, 103, 21);
        
        List<String> anosDisponiveis = modsDisponiveis.get(keyMod);
        
        if (anosDisponiveis == null || anosDisponiveis.isEmpty()) {
            rb.setEnabled(false);
            rb.setToolTipText("Nenhum mod encontrado.");
        } else {
            rb.addActionListener(e -> {
                ajustarLayout(false);
                atualizarSelecaoPadrao(keyMod, nomeCategoria, pathLogo, pathCarroPrefix, anosDisponiveis);
            });
        }
        
        contentPane.add(rb);
    }

    private void configurarLogicaOutros() {
        List<String> categoriasExtras = new ArrayList<>();
        
        for (String key : modsDisponiveis.keySet()) {
            if (!key.equalsIgnoreCase("f1") && 
                !key.equalsIgnoreCase("indy") && 
                !key.equalsIgnoreCase("nascar")) {
                categoriasExtras.add(key);
            }
        }

        if (categoriasExtras.isEmpty()) {
            rbOutro.setEnabled(false);
            rbOutro.setToolTipText("Nenhum mod extra encontrado na pasta /mods.");
        } else {
            for (String cat : categoriasExtras) {
                // AQUI: Adiciona no combo convertendo para MAIÚSCULO
                cbCategoriasOutras.addItem(cat.toUpperCase());
            }
            if (cbCategoriasOutras.getItemCount() > 0) {
                cbCategoriasOutras.setSelectedIndex(0);
            }

            rbOutro.addActionListener(e -> {
                ajustarLayout(true);
                dispararSelecaoOutros();
            });

            cbCategoriasOutras.addActionListener(e -> {
                if (rbOutro.isSelected()) {
                    dispararSelecaoOutros();
                }
            });
        }
    }

    private void dispararSelecaoOutros() {
        String itemSelecionado = (String) cbCategoriasOutras.getSelectedItem();
        
        if (itemSelecionado != null) {
            // A chave do mapa é minúscula (ex: "wec"), mas o item do combo está maiúsculo (ex: "WEC")
            // Precisamos converter para minúsculo para ACHAR no mapa
            String keyParaBusca = itemSelecionado.toLowerCase();
            List<String> anos = modsDisponiveis.get(keyParaBusca);
            
            // 1. lbCarro recebe o Banner F1
            try {
                lbCarro.setIcon(new ImageIcon(getClass().getResource("/resource/BannerOutro.png")));
            } catch (Exception ex) { lbCarro.setIcon(null); }

            // 2. lbLogo recebe o texto exatamente como está no combo (MAIÚSCULO)
            lbLogo.setIcon(null);
            //lbLogo.setIcon(new ImageIcon(getClass().getResource("/resource/BannerLogo.jpg")));
            lbLogo.setText(itemSelecionado); // "WEC"
            
            // 3. Atualiza variáveis e combo
            IMAGEM_SELECIONADA = "/resource/Banner F1_OK.png"; 
            atualizarComboAnos(anos);
            
            SessaoJogo.categoriaSelecionada = itemSelecionado; // Exibe "WEC"
            SessaoJogo.categoriaKey = keyParaBusca; // Carrega arquivo "wec_2023..."
        }
    }

    private void atualizarSelecaoPadrao(String keyMod, String nomeCategoria, String pathLogo, String pathCarroPrefix, List<String> anos) {
        CATEGORIA_SELECIONADA = nomeCategoria;
        IMAGEM_SELECIONADA = pathLogo;
        
        lbLogo.setText(""); 
        try {
            lbLogo.setIcon(new ImageIcon(getClass().getResource(pathLogo)));
        } catch (Exception ex) { lbLogo.setIcon(null); }
        
        int max = 7; 
        if (keyMod.equals("indy")) max = 8;
        int randomNum = new Random().nextInt(max) + 1;
        
        try {
            lbCarro.setIcon(new ImageIcon(getClass().getResource(pathCarroPrefix + randomNum + ".png")));
        } catch (Exception ex) { lbCarro.setIcon(null); }

        atualizarComboAnos(anos);
        
        SessaoJogo.categoriaSelecionada = nomeCategoria;
        SessaoJogo.categoriaKey = keyMod; 
    }

    private void atualizarComboAnos(List<String> anos) {
        cbListaTemp.removeAllItems();
        if (anos != null) {
            String[] listaAnos = new String[anos.size()];
            for (int i = 0; i < anos.size(); i++) {
                listaAnos[i] = "Temporada " + anos.get(i);
            }
            cbListaTemp.setModel(new DefaultComboBoxModel<>(listaAnos));
        }
    }

    private void selecionarPrimeiraCategoriaDisponivel() {
        if (rbF1.isEnabled()) {
            rbF1.doClick();
        } else if (rbIndy.isEnabled()) {
            rbIndy.doClick();
        } else if (rbNascar.isEnabled()) {
            rbNascar.doClick();
        } else if (rbOutro.isEnabled()) {
            rbOutro.doClick();
        } else {
            JOptionPane.showMessageDialog(this, "ERRO CRÍTICO:\nNenhum mod encontrado na pasta /mods!\nO jogo não pode continuar.", "Sem Mods", JOptionPane.ERROR_MESSAGE);
            btnComecar.setEnabled(false);
        }
    }

    private void irParaEquipes() {
        String itemSelecionado = (String) cbListaTemp.getSelectedItem();
        if (itemSelecionado != null) {
            String anoStr = itemSelecionado.replace("Temporada ", "").trim();
            SessaoJogo.anoSelecionado = Integer.parseInt(anoStr);
            TEMPORADA_SELECIONADA = itemSelecionado;

            TelaSelecionarEquipe telaEquipe = new TelaSelecionarEquipe();
            telaEquipe.setVisible(true);
            telaEquipe.setLocationRelativeTo(null);
            dispose();
        }
    }
}
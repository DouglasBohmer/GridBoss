package telas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;
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
    
    // Variáveis Estáticas Locais
    public static String CATEGORIA_SELECIONADA = "Fórmula 1";
    public static String TEMPORADA_SELECIONADA = "";
    
    // Mapa com os mods carregados do disco
    private Map<String, List<String>> modsDisponiveis;

    // Componentes
    private JRadioButton rbF1;
    private JRadioButton rbIndy;
    private JRadioButton rbNascar;
    private JRadioButton rbOutro;
    
    // REMOVIDO: private JLabel lbCarro;
    private JLabel lbLogo;
    
    private JLabel lblSelecioneCat; 
    private JLabel lblSelecioneTemp; 
    
    private JComboBox<String> cbCategoriasOutras;
    private JComboBox<String> cbListaTemp;
    private JButton btnComecar;
    private JButton btnVoltar;

    // NOVAS ALTURAS (Mais compactas)
    private static final int ALTURA_NORMAL = 400;
    private static final int ALTURA_EXPANDIDA = 440;

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

        configurarIconeJanela();
        
        setTitle("Grid Boss");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, ALTURA_NORMAL);
        setResizable(false);
        
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

        // Logo (Topo) - Mantido na posição original
        lbLogo = new JLabel("");
        lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lbLogo.setBounds(20, 11, 450, 148);
        lbLogo.setFont(new Font("Castellar", Font.BOLD, 48)); 
        contentPane.add(lbLogo);

        // REMOVIDO: lbCarro

        // Subiu de y=442 para y=179
        lblSelecioneCat = new JLabel("SELECIONE A CATEGORIA");
        lblSelecioneCat.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneCat.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneCat.setBounds(20, 179, 450, 27);
        contentPane.add(lblSelecioneCat);

        // --- RADIO BUTTONS ---
        // Subiram de y=476 para y=216
        rbF1 = new JRadioButton("FÓRMULA 1");
        configurarRadioButton(rbF1, 10, 216, "f1", "Fórmula 1", "/resource/Logo F1 Novo.svg");
        
        rbIndy = new JRadioButton("INDY");
        configurarRadioButton(rbIndy, 125, 216, "indy", "Fórmula INDY", "/resource/Logo Indy.svg");
        
        rbNascar = new JRadioButton("NASCAR");
        configurarRadioButton(rbNascar, 240, 216, "nascar", "NASCAR", "/resource/Logo Nascar.svg");

        rbOutro = new JRadioButton("OUTRO");
        rbOutro.setBackground(new Color(255, 255, 255));
        rbOutro.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rbOutro.setHorizontalAlignment(SwingConstants.CENTER);
        rbOutro.setBounds(355, 216, 103, 21); // y=216
        contentPane.add(rbOutro);

        ButtonGroup grupoBotoes = new ButtonGroup();
        grupoBotoes.add(rbF1);
        grupoBotoes.add(rbIndy);
        grupoBotoes.add(rbNascar);
        grupoBotoes.add(rbOutro);
        
        // --- COMBOBOX OUTROS ---
        // Posição inicial (invisível), referência para y=252
        cbCategoriasOutras = new JComboBox<>();
        cbCategoriasOutras.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbCategoriasOutras.setBounds(20, 252, 450, 21);
        cbCategoriasOutras.setVisible(false);
        contentPane.add(cbCategoriasOutras);

        configurarLogicaOutros();

        // --- TEMPORADA ---
        // Posição inicial Normal: y=252
        lblSelecioneTemp = new JLabel("SELECIONE A TEMPORADA");
        lblSelecioneTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneTemp.setBounds(20, 252, 450, 27); 
        contentPane.add(lblSelecioneTemp);

        // Posição inicial Normal: y=284
        cbListaTemp = new JComboBox<>();
        cbListaTemp.setMaximumRowCount(6);
        cbListaTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbListaTemp.setBounds(20, 284, 450, 21);
        contentPane.add(cbListaTemp);

        // --- BOTÕES NAVEGAÇÃO ---
        // Posição inicial Normal: y=325
        btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnVoltar.setBounds(20, 325, 172, 21);
        btnVoltar.addActionListener(e -> {
            TelaInicialCarregar telaInicial = new TelaInicialCarregar();
            telaInicial.setVisible(true);
            telaInicial.setLocationRelativeTo(null);
            dispose();
        });
        contentPane.add(btnVoltar);

        btnComecar = new JButton("ESCOLHER EQUIPE");
        btnComecar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnComecar.setBounds(298, 325, 172, 21);
        btnComecar.addActionListener(e -> irParaEquipes());
        contentPane.add(btnComecar);
    }

    private void configurarIconeJanela() {
        try {
            String path = "/resource/Icone.svg";
            java.net.URL url = getClass().getResource(path);
            
            if (url == null) {
                System.err.println("ERRO: O Java não encontrou o arquivo: " + path);
                return; 
            }

            java.awt.Image icon = FlatSVGUtils.svg2image(path, 32, 32);
            if (icon != null) {
                setIconImage(icon);
            }
            
        } catch (Exception e) {
            System.err.println("Falha ao definir ícone da janela: " + e.getMessage());
        }
    }

    private void ajustarLayout(boolean mostrarComboExtra) {
        cbCategoriasOutras.setVisible(mostrarComboExtra);

        if (mostrarComboExtra) {
            // Expandido (Tem o combo "Outros")
            setSize(500, ALTURA_EXPANDIDA); 
            
            // Empurra tudo pra baixo
            lblSelecioneTemp.setBounds(20, 288, 450, 27);
            cbListaTemp.setBounds(20, 320, 450, 21);
            
            btnVoltar.setBounds(20, 361, 172, 21);
            btnComecar.setBounds(298, 361, 172, 21);
        } else {
            // Normal
            setSize(500, ALTURA_NORMAL);
            
            // Puxa de volta pra cima
            lblSelecioneTemp.setBounds(20, 252, 450, 27);
            cbListaTemp.setBounds(20, 284, 450, 21);
            
            btnVoltar.setBounds(20, 325, 172, 21);
            btnComecar.setBounds(298, 325, 172, 21);
        }
    }

    // Atualizei para receber o Y
    private void configurarRadioButton(JRadioButton rb, int x, int y, String keyMod, String nomeCategoria, String pathLogo) {
        rb.setBackground(new Color(255, 255, 255));
        rb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rb.setHorizontalAlignment(SwingConstants.CENTER);
        rb.setBounds(x, y, 103, 21);
        
        List<String> anosDisponiveis = modsDisponiveis.get(keyMod);
        
        if (anosDisponiveis == null || anosDisponiveis.isEmpty()) {
            rb.setEnabled(false);
            rb.setToolTipText("Nenhum mod encontrado.");
        } else {
            rb.addActionListener(e -> {
                ajustarLayout(false);
                atualizarSelecaoPadrao(keyMod, nomeCategoria, pathLogo, anosDisponiveis);
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
            String keyParaBusca = itemSelecionado.toLowerCase();
            List<String> anos = modsDisponiveis.get(keyParaBusca);
            
            // lbCarro removido daqui

            lbLogo.setIcon(null);
            lbLogo.setText(itemSelecionado); 
            
            SessaoJogo.IMAGEM_SELECIONADA = "/resource/Banner F1.svg"; 
            
            atualizarComboAnos(anos);
            
            SessaoJogo.categoriaSelecionada = itemSelecionado; 
            SessaoJogo.categoriaKey = keyParaBusca; 
        }
    }

    // Removi o pathCarroPrefix pois não usamos mais
    private void atualizarSelecaoPadrao(String keyMod, String nomeCategoria, String pathLogo, List<String> anos) {
        CATEGORIA_SELECIONADA = nomeCategoria;
        SessaoJogo.IMAGEM_SELECIONADA = pathLogo;
        
        lbLogo.setText(""); 
        carregarImagem(lbLogo, pathLogo);
        
        // lbCarro removido daqui

        atualizarComboAnos(anos);
        
        SessaoJogo.categoriaSelecionada = nomeCategoria;
        SessaoJogo.categoriaKey = keyMod; 
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

                finalW = Math.max(1, finalW - 2);
                finalH = Math.max(1, finalH - 2);

                lbl.setIcon(new FlatSVGIcon(svgPath, finalW, finalH));
                
            } else {
                lbl.setIcon(new FlatSVGIcon(svgPath));
            }

        } catch (Exception e) {
            lbl.setIcon(null);
        }
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
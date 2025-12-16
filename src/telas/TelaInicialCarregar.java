package telas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;   // Import necessário
import com.formdev.flatlaf.extras.FlatSVGUtils;  // Import necessário
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import dados.DadosDoJogo; 
import javax.swing.filechooser.FileNameExtensionFilter;

public class TelaInicialCarregar extends JFrame {

    private JPanel contentPane;
    public static File ArquivoCarregado; 

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10); 
        } catch (Exception ex) {
            System.err.println("Erro ao iniciar FlatLaf");
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaInicialCarregar frame = new TelaInicialCarregar();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); 
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public TelaInicialCarregar() {
        // 1. Carrega o ícone da janela em SVG (com verificação de segurança)
        configurarIconeJanela();
        
        setTitle("Grid Boss");
        setBounds(100, 100, 487, 450); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); 
        setContentPane(contentPane);

        // --- TÍTULO ---
        JLabel lblTitulo = new JLabel("GRID BOSS");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblTitulo.setBounds(10, 10, 451, 36);
        contentPane.add(lblTitulo);

        // --- BANNER / IMAGEM ---
        JLabel lblBanner = new JLabel("");
        lblBanner.setHorizontalAlignment(SwingConstants.CENTER);
        lblBanner.setVerticalAlignment(SwingConstants.BOTTOM);
        lblBanner.setBounds(10, 56, 451, 170);
        
        // 2. Carrega o Banner em SVG com redimensionamento automático
        carregarImagem(lblBanner, "/resource/Banner F1_OK.svg");
        
        contentPane.add(lblBanner);

        // --- BOTÕES ---
        int btnX = 150;
        int btnH = 25; 
        int startY = 240; 
        int gap = 35;     

        // 1. INICIAR NOVO JOGO
        JButton btnNovoJogo = new JButton("INICIAR NOVO JOGO");
        btnNovoJogo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
        btnNovoJogo.setBounds(btnX, startY, 185, btnH);
        
        btnNovoJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                irParaSelecaoCategoria();
            }
        });
        contentPane.add(btnNovoJogo);

        // 2. CARREGAR JOGO
        JButton btnCarregar = new JButton("CARREGAR JOGO SALVO");
        btnCarregar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
        btnCarregar.setBounds(btnX, startY + gap, 185, btnH);
        
        btnCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarJogoAction();
            }
        });
        contentPane.add(btnCarregar);

        // 3. ADICIONAR MODS 
        JButton btnMods = new JButton("ADICIONAR MODS");
        btnMods.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
        
        btnMods.setBounds(btnX, startY + (gap * 2), 185, btnH);
        btnMods.setEnabled(false);
        btnMods.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Funcionalidade de Mods em breve!");
            }
        });
        contentPane.add(btnMods);

        // 4. SAIR
        JButton btnSair = new JButton("SAIR");
        btnSair.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
        btnSair.setBounds(btnX, startY + (gap * 3), 185, btnH);
        
        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(btnSair);
        
        // --- RODAPÉ ---
        JLabel lblVersao = new JLabel("V 0.0.3");
        lblVersao.setFont(new Font("Berlin Sans FB", Font.PLAIN, 10));
        lblVersao.setForeground(Color.GRAY);
        lblVersao.setBounds(10, 390, 100, 14);
        contentPane.add(lblVersao);
    }

    // --- MÉTODOS AUXILIARES (SVG) ---

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

    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path == null || path.isEmpty()) {
                lbl.setIcon(null);
                return;
            }

            // Tratamento do caminho
            if (!path.startsWith("/")) path = "/" + path;
            if (!path.startsWith("/resource")) path = "/resource" + path;
            path = path.replace("//", "/");

            // Garante .svg
            if (path.toLowerCase().endsWith(".png")) {
                path = path.substring(0, path.length() - 4) + ".svg";
            }
            if (!path.toLowerCase().endsWith(".svg")) {
                path = path + ".svg";
            }

            String svgPath = path.startsWith("/") ? path.substring(1) : path;

            // Dimensões
            int labelW = lbl.getWidth();
            int labelH = lbl.getHeight();

            if (labelW > 0 && labelH > 0) {
                // Lê o tamanho original do SVG
                FlatSVGIcon iconOriginal = new FlatSVGIcon(svgPath);
                
                if (iconOriginal.getIconWidth() <= 0) {
                    lbl.setIcon(null);
                    return;
                }

                float origW = iconOriginal.getIconWidth();
                float origH = iconOriginal.getIconHeight();

                // Matemática de Ajuste (Scale to Fit)
                float ratioW = (float) labelW / origW;
                float ratioH = (float) labelH / origH;
                float scale = Math.min(ratioW, ratioH);

                int finalW = Math.round(origW * scale);
                int finalH = Math.round(origH * scale);

                // Margem segura
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

    // --- AÇÕES ---

    private void irParaSelecaoCategoria() {
        TelaSelecionarCategoria tela = new TelaSelecionarCategoria();
        tela.setVisible(true);
        tela.setLocationRelativeTo(null); 
        this.dispose(); 
    }

    private void carregarJogoAction() {
        TelaCarregarJogo tela = new TelaCarregarJogo(this);
        tela.setLocationRelativeTo(this);
        tela.setVisible(true);
    }
}
package telas;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class TelaInicialCarregar extends JFrame {

    private JPanel contentPane;
    public static File ArquivoCarregado; // Para uso futuro

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Configuração do Tema (Claro)
        try {
            FlatLightLaf.setup();
            // Ajustes opcionais para combinar com o estilo "compacto" do seu layout original
            UIManager.put("Button.arc", 10); 
        } catch (Exception ex) {
            System.err.println("Erro ao iniciar FlatLaf");
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaInicialCarregar frame = new TelaInicialCarregar();
                    frame.setVisible(true);
                    frame.setLocationRelativeTo(null); // Centraliza na tela
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
        setTitle("Grid Boss");
        // Ajustei a altura (450) para caber os novos botões sem apertar
        setBounds(100, 100, 487, 450); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // Layout Absoluto (Fiel ao original)
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
        
        try {
            // PNG (Original recuperado do seu código)
            lblBanner.setIcon(new ImageIcon(getClass().getResource("/resource/Banner F1_OK.png")));
            
            // SVG (Futuro - Comentado)
            // lblBanner.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/Banner F1_OK.svg"));
        } catch (Exception e) {
            // Se falhar, não quebra a tela
        }
        contentPane.add(lblBanner);

        // --- BOTÕES ---
        // Mantendo a largura (185) e altura (21) do seu original
        // Centralizando matematicamente: (487 largura total - 185 botão) / 2 ~= 150 (X do seu código)
        int btnX = 150;
        int btnH = 25; // Aumentei levemente de 21 pra 25 pra caber melhor a fonte "Berlin" no FlatLaf
        int startY = 240; // Começando logo abaixo do banner
        int gap = 35;     // Espaço entre botões

        // 1. INICIAR NOVO JOGO
        JButton btnNovoJogo = new JButton("INICIAR NOVO JOGO");
        btnNovoJogo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
        btnNovoJogo.setBounds(btnX, startY, 185, btnH);
        
        // Ícones (Opcionais, adicionei placeholders caso queira usar)
        // btnNovoJogo.setIcon(new ImageIcon(getClass().getResource("/resource/IconeCorrida16px.png")));
        // btnNovoJogo.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/IconeCorrida16px.svg"));

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

        // 3. ADICIONAR MODS (Novo)
        JButton btnMods = new JButton("ADICIONAR MODS");
        btnMods.setFont(new Font("Berlin Sans FB", Font.PLAIN, 12));
        btnMods.setBounds(btnX, startY + (gap * 2), 185, btnH);
        
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

    // --- AÇÕES ---

    private void irParaSelecaoCategoria() {
        TelaSelecionarCategoria tela = new TelaSelecionarCategoria();
        tela.setVisible(true);
        tela.setLocationRelativeTo(null); // Centraliza na tela
        this.dispose(); // Fecha a tela de carregamento
    }

    private void carregarJogoAction() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Save Game JSON", "json"));
        int retorno = fileChooser.showOpenDialog(this);
        
        if (retorno == JFileChooser.APPROVE_OPTION) {
            ArquivoCarregado = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Carregando: " + ArquivoCarregado.getName());
        }
    }
}
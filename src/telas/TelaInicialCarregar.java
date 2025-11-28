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
    public static File ArquivoCarregado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Forçar tema CLARO (Light)
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10); // Arredondamento suave nos botões
        } catch (Exception ex) {
            System.err.println("Erro ao carregar tema.");
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
        setTitle("Grid Boss");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false); // Mantém o tamanho fixo como no original
        setBounds(100, 100, 977, 674); // Dimensões originais exatas

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null); // Layout absoluto para fidelidade total
        setContentPane(contentPane);

        // --- BANNER DO JOGO ---
        JLabel lbBanner = new JLabel("");
        lbBanner.setHorizontalAlignment(SwingConstants.CENTER);
        lbBanner.setBounds(300, 77, 350, 160);
        
        try {
            // PNG
            lbBanner.setIcon(new ImageIcon(getClass().getResource("/resource/Banner Jogo.png")));
            // SVG
            // lbBanner.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/Banner Jogo.svg"));
        } catch (Exception e) {
            lbBanner.setText("GRID BOSS"); // Fallback se a imagem falhar
            lbBanner.setFont(new Font("Arial", Font.BOLD, 40));
        }
        contentPane.add(lbBanner);

        // --- BOTÃO: INICIAR NOVO JOGO ---
        JButton btnNovoJogo = new JButton("Iniciar Novo Jogo");
        btnNovoJogo.setFont(new Font("Segoe UI", Font.BOLD, 12)); // Fonte moderna mas tamanho original
        btnNovoJogo.setBounds(365, 308, 225, 40);
        
        try {
            // PNG
            btnNovoJogo.setIcon(new ImageIcon(getClass().getResource("/resource/IconeCorrida16px.png")));
            // SVG
            // btnNovoJogo.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/IconeCorrida16px.svg"));
        } catch (Exception e) { }

        btnNovoJogo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                irParaSelecaoCategoria();
            }
        });
        contentPane.add(btnNovoJogo);

        // --- BOTÃO: CARREGAR JOGO ---
        JButton btnCarregar = new JButton("Carregar Jogo");
        btnCarregar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCarregar.setBounds(365, 359, 225, 40); // +51px de Y
        
        try {
            // PNG
            btnCarregar.setIcon(new ImageIcon(getClass().getResource("/resource/Icone16px.png")));
            // SVG
            // btnCarregar.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/Icone16px.svg"));
        } catch (Exception e) { }

        btnCarregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                carregarJogoAction();
            }
        });
        contentPane.add(btnCarregar);

        // --- BOTÃO: ADICIONAR MODS (NOVO) ---
        JButton btnMods = new JButton("Adicionar Mods");
        btnMods.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnMods.setBounds(365, 410, 225, 40); // Inserido na sequência (+51px de Y)
        
        try {
            // PNG (Reutilizando ícone de equipe que faz sentido para mods)
            btnMods.setIcon(new ImageIcon(getClass().getResource("/resource/Icone24pxEquipe.png"))); 
            // SVG
            // btnMods.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/Icone24pxEquipe.svg").derive(16, 16));
        } catch (Exception e) { }

        btnMods.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Coloque os arquivos JSON na pasta /mods do jogo.");
            }
        });
        contentPane.add(btnMods);

        // --- BOTÃO: SAIR ---
        JButton btnSair = new JButton("Sair");
        btnSair.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnSair.setBounds(365, 461, 225, 40); // Empurrado para baixo (+51px do botão de mods)
        
        try {
            // PNG
            btnSair.setIcon(new ImageIcon(getClass().getResource("/resource/Icone16pxErro.png")));
            // SVG
            // btnSair.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/Icone16pxErro.svg"));
        } catch (Exception e) { }

        btnSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        contentPane.add(btnSair);

        // --- RODAPÉ: VERSÃO ---
        JLabel lbVersao = new JLabel("V 0.0.2");
        lbVersao.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbVersao.setBounds(10, 610, 137, 14);
        contentPane.add(lbVersao);
    }

    // --- MÉTODOS DE NAVEGAÇÃO ---

    private void irParaSelecaoCategoria() {
        // SelecionarCategoria tela = new SelecionarCategoria();
        // tela.setVisible(true);
        // tela.setLocationRelativeTo(null);
        // this.dispose();
        JOptionPane.showMessageDialog(this, "Indo para Seleção de Categoria...");
    }

    private void carregarJogoAction() {
        JFileChooser fileChooser = new JFileChooser();
        // Filtra apenas arquivos JSON (formato novo)
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Arquivos de Save (.json)", "json"));
        
        int retorno = fileChooser.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            ArquivoCarregado = fileChooser.getSelectedFile();
            JOptionPane.showMessageDialog(this, "Carregando: " + ArquivoCarregado.getName());
            // Lógica de carregar via JSON entraria aqui
        }
    }
}
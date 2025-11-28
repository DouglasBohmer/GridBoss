package telas;

import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class TelaSelecionarCategoria extends JFrame {

    private JPanel contentPane;
    
    // Variáveis Estáticas (Mantendo sua lógica de compartilhamento de dados)
    public static String CATEGORIA_SELECIONADA = "Fórmula 1";
    public static String TEMPORADA_SELECIONADA = "Temporada 2024";
    public static String IMAGEM_SELECIONADA = "/resource/Logo F1 Novo_OK.png";

    // Componentes
    private JRadioButton rbF1;
    private JRadioButton rbIndy;
    private JRadioButton rbNascar;
    private JLabel lbCarro;
    private JLabel lbLogo;
    private JComboBox<String> cbListaTemp;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Configuração do FlatLaf
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 10);
            UIManager.put("Component.arc", 10);
        } catch (Exception ex) {
            System.err.println("Erro ao iniciar FlatLaf");
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TelaSelecionarCategoria frame = new TelaSelecionarCategoria();
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
    public TelaSelecionarCategoria() {
        // Configurações da Janela
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSelecionarCategoria.class.getResource("/resource/Icone16px.png")));
        setTitle("Motorsport Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 716);
        setResizable(false);
        
        // Listener para resetar a seleção ao abrir
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                rbF1.setSelected(true);
                CATEGORIA_SELECIONADA = "Fórmula 1";
                IMAGEM_SELECIONADA = "/resource/Logo F1 Novo_OK.png";
            }
        });

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // --- COMPONENTES ---

        // Título Principal
        JLabel lblTitulo = new JLabel("MOTORSPORT MANAGER");
        lblTitulo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(25, 10, 460, 36);
        contentPane.add(lblTitulo);

        // Logo da Categoria
        lbLogo = new JLabel("");
        lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lbLogo.setBounds(20, 56, 450, 148);
        try {
            lbLogo.setIcon(new ImageIcon(getClass().getResource("/resource/Logo F1 Novo_OK.png")));
            // SVG:
            // lbLogo.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/Logo F1 Novo_OK.svg"));
        } catch (Exception e) {}
        contentPane.add(lbLogo);

        // Imagem do Carro (Dinâmica)
        lbCarro = new JLabel("");
        lbCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lbCarro.setBounds(20, 214, 450, 274);
        try {
            lbCarro.setIcon(new ImageIcon(getClass().getResource("/resource/F1 1.png")));
            // SVG:
            // lbCarro.setIcon(new com.formdev.flatlaf.extras.FlatSVGIcon("resource/F1 1.svg"));
        } catch (Exception e) {}
        contentPane.add(lbCarro);

        // Label Categoria
        JLabel lblSelecioneCat = new JLabel("SELECIONE A CATEGORIA");
        lblSelecioneCat.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneCat.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneCat.setBounds(20, 498, 450, 27);
        contentPane.add(lblSelecioneCat);

        // --- RADIO BUTTONS ---
        
        // F1
        rbF1 = new JRadioButton("FÓRMULA 1");
        rbF1.setBackground(new Color(255, 255, 255));
        rbF1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rbF1.setHorizontalAlignment(SwingConstants.CENTER);
        rbF1.setSelected(true);
        rbF1.setBounds(20, 543, 103, 21);
        rbF1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CATEGORIA_SELECIONADA = "Fórmula 1";
                IMAGEM_SELECIONADA = "/resource/Logo F1 Novo_OK.png";
                
                // Lógica de Randomização do Carro
                int randomF1 = new Random().nextInt(7); // 0 a 6
                try {
                    lbCarro.setIcon(new ImageIcon(getClass().getResource("/resource/F1 " + (randomF1 + 1) + ".png")));
                    lbLogo.setIcon(new ImageIcon(getClass().getResource("/resource/Logo F1 Novo_OK.png")));
                } catch (Exception ex) {}

                // Atualiza Anos F1
                cbListaTemp.setModel(new DefaultComboBoxModel<>(new String[] {
                    "Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021"
                }));
            }
        });
        contentPane.add(rbF1);

        // INDY
        rbIndy = new JRadioButton("INDY");
        rbIndy.setBackground(new Color(255, 255, 255));
        rbIndy.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rbIndy.setHorizontalAlignment(SwingConstants.CENTER);
        rbIndy.setBounds(197, 543, 103, 21);
        rbIndy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CATEGORIA_SELECIONADA = "Fórmula INDY";
                IMAGEM_SELECIONADA = "/resource/Logo Indy_OK.png";
                
                int randomIndy = new Random().nextInt(8);
                try {
                    lbCarro.setIcon(new ImageIcon(getClass().getResource("/resource/Indy " + (randomIndy + 1) + ".png")));
                    lbLogo.setIcon(new ImageIcon(getClass().getResource("/resource/Logo Indy_OK.png")));
                } catch (Exception ex) {}

                // Atualiza Anos Indy
                cbListaTemp.setModel(new DefaultComboBoxModel<>(new String[] {"Temporada 2024"}));
            }
        });
        contentPane.add(rbIndy);

        // NASCAR
        rbNascar = new JRadioButton("NASCAR");
        rbNascar.setBackground(new Color(255, 255, 255));
        rbNascar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rbNascar.setHorizontalAlignment(SwingConstants.CENTER);
        rbNascar.setBounds(367, 543, 103, 21);
        rbNascar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CATEGORIA_SELECIONADA = "NASCAR";
                IMAGEM_SELECIONADA = "/resource/Logo Nascar_OK.png";
                
                int randomNascar = new Random().nextInt(7);
                try {
                    // Nota: O nome da imagem é "Cup Series X.png" segundo seu código original
                    lbCarro.setIcon(new ImageIcon(getClass().getResource("/resource/Cup Series " + (randomNascar + 1) + ".png")));
                    lbLogo.setIcon(new ImageIcon(getClass().getResource("/resource/Logo Nascar_OK.png")));
                } catch (Exception ex) {}

                // Atualiza Anos Nascar
                cbListaTemp.setModel(new DefaultComboBoxModel<>(new String[] {"Temporada 2024"}));
            }
        });
        contentPane.add(rbNascar);

        // Grupo de Botões (Para selecionar apenas um por vez)
        ButtonGroup grupoBotoes = new ButtonGroup();
        grupoBotoes.add(rbF1);
        grupoBotoes.add(rbIndy);
        grupoBotoes.add(rbNascar);

        // --- SELEÇÃO DE TEMPORADA ---
        
        JLabel lblSelecioneTemp = new JLabel("SELECIONE A TEMPORADA");
        lblSelecioneTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneTemp.setBounds(20, 570, 450, 27);
        contentPane.add(lblSelecioneTemp);

        cbListaTemp = new JComboBox<>();
        cbListaTemp.setMaximumRowCount(6);
        cbListaTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbListaTemp.setModel(new DefaultComboBoxModel<>(new String[] {
            "Temporada 2024", "Temporada 2023", "Temporada 2022", "Temporada 2021"
        }));
        cbListaTemp.setBounds(20, 607, 450, 21);
        contentPane.add(cbListaTemp);

        // --- BOTÕES DE NAVEGAÇÃO ---

        // Voltar
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnVoltar.setBounds(20, 638, 172, 21);
        btnVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TelaInicialCarregar telaInicial = new TelaInicialCarregar();
                telaInicial.setVisible(true);
                telaInicial.setLocationRelativeTo(null);
                dispose();
            }
        });
        contentPane.add(btnVoltar);

        // Escolher Equipe (Próxima Tela)
        JButton btnComecar = new JButton("ESCOLHER EQUIPE");
        btnComecar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnComecar.setBounds(298, 638, 172, 21);
        btnComecar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Salva a temporada selecionada
                TEMPORADA_SELECIONADA = "" + cbListaTemp.getSelectedItem();
                /*
                // Abre a próxima tela
                TelaSelecionarEquipe telaEquipe = new TelaSelecionarEquipe();
                telaEquipe.setVisible(true);
                telaEquipe.setLocationRelativeTo(null);
                dispose();
                */
            }
        });
        contentPane.add(btnComecar);
    }
}
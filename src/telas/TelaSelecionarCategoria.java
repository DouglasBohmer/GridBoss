package telas;

import com.formdev.flatlaf.FlatLightLaf;
import dados.CarregadorJSON;
import dados.SessaoJogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    private JLabel lbCarro;
    private JLabel lbLogo;
    private JComboBox<String> cbListaTemp;
    private JButton btnComecar;

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
        // --- ESCANEAMENTO DE MODS ---
        // Antes de desenhar a tela, vemos o que existe na pasta /mods
        modsDisponiveis = CarregadorJSON.escanearModsInstalados();

        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSelecionarCategoria.class.getResource("/resource/Icone16px.png")));
        setTitle("Motorsport Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 501, 716);
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

        // Título
        JLabel lblTitulo = new JLabel("MOTORSPORT MANAGER");
        lblTitulo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(25, 10, 460, 36);
        contentPane.add(lblTitulo);

        // Logo
        lbLogo = new JLabel("");
        lbLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lbLogo.setBounds(20, 56, 450, 148);
        contentPane.add(lbLogo);

        // Carro
        lbCarro = new JLabel("");
        lbCarro.setHorizontalAlignment(SwingConstants.CENTER);
        lbCarro.setBounds(20, 214, 450, 274);
        contentPane.add(lbCarro);

        JLabel lblSelecioneCat = new JLabel("SELECIONE A CATEGORIA");
        lblSelecioneCat.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneCat.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneCat.setBounds(20, 498, 450, 27);
        contentPane.add(lblSelecioneCat);

        // --- RADIO BUTTONS ---
        
        // F1
        rbF1 = new JRadioButton("FÓRMULA 1");
        configurarRadioButton(rbF1, 20, "f1", "Fórmula 1", "/resource/Logo F1 Novo_OK.png", "/resource/F1 ");
        
        // INDY
        rbIndy = new JRadioButton("INDY");
        configurarRadioButton(rbIndy, 197, "indy", "Fórmula INDY", "/resource/Logo Indy_OK.png", "/resource/Indy ");
        
        // NASCAR
        rbNascar = new JRadioButton("NASCAR");
        configurarRadioButton(rbNascar, 367, "nascar", "NASCAR", "/resource/Logo Nascar_OK.png", "/resource/Cup Series ");

        ButtonGroup grupoBotoes = new ButtonGroup();
        grupoBotoes.add(rbF1);
        grupoBotoes.add(rbIndy);
        grupoBotoes.add(rbNascar);

        // --- TEMPORADA ---
        JLabel lblSelecioneTemp = new JLabel("SELECIONE A TEMPORADA");
        lblSelecioneTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        lblSelecioneTemp.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelecioneTemp.setBounds(20, 570, 450, 27);
        contentPane.add(lblSelecioneTemp);

        cbListaTemp = new JComboBox<>();
        cbListaTemp.setMaximumRowCount(6);
        cbListaTemp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        cbListaTemp.setBounds(20, 607, 450, 21);
        contentPane.add(cbListaTemp);

        // --- BOTÕES NAVEGAÇÃO ---
        JButton btnVoltar = new JButton("VOLTAR");
        btnVoltar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnVoltar.setBounds(20, 638, 172, 21);
        btnVoltar.addActionListener(e -> {
            TelaInicialCarregar telaInicial = new TelaInicialCarregar();
            telaInicial.setVisible(true);
            telaInicial.setLocationRelativeTo(null);
            dispose();
        });
        contentPane.add(btnVoltar);

        btnComecar = new JButton("ESCOLHER EQUIPE");
        btnComecar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
        btnComecar.setBounds(298, 638, 172, 21);
        btnComecar.addActionListener(e -> irParaEquipes());
        contentPane.add(btnComecar);
    }

    /**
     * Configura o botão: Posição, Ação ao clicar e SE DEVE FICAR ATIVO (baseado nos mods).
     */
    private void configurarRadioButton(JRadioButton rb, int x, String keyMod, String nomeCategoria, String pathLogo, String pathCarroPrefix) {
        rb.setBackground(new Color(255, 255, 255));
        rb.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
        rb.setHorizontalAlignment(SwingConstants.CENTER);
        rb.setBounds(x, 543, 103, 21);
        
        // VERIFICAÇÃO CRUCIAL: Existem mods para essa categoria?
        List<String> anosDisponiveis = modsDisponiveis.get(keyMod);
        
        if (anosDisponiveis == null || anosDisponiveis.isEmpty()) {
            // Não tem mod: Desabilita
            rb.setEnabled(false);
            rb.setToolTipText("Nenhum mod encontrado na pasta /mods/" + keyMod + "_ANO");
        } else {
            // Tem mod: Adiciona listener
            rb.addActionListener(e -> {
                atualizarSelecao(keyMod, nomeCategoria, pathLogo, pathCarroPrefix, anosDisponiveis);
            });
        }
        
        contentPane.add(rb);
    }

    private void atualizarSelecao(String keyMod, String nomeCategoria, String pathLogo, String pathCarroPrefix, List<String> anos) {
        CATEGORIA_SELECIONADA = nomeCategoria;
        IMAGEM_SELECIONADA = pathLogo;
        
        // Randomizar Carro
        int max = keyMod.equals("indy") ? 8 : 7;
        int randomNum = new Random().nextInt(max) + 1;
        
        try {
            lbCarro.setIcon(new ImageIcon(getClass().getResource(pathCarroPrefix + randomNum + ".png")));
            lbLogo.setIcon(new ImageIcon(getClass().getResource(pathLogo)));
        } catch (Exception ex) {}

        // ATUALIZA O COMBOBOX COM OS ANOS DO MOD
        // Cria array de "Temporada 2024", "Temporada 2023"...
        String[] listaAnos = new String[anos.size()];
        for (int i = 0; i < anos.size(); i++) {
            listaAnos[i] = "Temporada " + anos.get(i);
        }
        cbListaTemp.setModel(new DefaultComboBoxModel<>(listaAnos));
        
        // Salva na memória global para a próxima tela
        SessaoJogo.categoriaSelecionada = nomeCategoria; // "Fórmula 1"
        // Salva a chave curta para o carregador usar ("f1")
        SessaoJogo.categoriaKey = keyMod; 
    }

    private void selecionarPrimeiraCategoriaDisponivel() {
        // Tenta selecionar automaticamente o primeiro botão que estiver ativo
        if (rbF1.isEnabled()) {
            rbF1.doClick();
        } else if (rbIndy.isEnabled()) {
            rbIndy.doClick();
        } else if (rbNascar.isEnabled()) {
            rbNascar.doClick();
        } else {
            // Caso extremo: Nenhum mod instalado
            JOptionPane.showMessageDialog(this, "ERRO CRÍTICO:\nNenhum mod encontrado na pasta /mods!\nO jogo não pode continuar.", "Sem Mods", JOptionPane.ERROR_MESSAGE);
            btnComecar.setEnabled(false);
        }
    }

    private void irParaEquipes() {
        String itemSelecionado = (String) cbListaTemp.getSelectedItem();
        if (itemSelecionado != null) {
            // Extrai apenas o número "2024" de "Temporada 2024"
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
package telas;

import dados.DadosDoJogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class TelaCarregarJogo extends JDialog {

    private final JPanel contentPane;
    private JList<String> listSaves;
    private DefaultListModel<String> listModel;
    private TelaPrincipal telaPrincipalPai; // Referência para poder fechar a tela de trás

    public TelaCarregarJogo(TelaPrincipal pai) {
        this.telaPrincipalPai = pai;
        
        setModal(true);
        setTitle("Grid Boss - Carregar Jogo");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaCarregarJogo.class.getResource("/resource/Icone16px.png")));
        setBounds(100, 100, 500, 400);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("CARREGAR JOGO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 20));
        lblTitulo.setBounds(10, 11, 464, 30);
        contentPane.add(lblTitulo);
        
        // Lista de Saves
        listModel = new DefaultListModel<>();
        listarSaves();
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(30, 50, 424, 230);
        contentPane.add(scrollPane);
        
        listSaves = new JList<>(listModel);
        listSaves.setFont(new Font("Arial", Font.PLAIN, 14));
        listSaves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        scrollPane.setViewportView(listSaves);
        
        // Botões
        JButton btnCarregar = new JButton("CARREGAR");
        btnCarregar.setBackground(new Color(173, 216, 230));
        btnCarregar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        btnCarregar.setBounds(284, 300, 170, 40);
        btnCarregar.addActionListener(e -> acaoCarregar());
        contentPane.add(btnCarregar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        btnCancelar.setBounds(30, 300, 170, 40);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);
    }
    
    private void listarSaves() {
        File folder = new File("saves");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".save"));
            
            if (files != null && files.length > 0) {
                for (File f : files) {
                    listModel.addElement(f.getName());
                }
            } else {
                listModel.addElement("Nenhum save encontrado.");
                listSaves.setEnabled(false);
            }
        } else {
            listModel.addElement("Pasta de saves não encontrada.");
            listSaves.setEnabled(false);
        }
    }
    
    private void acaoCarregar() {
        String selecionado = listSaves.getSelectedValue();
        if (selecionado == null || selecionado.equals("Nenhum save encontrado.")) {
            JOptionPane.showMessageDialog(this, "Selecione um arquivo para carregar!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // 1. Carrega o jogo usando a classe DadosDoJogo
        DadosDoJogo jogoCarregado = DadosDoJogo.carregarJogo(selecionado);
        
        if (jogoCarregado != null) {
            JOptionPane.showMessageDialog(this, "Jogo carregado com sucesso!");
            
            // 2. Abre a nova Tela Principal
            TelaPrincipal novaTela = new TelaPrincipal(jogoCarregado);
            novaTela.setVisible(true);
            novaTela.setLocationRelativeTo(null);
            
            // 3. Fecha janelas antigas
            this.dispose(); // Fecha o dialog
            if (telaPrincipalPai != null) {
                telaPrincipalPai.dispose(); // Fecha a tela principal antiga (menu)
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao carregar o arquivo. O save pode estar corrompido.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
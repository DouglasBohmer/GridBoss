package telas;

import dados.DadosDoJogo;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TelaSalvarJogo extends JDialog {

    private final JPanel contentPane;
    private JTextField txtNomeSave;
    private DadosDoJogo dadosDoJogo;

    public TelaSalvarJogo(DadosDoJogo dados) {
        this.dadosDoJogo = dados;
        
        setModal(true);
        setTitle("Grid Boss - Salvar Jogo");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TelaSalvarJogo.class.getResource("/resource/Icone16px.png")));
        setBounds(100, 100, 450, 220);
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitulo = new JLabel("SALVAR COMO");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 18));
        lblTitulo.setBounds(10, 11, 414, 30);
        contentPane.add(lblTitulo);
        
        JLabel lblInstrucao = new JLabel("Nome do arquivo:");
        lblInstrucao.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        lblInstrucao.setBounds(40, 60, 200, 20);
        contentPane.add(lblInstrucao);
        
        txtNomeSave = new JTextField();
        txtNomeSave.setFont(new Font("Arial", Font.PLAIN, 14));
        
        // Sugestão de nome (Se já tiver um arquivo salvo, sugere ele, senão sugere o nome do dirigente)
        if (dados.getArquivoAtual() != null) {
            txtNomeSave.setText(dados.getArquivoAtual());
        } else {
            String nomeLimpo = dados.getNomeDoDirigente().split(" ")[0];
            txtNomeSave.setText("Carreira " + nomeLimpo);
        }
        
        txtNomeSave.setBounds(40, 85, 360, 30);
        contentPane.add(txtNomeSave);
        txtNomeSave.setColumns(10);
        
        JButton btnSalvar = new JButton("SALVAR");
        btnSalvar.setBackground(new Color(144, 238, 144));
        btnSalvar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        btnSalvar.setBounds(230, 135, 170, 30);
        btnSalvar.addActionListener(e -> acaoSalvar());
        contentPane.add(btnSalvar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        btnCancelar.setBounds(40, 135, 170, 30);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);
    }
    
    private void acaoSalvar() {
        String nome = txtNomeSave.getText().trim();
        if(nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Digite um nome para o save!", "Erro", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Remove caracteres inválidos para nome de arquivo
        nome = nome.replaceAll("[^a-zA-Z0-9\\s-_]", "");
        
        // --- VERIFICAÇÃO SE EXISTE (NOVO) ---
        if (dadosDoJogo.verificarSeSaveExiste(nome)) {
            int resposta = JOptionPane.showConfirmDialog(this, 
                "O save '" + nome + "' já existe.\nDeseja substituir?",
                "Confirmar Sobrescrita",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );
            
            if (resposta != JOptionPane.YES_OPTION) {
                return; // Cancela se o usuário disser "Não"
            }
        }
        // -------------------------------------
        
        boolean sucesso = dadosDoJogo.salvarJogo(nome);
        
        if(sucesso) {
            JOptionPane.showMessageDialog(this, "Jogo salvo com sucesso em /saves/" + nome + ".save");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao salvar o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
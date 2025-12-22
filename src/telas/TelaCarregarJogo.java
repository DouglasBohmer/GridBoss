package telas;

import dados.DadosDoJogo;
import modelos.Equipe;
import servicos.CampeonatoService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.formdev.flatlaf.extras.FlatSVGUtils;

import java.awt.*;
import java.io.File;

public class TelaCarregarJogo extends JDialog {

    private final JPanel contentPane;
    private JList<ItemSave> listSaves;
    private DefaultListModel<ItemSave> listModel;
    private JFrame telaPai; // Mudamos de TelaPrincipal para JFrame (genérico)

    // Construtor aceita qualquer JFrame (TelaPrincipal ou TelaInicialCarregar)
    public TelaCarregarJogo(JFrame pai) {
        this.telaPai = pai;
        
        setModal(true);
        setTitle("Grid Boss - Carregar Jogo");
        try {
            java.awt.Image icon = FlatSVGUtils.svg2image("/resource/Icone.svg", 32, 32);
            if (icon != null) setIconImage(icon);
        } catch (Exception e) {
        }
        setBounds(100, 100, 650, 500); // Aumentei um tiquinho a altura
        setResizable(false);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // --- CABEÇALHO ---
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(Color.WHITE);
        headerPanel.setBounds(0, 0, 634, 60);
        headerPanel.setLayout(null);
        headerPanel.setBorder(new LineBorder(new Color(220, 220, 220), 1, false));
        contentPane.add(headerPanel);

        JLabel lblTitulo = new JLabel("SELECIONE UM SAVE");
        lblTitulo.setForeground(new Color(50, 50, 50));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 22));
        lblTitulo.setBounds(10, 15, 614, 30);
        headerPanel.add(lblTitulo);
        
        // --- LISTA ---
        listModel = new DefaultListModel<>();
        
        listSaves = new JList<>(listModel);
        listSaves.setCellRenderer(new SaveGameRenderer()); 
        listSaves.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSaves.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(listSaves);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        scrollPane.setBounds(30, 80, 574, 320);
        contentPane.add(scrollPane);
        
        carregarListaDeSaves();
        
        // --- BOTÕES ---
        JButton btnCarregar = new JButton("CARREGAR JOGO");
        btnCarregar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCarregar.setBackground(new Color(34, 139, 34));
        btnCarregar.setForeground(Color.WHITE);
        btnCarregar.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 16));
        btnCarregar.setBounds(384, 420, 220, 27);
        btnCarregar.setFocusPainted(false);
        btnCarregar.addActionListener(e -> acaoCarregar());
        contentPane.add(btnCarregar);
        
        JButton btnCancelar = new JButton("Voltar");
        btnCancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelar.setBackground(new Color(220, 220, 220));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        btnCancelar.setBounds(30, 420, 150, 27);
        btnCancelar.setFocusPainted(false);
        btnCancelar.addActionListener(e -> dispose());
        contentPane.add(btnCancelar);
        
        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnExcluir.setBackground(new Color(255, 99, 71));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        btnExcluir.setBounds(190, 420, 100, 27);
        btnExcluir.setFocusPainted(false);
        btnExcluir.addActionListener(e -> acaoExcluir());
        contentPane.add(btnExcluir);
    }
    
    // --- LÓGICA ---

    private void carregarListaDeSaves() {
        listModel.clear();
        File folder = new File("saves");
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".save"));
            
            if (files != null && files.length > 0) {
                for (File f : files) {
                    try {
                        DadosDoJogo tempDados = DadosDoJogo.carregarJogo(f.getName());
                        if (tempDados != null) {
                            Equipe eq = tempDados.getEquipeDoJogador();
                            CampeonatoService camp = tempDados.getCampeonato();
                            
                            // Extrai informações da etapa
                            String infoEtapa = "Pré-Temporada";
                            String nomePista = "---";
                            
                            if (camp != null) {
                                int atual = camp.getNumeroEtapaAtual();
                                int total = camp.getTotalEtapas();
                                infoEtapa = "Etapa " + atual + "/" + total;
                                
                                if (camp.getPistaAtual() != null) {
                                    nomePista = camp.getPistaAtual().getNome();
                                } else if (camp.isTemporadaFinalizada()) {
                                    infoEtapa = "Temporada Finalizada";
                                    nomePista = "Campeonato Encerrado";
                                }
                            }

                            listModel.addElement(new ItemSave(
                                f.getName(), 
                                tempDados.getCategoriaKey().toUpperCase(),
                                tempDados.getAnoAtual(),
                                (eq != null ? eq.getNome() : "Desconhecido"),
                                (eq != null ? eq.getSaldoFinanceiro() : 0.0),
                                tempDados.getNomeDoDirigente(),
                                infoEtapa, // Novo
                                nomePista  // Novo
                            ));
                        }
                    } catch (Exception e) {
                        System.err.println("Erro ao ler save: " + f.getName());
                    }
                }
            } else {
                listModel.addElement(new ItemSave(null, "", 0, "Nenhum save encontrado", 0, "", "", ""));
            }
        }
    }
    
    private void acaoCarregar() {
        ItemSave item = listSaves.getSelectedValue();
        if (item == null || item.nomeArquivo == null) {
            JOptionPane.showMessageDialog(this, "Selecione um save válido!", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        DadosDoJogo jogoCarregado = DadosDoJogo.carregarJogo(item.nomeArquivo);
        if (jogoCarregado != null) {
            // Abre a Tela Principal
            TelaPrincipal novaTela = new TelaPrincipal(jogoCarregado);
            novaTela.setVisible(true);
            novaTela.setLocationRelativeTo(null);
            
            // Fecha as janelas antigas
            this.dispose();
            if (telaPai != null) telaPai.dispose();
            
        } else {
            JOptionPane.showMessageDialog(this, "Erro crítico ao carregar o save.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void acaoExcluir() {
        ItemSave item = listSaves.getSelectedValue();
        if (item == null || item.nomeArquivo == null) return;
        
        int resp = JOptionPane.showConfirmDialog(this, "Tem certeza que deseja excluir '" + item.nomeArquivo + "'?", "Excluir Save", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            File f = new File("saves/" + item.nomeArquivo);
            if (f.delete()) {
                carregarListaDeSaves();
            } else {
                JOptionPane.showMessageDialog(this, "Não foi possível excluir o arquivo.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    // --- CLASSES AUXILIARES ---
    
    private static class ItemSave {
        String nomeArquivo;
        String categoria;
        int ano;
        String equipe;
        double saldo;
        String dirigente;
        String etapaStr; // "Etapa 1/24"
        String nomePista; // "GP do Bahrein"
        
        public ItemSave(String arquivo, String cat, int ano, String eq, double grana, String dir, String etapa, String pista) {
            this.nomeArquivo = arquivo;
            this.categoria = cat;
            this.ano = ano;
            this.equipe = eq;
            this.saldo = grana;
            this.dirigente = dir;
            this.etapaStr = etapa;
            this.nomePista = pista;
        }
    }

    private class SaveGameRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            
            ItemSave item = (ItemSave) value;
            
            if (item.nomeArquivo == null) {
                label.setText(item.equipe);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBorder(new EmptyBorder(10, 10, 10, 10));
                return label;
            }

            String nomeLimpo = item.nomeArquivo.replace(".save", "");
            
            // Layout em HTML com 3 linhas
            String html = String.format(
                "<html>" +
                "<div style='width: 400px; padding: 5px;'>" +
                "  <span style='font-size: 14px; font-weight: bold; color: #333;'>%s</span> <span style='color: gray; font-size: 10px;'>(%s)</span><br>" +
                "  <span style='font-size: 11px; color: #00008B;'>%s %d</span> • <span style='font-size: 11px;'>%s: <b>%s</b></span><br>" +
                "  <span style='font-size: 11px; color: #555;'>Equipe: <b>%s</b></span> • <span style='font-size: 11px; color: #006400;'>€ %.1f M</span>" +
                "</div>" +
                "</html>", 
                nomeLimpo, item.dirigente, 
                item.categoria, item.ano, item.etapaStr, item.nomePista,
                item.equipe, item.saldo
            );
            
            label.setText(html);
            
            label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)),
                new EmptyBorder(5, 10, 5, 10)
            ));

            if (isSelected) {
                label.setBackground(new Color(220, 240, 255));
                label.setForeground(Color.BLACK);
            } else {
                label.setBackground(Color.WHITE);
            }

            return label;
        }
    }
}
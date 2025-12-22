package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils; 
import dados.CarregadorJSON;
import dados.DadosDoJogo;
import modelos.Equipe;
import modelos.Patrocinador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaPatrocinador extends JDialog {

    private JPanel contentPane;
    private DadosDoJogo dados;
    private Equipe equipeJogador;

    // --- Componentes de Dados ---
    private List<Patrocinador> listaPatrocinadores; 
    private Patrocinador patrocinadorSelecionado;
    
    // Array local para validação de conflitos e controle dos slots
    private Patrocinador[] patrocinadoresAtivos = new Patrocinador[6]; 

    // --- Componentes Visuais ---
    private JList<Patrocinador> listPatrocinadores;
    private DefaultListModel<Patrocinador> listModel;

    private JLabel lblLogoDetalhe;
    private JLabel lblNomePatrocinador;
    private JLabel lblTipoEmpresa; 
    private JLabel lblBandeiraPais; 
    private JLabel lblValorMensal; 
    private JLabel lblBonusAssinatura;
    private JLabel lblReputacaoExigida; 
    
    private JComboBox<String> comboDuracao; 
    private JButton btnAssinar;

    private JPanel[] slotsPaineis;
    private JLabel[] slotsLogos;
    private JLabel[] slotsStatus; 
    private JLabel[] slotsValores; 
    private JLabel[] slotsNomes;
    private JLabel[] slotsSegmentos;

    public TelaPatrocinador(DadosDoJogo dados) {
        this.dados = dados;
        this.equipeJogador = dados.getEquipeDoJogador();

        setTitle("Gestão de Patrocinadores e Parceiros");
        
        setModal(true);
        setBounds(100, 100, 1030, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        try {
            java.awt.Image icon = FlatSVGUtils.svg2image("/resource/Icone.svg", 32, 32);
            if (icon != null) setIconImage(icon);
        } catch (Exception e) {
        }

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        carregarDadosReais(); 
        inicializarTopo();
        inicializarSlotsInferiores();
        
        // NOVO: Preenche os slots com o que a equipe já tem salvo
        carregarPatrociniosExistentes();
    }
    
    // --- NOVO MÉTODO PARA CARREGAR DADOS SALVOS ---
    private void carregarPatrociniosExistentes() {
        if (equipeJogador.getPatrocinadoresAtivos() == null) return;

        List<Patrocinador> salvos = equipeJogador.getPatrocinadoresAtivos();
        
        // Percorre a lista salva e preenche os slots sequencialmente
        for (int i = 0; i < salvos.size(); i++) {
            if (i >= 6) break; // Segurança para não estourar os slots
            
            Patrocinador p = salvos.get(i);
            
            // Atualiza o array local de validação
            patrocinadoresAtivos[i] = p;
            
            // Atualiza a parte Visual (Igual ao assinar)
            slotsStatus[i].setText(p.getDuracaoRestante() + " Meses Restantes");
            slotsStatus[i].setForeground(new Color(0, 100, 0)); // Verde escuro para diferenciar
            slotsLogos[i].setText(""); 
            
            slotsNomes[i].setText(p.getNome());
            slotsSegmentos[i].setText(p.getSegmento());
            slotsValores[i].setText(String.format("€ %.2f mi/mês", p.getValorBase()));
            
            carregarImagem(slotsLogos[i], p.getCaminhoLogo());
        }
    }
    
    private void carregarDadosReais() {
        this.listaPatrocinadores = new ArrayList<>();
        try {
            List<Patrocinador> todos = CarregadorJSON.carregarPatrocinadores(dados.getCategoriaKey(), dados.getAnoAtual());
            if (todos != null) {
                int reputacaoAtual = equipeJogador.getReputacao();
                
                for (Patrocinador p : todos) {
                    if (reputacaoAtual >= p.getReputacaoMinima()) {
                        this.listaPatrocinadores.add(p);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar patrocinadores: " + e.getMessage());
        }
        
        this.listaPatrocinadores.sort((p1, p2) -> Double.compare(p2.getValorBase(), p1.getValorBase()));
    }

    private void inicializarTopo() {
        JPanel panelLista = new JPanel();
        panelLista.setBorder(new TitledBorder(null, "Mercado de Patrocinadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLista.setBounds(10, 10, 280, 350);
        panelLista.setLayout(new BorderLayout(0, 0));
        contentPane.add(panelLista);

        listModel = new DefaultListModel<>();
        for (Patrocinador p : listaPatrocinadores) {
            listModel.addElement(p);
        }

        listPatrocinadores = new JList<>(listModel);
        listPatrocinadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPatrocinadores.setCellRenderer(new PatrocinadorRenderer());
        listPatrocinadores.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selecionarPatrocinador(listPatrocinadores.getSelectedValue());
            }
        });

        JScrollPane scrollPane = new JScrollPane(listPatrocinadores);
        panelLista.add(scrollPane, BorderLayout.CENTER);

        // === PAINEL DIREITO ===
        int larguraPainelDir = 1020 - 320; 
        
        JPanel panelDetalhes = new JPanel();
        panelDetalhes.setBackground(Color.WHITE);
        panelDetalhes.setBorder(new TitledBorder(null, "Detalhes da Proposta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDetalhes.setBounds(300, 10, larguraPainelDir, 350);
        panelDetalhes.setLayout(null);
        contentPane.add(panelDetalhes);

        lblLogoDetalhe = new JLabel(""); 
        lblLogoDetalhe.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoDetalhe.setBorder(new LineBorder(Color.LIGHT_GRAY));
        lblLogoDetalhe.setBounds(20, 30, 120, 120);
        panelDetalhes.add(lblLogoDetalhe);

        lblNomePatrocinador = new JLabel("Selecione um Patrocinador");
        lblNomePatrocinador.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        lblNomePatrocinador.setBounds(210, 30, 450, 30);
        panelDetalhes.add(lblNomePatrocinador);
        
        lblBandeiraPais = new JLabel("");
        lblBandeiraPais.setBounds(160, 30, 40, 30); 
        panelDetalhes.add(lblBandeiraPais);

        lblTipoEmpresa = new JLabel("Setor: --");
        lblTipoEmpresa.setForeground(Color.GRAY);
        lblTipoEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTipoEmpresa.setBounds(160, 60, 200, 20);
        panelDetalhes.add(lblTipoEmpresa);
        
        lblReputacaoExigida = new JLabel("Reputação Mínima: --");
        lblReputacaoExigida.setForeground(Color.DARK_GRAY);
        lblReputacaoExigida.setFont(new Font("Arial", Font.BOLD, 11));
        lblReputacaoExigida.setBounds(450, 60, 200, 20);
        panelDetalhes.add(lblReputacaoExigida);
        
        // --- PAINEL FINANCEIRO ---
        JPanel panelFin = new JPanel();
        panelFin.setBackground(new Color(245, 245, 250));
        panelFin.setBounds(160, 90, 500, 60); 
        panelFin.setLayout(null);
        panelDetalhes.add(panelFin);
        
        lblValorMensal = new JLabel("Pagamento Mensal: € 0.00 mi");
        lblValorMensal.setHorizontalAlignment(SwingConstants.CENTER); 
        lblValorMensal.setFont(new Font("Arial", Font.BOLD, 13));
        lblValorMensal.setBounds(0, 10, 500, 20); 
        panelFin.add(lblValorMensal);
        
        lblBonusAssinatura = new JLabel("Bônus Assinatura (Upfront): € 0.00 mi");
        lblBonusAssinatura.setHorizontalAlignment(SwingConstants.CENTER);
        lblBonusAssinatura.setForeground(new Color(0, 100, 0));
        lblBonusAssinatura.setBounds(0, 35, 500, 20); 
        panelFin.add(lblBonusAssinatura);

        JSeparator separator = new JSeparator();
        separator.setBounds(20, 180, larguraPainelDir - 40, 2);
        panelDetalhes.add(separator);

        JLabel lblDuracao = new JLabel("Duração do Contrato:");
        lblDuracao.setFont(new Font("Arial", Font.BOLD, 14));
        lblDuracao.setBounds(20, 200, 200, 20);
        panelDetalhes.add(lblDuracao);

        String[] opcoesDuracao = {
            "3 Meses (Curto Prazo)", 
            "6 Meses (Meia Temporada)", 
            "9 Meses", 
            "12 Meses (1 Temporada)", 
            "24 Meses (2 Temporadas)"
        };
        
        comboDuracao = new JComboBox<String>();
        comboDuracao.setModel(new DefaultComboBoxModel<String>(opcoesDuracao));
        comboDuracao.setBounds(20, 230, 250, 30);
        panelDetalhes.add(comboDuracao);

        btnAssinar = new JButton("Assinar Contrato");
        btnAssinar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAssinar.setBounds(20, 290, larguraPainelDir - 40, 40);
        btnAssinar.setEnabled(false);
        btnAssinar.addActionListener(e -> acaoAssinarContrato());
        panelDetalhes.add(btnAssinar);
    }

    private void inicializarSlotsInferiores() {
        JLabel lblTituloSlots = new JLabel("ESPAÇOS NO CARRO (SLOTS ATIVOS)");
        lblTituloSlots.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloSlots.setForeground(Color.DARK_GRAY);
        lblTituloSlots.setBounds(10, 370, 400, 20);
        contentPane.add(lblTituloSlots);

        slotsPaineis = new JPanel[6];
        slotsLogos = new JLabel[6];
        slotsStatus = new JLabel[6];
        slotsValores = new JLabel[6]; 
        slotsNomes = new JLabel[6];     
        slotsSegmentos = new JLabel[6]; 

        int startX = 10;
        int startY = 400;
        int larguraSlot = 155; 
        int alturaSlot = 240;
        int gap = 12; 

        for (int i = 0; i < 6; i++) {
            JPanel p = new JPanel();
            p.setLayout(null);
            p.setBackground(Color.WHITE);
            p.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
            p.setBounds(startX + (i * (larguraSlot + gap)), startY, larguraSlot, alturaSlot);
            
            JLabel lblTitulo = new JLabel("Slot " + (i + 1));
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
            lblTitulo.setBounds(0, 5, larguraSlot, 20);
            p.add(lblTitulo);

            JLabel lblLogo = new JLabel("Vazio");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setForeground(Color.LIGHT_GRAY);
            lblLogo.setBorder(new EmptyBorder(5,5,5,5)); 
            lblLogo.setBounds(10, 25, larguraSlot - 20, 80); 
            p.add(lblLogo);
            slotsLogos[i] = lblLogo;
            
            JLabel lblNome = new JLabel("");
            lblNome.setHorizontalAlignment(SwingConstants.CENTER);
            lblNome.setFont(new Font("Arial", Font.BOLD, 12));
            lblNome.setBounds(5, 110, larguraSlot - 10, 15);
            p.add(lblNome);
            slotsNomes[i] = lblNome;
            
            JLabel lblRamo = new JLabel("");
            lblRamo.setHorizontalAlignment(SwingConstants.CENTER);
            lblRamo.setFont(new Font("Arial", Font.ITALIC, 10));
            lblRamo.setForeground(Color.GRAY);
            lblRamo.setBounds(5, 125, larguraSlot - 10, 15);
            p.add(lblRamo);
            slotsSegmentos[i] = lblRamo;
            
            JLabel lblValor = new JLabel("-");
            lblValor.setHorizontalAlignment(SwingConstants.CENTER);
            lblValor.setFont(new Font("Arial", Font.BOLD, 12));
            lblValor.setForeground(new Color(0, 100, 0));
            lblValor.setBounds(5, 145, larguraSlot - 10, 20);
            p.add(lblValor);
            slotsValores[i] = lblValor;

            JLabel lblStatus = new JLabel("Disponível");
            lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
            lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
            lblStatus.setForeground(Color.GRAY);
            lblStatus.setBounds(5, 165, larguraSlot - 10, 20);
            p.add(lblStatus);
            slotsStatus[i] = lblStatus;

            JButton btnGerir = new JButton("Gerir");
            btnGerir.setFont(new Font("Arial", Font.PLAIN, 10));
            btnGerir.setBounds(20, 200, larguraSlot - 40, 25);
            p.add(btnGerir);

            slotsPaineis[i] = p;
            contentPane.add(p);
        }
    }

    // --- LÓGICA ---

    private void selecionarPatrocinador(Patrocinador p) {
        if (p == null) return;
        this.patrocinadorSelecionado = p;

        lblNomePatrocinador.setText(p.getNome());
        lblTipoEmpresa.setText("Setor: " + p.getSegmento());
        
        lblValorMensal.setText(String.format("Pagamento Mensal: € %.2f mi", p.getValorBase()));
        lblBonusAssinatura.setText(String.format("Bônus Assinatura: € %.2f mi", p.getBonusAssinatura()));
        
        carregarImagem(lblLogoDetalhe, p.getCaminhoLogo());
        carregarImagem(lblBandeiraPais, p.getCaminhoBandeira());

        int reputacaoEquipe = equipeJogador.getReputacao(); 
        int reputacaoExigida = p.getReputacaoMinima();
        
        lblReputacaoExigida.setText("Reputação Mínima: " + reputacaoExigida);
        
        if (reputacaoEquipe >= reputacaoExigida) {
            lblReputacaoExigida.setForeground(new Color(0, 128, 0)); 
            btnAssinar.setEnabled(true);
            btnAssinar.setText("Assinar com " + p.getNome());
        } else {
            lblReputacaoExigida.setForeground(Color.RED);
            btnAssinar.setEnabled(false);
            btnAssinar.setText("Reputação Insuficiente");
        }
    }
    
    private void acaoAssinarContrato() {
        if (patrocinadorSelecionado == null) return;
        
        // Verifica se já tem do mesmo ramo nos slots ativos
        for (Patrocinador ativo : patrocinadoresAtivos) {
            if (ativo != null && ativo.getSegmento().equalsIgnoreCase(patrocinadorSelecionado.getSegmento())) {
                JOptionPane.showMessageDialog(this, 
                    "Conflito de Interesse!\n\n" +
                    "Você já possui um patrocinador do ramo '" + ativo.getSegmento() + "' (" + ativo.getNome() + ").\n" +
                    "Não é permitido ter dois concorrentes no mesmo carro.",
                    "Contrato Bloqueado", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        String duracaoTexto = (String) comboDuracao.getSelectedItem();
        int meses = 12; 
        if (duracaoTexto.startsWith("3")) meses = 3;
        else if (duracaoTexto.startsWith("6")) meses = 6;
        else if (duracaoTexto.startsWith("9")) meses = 9;
        else if (duracaoTexto.startsWith("12")) meses = 12;
        else if (duracaoTexto.startsWith("24")) meses = 24;

        boolean assinou = false;
        for (int i = 0; i < 6; i++) {
            if (slotsStatus[i].getText().equals("Disponível")) { // Slot Vazio
                
                // Cria e vincula o contrato
                Patrocinador novoContrato = patrocinadorSelecionado.criarContrato(meses);
                equipeJogador.adicionarPatrocinador(novoContrato);
                
                // Atualiza array local
                patrocinadoresAtivos[i] = novoContrato;
                
                // Atualiza UI
                slotsStatus[i].setText(duracaoTexto);
                slotsStatus[i].setForeground(Color.BLUE);
                slotsLogos[i].setText(""); 
                
                slotsNomes[i].setText(novoContrato.getNome());
                slotsSegmentos[i].setText(novoContrato.getSegmento());
                
                slotsValores[i].setText(String.format("€ %.2f mi/mês", novoContrato.getValorBase()));
                
                carregarImagem(slotsLogos[i], novoContrato.getCaminhoLogo());
                
                double bonus = novoContrato.getBonusAssinatura();
                equipeJogador.receberReceita(bonus);
                
                JOptionPane.showMessageDialog(this, 
                    "Contrato assinado com " + novoContrato.getNome() + "!\n" +
                    "Bônus de € " + bonus + " mi adicionado ao caixa.");
                
                assinou = true;
                break;
            }
        }
        
        if (!assinou) {
            JOptionPane.showMessageDialog(this, "Todos os slots estão cheios!");
        }
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
                 
                 int padding = 5; 
                 float availableW = Math.max(1, labelW - (padding * 2));
                 float availableH = Math.max(1, labelH - (padding * 2));

                 float ratioW = availableW / origW;
                 float ratioH = availableH / origH;
                 
                 float scale = Math.min(ratioW, ratioH);
                 
                 int finalW = Math.round(origW * scale);
                 int finalH = Math.round(origH * scale);
                 
                 lbl.setIcon(new FlatSVGIcon(svgPath, Math.max(1, finalW), Math.max(1, finalH)));
                 lbl.setText("");
                 
            } else {
                 lbl.setIcon(new FlatSVGIcon(svgPath));
                 lbl.setText("");
            }

        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    class PatrocinadorRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Patrocinador) {
                Patrocinador p = (Patrocinador) value;
                label.setText(p.getNome() + " (Valor: " + p.getValorBase() + " mi/mês)");
                label.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
            return label;
        }
    }
}
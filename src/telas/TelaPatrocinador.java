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
    private Patrocinador[] patrocinadoresAtivos = new Patrocinador[6]; 

    // --- Componentes Visuais ---
    private JList<Patrocinador> listPatrocinadores;
    private DefaultListModel<Patrocinador> listModel;

    private JLabel lblLogoDetalhe;
    private JLabel lblNomePatrocinador;
    private JLabel lblTipoEmpresa; 
    private JLabel lblBandeiraPais; 
    
    // Detalhes Financeiros
    private JLabel lblValorMensal; 
    private JLabel lblBonusAssinatura;
    private JLabel lblBonusVitoria;
    private JLabel lblBonusPodio;
    private JLabel lblBonusTop10;
    
    private JLabel lblReputacaoExigida; 
    private JComboBox<String> comboDuracao; 
    private JButton btnAssinar;

    private JLabel lblResumoMensal;
    private JLabel lblResumoVitoria;
    private JLabel lblResumoPodio;
    private JLabel lblResumoTop10;

    private JPanel[] slotsPaineis;
    private JLabel[] slotsLogos;
    private JLabel[] slotsStatus; 
    private JLabel[] slotsValores; 
    private JLabel[] slotsNomes;
    private JLabel[] slotsSegmentos;
    
    private JLabel[] slotsBonusVit;
    private JLabel[] slotsBonusPod;
    private JLabel[] slotsBonusTop;

    public TelaPatrocinador(DadosDoJogo dados) {
        this.dados = dados;
        this.equipeJogador = dados.getEquipeDoJogador();

        setTitle("Gestão de Patrocinadores e Parceiros");
        setModal(true);
        setBounds(100, 100, 1100, 700); 
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        
        try {
            java.awt.Image icon = FlatSVGUtils.svg2image("/resource/Icone.svg", 32, 32);
            if (icon != null) setIconImage(icon);
        } catch (Exception e) {}

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        carregarDadosReais(); 
        inicializarTopo();
        inicializarSlotsInferiores();
        
        carregarPatrociniosExistentes();
        atualizarPainelResumo();
    }
    
    private String formatarValor(double valorEmMilhoes) {
        if (valorEmMilhoes < 1.0) {
            return String.format("€ %.0f k", valorEmMilhoes * 1000);
        } else {
            return String.format("€ %.2f mi", valorEmMilhoes);
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

    private void carregarPatrociniosExistentes() {
        if (equipeJogador.getPatrocinadoresAtivos() == null) return;

        List<Patrocinador> salvos = equipeJogador.getPatrocinadoresAtivos();
        for (int i = 0; i < salvos.size(); i++) {
            if (i >= 6) break; 
            Patrocinador p = salvos.get(i);
            patrocinadoresAtivos[i] = p;
            preencherSlotVisual(i, p);
        }
    }

    private void inicializarTopo() {
        // 1. LISTA
        JPanel panelLista = new JPanel();
        panelLista.setBorder(new TitledBorder(null, "Mercado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLista.setBounds(10, 10, 260, 350);
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
            if (!e.getValueIsAdjusting()) selecionarPatrocinador(listPatrocinadores.getSelectedValue());
        });
        panelLista.add(new JScrollPane(listPatrocinadores), BorderLayout.CENTER);

        // 2. DETALHES
        JPanel panelDetalhes = new JPanel();
        panelDetalhes.setBackground(Color.WHITE);
        panelDetalhes.setBorder(new TitledBorder(null, "Proposta Selecionada", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDetalhes.setBounds(280, 10, 500, 350); 
        panelDetalhes.setLayout(null);
        contentPane.add(panelDetalhes);

        lblLogoDetalhe = new JLabel(""); 
        lblLogoDetalhe.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoDetalhe.setBorder(new LineBorder(Color.LIGHT_GRAY));
        lblLogoDetalhe.setBounds(20, 30, 130, 130);
        panelDetalhes.add(lblLogoDetalhe);

        lblNomePatrocinador = new JLabel("Selecione...");
        lblNomePatrocinador.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        lblNomePatrocinador.setBounds(160, 30, 300, 30);
        panelDetalhes.add(lblNomePatrocinador);
        
        lblBandeiraPais = new JLabel("");
        lblBandeiraPais.setBounds(450, 30, 40, 30); 
        panelDetalhes.add(lblBandeiraPais);

        lblTipoEmpresa = new JLabel("Setor: --");
        lblTipoEmpresa.setForeground(Color.GRAY);
        lblTipoEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTipoEmpresa.setBounds(160, 60, 200, 20);
        panelDetalhes.add(lblTipoEmpresa);
        
        // MUDANÇA: Reputação logo abaixo do Setor
        lblReputacaoExigida = new JLabel("Reputação: --");
        lblReputacaoExigida.setForeground(Color.DARK_GRAY);
        lblReputacaoExigida.setFont(new Font("Arial", Font.BOLD, 11));
        lblReputacaoExigida.setBounds(160, 85, 200, 20); 
        panelDetalhes.add(lblReputacaoExigida);
        
        // MUDANÇA: Painel Financeiro abaixo da Logo
        JPanel panelFin = new JPanel();
        panelFin.setBackground(new Color(245, 245, 250));
        panelFin.setBorder(new LineBorder(Color.LIGHT_GRAY));
        // Posicionado abaixo da logo (Y=30+130=160), ocupando largura total menos margem
        panelFin.setBounds(20, 170, 460, 100); 
        panelFin.setLayout(new GridLayout(2, 3, 5, 0)); // Grid para organizar os valores
        panelDetalhes.add(panelFin);
        
        lblValorMensal = criarLabelFinanceiro(panelFin, "Mensal: -", true);
        lblBonusAssinatura = criarLabelFinanceiro(panelFin, "Assinatura: -", false);
        lblBonusVitoria = criarLabelFinanceiro(panelFin, "Vitória: -", false);
        lblBonusPodio = criarLabelFinanceiro(panelFin, "Pódio: -", false);
        lblBonusTop10 = criarLabelFinanceiro(panelFin, "Top 10: -", false);

        // Botões na Base
        JLabel lblDuracao = new JLabel("Duração:");
        lblDuracao.setFont(new Font("Arial", Font.BOLD, 12));
        lblDuracao.setBounds(20, 285, 100, 20);
        panelDetalhes.add(lblDuracao);

        String[] opcoesDuracao = { "3 Meses", "6 Meses", "9 Meses", "12 Meses", "24 Meses" };
        comboDuracao = new JComboBox<>(opcoesDuracao);
        comboDuracao.setSelectedIndex(3); 
        comboDuracao.setBounds(20, 305, 130, 30);
        panelDetalhes.add(comboDuracao);

        btnAssinar = new JButton("Assinar Contrato");
        btnAssinar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAssinar.setBounds(160, 305, 320, 30);
        btnAssinar.setEnabled(false);
        btnAssinar.addActionListener(e -> acaoAssinarContrato());
        panelDetalhes.add(btnAssinar);

        // 3. RESUMO (DIREITA)
        JPanel panelResumo = new JPanel();
        panelResumo.setBackground(Color.WHITE); 
        panelResumo.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY, 1), "Resumo de Ganhos (Atual)", TitledBorder.CENTER, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14), Color.BLACK));
        panelResumo.setBounds(790, 10, 285, 350);
        panelResumo.setLayout(null);
        contentPane.add(panelResumo);
        
        JLabel lblTituloResumo = new JLabel("Renda dos 6 Patrocinadores");
        lblTituloResumo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTituloResumo.setBounds(10, 30, 265, 20);
        panelResumo.add(lblTituloResumo);
        
        lblResumoMensal = criarBlocoResumo(panelResumo, "Renda Mensal Fixa", 70);
        lblResumoVitoria = criarBlocoResumo(panelResumo, "Potencial p/ Vitória", 130);
        lblResumoPodio = criarBlocoResumo(panelResumo, "Potencial p/ Pódio", 190);
        lblResumoTop10 = criarBlocoResumo(panelResumo, "Potencial p/ Top 10", 250);
    }
    
    private JLabel criarLabelFinanceiro(JPanel panel, String texto, boolean bold) {
        JLabel lbl = new JLabel(texto);
        lbl.setBorder(new EmptyBorder(0, 5, 0, 0));
        if (bold) lbl.setFont(new Font("Arial", Font.BOLD, 12));
        else lbl.setFont(new Font("Arial", Font.PLAIN, 12));
        panel.add(lbl);
        return lbl;
    }
    
    private JLabel criarBlocoResumo(JPanel panel, String titulo, int y) {
        JLabel lblTit = new JLabel(titulo);
        lblTit.setHorizontalAlignment(SwingConstants.CENTER);
        lblTit.setFont(new Font("Arial", Font.PLAIN, 11));
        lblTit.setBounds(10, y, 265, 15);
        panel.add(lblTit);
        
        JLabel lblVal = new JLabel("-");
        lblVal.setHorizontalAlignment(SwingConstants.CENTER);
        lblVal.setFont(new Font("Arial", Font.BOLD, 22));
        lblVal.setForeground(new Color(0, 100, 0));
        lblVal.setBounds(10, y + 15, 265, 30);
        panel.add(lblVal);
        
        return lblVal;
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
        slotsBonusVit = new JLabel[6];
        slotsBonusPod = new JLabel[6];
        slotsBonusTop = new JLabel[6];

        int startX = 10;
        int startY = 400;
        int larguraSlot = 168; 
        int alturaSlot = 250;
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
            lblTitulo.setBounds(0, 5, larguraSlot, 15);
            p.add(lblTitulo);

            JLabel lblLogo = new JLabel("Vazio");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setForeground(Color.LIGHT_GRAY);
            lblLogo.setBorder(new EmptyBorder(2,2,2,2)); 
            lblLogo.setBounds(10, 25, larguraSlot - 20, 60); 
            p.add(lblLogo);
            slotsLogos[i] = lblLogo;
            
            JLabel lblNome = new JLabel("");
            lblNome.setHorizontalAlignment(SwingConstants.CENTER);
            lblNome.setFont(new Font("Arial", Font.BOLD, 12));
            lblNome.setBounds(5, 90, larguraSlot - 10, 15);
            p.add(lblNome);
            slotsNomes[i] = lblNome;
            
            JLabel lblRamo = new JLabel("");
            lblRamo.setHorizontalAlignment(SwingConstants.CENTER);
            lblRamo.setFont(new Font("Arial", Font.ITALIC, 10));
            lblRamo.setForeground(Color.GRAY);
            lblRamo.setBounds(5, 105, larguraSlot - 10, 15);
            p.add(lblRamo);
            slotsSegmentos[i] = lblRamo;
            
            JLabel lblValor = new JLabel("-");
            lblValor.setHorizontalAlignment(SwingConstants.CENTER);
            lblValor.setFont(new Font("Arial", Font.BOLD, 13));
            lblValor.setForeground(new Color(0, 100, 0));
            lblValor.setBounds(5, 125, larguraSlot - 10, 20);
            p.add(lblValor);
            slotsValores[i] = lblValor;
            
            // MUDANÇA: Fonte maior (BOLD 12)
            slotsBonusVit[i] = criarLabelBonusSlot(p, "Vitória: -", 150, larguraSlot);
            slotsBonusPod[i] = criarLabelBonusSlot(p, "Pódio: -", 165, larguraSlot);
            slotsBonusTop[i] = criarLabelBonusSlot(p, "Top 10: -", 180, larguraSlot);

            JLabel lblStatus = new JLabel("Disponível");
            lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
            lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
            lblStatus.setForeground(Color.GRAY);
            lblStatus.setBounds(5, 225, larguraSlot - 10, 20); 
            p.add(lblStatus);
            slotsStatus[i] = lblStatus;

            slotsPaineis[i] = p;
            contentPane.add(p);
        }
    }
    
    private JLabel criarLabelBonusSlot(JPanel p, String txt, int y, int width) {
        JLabel lbl = new JLabel(txt);
        lbl.setHorizontalAlignment(SwingConstants.CENTER);
        // AUMENTADO: Fonte maior para os bônus nos slots
        lbl.setFont(new Font("Arial", Font.BOLD, 12)); 
        lbl.setForeground(Color.DARK_GRAY);
        lbl.setBounds(2, y, width - 4, 15); 
        p.add(lbl);
        return lbl;
    }

    // --- LÓGICA ---

    private void selecionarPatrocinador(Patrocinador p) {
        if (p == null) return;
        this.patrocinadorSelecionado = p;

        lblNomePatrocinador.setText(p.getNome());
        lblTipoEmpresa.setText("Setor: " + p.getSegmento());
        
        lblValorMensal.setText("Mensal: " + formatarValor(p.getValorBase()));
        lblBonusAssinatura.setText("Assinatura: " + formatarValor(p.getBonusAssinatura()));
        lblBonusVitoria.setText("Vitória: " + formatarValor(p.getBonusVitoria()));
        lblBonusPodio.setText("Pódio: " + formatarValor(p.getBonusPodio()));
        lblBonusTop10.setText("Top 10: " + formatarValor(p.getBonusTop10()));
        
        carregarImagem(lblLogoDetalhe, p.getCaminhoLogo());
        carregarImagem(lblBandeiraPais, p.getCaminhoBandeira());

        int reputacaoEquipe = equipeJogador.getReputacao(); 
        int reputacaoExigida = p.getReputacaoMinima();
        
        lblReputacaoExigida.setText("Reputação Min: " + reputacaoExigida);
        
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
        
        for (Patrocinador ativo : patrocinadoresAtivos) {
            if (ativo != null && ativo.getSegmento().equalsIgnoreCase(patrocinadorSelecionado.getSegmento())) {
                JOptionPane.showMessageDialog(this, 
                    "Conflito de Interesse!\nJá existe um patrocinador de '" + ativo.getSegmento() + "'.",
                    "Bloqueado", JOptionPane.WARNING_MESSAGE);
                return;
            }
        }
        
        String duracaoTexto = (String) comboDuracao.getSelectedItem();
        int meses = 12; 
        if (duracaoTexto.startsWith("3")) meses = 3;
        else if (duracaoTexto.startsWith("6")) meses = 6;
        else if (duracaoTexto.startsWith("9")) meses = 9;
        else if (duracaoTexto.startsWith("24")) meses = 24;

        boolean assinou = false;
        for (int i = 0; i < 6; i++) {
            if (slotsStatus[i].getText().equals("Disponível") || slotsStatus[i].getText().contains("Vencido")) {
                
                Patrocinador novoContrato = patrocinadorSelecionado.criarContrato(meses);
                equipeJogador.adicionarPatrocinador(novoContrato);
                patrocinadoresAtivos[i] = novoContrato;
                
                preencherSlotVisual(i, novoContrato);
                
                double bonus = novoContrato.getBonusAssinatura();
                equipeJogador.receberReceita(bonus);
                
                JOptionPane.showMessageDialog(this, "Contrato assinado com " + novoContrato.getNome() + "!");
                
                atualizarPainelResumo(); 
                
                assinou = true;
                break;
            }
        }
        
        if (!assinou) JOptionPane.showMessageDialog(this, "Todos os slots estão cheios!");
    }
    
    private void preencherSlotVisual(int i, Patrocinador p) {
        slotsStatus[i].setText(p.getDuracaoRestante() + " Meses Restantes");
        slotsStatus[i].setForeground(new Color(0, 100, 0));
        slotsLogos[i].setText(""); 
        
        slotsNomes[i].setText(p.getNome());
        slotsSegmentos[i].setText(p.getSegmento());
        
        slotsValores[i].setText(formatarValor(p.getValorBase()) + "/mês");
        
        slotsBonusVit[i].setText("Vitória: " + formatarValor(p.getBonusVitoria()));
        slotsBonusPod[i].setText("Pódio: " + formatarValor(p.getBonusPodio()));
        slotsBonusTop[i].setText("Top 10: " + formatarValor(p.getBonusTop10()));
        
        carregarImagem(slotsLogos[i], p.getCaminhoLogo());
    }
    
    private void atualizarPainelResumo() {
        double totalMensal = 0;
        double totalVitoria = 0;
        double totalPodio = 0;
        double totalTop10 = 0;
        
        for (Patrocinador p : patrocinadoresAtivos) {
            if (p != null) {
                totalMensal += p.getValorBase();
                totalVitoria += p.getBonusVitoria();
                totalPodio += p.getBonusPodio();
                totalTop10 += p.getBonusTop10();
            }
        }
        
        lblResumoMensal.setText(formatarValor(totalMensal));
        lblResumoVitoria.setText(formatarValor(totalVitoria));
        lblResumoPodio.setText(formatarValor(totalPodio));
        lblResumoTop10.setText(formatarValor(totalTop10));
    }

    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path == null || path.isEmpty()) {
                lbl.setIcon(null); return;
            }
            if (!path.startsWith("/")) path = "/" + path;
            if (!path.startsWith("/resource")) path = "/resource" + path;
            path = path.replace("//", "/");
            if (path.toLowerCase().endsWith(".png")) path = path.substring(0, path.length() - 4) + ".svg";
            if (!path.toLowerCase().endsWith(".svg")) path = path + ".svg";

            String svgPath = path.startsWith("/") ? path.substring(1) : path;
            int labelW = lbl.getWidth();
            int labelH = lbl.getHeight();
            
            if (labelW > 0 && labelH > 0) {
                 FlatSVGIcon iconOriginal = new FlatSVGIcon(svgPath);
                 if (iconOriginal.getIconWidth() <= 0) { lbl.setIcon(null); return; }
                 
                 float origW = iconOriginal.getIconWidth();
                 float origH = iconOriginal.getIconHeight();
                 int padding = 5; 
                 float availableW = Math.max(1, labelW - (padding * 2));
                 float availableH = Math.max(1, labelH - (padding * 2));
                 float scale = Math.min(availableW / origW, availableH / origH);
                 
                 lbl.setIcon(new FlatSVGIcon(svgPath, Math.round(origW * scale), Math.round(origH * scale)));
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
                label.setText(p.getNome() + " (" + formatarValor(p.getValorBase()) + ")");
                label.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
            return label;
        }
    }
}
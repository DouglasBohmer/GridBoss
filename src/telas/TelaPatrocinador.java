package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dados.CarregadorJSON;
import dados.DadosDoJogo;
import modelos.Equipe;
import modelos.Patrocinador;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaPatrocinador extends JFrame {

    private JPanel contentPane;
    private DadosDoJogo dados;
    private Equipe equipeJogador;

    // --- Componentes de Dados ---
    private List<Patrocinador> listaPatrocinadores; 
    private Patrocinador patrocinadorSelecionado;
    
    // Armazena os objetos reais que estão nos slots para validação de segmento
    private Patrocinador[] patrocinadoresAtivos = new Patrocinador[6]; 

    // --- Componentes Visuais (Topo Esquerda - Lista) ---
    private JList<Patrocinador> listPatrocinadores;
    private DefaultListModel<Patrocinador> listModel;

    // --- Componentes Visuais (Topo Direita - Detalhes) ---
    private JLabel lblLogoDetalhe;
    private JLabel lblNomePatrocinador;
    private JLabel lblTipoEmpresa; 
    private JLabel lblBandeiraPais; 
    private JLabel lblValorPorCorrida;
    private JLabel lblBonusAssinatura;
    private JLabel lblReputacaoExigida; 
    
    // Contrato
    private JComboBox<String> comboDuracao; 
    private JButton btnAssinar;

    // --- Componentes Visuais (Baixo - Slots Atuais) ---
    private JPanel[] slotsPaineis;
    private JLabel[] slotsLogos;
    private JLabel[] slotsStatus; 
    private JLabel[] slotsValores; // Novo: Mostra o valor no slot

    public TelaPatrocinador(DadosDoJogo dados) {
        this.dados = dados;
        this.equipeJogador = dados.getEquipeDoJogador();

        setTitle("Gestão de Patrocinadores e Parceiros");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        carregarDadosReais(); 
        inicializarTopo();
        inicializarSlotsInferiores();
    }
    
    private void carregarDadosReais() {
        this.listaPatrocinadores = new ArrayList<>();
        try {
            List<Patrocinador> todos = CarregadorJSON.carregarPatrocinadores(dados.getCategoriaKey(), dados.getAnoAtual());
            if (todos != null) {
                int reputacaoAtual = equipeJogador.getReputacao();
                
                for (Patrocinador p : todos) {
                    // REGRA 1: Só adiciona se a reputação da equipe for suficiente
                    if (reputacaoAtual >= p.getReputacaoMinima()) {
                        this.listaPatrocinadores.add(p);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar patrocinadores: " + e.getMessage());
        }
        
        // Ordena por Valor Base (Maior para menor)
        this.listaPatrocinadores.sort((p1, p2) -> Double.compare(p2.getValorBase(), p1.getValorBase()));
    }

    private void inicializarTopo() {
        // === PAINEL ESQUERDO (LISTA DE DISPONÍVEIS) ===
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

        // === PAINEL DIREITO (DETALHES DA PROPOSTA) ===
        JPanel panelDetalhes = new JPanel();
        panelDetalhes.setBackground(Color.WHITE);
        panelDetalhes.setBorder(new TitledBorder(null, "Detalhes da Proposta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelDetalhes.setBounds(300, 10, 575, 350);
        panelDetalhes.setLayout(null);
        contentPane.add(panelDetalhes);

        // Logo Grande
        lblLogoDetalhe = new JLabel("Logo");
        lblLogoDetalhe.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogoDetalhe.setBorder(new LineBorder(Color.LIGHT_GRAY));
        lblLogoDetalhe.setBounds(20, 30, 120, 120);
        panelDetalhes.add(lblLogoDetalhe);

        // Informações Principais
        lblNomePatrocinador = new JLabel("Selecione um Patrocinador");
        lblNomePatrocinador.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 20));
        lblNomePatrocinador.setBounds(160, 30, 300, 30);
        panelDetalhes.add(lblNomePatrocinador);
        
        lblBandeiraPais = new JLabel("");
        lblBandeiraPais.setBounds(470, 30, 40, 30);
        panelDetalhes.add(lblBandeiraPais);

        lblTipoEmpresa = new JLabel("Setor: --");
        lblTipoEmpresa.setForeground(Color.GRAY);
        lblTipoEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTipoEmpresa.setBounds(160, 60, 200, 20);
        panelDetalhes.add(lblTipoEmpresa);
        
        lblReputacaoExigida = new JLabel("Reputação Mínima: --");
        lblReputacaoExigida.setForeground(Color.DARK_GRAY);
        lblReputacaoExigida.setFont(new Font("Arial", Font.BOLD, 11));
        lblReputacaoExigida.setBounds(370, 60, 180, 20);
        panelDetalhes.add(lblReputacaoExigida);
        
        // Área Financeira
        JPanel panelFin = new JPanel();
        panelFin.setBackground(new Color(245, 245, 250));
        panelFin.setBounds(160, 90, 390, 60);
        panelFin.setLayout(null);
        panelDetalhes.add(panelFin);
        
        lblValorPorCorrida = new JLabel("Pagamento por Corrida: € 0.00 mi");
        lblValorPorCorrida.setFont(new Font("Arial", Font.BOLD, 13));
        lblValorPorCorrida.setBounds(10, 10, 300, 20);
        panelFin.add(lblValorPorCorrida);
        
        lblBonusAssinatura = new JLabel("Bônus Assinatura (Upfront): € 0.00 mi");
        lblBonusAssinatura.setForeground(new Color(0, 100, 0));
        lblBonusAssinatura.setBounds(10, 35, 300, 20);
        panelFin.add(lblBonusAssinatura);

        // Área de Contrato
        JSeparator separator = new JSeparator();
        separator.setBounds(20, 180, 530, 2);
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

        // Botão de Assinar
        btnAssinar = new JButton("Assinar Contrato");
        btnAssinar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAssinar.setBounds(20, 290, 530, 40);
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
        slotsValores = new JLabel[6]; // REGRA 2: Inicializa array de valores

        int startX = 10;
        int startY = 400;
        int larguraSlot = 140;
        int alturaSlot = 240;
        int gap = 10;

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
            lblLogo.setBounds(10, 35, larguraSlot - 20, 100);
            p.add(lblLogo);
            slotsLogos[i] = lblLogo;
            
            // Valor do Contrato (Novo)
            JLabel lblValor = new JLabel("-");
            lblValor.setHorizontalAlignment(SwingConstants.CENTER);
            lblValor.setFont(new Font("Arial", Font.BOLD, 12));
            lblValor.setForeground(new Color(0, 100, 0));
            lblValor.setBounds(5, 140, larguraSlot - 10, 20);
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
        lblValorPorCorrida.setText(String.format("Pagamento por Corrida: € %.2f mi", p.getValorBase()));
        lblBonusAssinatura.setText(String.format("Bônus Assinatura: € %.2f mi", p.getBonusAssinatura()));
        
        carregarImagem(lblLogoDetalhe, p.getCaminhoLogo());
        carregarImagem(lblBandeiraPais, p.getCaminhoBandeira());

        int reputacaoEquipe = equipeJogador.getReputacao(); 
        int reputacaoExigida = p.getReputacaoMinima();
        
        lblReputacaoExigida.setText("Reputação Mínima: " + reputacaoExigida);
        
        // Embora já tenhamos filtrado na lista, mantemos a validação visual caso algo mude
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
        
        // REGRA 3: Verifica conflito de segmento (Mesmo Ramo)
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
        
        boolean assinou = false;
        for (int i = 0; i < 6; i++) {
            if (slotsStatus[i].getText().equals("Disponível")) {
                
                // Salva o objeto para validações futuras
                patrocinadoresAtivos[i] = patrocinadorSelecionado;
                
                // Atualiza UI
                slotsStatus[i].setText(duracaoTexto);
                slotsStatus[i].setForeground(Color.BLUE);
                slotsLogos[i].setText(""); 
                
                // REGRA 2: Mostra o valor no slot
                slotsValores[i].setText(String.format("€ %.2f mi", patrocinadorSelecionado.getValorBase()));
                
                carregarImagem(slotsLogos[i], patrocinadorSelecionado.getCaminhoLogo());
                
                // Aplica Bônus financeiro
                double bonus = patrocinadorSelecionado.getBonusAssinatura();
                equipeJogador.receberReceita(bonus);
                
                JOptionPane.showMessageDialog(this, 
                    "Contrato assinado com " + patrocinadorSelecionado.getNome() + "!\n" +
                    "Bônus de € " + bonus + " mi adicionado ao caixa.");
                
                assinou = true;
                break;
            }
        }
        
        if (!assinou) {
            JOptionPane.showMessageDialog(this, "Todos os slots estão cheios! Remova um patrocinador primeiro.");
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
                 
                 // Margem de 5px (total 10px)
                 int padding = 5; 
                 float availableW = Math.max(1, labelW - (padding * 2));
                 float availableH = Math.max(1, labelH - (padding * 2));

                 float ratioW = availableW / origW;
                 float ratioH = availableH / origH;
                 
                 float scale = Math.min(ratioW, ratioH);
                 
                 int finalW = Math.round(origW * scale);
                 int finalH = Math.round(origH * scale);
                 
                 lbl.setIcon(new FlatSVGIcon(svgPath, Math.max(1, finalW), Math.max(1, finalH)));
            } else {
                 lbl.setIcon(new FlatSVGIcon(svgPath));
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
                label.setText(p.getNome() + " (Valor: " + p.getValorBase() + " mi)");
                label.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
            return label;
        }
    }
}
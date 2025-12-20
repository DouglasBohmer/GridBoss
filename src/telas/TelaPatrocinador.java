package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.extras.FlatSVGUtils;

import dados.DadosDoJogo;
import modelos.Equipe;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class TelaPatrocinador extends JFrame {

    private JPanel contentPane;
    private DadosDoJogo dados;
    private Equipe equipeJogador;

    // --- Componentes de Dados ---
    private List<PatrocinadorMock> listaPatrocinadores; // Substituir pela sua classe oficial depois
    private PatrocinadorMock patrocinadorSelecionado;

    // --- Componentes Visuais (Topo Esquerda - Lista) ---
    private JList<PatrocinadorMock> listPatrocinadores;
    private DefaultListModel<PatrocinadorMock> listModel;

    // --- Componentes Visuais (Topo Direita - Detalhes) ---
    private JLabel lblLogoDetalhe;
    private JLabel lblNomePatrocinador;
    private JLabel lblTipoEmpresa; // Ex: Bebidas, Tech, Auto
    private JLabel lblValorPorCorrida;
    private JLabel lblBonusAssinatura;
    
    // Contrato
    private JComboBox<String> comboDuracao;
    private JButton btnAssinar;

    // --- Componentes Visuais (Baixo - Slots Atuais) ---
    private JPanel[] slotsPaineis;
    private JLabel[] slotsLogos;
    private JLabel[] slotsStatus; // Ex: "Vazio" ou "X Meses restantes"

    public TelaPatrocinador(DadosDoJogo dados) {
        this.dados = dados;
        this.equipeJogador = dados.getEquipeDoJogador();

        setTitle("Grid Boss - Patrocinador");
        try {
            java.awt.Image icon = FlatSVGUtils.svg2image("/resource/Icone.svg", 32, 32);
            if (icon != null) setIconImage(icon);
        } catch (Exception e) {
        }
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 700);
        setResizable(false);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        carregarDadosMock(); // TODO: Substituir por CarregadorJSON
        inicializarTopo();
        inicializarSlotsInferiores();
    }

    private void inicializarTopo() {
        // === PAINEL ESQUERDO (LISTA DE DISPONÍVEIS) ===
        JPanel panelLista = new JPanel();
        panelLista.setBorder(new TitledBorder(null, "Mercado de Patrocinadores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panelLista.setBounds(10, 10, 280, 350);
        panelLista.setLayout(new BorderLayout(0, 0));
        contentPane.add(panelLista);

        listModel = new DefaultListModel<>();
        for (PatrocinadorMock p : listaPatrocinadores) {
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
        lblNomePatrocinador.setBounds(160, 30, 380, 30);
        panelDetalhes.add(lblNomePatrocinador);

        lblTipoEmpresa = new JLabel("Setor: --");
        lblTipoEmpresa.setForeground(Color.GRAY);
        lblTipoEmpresa.setFont(new Font("Arial", Font.PLAIN, 12));
        lblTipoEmpresa.setBounds(160, 60, 200, 20);
        panelDetalhes.add(lblTipoEmpresa);
        
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

        // ComboBox conforme solicitado (3, 6, 9, 12, 24)
        String[] opcoesDuracao = {
            "3 Meses (Curto Prazo)", 
            "6 Meses (Meia Temporada)", 
            "9 Meses", 
            "12 Meses (1 Temporada)", 
            "24 Meses (2 Temporadas)"
        };
        comboDuracao = new JComboBox<>(opcoesDuracao);
        comboDuracao.setBounds(20, 230, 250, 30);
        panelDetalhes.add(comboDuracao);

        // Botão de Assinar
        btnAssinar = new JButton("Assinar Contrato");
        btnAssinar.setFont(new Font("Arial", Font.BOLD, 14));
        btnAssinar.setBounds(20, 290, 530, 40);
        btnAssinar.setEnabled(false); // Só habilita ao selecionar
        btnAssinar.addActionListener(e -> acaoAssinarContrato());
        panelDetalhes.add(btnAssinar);
    }

    private void inicializarSlotsInferiores() {
        // Título da seção inferior
        JLabel lblTituloSlots = new JLabel("ESPAÇOS NO CARRO (SLOTS ATIVOS)");
        lblTituloSlots.setFont(new Font("Arial", Font.BOLD, 14));
        lblTituloSlots.setForeground(Color.DARK_GRAY);
        lblTituloSlots.setBounds(10, 370, 400, 20);
        contentPane.add(lblTituloSlots);

        // Configuração dos 6 slots
        slotsPaineis = new JPanel[6];
        slotsLogos = new JLabel[6];
        slotsStatus = new JLabel[6];

        int startX = 10;
        int startY = 400;
        int larguraSlot = 140; // 900px / 6 slots dá +/- 150px
        int alturaSlot = 240;
        int gap = 10;

        for (int i = 0; i < 6; i++) {
            // Painel do Slot
            JPanel p = new JPanel();
            p.setLayout(null);
            p.setBackground(Color.WHITE);
            p.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
            // Cálculo da posição X para ficar um ao lado do outro
            p.setBounds(startX + (i * (larguraSlot + gap)), startY, larguraSlot, alturaSlot);
            
            // Título do Slot (Slot 1, Slot 2...)
            JLabel lblTitulo = new JLabel("Slot " + (i + 1));
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            lblTitulo.setFont(new Font("Arial", Font.BOLD, 12));
            lblTitulo.setBounds(0, 5, larguraSlot, 20);
            p.add(lblTitulo);

            // Placeholder da Logo
            JLabel lblLogo = new JLabel("Vazio");
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
            lblLogo.setForeground(Color.LIGHT_GRAY);
            lblLogo.setBorder(new EmptyBorder(5,5,5,5)); // Padding
            lblLogo.setBounds(10, 35, larguraSlot - 20, 100);
            p.add(lblLogo);
            slotsLogos[i] = lblLogo;

            // Status do Contrato no Slot
            JLabel lblStatus = new JLabel("Disponível");
            lblStatus.setHorizontalAlignment(SwingConstants.CENTER);
            lblStatus.setFont(new Font("Arial", Font.PLAIN, 11));
            lblStatus.setForeground(new Color(0, 128, 0)); // Verde
            lblStatus.setBounds(5, 150, larguraSlot - 10, 20);
            p.add(lblStatus);
            slotsStatus[i] = lblStatus;

            // Botãozinho "Gerenciar" ou "Remover" (Opcional)
            JButton btnGerir = new JButton("Gerir");
            btnGerir.setFont(new Font("Arial", Font.PLAIN, 10));
            btnGerir.setBounds(20, 200, larguraSlot - 40, 25);
            // btnGerir.addActionListener(...) 
            p.add(btnGerir);

            slotsPaineis[i] = p;
            contentPane.add(p);
        }
    }

    // --- LÓGICA ---

    private void selecionarPatrocinador(PatrocinadorMock p) {
        if (p == null) return;
        this.patrocinadorSelecionado = p;

        lblNomePatrocinador.setText(p.nome);
        lblTipoEmpresa.setText("Setor: " + p.tipo);
        lblValorPorCorrida.setText(String.format("Pagamento por Corrida: € %.2f mi", p.valorPorCorrida));
        lblBonusAssinatura.setText(String.format("Bônus Assinatura: € %.2f mi", p.bonusAssinatura));
        
        // Simulação de Logo
        carregarImagem(lblLogoDetalhe, p.caminhoLogo);

        btnAssinar.setEnabled(true);
        btnAssinar.setText("Assinar com " + p.nome);
    }
    
    private void acaoAssinarContrato() {
        if (patrocinadorSelecionado == null) return;
        
        String duracaoTexto = (String) comboDuracao.getSelectedItem();
        
        // Lógica simples para preencher o primeiro slot vazio (simulação)
        boolean assinou = false;
        for (int i = 0; i < 6; i++) {
            if (slotsStatus[i].getText().equals("Disponível")) {
                slotsStatus[i].setText(duracaoTexto);
                slotsStatus[i].setForeground(Color.BLUE);
                slotsLogos[i].setText(""); // Remove o texto "Vazio"
                carregarImagem(slotsLogos[i], patrocinadorSelecionado.caminhoLogo);
                
                JOptionPane.showMessageDialog(this, "Contrato assinado com sucesso no Slot " + (i+1) + "!");
                assinou = true;
                break;
            }
        }
        
        if (!assinou) {
            JOptionPane.showMessageDialog(this, "Todos os slots estão cheios! Remova um patrocinador primeiro.");
        }
    }

    // Método utilitário igual ao da TelaMotor
    private void carregarImagem(JLabel lbl, String path) {
        try {
            if (path == null || path.isEmpty()) {
                lbl.setIcon(null); 
                if (lbl.getText().isEmpty()) lbl.setText("Sem Logo");
                return;
            }
            // Se tiver FlatSVGIcon, usa a lógica de redimensionamento aqui
            // Por enquanto, texto para teste
            lbl.setIcon(null);
            lbl.setText("<html><center>LOGO<br>" + path + "</center></html>");
            
            // Exemplo REAL (Descomente se tiver as imagens):
            /*
            FlatSVGIcon icon = new FlatSVGIcon(path);
            // ... lógica de scale igual TelaMotor ...
            lbl.setIcon(icon);
            lbl.setText("");
            */
        } catch (Exception e) {
            lbl.setIcon(null);
        }
    }

    // --- MOCKS E CLASSES AUXILIARES ---

    private void carregarDadosMock() {
        listaPatrocinadores = new ArrayList<>();
        listaPatrocinadores.add(new PatrocinadorMock("Rolex", "Luxo", 0.5, 2.0, "rolex.svg"));
        listaPatrocinadores.add(new PatrocinadorMock("Shell", "Petróleo", 0.8, 1.5, "shell.svg"));
        listaPatrocinadores.add(new PatrocinadorMock("Heineken", "Bebidas", 0.4, 1.0, "heineken.svg"));
        listaPatrocinadores.add(new PatrocinadorMock("AWS", "Tecnologia", 0.6, 1.8, "aws.svg"));
        listaPatrocinadores.add(new PatrocinadorMock("Santander", "Banco", 0.55, 1.2, "santander.svg"));
    }

    // Classe interna provisória até você criar o JSON oficial
    class PatrocinadorMock {
        String nome;
        String tipo;
        double valorPorCorrida;
        double bonusAssinatura;
        String caminhoLogo;

        public PatrocinadorMock(String nome, String tipo, double v, double b, String img) {
            this.nome = nome;
            this.tipo = tipo;
            this.valorPorCorrida = v;
            this.bonusAssinatura = b;
            this.caminhoLogo = img;
        }
        
        @Override
        public String toString() {
            return nome; // Para aparecer na JList
        }
    }
    
    // Renderer para deixar a lista bonita
    class PatrocinadorRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof PatrocinadorMock) {
                PatrocinadorMock p = (PatrocinadorMock) value;
                label.setText(p.nome + " (" + p.tipo + ")");
                label.setBorder(new EmptyBorder(5, 5, 5, 5));
            }
            return label;
        }
    }
}
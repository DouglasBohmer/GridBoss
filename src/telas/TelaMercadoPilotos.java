package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dados.DadosDoJogo;
import dados.SessaoJogo;
import modelos.Equipe;
import modelos.Piloto;
import modelos.TipoContrato;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class TelaMercadoPilotos extends JDialog {

    private DadosDoJogo dados;
    private Equipe minhaEquipe;
    private boolean isF1;

    // --- Componentes ---
    private JTable tabelaPrincipal, tabelaMinhaEquipe;
    private DefaultTableModel modeloPrincipal, modeloMinhaEquipe;
    private TableRowSorter<DefaultTableModel> sorterPrincipal;

    // --- Filtros ---
    private JComboBox<String> cbFiltroOver, cbFiltroIdade, cbFiltroFuncao, cbFiltroContrato;
    private JTextField txtFiltroNome;

    // --- Listas ---
    private List<Piloto> todosPilotos = new ArrayList<>();
    private List<Piloto> pilotosMinhaEquipe = new ArrayList<>();

    // --- Stats ---
    private JLabel lbFotoPiloto, lbNomePilotoStats, lbNacionalidadeStats;
    private JPanel pnlListaStats; 

    // --- Ação / Negociação ---
    private JButton btnConfirmarAcao;
    private JSpinner spinSalario; // MUDANÇA: Spinner
    private JComboBox<String> cbDuracaoInput; // MUDANÇA: Combo de Anos
    private JComboBox<TipoContrato> cbVagaInput;
    private JLabel lbTituloAcao;

    private Piloto pilotoSelecionado = null;

    public TelaMercadoPilotos(DadosDoJogo dados) {
        this.dados = dados;
        this.minhaEquipe = dados.getEquipeDoJogador();
        this.isF1 = SessaoJogo.categoriaKey != null && SessaoJogo.categoriaKey.toLowerCase().contains("f1");

        setTitle("Mercado Global de Pilotos");
        setModal(true);
        setSize(1050, 700); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        try {
             setIconImage(new FlatSVGIcon("resource/Icone.svg").getImage());
        } catch (Exception e) {}

        carregarListaPilotos();
        initComponents();
        atualizarTabelaPrincipal();
        atualizarMinhaEquipe();
        limparSelecao();
    }

    private void carregarListaPilotos() {
        todosPilotos.clear();
        pilotosMinhaEquipe.clear();
        
        for (Piloto p : dados.getTodosOsPilotos()) {
            if (p != null) {
                todosPilotos.add(p);
                if (minhaEquipe.getPilotosTitulares().contains(p) || minhaEquipe.getPilotosReservas().contains(p)) {
                    pilotosMinhaEquipe.add(p);
                }
            }
        }
    }

    private void initComponents() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(0, 10));
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));
        painelPrincipal.setBackground(Color.WHITE);
        setContentPane(painelPrincipal);

        // =================================================================================
        // TOPO: TABELA GERAL + FILTROS
        // =================================================================================
        JPanel pnlTopo = new JPanel(new BorderLayout(0, 5));
        pnlTopo.setOpaque(false);
        pnlTopo.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY), "Mercado de Pilotos"));

        // Tabela
        String[] colunas = {"", "Nome", "Equipe", "Idade", "Over", "Contrato", "Função", "Exigência"};
        modeloPrincipal = new DefaultTableModel(colunas, 0) {
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tabelaPrincipal = new JTable(modeloPrincipal);
        configurarTabelaGeral(tabelaPrincipal);
        
        sorterPrincipal = new TableRowSorter<>(modeloPrincipal);
        tabelaPrincipal.setRowSorter(sorterPrincipal);

        tabelaPrincipal.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaPrincipal.getSelectedRow() != -1) {
                tabelaMinhaEquipe.clearSelection();
                int row = tabelaPrincipal.convertRowIndexToModel(tabelaPrincipal.getSelectedRow());
                String nome = (String) modeloPrincipal.getValueAt(row, 1);
                Piloto p = buscarPilotoPorNome(nome);
                selecionarPiloto(p);
            }
        });
        
        JScrollPane scrollTopo = new JScrollPane(tabelaPrincipal);
        scrollTopo.setPreferredSize(new Dimension(1030, 200)); 
        pnlTopo.add(scrollTopo, BorderLayout.CENTER);

        // Filtros
        JPanel pnlFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 5));
        pnlFiltros.setBackground(new Color(245, 245, 245));
        pnlFiltros.setBorder(new MatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));

        pnlFiltros.add(new JLabel("Over:"));
        cbFiltroOver = new JComboBox<>(new String[]{"Todos", "60+", "65+", "70+", "75+", "80+", "85+", "90+"});
        cbFiltroOver.addActionListener(e -> aplicarFiltros());
        pnlFiltros.add(cbFiltroOver);

        pnlFiltros.add(new JLabel("Idade:"));
        cbFiltroIdade = new JComboBox<>(new String[]{"Todas", "Até 21", "22-29", "30-35", "36+"});
        cbFiltroIdade.addActionListener(e -> aplicarFiltros());
        pnlFiltros.add(cbFiltroIdade);

        pnlFiltros.add(new JLabel("Função:"));
        cbFiltroFuncao = new JComboBox<>(new String[]{"Todas", "Titular", "Reserva", "Sem Contrato"});
        cbFiltroFuncao.addActionListener(e -> aplicarFiltros());
        pnlFiltros.add(cbFiltroFuncao);

        pnlFiltros.add(new JLabel("Contrato:"));
        cbFiltroContrato = new JComboBox<>(new String[]{"Todos", "Livre (0 meses)", "Até 6 meses", "Até 1 ano", "Mais de 1 ano"});
        cbFiltroContrato.addActionListener(e -> aplicarFiltros());
        pnlFiltros.add(cbFiltroContrato);

        pnlFiltros.add(new JLabel("Piloto:"));
        txtFiltroNome = new JTextField(15);
        txtFiltroNome.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { aplicarFiltros(); }
            public void removeUpdate(DocumentEvent e) { aplicarFiltros(); }
            public void changedUpdate(DocumentEvent e) { aplicarFiltros(); }
        });
        pnlFiltros.add(txtFiltroNome);

        pnlTopo.add(pnlFiltros, BorderLayout.SOUTH);
        painelPrincipal.add(pnlTopo, BorderLayout.NORTH);

        // =================================================================================
        // DIVISÃO INFERIOR (CENTRO)
        // =================================================================================
        JPanel pnlInferior = new JPanel(new GridLayout(1, 2, 10, 0));
        pnlInferior.setOpaque(false);

        // --- COLUNA ESQUERDA: ANÁLISE DETALHADA ---
        JPanel pnlAnalise = new JPanel(new BorderLayout());
        pnlAnalise.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Análise Detalhada"));
        pnlAnalise.setBackground(Color.WHITE);

        // Header
        JPanel pnlHeaderStats = new JPanel(new BorderLayout());
        pnlHeaderStats.setOpaque(false);
        pnlHeaderStats.setBorder(new EmptyBorder(10, 10, 5, 10));

        lbFotoPiloto = new JLabel();
        lbFotoPiloto.setPreferredSize(new Dimension(50, 50));
        lbFotoPiloto.setHorizontalAlignment(SwingConstants.CENTER);
        pnlHeaderStats.add(lbFotoPiloto, BorderLayout.WEST);

        JPanel pnlTextosHeader = new JPanel(new GridLayout(2, 1));
        pnlTextosHeader.setOpaque(false);
        pnlTextosHeader.setBorder(new EmptyBorder(0, 15, 0, 0));
        
        lbNomePilotoStats = new JLabel("Selecione um Piloto");
        lbNomePilotoStats.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lbNacionalidadeStats = new JLabel("");
        lbNacionalidadeStats.setForeground(Color.DARK_GRAY);

        pnlTextosHeader.add(lbNomePilotoStats);
        pnlTextosHeader.add(lbNacionalidadeStats);
        pnlHeaderStats.add(pnlTextosHeader, BorderLayout.CENTER);

        pnlAnalise.add(pnlHeaderStats, BorderLayout.NORTH);

        // Lista de Atributos
        pnlListaStats = new JPanel(new GridLayout(0, 2, 10, 5));
        pnlListaStats.setOpaque(false);
        pnlListaStats.setBorder(new EmptyBorder(10, 20, 10, 20));
        
        JScrollPane scrollStats = new JScrollPane(pnlListaStats);
        scrollStats.setBorder(null);
        scrollStats.getViewport().setBackground(Color.WHITE);
        pnlAnalise.add(scrollStats, BorderLayout.CENTER);
        
        pnlInferior.add(pnlAnalise);

        // --- COLUNA DIREITA: MINHA EQUIPE + PROPOSTA ---
        JPanel pnlDireita = new JPanel(new BorderLayout(0, 10));
        pnlDireita.setOpaque(false);

        // A. MINHA EQUIPE
        JPanel pnlMinhaEquipe = new JPanel(new BorderLayout());
        pnlMinhaEquipe.setBorder(new TitledBorder(new LineBorder(Color.GRAY), "Minha Equipe"));
        pnlMinhaEquipe.setBackground(Color.WHITE);

        String[] colE = {"Função", "", "Nome", "Over", "Fim Contrato", "Salário"};
        modeloMinhaEquipe = new DefaultTableModel(colE, 0) { public boolean isCellEditable(int r, int c) { return false; }};
        tabelaMinhaEquipe = new JTable(modeloMinhaEquipe);
        configurarTabelaMinhaEquipe(tabelaMinhaEquipe);
        
        // Tamanho fixo para 5 linhas
        tabelaMinhaEquipe.setPreferredScrollableViewportSize(new Dimension(500, 30 * 5)); 
        
        JScrollPane scrollEquipe = new JScrollPane(tabelaMinhaEquipe);
        scrollEquipe.getViewport().setBackground(Color.WHITE);
        scrollEquipe.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        
        pnlMinhaEquipe.add(scrollEquipe, BorderLayout.CENTER);
        
        tabelaMinhaEquipe.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabelaMinhaEquipe.getSelectedRow() != -1) {
                tabelaPrincipal.clearSelection();
                int row = tabelaMinhaEquipe.getSelectedRow();
                String nome = (String) modeloMinhaEquipe.getValueAt(row, 2);
                Piloto p = buscarPilotoPorNome(nome);
                selecionarPiloto(p);
            }
        });
        pnlDireita.add(pnlMinhaEquipe, BorderLayout.NORTH);

        // B. PROPOSTA (Reformulado)
        JPanel pnlPropostaContainer = new JPanel(new BorderLayout());
        pnlPropostaContainer.setBorder(new TitledBorder(new LineBorder(new Color(100, 100, 100)), "Negociação"));
        pnlPropostaContainer.setBackground(new Color(245, 250, 255));
        
        JPanel pnlFormProposta = new JPanel(null);
        pnlFormProposta.setOpaque(false);
        
        lbTituloAcao = new JLabel("Selecione um piloto");
        lbTituloAcao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTituloAcao.setBounds(15, 10, 400, 20);
        pnlFormProposta.add(lbTituloAcao);
        
        int yBase = 45;
        // 1. Salário (Spinner)
        pnlFormProposta.add(criarLabel("Salário Mensal:", 15, yBase));
        
        // Modelo do Spinner: Valor atual 0.5, Mínimo 0.1, Max 100.0, Passo 0.1
        SpinnerNumberModel spinModel = new SpinnerNumberModel(0.5, 0.1, 100.0, 0.1);
        spinSalario = new JSpinner(spinModel);
        // Formata para mostrar "0.5" em vez de "0,500" se possível, ou padrao
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinSalario, "0.0 'mi'");
        spinSalario.setEditor(editor);
        spinSalario.setBounds(120, yBase, 80, 25);
        pnlFormProposta.add(spinSalario);

        // 2. Duração (ComboBox com descontos)
        pnlFormProposta.add(criarLabel("Duração:", 220, yBase));
        String[] opcoesDuracao = {"1 Ano", "2 Anos (-5%)", "3 Anos (-10%)"};
        cbDuracaoInput = new JComboBox<>(opcoesDuracao);
        cbDuracaoInput.setBounds(280, yBase, 150, 25);
        pnlFormProposta.add(cbDuracaoInput);

        int y2 = 85;
        // 3. Vaga (Titular/Reserva)
        pnlFormProposta.add(criarLabel("Função:", 15, y2));
        cbVagaInput = new JComboBox<>(TipoContrato.values());
        if (!isF1) cbVagaInput.removeItem(TipoContrato.RESERVA);
        cbVagaInput.setBounds(120, y2, 120, 25);
        pnlFormProposta.add(cbVagaInput);
        
        // 5. Botão de Ação
        btnConfirmarAcao = new JButton("ENVIAR PROPOSTA");
        btnConfirmarAcao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmarAcao.setBackground(new Color(0, 120, 215));
        btnConfirmarAcao.setForeground(Color.WHITE);
        btnConfirmarAcao.setBounds(280, 80, 150, 35);
        btnConfirmarAcao.addActionListener(e -> executarAcaoContrato());
        pnlFormProposta.add(btnConfirmarAcao);

        pnlPropostaContainer.add(pnlFormProposta, BorderLayout.CENTER);
        pnlDireita.add(pnlPropostaContainer, BorderLayout.CENTER);

        pnlInferior.add(pnlDireita);
        painelPrincipal.add(pnlInferior, BorderLayout.CENTER);
    }
    
    // =================================================================================
    // LÓGICA DE DADOS
    // =================================================================================

    private void atualizarTabelaPrincipal() {
        modeloPrincipal.setRowCount(0);
        Random rng = new Random();

        for (Piloto p : todosPilotos) {
            String nomeEquipe = "-";
            String funcao = "Sem Contrato";
            String contratoStr = "-";
            
            if (p.getContrato() != null) {
                nomeEquipe = p.getContrato().getEquipeAtual().getNome();
                if (p.getContrato().getTipo() == TipoContrato.TITULAR) funcao = "Titular";
                else if (p.getContrato().getTipo() == TipoContrato.RESERVA) funcao = "Reserva";
                
                int meses = p.getContrato().getMesesRestantes();
                if (meses > 0) contratoStr = meses + " meses";
                else contratoStr = (1 + rng.nextInt(4)) + " anos";
            }
            
            modeloPrincipal.addRow(new Object[]{
                p.getNacionalidade(),
                p.getNome(),
                nomeEquipe,
                p.getIdade(),
                (int)p.getOverall(),
                contratoStr,
                funcao,
                p.getExigenciaMinimaDeEquipe()
            });
        }
    }

    private void atualizarMinhaEquipe() {
        modeloMinhaEquipe.setRowCount(0);
        
        for (Piloto p : minhaEquipe.getPilotosTitulares()) {
            double sal = (p.getContrato() != null) ? p.getContrato().getSalarioMensal() : 0;
            String tempo = (p.getContrato() != null) ? p.getContrato().getMesesRestantes() + " m" : "-";
            modeloMinhaEquipe.addRow(new Object[]{
                "Titular", p.getNacionalidade(), p.getNome(), (int)p.getOverall(), tempo, "€ " + formatarMoeda(sal)
            });
        }
        
        for (Piloto p : minhaEquipe.getPilotosReservas()) {
            double sal = (p.getContrato() != null) ? p.getContrato().getSalarioMensal() : 0;
            String tempo = (p.getContrato() != null) ? p.getContrato().getMesesRestantes() + " m" : "-";
            modeloMinhaEquipe.addRow(new Object[]{
                "Reserva", p.getNacionalidade(), p.getNome(), (int)p.getOverall(), tempo, "€ " + formatarMoeda(sal)
            });
        }
    }

    private void aplicarFiltros() {
        String fNome = txtFiltroNome.getText().toLowerCase();
        String fOver = (String) cbFiltroOver.getSelectedItem();
        String fIdade = (String) cbFiltroIdade.getSelectedItem();
        String fFuncao = (String) cbFiltroFuncao.getSelectedItem();
        String fContrato = (String) cbFiltroContrato.getSelectedItem();

        sorterPrincipal.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                String nome = entry.getStringValue(1).toLowerCase();
                if (!nome.contains(fNome)) return false;

                int over = (int) entry.getValue(4);
                if (!fOver.equals("Todos")) {
                    int min = Integer.parseInt(fOver.replace("+", ""));
                    if (over < min) return false;
                }

                int idade = (int) entry.getValue(3);
                if (fIdade.equals("Até 21") && idade > 21) return false;
                if (fIdade.equals("22-29") && (idade < 22 || idade > 29)) return false;
                if (fIdade.equals("30-35") && (idade < 30 || idade > 35)) return false;
                if (fIdade.equals("36+") && idade < 36) return false;

                String funcao = entry.getStringValue(6);
                if (!fFuncao.equals("Todas") && !funcao.equalsIgnoreCase(fFuncao)) return false;

                String cont = entry.getStringValue(5); 
                if (!fContrato.equals("Todos")) {
                    boolean semContrato = cont.equals("-");
                    if (fContrato.startsWith("Livre") && !semContrato) return false;
                    
                    if (!semContrato && cont.contains("meses")) {
                        int meses = Integer.parseInt(cont.split(" ")[0]);
                        if (fContrato.equals("Até 6 meses") && meses > 6) return false;
                        if (fContrato.equals("Até 1 ano") && meses > 12) return false;
                        if (fContrato.equals("Mais de 1 ano")) return false; 
                    } else if (!semContrato && cont.contains("anos")) {
                         if (fContrato.equals("Até 6 meses") || fContrato.equals("Até 1 ano")) return false;
                    }
                }
                return true;
            }
        });
    }

    private Piloto buscarPilotoPorNome(String nome) {
        for (Piloto p : todosPilotos) {
            if (p.getNome().equals(nome)) return p;
        }
        return null;
    }

    private void selecionarPiloto(Piloto p) {
        this.pilotoSelecionado = p;
        
        lbNomePilotoStats.setText(p.getNome());
        lbNacionalidadeStats.setText(p.getNacionalidade() + ", " + p.getIdade() + " anos");
        try {
            lbFotoPiloto.setIcon(new FlatSVGIcon("resource/Bandeira " + p.getNacionalidade() + ".svg", 45, 45));
        } catch(Exception e) { lbFotoPiloto.setIcon(null); }

        // --- PREENCHER LISTA DE STATS (Cores Customizadas) ---
        pnlListaStats.removeAll();
        // Over = Azul, Salário/Multa = Vermelho, Resto = Preto
        adicionarItemStat("Overall", String.valueOf((int)p.getOverall()), Color.BLUE);
        adicionarItemStat("Experiência", String.valueOf((int)p.getExperiencia()), Color.BLACK);
        adicionarItemStat("Ritmo", String.valueOf((int)p.getRitmo()), Color.BLACK);
        adicionarItemStat("Agressividade", String.valueOf((int)p.getAgressividade()), Color.BLACK);
        adicionarItemStat("Ultrapassagem", String.valueOf((int)p.getUltrapassagem()), Color.BLACK);
        adicionarItemStat("Defesa", String.valueOf((int)p.getDefesa()), Color.BLACK);
        adicionarItemStat("Físico", String.valueOf((int)p.getFisico()), Color.BLACK);
        adicionarItemStat("Largada", String.valueOf((int)p.getLargada()), Color.BLACK);
        
        adicionarItemStat("Hab. Chuva", String.valueOf((int)p.getHabilidadeChuva()), Color.BLACK);
        adicionarItemStat("Hab. Rua", String.valueOf((int)p.getHabilidadeRua()), Color.BLACK);
        adicionarItemStat("Hab. Misto", String.valueOf((int)p.getHabilidadeMisto()), Color.BLACK);
        adicionarItemStat("Hab. Oval", String.valueOf((int)p.getHabilidadeOval()), Color.BLACK);
        
        if (p.getContrato() != null) {
            adicionarItemStat("Equipe Atual", p.getContrato().getEquipeAtual().getNome(), Color.BLACK);
            adicionarItemStat("Salário Atual", "€ " + formatarMoeda(p.getContrato().getSalarioMensal()), Color.RED);
            
            // Adiciona Multa
            double multa = p.getContrato().calcularMultaRescisoria();
            // Se for minha equipe, a multa é zero (tecnicamente) ou não aplicável para exibição de custo
            if (!pilotosMinhaEquipe.contains(p)) {
                adicionarItemStat("Multa Rescisória", "€ " + formatarMoeda(multa), Color.RED);
            }
            
            if(p.getContrato().getMesesRestantes() > 0)
                adicionarItemStat("Tempo Restante", p.getContrato().getMesesRestantes() + " meses", Color.BLACK);
        } else {
            adicionarItemStat("Situação", "Agente Livre", Color.BLACK);
        }
        
        pnlListaStats.revalidate();
        pnlListaStats.repaint();

        // Inputs Proposta
        boolean isMeu = pilotosMinhaEquipe.contains(p);
        lbTituloAcao.setText(isMeu ? "Renovação: " + p.getNome() : "Negociar: " + p.getNome());
        btnConfirmarAcao.setText(isMeu ? "RENOVAR" : "CONTRATAR");
        
        spinSalario.setEnabled(true);
        cbDuracaoInput.setEnabled(true);
        cbVagaInput.setEnabled(true);
        btnConfirmarAcao.setEnabled(true);
        
        if (p.getContrato() != null) {
            spinSalario.setValue(p.getContrato().getSalarioMensal());
            if (isMeu) cbVagaInput.setSelectedItem(p.getContrato().getTipo());
        } else {
            spinSalario.setValue(0.5);
        }
    }
    
    private void limparSelecao() {
        pilotoSelecionado = null;
        lbNomePilotoStats.setText("---");
        lbNacionalidadeStats.setText("");
        lbFotoPiloto.setIcon(null);
        pnlListaStats.removeAll();
        pnlListaStats.repaint();
        
        spinSalario.setEnabled(false);
        cbDuracaoInput.setEnabled(false);
        cbVagaInput.setEnabled(false);
        btnConfirmarAcao.setEnabled(false);
    }

    private void executarAcaoContrato() {
        if (pilotoSelecionado == null) return;
        
        // Verifica limites da equipe 
        int nTit = minhaEquipe.getPilotosTitulares().size();
        int nRes = minhaEquipe.getPilotosReservas().size();
        if (pilotosMinhaEquipe.contains(pilotoSelecionado)) {
            if (pilotoSelecionado.isTitular()) nTit--; else nRes--; 
        } else {
            if (nTit + nRes >= 5) {
                JOptionPane.showMessageDialog(this, "Equipe Cheia (Máx 5)!", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        try {
            double salBase = (Double) spinSalario.getValue();
            
            // Lógica de Desconto por Duração
            int duracaoIndex = cbDuracaoInput.getSelectedIndex();
            double desconto = 0;
            int meses = 12;
            
            if (duracaoIndex == 0) { // 1 Ano
                meses = 12; desconto = 0;
            } else if (duracaoIndex == 1) { // 2 Anos
                meses = 24; desconto = 0.05;
            } else if (duracaoIndex == 2) { // 3 Anos
                meses = 36; desconto = 0.10;
            }
            
            double salFinal = salBase * (1.0 - desconto);
            TipoContrato tipo = (TipoContrato) cbVagaInput.getSelectedItem();
            
            // Confirmar Multa se necessário
            double custoMulta = 0;
            if (pilotoSelecionado.getContrato() != null && !pilotosMinhaEquipe.contains(pilotoSelecionado)) {
                custoMulta = pilotoSelecionado.getContrato().calcularMultaRescisoria();
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Este piloto tem contrato vigente.\nDeseja pagar a multa de € " + formatarMoeda(custoMulta) + "?",
                    "Pagamento de Multa", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;
            }

            String resp = pilotoSelecionado.receberProposta(minhaEquipe, salFinal, tipo);
            
            if (resp.startsWith("ACEITO")) {
                if (minhaEquipe.contratarPiloto(pilotoSelecionado, salFinal, custoMulta, meses, tipo)) {
                    JOptionPane.showMessageDialog(this, "Sucesso! Contrato de " + meses + " meses assinado.\nSalário: € " + formatarMoeda(salFinal));
                    carregarListaPilotos(); 
                    atualizarTabelaPrincipal();
                    atualizarMinhaEquipe();
                } else {
                    JOptionPane.showMessageDialog(this, "Erro financeiro ou saldo insuficiente.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Recusado: " + resp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Dados Inválidos");
        }
    }

    // =================================================================================
    // CONFIGURAÇÃO VISUAL
    // =================================================================================

    private void configurarTabelaGeral(JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableColumnModel cm = table.getColumnModel();
        
        cm.getColumn(0).setCellRenderer(new BandeiraRenderer());
        cm.getColumn(0).setMaxWidth(40); cm.getColumn(0).setPreferredWidth(40);
        
        cm.getColumn(3).setMaxWidth(50); // Idade
        cm.getColumn(4).setMaxWidth(50); // Over
        
        DefaultTableCellRenderer c = new DefaultTableCellRenderer();
        c.setHorizontalAlignment(SwingConstants.CENTER);
        for(int i=1; i<table.getColumnCount(); i++) cm.getColumn(i).setCellRenderer(c);
    }
    
    private void configurarTabelaMinhaEquipe(JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableColumnModel cm = table.getColumnModel();
        
        cm.getColumn(3).setMaxWidth(35); cm.getColumn(3).setPreferredWidth(35);
        
        DefaultTableCellRenderer funcRenderer = new DefaultTableCellRenderer() {
            public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
                super.getTableCellRendererComponent(t,v,s,f,r,c);
                setHorizontalAlignment(CENTER);
                String txt = (String) t.getValueAt(r, 0);
                if (txt.equals("Reserva")) setForeground(Color.GRAY);
                else setForeground(new Color(0, 100, 0));
                return this;
            }
        };
        cm.getColumn(0).setCellRenderer(funcRenderer);
        cm.getColumn(1).setCellRenderer(new BandeiraRenderer());
        cm.getColumn(1).setMaxWidth(40);
        
        DefaultTableCellRenderer c = new DefaultTableCellRenderer();
        c.setHorizontalAlignment(SwingConstants.CENTER);
        cm.getColumn(2).setCellRenderer(c);
        cm.getColumn(3).setCellRenderer(c);
        cm.getColumn(4).setCellRenderer(c);
        cm.getColumn(5).setCellRenderer(c);
    }
    
    // MUDANÇA: Aceita COR como parâmetro
    private void adicionarItemStat(String label, String valor, Color corValor) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new MatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        JLabel lbKey = new JLabel(label);
        lbKey.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbKey.setForeground(Color.BLACK); // Labels Pretos
        
        JLabel lbVal = new JLabel(valor);
        boolean isOver = label.equals("Overall");
        lbVal.setFont(new Font("Segoe UI", isOver ? Font.BOLD : Font.PLAIN, isOver ? 14 : 12));
        lbVal.setForeground(corValor); // Cor customizada
        lbVal.setHorizontalAlignment(SwingConstants.RIGHT);
        
        p.add(lbKey, BorderLayout.WEST);
        p.add(lbVal, BorderLayout.EAST);
        
        pnlListaStats.add(p);
    }

    class BandeiraRenderer extends DefaultTableCellRenderer {
        Map<String, Icon> cache = new HashMap<>();
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
            super.getTableCellRendererComponent(t,v,s,f,r,c);
            setText(""); setHorizontalAlignment(CENTER);
            if(v instanceof String) {
                String p = (String)v;
                if(!cache.containsKey(p)) {
                    try {
                        FlatSVGIcon ic = new FlatSVGIcon("resource/Bandeira " + p + ".svg");
                        float scale = Math.min(24f/ic.getIconWidth(), 16f/ic.getIconHeight());
                        cache.put(p, new FlatSVGIcon("resource/Bandeira " + p + ".svg", (int)(ic.getIconWidth()*scale), (int)(ic.getIconHeight()*scale)));
                    } catch(Exception e){ cache.put(p, null); }
                }
                setIcon(cache.get(p));
            }
            return this;
        }
    }

    private JLabel criarLabel(String t, int x, int y) {
        JLabel l = new JLabel(t); l.setBounds(x,y,100,20); return l;
    }
    private String formatarMoeda(double v) {
        if(v < 1) return (int)(v*1000) + " k";
        return new DecimalFormat("#,##0.00").format(v) + " mi";
    }
}
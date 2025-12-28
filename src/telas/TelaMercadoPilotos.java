package telas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import dados.DadosDoJogo;
import dados.SessaoJogo;
import modelos.Contrato;
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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class TelaMercadoPilotos extends JDialog {

    private DadosDoJogo dados;
    private Equipe minhaEquipe;
    private boolean isF1;
    private int anoAtualJogo;

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
    private JButton btnContratarAgora, btnPreContrato;
    private JSpinner spinSalario;
    private JComboBox<String> cbDuracaoInput;
    private JComboBox<TipoContrato> cbVagaInput;
    private JLabel lbTituloAcao;
    private JLabel lbReputacaoEquipe; 

    private Piloto pilotoSelecionado = null;

    public TelaMercadoPilotos(DadosDoJogo dados) {
        this.dados = dados;
        this.minhaEquipe = dados.getEquipeDoJogador();
        this.isF1 = SessaoJogo.categoriaKey != null && SessaoJogo.categoriaKey.toLowerCase().contains("f1");
        this.anoAtualJogo = dados.getAnoAtual();

        // Limpa duplicatas da memória da equipe antes de carregar a tela
        sanitizarEquipeLocal();

        setTitle("Mercado Global de Pilotos");
        setModal(true);
        setSize(1200, 750); 
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
    
    private void sanitizarEquipeLocal() {
        Set<String> nomesUnicos = new HashSet<>();
        List<Piloto> titularesLimpos = new ArrayList<>();
        List<Piloto> reservasLimpos = new ArrayList<>();

        for (Piloto p : minhaEquipe.getPilotosTitulares()) {
            if (nomesUnicos.add(p.getNome())) titularesLimpos.add(p);
        }
        for (Piloto p : minhaEquipe.getPilotosReservas()) {
            if (nomesUnicos.add(p.getNome())) reservasLimpos.add(p);
        }
        
        minhaEquipe.getPilotosTitulares().clear();
        minhaEquipe.getPilotosTitulares().addAll(titularesLimpos);
        minhaEquipe.getPilotosReservas().clear();
        minhaEquipe.getPilotosReservas().addAll(reservasLimpos);
    }

    private void carregarListaPilotos() {
        todosPilotos.clear();
        pilotosMinhaEquipe.clear();
        
        List<Piloto> listaBruta = new ArrayList<>();
        for (Piloto p : dados.getTodosOsPilotos()) {
            if (p != null) listaBruta.add(p);
        }
        
        listaBruta.sort((p1, p2) -> {
            int peso1 = getPesoFuncao(p1);
            int peso2 = getPesoFuncao(p2);
            if (peso1 != peso2) return Integer.compare(peso2, peso1);

            int compOver = Double.compare(p2.getOverall(), p1.getOverall());
            if (compOver != 0) return compOver;

            int m1 = (p1.getContrato() != null) ? p1.getContrato().getMesesRestantes() : 999;
            int m2 = (p2.getContrato() != null) ? p2.getContrato().getMesesRestantes() : 999;
            if (m1 != m2) return Integer.compare(m1, m2);

            double s1 = (p1.getContrato() != null) ? p1.getContrato().getSalarioMensal() : 0;
            double s2 = (p2.getContrato() != null) ? p2.getContrato().getSalarioMensal() : 0;
            int compSal = Double.compare(s2, s1);
            if (compSal != 0) return compSal;

            return p1.getNome().compareTo(p2.getNome());
        });

        todosPilotos.addAll(listaBruta);

        for (Piloto p : todosPilotos) {
            if (minhaEquipe.getPilotosTitulares().contains(p) || minhaEquipe.getPilotosReservas().contains(p)) {
                pilotosMinhaEquipe.add(p);
            }
        }
    }
    
    private int getPesoFuncao(Piloto p) {
        if (p.getContrato() == null) return 1;
        if (p.getContrato().getTipo() == TipoContrato.TITULAR) return 3;
        if (p.getContrato().getTipo() == TipoContrato.RESERVA) return 2;
        return 1;
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

        String[] colunas = {
            "", 
            "Nome", 
            "Equipe " + anoAtualJogo, 
            "Função " + anoAtualJogo, 
            "Equipe " + (anoAtualJogo + 1), 
            "Função " + (anoAtualJogo + 1), 
            "Idade", 
            "Over", 
            "Exigência"
        };
        
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
        scrollTopo.setPreferredSize(new Dimension(1030, 250)); 
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
        cbFiltroContrato = new JComboBox<>(new String[]{"Todos", "Livre (0 meses)", "Até fim do ano", "Longo Prazo"});
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
        pnlHeaderStats.add(pnlTextosHeader, BorderLayout.NORTH);

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

        // B. PROPOSTA
        JPanel pnlPropostaContainer = new JPanel(new BorderLayout());
        pnlPropostaContainer.setBorder(new TitledBorder(new LineBorder(new Color(100, 100, 100)), "Negociação"));
        pnlPropostaContainer.setBackground(new Color(245, 250, 255));
        
        JPanel pnlFormProposta = new JPanel(null);
        pnlFormProposta.setOpaque(false);
        
        lbTituloAcao = new JLabel("Selecione um piloto");
        lbTituloAcao.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbTituloAcao.setBounds(15, 10, 300, 20);
        pnlFormProposta.add(lbTituloAcao);
        
        lbReputacaoEquipe = new JLabel("Reputação: " + minhaEquipe.getReputacao());
        lbReputacaoEquipe.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lbReputacaoEquipe.setForeground(new Color(100, 100, 100));
        lbReputacaoEquipe.setHorizontalAlignment(SwingConstants.RIGHT);
        lbReputacaoEquipe.setBounds(350, 10, 180, 20); 
        pnlFormProposta.add(lbReputacaoEquipe);
        
        int yBase = 45;
        // 1. Salário (Spinner)
        pnlFormProposta.add(criarLabel("Salário Mensal:", 15, yBase));
        
        SpinnerNumberModel spinModel = new SpinnerNumberModel(0.5, 0.1, 100.0, 0.1);
        spinSalario = new JSpinner(spinModel);
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinSalario, "0.0 'mi'");
        spinSalario.setEditor(editor);
        spinSalario.setBounds(120, yBase, 80, 25);
        pnlFormProposta.add(spinSalario);

        // 2. Duração
        pnlFormProposta.add(criarLabel("Duração:", 220, yBase));
        String[] opcoesDuracao = {"1 Temporada", "2 Temporadas (-5%)"};
        cbDuracaoInput = new JComboBox<>(opcoesDuracao);
        cbDuracaoInput.setBounds(280, yBase, 150, 25);
        pnlFormProposta.add(cbDuracaoInput);

        int y2 = 85;
        // 3. Vaga
        pnlFormProposta.add(criarLabel("Função:", 15, y2));
        cbVagaInput = new JComboBox<>(TipoContrato.values());
        if (!isF1) cbVagaInput.removeItem(TipoContrato.RESERVA);
        cbVagaInput.setBounds(120, y2, 120, 25);
        pnlFormProposta.add(cbVagaInput);
        
        // Botões
        btnContratarAgora = new JButton("CONTRATAR AGORA");
        btnContratarAgora.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnContratarAgora.setBackground(new Color(0, 120, 215));
        btnContratarAgora.setForeground(Color.WHITE);
        btnContratarAgora.setBounds(250, 80, 160, 35); 
        btnContratarAgora.addActionListener(e -> executarContratacao(true));
        pnlFormProposta.add(btnContratarAgora);
        
        // Texto do botão alterado conforme solicitação
        btnPreContrato = new JButton("CONTRATAR P/ " + (anoAtualJogo + 1));
        btnPreContrato.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnPreContrato.setBackground(new Color(0, 150, 100)); // Verde
        btnPreContrato.setForeground(Color.WHITE);
        btnPreContrato.setBounds(420, 80, 160, 35); // Aumentado para caber o texto
        btnPreContrato.addActionListener(e -> executarContratacao(false));
        pnlFormProposta.add(btnPreContrato);

        pnlPropostaContainer.add(pnlFormProposta, BorderLayout.CENTER);
        pnlDireita.add(pnlPropostaContainer, BorderLayout.CENTER);

        pnlInferior.add(pnlDireita);
        painelPrincipal.add(pnlInferior, BorderLayout.CENTER);
    }
    
    private void atualizarTabelaPrincipal() {
        modeloPrincipal.setRowCount(0);

        for (Piloto p : todosPilotos) {
            String nomeEquipeAtual = "-";
            String funcaoAtual = "Livre";
            String nomeEquipeProx = "Livre";
            String funcaoProx = "-";
            
            // 1. Analisa Contrato VIGENTE (Ano Atual)
            if (p.getContrato() != null) {
                nomeEquipeAtual = p.getContrato().getEquipeAtual().getNome();
                if (p.getContrato().getTipo() == TipoContrato.TITULAR) funcaoAtual = "Titular";
                else if (p.getContrato().getTipo() == TipoContrato.RESERVA) funcaoAtual = "Reserva";
                
                // Por padrão, se não tiver contrato futuro definido, assume continuação se > 12 meses
                int meses = p.getContrato().getMesesRestantes();
                if (meses > 12) {
                    nomeEquipeProx = nomeEquipeAtual;
                    funcaoProx = funcaoAtual;
                }
            }
            
            // 2. Analisa Contrato FUTURO (Prioridade sobre a projeção do atual)
            if (p.getContratoFuturo() != null) {
                nomeEquipeProx = p.getContratoFuturo().getEquipeAtual().getNome();
                if (p.getContratoFuturo().getTipo() == TipoContrato.TITULAR) funcaoProx = "Titular";
                else if (p.getContratoFuturo().getTipo() == TipoContrato.RESERVA) funcaoProx = "Reserva";
            }
            
            modeloPrincipal.addRow(new Object[]{
                p.getNacionalidade(),
                p.getNome(),
                nomeEquipeAtual, 
                funcaoAtual,     
                nomeEquipeProx,  
                funcaoProx,      
                p.getIdade(),
                (int)p.getOverall(),
                p.getExigenciaMinimaDeEquipe()
            });
        }
    }

    private void atualizarMinhaEquipe() {
        modeloMinhaEquipe.setRowCount(0);
        
        // A tabela Minha Equipe mostra apenas quem está ATIVO HOJE
        List<Piloto> listaUnificada = new ArrayList<>();
        listaUnificada.addAll(minhaEquipe.getPilotosTitulares());
        listaUnificada.addAll(minhaEquipe.getPilotosReservas());
        
        listaUnificada.sort((p1, p2) -> {
            boolean t1 = p1.getContrato() != null && p1.getContrato().getTipo() == TipoContrato.TITULAR;
            boolean t2 = p2.getContrato() != null && p2.getContrato().getTipo() == TipoContrato.TITULAR;
            return Boolean.compare(t2, t1);
        });
        
        for (Piloto p : listaUnificada) {
            String funcaoExibida = "Titular";
            if (p.getContrato() != null && p.getContrato().getTipo() == TipoContrato.RESERVA) {
                funcaoExibida = "Reserva";
            }
            
            double sal = (p.getContrato() != null) ? p.getContrato().getSalarioMensal() : 0;
            int meses = (p.getContrato() != null) ? p.getContrato().getMesesRestantes() : 0;
            String tempo = (meses > 12) ? "2 Temps" : (meses > 0) ? "1 Temp" : "-";
            
            modeloMinhaEquipe.addRow(new Object[]{
                funcaoExibida, 
                p.getNacionalidade(), 
                p.getNome(), 
                (int)p.getOverall(), 
                tempo, 
                "€ " + formatarMoeda(sal)
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

                int over = (int) entry.getValue(7); 
                if (!fOver.equals("Todos")) {
                    int min = Integer.parseInt(fOver.replace("+", ""));
                    if (over < min) return false;
                }

                int idade = (int) entry.getValue(6); 
                if (fIdade.equals("Até 21") && idade > 21) return false;
                if (fIdade.equals("22-29") && (idade < 22 || idade > 29)) return false;
                if (fIdade.equals("30-35") && (idade < 30 || idade > 35)) return false;
                if (fIdade.equals("36+") && idade < 36) return false;

                String funcao = entry.getStringValue(3); 
                if (!fFuncao.equals("Todas") && !funcao.equalsIgnoreCase(fFuncao)) return false;

                String eqProx = entry.getStringValue(4); 
                
                if (fContrato.equals("Livre (0 meses)")) {
                   if (!entry.getStringValue(2).equals("-")) return false;
                } else if (fContrato.equals("Até fim do ano")) {
                   if (entry.getStringValue(2).equals("-")) return false; 
                   if (!eqProx.equals("Livre")) return false;
                } else if (fContrato.equals("Longo Prazo")) {
                   if (eqProx.equals("Livre")) return false;
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

        pnlListaStats.removeAll();
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
        
        boolean temContrato = p.getContrato() != null;
        boolean isMeu = pilotosMinhaEquipe.contains(p);
        boolean temContratoFuturo = p.getContratoFuturo() != null;
        
        if (temContrato) {
            adicionarItemStat("Equipe Atual", p.getContrato().getEquipeAtual().getNome(), Color.BLACK);
            adicionarItemStat("Salário Atual", "€ " + formatarMoeda(p.getContrato().getSalarioMensal()), Color.RED);
            
            double multa = p.getContrato().calcularMultaRescisoria();
            if (!isMeu) {
                adicionarItemStat("Multa Rescisória", "€ " + formatarMoeda(multa), Color.RED);
            }
            
            if(p.getContrato().getMesesRestantes() > 0)
                adicionarItemStat("Tempo Restante", p.getContrato().getMesesRestantes() + " meses", Color.BLACK);
        } else {
            adicionarItemStat("Situação", "Agente Livre", Color.BLACK);
        }
        
        // Exibe informação se já tem contrato para ano seguinte
        if (temContratoFuturo) {
             adicionarItemStat("Futuro (" + (anoAtualJogo + 1) + ")", p.getContratoFuturo().getEquipeAtual().getNome(), new Color(0, 100, 0));
        }
        
        pnlListaStats.revalidate();
        pnlListaStats.repaint();

        // Configuração Dinâmica dos Botões
        if (isMeu) {
            lbTituloAcao.setText("Renovação: " + p.getNome());
            btnContratarAgora.setText("RENOVAR CONTRATO");
            btnPreContrato.setVisible(false);
            
            spinSalario.setValue(p.getContrato().getSalarioMensal());
            cbVagaInput.setSelectedItem(p.getContrato().getTipo());
        } else {
            lbTituloAcao.setText("Negociar: " + p.getNome());
            btnContratarAgora.setText("CONTRATAR AGORA");
            btnPreContrato.setVisible(true);
            
            spinSalario.setValue(0.5);
        }
        
        spinSalario.setEnabled(true);
        cbDuracaoInput.setEnabled(true);
        cbVagaInput.setEnabled(true);
        btnContratarAgora.setEnabled(true);
        
        // Regra para habilitar Pré-Contrato:
        // 1. Não é meu piloto
        // 2. Piloto não tem contrato futuro assinado ainda
        // 3. Se tiver contrato atual, deve faltar 12 meses ou menos
        boolean podePreContrato = !isMeu && !temContratoFuturo;
        
        if (podePreContrato && temContrato) {
            if (p.getContrato().getMesesRestantes() > 12) podePreContrato = false;
        }
        
        btnPreContrato.setEnabled(podePreContrato);
        if (!podePreContrato) {
            btnPreContrato.setToolTipText("Indisponível (Contrato longo ou já assinado)");
        } else {
            btnPreContrato.setToolTipText("Assinar para " + (anoAtualJogo + 1) + " (Sem Multa)");
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
        btnContratarAgora.setEnabled(false);
        btnPreContrato.setEnabled(false);
    }
    
    private int contarPilotosUnicosDaEquipe() {
        Set<String> nomes = new HashSet<>();
        for (Piloto p : minhaEquipe.getPilotosTitulares()) nomes.add(p.getNome());
        for (Piloto p : minhaEquipe.getPilotosReservas()) nomes.add(p.getNome());
        return nomes.size();
    }

    private void executarContratacao(boolean contratarAgora) {
        if (pilotoSelecionado == null) return;
        
        boolean renovando = pilotosMinhaEquipe.contains(pilotoSelecionado);
        int totalPilotos = contarPilotosUnicosDaEquipe();
        
        // Se for contratar AGORA e não for renovação, verifica limite
        if (contratarAgora && !renovando && totalPilotos >= 5) {
             JOptionPane.showMessageDialog(this, "Equipe Cheia (Máx 5)!", "Erro", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        try {
            double salBase = (Double) spinSalario.getValue();
            
            int duracaoIndex = cbDuracaoInput.getSelectedIndex();
            double desconto = 0;
            int meses = 12; 
            
            if (duracaoIndex == 0) { 
                meses = 12; desconto = 0;
            } else if (duracaoIndex == 1) { 
                meses = 24; desconto = 0.05;
            }
            
            double salFinal = salBase * (1.0 - desconto);
            TipoContrato tipo = (TipoContrato) cbVagaInput.getSelectedItem();
            
            double custoMulta = 0;
            
            // Só cobra multa se for AGORA
            if (contratarAgora && pilotoSelecionado.getContrato() != null && !renovando) {
                custoMulta = pilotoSelecionado.getContrato().calcularMultaRescisoria();
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Este piloto tem contrato vigente.\nDeseja pagar a multa de € " + formatarMoeda(custoMulta) + "?",
                    "Pagamento de Multa", JOptionPane.YES_NO_OPTION);
                if (confirm != JOptionPane.YES_OPTION) return;
            }
            
            if (contratarAgora && minhaEquipe.getSaldoFinanceiro() < custoMulta) {
                JOptionPane.showMessageDialog(this, "Saldo Insuficiente para pagar a multa!");
                return;
            }

            // Simulação
            String resp = pilotoSelecionado.receberProposta(minhaEquipe, salFinal, tipo);
            
            if (!contratarAgora && resp.startsWith("RECUSADO: Sem dinheiro")) {
                resp = "ACEITO"; 
            }

            if (resp.startsWith("ACEITO")) {
                
                if (contratarAgora) {
                    // --- FLUXO 1: CONTRATAÇÃO IMEDIATA ---
                    boolean removidoTemp = false;
                    List<Piloto> listaOrigem = null;
                    
                    if (renovando) {
                        if (minhaEquipe.getPilotosTitulares().contains(pilotoSelecionado)) listaOrigem = minhaEquipe.getPilotosTitulares();
                        else listaOrigem = minhaEquipe.getPilotosReservas();
                        
                        if (listaOrigem != null) {
                            listaOrigem.remove(pilotoSelecionado);
                            removidoTemp = true;
                        }
                    }

                    if (minhaEquipe.contratarPiloto(pilotoSelecionado, salFinal, custoMulta, meses, tipo)) {
                        JOptionPane.showMessageDialog(this, renovando ? "Contrato Renovado!" : "Novo piloto contratado!");
                        recarregarTela();
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro: Limite de vagas ou saldo insuficiente.");
                        if (removidoTemp && listaOrigem != null) listaOrigem.add(pilotoSelecionado);
                    }
                    
                } else {
                    // --- FLUXO 2: PRÉ-CONTRATO (FUTURO) ---
                    // Não adiciona na equipe agora! Apenas salva no piloto.
                    Contrato novoContrato = new Contrato(minhaEquipe, salFinal, meses, tipo);
                    pilotoSelecionado.setContratoFuturo(novoContrato);
                    
                    JOptionPane.showMessageDialog(this, "Pré-Contrato assinado para " + (anoAtualJogo + 1) + "!");
                    recarregarTela();
                }

            } else {
                JOptionPane.showMessageDialog(this, "Recusado: " + resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Dados Inválidos");
        }
    }
    
    private void recarregarTela() {
        carregarListaPilotos(); 
        atualizarTabelaPrincipal();
        atualizarMinhaEquipe();
        limparSelecao();
    }

    private void configurarTabelaGeral(JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableColumnModel cm = table.getColumnModel();
        
        cm.getColumn(0).setCellRenderer(new BandeiraRenderer());
        cm.getColumn(0).setMaxWidth(40); cm.getColumn(0).setPreferredWidth(40);
        
        cm.getColumn(1).setPreferredWidth(150); 
        
        FuncaoRenderer fr = new FuncaoRenderer();
        cm.getColumn(3).setCellRenderer(fr);
        cm.getColumn(5).setCellRenderer(fr);

        cm.getColumn(6).setMaxWidth(50); 
        cm.getColumn(7).setMaxWidth(50); 
        
        DefaultTableCellRenderer c = new DefaultTableCellRenderer();
        c.setHorizontalAlignment(SwingConstants.CENTER);
        cm.getColumn(2).setCellRenderer(c); 
        cm.getColumn(4).setCellRenderer(c); 
        cm.getColumn(6).setCellRenderer(c); 
        cm.getColumn(7).setCellRenderer(c); 
        cm.getColumn(8).setCellRenderer(c); 
    }
    
    private void configurarTabelaMinhaEquipe(JTable table) {
        table.setRowHeight(30);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        TableColumnModel cm = table.getColumnModel();
        
        cm.getColumn(0).setPreferredWidth(60);
        cm.getColumn(0).setCellRenderer(new FuncaoRenderer());
        
        cm.getColumn(1).setCellRenderer(new BandeiraRenderer());
        cm.getColumn(1).setMaxWidth(40);
        
        DefaultTableCellRenderer c = new DefaultTableCellRenderer();
        c.setHorizontalAlignment(SwingConstants.CENTER);
        cm.getColumn(2).setCellRenderer(c);
        cm.getColumn(3).setCellRenderer(c);
        cm.getColumn(4).setCellRenderer(c);
        cm.getColumn(5).setCellRenderer(c);
    }
    
    private void adicionarItemStat(String label, String valor, Color corValor) {
        JPanel p = new JPanel(new BorderLayout());
        p.setOpaque(false);
        p.setBorder(new MatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        JLabel lbKey = new JLabel(label);
        lbKey.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbKey.setForeground(Color.BLACK);
        
        JLabel lbVal = new JLabel(valor);
        boolean isOver = label.equals("Overall");
        lbVal.setFont(new Font("Segoe UI", isOver ? Font.BOLD : Font.PLAIN, isOver ? 14 : 12));
        lbVal.setForeground(corValor);
        lbVal.setHorizontalAlignment(SwingConstants.RIGHT);
        
        p.add(lbKey, BorderLayout.WEST);
        p.add(lbVal, BorderLayout.EAST);
        
        pnlListaStats.add(p);
    }

    class FuncaoRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable t, Object v, boolean s, boolean f, int r, int c) {
            super.getTableCellRendererComponent(t,v,s,f,r,c);
            setHorizontalAlignment(CENTER);
            String txt = (String) v;
            
            if (txt.equalsIgnoreCase("Titular")) {
                setBackground(new Color(255, 200, 200)); 
                setForeground(Color.BLACK);
            } else if (txt.equalsIgnoreCase("Reserva")) {
                setBackground(new Color(255, 255, 200)); 
                setForeground(Color.BLACK);
            } else if (txt.equalsIgnoreCase("Livre") || txt.equals("-")) {
                setBackground(new Color(200, 255, 200)); 
                setForeground(Color.BLACK);
            } else {
                setBackground(Color.WHITE);
                setForeground(Color.BLACK);
            }
            
            if (s) { 
                setBackground(t.getSelectionBackground());
                setForeground(t.getSelectionForeground());
            }
            
            return this;
        }
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
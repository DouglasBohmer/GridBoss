
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;

public class PAGE_TESTE extends JFrame {
	private JPanel contentPane;
	private JButton BTVoltar;
	private JLabel LB_Crono;
	private JLabel LB_IMG_Pista;
	private JLabel LB_LocalPista;
	private JLabel LB_TipoPista;
	private JLabel LB_BandeiraPista;
	private JLabel LB_CurvaPista;
	private JLabel LB_ComprimentoPista;
	private JProgressBar ProgBar_TempoQualy;
	private JLabel LB_QualyEtapa;
	private JRadioButton RB_Normal_Tempo;
	private JLabel lblClimaAtual;
	private JLabel LB_Clima;
	private JLabel LB_IMG_Clima;
	private JButton btnNewButton;
	private JRadioButton RB_DobroDobro_Tempo;
	
	private int auxPause=0, aux=0, TIMER=1000, sec=60, min=60;
	private JLabel lblNewLabel_3;
	private JLabel LB_PneuAtual;
	private JLabel LB_IMGPneuAtual;
	private JButton BT_PlayPause;
	private JRadioButton RB_MetadeMetade_Tempo;
	private JLabel LB_TabelaTempos;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_11;
	private JRadioButton RB_PneuWet;
	private JRadioButton RB_PneuInter;
	private JRadioButton RB_PneuDuro;
	private JRadioButton RB_PneuMedio;
	private JRadioButton RB_PneuMacio;
	private JLabel lblNewLabel;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PAGE_TESTE frame = new PAGE_TESTE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PAGE_TESTE() {

		addWindowListener(new WindowAdapter() {

		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 521);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		
		
	}
	
	/*
	
	JLabel LB_PneuInter = new JLabel("");
		LB_PneuInter.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuIntermediarioF140px.png")));
		LB_PneuInter.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuInter.setBounds(460, 350, 60, 50);
		contentPane.add(LB_PneuInter);
		
		JLabel LB_PneuWet = new JLabel("");
		LB_PneuWet.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuChuvaF140px.png")));
		LB_PneuWet.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuWet.setBounds(400, 350, 60, 50);
		contentPane.add(LB_PneuWet);
		
		JLabel LB_PneuMedio = new JLabel("");
		LB_PneuMedio.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuMedioF140px.png")));
		LB_PneuMedio.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuMedio.setBounds(580, 350, 60, 50);
		contentPane.add(LB_PneuMedio);
		
		JLabel LB_PneuMacio = new JLabel("");
		LB_PneuMacio.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuMacioF140px.png")));
		LB_PneuMacio.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuMacio.setBounds(640, 350, 60, 50);
		contentPane.add(LB_PneuMacio);
		
		JLabel LB_PneuDuro = new JLabel("");
		LB_PneuDuro.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuDuroF140px.png")));
		LB_PneuDuro.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuDuro.setBounds(520, 350, 60, 50);
		contentPane.add(LB_PneuDuro);
		
		lblNewLabel_3 = new JLabel("WET");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(400, 400, 60, 20);
		contentPane.add(lblNewLabel_3);
		
		lblNewLabel = new JLabel("INTER");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(460, 400, 60, 20);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_9 = new JLabel("HARD");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setBounds(520, 400, 60, 20);
		contentPane.add(lblNewLabel_9);
		
		lblNewLabel_10 = new JLabel("MEDIUM");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(580, 400, 60, 20);
		contentPane.add(lblNewLabel_10);
		
		lblNewLabel_11 = new JLabel("SOFT");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setBounds(640, 400, 60, 20);
		contentPane.add(lblNewLabel_11);
		
		RB_PneuWet = new JRadioButton("");
		RB_PneuWet.setBackground(new Color(255, 255, 255));
		RB_PneuWet.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuWet.setBounds(400, 420, 60, 20);
		contentPane.add(RB_PneuWet);
		
		RB_PneuInter = new JRadioButton("");
		RB_PneuInter.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuInter.setBackground(Color.WHITE);
		RB_PneuInter.setBounds(460, 420, 60, 20);
		contentPane.add(RB_PneuInter);
		
		RB_PneuDuro = new JRadioButton("");
		RB_PneuDuro.setSelected(true);
		RB_PneuDuro.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuDuro.setBackground(Color.WHITE);
		RB_PneuDuro.setBounds(520, 420, 60, 20);
		contentPane.add(RB_PneuDuro);
		
		RB_PneuMedio = new JRadioButton("");
		RB_PneuMedio.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMedio.setBackground(Color.WHITE);
		RB_PneuMedio.setBounds(580, 420, 60, 20);
		contentPane.add(RB_PneuMedio);
		
		RB_PneuMacio = new JRadioButton("");
		RB_PneuMacio.setHorizontalAlignment(SwingConstants.CENTER);
		RB_PneuMacio.setBackground(Color.WHITE);
		RB_PneuMacio.setBounds(640, 420, 60, 20);
		contentPane.add(RB_PneuMacio);
		
		ButtonGroup botaoPneu = new ButtonGroup();
		botaoPneu.add(RB_PneuWet);
		botaoPneu.add(RB_PneuInter);
		botaoPneu.add(RB_PneuDuro);
		botaoPneu.add(RB_PneuMedio);
		botaoPneu.add(RB_PneuMacio);
		
	
	
	*/
	
	/*
	
	public void TESTE_TELA() {
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Dados dados = new Dados();	
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();
				
				LB_IMG_Pista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.LayoutPistaPQ)));
				LB_BandeiraPista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.PaisPista)));
				LB_ComprimentoPista.setText(""+Dados.ComprimentoPista);
				LB_CurvaPista.setText(""+Dados.Curvas_Pista);
				LB_LocalPista.setText(""+Dados.LocalPista);
				LB_TipoPista.setText(""+Dados.TipoPista);
				LB_QualyEtapa.setText("QUALY DA ETAPA "+PaginaInicial.EtapaAtual+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
				RB_Normal_Tempo.setSelected(true);
				
				dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				LB_PneuAtual.setText(dados.PneuAtual);
				LB_IMGPneuAtual.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMGPneuAtual)));
				
			}
		});
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 521);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		LB_IMG_Pista = new JLabel("");
		LB_IMG_Pista.setBounds(10, 45, 250, 200);
		LB_IMG_Pista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Pista.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(LB_IMG_Pista);
		
		BTVoltar = new JButton("Próxima Etapa");
		BTVoltar.setBounds(10, 290, 115, 23);
		BTVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SalvaDado data = new SalvaDado();
				Inicial_Carregar InicialCarregar = new Inicial_Carregar();
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();
				Selecionar_Categoria Categoria = new Selecionar_Categoria();
				
				PaginaInicial.EtapaAtual = PaginaInicial.EtapaAtual +1;
				PaginaInicial.Etapa = PaginaInicial.EtapaAtual;
				PaginaInicial.LB_Temporada.setText("Temporada "+PaginaInicial.TEMPORADAJOGADOR+", Etapa "+PaginaInicial.EtapaAtual+"/"+PaginaInicial.EtapaTotais);
				
				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					PaginaInicial.CriaCalendarioF1();
					PaginaInicial.LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					PaginaInicial.CriaCalendarioFIndy();
					PaginaInicial.LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					PaginaInicial.CriaCalendarioNascar();
					PaginaInicial.LB_PistaEtapas.setText("CALENDÁRIO, ETAPA "+PaginaInicial.Etapa+"/"+PaginaInicial.EtapaTotais+""+PaginaInicial.Playoffs);
				}
				
				if (PaginaInicial.Etapa > 1) {
					PaginaInicial.BT_EtapaAnt.setEnabled(true);
				}
				if (PaginaInicial.Etapa == PaginaInicial.EtapaTotais) {
					PaginaInicial.BT_EtapaProxima.setEnabled(false);
				}
				
				if (PaginaInicial.Etapa == 1) {
					PaginaInicial.BT_EtapaAnt.setEnabled(false);
				}
				if (PaginaInicial.Etapa < PaginaInicial.EtapaTotais) {
					PaginaInicial.BT_EtapaProxima.setEnabled(true);
				}
				
				PaginaInicial.setVisible(true);
				PaginaInicial.setLocationRelativeTo(null);
				
				dispose();					
				
			}
		});
		contentPane.add(BTVoltar);
		
		LB_QualyEtapa = new JLabel("QUALY DA ETAPA ");
		LB_QualyEtapa.setFont(new Font("Arial", Font.PLAIN, 20));
		LB_QualyEtapa.setHorizontalAlignment(SwingConstants.CENTER);
		LB_QualyEtapa.setBounds(10, 11, 864, 23);
		contentPane.add(LB_QualyEtapa);
		
		ProgBar_TempoQualy = new JProgressBar();
		ProgBar_TempoQualy.setMaximum(60);
		ProgBar_TempoQualy.setToolTipText("");
		ProgBar_TempoQualy.setForeground(new Color(50, 200, 50));
		ProgBar_TempoQualy.setBounds(10, 256, 864, 23);
		contentPane.add(ProgBar_TempoQualy);
		
		LB_LocalPista = new JLabel("1");
		LB_LocalPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_LocalPista.setForeground(Color.BLACK);
		LB_LocalPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_LocalPista.setBounds(325, 45, 273, 25);
		contentPane.add(LB_LocalPista);
		
		LB_BandeiraPista = new JLabel("");
		LB_BandeiraPista.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista.setBounds(270, 45, 45, 25);
		contentPane.add(LB_BandeiraPista);
		
		LB_TipoPista = new JLabel("1");
		LB_TipoPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_TipoPista.setForeground(Color.BLACK);
		LB_TipoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TipoPista.setBounds(270, 71, 328, 25);
		contentPane.add(LB_TipoPista);
		
		LB_CurvaPista = new JLabel("1");
		LB_CurvaPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_CurvaPista.setForeground(Color.BLACK);
		LB_CurvaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_CurvaPista.setBounds(270, 95, 328, 25);
		contentPane.add(LB_CurvaPista);
		
		LB_ComprimentoPista = new JLabel("1");
		LB_ComprimentoPista.setHorizontalAlignment(SwingConstants.LEFT);
		LB_ComprimentoPista.setForeground(Color.BLACK);
		LB_ComprimentoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_ComprimentoPista.setBounds(270, 120, 328, 25);
		contentPane.add(LB_ComprimentoPista);
		
		LB_Crono = new JLabel("TEMPO RESTANTE DA SESSÃO DE QUALY: 60 Minuto(s)");
		LB_Crono.setFont(new Font("Arial", Font.PLAIN, 20));
		LB_Crono.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Crono.setBounds(270, 211, 604, 34);
		contentPane.add(LB_Crono);
		
		JRadioButton RB_Metade_Tempo = new JRadioButton("0.5x");
		RB_Metade_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Metade_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Metade_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 2000;
				
			}
		});
		RB_Metade_Tempo.setBackground(new Color(255, 255, 255));
		RB_Metade_Tempo.setBounds(333, 177, 66, 23);
		contentPane.add(RB_Metade_Tempo);
		
		RB_Normal_Tempo = new JRadioButton("1x");
		RB_Normal_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Normal_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 1000;
			}
		});
		RB_Normal_Tempo.setSelected(true);
		RB_Normal_Tempo.setBackground(new Color(255, 255, 255));
		RB_Normal_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Normal_Tempo.setBounds(399, 177, 66, 23);
		contentPane.add(RB_Normal_Tempo);
		
		JRadioButton RB_Dobro_Tempo = new JRadioButton("2x");
		RB_Dobro_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Dobro_Tempo.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 500;
				
			}
		});
		RB_Dobro_Tempo.setBackground(new Color(255, 255, 255));
		RB_Dobro_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Dobro_Tempo.setBounds(465, 177, 66, 23);
		contentPane.add(RB_Dobro_Tempo);
		
		RB_DobroDobro_Tempo = new JRadioButton("4x");
		RB_DobroDobro_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_DobroDobro_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 250;
				
			}
		});
		RB_DobroDobro_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_DobroDobro_Tempo.setBackground(Color.WHITE);
		RB_DobroDobro_Tempo.setBounds(531, 177, 66, 23);
		contentPane.add(RB_DobroDobro_Tempo);
		
		RB_MetadeMetade_Tempo = new JRadioButton("0.25x");
		RB_MetadeMetade_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 4000;
				
			}
		});
		RB_MetadeMetade_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_MetadeMetade_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_MetadeMetade_Tempo.setBackground(Color.WHITE);
		RB_MetadeMetade_Tempo.setBounds(267, 177, 66, 23);
		contentPane.add(RB_MetadeMetade_Tempo);
		
		JLabel lblNewLabel = new JLabel("Velocidade do tempo");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(266, 150, 332, 20);
		contentPane.add(lblNewLabel);
		
		lblClimaAtual = new JLabel("CLIMA ATUAL");
		lblClimaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblClimaAtual.setFont(new Font("Arial", Font.ITALIC, 20));
		lblClimaAtual.setBounds(608, 45, 250, 50);
		contentPane.add(lblClimaAtual);
		
		LB_Clima = new JLabel("CLIMA");
		LB_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		LB_Clima.setBounds(608, 95, 183, 50);
		contentPane.add(LB_Clima);
		
		LB_IMG_Clima = new JLabel("");
		LB_IMG_Clima.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/Icone64pxErro.png")));
		LB_IMG_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		LB_IMG_Clima.setBounds(788, 93, 70, 50);
		contentPane.add(LB_IMG_Clima);
		
		btnNewButton = new JButton("TESTE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Dados dados = new Dados();
				
				dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				LB_PneuAtual.setText(dados.PneuAtual);
				LB_IMGPneuAtual.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMGPneuAtual)));
				
			}
		});
		btnNewButton.setBounds(135, 290, 89, 23);
		contentPane.add(btnNewButton);
		
		LB_IMGPneuAtual = new JLabel("");
		LB_IMGPneuAtual.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuMacioF140px.png")));
		LB_IMGPneuAtual.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMGPneuAtual.setBounds(653, 150, 60, 50);
		contentPane.add(LB_IMGPneuAtual);
		
		BT_PlayPause = new JButton("  COMEÇAR QUALY");
		BT_PlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(auxPause == 0) {
					
					BT_PlayPause.setText("  PAUSAR QUALY");
					BT_PlayPause.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePausa16px.png")));
					auxPause = 1;
					
					TIMER = 1000;
					new Temporizador().start();
					
				}else{
					BT_PlayPause.setText("  RECOMEÇAR QUALY");
					BT_PlayPause.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePlay16px.png")));
					auxPause = 0;
					
					TIMER = 100000000;
					
					RB_Normal_Tempo.setSelected(true);
				}
				
			}
		});
		BT_PlayPause.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePlay16px.png")));
		BT_PlayPause.setBounds(660, 290, 214, 23);
		contentPane.add(BT_PlayPause);
		
		ButtonGroup botao = new ButtonGroup();
		botao.add(RB_Dobro_Tempo);
		botao.add(RB_Normal_Tempo);
		botao.add(RB_Metade_Tempo);
		botao.add(RB_DobroDobro_Tempo);
		
		LB_PneuAtual = new JLabel("SOFT");
		LB_PneuAtual.setBackground(new Color(255, 255, 255));
		LB_PneuAtual.setForeground(new Color(255, 128, 0));
		LB_PneuAtual.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		LB_PneuAtual.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuAtual.setBounds(723, 174, 135, 26);
		contentPane.add(LB_PneuAtual);
		
		lblNewLabel_3 = new JLabel("Pneu Atual");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(723, 150, 135, 23);
		contentPane.add(lblNewLabel_3);
		
	}
	
	*/
	
	public class Temporizador extends Thread{
		public void run() {
			
			while (aux <= ProgBar_TempoQualy.getMaximum()) {
				
				try {
					
					ProgBar_TempoQualy.setValue(aux);
					
					aux++;
					
					LB_Crono.setText("TEMPO RESTANTE DA SESSÃO DE QUALY: "+min+" Minuto(s)");
					
					min--;
							
				sleep(TIMER);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			ImageIcon icon = new ImageIcon(Selecionar_Equipe.class.getResource("/Imagens/Icone64pxConfirmacao.png"));
			
			JOptionPane.showMessageDialog(null, "FINAL DO TREINO DE CLASSIFICAÇÃO!","FINAL DO QUALY", 
			JOptionPane.ERROR_MESSAGE, icon);
			
		}
	}
}




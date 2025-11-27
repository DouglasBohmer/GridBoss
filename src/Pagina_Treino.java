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

public class Pagina_Treino extends JFrame {
	private JButton BTVoltar;
	private JLabel LB_Crono;
	private JLabel LB_IMG_Pista;
	private JLabel LB_LocalPista;
	public static JLabel LB_TipoPista;
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
	
	private int auxPause=0, aux=0, TIMER=1000, sec=60, min=60, a=0;
	private JLabel lblNewLabel_3;
	private JLabel LB_PneuAtual;
	private JLabel LB_IMGPneuAtual;
	private JButton BT_PlayPause;
	private JRadioButton RB_MetadeMetade_Tempo;
	private JLabel LB_TabelaTempos;
	private JLabel LB_BandeiraPista1;
	private JRadioButton RB_Dobro_Tempo;
	private JRadioButton RB_Metade_Tempo;
	private JButton btnPular;
		
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pagina_Treino frame = new Pagina_Treino();
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
	public Pagina_Treino() {

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Dados dados = new Dados();	
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();
				
				LB_IMG_Pista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.LayoutPistaPQ)));
				LB_BandeiraPista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.PaisPista)));
				LB_BandeiraPista1.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.PaisPista)));
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
		setBounds(100, 100, 900, 600);
		setResizable(false);
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		LB_IMG_Pista = new JLabel("");
		LB_IMG_Pista.setBounds(10, 79, 250, 200);
		LB_IMG_Pista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Pista.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(LB_IMG_Pista);
		
		BTVoltar = new JButton("Próxima Etapa");
		BTVoltar.setBounds(737, 535, 137, 23);
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
		ProgBar_TempoQualy.setBounds(270, 147, 604, 23);
		contentPane.add(ProgBar_TempoQualy);
		
		LB_LocalPista = new JLabel("1");
		LB_LocalPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_LocalPista.setForeground(Color.BLACK);
		LB_LocalPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_LocalPista.setBounds(50, 45, 170, 25);
		contentPane.add(LB_LocalPista);
		
		LB_BandeiraPista = new JLabel("");
		LB_BandeiraPista.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista.setBounds(10, 45, 30, 25);
		contentPane.add(LB_BandeiraPista);
		
		LB_TipoPista = new JLabel("1");
		LB_TipoPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TipoPista.setForeground(Color.BLACK);
		LB_TipoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_TipoPista.setBounds(10, 282, 250, 25);
		contentPane.add(LB_TipoPista);
		
		LB_CurvaPista = new JLabel("1");
		LB_CurvaPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_CurvaPista.setForeground(Color.BLACK);
		LB_CurvaPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_CurvaPista.setBounds(10, 306, 250, 25);
		contentPane.add(LB_CurvaPista);
		
		LB_ComprimentoPista = new JLabel("1");
		LB_ComprimentoPista.setHorizontalAlignment(SwingConstants.CENTER);
		LB_ComprimentoPista.setForeground(Color.BLACK);
		LB_ComprimentoPista.setFont(new Font("Arial Rounded MT Bold", Font.ITALIC, 12));
		LB_ComprimentoPista.setBounds(10, 331, 250, 25);
		contentPane.add(LB_ComprimentoPista);
		
		LB_Crono = new JLabel("TEMPO RESTANTE DA SESSÃO DE QUALY: 60 Minuto(s)");
		LB_Crono.setFont(new Font("Arial", Font.PLAIN, 20));
		LB_Crono.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Crono.setBounds(270, 102, 604, 34);
		contentPane.add(LB_Crono);
		
		RB_Metade_Tempo = new JRadioButton("0.5x");
		RB_Metade_Tempo.setEnabled(false);
		RB_Metade_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Metade_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Metade_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 2000;
				
			}
		});
		RB_Metade_Tempo.setBackground(new Color(255, 255, 255));
		RB_Metade_Tempo.setBounds(337, 72, 66, 23);
		contentPane.add(RB_Metade_Tempo);
		
		RB_Normal_Tempo = new JRadioButton("1x");
		RB_Normal_Tempo.setEnabled(false);
		RB_Normal_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Normal_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 1000;
			}
		});
		RB_Normal_Tempo.setSelected(true);
		RB_Normal_Tempo.setBackground(new Color(255, 255, 255));
		RB_Normal_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Normal_Tempo.setBounds(403, 72, 66, 23);
		contentPane.add(RB_Normal_Tempo);
		
		RB_Dobro_Tempo = new JRadioButton("2x");
		RB_Dobro_Tempo.setEnabled(false);
		RB_Dobro_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_Dobro_Tempo.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 500;
				
			}
		});
		RB_Dobro_Tempo.setBackground(new Color(255, 255, 255));
		RB_Dobro_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_Dobro_Tempo.setBounds(469, 72, 66, 23);
		contentPane.add(RB_Dobro_Tempo);
		
		RB_DobroDobro_Tempo = new JRadioButton("4x");
		RB_DobroDobro_Tempo.setEnabled(false);
		RB_DobroDobro_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_DobroDobro_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				TIMER = 250;
				
			}
		});
		RB_DobroDobro_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_DobroDobro_Tempo.setBackground(Color.WHITE);
		RB_DobroDobro_Tempo.setBounds(535, 72, 66, 23);
		contentPane.add(RB_DobroDobro_Tempo);
		
		RB_MetadeMetade_Tempo = new JRadioButton("0.25x");
		RB_MetadeMetade_Tempo.setEnabled(false);
		RB_MetadeMetade_Tempo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 4000;
				
			}
		});
		RB_MetadeMetade_Tempo.setHorizontalAlignment(SwingConstants.CENTER);
		RB_MetadeMetade_Tempo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		RB_MetadeMetade_Tempo.setBackground(Color.WHITE);
		RB_MetadeMetade_Tempo.setBounds(271, 72, 66, 23);
		contentPane.add(RB_MetadeMetade_Tempo);
		
		JLabel lblNewLabel = new JLabel("Velocidade do tempo");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(270, 45, 332, 20);
		contentPane.add(lblNewLabel);
		
		lblClimaAtual = new JLabel("CLIMA ATUAL");
		lblClimaAtual.setHorizontalAlignment(SwingConstants.CENTER);
		lblClimaAtual.setFont(new Font("Arial", Font.ITALIC, 20));
		lblClimaAtual.setBounds(10, 369, 250, 50);
		contentPane.add(lblClimaAtual);
		
		LB_Clima = new JLabel("CLIMA");
		LB_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		LB_Clima.setBounds(10, 419, 183, 50);
		contentPane.add(LB_Clima);
		
		LB_IMG_Clima = new JLabel("");
		LB_IMG_Clima.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/Icone64pxErro.png")));
		LB_IMG_Clima.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMG_Clima.setFont(new Font("Arial", Font.ITALIC, 20));
		LB_IMG_Clima.setBounds(190, 417, 70, 50);
		contentPane.add(LB_IMG_Clima);
		
		btnNewButton = new JButton("TESTE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				/*
				Dados dados = new Dados();
				
				dados.GeraClima();
				
				LB_Clima.setText(dados.Clima);
				LB_IMG_Clima.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMG_Clima)));
				
				LB_PneuAtual.setText(dados.PneuAtual);
				LB_IMGPneuAtual.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.IMGPneuAtual)));
				*/
				
				TIMER = 0;
				
			}
		});
		btnNewButton.setBounds(612, 535, 115, 23);
		contentPane.add(btnNewButton);
		
		LB_IMGPneuAtual = new JLabel("");
		LB_IMGPneuAtual.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/PneuMacioF140px.png")));
		LB_IMGPneuAtual.setHorizontalAlignment(SwingConstants.CENTER);
		LB_IMGPneuAtual.setBounds(55, 474, 60, 50);
		contentPane.add(LB_IMGPneuAtual);
		
		BT_PlayPause = new JButton("  COMEÇAR QUALY");
		BT_PlayPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Selecionar_Categoria Categoria = new Selecionar_Categoria();
				
				if(auxPause == 15) {
					
					btnPular.setEnabled(false);
					
					if(Categoria.BT_Cate.equals("Fórmula 1")) {
						
						Pagina_EscolhePneuF1 PaginaEscolhePneuF1 = new Pagina_EscolhePneuF1();
						PaginaEscolhePneuF1.setVisible(true);
						PaginaEscolhePneuF1.setLocationRelativeTo(null);
						
						dispose();
						
					} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
						
						Pagina_Inicial PaginaInicial = new Pagina_Inicial();
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
						
					} else if(Categoria.BT_Cate.equals("NASCAR")) {
						
						Pagina_Inicial PaginaInicial = new Pagina_Inicial();
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
					
				}
				
				if(auxPause == 0) {
					
					btnPular.setEnabled(true);
					
					BT_PlayPause.setText("  PAUSAR QUALY");
					BT_PlayPause.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePausa16px.png")));
					auxPause = 1;
					
					TIMER = 1000;
					new Temporizador().start();
					
					RB_Dobro_Tempo.setEnabled(true);
					RB_Normal_Tempo.setEnabled(true);
					RB_Metade_Tempo.setEnabled(true);
					RB_DobroDobro_Tempo.setEnabled(true);
					RB_MetadeMetade_Tempo.setEnabled(true);
					
				}else{
					
					btnPular.setEnabled(true);
					
					BT_PlayPause.setText("  RECOMEÇAR QUALY");
					BT_PlayPause.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePlay16px.png")));
					auxPause = 0;
					
					TIMER = 100000000;
					
					RB_Normal_Tempo.setSelected(true);
				}
				
			}
		});
		BT_PlayPause.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePlay16px.png")));
		BT_PlayPause.setBounds(612, 45, 262, 23);
		contentPane.add(BT_PlayPause);
		
		ButtonGroup botao = new ButtonGroup();
		botao.add(RB_Dobro_Tempo);
		botao.add(RB_Normal_Tempo);
		botao.add(RB_Metade_Tempo);
		botao.add(RB_DobroDobro_Tempo);
		botao.add(RB_MetadeMetade_Tempo);
		
		LB_PneuAtual = new JLabel("SOFT");
		LB_PneuAtual.setBackground(new Color(255, 255, 255));
		LB_PneuAtual.setForeground(new Color(255, 128, 0));
		LB_PneuAtual.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		LB_PneuAtual.setHorizontalAlignment(SwingConstants.CENTER);
		LB_PneuAtual.setBounds(125, 498, 135, 26);
		contentPane.add(LB_PneuAtual);
		
		lblNewLabel_3 = new JLabel("Pneu Atual");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(125, 474, 135, 23);
		contentPane.add(lblNewLabel_3);
		
		LB_TabelaTempos = new JLabel("");
		LB_TabelaTempos.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/TabelaQualy.png")));
		LB_TabelaTempos.setHorizontalAlignment(SwingConstants.CENTER);
		LB_TabelaTempos.setBounds(270, 181, 604, 343);
		LB_TabelaTempos.setBorder(BorderFactory.createLineBorder(Color.gray));
		contentPane.add(LB_TabelaTempos);
		
		LB_BandeiraPista1 = new JLabel("");
		LB_BandeiraPista1.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/Icone16pxErro.png")));
		LB_BandeiraPista1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_BandeiraPista1.setBounds(230, 45, 30, 25);
		contentPane.add(LB_BandeiraPista1);
		
		btnPular = new JButton("  PULAR QUALY");
		btnPular.setEnabled(false);
		btnPular.setIcon(new ImageIcon(Pagina_Treino.class.getResource("/Imagens/IconePular16px.png")));
		btnPular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				TIMER = 0;
				
			}
		});
		btnPular.setBounds(612, 73, 262, 23);
		contentPane.add(btnPular);
		
	}
	
	public class Temporizador extends Thread{
		public void run() {
			
			while (aux <= ProgBar_TempoQualy.getMaximum()) {
				
				try {
					
					if (min < 11) {
						ProgBar_TempoQualy.setForeground(new Color(255, 0, 0));
					}
					
					ProgBar_TempoQualy.setValue(aux);
					
					aux++;
					
					LB_Crono.setText("TEMPO RESTANTE DA SESSÃO DE QUALY: "+min+" Minuto(s)");
					
					min--;
							
					sleep(TIMER);
					
					a=1;
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			ImageIcon icon = new ImageIcon(Selecionar_Equipe.class.getResource("/Imagens/Icone64pxConfirmacao.png"));
			
			//precisa parar se sair da página!!!!!!!
			
			JOptionPane.showMessageDialog(null, "FINAL DO TREINO DE CLASSIFICAÇÃO!","FINAL DO QUALY", 
			JOptionPane.ERROR_MESSAGE, icon);
			
			BT_PlayPause.setText("IR PARA CORRIDA!");
			BT_PlayPause.setIcon(new ImageIcon(Pagina_CorridaF1.class.getResource("/Imagens/IconeCorrida16px.png")));
			
			btnPular.setEnabled(false);
			RB_Dobro_Tempo.setEnabled(false);
			RB_Normal_Tempo.setEnabled(false);
			RB_Metade_Tempo.setEnabled(false);
			RB_DobroDobro_Tempo.setEnabled(false);
			RB_MetadeMetade_Tempo.setEnabled(false);
			
			auxPause = 15;
			
			
			//
		}
	}
}

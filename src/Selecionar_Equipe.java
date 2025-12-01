import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Selecionar_Equipe extends JFrame {

	private JPanel contentPane;
	private JLabel LB_Cat;
	private JComboBox CB_Lista_Temp;
	private JLabel LB_Foto_Carro;
	private JLabel LB_Piloto1;
	private JLabel LB_Piloto3;
	private JLabel LB_Piloto2;
	private JLabel LB_Piloto4;
	private JLabel LB_Piloto1_1;
	private JLabel LB_Piloto2_2;
	private JLabel LB_Piloto3_3;
	private JLabel LB_Piloto4_4;
	private JLabel LB_Piloto5;
	private JLabel LB_Piloto5_5;
	private JLabel LB_Sede_Equipe;
	private JLabel LB_Motor_Equipe;
	private JLabel LB_Orçamento_Equipe;
	private JLabel LB_Sede;
	private JLabel LB_Orçamento;
	private JLabel LB_Motor;
	private JLabel LB_FlagPiloto1;
	private JLabel LB_FlagPiloto2;
	private JLabel LB_FlagPiloto3;
	private JLabel LB_FlagPiloto4;
	private JLabel LB_FlagPiloto5;
	private JLabel LB_FlagSede;
	private JLabel LB_FlagMotor;
	
	
	private JLabel LB_Born1;
	
	
	private JLabel LB_Born2;
	private JLabel LB_Born3;
	private JLabel LB_Born4;
	private JLabel LB_Born5;
	private JLabel LB_Fundacao;
	private JLabel LB_Logo_Motor;
	private JLabel LB_NumPiloto1;
	private JLabel LB_NumPiloto2;
	private JLabel LB_NumPiloto3;
	private JLabel LB_NumPiloto4;
	private JLabel LB_NumPiloto5;
	private JLabel lblNewLabel;
	
	public static String EquipeEscolhida, NomeEquipeEscolhida, SedeEquipeEscolhida, BandeiraSedeEquipeEscolhida, BandeiraEquipeEscolhida;
	public static String LogoEquipeEscolhida, NomeMotorEscolhido, BandeiraMotorEscolhido, LogoMotorEscolhido, LogoMotorPQEscolhido;
	public static String NomePiloto1, NomePiloto2, NomePiloto3, NomePiloto4, NomePiloto5, NomeDirigente;
	public static String BandeiraPiloto1, BandeiraPiloto2, BandeiraPiloto3, BandeiraPiloto4, BandeiraPiloto5;
	public static int ano, FundacaoEquipeEscolhida, IdadePiloto1, IdadePiloto2, IdadePiloto3, IdadePiloto4, IdadePiloto5;
	public static int NascimentoPiloto1, NascimentoPiloto2, NascimentoPiloto3, NascimentoPiloto4, NascimentoPiloto5;
	public static String NumeroPiloto1, NumeroPiloto2, NumeroPiloto3, NumeroPiloto4, NumeroPiloto5;
	public static String PaisPiloto1, PaisPiloto2, PaisPiloto3, PaisPiloto4, PaisPiloto5;
	private JLabel LB_Idade1;
	private JLabel LB_Idade2;
	private JLabel LB_Idade3;
	private JLabel LB_Idade4;
	private JLabel LB_Idade5;
	private JTextField TF_NomeDirigente;
	private JLabel LB_FlagMotor_1;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selecionar_Equipe frame = new Selecionar_Equipe();
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
	public Selecionar_Equipe() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/resource/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 586, 740);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();

		LB_Cat = new JLabel("SEM CATEGORIA");
		LB_Cat.setFont(new Font("Berlin Sans FB Demi", Font.ITALIC, 15));
		LB_Cat.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Cat.setBounds(20, 208, 540, 26);
		LB_Cat.setText("Nome Categoria");
		contentPane.add(LB_Cat);
		
		JLabel LB_Logo = new JLabel("");
		LB_Logo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		LB_Logo.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("")));
		LB_Logo.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Logo.setBounds(20, 48, 540, 149);
		contentPane.add(LB_Logo);
		

		CB_Lista_Temp = new JComboBox();
		CB_Lista_Temp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					setInfoEquipesF1();
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					setInfoEquipesIndy();
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					setInfoEquipesNascar();
					
				}
			}
		});
		
		CB_Lista_Temp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				setInfoEquipesF1();
				
			}
		});
		CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"-- SELECIONE A EQUIPE --"}));
		CB_Lista_Temp.setMaximumRowCount(15);
		CB_Lista_Temp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 13));
		CB_Lista_Temp.setBounds(20, 238, 540, 21);
		contentPane.add(CB_Lista_Temp);
		
		setLocationRelativeTo(null);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
			
				Pagina_Inicial PaginaInicial = new Pagina_Inicial();
				
				PaginaInicial.Etapa = 1;
				
			try {
				
				LB_Cat.setBounds(20, 208, 540, 36);
				LB_Cat.setText(""+Categoria.BT_Cate+" - "+Categoria.BT_Temp);
				LB_Logo.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Categoria.BT_Imagem)));
				
				if(Categoria.BT_Cate.equals("Fórmula 1")) {
					
					setEquipesF1();
					setInfoEquipesF1();
					
				} else if(Categoria.BT_Cate.equals("Fórmula INDY")) {
					
					setEquipesIndy();
					setInfoEquipesIndy();
					
				} else if(Categoria.BT_Cate.equals("NASCAR")) {
					
					setEquipesF1();
					setInfoEquipesF1();
					
					setEquipesNascar();
					setInfoEquipesNascar();
					
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();				
			}			
			}
		});
		
		JButton BT_Voltar = new JButton("VOLTAR");
		BT_Voltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Categoria.setVisible(true);
				Categoria.setLocationRelativeTo(null);
							
				dispose();
			}
		});
		BT_Voltar.setBounds(10, 674, 172, 21);
		contentPane.add(BT_Voltar);
		
		JButton BT_Comecar = new JButton("COMEÇAR JOGO");
		BT_Comecar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (TF_NomeDirigente.getText().equals("")) {
					
					ImageIcon icon = new ImageIcon(Selecionar_Equipe.class.getResource("/resource/Icone64pxErro.png"));
				
					JOptionPane.showMessageDialog(null, "Nome do dirigente está vázio! \n\nDigite um nome para continuar.\n"
							+ ""+TF_NomeDirigente.getText(),"Falta de informação", 
							JOptionPane.INFORMATION_MESSAGE, icon);
					
					TF_NomeDirigente.grabFocus();
					TF_NomeDirigente.setBorder(new LineBorder(Color.red));
					lblNewLabel.setForeground(new Color(255, 0, 0));
					
				}else {
					
					Selecionar_Categoria SelecionarCategoria = new Selecionar_Categoria();
					Pagina_Inicial PaginaInicial = new Pagina_Inicial();
					PaginaInicial.setVisible(true);
					PaginaInicial.setLocationRelativeTo(null);
					
					setInfoEquipeEscolhida();
					NomeDirigente = TF_NomeDirigente.getText();
					PaginaInicial.LB_NomeDirigente.setText(""+NomeDirigente);
					dispose();
				}
			}
		});
		BT_Comecar.setBounds(388, 674, 172, 21);
		contentPane.add(BT_Comecar);
				
		JLabel lblNewLabel1 = new JLabel("MOTORSPORT MANAGER");
		lblNewLabel1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setBounds(20, 11, 540, 26);
		contentPane.add(lblNewLabel1);
		
		LB_Foto_Carro = new JLabel("");
		LB_Foto_Carro.setFont(new Font("Berlin Sans FB", Font.PLAIN, 11));
		LB_Foto_Carro.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Foto_Carro.setBounds(20, 282, 540, 100);
		contentPane.add(LB_Foto_Carro);
		
		LB_Piloto1 = new JLabel("");
		LB_Piloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto1.setBounds(20, 393, 91, 21);
		contentPane.add(LB_Piloto1);
		
		LB_Piloto2 = new JLabel("");
		LB_Piloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto2.setBounds(20, 425, 91, 21);
		contentPane.add(LB_Piloto2);
		
		LB_Piloto3 = new JLabel("");
		LB_Piloto3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto3.setBounds(20, 457, 91, 21);
		contentPane.add(LB_Piloto3);
		
		LB_Piloto4 = new JLabel("");
		LB_Piloto4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto4.setBounds(20, 489, 91, 21);
		contentPane.add(LB_Piloto4);
		
		LB_Piloto1_1 = new JLabel("");
		LB_Piloto1_1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto1_1.setBounds(166, 393, 249, 21);
		contentPane.add(LB_Piloto1_1);
				
		LB_Piloto2_2 = new JLabel("");
		LB_Piloto2_2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto2_2.setBounds(166, 425, 249, 21);
		contentPane.add(LB_Piloto2_2);
		
		LB_Piloto3_3 = new JLabel("");
		LB_Piloto3_3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto3_3.setBounds(166, 457, 249, 21);
		contentPane.add(LB_Piloto3_3);
		
		LB_Piloto4_4 = new JLabel("");
		LB_Piloto4_4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto4_4.setBounds(166, 489, 249, 21);
		contentPane.add(LB_Piloto4_4);
		
		LB_Piloto5 = new JLabel("");
		LB_Piloto5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto5.setBounds(20, 521, 91, 21);
		contentPane.add(LB_Piloto5);
		
		LB_Piloto5_5 = new JLabel("");
		LB_Piloto5_5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Piloto5_5.setBounds(166, 521, 249, 21);
		contentPane.add(LB_Piloto5_5);
		
		LB_Sede_Equipe = new JLabel("Sede/Fundação");
		LB_Sede_Equipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Sede_Equipe.setBounds(20, 553, 91, 21);
		contentPane.add(LB_Sede_Equipe);
		
		LB_Motor_Equipe = new JLabel("Motor");
		LB_Motor_Equipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Motor_Equipe.setBounds(20, 585, 91, 21);
		contentPane.add(LB_Motor_Equipe);
		
		LB_Orçamento_Equipe = new JLabel("Orçamento");
		LB_Orçamento_Equipe.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Orçamento_Equipe.setBounds(20, 617, 91, 21);
		contentPane.add(LB_Orçamento_Equipe);
		
		LB_Sede = new JLabel("Sede da Equipe");
		LB_Sede.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Sede.setBounds(121, 553, 318, 21);
		contentPane.add(LB_Sede);
		
		LB_Motor = new JLabel("Motor Usado");
		LB_Motor.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Motor.setBounds(121, 585, 318, 21);
		contentPane.add(LB_Motor);
		
		LB_Orçamento = new JLabel("€ milhões de Euros ");
		LB_Orçamento.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Orçamento.setBounds(121, 617, 318, 21);
		contentPane.add(LB_Orçamento);
		
		LB_FlagPiloto1 = new JLabel("");
		LB_FlagPiloto1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagPiloto1.setBounds(528, 393, 32, 21);
		contentPane.add(LB_FlagPiloto1);
		
		LB_FlagPiloto2 = new JLabel("");
		LB_FlagPiloto2.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagPiloto2.setBounds(528, 425, 32, 21);
		contentPane.add(LB_FlagPiloto2);
		
		LB_FlagPiloto3 = new JLabel("");
		LB_FlagPiloto3.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagPiloto3.setBounds(528, 457, 32, 21);
		contentPane.add(LB_FlagPiloto3);
		
		LB_FlagPiloto4 = new JLabel("");
		LB_FlagPiloto4.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagPiloto4.setBounds(528, 489, 32, 21);
		contentPane.add(LB_FlagPiloto4);
		
		LB_FlagPiloto5 = new JLabel("");
		LB_FlagPiloto5.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagPiloto5.setBounds(528, 521, 32, 21);
		contentPane.add(LB_FlagPiloto5);
		
		LB_FlagSede = new JLabel("");
		LB_FlagSede.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagSede.setBounds(528, 553, 32, 21);
		contentPane.add(LB_FlagSede);
		
		LB_FlagMotor = new JLabel("");
		LB_FlagMotor.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagMotor.setBounds(528, 585, 32, 21);
		contentPane.add(LB_FlagMotor);
		
		LB_Born1 = new JLabel("");
		LB_Born1.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_Born1.setBounds(425, 393, 52, 21);
		contentPane.add(LB_Born1);
		
		LB_Born2 = new JLabel("");
		LB_Born2.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_Born2.setBounds(425, 425, 52, 21);
		contentPane.add(LB_Born2);
		
		LB_Born3 = new JLabel("");
		LB_Born3.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_Born3.setBounds(425, 457, 52, 21);
		contentPane.add(LB_Born3);
		
		LB_Born4 = new JLabel("");
		LB_Born4.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_Born4.setBounds(425, 489, 52, 21);
		contentPane.add(LB_Born4);
		
		LB_Born5 = new JLabel("");
		LB_Born5.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_Born5.setBounds(425, 521, 52, 21);
		contentPane.add(LB_Born5);
		
		LB_Fundacao = new JLabel("");
		LB_Fundacao.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Fundacao.setBounds(425, 553, 93, 21);
		contentPane.add(LB_Fundacao);
		
		LB_Logo_Motor = new JLabel("");
		LB_Logo_Motor.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("")));
		LB_Logo_Motor.setHorizontalAlignment(SwingConstants.CENTER);
		LB_Logo_Motor.setBounds(425, 585, 93, 21);
		contentPane.add(LB_Logo_Motor);
		
		LB_NumPiloto1 = new JLabel("");
		LB_NumPiloto1.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_NumPiloto1.setBounds(118, 393, 43, 21);
		contentPane.add(LB_NumPiloto1);
		
		LB_NumPiloto2 = new JLabel("");
		LB_NumPiloto2.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_NumPiloto2.setBounds(118, 425, 43, 21);
		contentPane.add(LB_NumPiloto2);
		
		LB_NumPiloto3 = new JLabel("");
		LB_NumPiloto3.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_NumPiloto3.setBounds(118, 457, 43, 21);
		contentPane.add(LB_NumPiloto3);
		
		LB_NumPiloto4 = new JLabel("");
		LB_NumPiloto4.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_NumPiloto4.setBounds(118, 489, 43, 21);
		contentPane.add(LB_NumPiloto4);
		
		LB_NumPiloto5 = new JLabel("");
		LB_NumPiloto5.setHorizontalAlignment(SwingConstants.RIGHT);
		LB_NumPiloto5.setBounds(121, 521, 43, 21);
		contentPane.add(LB_NumPiloto5);
		
		lblNewLabel = new JLabel("Digite o nome do seu dirigente de equipe");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 645, 249, 21);
		contentPane.add(lblNewLabel);
		
		LB_Idade1 = new JLabel("");
		LB_Idade1.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Idade1.setBounds(484, 393, 32, 21);
		contentPane.add(LB_Idade1);
		
		LB_Idade2 = new JLabel("");
		LB_Idade2.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Idade2.setBounds(484, 425, 32, 21);
		contentPane.add(LB_Idade2);
		
		LB_Idade3 = new JLabel("");
		LB_Idade3.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Idade3.setBounds(484, 457, 32, 21);
		contentPane.add(LB_Idade3);
		
		LB_Idade4 = new JLabel("");
		LB_Idade4.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Idade4.setBounds(484, 489, 32, 21);
		contentPane.add(LB_Idade4);
		
		LB_Idade5 = new JLabel("");
		LB_Idade5.setHorizontalAlignment(SwingConstants.LEFT);
		LB_Idade5.setBounds(484, 521, 32, 21);
		contentPane.add(LB_Idade5);
		
		TF_NomeDirigente = new JTextField();
		TF_NomeDirigente.setText("Douglas Bohmer");
		TF_NomeDirigente.setHorizontalAlignment(SwingConstants.CENTER);
		TF_NomeDirigente.setBounds(269, 645, 291, 21);
		contentPane.add(TF_NomeDirigente);
		TF_NomeDirigente.setColumns(10);
		
		LB_FlagMotor_1 = new JLabel("");
		LB_FlagMotor_1.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/IconeEuro24px.png")));
		LB_FlagMotor_1.setHorizontalAlignment(SwingConstants.CENTER);
		LB_FlagMotor_1.setBounds(449, 617, 111, 25);
		contentPane.add(LB_FlagMotor_1);
		
	}
	
	public void setInfoEquipeEscolhida() {
		
		Dados Dados = new Dados();
		
		EquipeEscolhida = ""+CB_Lista_Temp.getSelectedItem();
		NomeEquipeEscolhida = Dados.NomeEquipe; 
		SedeEquipeEscolhida =  Dados.Sede;
		BandeiraSedeEquipeEscolhida =  Dados.BandeiraSede;
		BandeiraEquipeEscolhida = Dados.BandeiraEquipe; 
		LogoEquipeEscolhida =  Dados.LogoEquipe;
		FundacaoEquipeEscolhida = Dados.fundacao;
	
		NomeMotorEscolhido = Dados.NomeMotor;
		BandeiraMotorEscolhido =  Dados.BandeiraMotor;
		LogoMotorEscolhido =  Dados.LogoMotor;
		LogoMotorPQEscolhido =  Dados.LogoMotorPQ;

		NomePiloto1 = LB_Piloto1_1.getText();;
		NumeroPiloto1 = LB_NumPiloto1.getText();
		if (LB_Born1.getText().equals("")) {
			NascimentoPiloto1 = 0;
		}else {
			NascimentoPiloto1 = Integer.parseInt(LB_Born1.getText());
		}if (LB_Idade1.getText().equals("")) {
			IdadePiloto1 = 0;
		}else {
			IdadePiloto1= Integer.parseInt(LB_Idade1.getText());
		}
		
		NomePiloto2 = LB_Piloto2_2.getText();
		NumeroPiloto2 = LB_NumPiloto2.getText();
		if (LB_Born2.getText().equals("")) {
			NascimentoPiloto2 = 0;
		}else {
			NascimentoPiloto2 = Integer.parseInt(LB_Born2.getText());
		}if (LB_Idade2.getText().equals("")) {
			IdadePiloto2 = 0;
		}else {
			IdadePiloto2 = Integer.parseInt(LB_Idade2.getText());
		}
		
		NomePiloto3 = LB_Piloto3_3.getText();
		NumeroPiloto3 = LB_NumPiloto3.getText();
		if (LB_Born3.getText().equals("")) {
			NascimentoPiloto3 = 0;
		}else {
			NascimentoPiloto3 = Integer.parseInt(LB_Born3.getText());
		}if (LB_Idade3.getText().equals("")) {
			IdadePiloto3 = 0;
		}else {
			IdadePiloto3 = Integer.parseInt(LB_Idade3.getText());
		}
		
		NomePiloto4 = LB_Piloto4_4.getText();
		NumeroPiloto4 = LB_NumPiloto4.getText();
		if (LB_Born4.getText().equals("")) {
			NascimentoPiloto4 = 0;
		}else {
			NascimentoPiloto4 = Integer.parseInt(LB_Born4.getText());
		}if (LB_Idade4.getText().equals("")) {
			IdadePiloto4 = 0;
		}else {
			IdadePiloto4 = Integer.parseInt(LB_Idade4.getText());
		}
		
		NomePiloto5 = LB_Piloto5_5.getText();
		NumeroPiloto5 = LB_NumPiloto5.getText();
		if (LB_Born5.getText().equals("")) {
			NascimentoPiloto5 = 0;
		}else {
			NascimentoPiloto5 = Integer.parseInt(LB_Born5.getText());
		}if (LB_Idade5.getText().equals("")) {
			IdadePiloto5 = 0;
		}else {
			IdadePiloto5 = Integer.parseInt(LB_Idade5.getText());
		}
		
	}

//F1
	
	public void setEquipesF1() {
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		
		if(Categoria.BT_Temp.equals("Temporada 2024")) {
			
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Red Bull Racing", "Mercedes", 
					"Ferrari", "McLaren", "Aston Martin", 
					"Alpine", "Williams", "Racing Bulls", 
					"Stake Kick", "Haas"}));

		}else if(Categoria.BT_Temp.equals("Temporada 2023")) {
		
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Red Bull Racing", "Mercedes", 
				"Ferrari", "McLaren", "Aston Martin", 
				"Alpine", "Williams", "Alpha Tauri", 
				"Alfa Romeo", "Haas"}));
			
		}else if(Categoria.BT_Temp.equals("Temporada 2022")) {
		
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Red Bull Racing", "Mercedes", 
				"Ferrari", "McLaren", "Aston Martin", 
				"Alpine", "Williams", "Alpha Tauri", 
				"Alfa Romeo", "Haas"}));
			
		}else if(Categoria.BT_Temp.equals("Temporada 2021")) {
		
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Red Bull Racing", "Mercedes", 
				"Ferrari", "McLaren", "Aston Martin", 
				"Alpine", "Williams", "Alpha Tauri", 
				"Alfa Romeo", "Haas"}));
			
		}
	}
	
	public void setInfoEquipesF1() {
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		if(Categoria.BT_Temp.equals("Temporada 2024")) {
			Temp2024F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2023")) {
			Temp2023F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2022")) {
			Temp2022F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2021")) {
			Temp2021F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2020")) {
			Temp2020F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2019")) {
			Temp2019F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2018")) {
			Temp2018F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2017")) {
			Temp2017F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2016")) {
			Temp2016F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2015")) {
			Temp2015F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2014")) {
			Temp2014F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2013")) {
			Temp2013F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2012")) {
			Temp2012F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2011")) {
			Temp2011F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2010")) {
			Temp2010F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2009")) {
			Temp2009F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2008")) {
			Temp2008F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2007")) {
			Temp2007F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2006")) {
			Temp2006F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2005")) {
			Temp2005F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2004")) {
			Temp2004F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2003")) {
			Temp2003F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2002")) {
			Temp2002F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2001")) {
			Temp2001F1();
		}else if(Categoria.BT_Temp.equals("Temporada 2000")) {
			Temp2000F1();
		}
		
		
		
	}
	
	public void Temp2024F1() {
		
		String foto_carro = (String) CB_Lista_Temp.getSelectedItem();
		ano = 2024;
		
		Dados Dados = new Dados();
		
		if(foto_carro.equals("Stake Kick")) {
			
			Dados.EquipeStakeKick();
			
			Dados.GuanyuZhou();
			P1();
			
			Dados.ValtteriBottas();
			P2();
			
			Dados.ZaneMaloney();
			ReservaP3();
			
			Dados.ThéoPourchaire();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorFerrari();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Red Bull Racing")) {

			Dados.EquipeRBR();
			
			Dados.MaxVerstappen();
			P1();
			
			Dados.SergioPerez();
			P2();
						
			Dados.LiamLawson();
			ReservaP3();
						
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Alpine")) {

			Dados.EquipeAlpine();
			
			Dados.EstebanOcon();
			P1();
			
			Dados.PierreGasly();
			P2();
			
			Dados.JackDoohan();
			ReservaP3();
						
			Dados.SemPiloto();
			SemP4();
						
			Dados.SemPiloto();
			SemP5();

			Dados.MotorRenault();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Ferrari")) {
			
			Dados.EquipeFerrari();
	
			Dados.CharlesLeclerc();
			P1();
			
			Dados.CarlosSainzJr();
			P2();
			
			Dados.RobertShwartzman();
			ReservaP3();
			
			Dados.OliverBearman();
			ReservaP4();
			
			Dados.AntonioGiovinazzi();
			ReservaP5();

			Dados.MotorFerrari();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Williams")) {
			
			Dados.EquipeWilliams();
			
			Dados.AlexanderAlbon();
			P1();
			
			Dados.LoganSargeant();
			P2();

			Dados.SemPiloto();
			SemP3();

			Dados.SemPiloto();
			SemP4();

			Dados.SemPiloto();
			SemP5();			

			Dados.MotorMercedes();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Racing Bulls")) {
			
			Dados.EquipeRacingBulls();
	
			Dados.YukiTsunoda();
			P1();
			
			Dados.DanielRicciardo();
			P2();
			
			Dados.LiamLawson();
			ReservaP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Aston Martin")) {
			
			Dados.EquipeAstonMartin();
			
			Dados.FernandoAlonso();
			P1();
			
			Dados.LanceStroll();
			P2();
			
			Dados.FelipeDrugovich();
			ReservaP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
			
			Dados.MotorMercedes();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Haas")) {
			
			Dados.EquipeHaas();
			
			Dados.NicoHulkenberg();
			P1();
			
			Dados.KevinMagnussen();
			P2();

			Dados.PietroFittipaldiF1();
			ReservaP3();
			
			Dados.OliverBearman();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorFerrari();
			DadosEquipeMotor();

		}else if(foto_carro.equals("McLaren")) {
			
			Dados.EquipeMcLaren();
			
			Dados.LandoNorris();
			P1();
			
			Dados.OscarPiastri();
			P2();
			
			Dados.PatricioOWardF1();
			ReservaP3();
			
			Dados.RyoHirakawa();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();
			
			Dados.MotorMercedes();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Mercedes")) {
			
			Dados.EquipeMercedes();
			
			Dados.LewisHamilton();
			P1();
			
			Dados.GeorgeRussell();
			P2();
			
			Dados.MickSchumacher();
			ReservaP3();
			
			Dados.FrederikVesti();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorMercedes();
			DadosEquipeMotor();

		}
	}
	
	public void Temp2023F1() {
		
		
		String foto_carro = (String) CB_Lista_Temp.getSelectedItem();
		ano = 2024;
		
		Dados Dados = new Dados();
		
		if(foto_carro.equals("Alfa Romeo")) {
			
			Dados.EquipeAlfaRomeo();
			
			Dados.GuanyuZhou();
			P1();
			
			Dados.ValtteriBottas();
			P2();
			
			Dados.ThéoPourchaire();
			ReservaP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorFerrari();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Red Bull Racing")) {

			Dados.EquipeRBR();
			
			Dados.MaxVerstappen();
			P1();
			
			Dados.SergioPerez();
			P2();
						
			Dados.ZaneMaloney();
			ReservaP3();
			
			Dados.SebastienBuemi();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Alpine")) {

			Dados.EquipeAlpine();
			
			Dados.EstebanOcon();
			P1();
			
			Dados.PierreGasly();
			P2();
			
			Dados.JackDoohan();
			ReservaP3();
						
			Dados.SemPiloto();
			SemP4();
						
			Dados.SemPiloto();
			SemP5();

			Dados.MotorRenault();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Ferrari")) {
			
			Dados.EquipeFerrari();
	
			Dados.CharlesLeclerc();
			P1();
			
			Dados.CarlosSainzJr();
			P2();
			
			Dados.RobertShwartzman();
			ReservaP3();
			
			Dados.AntonioGiovinazzi();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorFerrari();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Williams")) {
			
			Dados.EquipeWilliams();
			
			Dados.AlexanderAlbon();
			P1();
			
			Dados.LoganSargeant();
			P2();

			Dados.SemPiloto();
			SemP3();

			Dados.SemPiloto();
			SemP4();

			Dados.SemPiloto();
			SemP5();			

			Dados.MotorMercedes();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Alpha Tauri")) {
			
			Dados.EquipeAlphaTauri();
	
			Dados.YukiTsunoda();
			P1();
			
			Dados.DanielRicciardo();;
			P2();
			
			Dados.LiamLawson();
			ReservaP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carro.equals("Aston Martin")) {
			
			Dados.EquipeAstonMartin();
			
			Dados.FernandoAlonso();
			P1();
			
			Dados.LanceStroll();
			P2();
			
			Dados.FelipeDrugovich();
			ReservaP3();
			
			Dados.StoffelVandoorne();
			ReservaP4();
			
			Dados.SemPiloto();
			SemP5();
			
			Dados.MotorMercedes();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Haas")) {
			
			Dados.EquipeHaas();
			
			Dados.NicoHulkenberg();
			P1();
			
			Dados.KevinMagnussen();
			P2();

			Dados.PietroFittipaldiF1();
			ReservaP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorFerrari();
			DadosEquipeMotor();

		}else if(foto_carro.equals("McLaren")) {
			
			Dados.EquipeMcLaren();
			
			Dados.LandoNorris();
			P1();
			
			Dados.OscarPiastri();
			P2();
			
			Dados.AlexPalouF1();
			ReservaP3();
			
			Dados.MickSchumacher();
			ReservaP4();
			
			Dados.FelipeDrugovich();
			ReservaP5();
			
			Dados.MotorMercedes();
			DadosEquipeMotor();

		}else if(foto_carro.equals("Mercedes")) {
			
			Dados.EquipeMercedes();
			
			Dados.LewisHamilton();
			P1();
			
			Dados.GeorgeRussell();
			P2();
			
			Dados.MickSchumacher();
			ReservaP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorMercedes();
			DadosEquipeMotor();

		}
		
	}
	
	public void Temp2022F1() {
		
		String foto_carro = (String) CB_Lista_Temp.getSelectedItem();
		
		if(foto_carro.equals("Alfa Romeo")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Alfa Romeo.jpg")));
			
		}else if(foto_carro.equals("Red Bull Racing")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 RBR.jpg")));
			
		}else if(foto_carro.equals("Alpine")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Alpine.jpg")));
			
		}else if(foto_carro.equals("Ferrari")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Ferrari.jpg")));
			
		}else if(foto_carro.equals("Williams")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Williams.jpg")));
			
		}else if(foto_carro.equals("Alpha Tauri")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Alpha Tauri.jpg")));
			
		}else if(foto_carro.equals("Aston Martin")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Aston Martin.jpg")));
			
		}else if(foto_carro.equals("Haas")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Haas.jpg")));
			
		}else if(foto_carro.equals("McLaren")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 McLAren.jpg")));
			
		}else if(foto_carro.equals("Mercedes")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2022 Mercedes.jpg")));
			
		}
	}
	
	public void Temp2021F1() {
		
		String foto_carro = (String) CB_Lista_Temp.getSelectedItem();
		
		if(foto_carro.equals("Alfa Romeo")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Alfa Romeo.jpg")));
			
		}else if(foto_carro.equals("Red Bull Racing")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 RBR.jpg")));
			
		}else if(foto_carro.equals("Alpine")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Alpine.jpg")));
			
		}else if(foto_carro.equals("Ferrari")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Ferrari.jpg")));
			
		}else if(foto_carro.equals("Williams")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Williams.jpg")));
			
		}else if(foto_carro.equals("Alpha Tauri")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Alpha Tauri.jpg")));
			
		}else if(foto_carro.equals("Aston Martin")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Aston Martin.jpg")));
			
		}else if(foto_carro.equals("Haas")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Haas.jpg")));
			
		}else if(foto_carro.equals("McLaren")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 McLAren.jpg")));
			
		}else if(foto_carro.equals("Mercedes")) {
			
			LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource("/resource/2021 Mercedes.jpg")));
			
		}
	}
	
	public void Temp2020F1() {
		
	}
	
	public void Temp2019F1() {
		
	}
	
	public void Temp2018F1() {
		
	}
	
	public void Temp2017F1() {
		
	}
	
	public void Temp2016F1() {
		
	}
	
	public void Temp2015F1() {
		
	}
	
	public void Temp2014F1() {
		
	}
	
	public void Temp2013F1() {
		
	}
	
	public void Temp2012F1() {
		
	}
	
	public void Temp2011F1() {
		
	}
	
	public void Temp2010F1() {
		
	}
	
	public void Temp2009F1() {
		
	}
	
	public void Temp2008F1() {
		
	}
	
	public void Temp2007F1() {
		
	}
	
	public void Temp2006F1() {
		
	}
	
	public void Temp2005F1() {
		
	}
	
	public void Temp2004F1() {
		
	}
	
	public void Temp2003F1() {
		
	}
	
	public void Temp2002F1() {
		
	}
	
	public void Temp2001F1() {
		
	}
	
	public void Temp2000F1() {
		
	}
	
//INDY
	
	public void setEquipesIndy() {
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		if(Categoria.BT_Temp.equals("Temporada 2024")) {
			
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Team Penske", "Chip Ganassi Racing",
																		  "Andretti","Arrow McLaren", "Ed Carpenter Racing",
																		  "Abel Motorsports","A. J. Foyt Racing", "Juncos Hollinger Racing",
																		  "Dale Coyne Racing", "Meyer Shank Racing",
																		  "Dreyer & Reinbold Racing / Cusick Motorsports",
																		  "Rahal Letterman Lanigan Racing",
																		}));

		}else if(Categoria.BT_Temp.equals("Temporada 2023")) {
		
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Equipes Indy Temp 2023"}));
			
		}
		
	}
	
	public void setInfoEquipesIndy() {
		//IFs das temp
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		if(Categoria.BT_Temp.equals("Temporada 2024")) {
			Temp2024Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2023")) {
			Temp2023Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2022")) {
			Temp2022Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2021")) {
			Temp2021Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2020")) {
			Temp2020Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2019")) {
			Temp2019Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2018")) {
			Temp2018Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2017")) {
			Temp2017Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2016")) {
			Temp2016Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2015")) {
			Temp2015Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2014")) {
			Temp2014Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2013")) {
			Temp2013Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2012")) {
			Temp2012Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2011")) {
			Temp2011Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2010")) {
			Temp2010Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2009")) {
			Temp2009Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2008")) {
			Temp2008Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2007")) {
			Temp2007Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2006")) {
			Temp2006Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2005")) {
			Temp2005Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2004")) {
			Temp2004Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2003")) {
			Temp2003Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2002")) {
			Temp2002Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2001")) {
			Temp2001Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 2000")) {
			Temp2000Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 1999")) {
			Temp1999Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 1998")) {
			Temp1998Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 1997")) {
			Temp1997Indy();
		}else if(Categoria.BT_Temp.equals("Temporada 1996")) {
			Temp1996Indy();
		}
		
	}
	
	public void Temp2024Indy() {
		//Infos das Equipes daquele ano
		
		String foto_carroIndy = (String) CB_Lista_Temp.getSelectedItem();
		ano = 2024;
		
		if(foto_carroIndy.equals("Abel Motorsports")) {
				
			Dados.EquipeAbelMotorsports();
			
			Dados.RichardChadEnerson();;
			P1();
			
			Dados.SemPiloto();
			SemP2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
			
			Dados.MotorChevrolet();
			DadosEquipeMotor();

		}else if(foto_carroIndy.equals("A. J. Foyt Racing")) {

			Dados.EquipeAJFoyt();
			
			Dados.SantinoFerrucci();
			P1();
			
			Dados.StingRayRobb();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Andretti")) {
			
			Dados.EquipeAndretti();
			
			Dados.MarcusEricssonIndy();
			P1();
			
			Dados.ColtonHerta();
			P2();
			
			Dados.KyleKirkwood();
			P3();
			
			Dados.MarcoAndretti();
			P4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();			
			
		}else if(foto_carroIndy.equals("Arrow McLaren")) {
			
			Dados.EquipeArrowMcLaren();
			
			Dados.PatricioOWardFIndy();
			P1();
			
			Dados.DavidMalukas();
			P2();
			
			Dados.AlexanderRossiIndy();
			P3();
			
			Dados.KyleLarsonIndy();
			P4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Chip Ganassi Racing")) {
			
			Dados.EquipeChipGanassi();
			
			Dados.KyffinSimpson();
			P1();
			
			Dados.LinusLundqvist();
			P2();
			
			Dados.ScottDixon();
			P3();
			
			Dados.AlexPalouIndy();
			P4();
			
			Dados.MarcusArmstrong();
			P5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Dale Coyne Racing")) {
			
			Dados.EquipeDaleCoyne();
			
			Dados.JackHarvey();
			P1();
			
			Dados.ColinBraun();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Dreyer & Reinbold Racing / Cusick Motorsports")) {
			
			Dados.EquipeDreyer();
			
			Dados.RyanHunterReay();
			P1();
			
			Dados.ConorDaly();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Ed Carpenter Racing")) {

			Dados.EquipeEdCarpenter();
			
			Dados.EdCarpenter();
			P1();
			
			Dados.RinusVeeKay();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
			
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Juncos Hollinger Racing")) {
			
			Dados.EquipeJuncos();
			
			Dados.RomainGrosjean();
			P1();
			
			Dados.AgustinCanapino();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Meyer Shank Racing")) {
			
			Dados.EquipeMeyer();
			
			Dados.HelioCastroneves();
			P1();
			
			Dados.FelixRosenqvist();
			P2();
			
			Dados.TomBlomqvist();
			P3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
				
		}else if(foto_carroIndy.equals("Rahal Letterman Lanigan Racing")) {
			
			Dados.EquipeRahal();
			
			Dados.GrahamRahal();
			P1();
			
			Dados.PietroFittipaldiIndy();
			P2();
			
			Dados.ChristianLundgaard();
			P3();
			
			Dados.TakumaSatoIndy();
			P4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorHonda();
			DadosEquipeMotor();
			
		}else if(foto_carroIndy.equals("Team Penske")) {
			
			Dados.EquipePenske();
			
			Dados.JosefNewgarden();
			P1();
			
			Dados.ScottMcLaughlin();
			P2();
			
			Dados.ScottDixon();
			P3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();

			Dados.MotorChevrolet();
			DadosEquipeMotor();
		}
		
	}
	
	public void Temp2023Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2022Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2021Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2020Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2019Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2018Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2017Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2016Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2015Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2014Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2013Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2012Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2011Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2010Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2009Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2008Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2007Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2006Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2005Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2004Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2003Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2002Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2001Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2000Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp1999Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp1998Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp1997Indy() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp1996Indy() {
		//Infos das Equipes daquele ano
	}
	
//NASCAR
	
	public void setEquipesNascar() {
		//ADD equipes no seletor
		
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		if(Categoria.BT_Temp.equals("Temporada 2024")) {
			
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {
					"Hendrick Motorsports", "Joe Gibbs Racing", "Team Penske", "Richard Childress Racing", 
					"RFK Racing", "23XI Racing","Trackhouse Racing", "Stewart–Haas Racing", 
					"JTG Daugherty Racing", "Wood Brothers Racing", "Legacy Motor Club",
					"Kaulig Racing", "Front Row Motorsports", "Spire Motorsports",  "Rick Ware Racing"}));
			
		}else if(Categoria.BT_Temp.equals("Temporada 2023")) {
		
			CB_Lista_Temp.setModel(new DefaultComboBoxModel(new String[] {"Equipes NASCAR Temp 2023"}));
			
		}
		
	}
	
	public void setInfoEquipesNascar() {
		Selecionar_Categoria Categoria = new Selecionar_Categoria();
		
		if(Categoria.BT_Temp.equals("Temporada 2024")) {
			Temp2024Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2023")) {
			Temp2023Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2022")) {
			Temp2022Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2021")) {
			Temp2021Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2020")) {
			Temp2020Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2019")) {
			Temp2019Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2018")) {
			Temp2018Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2017")) {
			Temp2017Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2016")) {
			Temp2016Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2015")) {
			Temp2015Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2014")) {
			Temp2014Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2013")) {
			Temp2013Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2012")) {
			Temp2012Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2011")) {
			Temp2011Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2010")) {
			Temp2010Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2009")) {
			Temp2009Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2008")) {
			Temp2008Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2007")) {
			Temp2007Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2006")) {
			Temp2006Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2005")) {
			Temp2005Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2004")) {
			Temp2004Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2003")) {
			Temp2003Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2002")) {
			Temp2002Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2001")) {
			Temp2001Nascar();
		}else if(Categoria.BT_Temp.equals("Temporada 2000")) {
			Temp2000Nascar();
		}
		
	}
	
	public void Temp2024Nascar() {
		//Infos das Equipes daquele ano
		
		String foto_carroNascar = (String) CB_Lista_Temp.getSelectedItem();
		
		if(foto_carroNascar.equals("Hendrick Motorsports")) {
			
			Dados.EquipeHendrick();
			
			Dados.KyleLarsonNASCAR();
			P1();
			
			Dados.ChaseElliott();
			P2();
			
			Dados.WilliamByron();
			P3();
			
			Dados.AlexBowman();
			P4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Front Row Motorsports")) {
			
			Dados.EquipeFrontRow();
			
			Dados.MichaelMcDowell();
			P1();
			
			Dados.ToddGilliland();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorFord();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("RFK Racing")) {
			
			Dados.EquipeRFK();
			
			Dados.BradKeselowski();
			P1();
			
			Dados.ChrisBuescher();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorFord();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Rick Ware Racing")) {
			
			Dados.EquipeRickWare();
			
			Dados.KazGrala();
			P1();
			
			Dados.JustinHaley();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorFord();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Stewart–Haas Racing")) {
			
			Dados.EquipeStewartHaas();
			
			Dados.JoshBerry();
			P1();
			
			Dados.NoahGragson();
			P2();
			
			Dados.ChaseBriscoe();
			P3();
			
			Dados.RyanPreece();
			P4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorFord();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Wood Brothers Racing")) {
			
			Dados.EquipeWoodBrothers();
			
			Dados.HarrisonBurton();
			P1();
			
			Dados.SemPiloto();
			SemP2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorFord();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("23XI Racing")) {
			
			Dados.Equipe23XI();
			
			Dados.BubbaWallace();
			P1();
			
			Dados.TylerReddick();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorToyota();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Joe Gibbs Racing")) {
			
			Dados.EquipeJoeGibbs();
			
			Dados.DennyHamlin();
			P1();
			
			Dados.MartinTruexJr();
			P2();
			
			Dados.ChristopherBell();
			P3();
			
			Dados.TyGibbs();
			P4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorToyota();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Legacy Motor Club")) {
			
			Dados.EquipeLegacy();
			
			Dados.JohnHunterNemechek();
			P1();
			
			Dados.ErikJones();
			P2();
			
			Dados.JimmieJohnson();
			P3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorToyota();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("JTG Daugherty Racing")) {
			
			Dados.EquipeJTG();
			
			Dados.RickyStenhouseJr();
			P1();
			
			Dados.SemPiloto();
			SemP2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Kaulig Racing")) {
			
			Dados.EquipeKaulig();
			
			Dados.AJAlmendinger();
			P1();
			
			Dados.ShanevanGisbergen();
			P2();
			
			Dados.DanielHemric();
			P3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Richard Childress Racing")) {
			
			Dados.EquipeRCR();
			
			Dados.AustinDillon();
			P1();
			
			Dados.KyleBusch();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Spire Motorsports")) {
			
			Dados.EquipeSpire();
			
			Dados.CoreyLaJoie();
			P1();
			
			Dados.ZaneSmith();
			P2();
			
			Dados.CarsonHocevar();
			P3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Trackhouse Racing")) {
			
			Dados.EquipeTrackhouse();
			
			Dados.RossChastain();
			P1();
			
			Dados.DanielSuarez();
			P2();
			
			Dados.SemPiloto();
			SemP3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorChevrolet();
			DadosEquipeMotor();
			
		}else if(foto_carroNascar.equals("Team Penske")) {

			Dados.EquipePenske();
			
			Dados.AustinCindric();
			P1();
			
			Dados.RyanBlaney();
			P2();
			
			Dados.JoeyLogano();
			P3();
			
			Dados.SemPiloto();
			SemP4();
			
			Dados.SemPiloto();
			SemP5();
				
			Dados.MotorFord();
			DadosEquipeMotor();
		}
		
	}
	
	public void Temp2023Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2022Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2021Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2020Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2019Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2018Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2017Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2016Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2015Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2014Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2013Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2012Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2011Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2010Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2009Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2008Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2007Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2006Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2005Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2004Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2003Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2002Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2001Nascar() {
		//Infos das Equipes daquele ano
	}
	
	public void Temp2000Nascar() {
		//Infos das Equipes daquele ano
	}

//Info Padrão	

	public void P1() {
		LB_Piloto1.setText("Piloto 1");
		LB_Piloto1_1.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born1.setText("");
			LB_Idade1.setText("");
		}else {
			LB_Born1.setText(""+Dados.ano_nasc);
			LB_Idade1.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto1.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto1.setText("");
		}else {
			LB_NumPiloto1.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto1 = Dados.Pais;
	}
	
	public void P2() {
		LB_Piloto2.setText("Piloto 2");
		LB_Piloto2_2.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born2.setText("");
			LB_Idade2.setText("");
		}else {
			LB_Born2.setText(""+Dados.ano_nasc);
			LB_Idade2.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto2.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto2.setText("");
		}else {
			LB_NumPiloto2.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto2 = Dados.Pais;
	}
	
	public void P3() {
		LB_Piloto3.setText("Piloto 3");
		LB_Piloto3_3.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born3.setText("");
			LB_Idade3.setText("");
		}else {
			LB_Born3.setText(""+Dados.ano_nasc);
			LB_Idade3.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto3.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto3.setText("");
		}else {
			LB_NumPiloto3.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto3 = Dados.Pais;
	}
	
	public void P4() {
		LB_Piloto4.setText("Piloto 4");
		LB_Piloto4_4.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born4.setText("");
			LB_Idade4.setText("");
		}else {
			LB_Born4.setText(""+Dados.ano_nasc);
			LB_Idade4.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto4.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto4.setText("");
		}else {
			LB_NumPiloto4.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto4 = Dados.Pais;
	}
	
	public void P5() {
		LB_Piloto5.setText("Piloto 5");
		LB_Piloto5_5.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born5.setText("");
			LB_Idade5.setText("");
		}else {
			LB_Born5.setText(""+Dados.ano_nasc);
			LB_Idade5.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto5.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto5.setText("");
		}else {
			LB_NumPiloto5.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto5 = Dados.Pais;
	}
	
	public void SemP1() {
		LB_Piloto1.setText("");
		LB_Piloto1_1.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born1.setText("");
			LB_Idade1.setText("");
		}else {
			LB_Born1.setText(""+Dados.ano_nasc);
			LB_Idade1.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto1.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto1.setText("");
		}else {
			LB_NumPiloto1.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto1 = Dados.Pais;
	}

	public void SemP2() {
		LB_Piloto2.setText("");
		LB_Piloto2_2.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born2.setText("");
			LB_Idade2.setText("");
		}else {
			LB_Born2.setText(""+Dados.ano_nasc);
			LB_Idade2.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto2.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto2.setText("");
		}else {
			LB_NumPiloto2.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto2 = Dados.Pais;
	}

	public void SemP3() {
		LB_Piloto3.setText("");
		LB_Piloto3_3.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born3.setText("");
			LB_Idade3.setText("");
		}else {
			LB_Born3.setText(""+Dados.ano_nasc);
			LB_Idade3.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto3.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto3.setText("");
		}else {
			LB_NumPiloto3.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto3 = Dados.Pais;
	}
	
	public void SemP4() {
		LB_Piloto4.setText("");
		LB_Piloto4_4.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born4.setText("");
			LB_Idade4.setText("");
		}else {
			LB_Born4.setText(""+Dados.ano_nasc);
			LB_Idade4.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto4.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto4.setText("");
		}else {
			LB_NumPiloto4.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto4 = Dados.Pais;
	}
	
	public void SemP5() {
		LB_Piloto5.setText("");
		LB_Piloto5_5.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born5.setText("");
			LB_Idade5.setText("");
		}else {
			LB_Born5.setText(""+Dados.ano_nasc);
			LB_Idade5.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto5.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto5.setText("");
		}else {
			LB_NumPiloto5.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto5 = Dados.Pais;
	}

	public void ReservaP1() {
		LB_Piloto1.setText("Piloto Reserva");
		LB_Piloto1_1.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born1.setText("");
			LB_Idade1.setText("");
		}else {
			LB_Born1.setText(""+Dados.ano_nasc);
			LB_Idade1.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto1.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto1.setText("");
		}else {
			LB_NumPiloto1.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto1 = Dados.Pais;
	}
	
	public void ReservaP2() {
		LB_Piloto2.setText("Piloto Reserva");
		LB_Piloto2_2.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born2.setText("");
			LB_Idade2.setText("");
		}else {
			LB_Born2.setText(""+Dados.ano_nasc);
			LB_Idade2.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto2.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto2.setText("");
		}else {
			LB_NumPiloto2.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto2 = Dados.Pais;
	}
	
	public void ReservaP3() {
		LB_Piloto3.setText("Piloto Reserva");
		LB_Piloto3_3.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born3.setText("");
			LB_Idade3.setText("");
		}else {
			LB_Born3.setText(""+Dados.ano_nasc);
			LB_Idade3.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto3.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto3.setText("");
		}else {
			LB_NumPiloto3.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto3 = Dados.Pais;
	}
	
	public void ReservaP4() {
		LB_Piloto4.setText("Piloto Reserva");
		LB_Piloto4_4.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born4.setText("");
			LB_Idade4.setText("");
		}else {
			LB_Born4.setText(""+Dados.ano_nasc);
			LB_Idade4.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto4.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto4.setText("");
		}else {
			LB_NumPiloto4.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto4 = Dados.Pais;
	}
	
	public void ReservaP5() {
		LB_Piloto5.setText("Piloto Reserva");
		LB_Piloto5_5.setText(""+Dados.Nome);
		if (Dados.ano_nasc == 0) {
			LB_Born5.setText("");
			LB_Idade5.setText("");
		}else {
			LB_Born5.setText(""+Dados.ano_nasc);
			LB_Idade5.setText(""+(ano - Dados.ano_nasc)+"");
		}
		LB_FlagPiloto5.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraP)));
		if(Dados.n_piloto == 100 ) {
			LB_NumPiloto5.setText("");
		}else {
			LB_NumPiloto5.setText("#"+Dados.n_piloto+" - ");
		}
		PaisPiloto5 = Dados.Pais;
	}
	
	public void DadosEquipeMotor() {
		
		LB_Foto_Carro.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.LogoEquipe)));
		LB_Sede.setText(""+Dados.Sede);
		LB_FlagSede.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraSede)));
		LB_Fundacao.setText(""+Dados.fundacao);
		LB_Motor.setText(""+Dados.NomeMotor);
		LB_FlagMotor.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.BandeiraMotor)));
		LB_Logo_Motor.setIcon(new ImageIcon(Selecionar_Equipe.class.getResource(Dados.LogoMotorPQ)));
		LB_Orçamento.setText("€"+Dados.Orcamento+" milhões de Euros");
	}
}

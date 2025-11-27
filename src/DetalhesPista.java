import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class DetalhesPista extends JFrame {

	private JPanel contentPane;
	private JLabel LBDetalhesPista;
	private JButton BTVoltar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetalhesPista frame = new DetalhesPista();
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
	public DetalhesPista() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				Dados dados = new Dados();				
				
				LBDetalhesPista.setIcon(new ImageIcon(Selecionar_Categoria.class.getResource(""+Dados.LayoutPista)));;
				
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		LBDetalhesPista = new JLabel("");
		LBDetalhesPista.setIcon(new ImageIcon(DetalhesPista.class.getResource("")));
		LBDetalhesPista.setHorizontalAlignment(SwingConstants.CENTER);
		LBDetalhesPista.setBounds(10, 11, 864, 640);
		contentPane.add(LBDetalhesPista);
		
		BTVoltar = new JButton("Voltar");
		BTVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dispose();					
				
			}
		});
		BTVoltar.setBounds(392, 662, 115, 23);
		contentPane.add(BTVoltar);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selecionar_Equipe.class.getResource("/Imagens/Icone16px.png")));
		setTitle("Motorsport Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 730);
		setResizable(false);
		
	}
}

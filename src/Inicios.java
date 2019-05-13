import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JTable;
import javax.sound.midi.Synthesizer;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;












public class Inicios {

	private JFrame frame;
	JPanel inicio = new JPanel();
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicios window = new Inicios();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Inicios() {

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// --------todas las encuestas--------------------------
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().add(inicio, BorderLayout.CENTER);

		inicio.setLayout(new BorderLayout(0, 0));

		inicio.add(new inicioSesion(), BorderLayout.CENTER);
		inicio.setVisible(true);

	}

	class inicioSesion extends JPanel {
		gestionBase ges = new gestionBase();

		private JTextField textField;
		private Label label_1;
		private JPasswordField passwordField;
		private JPanel panel_1;
		private JPanel panel_2;

		public inicioSesion() {
			setVisible(true);
			setLayout(new BorderLayout(0, 0));
			JPanel panel = new JPanel();
			add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			Label label = new Label("Correo Electronico/Usuario");
			label.setFont(new Font("Dialog", Font.PLAIN, 25));
			label.setAlignment(Label.CENTER);
			label.setBounds(124, 304, 388, 24);
			panel.add(label);

			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setToolTipText("k");
			textField.setBounds(140, 353, 388, 22);
			panel.add(textField);
			textField.setColumns(10);

			label_1 = new Label("Contrase\u00F1a");
			label_1.setFont(new Font("Dialog", Font.PLAIN, 25));
			label_1.setAlignment(Label.CENTER);
			label_1.setBounds(124, 398, 388, 24);
			panel.add(label_1);

			passwordField = new JPasswordField();
			passwordField.setBounds(198, 444, 288, 22);
			panel.add(passwordField);

			Imagen imagen = new Imagen();
			imagen.setLocation(80, 0);
			imagen.setSize(500, 250);
			panel.add(imagen);

			panel_2 = new JPanel();
			panel_2.setBounds(198, 485, 288, 68);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));

			JButton btnNewButton = new JButton("No tengo cuenta");

			panel_2.add(btnNewButton, BorderLayout.WEST);

			JButton iniciar = new JButton("Iniciar Sesion");

			iniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String contraseña = passwordField.getText();

					inicio.removeAll();
					iniciado inis = ges.conseguirIniciado(textField.getText(), comprobarCorreo(textField.getText()),
							contraseña);
					if (inis != null) {
						inicio.add(new menu(inis), BorderLayout.CENTER);
						inicio.setVisible(false);
						inicio.setVisible(true);
					}
				}
			});
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					inicio.removeAll();
					
						inicio.add(new registro(), BorderLayout.CENTER);
						inicio.setVisible(false);
						inicio.setVisible(true);
					}
				
			});

			panel_2.add(iniciar, BorderLayout.NORTH);

			JButton btnNewButton_1 = new JButton("Olvide contrase\u00F1a");
			panel_2.add(btnNewButton_1, BorderLayout.EAST);
		}

		private boolean comprobarCorreo(String correo) {
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(correo);
			return mather.find();
		}

	}

	class Imagen extends javax.swing.JPanel {

		public Imagen() {
			this.setSize(500, 500); // se selecciona el tamaño del panel
		}

		// Se crea un método cuyo parámetro debe ser un objeto Graphics

		public void paint(Graphics grafico) {
			Dimension height = getSize();

			// Se selecciona la imagen que tenemos en el paquete de la //ruta del programa

			ImageIcon Img = new ImageIcon(getClass().getResource("logo.png"));

			// se dibuja la imagen que tenemos en el paquete Images //dentro de un panel

			grafico.drawImage(Img.getImage(), 140, 50, 200, 200, null);

			setOpaque(false);
			super.paintComponent(grafico);
		}
	}

	class menu extends JPanel {
		private JPanel panels = new JPanel();
		private iniciado iniciado;

		public menu(iniciado iniciado) {
			this.iniciado = iniciado;
			setLayout(new BorderLayout(0, 0));
			setVisible(true);

			FlowLayout flowLayout = (FlowLayout) panels.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setAlignOnBaseline(true);
			panels.setBackground(Color.DARK_GRAY);
			add(panels, BorderLayout.NORTH);

			JButton btnNewButton_1 = new JButton("New button");

			panels.add(btnNewButton_1);

			JButton btnNewButton = new JButton("Debates");

			panels.add(btnNewButton);
			JButton tests = new JButton("Insertar Test");

			panels.add(tests);
			JButton gesT = new JButton("Gestion");

			panels.add(gesT);

			JPanel panel_1 = new JPanel();
			panel_1.setBackground(Color.GRAY);
			add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BorderLayout(0, 0));
			JButton crearDebates = new JButton("Crear debate");
			crearDebates.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel_1.removeAll();

					panel_1.add(new crearDebates(iniciado), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});
			panels.add(crearDebates);
			btnNewButton_1.setText("Tests");

			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel_1.removeAll();

					panel_1.add(new panelTests(frame, iniciado), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});
			tests.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel_1.removeAll();

					panel_1.add(new crearTest(frame, iniciado), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						Socket misocket = new Socket("192.168.4.155", 9999);
						ObjectOutputStream paquete_datoss = new ObjectOutputStream(misocket.getOutputStream());

						paquete_datoss.writeObject(new online());

						paquete_datoss.close();
						misocket.close();
						panel_1.removeAll();
						panel_1.add(new panelDebates(iniciado, frame), BorderLayout.CENTER);
						panel_1.setVisible(false);
						panel_1.setVisible(true);
					} catch (UnknownHostException e1) {
						System.out.println("no esta arrancado");
					} catch (IOException e1) {
						System.out.println("no esta arrancado");
						JOptionPane.showMessageDialog(frame, "EL Servidor Esta En Mantenimiento Pruebe Mas Tarde",
								"Error 404", JOptionPane.WARNING_MESSAGE);
					}

				}
			});

			gesT.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel_1.removeAll();

					panel_1.add(new gestionTests(frame, iniciado), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});

		}

	}

}

class iniciado {
	private String correo;
	private String usuario;
	private int codigoUsuario;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getCodigoCorreo() {
		return codigoUsuario;
	}

	public void setCodigoCorreo(int codigoCorreo) {
		this.codigoUsuario = codigoCorreo;
	}

	public iniciado(String correo, String usuario, int codigoUsuario) {
		this.correo = correo;
		this.usuario = usuario;
		this.codigoUsuario = codigoUsuario;
	}

}

class online implements Serializable {

}

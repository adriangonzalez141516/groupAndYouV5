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
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import com.sun.*;

import rojerusan.complementos.RSProgressCircleUIAnimated;
import rojerusan.componentes.RSProgressBarAnimated;
import rojerusan.componentes.RSProgressCircle;
import rojerusan.componentes.RSProgressMaterial;

import javax.mail.*;
import javax.mail.internet.*;

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
	private myTextField textField;

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

		private myTextField textField;
		private Label label_1;
		private String correo;
		private JPasswordField passwordField;
		private JPanel panel_1;
		private JPanel panel_2;
		private menu menu;
		private myTextField codigo;
		private String codigoGenerado;
		private JButton enviarCodigo;
		private JLabel introduceCodigo;
		private JPanel panel;

		public inicioSesion() {
			setVisible(true);
			setLayout(new BorderLayout(0, 0));
			panel = new JPanel();
			add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			Label label = new Label("Correo Electronico/Usuario");
			label.setFont(new Font("Dialog", Font.PLAIN, 25));
			label.setAlignment(Label.CENTER);
			label.setBounds(124, 264, 378, 24);
			panel.add(label);

			introduceCodigo = new JLabel("Introduce el codigo que hemos enviado a tu correo");
			introduceCodigo.setBounds(198, 500, 300, 24);
			introduceCodigo.setVisible(false);
			panel.add(introduceCodigo);
			codigo = new myTextField();
			codigo.setBounds(230, 535, 60, 24);
			codigo.setVisible(false);
			panel.add(codigo);
			enviarCodigo = new JButton();
			enviarCodigo.setVisible(false);
			enviarCodigo.setText("Enviar codigo");
			enviarCodigo.setBounds(320, 535, 120, 24);
			panel.add(enviarCodigo);

			textField = new myTextField();
			textField.setHorizontalAlignment(SwingConstants.CENTER);
			textField.setToolTipText("k");
			textField.setBounds(140, 303, 388, 22);
			panel.add(textField);

			textField.setColumns(10);

			label_1 = new Label("Contrase\u00F1a");
			label_1.setFont(new Font("Dialog", Font.PLAIN, 25));
			label_1.setAlignment(Label.CENTER);
			label_1.setBounds(124, 335, 388, 24);
			panel.add(label_1);

			passwordField = new JPasswordField();
			passwordField.setBounds(198, 375, 288, 22);
			panel.add(passwordField);

			imagen imagen = new imagen();
			imagen.setLocation(80, 0);
			imagen.setSize(500, 250);
			panel.add(imagen);

			panel_2 = new JPanel();
			panel_2.setBounds(198, 417, 288, 68);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));

			JButton btnNewButton = new JButton("No tengo cuenta");

			panel_2.add(btnNewButton, BorderLayout.WEST);

			JButton iniciar = new JButton("Iniciar Sesion");
			enviarCodigo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					if (codigo.getText().equals(codigoGenerado)) {
						enviarCodigo.setVisible(false);
						introduceCodigo.setVisible(false);
						codigo.setVisible(false);
						myTextField contra = new myTextField();
						contra.setMaximo(19);
						JButton cambiar = new JButton("Cambiar Contraseña");
						JLabel introducecontra = new JLabel("Introduce la nueva contraseña");
						contra.setBounds(220, 535, 70, 24);
						introducecontra.setBounds(250, 500, 300, 24);
						cambiar.setBounds(310, 535, 155, 24);
						cambiar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {

								if (contra.superaMaximo()) {
									if (contra.getText().length() > 6) {
										ges.actualizarContra(correo, contra.getText());
										JOptionPane.showMessageDialog(frame, "Se a actualizado la contraseña");
										panel.remove(cambiar);
										panel.remove(introducecontra);
										panel.remove(contra);
										panel.setVisible(false);
										panel.setVisible(true);
									} else {
										JOptionPane.showMessageDialog(frame,
												"La contraseña tiene un minimo de 6 caracteres",
												"no supera caracteres minimos", JOptionPane.WARNING_MESSAGE);
									}
								} else {
									JOptionPane.showMessageDialog(frame,
											"La contraseña tiene un maixmo de 19 caracteres",
											"Superar caracteres maximos", JOptionPane.WARNING_MESSAGE);
								}

							}
						});
						panel.add(contra);
						panel.add(cambiar);
						panel.add(introducecontra);

					}
				}
			});

			iniciar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String contraseña = passwordField.getText();

					iniciado inis = ges.conseguirIniciado(textField.getText(), comprobarCorreo(textField.getText()),
							contraseña);
					if (inis != null) {

						inicio.removeAll();
						menu = new menu(inis);
						inicio.add(menu, BorderLayout.CENTER);
						inicio.setVisible(false);
						inicio.setVisible(true);
					} else {
						passwordField.setText("");
						JOptionPane.showMessageDialog(frame, "El usuario y contraseña no coinciden",
								"Error de auntentificacion", JOptionPane.WARNING_MESSAGE);
					}
				}
			});

			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					inicio.removeAll();

					inicio.add(new registro(frame, panel), BorderLayout.CENTER);
					inicio.setVisible(false);
					inicio.setVisible(true);
				}

			});

			panel_2.add(iniciar, BorderLayout.NORTH);

			JButton btnNewButton_1 = new JButton("Olvide contrase\u00F1a");
			btnNewButton_1.addActionListener(new ActionListener() {

				protected String generarCodigo() {
					char elegido;
					String codigo = "";
					char[] arr = new char[] { 'A', 'b', 'C', 'd', 'F', 'G', 'h', 'i', 'O', 'p', 'Q', 'r', 'S', 't', 'U',
							'v', 'W', 'x', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

					for (int i = 0; i < 6; i++) {
						elegido = arr[(int) (Math.random() * arr.length - 1) + 1];
						codigo += elegido;
					}
					return codigo;
				}

				public void actionPerformed(ActionEvent arg0) {
					if (!textField.getText().equals("")) {

						codigoGenerado = generarCodigo();
						String destinatario = ges.conseguirCorreo(textField.getText());
						if (!destinatario.equals("")) {
							correo = destinatario;
							String asunto = "Recuperar Clave";
							String cuerpo = "Este es el codigo: " + codigoGenerado;
							correo correo = new correo(asunto, cuerpo, destinatario, frame, "inicio");
							correo.start();
							codigo.setVisible(true);
							enviarCodigo.setVisible(true);
							introduceCodigo.setVisible(true);

							JOptionPane.showMessageDialog(frame, "Enviado con Exito");
						} else {
							JOptionPane.showMessageDialog(frame, "Este correo no esta registrado",
									"Error de autentificacion", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});
			panel_2.add(btnNewButton_1, BorderLayout.EAST);

		}

		private boolean comprobarCorreo(String correo) {
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(correo);
			return mather.find();
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

					panel_1.add(new crearDebates(iniciado, frame), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});
			panels.add(crearDebates);
			btnNewButton_1.setText("Tests");
			JButton gestionDebates = new JButton("Gestionar debates");
			gestionDebates.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel_1.removeAll();

					panel_1.add(new gestionDebate(frame, iniciado), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});
			panels.add(gestionDebates);

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
						Socket misocket = new Socket("192.168.4.232", 9999);
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
			JOvalBtn cuenta = new JOvalBtn(45, 45);
			cuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("iconov2.png")));
			cuenta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					panel_1.removeAll();

					panel_1.add(new gestionCuenta	(frame, iniciado), BorderLayout.CENTER);
					panel_1.setVisible(false);
					panel_1.setVisible(true);

				}
			});
			panels.add(cuenta);

		}

	}

}

class online implements Serializable {

}


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.sound.midi.Synthesizer;
import javax.swing.*;

public class panelDebate extends JPanel implements Runnable {
	private JLabel titulo;
	private JPanel debate;
	private String titulos;
	private JScrollPane scrollPane;
	gestionBase ges = new gestionBase();
	private iniciado ini;
	private int puerto;
	private JFrame frame;

	public panelDebate(String titulos, iniciado ini, JFrame frame) {
		this.frame = frame;
		this.ini = ini;
		this.titulos = titulos;
		System.out.println(titulos);
		this.puerto = ges.conseguirPuerto(titulos);

		setLayout(new BorderLayout(0, 0));
		titulo = new JLabel(titulos);

		debate = new JPanel();
		debate.setLayout(new GridLayout(10, 1, 10, 10));
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(debate);

		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 28));

		insertarMensajes(titulos);

		add(scrollPane, BorderLayout.CENTER);
		add(titulo, BorderLayout.NORTH);

		insertarBotonEnviar();
		setVisible(true);

		Socket envia_destinatario;
		try {

			envia_destinatario = new Socket("192.168.4.232", 9999);

			ObjectOutputStream paqueteReenvio = new ObjectOutputStream(envia_destinatario.getOutputStream());

			paqueteReenvio.writeObject(new mensaje(null, null));

			paqueteReenvio.close();
			envia_destinatario.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Thread mihilo = new Thread(this);
		mihilo.start();

	}

	private ServerSocket servidor_cliente;

	public void run() {
		boolean hey = false;
		do {
			try {

				if (!hey) {
					servidor_cliente = new ServerSocket(puerto);
					hey = false;

				}
				Socket cliente;
				while (true) {

					cliente = servidor_cliente.accept();
					ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());

					try {

						insertarMensajes(titulo.getText());
						notificar a = new notificar();
						mensaje men = (mensaje) flujoentrada.readObject();
						a.displayTray(men.getPersona() + ": " + men.getMesaje().getText());
						flujoentrada.close();
						cliente.close();

					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (AWTException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			} catch (Exception e) {
				hey = true;

			}
		} while (hey);

	}

	protected void insertarBotonEnviar() {

		add(new panelSur(), BorderLayout.SOUTH);
	}

	protected void insertarMensajes(String titulos) {
		int numero = 0;
		debate.removeAll();
		ArrayList<mensaje> mensajes;

		mensajes = ges.conseguirMensajes(titulos);

		for (mensaje l : mensajes) {

			numero++;

			JLabel h = new JLabel(l.getPersona() + ": " + l.getMesaje().getText());
			h.setHorizontalAlignment(SwingConstants.CENTER);
			panelBoton a = new panelBoton(h);
			if (numero % 2 == 0) {
				h.setForeground(Color.WHITE);
				a.setBackground(Color.DARK_GRAY);
			} else {
				a.setBackground(Color.LIGHT_GRAY);
			}
			if (numero > 10) {

				debate.setLayout(new GridLayout(numero, 1, 10, 10));
			}
			debate.add(a);
			debate.setVisible(false);
			debate.setVisible(true);

		}
	}

	class panelSur extends JPanel {

		private JButton enviar = new JButton("enviar");
		private myTextField mensaje = new myTextField();

		public panelSur() {
			mensaje.setMaximo(99);
			setLayout(new BorderLayout(0, 0));

			enviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					gestionBase ges = new gestionBase();
					try {
						Socket misocket = new Socket("192.168.4.232", 9999);
						ObjectOutputStream paquete_datoss = new ObjectOutputStream(misocket.getOutputStream());

						paquete_datoss.writeObject(new online());

						paquete_datoss.close();
						misocket.close();
					} catch (Exception i) {
						JOptionPane.showMessageDialog(frame, "EL Servidor Esta En Mantenimiento Pruebe Mas Tarde",
								"Error 404", JOptionPane.WARNING_MESSAGE);

					}

					if (!mensaje.getText().equals("")) {
						if (mensaje.superaMaximo()) {

							ges.enviarRespuesta(ini.getCodigoCorreo(), titulo.getText(), mensaje.getText());
							try {
								Socket misocket = new Socket("192.168.4.232", 9999);
								ObjectOutputStream paquete_datoss = new ObjectOutputStream(misocket.getOutputStream());
								mensaje men = new mensaje(new JLabel(mensaje.getText()), ini.getUsuario());
								men.setPuerto(puerto);
								paquete_datoss.writeObject(men);

								mensaje.setText("");
								paquete_datoss.close();
								misocket.close();
							} catch (UnknownHostException e1) {
								System.out.println("host desconocido");
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(frame,
										"EL servidor Esta En Mantenimiento Pruebe Mas Tarde", "Error 404",
										JOptionPane.WARNING_MESSAGE);

							}
						} else {
							JOptionPane.showMessageDialog(frame, "El Mensaje Supera la longitud maxima (99)",
									"Error en el mensaje", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			});

			add(enviar, BorderLayout.SOUTH);
			add(mensaje, BorderLayout.NORTH);
		}
	}

	class EnvioOnline extends WindowAdapter {

		public void windowOpened(WindowEvent e) {

			try {

				Socket misocket = new Socket("192.168.4.232", 9909);
				String thisIp = InetAddress.getLocalHost().getHostAddress();
				ObjectOutputStream paquete_datos = new ObjectOutputStream(misocket.getOutputStream());
				paquete_datos.writeUTF(thisIp);

				paquete_datos.close();
				misocket.close();
			} catch (Exception e1) {
				System.out.println("peto");
			}

		}
	}

	class panelBoton extends JPanel {
		private JLabel boton;

		public panelBoton(JLabel a) {
			boton = a;
			add(boton);

		}
	}

}
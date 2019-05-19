
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class panelTest extends JPanel {
	private JLabel titulo;
	private JPanel debate;
	ArrayList<panelMensajess> panel = new ArrayList();
	gestionBase ges = new gestionBase();
	JFrame frame;
	iniciado ini;

	public panelTest(String titulos, JFrame frame, iniciado ini) {
		JScrollPane scrollPane = new JScrollPane();
		this.frame = frame;
		this.ini = ini;

		setLayout(new BorderLayout(0, 0));
		titulo = new JLabel(titulos);
		debate = new JPanel();
		debate.setLayout(new GridLayout(0, 1, 10, 10));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Dialog", Font.PLAIN, 28));
		insertarMensajes(titulos);

		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(debate);
		add(scrollPane, BorderLayout.CENTER);
		add(titulo, BorderLayout.NORTH);
		insertarBotonEnviar();
		setVisible(true);

	}

	protected void insertarBotonEnviar() {

		JButton enviar = new JButton("Enviar");
		enviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean problem = false;
				int codigo = 0;
				int numero = 0;
				String mensaje;
				for (panelMensajess p : panel) {
					mensaje = p.getRespuesta();

					if (p.noApto()) {

						if (!mensaje.equals("")) {
							numero++;
							codigo = ges.conseguirCodigoPregunta(titulo.getText(), p.getMensaje());
							ges.responder(codigo, p.getRespuesta(), ini.getCodigoCorreo());
							p.borrarRespuesta();

						}
					} else {
						problem = true;
					}

				}
				if (problem) {
					JOptionPane.showMessageDialog(frame, "Hay algunas preguntas que superan los valores maximos(99)",
							"Supera la longituz maxima", JOptionPane.WARNING_MESSAGE);
				}

				if (numero > 0) {
					JOptionPane.showMessageDialog(frame, "Gracias por participar");
				} else {
					JOptionPane.showMessageDialog(frame, "No has respondido ninguna pregunta", "Gracias Pero....",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		add(enviar, BorderLayout.SOUTH);
	}

	protected void insertarMensajes(String titulos) {
		int numero = 0;
		ArrayList<JLabel> mensajes;
		mensajes = ges.conseguirPreguntas(titulos);
		for (JLabel l : mensajes) {
			numero++;
			l.setHorizontalAlignment(SwingConstants.CENTER);
			if (numero % 2 == 0) {
				l.setForeground(Color.WHITE);
			} else {

			}
			panelMensajess p = new panelMensajess(l);
			if (numero % 2 == 0) {
				p.setBackground(Color.DARK_GRAY);
			} else {
				p.setBackground(Color.LIGHT_GRAY);
			}
			panel.add(p);
			debate.add(p);

		}
	}

}

class panelMensajess extends JPanel {

	private JLabel mensaje;
	private myTextField respuesta = new myTextField();

	public panelMensajess(JLabel m) {
		setLayout(new BorderLayout(0, 0));
		respuesta.setMaximo(99);
		this.mensaje = m;
		respuesta.setVisible(true);
		add(mensaje, BorderLayout.NORTH);
		add(respuesta, BorderLayout.SOUTH);
		setVisible(true);

	}

	protected String getMensaje() {
		return mensaje.getText();
	}

	protected void borrarRespuesta() {
		respuesta.setText("");
	}

	protected String getRespuesta() {
		return respuesta.getText();
	}

	protected boolean noApto() {
		return respuesta.superaMaximo();
	}

}

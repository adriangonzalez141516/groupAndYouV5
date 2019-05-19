import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class actualizarTest extends JPanel {
	int actual = 10;
	JPanel panelN = new JPanel();
	ArrayList<pregunta1> preguntas = new ArrayList();
	JFrame frame;
	panelNor1 n;

	public actualizarTest(JFrame frame) {
		JScrollPane scrollPane = new JScrollPane();
		this.frame = frame;
		panelN.setLayout(new GridLayout(10, 1, 10, 10));
		setBounds(100, 100, 700, 700);
		n = new panelNor1();
		setLayout(new BorderLayout(0, 0));
		add(n, BorderLayout.NORTH);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(panelN);
		add(scrollPane, BorderLayout.CENTER);
		panelSurs1 pa = new panelSurs1();
		pa.añadirAñadir();
		pa.añadirEnviar(n);
		pa.añadirBorrar();

		add(pa, BorderLayout.SOUTH);
		setVisible(false);
		setVisible(true);

	}
 
	protected void insertarTodo(String s) {
		gestionBase ges = new gestionBase();

		for (JLabel p : ges.conseguirPreguntas(s)) {
			pregunta1 a = new pregunta1();
			a.setPregunta(p.getText());
			n.setLabel(s);;
			panelN.add(a);
			preguntas.add(a);

		}

	}

	class panelNor1 extends JPanel {
		private JLabel nombre = new JLabel("Introduce el titulo");
		private myTextField res = new myTextField();

		public panelNor1() {
			setLayout(new GridLayout(1, 2, 10, 10));
			nombre.setHorizontalAlignment(SwingConstants.CENTER);
			nombre.setFont(new Font("Dialog", Font.PLAIN, 28));
			add(nombre);
			add(res);
		}

		protected String getTitulo() {
			return res.getText();

		}

		protected void setTitulo(String t) {
			res.setText(t);

		}

		protected String getLabel() {
			return nombre.getText();
		}

		protected void setLabel(String t) {
			nombre.setText(t);

		}

	}

	class panelSurs1 extends JPanel {

		protected void añadirAñadir() {
			JButton añadir = new JButton("Añadir Pregunta");
			añadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pregunta1 p = new pregunta1();
					preguntas.add(p);
					if (preguntas.size() > actual) {
						actual++;
						panelN.setLayout(new GridLayout(actual, 1, 10, 10));
					}
					panelN.add(p);
					panelN.setBackground(Color.LIGHT_GRAY);
					panelN.setVisible(false);
					panelN.setVisible(true);

				}
			});
			add(añadir);
			setVisible(false);
			setVisible(true);
		}

		protected void añadirEnviar(panelNor1 p) {

			JButton enviar = new JButton("Actualizar Test");
			enviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					gestionBase ges = new gestionBase();
					ges.actualizarPreguntas(p.getTitulo(), preguntas);

				}
			});
			add(enviar);
			setVisible(false);
			setVisible(true);
		}

		protected void añadirBorrar() {
			JButton borrar = new JButton("Reiniciar");
			borrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (preguntas.size() >= 1) {
						preguntas.remove(preguntas.size());
						panelN.remove(preguntas.size());
						actual--;
						if (actual >= 10) {
							panelN.setLayout(new GridLayout(actual, 1, 10, 10));
						}

						panelN.setVisible(false);
						panelN.setVisible(true);

					} else {
						JOptionPane.showMessageDialog(frame, "Ya no quedan mas preguntas que borrar", "Alerta",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			add(borrar);
			setVisible(false);
			setVisible(true);
		}

	}

}

class pregunta1 extends JPanel {
	private JLabel pregunta = new JLabel("Introduce una pregunta");
	private myTextField respuesta = new myTextField();

	public pregunta1() {
		setBackground(Color.DARK_GRAY);
		pregunta.setForeground(Color.WHITE);
		pregunta.setHorizontalAlignment(SwingConstants.CENTER);
		pregunta.setFont(new Font("Dialog", Font.PLAIN, 28));
		setLayout(new GridLayout(1, 1, 10, 10));
		add(pregunta);
		add(respuesta);
		setVisible(false);
		setVisible(true);
	}
	protected String pregunta() {
		return pregunta.getText();
	}
	
	protected String respuesta() {
		return respuesta.getText();
	}

	protected void setPregunta(String pregunta) {
		this.pregunta.setText(pregunta);
		;
	}

	protected void setRespuesta(String respuestas) {
		respuesta.setText(respuestas);
	}

}

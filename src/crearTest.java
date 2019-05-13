import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import org.omg.Messaging.SyncScopeHelper;

public class crearTest extends JPanel {
	int actual = 10;
	JPanel panelN = new JPanel();
	ArrayList<pregunta> preguntas = new ArrayList();
	JFrame frame;
	panelNor n;
	iniciado ini;

	public crearTest(JFrame frame, iniciado ini) {
		this.ini=ini;
		JScrollPane scrollPane = new JScrollPane();
		this.frame = frame;
		panelN.setLayout(new GridLayout(10, 1, 10, 10));
		setBounds(100, 100, 700, 700);
		n = new panelNor();
		setLayout(new BorderLayout(0, 0));
		add(n, BorderLayout.NORTH);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportView(panelN);
		add(scrollPane, BorderLayout.CENTER);
		panelSurs pa = new panelSurs();
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
			pregunta a = new pregunta();
			a.setRespuesta(p.getText());
			n.setTitulo(s);
			panelN.add(a);

		}

	}

	class panelNor extends JPanel {
		private JLabel nombre = new JLabel("Introduce el titulo");
		private JTextField res = new JTextField();

		public panelNor() {
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

	}

	class panelSurs extends JPanel {

		protected void añadirAñadir() {
			JButton añadir = new JButton("Añadir Pregunta");
			añadir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					pregunta p = new pregunta();
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

		protected void añadirEnviar(panelNor p) {

			JButton enviar = new JButton("Crear Test");
			enviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					gestionBase ges = new gestionBase();
					if (ges.crearEncuesta(p.getTitulo(), ini)) {
						ges.insertarPreguntas(p.getTitulo(), preguntas, ini);
						JOptionPane.showMessageDialog(frame, "Test Creado Con exito");
						
					} else {
						JOptionPane.showMessageDialog(frame, "EL TEST YA EXISTE", "Alerta",
								JOptionPane.WARNING_MESSAGE);
					}
					
					for (pregunta pregunta : preguntas) {
						pregunta.setRespuesta("");
					}

				}
			});
			add(enviar);
			setVisible(false);
			setVisible(true);
		}

		protected void añadirBorrar() {
			JButton borrar = new JButton("Borrar Pregunta");
			borrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (preguntas.size() >= 1) {
						preguntas.remove(preguntas.size()-1);
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

class pregunta extends JPanel {
	private JLabel pregunta = new JLabel("Introduce una pregunta");
	private JTextField respuesta = new JTextField();

	public pregunta() {
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

	protected JTextField respuesta() {
		return respuesta;
	}

	protected void setPregunta(JLabel pregunta) {
		this.pregunta = pregunta;
	}

	protected void setRespuesta(String respuestas) {
		respuesta.setText(respuestas);
	}

}

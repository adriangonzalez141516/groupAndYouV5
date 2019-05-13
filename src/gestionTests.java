import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;

public class gestionTests extends JPanel {
	JPanel panelN = new JPanel();
	JPanel panel = new JPanel();
	gestionBase ges = new gestionBase();
	ArrayList<JLabel> array;
	JFrame a;
	JPanel norte;
	iniciado ini;

	public gestionTests(JFrame a, iniciado ini) {
		this.ini = ini;
		setLayout(new BorderLayout(0, 0));
		panel.setLayout(new BorderLayout(0, 0));
		this.a = a;
		panelN.setLayout(new GridLayout(10, 1, 10, 20));
		panelN.setBackground(Color.DARK_GRAY);
		panelN.setBorder(BorderFactory.createEmptyBorder(10, 100, 10, 100));
		array = ges.conseguirTest(1);

		insertar();
		panel.add(panelN, BorderLayout.CENTER);
		add(panel, BorderLayout.CENTER);
	}

	private void insertar() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		JLabel t = new JLabel("Titulo");
		JLabel p = new JLabel("Personas");
		JLabel e = new JLabel("Eliminar");

		p.setHorizontalAlignment(SwingConstants.LEFT);
		p.setFont(new Font("Dialog", Font.PLAIN, 28));
		t.setHorizontalAlignment(SwingConstants.CENTER);
		t.setFont(new Font("Dialog", Font.PLAIN, 28));

		e.setHorizontalAlignment(SwingConstants.RIGHT);
		e.setFont(new Font("Dialog", Font.PLAIN, 28));
		panel.add(t, BorderLayout.CENTER);
		panel.add(p, BorderLayout.WEST);
		panel.add(e, BorderLayout.EAST);
		panelN.add(panel);
		panelN.setVisible(false);
		panelN.setVisible(true);
		for (JLabel o : ges.conseguirTest(ini.getCodigoCorreo())) {
			panelN.add(new panelTesta(o.getText(), a));
		}

	}

	private void insertarBotones() {
		JButton botonInsertar = new JButton("Insertar Test");
	}

	class panelTesta extends JPanel {

		private JLabel titulo;
		private JLabel personas;
		private JLabel descripcion;
		private JButton eliminar;

		public panelTesta(String t, JFrame f) {
			int numero = ges.conseguirTest(1).size();
			setLayout(new BorderLayout(0, 0));
			titulo = new JLabel(t);
			titulo.setHorizontalAlignment(SwingConstants.CENTER);
			titulo.setFont(new Font("Dialog", Font.PLAIN, 28));
			titulo.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent arg0) {
					panel.removeAll();
					actualizarTest test = new actualizarTest(f);
					test.insertarTodo(titulo.getText());
					panel.add(test, BorderLayout.CENTER);
					panel.setVisible(false);
					panel.setVisible(true);

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub

				}
			});
			eliminar = new JButton("-");
			personas = new JLabel(ges.conseguirCantidad(t) + "");
			personas.setHorizontalAlignment(SwingConstants.CENTER);
			personas.setFont(new Font("Dialog", Font.PLAIN, 28));
			add(titulo, BorderLayout.CENTER);
			add(personas, BorderLayout.WEST);
			add(eliminar, BorderLayout.EAST);
			eliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int resp = JOptionPane.showConfirmDialog(a, "¿Esta seguro?", "Alerta!", JOptionPane.YES_NO_OPTION,
							JOptionPane.ERROR_MESSAGE);
					System.out.println(resp);
					if (resp == 0) {
						ges.borrarEncuesta(t);
						panelN.removeAll();
						insertar();

						panelN.setVisible(false);
						panelN.setVisible(true);

					}
				}
			});

		}

	}

}

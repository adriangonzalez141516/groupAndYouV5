
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class panelDebates extends JPanel {
	private JPanel panelBotones = new JPanel();
	private JPanel panelDebates = new JPanel();
	private iniciado ini;
	private JFrame frame;

	public panelDebates(iniciado ini, JFrame frame) {
		this.ini = ini;
		this.frame=frame;
		panelBotones.setVisible(true);
		panelBotones.setLayout(new GridLayout(0, 1, 0, 2));
		panelDebates.setVisible(true);
		panelBotones.setBackground(Color.DARK_GRAY);
		panelDebates.setBackground(Color.pink);
		panelDebates.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		setBackground(Color.blue);
		setLayout(new BorderLayout(0, 0));
		insertarBotones();
		add(panelBotones, BorderLayout.WEST);
		add(panelDebates, BorderLayout.CENTER);
	}

	protected void insertarBotones() {
		ArrayList<JButton> botones;
		gestionBase ges = new gestionBase();
		botones = ges.conseguirBotonesDebates();

		for (JButton b : botones) {
		

			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panelDebates.removeAll();
					
					try {

						Socket misocket = new Socket("192.168.4.232", 9999);
						ObjectOutputStream paquete_datoss = new ObjectOutputStream(misocket.getOutputStream());

						paquete_datoss.writeObject(new online());

						paquete_datoss.close();
						misocket.close();

						panelDebates.add(new panelDebate(b.getText(), ini, frame), BorderLayout.CENTER);

					} catch (IOException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(frame, "EL Servidor Esta En Mantenimiento Pruebe Mas Tarde", "Error 404",
								JOptionPane.WARNING_MESSAGE);
					}
					panelDebates.setVisible(false);
					panelDebates.setVisible(true);

				}
			});

			panelBotones.add(b);

		}

	}

}

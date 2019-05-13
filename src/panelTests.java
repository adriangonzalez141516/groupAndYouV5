
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class panelTests extends JPanel {
	private JPanel panelBotones = new JPanel();
	private JPanel panelTests = new JPanel();
	private JFrame frame;
	private iniciado ini;

	public panelTests(JFrame frame, iniciado ini) {
		this.frame = frame;
		this.ini = ini;
		panelBotones.setVisible(true);
		panelBotones.setLayout(new GridLayout(0, 1, 0, 2));
		panelTests.setVisible(true);
		panelBotones.setBackground(Color.DARK_GRAY);
		panelTests.setBackground(Color.pink);
		panelTests.setLayout(new BorderLayout(0, 0));
		setVisible(true);
		setBackground(Color.blue);
		setLayout(new BorderLayout(0, 0));
		insertarBotones();
		add(panelBotones, BorderLayout.WEST);
		add(panelTests, BorderLayout.CENTER);

	}

	protected void insertarBotones() {
		ArrayList<JButton> botones;
		gestionBase ges = new gestionBase();
		botones = ges.conseguirBotonesTests();

		for (JButton b : botones) {

			b.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					panelTests.removeAll();
					panelTests.add(new panelTest(b.getText(), frame, ini), BorderLayout.CENTER);
					panelTests.setVisible(false);
					panelTests.setVisible(true);

				}
			});

			panelBotones.add(b);

		}

	}

}

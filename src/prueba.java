import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Label;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class prueba {

	private JFrame frame;
	private JTextField textField;
	private Label label_1;
	private JPasswordField passwordField;
	private JPanel panel_1;
	private JPanel panel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					prueba window = new prueba();
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
	public prueba() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_2.add(btnNewButton, BorderLayout.WEST);
		
		JButton btnNewButton_1 = new JButton("Olvide contrase\u00F1a");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_2.add(btnNewButton_1, BorderLayout.EAST);
		
	}
}

class Imagen extends javax.swing.JPanel {

	public Imagen() {
		this.setSize(500, 500); // se selecciona el tamaño del panel
	}

//Se crea un método cuyo parámetro debe ser un objeto Graphics

	public void paint(Graphics grafico) {
		Dimension height = getSize();

//Se selecciona la imagen que tenemos en el paquete de la //ruta del programa

		ImageIcon Img = new ImageIcon(getClass().getResource("logo.png"));

//se dibuja la imagen que tenemos en el paquete Images //dentro de un panel

		grafico.drawImage(Img.getImage(), 140, 50, 200, 200, null);

		setOpaque(false);
		super.paintComponent(grafico);
	}
}
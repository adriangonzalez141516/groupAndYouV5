package groupAndYouV5;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JPasswordField;

public class prueba {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JPasswordField passwordField;
	private JTextField textField_10;

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
		frame.setBounds(100, 100, 700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		
		JButton btnNewButton = new JButton("New button");
		panel_1.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(38, 77, 78, 16);
		panel_2.add(lblNewLabel);
		
		textField_6 = new JTextField();
		textField_6.setEnabled(false);
		textField_6.setBounds(124, 77, 116, 22);
		panel_2.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblApellidos.setBounds(258, 74, 80, 22);
		panel_2.add(lblApellidos);
		
		textField_7 = new JTextField();
		textField_7.setEnabled(false);
		textField_7.setBounds(350, 77, 116, 22);
		panel_2.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNif = new JLabel("NIF");
		lblNif.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNif.setBounds(478, 80, 56, 16);
		panel_2.add(lblNif);
		
		textField_8 = new JTextField();
		textField_8.setEnabled(false);
		textField_8.setBounds(524, 77, 116, 22);
		panel_2.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblCorreoElectronico = new JLabel("Correo");
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorreoElectronico.setBounds(192, 136, 67, 16);
		panel_2.add(lblCorreoElectronico);
		
		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setBounds(271, 136, 88, 22);
		panel_2.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(371, 136, 108, 16);
		panel_2.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setEnabled(false);
		passwordField.setBounds(491, 136, 56, 22);
		panel_2.add(passwordField);
		
		JButton btnEliminarCuenta = new JButton("Eliminar Cuenta");
		btnEliminarCuenta.setBounds(0, 593, 682, 25);
		panel_2.add(btnEliminarCuenta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(572, 135, 85, 25);
		panel_2.add(btnModificar);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUsuario.setBounds(34, 139, 78, 16);
		panel_2.add(lblUsuario);
		
		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setBounds(113, 136, 67, 22);
		panel_2.add(textField_10);
		textField_10.setColumns(10);
		
		
	}
}

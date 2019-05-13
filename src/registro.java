import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class registro extends JPanel {

	private JTextField txtNombre;
	private JPasswordField txtCorreo;

	public registro() {
		setLayout(new BorderLayout(0,0));
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("INICIO DE SESION");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBounds(158, 26, 151, 29);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nombre de usuario:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_1.setBounds(38, 73, 129, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Correo electronico:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(38, 128, 129, 14);
		panel.add(lblNewLabel_2);

		txtNombre = new JTextField();
		txtNombre.setBounds(177, 70, 175, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtCorreo = new JPasswordField();
		txtCorreo.setBounds(177, 125, 175, 20);
		panel.add(txtCorreo);

		JButton btnEntrar = new JButton("Entrar");
		btnEntrar.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				metodos m = new metodos();
				String busqueda_usuario = m.buscarUsuario(txtNombre.getText(), txtCorreo.getText());

				if (txtNombre.getText().equals("root") && txtCorreo.getText().equals("root")) {
					JOptionPane.showMessageDialog(null, "Bienvenido iniciaste sesion como administrador");

				} else if (busqueda_usuario.equals("Usuario encontrado")) {
					String busqueda_nombre = m.buscarNombre(txtCorreo.getText());
					JOptionPane.showMessageDialog(null, "Bienvenid@ \n" + busqueda_nombre);
					sistema ventana = new sistema();

				} else {
					JOptionPane.showMessageDialog(null, "Por favor dese de alta");
				}
			}
		});
		btnEntrar.setBounds(67, 184, 89, 23);
		panel.add(btnEntrar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed1(ActionEvent e) {

			}

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(245, 184, 89, 23);
		panel.add(btnCancelar);
		add(panel, BorderLayout.CENTER);
	}
}

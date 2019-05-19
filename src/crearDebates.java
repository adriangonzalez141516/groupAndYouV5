import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class crearDebates extends JPanel {
	private myTextField txtNombre;
	private myTextField textDebate;
	private JComboBox cbEdad;
	private iniciado ini;

	public crearDebates(iniciado ini, JFrame frame) {
		gestionBase ges = new gestionBase();
		this.ini = ini;
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NOMBRE:");
		lblNewLabel.setBounds(128, 69, 111, 14);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("INSERTAR TEMA DE DEBATE:");
		lblNewLabel_1.setBounds(116, 172, 188, 14);
		add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("ERES MAYOR DE EDAD:");
		lblNewLabel_2.setBounds(114, 123, 159, 14);
		add(lblNewLabel_2);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.setBounds(310, 305, 89, 23);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				debate debate = new debate(textDebate.getText(),
						comprobarStringBooleano(cbEdad.getSelectedItem().toString()), txtNombre.getText(),
						ini.getCodigoCorreo());
				debate.setPuerto(ges.getPuerto() + 1);
				if (txtNombre.superaMaximo()) {
					if (textDebate.superaMaximo()) {
						ges.crearDebate(debate);
					} else {
						JOptionPane.showMessageDialog(frame, "El nombre del tema supera el maximo(14)", "Alerta",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "El nombre del debate supera el maximo(19)", "Alerta",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		add(btnAceptar);

		txtNombre = new myTextField();
		txtNombre.setBounds(343, 66, 149, 20);
		txtNombre.setMaximo(19);
	
		add(txtNombre);
		txtNombre.setColumns(10);

		textDebate = new myTextField();
		textDebate.setBounds(343, 167, 149, 23);
		textDebate.setMaximo(14);
		add(textDebate);

		JLabel lblNewLabel_3 = new JLabel("DEBATES");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel_3.setBounds(280, 22, 149, 14);
		add(lblNewLabel_3);

		cbEdad = new JComboBox();
		cbEdad.setModel(new DefaultComboBoxModel(new String[] { "No", "Si" }));
		cbEdad.setBounds(343, 120, 149, 20);
		add(cbEdad);

		setVisible(true);

	}

	private boolean comprobarStringBooleano(String siono) {
		boolean hola;

		if (siono.equalsIgnoreCase("si")) {
			hola = true;
		} else {
			hola = false;
		}

		return hola;
	}

}

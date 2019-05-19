import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class registro extends JPanel {

	private myTextField textField;
	private myTextField textField_1;
	private myTextField textField_2;
	private myTextField textField_3;
	private myTextField textField_4;
	private JPasswordField textField_5;

	private JFrame frame;
	private JPanel panela;
	private JPanel panel = new JPanel();

	public registro(JFrame frame, JPanel panela) {
		this.panela = panela;
		gestionBase ges = new gestionBase();
		setLayout(new BorderLayout(0, 0));

		panel.setLayout(null);
		imagen imagen = new imagen();
		imagen.setLocation(80, 0);
		imagen.setSize(500, 250);
		panel.add(imagen);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(67, 315, 107, 43);
		panel.add(lblNewLabel);

		textField = new myTextField();
		textField.setMaximo(14);

		textField.setBounds(186, 328, 116, 22);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Apellidos");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(326, 323, 116, 27);
		panel.add(lblNewLabel_1);

		textField_1 = new myTextField();
		textField_1.setMaximo(19);
		textField_1.setBounds(447, 328, 116, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Usuario");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(67, 390, 88, 16);
		panel.add(lblNewLabel_2);

		textField_2 = new myTextField();
		textField_2.setMaximo(9);
		textField_2.setBounds(186, 390, 116, 22);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblDni = new JLabel("Dni");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDni.setBounds(379, 390, 56, 16);
		panel.add(lblDni);

		textField_3 = new myTextField();
		textField_3.setMaximo(19);
		textField_3.setBounds(447, 390, 116, 22);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblCorreoElectronico = new JLabel("Correo Electronico");
		lblCorreoElectronico.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblCorreoElectronico.setBounds(46, 458, 162, 16);
		panel.add(lblCorreoElectronico);

		textField_4 = new myTextField();
		textField_4.setMaximo(29);
		textField_4.setBounds(220, 458, 82, 22);
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Contrase\u00F1a");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(348, 458, 116, 16);
		panel.add(lblNewLabel_3);

		textField_5 = new JPasswordField();
		textField_5.setBounds(457, 458, 106, 22);
		panel.add(textField_5);
		textField_5.setColumns(10);

		JButton btnNewButton = new JButton("Registrarse");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (textField.superaMaximo()) {

					if (!textField.getText().equals("")) {

						if (textField_1.superaMaximo()) {
							if (!textField_1.getText().equals("")) {

								if (textField_2.superaMaximo()) {
									if (!textField_2.getText().equals("")) {
										if (!ges.usuarioRegistrado(textField_2.getText())) {

											if (textField_3.superaMaximo()) {
												if (!textField_3.getText().equals("")) {

													if (validarNIF(textField_3.getText())) {

														if (!ges.nifRegistrado(textField_3.getText())) {

															if (textField_5.getText().length() > 5) {
																if (!textField_5.getText().equals("")) {
																	if (textField_4.superaMaximo()) {

																		if (!textField_4.getText().equals("")) {

																			if (ges.comprobarCorreo(
																					textField_4.getText())) {
																				if (!ges.correoRegistrado(
																						textField_4.getText())) {

																					iniciado ini = new iniciado();
																					ini.setNombre(textField.getText());
																					ini.setApellidos(
																							textField_1.getText());
																					ini.setUsuario(
																							textField_2.getText());
																					ini.setNif(textField_3.getText());
																					ini.setCorreo(
																							textField_4.getText());
																					ini.setContraseña(
																							textField_5.getText());
																					correo cor = new correo(
																							textField_4.getText(),
																							frame, panel, "registro",
																							ini);
																					cor.start();

																					textField.setText("");
																					textField_1.setText("");
																					textField_2.setText("");
																					textField_3.setText("");
																					textField_4.setText("");
																					textField_5.setText("");

																				} else {
																					textField_4.requestFocus();
																					JOptionPane.showMessageDialog(frame,
																							"Este correo ya esta registrado",
																							"Error",
																							JOptionPane.WARNING_MESSAGE);
																				}

																			} else {
																				textField_4.requestFocus();
																				JOptionPane.showMessageDialog(frame,
																						"Se supone que esto es un correo?",
																						"Error",
																						JOptionPane.WARNING_MESSAGE);
																			}

																		} else {
																			textField_4.requestFocus();
																			JOptionPane.showMessageDialog(frame,
																					"Completa el campo", "Error",
																					JOptionPane.WARNING_MESSAGE);

																		}
																	} else {
																		textField_4.requestFocus();
																		JOptionPane.showMessageDialog(frame,
																				"El correo electronico tiene un maximo de 29 caracteres",
																				"Error", JOptionPane.WARNING_MESSAGE);
																	}
																} else {
																	textField_5.requestFocus();
																	JOptionPane.showMessageDialog(frame,
																			"Completa el campo", "Error",
																			JOptionPane.WARNING_MESSAGE);
																}
															} else {

																textField_4.requestFocus();
																JOptionPane.showMessageDialog(frame,
																		"El correo electronico dni ya estaba registrado",
																		"Error", JOptionPane.WARNING_MESSAGE);

															}
														} else {
															textField_5.requestFocus();
															JOptionPane.showMessageDialog(frame,
																	"El dni ya esta registrado", "Error",
																	JOptionPane.WARNING_MESSAGE);
														}

													} else {
														textField_3.requestFocus();
														JOptionPane.showMessageDialog(frame,
																"Se supone que esto es un NIf?", "Error",
																JOptionPane.WARNING_MESSAGE);
													}

												} else {
													textField_3.requestFocus();
													JOptionPane.showMessageDialog(frame, "Completa el campo", "Error",
															JOptionPane.WARNING_MESSAGE);
												}
											} else {
												textField_3.requestFocus();
												JOptionPane.showMessageDialog(frame,
														"Lso documentos de identidad tienen un maximo de 19 caracteres	",
														"Error", JOptionPane.WARNING_MESSAGE);
											}
										} else {
											textField_2.requestFocus();
											JOptionPane.showMessageDialog(frame, "Este usuario ya esta registrado",
													"Error", JOptionPane.WARNING_MESSAGE);
										}
									} else {
										textField_2.requestFocus();
										JOptionPane.showMessageDialog(frame, "Completa el campo", "Error",
												JOptionPane.WARNING_MESSAGE);
									}

								} else {
									textField_2.requestFocus();
									JOptionPane.showMessageDialog(frame,
											"El nombre de usuario tienen un maximo de 9 caracteres", "Error",
											JOptionPane.WARNING_MESSAGE);
								}
							} else {
								textField_1.requestFocus();
								JOptionPane.showMessageDialog(frame, "Completa el campo", "Error",
										JOptionPane.WARNING_MESSAGE);
							}
						} else {
							textField_1.requestFocus();
							JOptionPane.showMessageDialog(frame, "Los apellidos tienen un maximo de 19 caracteres",
									"Error", JOptionPane.WARNING_MESSAGE);
						}
					} else {
						textField.requestFocus();
						JOptionPane.showMessageDialog(frame, "Completa el campo", "Error", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					textField.requestFocus();
					JOptionPane.showMessageDialog(frame, "El nombre tiene un maximo de 14 caracteres", "Error",
							JOptionPane.WARNING_MESSAGE);
				}

			}
		});
		btnNewButton.setBounds(447, 503, 116, 25);
		panel.add(btnNewButton);
		JButton atras = new JButton("<<------");
		atras.setBounds(321, 503, 116, 25);
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				removeAll();
				add(panela, BorderLayout.CENTER);
				setVisible(false);
				setVisible(true);
			}
		});
		panel.add(atras);
		panel.setVisible(true);
		add(panel, BorderLayout.CENTER);
	}

	private boolean validarNIF(String nif) {

		boolean correcto = false;

		Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");

		Matcher matcher = pattern.matcher(nif);

		if (matcher.matches()) {

			String letra = matcher.group(2);

			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";

			int index = Integer.parseInt(matcher.group(1));

			index = index % 23;

			String reference = letras.substring(index, index + 1);

			if (reference.equalsIgnoreCase(letra)) {

				correcto = true;

			} else {

				correcto = false;

			}

		} else {

			correcto = false;

		}

		return correcto;

	}
}

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;

import javax.imageio.ImageIO;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.*;

import rojerusan.componentes.RSProgressCircleAnimated;
import rojerusan.componentes.RSProgressMaterial;

class correo extends Thread {

	private String asunto = "Bienvendio al equipo";
	private String mensaje = "Estamos encantados de contar contigo en nuestra aplicacion";
	private JFrame frame;
	private JPanel panel;
	private String destinatario;
	private iniciado ini = new iniciado();
	private String dondeestoy;
	private RSProgressCircleAnimated barra;

	public correo(String asunto, String mensaje, String destinatario, JFrame frame, String dondeestoy) {
		this.asunto = asunto;
		this.mensaje = mensaje;
		this.frame = frame;
		this.destinatario = destinatario;
		this.dondeestoy = dondeestoy;

	}

	public boolean correo() {
		boolean esta = false;
		Properties prop = new Properties();
		prop.setProperty("mail.pop3.starttls.enable", "false");
		prop.setProperty("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		prop.setProperty("mail.pop3.socketFactory.fallback", "false");
		prop.setProperty("mail.pop3.port", "995");
		prop.setProperty("mail.pop3.socketFactory.port", "995");
		Session sesion = Session.getInstance(prop);
		// sesion.setDebug(true);

		try {
			// Se obtiene el Store y el Folder, para poder leer el
			// correo.
			Store store = sesion.getStore("pop3");
			store.connect("pop.ionos.es", "info@groupandyou.club", "Bidifut1!");
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);

			// Se obtienen los mensajes.
			Message[] mensajess = folder.getMessages();

			int p = 0;
			for (Message message : mensajess) {
				if (message.getSubject().equals("Mail delivery failed: returning message to sender")) {
					p++;
				}
			}
			Message[] mensajes = new Message[p];
			int k = 0;

			for (Message message : mensajess) {
				if (message.getSubject().equals("Mail delivery failed: returning message to sender")) {
					System.out.println(message.getSubject());
					mensajes[k] = message;
					k++;
				}
			}
			// Se escribe from y subject de cada mensaje
			for (int i = 0; i < mensajes.length; i++) {

				if (analizaParteDeMensaje(mensajes[i])) {
					esta = true;
				}
			}

			folder.close(false);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return esta;
	}

	private boolean analizaParteDeMensaje(Part unaParte) {
		boolean esta = true;
		try {
			// Si es multipart, se analiza cada una de sus partes recursivamente.
			if (unaParte.isMimeType("multipart/*")) {
				Multipart multi;
				multi = (Multipart) unaParte.getContent();

				for (int j = 0; j < multi.getCount(); j++) {
			
					analizaParteDeMensaje(multi.getBodyPart(j));
				}
			} else {
				// Si es texto, se escribe el texto.
				if (unaParte.isMimeType("text/*")) {
					System.out.println("fghjk");
					if (((String) unaParte.getContent()).indexOf(destinatario) != -1) {
						System.out.println("joder tio no esta en este");
						esta = false;
					}else {
						
					}
				} else {
					// Si es imagen, se guarda en fichero y se visualiza en JFrame
					if (unaParte.isMimeType("image/*")) {

						salvaImagenEnFichero(unaParte);
						visualizaImagenEnJFrame(unaParte);
					} else {
						// Si no es ninguna de las anteriores, se escribe en pantalla
						// el tipo.
						System.out.println("Recibido " + unaParte.getContentType());
						System.out.println("---------------------------------");
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return esta;
	}

	private static void salvaImagenEnFichero(Part unaParte)
			throws FileNotFoundException, MessagingException, IOException {
		FileOutputStream fichero = new FileOutputStream("d:/" + unaParte.getFileName());
		InputStream imagen = unaParte.getInputStream();
		byte[] bytes = new byte[1000];
		int leidos = 0;

		while ((leidos = imagen.read(bytes)) > 0) {
			fichero.write(bytes, 0, leidos);
		}
	}

	/**
	 * Presupone que unaParte es una foto adjunta a un correo. Recoge la imagen y la
	 * visualiza en un JFrame
	 *
	 * @param unaParte Parte de un correo correspondiente a una imagen.
	 *
	 * @throws IOException
	 * @throws MessagingException
	 */
	private static void visualizaImagenEnJFrame(Part unaParte) throws IOException, MessagingException {
		JFrame v = new JFrame();
		ImageIcon icono = new ImageIcon(ImageIO.read(unaParte.getInputStream()));
		JLabel l = new JLabel(icono);
		v.getContentPane().add(l);
		v.pack();
		v.setVisible(true);
	}

	public void run() {

		gestionBase ges = new gestionBase();
		String contraseña;
		String remitente = "info@groupandyou.club"; // Para la dirección nomcuenta@gmail.com
		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.ionos.es"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "Bidifut1!"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario); // pongo el puto correo
			System.out.println(destinatario + " ha este correo se le ha enviado");
			message.setSubject(asunto);
			message.setText(mensaje);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.ionos.es", remitente, "Bidifut1!");
			transport.sendMessage(message, message.getAllRecipients());
			JOptionPane.showMessageDialog(frame, "Enviado con Exito");
			transport.close();
		} catch (MessagingException me) {
			JOptionPane.showMessageDialog(frame, "Este correo no esta registrado",
					"Error de autentificacion", JOptionPane.WARNING_MESSAGE);

		}

	
	}
}
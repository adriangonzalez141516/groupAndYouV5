
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class servidor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FrameServidor ser = new FrameServidor();

		ser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}

class FrameServidor extends JFrame implements Runnable {

	private ArrayList<String> online = new ArrayList();
	int posicion = -1;
	private Conexion conexion;
	private Connection con;
	private JTextArea areatexto;
	private Statement st;

	public FrameServidor() {

		setBounds(1200, 300, 280, 350);

		JPanel milamina = new JPanel();

		add(milamina);

		setVisible(true);
		Thread mihilo = new Thread(this);
		mihilo.start();

	}

	public void run() {
		try {

			ServerSocket servidor_cliente = new ServerSocket(9999);

			while (true) {

				Socket cliente = servidor_cliente.accept();
				ObjectInputStream flujoentrada = new ObjectInputStream(cliente.getInputStream());

				Object m = flujoentrada.readObject();

				if (m instanceof mensaje) {
					mensaje menaje = (mensaje) m;

					if (menaje.getMesaje() == null) {

						InetAddress localizacion = cliente.getInetAddress();
						System.out.println("beinvenido: " + localizacion);
						if (!esta(localizacion.getHostAddress())) {
							online.add(localizacion.getHostAddress());
						}
					} else {

						System.out.println(menaje.getMesaje().getText());
						posicion = -1;
						for (String e : online) {
							System.out.println(e);
							posicion++;

							Socket envia_destinatario = new Socket(e, menaje.getPuerto());
							ObjectOutputStream paqueteReenvio = new ObjectOutputStream(
									envia_destinatario.getOutputStream());
							paqueteReenvio.writeObject(menaje);
							paqueteReenvio.close();
							envia_destinatario.close();
						}

					}
				}else {
					JOptionPane.showInputDialog("estoy arrancado te lo juro");
				}
			}
		} catch (Exception e) {
			if (!online.isEmpty()) {
				online.remove(posicion);
			}
		}

	}

	protected boolean esta(String a) {
		boolean esta = false;
		for (String e : online) {
			if (e.equalsIgnoreCase(a)) {
				esta = true;
			}
		}
		return esta;
	}
}

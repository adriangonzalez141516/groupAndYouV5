import java.awt.*;
import java.awt.TrayIcon.MessageType;

import javax.swing.JOptionPane;

public class notificar {


	public void displayTray(String mensaje) throws AWTException, java.net.MalformedURLException {
		// Obtain only one instance of the SystemTray object
		SystemTray tray = SystemTray.getSystemTray();

		// If the icon is a file
		Image image = Toolkit.getDefaultToolkit().createImage("icono.png");
		// Alternative (if the icon is on the classpath):
		// Image image =
		// Toolkit.getToolkit().createImage(getClass().getResource("icon.png"));
		TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
		// Let the system resizes the image if needed
		trayIcon.setImageAutoSize(true);
		// Set tooltip text for the tray icon
		trayIcon.setToolTip("System tray icon demo");

		tray.add(trayIcon);
		trayIcon.displayMessage("GroupAndYou", mensaje, MessageType.INFO);
	}
}
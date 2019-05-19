import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;

class imagen extends javax.swing.JPanel {

		public imagen() {
			this.setSize(300, 300); // se selecciona el tamaño del panel
		}

		// Se crea un método cuyo parámetro debe ser un objeto Graphics

		public void paint(Graphics grafico) {
			Dimension height = getSize();

			// Se selecciona la imagen que tenemos en el paquete de la //ruta del programa

			ImageIcon Img = new ImageIcon(getClass().getResource("logo.png"));

			// se dibuja la imagen que tenemos en el paquete Images //dentro de un panel

			grafico.drawImage(Img.getImage(), 140, 20, 200, 200, null);

			setOpaque(false);
			super.paintComponent(grafico);
		}

	}
import java.io.Serializable;

import javax.swing.JLabel;

public class mensaje implements Serializable {
	private String persona;
	private String debate;
	private int puerto;

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public String getDebate() {
		return debate;
	}

	public void setDebate(String debate) {
		this.debate = debate;
	}

	private JLabel mesaje;

	public mensaje(JLabel mesaje, String persona) {

		this.mesaje = mesaje;
	
		this.persona = persona;
	}

	public JLabel getMesaje() {
		return mesaje;
	}

	public void setMesaje(JLabel mesaje) {
		this.mesaje = mesaje;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

}

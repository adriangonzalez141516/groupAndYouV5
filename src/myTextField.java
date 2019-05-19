
import javax.swing.JTextField;

public class myTextField extends JTextField {
	private int maximo;

	protected void setMaximo(int maximo) {
		this.maximo = maximo;
	}

	protected boolean superaMaximo() {
		boolean booleano = true;
		if (getText().length() > maximo) {
			booleano = false;
		}

		return booleano;

	}

}

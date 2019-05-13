
public class debate {

	private String tema;
	private boolean mayor;
	private String nombre;
	private int codigoCreador;
	private int puerto;

	
	
	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public debate(String tema, boolean mayor, String nombre, int codigoCreador) {
		super();
		this.tema = tema;
		this.mayor = mayor;
		this.nombre = nombre;
		this.codigoCreador = codigoCreador;
	}

	public int getCodigoCreador() {
		return codigoCreador;
	}

	public void setCodigoCreador(int codigoCreador) {
		this.codigoCreador = codigoCreador;
	}

	

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public boolean isMayor() {
		return mayor;
	}

	public void setMayor(boolean mayor) {
		this.mayor = mayor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

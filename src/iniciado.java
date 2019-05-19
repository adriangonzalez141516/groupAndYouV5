public class iniciado {
	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public int getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(int codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	private String correo;
	private String contraseña;
	private String nombre;
	private String apellidos;
	private String nif;
	private String usuario;
	private int codigoUsuario;

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public int getCodigoCorreo() {
		return codigoUsuario;
	}

	public void setCodigoCorreo(int codigoCorreo) {
		this.codigoUsuario = codigoCorreo;
	}
	public iniciado() {
		
	}
	public iniciado(String correo, String usuario, int codigoUsuario) {
		this.correo = correo;
		this.usuario = usuario;
		this.codigoUsuario = codigoUsuario;
	}

}


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class metodos {

	public java.sql.PreparedStatement sentencia_preparada;
	public ResultSet resultado;
	private String sql;
	private int resultado_numero = 0;
	private Conexion conexion;

public int guardar(String nombreUsuario,String correoElectronico) {
	int resultado=0;
	
	conexion=new Conexion();
	Connection con=conexion.getConnection();
	String sentencia_guardar=("INSERT INTO persona(nombreUsuario,correoElectronico) VALUES(,)");
	try {
		
		sentencia_preparada=con.prepareStatement(sentencia_guardar);
		sentencia_preparada.setString(1, nombreUsuario);
		sentencia_preparada.setString(2, correoElectronico);
		resultado=sentencia_preparada.executeUpdate();
		sentencia_preparada.close();
		con.close();
	}catch(Exception e) {
		System.out.println(e);
		
	}
	
	return resultado;

}

	public String buscarNombre(String correoElectronico) {
		String busqueda_nombre = null;

		try {
			conexion=new Conexion();
			Connection con=conexion.getConnection();
			String sentencia_buscar = ("SELECT nombreUsuario FROM persona WHERE correoElectronico='"
					+ correoElectronico);
			sentencia_preparada = con.prepareStatement(sentencia_buscar);
			resultado = sentencia_preparada.executeQuery();
			if (resultado.next()) {
				String nombre = resultado.getString("nombreUsuario");

			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return busqueda_nombre;
	}

	public String buscarUsuario(String nombreUsuario, String correoElectronico) {
		String busqueda_usuario = null;

		try {
			conexion=new Conexion();
			Connection con=conexion.getConnection();
			String sentencia_buscar_usuario = ("SELECT nombreUsuario,correoElectronico FROM persona WHERE correoElectronico='"
					+ correoElectronico + "'&& combreUsuario='" + nombreUsuario);
			sentencia_preparada = con.prepareStatement(sentencia_buscar_usuario);
			resultado = sentencia_preparada.executeQuery();
			if (resultado.next()) {
				busqueda_usuario = "Usuario encontrado";
			} else {
				busqueda_usuario = "Usuario no encontrado";
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return busqueda_usuario;
	}

}

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;


public class Conexion {
	
	private String servidor = "jdbc:mysql://localhost/trabajo3v3";
	private String user ="root";
	private String pass = "";
	private String driver = "com.mysql.jdbc.Driver";
	private Connection conexion;
	
	public Conexion() {
		try {
			Class.forName(driver);

			conexion = DriverManager.getConnection(servidor, user, pass);
			
		} catch (ClassNotFoundException | SQLException e){
			System.out.println("conexion fallida");
		}
	}
	public Connection getConnection() {
		return conexion;
	}

}
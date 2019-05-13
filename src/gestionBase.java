
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.*;

public class gestionBase {

	private Conexion conexion;

	/*
	 * public void insertar(String pregunta, String respuesta, String titulo) {
	 * conexion = new Conexion(); Connection con = conexion.getConnection();
	 * Statement st; String sql =
	 * "INSERT INTO `preguntas`(`pregunta`, `respuesta`,`encuesta`) VALUES ('" +
	 * pregunta + "','" + respuesta + "', '" + titulo + "')"; try { st =
	 * con.createStatement(); st.executeUpdate(sql); con.close(); st.close();
	 * System.out.println("bien");
	 * 
	 * } catch (SQLException e) { System.out.println("mal"); e.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */

	protected int conseguirCodigoPregunta(String titulo, String pregunta) {
		int numero = 0;
		int codigo = 0;
		try {
			numero = buscarEncuestas(titulo);
			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM `preguntas` WHERE `codigoTest_Aux` =" + numero
					+ " and `pregunta`='" + pregunta + "'");
			if (rs.next()) {
				codigo = rs.getInt("codigoPregunta");
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return codigo;

	}

	protected iniciado conseguirIniciado(String usuario, boolean escorreo, String contraseña) {
		iniciado ini = null;
		String sql;
		try {

			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();
			if (escorreo) {
				sql = "SELECT * FROM `persona` WHERE `correoElectronico`='" + usuario + "' and `contraseña`='"
						+ contraseña + "'";
			} else {
				sql = "SELECT * FROM `persona` WHERE `nombreUsuario`='" + usuario + "' and `contraseña`='" + contraseña
						+ "'";
			}

			ResultSet rs = st.executeQuery(sql);

			while (rs.next()) {
				System.out.println(rs.getString("correoElectronico"));
				ini = new iniciado(rs.getString("correoElectronico"), rs.getString("nombreUsuario"),
						rs.getInt("codigoPersona"));
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ini;

	}

	protected iniciado conseguirPersona(int codigo) {
		iniciado ini = null;

		try {

			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM `persona` WHERE `codigoPersona`=" + codigo + "");
			while (rs.next()) {
				ini = new iniciado(rs.getString("correoElectronico"), rs.getString("nombreUsuario"),
						rs.getInt("codigoPersona"));
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ini;

	}

	protected void responder(int codigo, String respuesta, int codigoIniciado) {
		conexion = new Conexion();
		boolean puede = true;
		Connection con = conexion.getConnection();
		Statement st;

		puede = true;
		String sql = "INSERT INTO `respuesta`(`codigoPersona_Aux`, `codigoPregunta_Aux`, `respuesta`) VALUES ("
				+ codigoIniciado + "," + codigo + ",'" + respuesta + "')";
		try {
			st = con.createStatement();
			st.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("mal");
			e.printStackTrace();

		}
	}

	protected boolean enviarRespuesta(int codigo, String titulo, String mensaje) {
		conexion = new Conexion();
		boolean puede = true;
		Connection con = conexion.getConnection();
		Statement st;
		int numero = buscarDebate(titulo);
		if (numero != 0) {
			puede = true;
			String sql = "INSERT INTO `debatir`(`codigoPersona_Aux`, `codigoDebate_Aux`, `mensaje`) VALUES (" + codigo
					+ ", " + numero + ",'" + mensaje + "')";
			System.out.println(sql);
			try {
				st = con.createStatement();
				st.executeUpdate(sql);

				st.close();
				con.close();
			} catch (SQLException e) {
				System.out.println("mal");
				e.printStackTrace();

			}
		} else {
			puede = false;
		}

		return puede;

	}

	protected void crearDebate(debate debate) {
		conexion = new Conexion();
		Connection con = conexion.getConnection();
		Statement st;

		String sql = "INSERT INTO `debates`( `nombreDebate`, `codigoPersona_Aux`, `puerto`, `tema`) VALUES ('"
				+ debate.getNombre() + "'," + debate.getCodigoCreador() + "," + debate.getPuerto() + ",'"
				+ debate.getTema() + "')";
		System.out.println(sql);
		try {
			st = con.createStatement();
			st.executeUpdate(sql);

			st.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("mal");
			e.printStackTrace();

		}


	}

	protected int getPuerto() {
		int puerto = 0;
		try {

			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT `puerto` FROM `debates` order by `codigoDebate` desc limit 1");
			while (rs.next()) {
				puerto = rs.getInt("puerto");
			}
			rs.close();
			st.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return puerto;
	}

	protected boolean crearEncuesta(String titulo, iniciado ini) {
		conexion = new Conexion();
		boolean puede = true;
		Connection con = conexion.getConnection();
		Statement st;
		int numero = buscarEncuestas(titulo);
		if (numero == 0) {
			puede = true;
			String sql = "INSERT INTO `tests`(`nombre`, `codigoPersona_Aux`) VALUES ('" + titulo + "', "
					+ ini.getCodigoCorreo() + ")";
			try {
				st = con.createStatement();
				st.executeUpdate(sql);

				st.close();
				con.close();

			} catch (SQLException e) {
				System.out.println("mal");
				e.printStackTrace();

			}
		} else {
			puede = false;
		}

		return puede;

	}

	protected void borrarEncuesta(String titulo) {

		conexion = new Conexion();

		Connection con = conexion.getConnection();
		Statement st;

		borrarPregun(titulo);
		String sql = "DELETE FROM `tests` WHERE nombre='" + titulo + "'";
		System.out.println(sql);
		try {
			st = con.createStatement();
			st.executeUpdate(sql);

			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("mal");
			e.printStackTrace();

		}

	}

	protected void borrarPregun(String titulo) {
		conexion = new Conexion();

		Connection con = conexion.getConnection();
		Statement st;
		int numero = buscarEncuestas(titulo);
		System.out.println(numero);

		String sql = "DELETE FROM `preguntas` WHERE `codigoTest_Aux`=" + numero;
		System.out.println(sql);
		try {
			st = con.createStatement();
			st.executeUpdate(sql);

			st.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("mal");
			e.printStackTrace();

		}

	}

	protected int conseguirCantidad(String titulo) {
		int numero = 0;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM tests");
			while (rs.next()) {

				if (rs.getString("nombre").equalsIgnoreCase(titulo)) {
					numero = rs.getInt("vecesRealizadas");
				}
			}
			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return numero;

	}

	protected void insertarPreguntas(String titulo, ArrayList<pregunta> preguntas, iniciado ini) {
		conexion = new Conexion();
		Connection con = conexion.getConnection();
		Statement st = null;
		int numero = buscarEncuestas(titulo);

		for (pregunta e : preguntas) {
			if (!e.respuesta().getText().equals("")) {
				String sql = "INSERT INTO `preguntas`(`pregunta`, `codigoTest_Aux`, `codigoPersona_Aux`) VALUES ('"
						+ e.respuesta().getText() + "'," + numero + "," + ini.getCodigoCorreo() + ")";
				try {
					st = con.createStatement();
					st.executeUpdate(sql);

				} catch (SQLException i) {
					System.out.println("mal");
					i.printStackTrace();

				}
			}

		}

	}

	protected void actualizarPreguntas(String titulo, ArrayList<pregunta1> preguntas) {
		conexion = new Conexion();
		Connection con = conexion.getConnection();
		Statement st = null;
		int numero = buscarEncuestas(titulo);

		for (pregunta1 e : preguntas) {
			if (!e.respuesta().equals("")) {
				String sql = "UPDATE `preguntas` SET `pregunta`='" + e.respuesta() + "' WHERE `pregunta`='"
						+ e.pregunta() + "'";
				System.out.println(sql);
				try {
					st = con.createStatement();
					st.executeUpdate(sql);

				} catch (SQLException i) {
					System.out.println("mal");
					i.printStackTrace();

				}
			}
		}
		try {
			st.close();
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}/*
		 * describe el corpontamiento del sistema desde el punto de vista de un usuario
		 * que interactua con el
		 * 
		 * --Requisitos funcionales y operativos del sistema, diseñando un conjunto de
		 * escenarios que faciliten una descripcion de como se usara el sistema
		 * --descripcion clara de como el usuario interactua con el sistema y viceberas
		 * -- proporcionar una base para la validacion de pruebas -Elemenostos de el
		 * diagrama del caso de uso --Actores: cualquier cosa que interactua con el
		 * sistema --Casos de uso: representan una unidad funcional de sistema que se
		 * realiza tras una orden del actor (elipse) --Relaciones: identifican el tipo
		 * de relacion que tiene el actorcon el caso de uso
		 *
		 * Identificar actores: que y quien iteractura con el sistema y que Rol
		 * desempeña: -Los actores son siempre externos al sistema - Interctuan
		 * directamente con el sistema -representan roles en relacion al sistema -la
		 * misma persona u elemento pueden interpretar varios roles como actores
		 * distintos Como identificar casos de uso: es necesiario entender lo que el
		 * sistema debe hacer, identificando los actores que usaran el sistema y como
		 * para esto nos planteamos una serie de preguntas. -Que tarea hacen los actores
		 * -Que informacion crea,almacena,modifica,destruye o lee el actor -el actor a
		 * de modificar cambios externos -debe el sistema notificar de algun cambio
		 * interno -debo saber si mi sistema interactua con otro
		 *
		 *
		 * Nombre................. ID: CU-1............... -----------------------
		 * Descripcion............ ----------------------- Actores involucrados...
		 * ----------------------- Precondiciones......... ----------------------- Curso
		 * normal cas de uso ----------------------- Post Codiciones........
		 * ----------------------- Alternativas........... asociacion: es la linea de
		 * comunicacion entre actor y casos de uso
		 * 
		 * generalizacion me sirve para indicar que el hijo hereda cosas del padre
		 *
		 * extension <<Extend>> permite que un caso de uso extiena su comportamiento de
		 * uno o varios casos de uso se utiliza para especificar que el corpontameinto
		 * de un caso de uso es diferente dependiendo de circustancias del caso anterior
		 * inclusion <<Include>> permite que un caso base incluya el corponatmeinto de
		 * otro caso de uso
		 * 
		 * diagrama de secuencias: muestra los eventos que fluyen de los actores al
		 * sistema. tiene 2 dimensiones: * .Vertical: representa el tiempo *
		 * .Horizontal: representa los roles o eventos * * .Elementos: * . Linea de
		 * vida: representa a un participante durante la interaccion * . actores: * .
		 * mensaje: * .sincrono: ------> cuando se envia un mensaje a un objeto, no se
		 * revibe el control hasta que el objeto receptor ha finaizado la ejecucion *
		 * .asincrono: -----(>)Quien envia el mensaje puede seguir trabajando sin que el
		 * receptor haya teminado al tarea * .retorno: (<)---- mensaje de confirmacion *
		 * . Activacion: representa el tiempo en el que se esta ejecutando una funcion *
		 * * * * * * Buscar productos * 1. el cliente selecciona buscar productos * 2.
		 * el sistema solicita los criterios de busqueda * 3. el cliente introduce los
		 * criterios solicitados * 4. el sistema busca producto que cumoplan con esos
		 * requisitos * 5. el sistema encuentra los productos y se los muestra al
		 * cliente
		 * 
		 */

	protected ArrayList<mensaje> conseguirMensajes(String nombre) {
		ArrayList<mensaje> mensajes = new ArrayList();
		String titulo;
		int codigo = conseguirCodigoDebate(nombre);
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery(
					"SELECT `codigoPersona_Aux`,`mensaje` FROM `debatir` WHERE codigoDebate_Aux=" + codigo);

			while (rs.next()) {

				mensajes.add(new mensaje(new JLabel(rs.getString("mensaje")),
						conseguirPersona(rs.getInt("codigoPersona_Aux")).getUsuario()));

			}
			rs.close();
			st.close();
			con.close();

		} catch (Exception e) {
			System.out.println("error");
		}
		Collections.reverse(mensajes);

		return mensajes;
	}

	protected int buscarDebate(String titulo) {

		int encontrado = 0;
		try {
			conexion = new Conexion();
			System.out.println("porfaaaaaaaaaaaaa");
			Connection con = conexion.getConnection();
			System.out.println("no me toques la polla");
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FRO" + "M debates");
			while (rs.next()) {

				if (rs.getString("nombreDebate").equalsIgnoreCase(titulo)) {
					encontrado = rs.getInt("codigoDebate");
				}
			}
			st.close();
			rs.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return encontrado;
	}

	protected int conseguirPuerto(String titulo) {

		int encontrado = 0;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM `debates` WHERE `nombreDebate`='" + titulo + "'");
			while (rs.next()) {
				encontrado = rs.getInt("puerto");

			}
			st.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encontrado;
	}

	protected int buscarEncuestas(String titulo) {

		int encontrado = 0;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM tests");
			while (rs.next()) {

				if (rs.getString("nombre").equalsIgnoreCase(titulo)) {
					encontrado = rs.getInt("codigoTest");
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encontrado;
	}

	protected int contarEncuestas() {
		int contador = 0;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM tests");
			while (rs.next()) {

				contador++;
			}
			st.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return contador;
	}

	protected ArrayList<JLabel> conseguirTest(int nombre) {
		ArrayList<JLabel> mensajes = new ArrayList();

		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();

			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT * FROM `tests` WHERE `codigoPersona_Aux`=" + nombre);

			while (rs.next()) {

				mensajes.add(new JLabel(rs.getString("nombre")));

			}
			st.close();
			rs.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mensajes;
	}

	protected ArrayList<JLabel> conseguirPreguntas(String nombre) {
		int codigo = conseguirCodigoTest(nombre);
		ArrayList<JLabel> mensajes = new ArrayList();
		String titulo;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT *  " + "FROM  `preguntas` WHERE codigoTest_Aux='" + codigo + "'");
			while (rs.next()) {
				mensajes.add(new JLabel(rs.getString("pregunta")));
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}

		return mensajes;
	}

	protected int conseguirCodigoTest(String nombre) {
		int codigo = 0;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT *  " + "FROM  `tests` ");
			while (rs.next()) {
				if (rs.getString("nombre").equals(nombre)) {
					codigo = rs.getInt("codigoTest");
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return codigo;
	}

	protected int conseguirCodigoDebate(String nombre) {
		int codigo = 0;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT *  " + "FROM  `debates` ");
			while (rs.next()) {
				if (rs.getString("nombreDebate").equals(nombre)) {
					codigo = rs.getInt("codigoDebate");
				}
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}
		return codigo;
	}

	protected ArrayList<JButton> conseguirBotonesTests() {
		ArrayList<JButton> botones = new ArrayList();
		String titulo;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT *  " + "FROM  `tests` ");
			while (rs.next()) {
				botones.add(new JButton(rs.getString("nombre")));
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}

		return botones;
	}

	protected ArrayList<JButton> conseguirBotonesDebates() {
		ArrayList<JButton> botones = new ArrayList();
		String titulo;
		try {
			conexion = new Conexion();
			Connection con = conexion.getConnection();
			Statement st = con.createStatement();

			ResultSet rs = st.executeQuery("SELECT *  " + "FROM  `debates` ");
			while (rs.next()) {
				botones.add(new JButton(rs.getString("nombreDebate")));
			}
			st.close();
			rs.close();
			con.close();
		} catch (Exception e) {

		}

		return botones;
	}

}

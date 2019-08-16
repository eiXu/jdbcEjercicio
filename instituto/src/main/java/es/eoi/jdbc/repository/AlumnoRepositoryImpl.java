package es.eoi.jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.eoi.jdbc.entity.Alumno;

public class AlumnoRepositoryImpl implements AlumnoRepository {

	Connection conn;
	String server;
	String user;
	String pass;

	public AlumnoRepositoryImpl() {
		server = "jdbc:mysql://localhost:3306/instituto?serverTimezone=UTC&useSSL=false";
		user = "root";
		pass = "1234";

	}

	private java.sql.Connection openConnection() {
		Connection newConn = null;
		try {
			newConn = DriverManager.getConnection(server, user, pass);
		} catch (Exception e) {
			System.err.println("No se ha podido crear la conexión");
		}
		return newConn;
	}

	public Alumno findByDni(String dni) {

		Alumno alumno = null;
		String sql = "SELECT * FROM alumnos WHERE DNI = ?";
		PreparedStatement pst;
		ResultSet rs;

		conn = openConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, dni);
			rs = pst.executeQuery();
			while (rs.next()) {
				alumno = new Alumno(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
						rs.getInt("EDAD"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alumno;
	}

	public List<Alumno> findAll() {

		List<Alumno> alumnos = new ArrayList<Alumno>();
		ResultSet rs;
		Statement st;
		String sql = "SELECT * FROM alumnos";

		conn = openConnection();

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			while (rs.next()) {
				alumnos.add(new Alumno(rs.getString("DNI"), rs.getString("NOMBRE"), rs.getString("APELLIDOS"),
						rs.getInt("EDAD")));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return alumnos;
	}

	public boolean create(Alumno newAlumno) {

		PreparedStatement pst;
		Integer num = 0;
		String sql = "INSERT INTO alumnos VALUES(?,?,?,?)";
		
		conn = openConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, newAlumno.getDni());
			pst.setString(2,newAlumno.getNombre());
			pst.setString(3,newAlumno.getApellidos());
			pst.setInt(4,newAlumno.getEdad());
			num = pst.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println("Ya existe");;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num > 0;
	}

	public boolean delete(String dni) {

		PreparedStatement pst;
		Integer num = 0;
		String sql = "DELETE FROM alumnos WHERE DNI = ?";
		
		conn = openConnection();

		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, dni);
			num = pst.executeUpdate();
			
		} catch (SQLException e1) {
			System.err.println("Ya existe");;
		}
		
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num > 0;
	}

	public boolean update(String dni, String nombre, String apellidos, Integer edad) {

		PreparedStatement pst;
		Integer num = 0;
		String sql = "UPDATE alumnos SET NOMBRE = ?, APELLIDOS = ?, EDAD = ? WHERE DNI = ?";
		
		conn = openConnection();
		
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setInt(3, edad);
			pst.setString(4, dni);
			num = pst.executeUpdate();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.err.println("Ya existe");;
		}

		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return num > 0;
	}

}

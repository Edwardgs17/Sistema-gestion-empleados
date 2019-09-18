package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;

import connection.Conexion;
import vo.PersonaVO;

public class PersonaDAO {


	public String registrarPersona(PersonaVO miPersona) {
		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "INSERT INTO empleado (documento,nombre,sexo,direccion,telefono,salario,password,tipo)"
				+ "  VALUES (?,?,?,?,?,?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersona.getDocumento());
			preStatement.setString(2, miPersona.getNombre());
			preStatement.setString(3, miPersona.getSexo());
			preStatement.setString(4, miPersona.getDireccion());
			preStatement.setString(5, miPersona.getTelefono());
			preStatement.setString(6, miPersona.getSueldoTotal() + "");
			preStatement.setString(7, miPersona.getDocumento());
			preStatement.setString(8, miPersona.getTipo());
			preStatement.execute();

			resultado = "Registro Exitoso";

		} catch (SQLException e) {
			System.out.println("No se pudo registrar el empleado: " + e.getMessage());
			resultado = "No se pudo registrar el empleado";
		} finally {
			conexion.desconectar();
		}

		return resultado;
	}


	public ArrayList<PersonaVO> obtenerListaPersonas() {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		PersonaVO miPersona = new PersonaVO();
		ArrayList<PersonaVO> listaPersonas = null;
		
		FacesContext context= FacesContext.getCurrentInstance();
		PersonaVO personaLogueada=(PersonaVO) context.getExternalContext().getSessionMap().get("usuario");
		
		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM empleado ";

		try {
			if (connection != null) {
				listaPersonas = new ArrayList<>();
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				while (result.next() == true) {
					miPersona = new PersonaVO();
					miPersona.setDocumento(result.getString("documento"));
					miPersona.setNombre(result.getString("nombre"));
					miPersona.setSexo(result.getString("sexo"));
					miPersona.setDireccion(result.getString("direccion"));
					miPersona.setTelefono(result.getString("telefono"));
					miPersona.setSalario(Double.parseDouble(result.getString("salario")));
					miPersona.setPassword(result.getString("password"));
					miPersona.setTipo(result.getString("tipo"));
					
					if (personaLogueada!=null) {
						String cad="Login tipo: "+personaLogueada.getTipo()+", documento: "+personaLogueada.getDocumento()+"\n";
						cad+="persona tipo: "+miPersona.getTipo()+", documento: "+miPersona.getDocumento()+"\n";
						
						System.out.println(cad);
						if (personaLogueada.getTipo().equals("1") ||
								personaLogueada.getDocumento().equals(miPersona.getDocumento())) {
							miPersona.setPermiso(true);
						}
					}
					
					listaPersonas.add(miPersona);
				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta de la persona: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return listaPersonas;
	}
	
	public String editarPersona(PersonaVO persona) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE empleado "
					+ " SET nombre = ? , sexo=? , direccion=? , telefono=? , salario= ? , tipo= ?"
					+ " WHERE documento= ? ";
			PreparedStatement preStatement = connection.prepareStatement(consulta);

			preStatement.setString(1, persona.getNombre());
			preStatement.setString(2, persona.getSexo());
			preStatement.setString(3, persona.getDireccion());
			preStatement.setString(4, persona.getTelefono());
			preStatement.setString(5, persona.getSalario() + "");
			preStatement.setString(6, persona.getTipo());
			preStatement.setString(7, persona.getDocumento());
			
			preStatement.executeUpdate();

			resultado = "Se ha Actualizado el empleado satisfactoriamente";

			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "No se pudo actualizar el empleado";
		}
		return resultado;
	}

	public String eliminarPersona(String documento) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();

		String resp = "";
		try {
			String sentencia = "DELETE FROM empleado WHERE documento= ? ";

			PreparedStatement statement = connection.prepareStatement(sentencia);
			statement.setString(1, documento);

			statement.executeUpdate();

			resp = "El empleado se ha eliminado exitosamente";
			statement.close();
			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			resp = "No se pudo eliminar el empleado";
		}
		return resp;
	}
	
		public PersonaVO consultarPersonaIndividual(String documento) {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		FacesContext context= FacesContext.getCurrentInstance();
		PersonaVO personaLogueada=(PersonaVO) context.getExternalContext().getSessionMap().get("usuario");
		
		
		PersonaVO miPersona = null;
		System.out.println("Documento: "+documento);

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM empleado where documento = '"+documento+"'";
		System.out.println(consulta);
		try {
			if (connection != null) {
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				if (result.next() == true) {
					miPersona = new PersonaVO();
					miPersona.setDocumento(result.getString("documento"));
					miPersona.setNombre(result.getString("nombre"));
					miPersona.setSexo(result.getString("sexo"));
					miPersona.setDireccion(result.getString("direccion"));
					miPersona.setTelefono(result.getString("telefono"));
					miPersona.setSalario(Double.parseDouble(result.getString("salario")));
					miPersona.setPassword(result.getString("password"));
					miPersona.setTipo(result.getString("tipo"));
					
					if (personaLogueada!=null) {
						String cad="Login tipo: "+personaLogueada.getTipo()+", documento: "+personaLogueada.getDocumento()+"\n";
						cad+="persona tipo: "+miPersona.getTipo()+", documento: "+miPersona.getDocumento()+"\n";
						
						System.out.println(cad);
						if (personaLogueada.getTipo().equals("1") ||
								personaLogueada.getDocumento().equals(miPersona.getDocumento())) {
							miPersona.setEditar(true);
						}
					}					
				}
			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del empleado: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		
		if (miPersona!=null) {
			System.out.println("Nombre Persona: "+miPersona.getNombre());
		}else{
			System.out.println("Nombre Persona: "+miPersona);
		}
		
		return miPersona;
	}
		

	public PersonaVO consultarLogin(String documento, String pass) {
			Connection connection = null;
			Conexion miConexion = new Conexion();
			PreparedStatement statement = null;
			ResultSet result = null;

			PersonaVO miPersona = null;

			connection = miConexion.getConnection();
			
			System.out.println("Documento: "+documento+" , pass: "+pass);
			
			try {
				if (connection != null) {
					
					String consulta = "SELECT * FROM empleado where documento = ? and password = ? ";

					statement = connection.prepareStatement(consulta);

					statement.setString(1, documento);
					statement.setString(2, pass);
									
					result = statement.executeQuery();
					
					if (result.next() == true) {
						miPersona = new PersonaVO();
						miPersona.setDocumento(result.getString("documento"));
						miPersona.setNombre(result.getString("nombre"));
						miPersona.setSexo(result.getString("sexo"));
						miPersona.setDireccion(result.getString("direccion"));
						miPersona.setTelefono(result.getString("telefono"));
						miPersona.setSalario(Double.parseDouble(result.getString("salario")));
						miPersona.setPassword(result.getString("password"));
						miPersona.setTipo(result.getString("tipo"));
					}

				}
			} catch (SQLException e) {
				System.out.println("Error al hacer el Login: " + e.getMessage());
			} finally {
				miConexion.desconectar();
			}
			return miPersona;

		}


	public String cambiarContraseña(PersonaVO miPersonaVo) {
		
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE empleado"
					+ " SET password = ? "
					+ " WHERE documento= ?";
			PreparedStatement preStatement = connection.prepareStatement(consulta);

			preStatement.setString(1, miPersonaVo.getPassword());
			preStatement.setString(2, miPersonaVo.getDocumento());
			preStatement.executeUpdate();

			resultado = "Se ha Actualizado la contraseña satisfactoriamente";

			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "No se pudo actualizar la contraseña";
		}
		return resultado;
	}
}


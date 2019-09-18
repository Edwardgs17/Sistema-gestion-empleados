package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.faces.context.FacesContext;

import connection.Conexion;
import vo.PagosVO;
import vo.PersonaVO;

public class PagosDAO {
	public String pagarPersona(PagosVO miPago) {
		
		String respuesta;
		Connection conn=null;
		Conexion miConexion= new Conexion();
		PreparedStatement statement;
		
		conn=miConexion.getConnection();
		
		String consulta="INSERT INTO pagos(documentoEmpleado,nombreEmpleado,salario,horasTrabajadas,salarioTotal,fechaPago)VALUES(?,?,?,?,?,curdate())";
		
		   try {

				    statement=conn.prepareStatement(consulta);
					statement.setString(1,miPago.getDocumentoEmpleado());
					statement.setString(2,miPago.getNombreEmpleado());
					statement.setString(3,miPago.getSalario()+"");
					statement.setInt(4,miPago.getHorasTrabajadas());
					statement.setString(5,miPago.getSalarioTotal()+"");
					
					statement.execute();	
					respuesta="Registro Exitoso";
					
			
			} catch (SQLException e) {
				
				System.out.println("no se pudo registrar la persona"+e.getMessage());
				respuesta="no se pudo registrar el Usuario";
			}
			finally {
				
				miConexion.desconectar();
			}
			
				return respuesta;
			}

	public PagosVO consultarPago(String documentoEmpleado) {
		
		ResultSet result=null;
		Connection conn=null;
		Conexion miConexion= new Conexion();
		PreparedStatement statement=null;
		
		conn=miConexion.getConnection();
		
		return null;
	}

	public ArrayList<PagosVO> obtenerPagos() {
		
		Connection conn=null;
		Conexion miConexion=new Conexion();
		PreparedStatement statement=null;
		ResultSet result=null;
		
		PagosVO misPagos=new PagosVO();
		PersonaVO miPersona=new PersonaVO();
		ArrayList<PagosVO> listaPagos=null;
		
	     FacesContext context= FacesContext.getCurrentInstance();
		 PersonaVO personaLogueada=(PersonaVO) context.getExternalContext().getSessionMap().get("usuario");
		
		conn=miConexion.getConnection();
		
		String consu="SELECT documentoEmpleado,nombreEmpleado,salario,horasTrabajadas,salarioTotal,fechaPago FROM pagos";
		
		try {
			
			if(conn!=null) {
				listaPagos=new ArrayList<>();
				statement=conn.prepareStatement(consu);
					result=statement.executeQuery();
				
				while(result.next()==true) {	
					misPagos=new PagosVO();
				
					misPagos.setDocumentoEmpleado(result.getString("documentoEmpleado"));
					misPagos.setNombreEmpleado(result.getString("nombreEmpleado"));
					misPagos.setSalario(Double.parseDouble(result.getString("salario")));
					misPagos.setHorasTrabajadas(Integer.parseInt(result.getString("horasTrabajadas")));
					misPagos.setSalarioTotal(Double.parseDouble(result.getString("salarioTotal")));
					misPagos.setFechaPago(result.getString("fechaPago"));
				
					if (personaLogueada!=null) {
						String cad="Login tipo: "+personaLogueada.getTipo()+", documento: "+personaLogueada.getDocumento()+"\n";
						cad+="persona tipo: "+miPersona.getTipo()+", documento: "+miPersona.getDocumento()+"\n";
						
						System.out.println(cad);
						if (personaLogueada.getTipo().equals("1") ||
								personaLogueada.getDocumento().equals(miPersona.getDocumento())) {
							miPersona.setPermiso(true);
						}
					}
					
					listaPagos.add(misPagos);
					System.out.println("Lista: " + listaPagos);
				}
			}
		} catch (SQLException e) {
			 System.out.println("Error en la consulta de los pagos!!"+e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		
		return listaPagos;
	}
	
	public String editarPago(PagosVO miPago) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		try {
			String consulta = "UPDATE pagos"
					+ " SET horasTrabajadas=?, salarioTotal=?"
					+ " WHERE documentoEmpleado= ? ";
			PreparedStatement preStatement = connection.prepareStatement(consulta);

			preStatement.setInt(1, miPago.getHorasTrabajadas());
			preStatement.setString(2, miPago.getSalarioTotal()+"");
			preStatement.setString(3, miPago.getDocumentoEmpleado());
			
			preStatement.executeUpdate();

			resultado = "Se ha Actualizado el pago satisfactoriamente";

			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "No se pudo actualizar el pago";
		}
		return resultado;
	}




}

package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.swing.JOptionPane;

import dao.PagosDAO;
import dao.PersonaDAO;
import logica.Procesos;
import vo.PagosVO;
import vo.PersonaVO;

@ManagedBean
@ViewScoped
public class PersonaBean {
	
	private PersonaVO miPersonaVo;
	PersonaDAO miPersonaDao;
    private PagosVO misPagosVo;
	PagosDAO misPagosDAO;
	Procesos misProcesos;
	private String sueldoTotal;
	private String mensaje;
	private boolean mostrar;
	private boolean mostrarError;
	private boolean permisoAdmin;
	
	private ArrayList<PagosVO> listaPagos=new ArrayList<>();
	private ArrayList<PersonaVO> listaPersona=new ArrayList<>();
	
	public PersonaBean(){
		System.out.println("INGRESA NUEVAMENTE!!!!");
		miPersonaVo=new PersonaVO();
		misPagosVo=new PagosVO();
		miPersonaDao=new PersonaDAO();
		misPagosDAO=new PagosDAO();
		misProcesos=new Procesos();
		consultarPago();
		cargarPersonas();// cargamos la tabla desde el llamado a la vista en con el preRenderView en el f:event
	}

	public PersonaVO getMiPersonaVo() {
		return miPersonaVo;
	}

	public void setMiPersonamiPersonaVo(PersonaVO miPersonaVo) {
		this.miPersonaVo = miPersonaVo;
	}
	
	public void cargarPersonas(){
		
		listaPersona.clear();
		listaPersona=miPersonaDao.obtenerListaPersonas();
//		
//		for (int i = 0; i < listaPersona.size(); i++) {
//			System.out.println(listaPersona.get(i).getNombre());
//			
//			//setMostrar(validarPermisoAdmin(listaPersona.get(i)));
//			
//		}
		
		if (listaPersona==null) {
			setMensaje("No se pudo conectar, verifique que la Base de Datos "
					+ "se encuentre iniciada");
		}
	}
	
	public String editarPersona(PersonaVO persona){
		//mensaje="ingresa";
		//JOptionPane.showMessageDialog(null, "En Editar");
		System.out.println("INGRESA A EDITAR");
		persona.setEditar(true);
	
		return "consultar_lista_personas";
	}
	
	public void guardarPersona(PersonaVO persona){
		//JOptionPane.showMessageDialog(null, "En GUARDAR");
		misPagosVo.setSalarioTotal(misProcesos.calcularSalario(miPersonaVo.getSalario(),miPersonaVo.getCantidadHorasExtras()));
		System.out.println(misProcesos.calcularSalario(miPersonaVo.getSalario(),miPersonaVo.getCantidadHorasExtras()));
		setMensaje(miPersonaDao.editarPersona(persona));
		persona.setEditar(false);
		if (!getMensaje().equals("No se pudo actualizar la persona")) {
			setMostrar(true);	
		}else{
			setMostrarError(true);
		}	
	}
	
	public String editarPago(PagosVO miPago){
		
		
		System.out.println("INGRESA A EDITAR");
		miPago.setEditar(true);
	
		return "consultar_pago";
	}
	
	public void guardarPago(PagosVO miPago){
	    
		System.out.println("INGRESA A GUARDAR");
		miPago.setSalarioTotal(misProcesos.calcularSalario(miPago.getSalario(),miPago.getHorasTrabajadas()));
		setMensaje(misPagosDAO.editarPago(miPago));
		miPago.setEditar(false);
		if (!getMensaje().equals("No se pudo actualizar el Pago")) {
			sueldoTotal="El Sueldo total a Pagar es: "+misProcesos.calcularSalario(miPago.getSalario(),miPago.getHorasTrabajadas());
			setMostrar(true);	
		}else{
			setMostrarError(true);
		}	
	}
	
	public void eliminarPersona(PersonaVO persona){
		setMensaje(miPersonaDao.eliminarPersona(persona.getDocumento()));
		listaPersona.remove(persona);
	}

	public void registrarPersona(){
		miPersonaVo.setSueldoTotal(misProcesos.calcularSalario(miPersonaVo.getSalario(),miPersonaVo.getCantidadHorasExtras()));
		mensaje=miPersonaDao.registrarPersona(miPersonaVo);
		if (mensaje.equalsIgnoreCase("Registro Exitoso")) {
			mensaje="Se ha registrado exitosamente!";
			sueldoTotal="El Sueldo total a Pagar es: "+miPersonaVo.getSueldoTotal();
			setMostrar(true);
		}else{
			mensaje="Ocurrió un problema al registrar, verifique nuevamente";
			setMostrarError(true);
		}
	}
	
	public void consultarPersona(){
		miPersonaVo=miPersonaDao.consultarPersonaIndividual(miPersonaVo.getDocumento());
		if (miPersonaVo!=null) {
			
		}else{
			setMostrarError(true);
			setMostrar(false);
			setMensaje("No se encuentra la persona");
		}		
	}
	
	public void actualizarUsuario(){
		System.out.println("Actualizar Usuario");
		setMensaje(miPersonaDao.editarPersona(miPersonaVo));
		if (!getMensaje().equals("No se pudo actualizar la persona")) {
			setMostrar(true);	
		}else{
			setMostrarError(true);
		}
		
	}
	
	public void eliminarUsuario(){
		System.out.println("Eliminar Usuario");
		setMensaje(miPersonaDao.eliminarPersona(miPersonaVo.getDocumento()));
		
		if (!getMensaje().equals("No se pudo eliminar")) {
			setMostrar(true);
			miPersonaVo=new PersonaVO();
		}else{
			setMostrarError(true);
		}
		
	}
	
	public void pagarPersona() {
		
		String msj2="";
		
		misPagosVo.setDocumentoEmpleado(miPersonaVo.getDocumento());
		misPagosVo.setNombreEmpleado(miPersonaVo.getNombre());
		misPagosVo.setSalario(miPersonaVo.getSalario());
		misPagosVo.setHorasTrabajadas(miPersonaVo.getCantidadHorasExtras());
		System.out.println(miPersonaVo.getCantidadHorasExtras());
		misPagosVo.setSalarioTotal(misProcesos.calcularSalario(miPersonaVo.getSalario(),miPersonaVo.getCantidadHorasExtras()));
		System.out.println(misProcesos.calcularSalario(miPersonaVo.getSalario(),miPersonaVo.getCantidadHorasExtras()));
		
		mensaje=misPagosDAO.pagarPersona(misPagosVo);		
		if (mensaje.equalsIgnoreCase("Registro Exitoso")) {
			mensaje="Se ha registrado exitosamente!";
			sueldoTotal="El Sueldo total a Pagar es: "+miPersonaVo.getSalario();
			setMostrar(true);
		}else{
			mensaje="Ocurrió un problema al registrar, verifique nuevamente";
			setMostrarError(true);
		}
	}
	public void consultarPago() {
		
		listaPagos.clear();
		listaPagos=misPagosDAO.obtenerPagos();
		System.out.println("Entra");
		if(listaPagos==null) {
			
			setMensaje("No se puede ejecutar la consulta porque la lista esta vacia");
		}
	}
		
	public void limpiarFormulario(){
		miPersonaVo=new PersonaVO();
	}

	public String getSueldoTotal() {
		return sueldoTotal;
	}

	public void setSueldoTotal(String sueldoTotal) {
		this.sueldoTotal = sueldoTotal;
	}

	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public boolean isMostrarError() {
		return mostrarError;
	}

	public void setMostrarError(boolean mostrarError) {
		this.mostrarError = mostrarError;
	}
	

	public ArrayList<PersonaVO> getListaPersona() {
		return listaPersona;
	}

	public void setListaPersona(ArrayList<PersonaVO> listaPersona) {
		this.listaPersona = listaPersona;
	}

	public boolean isPermisoAdmin() {
		return permisoAdmin;
	}

	public void setPermisoAdmin(boolean permisoAdmin) {
		this.permisoAdmin = permisoAdmin;
	}

	public PagosVO getMisPagosVo() {
		return misPagosVo;
	}

	public void setMisPagosVo(PagosVO misPagosVo) {
		this.misPagosVo = misPagosVo;
	}

	public ArrayList<PagosVO> getListaPagos() {
		return listaPagos;
	}

	public void setListaPagos(ArrayList<PagosVO> listaPagos) {
		this.listaPagos = listaPagos;
	}
}

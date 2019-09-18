package bean;


import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import dao.PersonaDAO;
import vo.PersonaVO;

@ManagedBean
@SessionScoped
public class LoginBean {

	private PersonaVO miPersonaVo;
	private PersonaDAO miPersonaDao;
	private String mensaje="Mensaje";
	private boolean mostrarError;
	private boolean permisoAdmin;

	public LoginBean(){
		miPersonaVo=new PersonaVO();
		miPersonaDao=new PersonaDAO();
	}
	
	public String validarUsuario(){
		
		String resp="";
			
		System.out.println("*****************************************************");
		System.out.println("Documento: "+miPersonaVo.getDocumento());
		System.out.println("Nombre: "+miPersonaVo.getPassword());
		
		PersonaVO persona=miPersonaDao.consultarLogin(miPersonaVo.getDocumento(),miPersonaVo.getPassword());
		
		if (persona!=null) {
			resp="inicio.jsf";
			setMensaje("");
			setMostrarError(false);
			miPersonaVo=persona;
			//almacenamos la sesión del usuario para usarlo en el sistema
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", miPersonaVo);

				if (miPersonaVo.getTipo().equals("1")) {
					setPermisoAdmin(true);
				}else{
					setPermisoAdmin(false);
				}
			
			
			System.out.println("USUARIO VALIDO: "+miPersonaVo.getNombre()+" ");
		}else{
			resp="#";
			setMensaje("El usuario no es Valido, Verifique nuevamente...");
			setMostrarError(true);
			System.out.println("USUARIO NO VALIDO");
		}
		System.out.println("*****************************************************");
		return resp;
	}
	
	public void validarAcceso(){
		
		System.out.println("INGRESA A VALIDAR ACCESO");
		
		try {
			FacesContext context= FacesContext.getCurrentInstance();
			PersonaVO miPersona=(PersonaVO) context.getExternalContext().getSessionMap().get("usuario");
			
			if (miPersona==null) {
				context.getExternalContext().redirect("error_ingreso.jsf");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public String cerrarSesion(){
		
		System.out.println("INGRESA A CERRAR SESION");
		
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		return "login.jsf";
	}
	public void actualizarContraseña(){
		System.out.println("Actualizar Contraseña");
		setMensaje(miPersonaDao.cambiarContraseña(miPersonaVo));
		if (!getMensaje().equals("No se pudo actualizar la contraseña")) {
			setMensaje("Actualizado!!!");	
		}else{
			setMostrarError(true);
		}
		
		if(miPersonaVo.getPassword().equals(miPersonaVo.getNuevaContraseña())) {
			 miPersonaDao.cambiarContraseña(miPersonaVo);
			 setMensaje("Contraseña actualizada correctamente!!");
			 setMensaje("No se pudo actualizar!!");
		}
		else {
			 
			 setMensaje("Las contraseñas no coinciden!!");
		}
	}
	
	public PersonaVO getMiPersonaVo() {
		return miPersonaVo;	
	}

	public void setMiPersonaVo(PersonaVO miPersonaVo) {
		this.miPersonaVo = miPersonaVo;
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

	public boolean isPermisoAdmin() {
		return permisoAdmin;
	}

	public void setPermisoAdmin(boolean permisoAdmin) {
		this.permisoAdmin = permisoAdmin;
	}
	
}

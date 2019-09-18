package vo;

public class PersonaVO {

	private String documento;
	private String nombre;
	private String sexo;
	private String direccion;
	private String telefono;
	private double salario;
	private int cantidadHorasExtras;
	private double sueldoTotal;
	private String password;
	private boolean editar;
	private boolean permiso;
	private String tipo;
	private String nuevaContraseña;
	
	public PersonaVO(){
		
	}
		
	public PersonaVO(String documento, String nombre, String sexo, String direccion, String telefono, double salario,
			int cantidadHorasExtras, double sueldoTotal,String password,String tipo) {
		super();
		this.documento = documento;
		this.nombre = nombre;
		this.sexo = sexo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.salario = salario;
		this.cantidadHorasExtras = cantidadHorasExtras;
		this.sueldoTotal = sueldoTotal;
		this.password=password;
		this.tipo=tipo;
	}
	
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public int getCantidadHorasExtras() {
		return cantidadHorasExtras;
	}
	public void setCantidadHorasExtras(int cantidadHorasExtras) {
		this.cantidadHorasExtras = cantidadHorasExtras;
	}
	public double getSueldoTotal() {
		return sueldoTotal;
	}
	public void setSueldoTotal(double sueldoTotal) {
		this.sueldoTotal = sueldoTotal;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEditar() {
		return editar;
	}

	public void setEditar(boolean editar) {
		this.editar = editar;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isPermiso() {
		return permiso;
	}

	public void setPermiso(boolean permiso) {
		this.permiso = permiso;
	}

	public String getNuevaContraseña() {
		return nuevaContraseña;
	}

	public void setNuevaContraseña(String nuevaContraseña) {
		this.nuevaContraseña = nuevaContraseña;
	}

	
}

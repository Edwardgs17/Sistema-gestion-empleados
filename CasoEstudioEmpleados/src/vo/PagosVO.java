package vo;

public class PagosVO {

	private String documentoEmpleado;
	private String nombreEmpleado;
	private String fechaPago;
	private double salario;
	private int horasTrabajadas;
	private double salarioTotal;
	private boolean editar;
	
	public String getDocumentoEmpleado() {
		return documentoEmpleado;
	}
	public void setDocumentoEmpleado(String documentoEmpleado) {
		this.documentoEmpleado = documentoEmpleado;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public int getHorasTrabajadas() {
		return horasTrabajadas;
	}
	public void setHorasTrabajadas(int d) {
		this.horasTrabajadas = d;
	}
	public double getSalarioTotal() {
		return salarioTotal;
	}
	public void setSalarioTotal(double d) {
		this.salarioTotal = d;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	
}

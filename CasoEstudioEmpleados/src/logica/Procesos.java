package logica;

public class Procesos {

	double horaExtra=3000;
	
	public double calcularSalario(double salario, int horasTrabajadas) {
		
	 double resultado=(horaExtra*horasTrabajadas)+salario;
	 
	 return resultado;
		
	}

}


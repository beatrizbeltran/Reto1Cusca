package reto.test;

public class CalculoCuotaValidacion {
	public static void main(String[] args) {
		double M = 250000.00; 
		double P = (M * 0.20);
		double MontoMenosPrima = M-P;
		double interes = 4*0.01;
		double r = interes / 12; 
		int n = 360; 

		// Calcular la cuota mensual
		double C = MontoMenosPrima * (r * Math.pow(1 + r, n)) / (Math.pow(1 + r, n) - 1);

		// Imprimir la cuota mensual
		System.out.println("La cuota mensual a pagar es: " + C);
	}
}

package calculadoraip;

public class Test {

	public static void main(String[] args) {
		
		Ipv4 ip = new Ipv4(132,18,0,0);
		MascaraDeRed mascaraDeRed = new MascaraDeRed(255,255,0,0);
		
		
		System.out.println("IPv4: " + ip);
		
		System.out.println("Mascara de red:  " + mascaraDeRed);
		
		System.out.println("La clase de la IP es: " + Utilidades.obtenerclaseIp(ip));

		System.out.println("El valor de n es | n = " + Utilidades.obtenerN(4));
		
		Utilidades.obtenerMascaraSubred(Utilidades.obtenerN(4),mascaraDeRed);
	}
	
	
	
	

}

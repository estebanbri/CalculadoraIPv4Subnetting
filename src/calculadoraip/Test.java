package calculadoraip;

public class Test {

	public static void main(String[] args) {
		
		Ipv4 ip = new Ipv4(132,18,0,0);
		MascaraDeRed mascaraDeRed = new MascaraDeRed(255,255,0,0);
		
		int cantidadSubRedes = 4;
		Utilidades.subnetting(ip, cantidadSubRedes, mascaraDeRed);
	}
	
	
	
	

}

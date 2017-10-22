package calculadoraip;

public class Test {

	public static void main(String[] args) {
		
		Ipv4 ip = new Ipv4(192,168,0,1);
		MascaraDeRed mascaraDeRed = new MascaraDeRed(255,255,255,0);
		
		int cantidadSubRedes = 4;
		Utilidades.subnetting(ip, cantidadSubRedes, mascaraDeRed);
	}
	
	
	
	

}

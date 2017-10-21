package calculadoraip;

import java.util.regex.Pattern;

public class Utilidades {
	private static boolean estaEntre(int valor, int limInf, int limSup){
		return (limInf <= valor && valor <= limSup );
	}
	public static void subnetting(Ipv4 ip, int cantidadSubredes, MascaraDeRed mascaraDeRed) {
		System.out.println("IPv4: " + ip);
		System.out.println("Mascara de red:  " + mascaraDeRed);
		System.out.println("La clase de la IP es: " + Utilidades.obtenerclaseIp(ip));
		System.out.println("El valor de n es | n = " + Utilidades.obtenerN(4));
		obtenerMascaraSubred(obtenerN(cantidadSubredes), mascaraDeRed);
	}
	
	public static char obtenerclaseIp(Ipv4 ip) {
		if(estaEntre(ip.primerOcteto, 0, 127 )) {
			ip.setClase('A');
		}
		if(estaEntre(ip.primerOcteto, 128, 191 )) {
			ip.setClase('B');
		}
		if(estaEntre(ip.primerOcteto, 192, 223 )) {
			ip.setClase('C');
		}
		if(estaEntre(ip.primerOcteto, 224, 239 )) {
			ip.setClase('D');
		}
		if(estaEntre(ip.primerOcteto, 240, 255 )) {
			ip.setClase('E');
		}
		return ip.getClase();
	}

	//obtiene el n  para que se cumpla la expresion (2 elevado a n) -2 <= cantidadSubredes
	public static int obtenerN(int cantidadSubredes) {
		int n = 0;
		
		while((Math.pow(2, n)-2)<=cantidadSubredes) {
			n++;
		}
		
		return n;
	}

	public static void obtenerMascaraSubred(int n, MascaraDeRed mascaraDeRed) {
		
		String mascaraRed = conversorDecimalAbits(mascaraDeRed);
		
		char[] arrayCharBit = mascaraRed.toCharArray();
		
		System.out.print("Mascara de red (binario)= ");
		for(char c:arrayCharBit) {System.out.print(c);}
		System.out.println("");
		
		
		for(int i=0; i<=arrayCharBit.length;i++) {
			if(arrayCharBit[i] == '0'){
				switch(n){
					case 1:arrayCharBit[i]='1';break;
					case 2:arrayCharBit[i]='1';arrayCharBit[i+1]='1';break;
					case 3:arrayCharBit[i]='1';arrayCharBit[i+1]='1';arrayCharBit[i+2]='1';break;
					default: System.out.println("No lo programe para n mayores a 3");
				}
				break;
			}
		}
		System.out.print("Mascara de subred (binario)= ");
		for(char c:arrayCharBit) {System.out.print(c);}
		System.out.println("");
		System.out.println("Mascara de subred (decimal)= " + conversorBitsAdecimales(arrayCharBit));
		
		System.out.println("Hosts por subred: " + obtenerCantHostPorSubred(arrayCharBit) + " hosts");

	}
	
	private static String conversorDecimalAbits(MascaraDeRed mascaraDeRed) {
		String mascaraDeRedString = mascaraDeRed.toString();
		String patron = Pattern.quote(".");
		String[] octetos = mascaraDeRedString.split(patron);
		String octetosEnBinario="";
		for(String s: octetos) {
			if(s.equals("0")) {
				octetosEnBinario+="00000000";
			}else {
				octetosEnBinario+=Integer.toBinaryString(Integer.parseInt(s));
			}
		}
		return octetosEnBinario;
	}
	
	private static String conversorBitsAdecimales(char[] bits) {
		String mascara = "";
		for(char c : bits) {
			mascara += c;
		}
		
		mascara.trim();

		int primerOcteto = Integer.parseInt(mascara.substring(0, 8), 2);
		int segundoOcteto = Integer.parseInt(mascara.substring(8, 16), 2);
		int tercerOcteto = Integer.parseInt(mascara.substring(16, 24), 2);
		int cuartoOcteto = Integer.parseInt(mascara.substring(24), 2);
		
		
		
		return primerOcteto+"." + segundoOcteto+"."+ tercerOcteto+"."+ cuartoOcteto;
	}
	
	public static String obtenerCantHostPorSubred(char[] bits) {
		int potencia = 0;
	
		for(char c : bits) {
			if(c=='0') {
				potencia++;
			}
		}
		Integer i = (int) (Math.pow(2, potencia) - 2);
		return Integer.toString(i);
	}


}

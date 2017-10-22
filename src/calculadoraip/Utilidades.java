package calculadoraip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class Utilidades {
	private static boolean estaEntre(int valor, int limInf, int limSup){
		return (limInf <= valor && valor <= limSup );
	}
	public static void subnetting(Ipv4 ip, int cantidadSubredes, MascaraDeRed mascaraDeRed) {
		System.out.println("IPv4: " + ip);
		System.out.println("Mascara de red:  " + mascaraDeRed);
		System.out.println("La clase de la IP es: " + Utilidades.obtenerclaseIp(ip));
		System.out.println("El valor de n es | n = " + Utilidades.obtenerN(cantidadSubredes));
		char[] arrayCharBit = obtenerMascaraSubred(obtenerN(cantidadSubredes), mascaraDeRed);
		System.out.println("Mascara de subred (decimal)= " + conversorBitsAdecimales(arrayCharBit));
		System.out.println("RED IP: " + obtenerRedIP(ip).toString());
		System.out.println("BROADCAST: " + obtenerBroadCast(ip).toString());
		mostrarRangoHostMinMax(obtenerRangoHostMinMax(ip));

		System.out.println("Hosts por subred: " + obtenerCantHostPorSubred(arrayCharBit) + " hosts");
		List<Ipv4> listaIpv4 = calculoRangoIpGeneradasPorSubRed(ip , conversorBitsAdecimales(arrayCharBit) ,  cantidadSubredes);
		mostrarListaIPs(listaIpv4);
		
	}
	private static void mostrarRangoHostMinMax(List<Ipv4> min_max) {
		System.out.print("Rango Hosts |Min|Max|:  ");
		for(Ipv4 ip: min_max) {
			System.out.print(" | " + ip + " | ");
		}
		System.out.println();
	}
	private static void mostrarListaIPs(List<Ipv4> lista) {

		Iterator<Ipv4> itr = lista.iterator();
	    int count=0;
	    System.out.println("          Desde            Hasta");
		while (itr.hasNext()) {
			count++;
			System.out.print("Red n° "+ count);
			for(int i = 0; i<2; i++) {
				if(!itr.hasNext()) break;
				System.out.print(" "+itr.next() + " ");
			}
			System.out.println();
	    }
	}
	
	private static char obtenerclaseIp(Ipv4 ip) {
		if(estaEntre(ip.getPrimerOcteto(), 0, 127 )) {
			ip.setClase('A');
		}
		if(estaEntre(ip.getPrimerOcteto(), 128, 191 )) {
			ip.setClase('B');
		}
		if(estaEntre(ip.getPrimerOcteto(), 192, 223 )) {
			ip.setClase('C');
		}
		if(estaEntre(ip.getPrimerOcteto(), 224, 239 )) {
			ip.setClase('D');
		}
		if(estaEntre(ip.getPrimerOcteto(), 240, 255 )) {
			ip.setClase('E');
		}
		return ip.getClase();
	}

	//obtiene el n  para que se cumpla la expresion (2 elevado a n) -2 <= cantidadSubredes
	private static int obtenerN(int cantidadSubredes) {
		int n = 1;
		
		while(!((Math.pow(2, n)-2)>=cantidadSubredes)) {
			n++;
		}
		
		return n;
	}

	private static char[] obtenerMascaraSubred(int n, MascaraDeRed mascaraDeRed) {
		
		String mascaraRed = obtenerMascaraDeRedEnBits(mascaraDeRed);
		
		char[] arrayCharBit = mascaraRed.toCharArray();
		
		System.out.print("Mascara de red (binario)= ");
		for(char c:arrayCharBit) {System.out.print(c);}
		System.out.println("");
		
		System.out.println("Cantidad de host: " + cantidadHost(arrayCharBit) + " hosts" );
		
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
		
		return arrayCharBit;
	}
	
	private static String obtenerMascaraDeRedEnBits(MascaraDeRed mascaraDeRed) {
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

	private static Ipv4 obtenerRedIP(Ipv4 ip) {
		Ipv4 ipv4 = new Ipv4(ip.getPrimerOcteto(),ip.getSegundoOcteto(),ip.getTercerOcteto(),ip.getCuartoOcteto());
		ipv4.setClase(ip.getClase());
		
		switch (ipv4.getClase()) {
		case 'A': {
			ipv4.setSegundoOcteto(0);
			ipv4.setTercerOcteto(0);
			ipv4.setCuartoOcteto(0);
			break;
		}
		case 'B': {
			ipv4.setTercerOcteto(0);
			ipv4.setCuartoOcteto(0);
			break;
		}
		case 'C': {
			ipv4.setCuartoOcteto(0);
			break;
		}
		}
		return ipv4;
	}
	
	private static Ipv4 obtenerBroadCast(Ipv4 ip) {
		Ipv4 ipv4 = new Ipv4(ip.getPrimerOcteto(),ip.getSegundoOcteto(),ip.getTercerOcteto(),ip.getCuartoOcteto());
		ipv4.setClase(ip.getClase());
		switch (ipv4.getClase()) {
		case 'A': {
			ipv4.setSegundoOcteto(255);
			ipv4.setTercerOcteto(255);
			ipv4.setCuartoOcteto(255);
			break;
		}
		case 'B': {
			ipv4.setTercerOcteto(255);
			ipv4.setCuartoOcteto(255);
			break;
		}
		case 'C': {
			ipv4.setCuartoOcteto(255);
			break;
		}
		}
		return ipv4;
	}

	private static List<Ipv4> obtenerRangoHostMinMax(Ipv4 ip) {
		Ipv4 ipMin = new Ipv4(ip.getPrimerOcteto(),ip.getSegundoOcteto(),ip.getTercerOcteto(),ip.getCuartoOcteto());
		Ipv4 ipMax = new Ipv4(ip.getPrimerOcteto(),ip.getSegundoOcteto(),ip.getTercerOcteto(),ip.getCuartoOcteto());
		ipMax.setClase(ip.getClase());
		List<Ipv4> min_max = new ArrayList<>();
		ipMin.setCuartoOcteto(1);
		min_max.add(ipMin);
		switch (ipMax.getClase()) {
		case 'A': {
			ipMax.setSegundoOcteto(255);
			ipMax.setTercerOcteto(255);
			ipMax.setCuartoOcteto(254);
			min_max.add(ipMax);
			break;
		}
		case 'B': {
			ipMax.setTercerOcteto(255);
			ipMax.setCuartoOcteto(254);
			min_max.add(ipMax);
			break;
		}
		case 'C': {
			ipMax.setCuartoOcteto(254);
			min_max.add(ipMax);
			break;
		}
		}
		return min_max;
	}
	
	private static String cantidadHost(char[] arrayCharBit) {
		
		int cantidadHost = 0;
		for(char c: arrayCharBit) {
			if(c=='0') {
				cantidadHost++;
			}
		}
		Integer i  = (int)(Math.pow(2, cantidadHost)) -2;
		return Integer.toString(i);
	}
	
	private static MascaraDeSubRed conversorBitsAdecimales(char[] bits) {
		String mascara = "";
		for(char c : bits) {
			mascara += c;
		}
		
		mascara.trim();
		
		MascaraDeSubRed mascaraDeSubRed = new MascaraDeSubRed();
		mascaraDeSubRed.setPrimerOcteto(Integer.parseInt(mascara.substring(0, 8), 2));
		mascaraDeSubRed.setSegundoOcteto(Integer.parseInt(mascara.substring(8, 16), 2));
		mascaraDeSubRed.setTecerOcteto(Integer.parseInt(mascara.substring(16, 24), 2));
		mascaraDeSubRed.setCuartoOcteto(Integer.parseInt(mascara.substring(24), 2));
		
		
		return mascaraDeSubRed;
	}
	
	private static String obtenerCantHostPorSubred(char[] bits) {
		int potencia = 0;
	
		for(char c : bits) {
			if(c=='0') {
				potencia++;
			}
		}
		Integer i = (int) (Math.pow(2, potencia) - 2);
		return Integer.toString(i);
	}
	
	
	private static List<Ipv4> calculoRangoIpGeneradasPorSubRed(Ipv4 ip, MascaraDeSubRed mascaraDeSubRed, int cantidadSubredes) {
		
		int numeroSaltos = numeroSaltos(ip, mascaraDeSubRed);
		System.out.println("Cantidad de saltos: " + numeroSaltos);
		List<Ipv4> listaIpv4 = new ArrayList<>();
		
		switch (ip.getClase()) {
		case 'A': {
			int j = 0;

			for (int i = 0; i < cantidadSubredes; i++) {
				for (int x = 0; x < 2; x++) {
					Ipv4 ipNueva = new Ipv4();
					ipNueva.setPrimerOcteto(ip.getPrimerOcteto());
					ipNueva.setSegundoOcteto(j);
					ipNueva.setTercerOcteto(ip.getTercerOcteto());
					ipNueva.setCuartoOcteto(ip.getCuartoOcteto());
					listaIpv4.add(ipNueva);
					if (x == 1)
						break;
					j = j + (numeroSaltos - 1);
				}
				j++;
			}
			break;
		}
		case 'B': {
			int j = 0;

			for (int i = 0; i < cantidadSubredes; i++) {
				for (int x = 0; x < 2; x++) {
					Ipv4 ipNueva = new Ipv4();
					ipNueva.setPrimerOcteto(ip.getPrimerOcteto());
					ipNueva.setSegundoOcteto(ip.getSegundoOcteto());
					ipNueva.setTercerOcteto(j);
					if(x==0) {
						ipNueva.setCuartoOcteto(ip.getCuartoOcteto());
					}else {
						ipNueva.setCuartoOcteto(255);
					}
					
					listaIpv4.add(ipNueva);
					if (x == 1)
						break;
					j = j + (numeroSaltos - 1);
				}
				j++;
			}
			break;
		}

		case 'C': {
			int j = 0;

			for (int i = 0; i < cantidadSubredes; i++) {
				for (int x = 0; x < 2; x++) {
					Ipv4 ipNueva = new Ipv4();
					ipNueva.setPrimerOcteto(ip.getPrimerOcteto());
					ipNueva.setSegundoOcteto(ip.getSegundoOcteto());
					ipNueva.setTercerOcteto(ip.getTercerOcteto());
					ipNueva.setCuartoOcteto(j);
					listaIpv4.add(ipNueva);
					if (x == 1)
						break;
					j = j + (numeroSaltos - 1);
				}
				j++;
			}
			break;
		}

		}
		return listaIpv4;

	}
	
	
	private static int numeroSaltos(Ipv4 ip, MascaraDeSubRed mascaraDeSubRed) {
		int cantidadSaltos = 0;
		switch(ip.getClase()) {
			case 'A': cantidadSaltos = 256 - mascaraDeSubRed.getSegundoOcteto();break;
			case 'B': cantidadSaltos = 256 - mascaraDeSubRed.getTecerOcteto();break;
			case 'C': cantidadSaltos = 256 - mascaraDeSubRed.getCuartoOcteto();break;
		}
		
		return cantidadSaltos;
	}


}

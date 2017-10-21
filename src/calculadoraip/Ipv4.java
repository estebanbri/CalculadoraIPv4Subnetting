package calculadoraip;


public class Ipv4{
	private int primerOcteto;
	private int	segundoOcteto;
	private int tercerOcteto;
	private int cuartoOcteto;
	private char clase;
	private MascaraDeRed mascaraDeRed;
	
	
	public Ipv4() {
		super();
	}

	public Ipv4(int primerOcteto, int segundoOcteto, int tecerOcteto, int cuartoOcteto) {
		super();
		this.primerOcteto = primerOcteto;
		this.segundoOcteto = segundoOcteto;
		this.tercerOcteto = tecerOcteto;
		this.cuartoOcteto = cuartoOcteto;
	}

	public int getPrimerOcteto() {
		return primerOcteto;
	}

	public void setPrimerOcteto(int primerOcteto) {
		this.primerOcteto = primerOcteto;
	}

	public int getSegundoOcteto() {
		return segundoOcteto;
	}

	public void setSegundoOcteto(int segundoOcteto) {
		this.segundoOcteto = segundoOcteto;
	}

	public int getTercerOcteto() {
		return tercerOcteto;
	}

	public void setTercerOcteto(int tecerOcteto) {
		this.tercerOcteto = tecerOcteto;
	}

	public int getCuartoOcteto() {
		return cuartoOcteto;
	}

	public void setCuartoOcteto(int cuartoOcteto) {
		this.cuartoOcteto = cuartoOcteto;
	}

	public char getClase() {
		return clase;
	}

	public void setClase(char clase) {
		this.clase = clase;
	}
	
	public MascaraDeRed getMascaraDeRed() {
		return mascaraDeRed;
	}

	public void setMascaraDeRed(MascaraDeRed mascaraDeRed) {
		this.mascaraDeRed = mascaraDeRed;
	}

	@Override
	public String toString() {
		return  primerOcteto + "." + 
				segundoOcteto + "." + 
				tercerOcteto + "." +
				cuartoOcteto;
	}
	
	
	
}
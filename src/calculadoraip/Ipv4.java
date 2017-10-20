package calculadoraip;


public class Ipv4{
	int primerOcteto;
	int	segundoOcteto;
	int tecerOcteto;
	int cuartoOcteto;
	char clase;
	MascaraDeRed mascaraDeRed;
	
	
	public Ipv4() {
		super();
	}

	public Ipv4(int primerOcteto, int segundoOcteto, int tecerOcteto, int cuartoOcteto) {
		super();
		this.primerOcteto = primerOcteto;
		this.segundoOcteto = segundoOcteto;
		this.tecerOcteto = tecerOcteto;
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

	public int getTecerOcteto() {
		return tecerOcteto;
	}

	public void setTecerOcteto(int tecerOcteto) {
		this.tecerOcteto = tecerOcteto;
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
				tecerOcteto + "." +
				cuartoOcteto;
	}
	
	
	
}
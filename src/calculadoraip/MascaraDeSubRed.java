package calculadoraip;

public class MascaraDeSubRed {
	private int primerOcteto;
	private int	segundoOcteto;
	private int tecerOcteto;
	private int cuartoOcteto;
	
	public MascaraDeSubRed() {
		super();
	}

	public MascaraDeSubRed(int primerOcteto, int segundoOcteto, int tecerOcteto, int cuartoOcteto) {
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
	
	@Override
	public String toString() {
		return  primerOcteto + "." + 
				segundoOcteto + "." + 
				tecerOcteto + "." +
				cuartoOcteto;
	}
	

}

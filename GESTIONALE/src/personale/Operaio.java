package personale;

public class Operaio extends Dipendente {
	private int numeroCantieri;
	
	public Operaio(String nome, String cognome) {
		super(nome, cognome);
		numeroCantieri = 0;
	}
	
	public void impegnaDipendente() {
		if(!getStato()) {
			super.impegnaDipendente();
			numeroCantieri++;
		}
	}
	
	public int getNumeroCantieri() {
		return numeroCantieri;
	}
}

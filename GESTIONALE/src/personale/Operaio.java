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
	
	public String toString() {
		return super.toString() + "[numero cantieri="+numeroCantieri+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Operaio op = (Operaio) o;
		return numeroCantieri ==op.numeroCantieri; 
	}
}

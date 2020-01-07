package personale;

public class Operaio extends Dipendente {
	private int numeroCantieri;
	
	public Operaio(String nome, String cognome) {
		super(nome, cognome);
		numeroCantieri = 0;
	}
	/**
	 * Impegna l'operaio ed aumenta il numero di cantieri a cui ha lavorato 
	 */
	public void impegnaDipendente() {
		if(!getStato()) {
			super.impegnaDipendente();
			numeroCantieri++;
		}
	}
	
	/**
	 * Contrassegna l'operaio come pagato e restituisce l'ammontare del suo stipendio. 
	 * Questo parte da un fisso più un bonus per ogni cantiere a cui ha lavorato
	 */
	public double paga() {
		super.setStatoPagamento();
		return Pagabile.STIPENDIO_OPERAIO + Pagabile.BONUS_OPERAIO * numeroCantieri;
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
	public Operaio clone() {
		return (Operaio) super.clone();
	}
}

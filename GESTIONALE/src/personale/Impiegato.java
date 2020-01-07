package personale;

public class Impiegato extends Dipendente {
	private int numeroOreSettimanali;
	
	public Impiegato(String nome, String cognome, int numeroOreSettimanali) {
		super(nome, cognome);
		this.numeroOreSettimanali = numeroOreSettimanali;
	}
	
	/**
	 * Contrassegna l'impiegato come pagato e restituisce lo stipendio che varia in base a quante ore a settimana lavora
	 */
	public double paga() {
		super.setStatoPagamento();
		if (numeroOreSettimanali >= Pagabile.ORE_PARTTIME && numeroOreSettimanali <= Pagabile.ORE_FULLTIME) return Pagabile.STIPENDIO_IMPIEGATOFT;
		else if (numeroOreSettimanali <= Pagabile.ORE_PARTTIME) return Pagabile.STIPENDIO_IMPIEGATOPT;
		else return Pagabile.STIPENDIO_IMPIEGATOFT + ((Pagabile.ORE_FULLTIME - numeroOreSettimanali) * Pagabile.BONUS_IMPIEGATO);
	}
	
	public int getNumeroOreSettimanali() {
		return numeroOreSettimanali;
	}
	
	public String toString() {
		return super.toString()+"[numeroPreSettimanali="+numeroOreSettimanali+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Impiegato i = (Impiegato) o;
		
		return numeroOreSettimanali == i.numeroOreSettimanali; 
	}
}

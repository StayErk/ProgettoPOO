package personale;

public class Impiegato extends Dipendente {
	private int numeroOreSettimanali;
	
	public Impiegato(String nome, String cognome, int numeroOreSettimanali) {
		super(nome, cognome);
		this.numeroOreSettimanali = numeroOreSettimanali;
	}
	
	public double paga() {
		super.setStatoPagamento();
		if (numeroOreSettimanali >= Pagabile.ORE_PARTTIME && numeroOreSettimanali <= Pagabile.ORE_FULLTIME) return Pagabile.STIPENDIO_IMPIEGATOFT;
		else if (numeroOreSettimanali <= Pagabile.ORE_PARTTIME) return Pagabile.STIPENDIO_IMPIEGATOPT;
		else return Pagabile.STIPENDIO_IMPIEGATOFT + ((Pagabile.ORE_FULLTIME - numeroOreSettimanali) * Pagabile.BONUS_IMPIEGATO);
	}
	
	public int getNumeroOreSettimanali() {
		return numeroOreSettimanali;
	}
}

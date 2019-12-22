package personale;

public class Impiegato extends Dipendente {
	private int numeroOreSettimanali;
	
	public Impiegato(String nome, String cognome, int numeroOreSettimanali) {
		super(nome, cognome);
		this.numeroOreSettimanali = numeroOreSettimanali;
	}
	
	public int getNumeroOreSettimanali() {
		return numeroOreSettimanali;
	}
}

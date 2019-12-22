package personale;

public class Quadro extends Dipendente {
	private int numeroGiorniConsulenza;
	
	public Quadro(String nome, String cognome, int numeroGiorniConsulenza) {
		super(nome, cognome);
		this.numeroGiorniConsulenza = numeroGiorniConsulenza;
	}
	
	public int getNumeroGiorniConsulenza() {
		return numeroGiorniConsulenza;
	}
}

package personale;

public class Dirigente extends Dipendente {
	private double stipendio;
	
	public Dirigente(String nome, String cognome, double stipendio) {
		super(nome, cognome);
		this.stipendio = stipendio; 
	}
	
	public double paga() {
		super.paga();
		return stipendio;
	}
	
}

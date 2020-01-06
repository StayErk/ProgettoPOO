package personale;

public class Dirigente extends Dipendente implements Responsabile{
	private String titoloStudio;
	
	public Dirigente(String nome, String cognome, String titoloStudio) {
		super(nome, cognome);
		this.titoloStudio = titoloStudio;
	}
	
	public String getTitoloStudio(){
		return titoloStudio;
	}
	
	public double paga() {
		super.setStatoPagamento();
		return Pagabile.STIPENDIO_DIRIGENTE;
	}
	
	public String toString() {
		return super.toString() + "[titolo di Studio="+titoloStudio+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Dirigente d = (Dirigente) o;
		return titoloStudio.equals(d.titoloStudio);
	}
	
	public Dirigente clone() {
		return (Dirigente) super.clone();
	}
}

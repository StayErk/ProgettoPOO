package personale;

public class Dirigente extends Dipendente {
	private String titoloStudio;
	
	public Dirigente(String nome, String cognome, String titoloStudio) {
		super(nome, cognome);
		this.titoloStudio = titoloStudio;
	}
	
	public String getTitoloStudio(){
		return titoloStudio;
	}
	
	
}

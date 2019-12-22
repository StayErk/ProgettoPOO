package personale;

import java.util.ArrayList;
import utils.Estraibile;
public class Squadra {
	private Dipendente caposquadra;
	private ArrayList<Dipendente> gruppo;
	
	public Squadra(Dipendente caposquadra) {
		Estraibile<Dipendente> estrattore = (p) -> p.getClass().getSimpleName().equalsIgnoreCase("dirigente") || p.getClass().getSimpleName().equalsIgnoreCase("quadro"); 
		if(estrattore.estrai(caposquadra)) {
			this.caposquadra = caposquadra;
		}
		else throw new IllegalArgumentException("Inserire un dirigente o un caposquadra");
		gruppo = new ArrayList<Dipendente>();
	}
	
	public Dipendente getCaposquadra() {
		return caposquadra;
	}
	
	public ArrayList<Dipendente> getGruppo(){
		return gruppo;
	}
	
	public void aggiungiOperaio(Dipendente daAggiungere) {
		if (gruppo.contains(daAggiungere) || daAggiungere.getStato()) return;
		gruppo.add(daAggiungere);
	}
	
	public void impegnaSquadra() {
		caposquadra.impegnaDipendente();
		for(Dipendente d:gruppo) {
			d.impegnaDipendente();
		}
	}
	
	public void liberaSquadra() {
		caposquadra.liberaDipendente();
		for(Dipendente d:gruppo) {
			d.liberaDipendente();
		}
	}
	
	
	
	
}

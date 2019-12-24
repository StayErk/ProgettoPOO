package personale;

import java.util.ArrayList;
import utils.Estraibile;
public class Squadra {
	private Responsabile caposquadra;
	private ArrayList<Dipendente> gruppo;
	
	public Squadra(Responsabile caposquadra) { 
		this.caposquadra = caposquadra;
		gruppo = new ArrayList<Dipendente>();
	}
	
	public Responsabile getCaposquadra() {
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
		Dipendente d = (Dipendente) caposquadra;
		d.impegnaDipendente();
		for(Dipendente dip:gruppo) {
			dip.impegnaDipendente();
		}
	}
	
	public void liberaSquadra() {
		Dipendente d = (Dipendente) caposquadra;
		d.liberaDipendente();
		for(Dipendente dip:gruppo) {
			dip.liberaDipendente();
		}
	}
	
	
	
	
}

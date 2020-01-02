package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import personale.Dipendente;
import personale.Quadro;
import utils.Estraibile;
public class Squadra implements Serializable {
	private Quadro caposquadra;
	private ArrayList<Dipendente> gruppo;
	
	public Squadra(Quadro caposquadra) { 
		if (caposquadra.getStato()) throw new IllegalArgumentException();
		this.caposquadra = caposquadra;
		gruppo = new ArrayList<Dipendente>();
	}
	
	public Quadro getCaposquadra() {
		return caposquadra;
	}
	
	public ArrayList<Dipendente> getGruppo(){
		return gruppo;
	}
	
	public void aggiungiOperaio(Dipendente daAggiungere) {
		if (gruppo.contains(daAggiungere) || daAggiungere.getStato()) return;
		gruppo.add(daAggiungere);
	}
	
	public void aggiungiOperaio(ArrayList<Dipendente> daAggiungere) {
		for(Dipendente d:daAggiungere) {
			if (gruppo.contains(d) || d.getStato()) return;
			gruppo.add(d);
		}
			
	}
	
	public void impegnaSquadra() {
		caposquadra.impegnaDipendente();
		for(Dipendente dip:gruppo) {
			dip.impegnaDipendente();
		}
	}
	
	public void liberaSquadra() {
		caposquadra.liberaDipendente();
		for(Dipendente dip:gruppo) {
			dip.liberaDipendente();
		}
	}
	
	public String toString() {
		return getClass().getName()+"[responsabile="+caposquadra+", gruppo="+gruppo+"]";
	}
	
	
	
	
}

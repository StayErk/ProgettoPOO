package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import amministrativo.RisorseUmane;
import materiale.MaterialeDaCostruzione;
import personale.Dipendente;
import personale.Responsabile;
import utils.Estraibile;
import amministrativo.RisorseMateriali;

public class RepartoOperativo implements Serializable{
	private ArrayList<Cantiere> cantieri;
	
	public RepartoOperativo() {
		cantieri = new ArrayList<Cantiere>();
	}
	
	
	public ArrayList<Cantiere> getCantieriAperti(){
		return cantieri;
	}
	
	
	public void apriCantiere(double valoreIniziale, ArrayList<MaterialeDaCostruzione> materialiRichiesti, Responsabile capocantiere, String committente) {
		double valoreMateriali = 0;
		for(MaterialeDaCostruzione m: materialiRichiesti) {
			valoreMateriali += m.getValoreProdotto();
		}
		double valoreCantiere = valoreIniziale + valoreMateriali;
		Cantiere cantiere = new Cantiere(valoreCantiere, capocantiere, committente);
		cantieri.add(cantiere);
	}
	
	public void aggiungiSquadraACantiere(Cantiere cantiereDaModificare, Squadra team) {
		for(Cantiere c:cantieri) {
			if(c.equals(cantiereDaModificare)) {
				c.aggiungiSquadra(team);
			}
		}
	}
	
	public double chiudiCantiere(Cantiere cantiereDaChiudere) {
		Cantiere daRimuovere = null;
		for(Cantiere c:cantieri) {
			if(c.equals(cantiereDaChiudere)) {
				daRimuovere = c;
			}
		}
		if(daRimuovere != null) cantieri.remove(cantieri.indexOf(daRimuovere));
		return 0;
	}
	
	
	public String toString() {
		return getClass().getName()+"[cantieri="+cantieri+"]";
	}
}

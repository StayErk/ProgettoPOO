package operativo;

import java.util.ArrayList;

import amministrativo.RisorseUmane;
import materiale.MaterialeDaCostruzione;
import personale.Dipendente;
import personale.Squadra;
import utils.Estraibile;
import amministrativo.RisorseMateriali;

public class RepartoOperativo {
	private ArrayList<Cantiere> cantieri;
	
	public RepartoOperativo(RisorseUmane hr, RisorseMateriali mr) {
		cantieri = new ArrayList<Cantiere>();
	}
	
	public ArrayList<Cantiere> getCantieriAperti(){
		return cantieri;
	}
	
	private void apriCantiere(double valoreIniziale, ArrayList<MaterialeDaCostruzione> materialiRichiesti, Dipendente capocantiere) {
		double valoreMateriali = 0;
		for(MaterialeDaCostruzione m: materialiRichiesti) {
			valoreMateriali += m.getValoreProdotto();
		}
		double valoreCantiere = valoreIniziale + valoreMateriali;
		Cantiere cantiere = new Cantiere(valoreCantiere, capocantiere);
	}
	
	private void aggiungiSquadraACantiere(Cantiere cantiereDaModificare, Squadra team) {
		for(Cantiere c:cantieri) {
			if(c.equals(cantiereDaModificare)) {
				c.aggiungiSquadra(team);
			}
		}
	}
	
	private double chiudiCantiere(Cantiere cantiereDaChiudere) {
		for(Cantiere c:cantieri) {
			if(c.equals(cantiereDaChiudere)) {
				return c.chiusuraCantiere();
			}
		}
		return 0;
	}
}

package operativo;

import java.util.ArrayList;

import amministrativo.RisorseUmane;
import personale.Dipendente;
import personale.Squadra;
import amministrativo.RisorseMateriali;

public class RepartoOperativo {
	private ArrayList<Cantiere> cantieri;
	
	public RepartoOperativo(RisorseUmane hr, RisorseMateriali mr) {
		cantieri = new ArrayList<Cantiere>();
	}
	
	public ArrayList<Cantiere> getCantieriAperti(){
		return cantieri;
	}
	
	private void apriCantiere(double valoreIniziale, double valoreMateriali, Dipendente capocantiere) {
		double valoreCantiere = valoreIniziale + valoreMateriali;
		//TODO implementare insterfaccia per scegliere capocantiere
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
				double toReturn = c.getValoreCantiere();
				cantieri.remove(c);
				return toReturn;
			}
		}
		return 0;
	}
}

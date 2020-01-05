package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import amministrativo.RisorseUmane;
import materiale.MaterialeDaCostruzione;
import personale.Dipendente;
import personale.Responsabile;
import utils.Estraibile;
import amministrativo.RisorseMateriali;

/**
 * Questa classe rappresenta il concetto di reparto operativo, il reparto che si occupa della gestione dei cantieri aperti e quindi
 * della loro relativa apertura e chiusura. Nonché si occupa dell'aggiunta di una squadra ad un cantiere
 * @author Andrea Ercolino
 *
 */

public class RepartoOperativo implements Serializable{
	private ArrayList<Cantiere> cantieri;
	
	public RepartoOperativo() {
		cantieri = new ArrayList<Cantiere>();
	}
	
	
	public ArrayList<Cantiere> getCantieriAperti(){
		return cantieri;
	}
	
	
	/**
	 * Questo metodo gestisce l'apertura di un cantiera da aggiungere poi alla lista di Cantieri aperti.
	 * @param valoreIniziale valore di partenza del cantiere
	 * @param materialiRichiesti lista dei materiali richiesti. Il valore di tali materiali si va a sommare al valore iniziale del cantiere
	 * @param capocantiere oggetto di tipo responsabile, se il valore del cantiere supera complessivamente i 500.000 euro questo deve essere di tipo Dirigente
	 * @param committente nome del cliente che ha commissionato il cantiere
	 */
	public void apriCantiere(double valoreIniziale, ArrayList<MaterialeDaCostruzione> materialiRichiesti, Responsabile capocantiere, String committente) {
		double valoreMateriali = 0;
		for(MaterialeDaCostruzione m: materialiRichiesti) {
			valoreMateriali += m.getValoreProdotto();
		}
		double valoreCantiere = valoreIniziale + valoreMateriali;
		Cantiere cantiere = new Cantiere(valoreCantiere, capocantiere, committente);
		cantieri.add(cantiere);
	}
	
	/**
	 * Questo metodo, dato un cantiere ed una squadra permette di aggiungere uest'ultima ad un cantiere
	 * @param cantiereDaModificare 
	 * @param team squadra da aggiungere
	 */
	public void aggiungiSquadraACantiere(Cantiere cantiereDaModificare, Squadra team) {
		for(Cantiere c:cantieri) {
			if(c.equals(cantiereDaModificare)) {
				c.aggiungiSquadra(team);
			}
		}
	}
	
	/**
	 * Dato un cantiere lo rimuove dalla lista dei cantieri aperti restuendone il valore.
	 * Se il cantiere non è presente lancia IllegalArgumentException()
	 * @param cantiereDaChiudere
	 * @return valore del cantiere chiuso
	 */
	public double chiudiCantiere(Cantiere cantiereDaChiudere) {
		Cantiere daRimuovere = null;
		for(Cantiere c:cantieri) {
			if(c.equals(cantiereDaChiudere)) {
				daRimuovere = c;
			}
		}
		if(daRimuovere == null) throw new IllegalArgumentException(); 
		daRimuovere.chiusuraCantiere();
		cantieri.remove(cantieri.indexOf(daRimuovere));
		return daRimuovere.getValoreCantiere();
		
	}
	
	
	public String toString() {
		return getClass().getName()+"[cantieri="+cantieri+"]";
	}
}

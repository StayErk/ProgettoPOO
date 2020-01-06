package amministrativo;

import materiale.*;
import personale.Dipendente;
import utils.Estraibile;
import utils.Estrattore;

import java.util.ArrayList;

import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonPresenteException;
import esterno.Fornitore;
import materiale.*;

/**
 * Questa classe rappresenta il concetto specializzato della sezione del Reparto Amministrativo che si occupa dell'acquisto di materiali e dello
 * stockaggio nel magazzino
 * @author Andrea Ercolino
 *
 */
public class RisorseMateriali extends RepartoAmministrativo {
	private Magazzino magazzino;
	private ArrayList<Fornitore> fornitori;
	
	public RisorseMateriali(double capitale, Magazzino magazzino) {
		super(capitale);
		this.magazzino = magazzino;
		fornitori = new ArrayList<Fornitore>();
	}
	
	public Magazzino getMagazzino() {
		return magazzino;
	}
	
	public ArrayList<Fornitore> getFornitori(){
		return fornitori;
	}
	
	/**
	 * Questo metodo permette di aggiungere un fornitore alla lista dei fornitori che rifoniscono l'azienda
	 * Precondizione: il fornitore non deve essere presente nella lista
	 * Postcondizione: il fornitore vienie aggiunto alla lista dei fornitori
	 * @param daAggiungere il Fornitore da aggiungere alla lista
	 */
	public void aggiungiFornitore(Fornitore daAggiungere) {
		if(fornitori.contains(daAggiungere)) return;
		fornitori.add(daAggiungere);
	}
	
	/**
	 * Questo metodo permette di rimuovere un fornitore dalla lista dei fornitori che riforniscono l'azienda
	 * Precondizione: Rimuove un fornitore dalla lista dei fornitori se questo è presente
	 * Postcondizione: la lista non conterrà più quel fornitore
	 * @param daRimuovere il Fornitore da rimuovere dalla lista
	 */
	public void rimuoviFornitore(Fornitore daRimuovere) {
		if(fornitori.contains(daRimuovere)) {
			 fornitori.remove(daRimuovere);
		}
	}
	
	/**
	 * Questo metodo permette di acquistare un prodotto da un dato fornitore. Il metodo lancia l'eccezione controllata
	 * CapacitaSuperataException nel caso in cui il prodotto acquistato non entri all'interno del magazzino 
	 * @param doveAcquistare Fornitore dal quale acquistare la merce (se presente nel catalogo del dato fornitore)
	 * @param daAcquistare Prodotto da acquistare
	 * @throws CapacitaSuperataException eccezione lanciata nel caso in cui il prodotto acquistato non entri nel magazzino
	 */
	public void acquistaDaFornitore(Fornitore doveAcquistare, MaterialeDaCostruzione daAcquistare) throws CapacitaSuperataException{
		if(fornitori.contains(doveAcquistare)) {
			Fornitore<MaterialeDaCostruzione> f = fornitori.get(fornitori.indexOf(doveAcquistare));
			if(f.getCatalogo().contains(daAcquistare)) {
				ArrayList<MaterialeDaCostruzione> catalogo = f.getCatalogo();
				effettuaSpesa(daAcquistare.getValoreProdotto());
				magazzino.aggiungiMateriale(daAcquistare);			}
		}
	}
	
	public void acquistaMateriali(MaterialeDaCostruzione daAcquistare) throws CapacitaSuperataException {
		effettuaSpesa(daAcquistare.getValoreProdotto());
		magazzino.aggiungiMateriale(daAcquistare);
	}

	public void acquistaMateriali(ArrayList<MaterialeDaCostruzione> daAcquistare) throws CapacitaSuperataException{
		for(MaterialeDaCostruzione m:daAcquistare) {
				effettuaSpesa(m.getValoreProdotto());
				magazzino.aggiungiMateriale(m);
		}
	}
	
	/**
	 * Questo metodo permette di togliere un prodotto dal magazzino del caso in cui questo serva  ad un cantiere.
	 * @param codiceProdotto
	 * @return il MaterialeDaCostruzione scaricato dal magazzino. 
	 */
	public MaterialeDaCostruzione scaricaMateriale(String codiceProdotto) {
		MaterialeDaCostruzione rimosso = magazzino.rimuoviMateriale(codiceProdotto);
		return rimosso;
	}
	
	public ArrayList<MaterialeDaCostruzione> scegliMateriale(Estraibile<MaterialeDaCostruzione> criterio) {
		ArrayList<MaterialeDaCostruzione> estratto = new ArrayList<MaterialeDaCostruzione>();
		Estrattore<MaterialeDaCostruzione> estrattore = new Estrattore<MaterialeDaCostruzione>(magazzino.getMaterialiInMagazzino(), criterio);
		estratto = estrattore.estrai();
		return estratto;
	}
	
	/**
	 * Si occupa di chiudere un contratto, aggiunge il valore del contratto chiuso più un guadagno alle entrate dell'azienda
	 * @param valore valore economico del contratto chiuso
	 */
	public void chiusuraContratto(double valore) {
		aggiungiEntrate(valore + (valore / 100 * 35));
	}
	
	public String toString() {
		return super.toString() + "[Magazzino="+magazzino+", fornitori="+fornitori+"]";
	}
}

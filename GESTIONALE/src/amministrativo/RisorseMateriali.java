package amministrativo;

import materiale.*;
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
	 * @param daAggiungere il Fornitore da aggiungere alla lista
	 */
	public void aggiungiFornitore(Fornitore daAggiungere) {
		if(fornitori.contains(daAggiungere)) return;
		fornitori.add(daAggiungere);
	}
	
	/**
	 * Questo metodo permette di rimuovere un fornitore dalla lista dei fornitori che riforniscono l'azienda
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
			Fornitore f = fornitori.get(fornitori.indexOf(doveAcquistare));
			if(f.getCatalogo().contains(daAcquistare)) {
				ArrayList<MaterialeDaCostruzione> catalogo = f.getCatalogo();
				effettuaSpesa(daAcquistare.getValoreProdotto());
				magazzino.aggiungiMateriale(daAcquistare);			}
		}
	}
	
	/**
	 * Questo metodo permette di togliere un prodotto dal magazzino del caso in cui questo serva  ad un cantiere.
	 * @param codiceProdotto
	 * @return restituisce il valore del prodotto scaricato dal magazzino 
	 */
	public MaterialeDaCostruzione scaricaMateriale(String codiceProdotto) {
		MaterialeDaCostruzione rimosso = magazzino.rimuoviMateriale(codiceProdotto);
		return rimosso;
	}
	
	/**
	 * Si occupa di chiudere un contratto, aggiunge il valore del contratto chiuso più un guadagno alle entrate dell'azienda
	 * @param valore valore economico del contratto chiuso
	 */
	public void chiusuraContratto(double valore) {
		aggiungiEntrate(valore + (valore / 100 * 35));
	}
	
	public String toString() {
		return getClass().getName() + "[Magazzino="+magazzino+", fornitori="+fornitori+"]";
	}
}

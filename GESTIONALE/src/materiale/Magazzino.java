package materiale;

import java.util.ArrayList;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonPresenteException;

/**
 * Questa classe rappresenta il concetto di Magazzino. Il Magazzino si occupa di conservare i materiali acquistati dall'azienda, permette di aggiungere o 
 * rimuovere i suddetti materiali entro un certo limite di capacità.
 * @author Andrea Ercolino
 *
 */
public class Magazzino<T extends MaterialeDaCostruzione> {
	private int capacitaMax;
	private int caricoAttuale;
	private double valoreMagazzino;
	private ArrayList<T> materiali;
	
	public Magazzino(int capacitaMax) {
		this.capacitaMax = capacitaMax;
		valoreMagazzino = 0;
	}
	
	public int getCapacitàMax() {
		return capacitaMax;
	}
	
	public double getValoreMagazzino() {
		return valoreMagazzino;
	}
	
	/**
	 * Questo metodo restituisce la lista di materiali presenti nel magazzino
	 * @return
	 */
	public ArrayList<T> getMaterialiInMagazzino() {
		return materiali;
	}
	
	/**
	 * Questo metodo permette di aggiungere un MaterialeDaCostruzione al magazzino se l'aggiunta di quest'ultimo non implica
	 * il superamento della capacità massima del Magazzino.
	 * @param daAggiungere Materiale da aggiungere al magazzino
	 * @throws CapacitaSuperataException eccezione controllata lanciata nel caso in cui il prodotto non può essere aggiunto perché verrebbe superata la 
	 * capacità massima del magazzino
	 */
	public void aggiungiMateriale(T daAggiungere) throws CapacitaSuperataException{
		if(daAggiungere.getPeso() + caricoAttuale > capacitaMax) throw new CapacitaSuperataException();
		else {
			caricoAttuale += daAggiungere.getPeso();
			materiali.add(daAggiungere);
		}
		
	}
	
	/**
	 * Questo metodo permette di rimuovere un MaterialeDaCostruzione dal magazzino quest'ultimo è presente; altrimenti lancia l'eccezione non controllata
	 * ProdottoNonPresenteException 
	 * @param codiceProdotto codice del prodotto da rimuovere
	 * @return MaterialeDaCostruzione rimosso.
	 */
	public T rimuoviMateriale(String codiceProdotto) {
		for (T m:materiali) {
			if(m.getCodiceProdotto().equalsIgnoreCase(codiceProdotto)) {
				caricoAttuale -= m.getPeso();
				materiali.remove(materiali.indexOf(m));
				return m;
			}
		}
		throw new ProdottoNonPresenteException();
	}
}

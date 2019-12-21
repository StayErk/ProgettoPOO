package amministrativo;

import java.io.Serializable;


/**
 * Questa classe rappresenta il concetto di Reparto Amministrativo che viene specializzato poi in Risorse Umane
 * e Risorse Materiali. Consente di aumentare il Capitale o effettuare spese.
 * @author Andrea Ercolino
 *
 */
public abstract class RepartoAmministrativo implements Serializable{
	private static double capitale;
	
	public RepartoAmministrativo(double capitaleIniziale) {
		capitale = capitaleIniziale;
	}
	
	/**
	 * Aggiunge una determinata somma di denaro al capitale dell'azienda.
	 * Precondizioni: paramentro >= 0
	 * Postcondizioni: capitale nell'istante t = capitale nell'istante t-1 + guadagno 
	 * @param guadagno guadagno netto da aggiungere al capitale
	 */
	public void aggiungiEntrate(double guadagno) {
		if(guadagno < 0) throw new IllegalArgumentException();
		capitale += guadagno;
	}
	
	/**
	 * Detrae una data somma di denato dal capitale dell'azienda.
	 * Precondizioni: parametro >= 0
	 * Postcondizioni: capitale nell'istante t = capitale nell'istante t-1 - spesa
	 * @param spesa spesa netta da detrarre dal capitale
	 */
	public void effettuaSpesa(double spesa) {
		if(spesa < 0) throw new IllegalArgumentException();
		capitale -= spesa;
	}
	
	/**
	 * 
	 * @return il capitale attuale dell'impresa
	 */
	public double getCapitale() {
		return capitale;
	}
	
}

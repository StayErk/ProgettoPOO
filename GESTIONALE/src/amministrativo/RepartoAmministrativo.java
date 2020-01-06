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
	private double capitaleEffettivo;
	
	public RepartoAmministrativo(double capitaleIniziale) {
		capitaleEffettivo = capitaleIniziale;
		capitale = capitaleEffettivo;
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
		equilibraCapitale();
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
		equilibraCapitale();
	}
	
	public void equilibraCapitale() {
		capitaleEffettivo = capitale;
	}
	
	/**
	 * 
	 * @return il capitale attuale dell'impresa
	 */
	public double getCapitale() {
		equilibraCapitale();
		return capitale;
	}
	
	public void equilibraCapitaleInverso() {
		capitale = capitaleEffettivo;
	}
	
	public String toString() {
		return getClass().getName()+"[capitaleEffettivo="+capitaleEffettivo+", capitale=" + capitale+"]";
	}
	
}

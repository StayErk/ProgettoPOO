package operativo;

import java.io.Serializable;
import java.util.ArrayList;
import personale.*;

/**
 * La Classe cantiere rappresenta il concetto astratto di cantiere. Caratterizzato da un valore, un  Responsabile, un insieme di squadre
 * e il nomitavo del cliente che l'ha commissionato. 
 * @author Andrea Ercolino
 *
 */
public class Cantiere implements Serializable{
	private double valore;
	private Responsabile capocantiere;
	private ArrayList<Squadra> squadre;
	private String cliente;
	
	public Cantiere(double valore, Responsabile capocantiere, String cliente) {
		this.valore = valore;
		Dipendente d = (Dipendente) capocantiere;
		if(d.getStato()) throw new IllegalArgumentException(); 
		if(this.valore > 500000) {
			if(capocantiere instanceof Dirigente) {
				this.capocantiere = capocantiere;
			}
			else throw new IllegalArgumentException();
		}
		this.capocantiere = capocantiere;
		d.impegnaDipendente();
		squadre = new ArrayList<Squadra>();
		this.cliente = cliente;
	}
	
	public Responsabile getCapocantiere() {
		return capocantiere;
	}
	
	public double getValoreCantiere() {
		return valore;
	}
	
	public ArrayList<Squadra> getSquadre(){
		return squadre;
	}
	
	public String getCliente() {
		return cliente;
	}
	/**
	 * Aggiunge una squadra all'insieme di squadre che lavorano al cantiere. Una volta aggiunta tutti i dipendenti che 
	 * si trovano all'interno della squadra risulteranno impegnati
	 * @param team
	 */
	public void aggiungiSquadra(Squadra team) {
		team.impegnaSquadra();
		squadre.add(team);
	}
	
	/**
	 * Effettua la procedura di chiusura di un cantiere. Libera i dipendenti di tutte le squadre che hanno lavorato al cantiere e il Responsabile del cantiere
	 * 
	 * @return restituisce il valore del cantiere chiuso
	 */
	public double chiusuraCantiere() {
		Dipendente d = (Dipendente) capocantiere;
		d.liberaDipendente();
		for (Squadra team:squadre) {
			team.liberaSquadra();
		}
		return valore;
	}
	
	public String toString() {
		return getClass().getName()+"[capocantiere="+capocantiere+", squadre="+squadre+", valore="+valore+", cliente="+cliente+"]";
	}
}

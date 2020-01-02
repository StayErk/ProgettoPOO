package operativo;

import java.io.Serializable;
import java.util.ArrayList;
import personale.*;

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
	
	public void aggiungiSquadra(Squadra team) {
		team.impegnaSquadra();
		squadre.add(team);
	}
	
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

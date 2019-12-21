package operativo;

import java.util.ArrayList;
import personale.*;

public class Cantiere {
	private double valore;
	private Dipendente capocantiere;
	private ArrayList<Squadra> squadre;
	
	public Cantiere(double valore, Dipendente capocantiere) {
		this.valore = valore;
		this.capocantiere = capocantiere;
		squadre = new ArrayList<Squadra>();
	}
	
	public Dipendente getCapocantiere() {
		return capocantiere;
	}
	
	public double getValoreCantiere() {
		return valore;
	}
	
	public ArrayList<Squadra> getSquadre(){
		return squadre;
	}
	
	public void aggiungiSquadra(Squadra team) {
		squadre.add(team);
	}
}

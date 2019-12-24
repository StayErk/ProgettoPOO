package operativo;

import java.util.ArrayList;
import personale.*;

public class Cantiere {
	private double valore;
	private Responsabile capocantiere;
	private ArrayList<Squadra> squadre;
	
	public Cantiere(double valore, Responsabile capocantiere) {
		this.valore = valore;
		if(this.valore > 500000) {
			if(capocantiere instanceof Dirigente) {
				this.capocantiere = capocantiere;
			}
			else throw new IllegalArgumentException();
		}
		squadre = new ArrayList<Squadra>();
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
}

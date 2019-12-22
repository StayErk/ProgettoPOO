package operativo;

import java.util.ArrayList;
import personale.*;

public class Cantiere {
	private double valore;
	private Dipendente capocantiere;
	private ArrayList<Squadra> squadre;
	
	public Cantiere(double valore, Dipendente capocantiere) {
		this.valore = valore;
		if((!capocantiere.getStato() && capocantiere instanceof  Dirigente) /*|| (!capocantiere.getStato() && valore < 500000 && capocantiere instanceof Quadro)*/) {
			this.capocantiere = capocantiere;
			this.capocantiere.impegnaDipendente();
		}
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
		team.impegnaSquadra();
		squadre.add(team);
	}
	
	public double chiusuraCantiere() {
		capocantiere.liberaDipendente();
		for (Squadra team:squadre) {
			team.liberaSquadra();
		}
		return valore;
	}
}

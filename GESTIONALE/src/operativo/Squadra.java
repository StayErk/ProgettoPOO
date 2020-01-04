package operativo;

import java.io.Serializable;
import java.util.ArrayList;

import personale.Dipendente;
import personale.Quadro;
import utils.Estraibile;
/**
 * Questa classe rappresenta il concetto di Squadra. Questa è formata da un Caposquadra da scegliere tra i dipendenti di tipo Quadro
 * e un gruppo di Dipendenti
 * @author Andrea Ercolino
 *
 */
public class Squadra implements Serializable {
	private Quadro caposquadra;
	private ArrayList<Dipendente> gruppo;
	
	/**
	 * se il caposquadra scelto è già impegnato lancia IllegalArgumentException()
	 * @param caposquadra 
	 */
	public Squadra(Quadro caposquadra) { 
		if (caposquadra.getStato()) throw new IllegalArgumentException();
		this.caposquadra = caposquadra;
		gruppo = new ArrayList<Dipendente>();
	}
	
	public Quadro getCaposquadra() {
		return caposquadra;
	}
	
	public ArrayList<Dipendente> getGruppo(){
		return gruppo;
	}
	
	/**
	 * Aggiunge un dipendente alla gruppo della squadra. 
	 * Precondizioni: il dipendente deve essere libero e non deve essere già presente all'interno della lista
	 * Postcondizione: il gruppo di dipendeti della squadra nell'istante t è uguale al gruppo di dipendenti nella squadra nell'istante t-1 +1
	 * @param daAggiungere dipendente da aggiungere
	 */
	public void aggiungiOperaio(Dipendente daAggiungere) {
		if (gruppo.contains(daAggiungere) || daAggiungere.getStato()) return;
		gruppo.add(daAggiungere);
	}
	
	/**
	 * Aggiunge una lista di dipendenti al gruppo della squadra
	 * Precondizioni: i dipendenti devono essere liberi e non devono essere già presenti all'interno della lista
	 * Postcondizione:  il gruppo di dipendeti della squadra nell'istante t è uguale al gruppo di dipendenti nella squadra nell'istante t-1 + n dove n 
	 * è la grandezza della lista da aggiungere
	 * @param daAggiungere
	 */
	public void aggiungiOperaio(ArrayList<Dipendente> daAggiungere) {
		for(Dipendente d:daAggiungere) {
			if (gruppo.contains(d) || d.getStato()) return;
			gruppo.add(d);
		}
			
	}
	/**
	 * Impegna il tutti i dipendenti che fanno parte della squadra
	 */
	public void impegnaSquadra() {
		caposquadra.impegnaDipendente();
		for(Dipendente dip:gruppo) {
			dip.impegnaDipendente();
		}
	}
	
	/**
	 * Libera tutti i dipendenti della squadra
	 */
	public void liberaSquadra() {
		caposquadra.liberaDipendente();
		for(Dipendente dip:gruppo) {
			dip.liberaDipendente();
		}
	}
	
	public String toString() {
		return getClass().getName()+"[responsabile="+caposquadra+", gruppo="+gruppo+"]";
	}
	
	
	
	
}

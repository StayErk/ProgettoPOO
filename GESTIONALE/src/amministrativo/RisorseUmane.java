package amministrativo;

import java.util.ArrayList;

import personale.Dipendente;
import personale.Estraibile;

public class RisorseUmane extends RepartoAmministrativo {
	private ArrayList<Dipendente> personaleImpegnato;
	private ArrayList<Dipendente> personaleLibero;
	
	public RisorseUmane(double capitale) {
		super(capitale);
		personaleImpegnato = new ArrayList<Dipendente>();
		personaleLibero = new ArrayList<Dipendente>();
	}
	
	/**
	 * restituisce la lista del personale non impegnato in attività di cantiere
	 * @return personale impegnato a lavoro in un cantiere
	 */
	public ArrayList<Dipendente> getPersonaleImpegnato(){
		return personaleImpegnato;
	}
	
	/**
	 * Restituisci la lista del personale non impegnato in attivita di cantiere
	 * @return personale non impegnato a lavoro in un cantiere
	 */
	public ArrayList<Dipendente> getPersonaleLibero(){
		return personaleLibero;
	}
	
	/**
	 * Paga tutti i dipendenti di una data lista in base allo stipendio che gli spetta
	 * @param tipoDIpendentiDaPagare tipo del dipendente da pagare
	 */
	public void pagaDipendenti(String tipoDipendentiDaPagare) {
		ArrayList<Dipendente> daPagare = estrattore(tipoDipendentiDaPagare);
		for(Dipendente d:daPagare) {
			d.paga();
		}
	}
	
	public void nuovoMeseFiscale(String tipoDipendenti) {
		ArrayList<Dipendente> daPagare = estrattore(tipoDipendenti);
		for(Dipendente d:daPagare) {
			d.resetStatoPagamento();
		}
	}
	
	/**
	 * Aggiunge un dipendete alla lista dei dipendi liberi
	 * @param dipendenteAssunto dipendente da assumere
	 */
	public void assumi(Dipendente dipendeteAssunto) {
		personaleLibero.add(dipendeteAssunto);
	}
	
	/**
	 * Data la matricola di un dipendente lo licenzia se si trova nella lista del personale libero
	 * @param matricolaDipendente matricola del dipendente da licenziare
	 */
	public void licenzia(int matricolaDipendente) {
		for (Dipendente d:personaleLibero) {
			if (d.getMatricolaDipendente() == matricolaDipendente)
				personaleLibero.remove(personaleLibero.indexOf(d));
		}
	}
	
	private ArrayList<Dipendente> estrattore(String criterio){
		ArrayList<Dipendente> estratti = new ArrayList<Dipendente>();
		Estraibile<Dipendente> estrattore = (p)-> criterio.equalsIgnoreCase(p.getClass().getSimpleName());
		for(Dipendente d:personaleLibero) {
			if(estrattore.estrai(d) && !d.getStatoPagamento())
				estratti.add(d);
		}
		for(Dipendente d:personaleImpegnato) {
			if(estrattore.estrai(d) && !d.getStatoPagamento())
				estratti.add(d);
		}
		return estratti;
	}
	
}

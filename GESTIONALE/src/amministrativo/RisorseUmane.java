package amministrativo;

import java.util.ArrayList;

import personale.Dipendente;
import personale.Dirigente;
import personale.Operaio;
import personale.Impiegato;
import personale.Pagabile;
import personale.Quadro;
import utils.Estraibile;
import utils.Estrattore;

/**
 * La classe RisorseUmane rappresenta il concetto del Reparto Risorse Umane dell'impresa, branca del Reparto Amministrativo. 
 * Questa si occupa di gestire il personale. Conserva una Lista di Pagabili. 
 * @author Andrea Ercolino
 *
 * @param <T> deve implementare l'interfaccia pagabile
 */
public class RisorseUmane<T extends Pagabile> extends RepartoAmministrativo {
	private ArrayList<T> personale;
	

	
	public RisorseUmane(double capitale) {
		super(capitale);
		personale = new ArrayList<T>();
	}
	
	/**
	 * restituisce la lista del personale assunto
	 * @return lista del personale assunto
	 */
	public ArrayList<T> getPersonale(){
		return personale;
	}
	
	
	/**
	 * Paga tutti i dipendenti di una data lista in base allo stipendio che gli spetta. In base al tipo del dipendente
	 * effettua il metodo di pagamento corretto
	 * @param criterio criterio di scelta per selezionare i dipendenti da pagare
	 */
		public void pagaDipendenti(Estraibile<T> criterio) {
		ArrayList<T> daPagare = scegliDipendenti(criterio);
		for(Pagabile d:daPagare) {
			if(!d.getStatoPagamento()) {
				effettuaSpesa(d.paga());
			}
			
		}
	}
	
		/**
		 * Paga un pagabile passato come parametro in base allo stipendio che gli spetta
		 * @param d dipendente da pagare
		 */
	public void pagaDipendenti(T d) {
		if (!d.getStatoPagamento()) {
			effettuaSpesa(d.paga());
		}
	}
	
	/**
	 * Azzera lo stato di pagamento di un dato tipo di pagabili, in modo che possano essere pagati in futuro.
	 * @param criterio criterio di scelta per selezionare i dipendenti da pagare
	 */
	public void nuovoMeseFiscale(Estraibile<T> criterio) {
		ArrayList<T> daResettare =scegliDipendenti(criterio);
		for(Pagabile d:daResettare) {
			d.resetStatoPagamento();
		}
	}
	
	/**
	 * Assume un pagabile aggiungendolo allal lista del personale
	 * @param dipendenteAssunto pagabile da assumere
	 */
	public void assumi(T dipendenteAssunto) {
		personale.add(dipendenteAssunto);
	}
	
	/**
	 * Data la matricola di un dipendente lo licenzia se si trova nella lista del personale
	 * @param matricolaDipendente matricola del dipendente da licenziare
	 */
	public void licenzia(int matricolaDipendente) {
		Pagabile daRimuovere = null;
		for (Pagabile p:personale) {
			Dipendente d  = (Dipendente) p;
			if (d.getMatricolaDipendente() == matricolaDipendente)
				 daRimuovere = d;
		}
		if(daRimuovere != null)
			personale.remove(daRimuovere);
	}
	
	/**
	 * Dato un criterio di scelta restituisce una lista di pagabili che rispettano quel criterio
	 * Precondizioni: nessuna
	 * Postcondizioni: viene restituita una lista di grandezza maggiore uguale a zero
	 * @param criterio crtierio di scelta per selezionare i pagabili da pagare
	 * @return
	 */
	public ArrayList<T> scegliDipendenti(Estraibile<T> criterio){
		ArrayList<T> estratto = new ArrayList<T>();
		Estrattore<T> estrattore = new Estrattore<T>(personale, criterio);
		estratto = estrattore.estrai();
		return estratto;
	}
	
	public String toString() {
		return super.toString()+"[personale="+personale+"]";
	}
	
	
}

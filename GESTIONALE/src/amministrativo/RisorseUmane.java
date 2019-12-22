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

public class RisorseUmane extends RepartoAmministrativo {
	private ArrayList<Dipendente> personale;
	
	private static final int ORE_FULLTIME = 40;
	private static final int ORE_PARTTIME = 24;
	
	public RisorseUmane(double capitale) {
		super(capitale);
		personale = new ArrayList<Dipendente>();
	}
	
	/**
	 * restituisce la lista del personale non impegnato in attività di cantiere
	 * @return personale impegnato a lavoro in un cantiere
	 */
	public ArrayList<Dipendente> getPersonale(){
		return personale;
	}
	
	
	/**
	 * Paga tutti i dipendenti di una data lista in base allo stipendio che gli spetta. In base al tipo del dipendente effettua il metodo di pagamento corretto
	 * @param criterio criterio di scelta per selezionare i dipendenti da pagare
	 */
		public void pagaDipendenti(Estraibile<Dipendente> criterio) {
		ArrayList<Dipendente> daPagare = scegliDipendenti(criterio);
		for(Dipendente d:daPagare) {
			if(!d.getStato()) {
				d.paga();
				if(d instanceof Dirigente) {
					effettuaSpesa(Pagabile.STIPENDIO_DIRIGENTE);
				}
				else if(d instanceof Operaio) {
					Operaio o = (Operaio) d;
					effettuaSpesa(Pagabile.STIPENDIO_OPERAIO + Pagabile.BONUS_OPERAIO * o.getNumeroCantieri());
				}
				else if(d instanceof Impiegato) {
					Impiegato i = (Impiegato) d;
					if(i.getNumeroOreSettimanali() >= ORE_PARTTIME  && i.getNumeroOreSettimanali() <= ORE_FULLTIME)
						effettuaSpesa(Pagabile.STIPENDIO_IMPIEGATOFT);
					else if(i.getNumeroOreSettimanali() <= ORE_PARTTIME) {
						effettuaSpesa(Pagabile.STIPENDIO_IMPIEGATOPT);
					}
					else effettuaSpesa(Pagabile.STIPENDIO_IMPIEGATOFT + ((ORE_FULLTIME -i.getNumeroOreSettimanali()) * Pagabile.BONUS_IMPIEGATO));
				}
				else if(d instanceof Quadro) {
					Quadro q = (Quadro) d;
					effettuaSpesa(Pagabile.STIPENDIO_QUADRO * q.getNumeroGiorniConsulenza());
				}
			}
			
		}
	}
	
	/**
	 * Azzera lo stato di pagamento di un dato tipo di dipendenti, in modo che possano essere pagati.
	 * @param tipoDipendenti tipo di dipendenti di cui si vuole azzerare il mese fiscale
	 */
	public void nuovoMeseFiscale(Estraibile<Dipendente> criterio) {
		ArrayList<Dipendente> daResettare =scegliDipendenti(criterio);
		for(Dipendente d:daResettare) {
			d.resetStatoPagamento();
		}
	}
	
	/**
	 * Aggiunge un dipendete alla lista dei dipendi liberi
	 * @param dipendenteAssunto dipendente da assumere
	 */
	public void assumi(Dipendente dipendenteAssunto) {
		personale.add(dipendenteAssunto);
	}
	
	/**
	 * Data la matricola di un dipendente lo licenzia se si trova nella lista del personale libero
	 * @param matricolaDipendente matricola del dipendente da licenziare
	 */
	public void licenzia(int matricolaDipendente) {
		for (Dipendente d:personale) {
			if (d.getMatricolaDipendente() == matricolaDipendente)
				personale.remove(personale.indexOf(d));
		}
	}
	
	public ArrayList<Dipendente> scegliDipendenti(Estraibile<Dipendente> criterio){
		ArrayList<Dipendente> estratto = new ArrayList<Dipendente>();
		Estrattore<Dipendente> estrattore = new Estrattore<Dipendente>(personale, criterio);
		estratto = estrattore.estrai();
		return estratto;
	}
	
	
}

package amministrativo;

import materiale.*;
import java.util.ArrayList;

import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonPresenteException;
import esterno.Fornitore;
import materiale.*;

public class RisorseMateriali extends RepartoAmministrativo {
	private Magazzino magazzino;
	private ArrayList<Fornitore> fornitori;
	
	public RisorseMateriali(double capitale, Magazzino magazzino) {
		super(capitale);
		this.magazzino = magazzino;
		fornitori = new ArrayList<Fornitore>();
	}
	
	public void aggiungiFornitore(Fornitore daAggiungere) {
		if(fornitori.contains(daAggiungere)) return;
		fornitori.add(daAggiungere);
	}
	
	public void rimuoviFornitore(Fornitore daRimuovere) {
		if(fornitori.contains(daRimuovere)) {
			fornitori.remove(daRimuovere);
		}
	}
	
	public void acquistaDaFornitore(Fornitore doveAcquistare, MaterialeDaCostruzione daAcquistare) throws CapacitaSuperataException{
		if(fornitori.contains(doveAcquistare)) {
			Fornitore f = fornitori.get(fornitori.indexOf(doveAcquistare));
			if (f.getCatalogo().contains(daAcquistare)) {
				ArrayList<MaterialeDaCostruzione> catalogo = f.getCatalogo();
				MaterialeDaCostruzione acquisto = f.getCatalogo().get(catalogo.indexOf(daAcquistare));
				this.effettuaSpesa(acquisto.getValoreProdotto());
				magazzino.aggiungiMateriale(acquisto);
			}
		}
	}
	
	public double scaricaMateriale(String codiceProdotto) {
		try {
			MaterialeDaCostruzione rimosso = magazzino.rimuoviMateriale(codiceProdotto);
			return rimosso.getValoreProdotto();
		}
		catch(ProdottoNonPresenteException e) {
			return 0;
		}
	}
	
	public void chiusuraContratto(double valore) {
		aggiungiEntrate(valore + (valore / 100 * 35));
	}
}

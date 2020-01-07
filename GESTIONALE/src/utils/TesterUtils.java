package utils;

import java.util.ArrayList;

import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonPresenteException;
import esterno.Fornitore;
import materiale.Betoniera;
import materiale.Magazzino;
import materiale.MaterialeDaCostruzione;
import materiale.Trapano;
import materiale.TubiInnocenti;
import materiale.Viti;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import personale.Dipendente;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;
import personale.Responsabile;

public class TesterUtils {

	public static void main(String[] args) {
		Operaio op1 = new Operaio("Andrea", "Ercolino");
		Operaio op2 = new Operaio("Pietro", "Ercolino");

		
		
		
		Dirigente di1 = new Dirigente("Concetta", "Cinque", "Università del cantiere");
		Dirigente di2 = new Dirigente("Signor", "Mascarpone", "Università del cantiere");
		Impiegato im1 = new Impiegato("Luigi", "Ercolino", 40);
		Quadro q1 = new Quadro("Pablo", "Picasso", 4);

		
		System.out.println("Instanzio un reparto risorse umane (ru)");
		RisorseUmane<Dipendente> ru = new RisorseUmane<Dipendente>(50000000);
		ru.assumi(di1);
		ru.assumi(im1);
		ru.assumi(di2);
		ru.assumi(op2);
		ru.assumi(op1);
		ru.assumi(q1);
		
		Fornitore<MaterialeDaCostruzione> f1 = new Fornitore<MaterialeDaCostruzione>("EasyBuild");
		Fornitore<MaterialeDaCostruzione> f2 = new Fornitore<MaterialeDaCostruzione>("BuildMoon");
		Fornitore<MaterialeDaCostruzione> f3 = new Fornitore<MaterialeDaCostruzione>("Build4Life");
		f1.aggiungiProdotto(new Betoniera("bet1", 350, 100, 20));
		f1.aggiungiProdotto(new Trapano("tp1", 200, 30, 850));
		f1.aggiungiProdotto(new Betoniera("bet2", 300, 150, 15));
		f1.aggiungiProdotto(new TubiInnocenti("tb1", 500, 120, 15));
		f1.aggiungiProdotto(new Viti("vt1", 100, 20, "stella"));
		f1.aggiungiProdotto(new Betoniera("bet3", 250, 80, 15));
		f1.aggiungiProdotto(new Betoniera("bet4", 250, 90, 15));
		
		Magazzino<MaterialeDaCostruzione> mag = new Magazzino<MaterialeDaCostruzione>(500);

		
		
		RisorseMateriali rm = new RisorseMateriali(ru.getCapitale(), mag);

		rm.aggiungiFornitore(f1);
		rm.aggiungiFornitore(f2);
		
		rm.rimuoviFornitore(f2);
		try {
			
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(0));
			
			
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(1));
			
			
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(2));
			
			
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(3));
			

		}
		catch (CapacitaSuperataException e) {
			System.out.println("Superato caricoMax Magazzino");
		}
		
		RepartoOperativo ro = new RepartoOperativo();
		ArrayList<MaterialeDaCostruzione> materiali = new ArrayList<MaterialeDaCostruzione>();
		materiali.add(new TubiInnocenti("tb1", 230, 175, 25));
		materiali.add(new Viti("v1", 230, 110, "Stella"));
		materiali.add(new Betoniera("bt1", 400, 200, 20));
		materiali.add(new Trapano("tp1", 125, 30, 850));
		Estraibile<Dipendente> criterio = (p) -> p.getStato() == false && p.getClass().getSimpleName().equals("Dirigente");
		ro.apriCantiere(50000, materiali, (Responsabile) ru.scegliDipendenti(criterio).get(0), "Pedro");
		Impresa i = new Impresa(rm, ru, ro);
		SalvaLeggiImpresa loader = new SalvaLeggiImpresa(i);
		loader.SalvaImpresa("impresa.dat");
	}

}

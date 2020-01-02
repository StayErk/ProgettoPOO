package amministrativo;

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
import personale.Quadro;

public class TesterRisorseMateriali {

	public static void main(String[] args) {
		System.out.println("Instanzio tre oggetti di tipo fornitore (f1), (f2), (f3)");
		Fornitore<MaterialeDaCostruzione> f1 = new Fornitore<MaterialeDaCostruzione>("EasyBuild");
		Fornitore<MaterialeDaCostruzione> f2 = new Fornitore<MaterialeDaCostruzione>("BuildMoon");
		Fornitore<MaterialeDaCostruzione> f3 = new Fornitore<MaterialeDaCostruzione>("Build4Life");
		System.out.println(f1);
		System.out.println(f2);
		System.out.println(f3+"\n");
		f1.aggiungiProdotto(new Betoniera("bet1", 350, 100, 20));
		f1.aggiungiProdotto(new Trapano("tp1", 200, 30, 850));
		f1.aggiungiProdotto(new Betoniera("bet2", 300, 150, 15));
		f1.aggiungiProdotto(new TubiInnocenti("tb1", 500, 120, 15));
		f1.aggiungiProdotto(new Viti("vt1", 100, 20, "stella"));
		f1.aggiungiProdotto(new Betoniera("bet3", 250, 80, 15));
		f1.aggiungiProdotto(new Betoniera("bet4", 250, 90, 15));
		
		Cantiere c = new Cantiere(3500, new Quadro("Andrea", "Ercolino", 3), "Pedro");
		
		
		System.out.println("Instanzio un magazzino con capacità max 500 (mag)");
		Magazzino<MaterialeDaCostruzione> mag = new Magazzino<MaterialeDaCostruzione>(500);
		System.out.println(mag+"\n");
		
		System.out.println("Instanzio un reparto risorse umane (rm)");
		RisorseMateriali rm = new RisorseMateriali(5000000, mag);
		System.out.println(rm+"\n");
		
		System.out.println("Lista dei fornitori prima dell'aggiunta:");
		System.out.println(rm.getFornitori());
		System.out.println("Aggiungo (f1), (f2) alla lista dei fornitori");
		System.out.println("Lista dei fornitori dopo l'aggiunta:");
		rm.aggiungiFornitore(f1);
		rm.aggiungiFornitore(f2);
		System.out.println(rm.getFornitori()+"\n");
		
		System.out.println("Rimuovo il fornitore f2");
		rm.rimuoviFornitore(f2);
		System.out.println("Lista dei fornitori dopo la rimozione:");
		System.out.println(rm.getFornitori()+"\n");
		
		System.out.println("Acquisto di prodotti fino a scatenare CapacitaSuperataException");
		try {
			System.out.println("Aggiungo primo prodotto");
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(0));
			System.out.println("Capacità attuale: " + rm.getMagazzino().getCaricoAttuale()+ " su " + rm.getMagazzino().getCapacitàMax());
			System.out.println("Aggiungo secondo prodotto");
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(1));
			System.out.println("Capacità attuale: " + rm.getMagazzino().getCaricoAttuale()+ " su " + rm.getMagazzino().getCapacitàMax());
			System.out.println("Aggiungo terzo prodotto");
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(2));
			System.out.println("Capacità attuale: " + rm.getMagazzino().getCaricoAttuale()+ " su " + rm.getMagazzino().getCapacitàMax());
			System.out.println("Aggiungo quarto prodotto");
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(3));
			System.out.println("Capacità attuale: " + rm.getMagazzino().getCaricoAttuale()+ " su " + rm.getMagazzino().getCapacitàMax());
			System.out.println("Aggiungo quinto prodotto");
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(4));
			System.out.println("Capacità attuale: " + rm.getMagazzino().getCaricoAttuale()+ " su " + rm.getMagazzino().getCapacitàMax());
			System.out.println("Cerco di aggiungere un prodotto di peso: " + f1.getCatalogo().get(6).getPeso());
			System.out.println("Aggiungo sesto prodotto");
			rm.acquistaDaFornitore(f1, (MaterialeDaCostruzione)f1.getCatalogo().get(6));
			System.out.println("Capacità attuale: " + rm.getMagazzino().getCaricoAttuale()+ " su " + rm.getMagazzino().getCapacitàMax());
		}
		catch (CapacitaSuperataException e) {
			System.out.println("Superato caricoMax Magazzino");
		}
		
		System.out.println("Stampa dei materiali in magazzino");
		System.out.println(rm.getMagazzino().getMaterialiInMagazzino() + "\n");
		
		System.out.println("Peso in magazzino prima di scaricare un prodotto dal magazzino: " +rm.getMagazzino().getCaricoAttuale());
		System.out.println("Scarica prodotto da magazzino");
		try {
			MaterialeDaCostruzione prodottoScaricato = rm.scaricaMateriale("bet1");
			System.out.println("Peso del prodotto scaricato: "  + prodottoScaricato.getPeso());
			System.out.println("Peso in magazzino dopo la rimozione: " + rm.getMagazzino().getCaricoAttuale());
			System.out.println("Lista dei prodotti in magazzino: ");
			System.out.println(rm.getMagazzino().getMaterialiInMagazzino());
			System.out.println("Valore del prodotto scaricato: " + prodottoScaricato.getValoreProdotto() + "\n");
		}
		catch (ProdottoNonPresenteException e) {
			System.out.println("Il prodotto non è presente");
		}
		
		System.out.println("Provo a scaricare un prodotto non presente in magazzino");
		System.out.println("Mi aspetto ProdottoNonPresenteException");
		try {
			rm.scaricaMateriale("b1");
		}
		catch (ProdottoNonPresenteException e) {
			System.out.println("Il prodotto non è presente");
			e.printStackTrace();
		}
		
		System.out.println("Capitale prima: " + rm.getCapitale());
		System.out.println("Provo a chiudere un contratto del valore di: " + c.getValoreCantiere());
		rm.chiusuraContratto(c.getValoreCantiere());
		System.out.println("Capitale dopo: " + rm.getCapitale());
		
		
		
		
		
	}

}

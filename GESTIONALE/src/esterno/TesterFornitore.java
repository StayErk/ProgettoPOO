package esterno;

import eccezioni.ProdottoNonPresenteException;
import materiale.Betoniera;
import materiale.MaterialeDaCostruzione;
import materiale.Trapano;
import materiale.TubiInnocenti;
import materiale.Viti;

public class TesterFornitore {

	public static void main(String[] args) {
		System.out.println("Instanzio quattro oggetti del tipo MaterialeDaCostruzione (m1), (m2), (m3), (m4): ");
		TubiInnocenti m1 = new TubiInnocenti("tb1", 230, 175, 25);
		Viti m2 = new Viti("v1", 230, 110, "Stella");
		Betoniera m3 = new Betoniera("bt1", 400, 200, 20);
		Trapano m4 = new Trapano("tp1", 125, 30, 850);
		
		System.out.println("Instanzio un oggetto del tipo Fornitore (f1)");
		Fornitore<MaterialeDaCostruzione> f1 = new Fornitore<MaterialeDaCostruzione>("Build4Life");
		System.out.println(f1+"\n");
		
		
		System.out.println("Aggiungo (m1) al catalogo. Dato che non è già presente mi aspetto che alla stampa del catalogo compaia");
		f1.aggiungiProdotto(m1);
		System.out.println(f1.getCatalogo()+"\n");
		
		System.out.println("Provo ad aggiungere la seconda volta (m1) al catalogo. Mi aspetto che non compaia");
		f1.aggiungiProdotto(m1);
		System.out.println(f1.getCatalogo()+"\n");
		
		System.out.println("Aggiungo (m2) al catalogo");
		f1.aggiungiProdotto(m2);
		System.out.println(f1.getCatalogo()+"\n");
		
		System.out.println("Rimuovo (m1) dal catalogo");
		MaterialeDaCostruzione rimosso = f1.rimuoviProdotto("tb1");
		System.out.println("Peso del materiale rimosso: "+rimosso.getPeso() + "\n");
		
		System.out.println("Provo a rimuovere un prodotto non presente nel catalogo.\nMi aspetto ProdottoNonPresenteException\n");
		try {
			f1.rimuoviProdotto("abc");
		} catch(ProdottoNonPresenteException e) {
			System.out.println("Il prodotto non è presente a catalogo");
			e.printStackTrace();
		}
	}

}

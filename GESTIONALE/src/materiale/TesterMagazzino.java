package materiale;

import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonPresenteException;

public class TesterMagazzino {

	public static void main(String[] args) {
		System.out.println("Instanzio quattro oggetti del tipo MaterialeDaCostruzione (m1), (m2), (m3), (m4): ");
		TubiInnocenti m1 = new TubiInnocenti("tb1", 230, 175, 25);
		Viti m2 = new Viti("v1", 230, 110, "Stella");
		Betoniera m3 = new Betoniera("bt1", 400, 200, 20);
		Trapano m4 = new Trapano("tp1", 125, 30, 850);
		
		System.out.println("Instanzio oggetto di tipo Magazzino (mag1) con capacità 300");
		Magazzino<MaterialeDaCostruzione> mag1 = new Magazzino<MaterialeDaCostruzione>(300);
		System.out.println(mag1 + "\n");
		
		
		try {
			System.out.println("capacità attuale: " + mag1.getCaricoAttuale() + " capacità max: " + mag1.getCapacitàMax());
			System.out.println("Aggiungo il prodotto (m1) al magazzino");
			mag1.aggiungiMateriale(m1);
			System.out.println("capacità attuale: " + mag1.getCaricoAttuale() + " capacità max: " + mag1.getCapacitàMax());
			System.out.println("Aggiungo il prodotto (m2) al magazzino");
			mag1.aggiungiMateriale(m2);
			System.out.println("capacità attuale: " + mag1.getCaricoAttuale() + " capacità max: " + mag1.getCapacitàMax());
			System.out.println("Al prossimo inserimento mi aspetto CapacitaSuperataException");
			System.out.println("Provo ad inserire (m3) che pesa: " +m3.getPeso());
			mag1.aggiungiMateriale(m3);
		}
		catch (CapacitaSuperataException e) {
			System.out.println("Superata capacità del magazzino");
			System.out.println("Rimuovo (m1)");
			MaterialeDaCostruzione m  = mag1.rimuoviMateriale("tb1");
			System.out.println("Peso del materiale rimosso: "+m.getPeso());
			System.out.println("capacità attuale: " + mag1.getCaricoAttuale() + " capacità max: " + mag1.getCapacitàMax()+"\n");
			
			System.out.println("Provo a rimuovere un Materiale non presente. Mi aspetto ProdottoNonPresenteException");
			try {
				mag1.rimuoviMateriale("abc");
			}
			catch(ProdottoNonPresenteException p) {
				System.out.println("Il prodotto non è presente");
				p.printStackTrace();
			}
		}
		System.out.println("\nFine test");
		
		
	}

}

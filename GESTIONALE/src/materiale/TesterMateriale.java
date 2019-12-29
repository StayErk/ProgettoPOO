package materiale;

public class TesterMateriale {
	public static void main(String[] args) {
		System.out.println("Instanzio quattro oggetti del tipo MaterialeDaCostruzione (m1), (m2), (m3), (m4): ");
		TubiInnocenti m1 = new TubiInnocenti("tb1", 230, 175, 25);
		Viti m2 = new Viti("v1", 230, 110, "Stella");
		Betoniera m3 = new Betoniera("bt1", 400, 200, 20);
		Trapano m4 = new Trapano("tp1", 125, 30, 850);
		System.out.println(m1);
		System.out.println(m2);
		System.out.println(m3);
		System.out.println(m4+"\n");
		
		System.out.println("Codice prodotto di (m1)\nAspettato: tb1\nRestituito: "+m1.getCodiceProdotto()+"\n");
		System.out.println("Clono (m2) e lo confronto con (m2)\nAspettato: true\nrestituito: "+m2.equals(m2.clone()));
	}
}

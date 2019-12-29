package personale;

public class TesterDipendente {
	public static void main(String[] args) {
		System.out.println("Instanzio tre oggetti del tipo dipendente: (p1), (p2), (p3)");
		Operaio p1 = new Operaio("Franco", "Battiato");
		Dirigente p2 = new Dirigente("Elon", "Musk", "Troppo");
		Quadro p3 = new Quadro("Gino", "Paoli", 4);
		System.out.println(p1+"\n"+p2+"\n"+p3+"\n");
		
		System.out.println("Il dipendente (p1) è impegnato?\nAspettato: false\nRisultato: "+p1.getStato());
		System.out.println("Impegno il dipendente (p1)");
		p1.impegnaDipendente();
		System.out.println("Il dipendente (p1) è impegnato?\nAspettato: true\nRisultato: "+p1.getStato()+"\n");
		
		System.out.println("Il dipendente (p2) è stato pagato?\nAspettato: false\nRisultato: "+p2.getStatoPagamento());
		System.out.println("Pago il dipendente (p2)");
		p2.paga();
		System.out.println("Il dipendente (p2) è stato pagato?\nAspettato: true\nRisultato: "+p2.getStatoPagamento()+"\n");
		
		System.out.println("Libero il dipendente (p1)");
		p1.liberaDipendente();
		System.out.println("(p1) impegnato? " + p1.getStato()+"\n");
		
		System.out.println("é passato un mese. Azzero lo stato del pagamento di (p2)");
		p2.resetStatoPagamento();
		System.out.println("(p2) è stato pagato questo mese? " + p2.getStatoPagamento()+"\n");
		
		
		System.out.println("Clono il dipendente (p3) in (clonato)");
		Dipendente clonato = p3.clone();
		System.out.println("(clonato) e (p3) sono uguali? ");
		System.out.println(p3.equals(clonato) + "\n");
		
		System.out.println("Fine test");
	}
}

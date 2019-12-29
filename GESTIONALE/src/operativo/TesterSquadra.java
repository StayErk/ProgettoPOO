package operativo;

import personale.Dirigente;
import personale.Operaio;
import personale.Quadro;

public class TesterSquadra {
	public static void main(String[] args) {
		System.out.println("Instanzio quattro oggetti del tipo dipendente: (p1), (p2), (p3), (p4)");
		Operaio p1 = new Operaio("Franco", "Battiato");
		Operaio p4 = new Operaio("Alice", "Merton");
		Dirigente p2 = new Dirigente("Elon", "Musk", "Troppo");
		Quadro p3 = new Quadro("Gino", "Paoli", 4);
		System.out.println(p1+"\n"+p2+"\n"+p3+"\n");
		
		System.out.println("Instanzio un oggetto del tipo Squadra. Come Responsabile usero (p3)");
		Squadra team = new Squadra(p3);
		System.out.println(team);
		
		System.out.println("Impegno il dipendente (p4) e provo ad aggiungerlo alla squadra. Mi aspetto che non sia presente.");
		p4.impegnaDipendente();
		team.aggiungiOperaio(p4);
		System.out.println(team.getGruppo());
		
		System.out.println("Provo ad aggiungere (p1) che è libero. Mi aspetto di trovarlo all'interno della squadra");
		team.aggiungiOperaio(p1);
		System.out.println(team.getGruppo()+"\n");
		
		System.out.println("La squadra è stata chiamata per lavorare in un cantiere. Impegno il gruppo");
		team.impegnaSquadra();
		System.out.println(team+"\n");
		
		System.out.println("Provo a Creare una nuova squadra utilizzando lo stesso Responsabile, mi aspetto IllegalAregumentException");
		try {
			Squadra team2 = new Squadra(p3);
		}
		catch (IllegalArgumentException e) {
			System.out.println(p3 + " è già impegnato\n");
		}
		
		System.out.println("Il cantiere a cui ha lavorato la squadra è terminato. Libero la squadra");
		team.liberaSquadra();
		System.out.println(team);
	}
}

package operativo;

import java.util.ArrayList;

import amministrativo.RisorseUmane;
import materiale.Betoniera;
import materiale.MaterialeDaCostruzione;
import materiale.Trapano;
import materiale.TubiInnocenti;
import materiale.Viti;
import personale.Dipendente;
import personale.Dirigente;
import personale.Operaio;
import personale.Quadro;
import personale.Responsabile;
import utils.Estraibile;

public class TesterOperativo {

	public static void main(String[] args) {
		System.out.println("Instanzio un oggetto di tipo RisorseUmane ed assumo tre dipendenti");
		RisorseUmane ru = new RisorseUmane(500000);
		ru.assumi(new Dirigente("Andrea", "Ercolino", "Cantieristica Aziendale"));
		ru.assumi(new Operaio("Lara", "Croft"));
		ru.assumi(new Operaio("Franco", "Califano"));
		ru.assumi(new Quadro("Sly", "Cooper", 3));
		System.out.println(ru);
		
		System.out.println("Creo una nuova squadra");
		Estraibile<Dipendente> criterio = (p) -> p.getClass().getSimpleName().equals("Quadro");
		Quadro q = (Quadro) ru.scegliDipendenti(criterio).get(0);
		Squadra team = new Squadra(q);
		System.out.println(team);
		
		System.out.println("Aggiungo gli operai alla squadra");
		criterio = (p) -> p.getStato() == false && p.getClass().getSimpleName().equals("Operaio");
		team.aggiungiOperaio(ru.scegliDipendenti(criterio));
		System.out.println(team);
		
		System.out.println("\nInstanzio un oggetto di tipo Reparto Operativo");
		RepartoOperativo ro = new RepartoOperativo();
		System.out.println(ro+"\n");
		
		System.out.println("Apro un nuovo cantiere");
		ArrayList<MaterialeDaCostruzione> materiali = new ArrayList<MaterialeDaCostruzione>();
		materiali.add(new TubiInnocenti("tb1", 230, 175, 25));
		materiali.add(new Viti("v1", 230, 110, "Stella"));
		materiali.add(new Betoniera("bt1", 400, 200, 20));
		materiali.add(new Trapano("tp1", 125, 30, 850));
		criterio = (p) -> p.getStato() == false && p.getClass().getSimpleName().equals("Dirigente");
		ro.apriCantiere(50000, materiali, (Responsabile) ru.scegliDipendenti(criterio).get(0), "Pedro");
		
		System.out.println("Stampo i cantieri aperti: ");
		System.out.println(ro.getCantieriAperti()+"\n");
		
		System.out.println("Aggiungo squadra team al cantiere appena aperto");
		ro.aggiungiSquadraACantiere(ro.getCantieriAperti().get(0), team);
		System.out.println(ro.getCantieriAperti()+"\n");
		
		System.out.println("Chiudo cantiere aperto e stampo la lista dei cantieri aperti:");
		ro.chiudiCantiere(ro.getCantieriAperti().get(0));
		System.out.println(ro.getCantieriAperti());
		
	}

}

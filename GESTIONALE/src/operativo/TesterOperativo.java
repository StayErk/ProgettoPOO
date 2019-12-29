package operativo;

import amministrativo.RisorseUmane;
import personale.Dipendente;
import personale.Dirigente;
import personale.Operaio;
import personale.Quadro;
import personale.Responsabile;
import utils.Estraibile;

public class TesterOperativo {

	public static void main(String[] args) {
		System.out.println("Instanzio un oggetto di tipo RisorseUmane");
		RisorseUmane ru = new RisorseUmane(500000);
		ru.assumi(new Dirigente("Andrea", "Ercolino", "Cantieristica Aziendale"));
		ru.assumi(new Operaio("Lara", "Croft"));
		ru.assumi(new Operaio("Franco", "Califano"));
		ru.assumi(new Quadro("Sly", "Cooper", 3));
		Estraibile<Dipendente> criterio = (p) -> p.getClass().getSimpleName().equals("Quadro");
		Responsabile re = (Responsabile) ru.scegliDipendenti(criterio).get(0);
		Squadra team = new Squadra(re);
		
		criterio = (p) -> p.getStato() == false && p.getClass().getSimpleName().equals("Operaio");
		team.aggiungiOperaio(ru.scegliDipendenti(criterio));
		System.out.println("Instanzio tre oggetti di tipo cantiere:");
		
	}

}

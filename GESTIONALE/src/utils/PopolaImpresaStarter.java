package utils;

import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import eccezioni.CapacitaSuperataException;
import esterno.Fornitore;
import materiale.Betoniera;
import materiale.Magazzino;
import materiale.MaterialeDaCostruzione;
import materiale.Trapano;
import materiale.TubiInnocenti;
import materiale.Viti;
import operativo.RepartoOperativo;
import personale.Dipendente;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;

public class PopolaImpresaStarter {

	public static void main(String[] args) {
		Operaio op1 = new Operaio("Steve", "Rogers");
		Operaio op2 = new Operaio("Tony", "Stark");
		Operaio op3 = new Operaio("Bruce", "Banner");
		Operaio op4 = new Operaio("Clint", "Barton");
		Operaio op5 = new Operaio("Natasha", "Romanov");
		Operaio op6 = new Operaio("Thor", "Figlio di Odino");
		Operaio op7 = new Operaio("Wanda", "Maximoff");
		Operaio op8 = new Operaio("Phil", "Coulson");
		Operaio op9 = new Operaio("Peter", "Parker");
		Operaio op10 = new Operaio("Bruce", "Wayne");
		Operaio op11 = new Operaio("Flash", "Gordon");
		Operaio op12 = new Operaio("Dick", "Grayson");
		Operaio op13 = new Operaio("Diana", "Prince");
		Operaio op14 = new Operaio("Arthur", "Curry");
		Operaio op15 = new Operaio("Oliver", "Queen");
		Operaio op16 = new Operaio("Harold", "Jordan");
		Operaio op17 = new Operaio("Clark", "Kent");
		Operaio op18 = new Operaio("Victor", "Stone");
		Operaio op19 = new Operaio("Kendra", "Saunders");
		
		Dirigente di1 = new Dirigente("Quentin", "Tarantino", "regista");
		Dirigente di2 = new Dirigente("Miles", "Davis", "re del jazz");
		Dirigente di3 = new Dirigente("Woody", "Allen", "Comico");
		Dirigente di4 = new Dirigente("Tim", "Burton", "Horror");
		Dirigente di5 = new Dirigente("Frank", "Sinatra", "Autmun Leaves");
		Dirigente di6 = new Dirigente("Duke", "Ellington", "Compositore");
		
		Impiegato im1 = new Impiegato("Neil Patrick", "Harris", 40);
		Impiegato im2 = new Impiegato("Joaquin", "Phoneix", 19);
		Impiegato im3 = new Impiegato("Bradd", "Pitt", 40);
		Impiegato im4 = new Impiegato("Leonardo", "Di Caprio", 45);
		Impiegato im5 = new Impiegato("Jennifer", "Aniston", 23);
		Impiegato im6 = new Impiegato("Amy", "Adamns", 47);
		
		Quadro q1 = new Quadro("Rick", "Dalton", 4);
		Quadro q2 = new Quadro("Eddie", "Nice Guy Cabbot", 3);
		Quadro q3 = new Quadro("Larry", "Dimmick", 5);
		Quadro q4 = new Quadro("Vernita", "Green", 2);
		Quadro q5 = new Quadro("Vincent", "Vega", 7);
		Quadro q6 = new Quadro("Jules", "Winnfield", 1);
		
		System.out.println("Instanzio un reparto risorse umane (ru)");
		RisorseUmane<Dipendente> ru = new RisorseUmane<Dipendente>(5000000);
		ru.assumi(di1);
		ru.assumi(di2);
		ru.assumi(di3);
		ru.assumi(di4);
		ru.assumi(di5);
		ru.assumi(di6);
		ru.assumi(im1);
		ru.assumi(im2);
		ru.assumi(im3);
		ru.assumi(im4);
		ru.assumi(im5);
		ru.assumi(im6);
		
		ru.assumi(op1);
		ru.assumi(op2);
		ru.assumi(op3);
		ru.assumi(op4);
		ru.assumi(op5);
		ru.assumi(op6);
		ru.assumi(op7);
		ru.assumi(op8);
		ru.assumi(op9);
		ru.assumi(op10);
		ru.assumi(op11);
		ru.assumi(op12);
		ru.assumi(op13);
		ru.assumi(op14);
		ru.assumi(op15);
		ru.assumi(op16);
		ru.assumi(op17);
		ru.assumi(op18);
		ru.assumi(op19);
		
		
		ru.assumi(q1);
		ru.assumi(q2);
		ru.assumi(q3);
		ru.assumi(q4);
		ru.assumi(q5);
		ru.assumi(q6);
		
		Fornitore<MaterialeDaCostruzione> f1 = new Fornitore<MaterialeDaCostruzione>("EasyBuild");
		Fornitore<MaterialeDaCostruzione> f2 = new Fornitore<MaterialeDaCostruzione>("Build4Life");
		
		f1.aggiungiProdotto(new Betoniera("betonieraGrande", 250, 95, 25));
		f1.aggiungiProdotto(new Trapano("trapanoBosch1", 110, 8, 850));
		f1.aggiungiProdotto(new Betoniera("betonieraMedia", 235, 75, 20));
		f1.aggiungiProdotto(new TubiInnocenti("tuboMedio", 25, 20, 10));
		f1.aggiungiProdotto(new TubiInnocenti("tuboPiccolo", 20, 15, 5));
		f1.aggiungiProdotto(new TubiInnocenti("tuboGrande", 30, 25, 15));
		f1.aggiungiProdotto(new Viti("vitiStagno1", 2.5, 1, "stella"));
		f1.aggiungiProdotto(new Viti("vitiStagno2", 2.5, 1, "piatta"));
		f1.aggiungiProdotto(new Viti("vitiStagno3", 3, 2, "lunga"));
		f1.aggiungiProdotto(new Betoniera("betonieraPiccola", 215, 65, 17));
		
		
		f2.aggiungiProdotto(new Betoniera("betonieraGigante", 300, 105, 32));
		f2.aggiungiProdotto(new Trapano("trapanoBosch2", 110, 8, 850));
		f2.aggiungiProdotto(new Trapano("trapanoBosch3", 135, 10, 950));
		f2.aggiungiProdotto(new Trapano("trapanoBosch4", 95, 7, 700));
		f2.aggiungiProdotto(new Trapano("trapanoBosch5", 85, 7, 650));
		f2.aggiungiProdotto(new TubiInnocenti("tuboMedio", 25, 20, 10));
		f2.aggiungiProdotto(new TubiInnocenti("tuboPiccolo", 20, 15, 5));
		f2.aggiungiProdotto(new TubiInnocenti("tuboGrande", 30, 25, 15));
		f2.aggiungiProdotto(new Viti("bullonePiccolo1", 2.3, 1, "filettato"));
		f2.aggiungiProdotto(new Viti("bullonePiccolo2", 2.3, 1, "non filettato"));
		
		
		Magazzino<MaterialeDaCostruzione> mag = new Magazzino<MaterialeDaCostruzione>(1500);

		
		
		RisorseMateriali rm = new RisorseMateriali(ru.getCapitale(), mag);
		

		rm.aggiungiFornitore(f1);
		rm.aggiungiFornitore(f2);
		
		RepartoOperativo ro = new RepartoOperativo();
		Impresa i = new Impresa(rm, ru, ro);
		SalvaLeggiImpresa loader = new SalvaLeggiImpresa(i);
		loader.SalvaImpresa("impresaEsame.dat");

	}

}

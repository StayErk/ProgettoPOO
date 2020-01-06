package amministrativo;

import personale.Dipendente;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import utils.Estraibile;

public class TesterRisorseUmane {

	public static void main(String[] args) {
		
		System.out.println("Instanzio due operai: (op1) e (op2)");
		Operaio op1 = new Operaio("Andrea", "Ercolino");
		Operaio op2 = new Operaio("Pietro", "Ercolino");
		System.out.println(op1+"\n"+op2+"\n");
		
		System.out.println("Instanzio un dirigente (di1) e un impiegato (im1)");
		Dirigente di1 = new Dirigente("Concetta", "Cinque", "Università del cantiere");
		Impiegato im1 = new Impiegato("Luigi", "Ercolino", 40);
		System.out.println(di1+"\n"+im1+"\n");
		
		System.out.println("Instanzio un reparto risorse umane (ru)");
		RisorseUmane<Dipendente> ru = new RisorseUmane<Dipendente>(50000000);
		System.out.println(ru+"\n");
		
		System.out.println("Lista degli impiegati prima dell'assunzione:");
		System.out.println(ru.getPersonale());
		System.out.println("Assumo i suddetti Dipendenti");
		ru.assumi(op1);
		ru.assumi(op2);
		ru.assumi(im1);
		ru.assumi(di1);
		System.out.println(ru.getPersonale()+"\n");
		
		System.out.println("Estraggo dalla mia lista di Dipendenti tutti gli operai");
		Estraibile<Dipendente> criterio = (p) -> p.getClass().getSimpleName().equalsIgnoreCase("operaio");
		System.out.println(ru.scegliDipendenti(criterio));
		criterio = (p) -> p.getClass().getSimpleName().equalsIgnoreCase("dirigente");
		System.out.println("Estraggo dalla mia lista di dipendenti tutti quelli di tipo Dirigente");
		System.out.println(ru.scegliDipendenti(criterio));
		
		System.out.println("\ncapitale azienda:" + ru.getCapitale());
		double capitalePrima = ru.getCapitale();
		System.out.println("Pago lo stipensio al dirigente. Mi aspetto: " + (ru.getCapitale() - 2800));
		criterio = (p) -> p.getClass().getSimpleName().equalsIgnoreCase("dirigente") && p.getStatoPagamento() == false;
		ru.pagaDipendenti(criterio);
		System.out.println("capitale azienda:" + ru.getCapitale());
		System.out.println("Stipendio pagato:" + (capitalePrima - ru.getCapitale()));
		capitalePrima = ru.getCapitale();
		
		System.out.println("Capitale prima:" + capitalePrima);
		System.out.println("Pago lo stipendio a tutti i dipendenti che non sono stati acnora pagati: ");
		criterio = (p) -> p.getStatoPagamento() == false;
		ru.pagaDipendenti(criterio);
		System.out.println("Capitale dopo:" + ru.getCapitale());
		System.out.println("Pagati: " + (capitalePrima - ru.getCapitale()));
		
		System.out.println("Controllo lo stato dei pagamenti dei dipendenti\n");
		System.out.println(ru.getPersonale());
		System.out.println("Azzero lo stato del pagamento dei dipendenti\n");
		criterio = (p) -> p.getStatoPagamento() == true;
		ru.nuovoMeseFiscale(criterio);
		System.out.println(ru.getPersonale());
		
		ru.licenzia(2);
		System.out.println(ru.getPersonale());
		
		
	}

}

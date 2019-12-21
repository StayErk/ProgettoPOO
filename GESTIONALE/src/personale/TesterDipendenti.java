package personale;

public class TesterDipendenti {
	public static void main(String[] args) {
		Dirigente d = new Dirigente("Andrea", "Delle Serre", 3000.5);
		System.out.println(d.paga());
		System.out.println(d.getStatoPagamento());
		d.resetStatoPagamento();
		System.out.println(d.getStatoPagamento());
		
		Squadra sq = new Squadra(d);
		sq.getCaposquadra();
	}
}

package personale;

public abstract class Dipendente implements Pagabile{
	private String nome;
	private String cognome;
	private int matricolaDipendete;
	private boolean pagato;
	private static int numeroDipendenti = 1;
	
	
	public Dipendente(String nome, String cognome) {
		this.nome = nome;
		this.cognome = cognome;
		this.matricolaDipendete = numeroDipendenti++;
		pagato = false;
	}
	
	public boolean getStatoPagamento() {
		return pagato;
	}
	
	public double paga() {
		pagato = true;
		return 0;
	}
	
	public void resetStatoPagamento() {
		pagato = false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getMatricolaDipendente() {
		return matricolaDipendete;
	}
	
	
}

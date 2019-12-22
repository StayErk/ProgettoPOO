package personale;

public abstract class Dipendente implements Pagabile{
	private String nome;
	private String cognome;
	private int matricolaDipendete;
	private boolean pagato;
	private boolean impegnato;
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
	
	public void paga() {
		pagato = true;
	}
	
	public void resetStatoPagamento() {
		pagato = false;
	}
	
	public void impegnaDipendente() {
		impegnato = true;
	}
	
	public void liberaDipendente() {
		impegnato = false;
	}
	
	public String getNome() {
		return nome;
	}
	
	public boolean getStato() {
		return impegnato;
	}
	
	public String getCognome() {
		return cognome;
	}
	
	public int getMatricolaDipendente() {
		return matricolaDipendete;
	}
	
	
}

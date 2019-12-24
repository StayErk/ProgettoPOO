package personale;

public abstract class Dipendente implements Pagabile, Cloneable{
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
	
	public String toString() {
		return getClass().getName() + "[nome="+nome+", cognome="+cognome+", matricola dipendente="+matricolaDipendete+", pagato="+pagato+", impegnato="+impegnato+"]";
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(getClass() == o.getClass()) {
			Dipendente d = (Dipendente) o;
			return impegnato == d.impegnato && pagato == d.pagato && nome.equals(d.nome) && cognome.equals(d.cognome) && matricolaDipendete == d.matricolaDipendete;
		}
		return false;
	}
	
	public Dipendente clone() {
		try {
			return (Dipendente) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	
}

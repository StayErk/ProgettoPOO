package personale;

public class Quadro extends Dipendente implements Responsabile{
	private int numeroGiorniConsulenza;
	
	public Quadro(String nome, String cognome, int numeroGiorniConsulenza) {
		super(nome, cognome);
		this.numeroGiorniConsulenza = numeroGiorniConsulenza;
	}
	
	public double paga() {
		super.setStatoPagamento();
		return Pagabile.STIPENDIO_QUADRO * numeroGiorniConsulenza;
	}
	
	public int getNumeroGiorniConsulenza() {
		return numeroGiorniConsulenza;
	}
	
	public String toString() {
		return super.toString()+"[numeroGiorniConsulenza="+numeroGiorniConsulenza+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Quadro q = (Quadro) o;
		return numeroGiorniConsulenza == q.numeroGiorniConsulenza;
	}
	
	public Quadro clone() {
		return (Quadro) super.clone();
	}
}

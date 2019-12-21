package materiale;

public final class TubiInnocenti extends MaterialeDaCostruzione {
	private int lunghezza;
	
	public TubiInnocenti(String codiceProdotto, double valore, int peso, int lunghezza) {
		super(codiceProdotto, valore, peso);
		this.lunghezza = lunghezza;
	}
	
	public int getLunghezza() {
		return lunghezza;
	}
	
	public String toString() {
		return super.toString() +"[lunghezza="+lunghezza+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		TubiInnocenti t = (TubiInnocenti) o;
		return lunghezza == t.lunghezza;
	}
	
	public TubiInnocenti clone() {
		return (TubiInnocenti) super.clone();
	}
}

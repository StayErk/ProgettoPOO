package materiale;

public final class Betoniera extends MaterialeDaCostruzione {
	private int capacita;
	
	public Betoniera(String codiceProdotto, double valore, int peso, int capacita) {
		super(codiceProdotto, valore, peso);
		this.capacita = capacita;
	}
	
	public int getCapacita() {
		return capacita;
	}
	
	public String toString() {
		return super.toString()+"[capacità="+capacita+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Betoniera b = (Betoniera) o;
		return capacita == b.capacita;
	}
	
	public Betoniera clone() {
		return (Betoniera) super.clone();
	}
}

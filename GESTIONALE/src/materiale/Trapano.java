package materiale;

/**
 * Questa classe rappresenta il concetto di trapano caratterizzato da una potenza in watt
 * @author Andrea Ercolino
 *
 */
public final class Trapano extends MaterialeDaCostruzione {
	private int consumoWatt;
	
	public Trapano(String codiceProdotto, double valore, int peso, int consumoWatt) {
		super(codiceProdotto, valore, peso);
		this.consumoWatt = consumoWatt;
	}
	
	public int getConsumoWatt() {
		return consumoWatt;
	}
	
	public String toString() {
		return super.toString()+"[consumoWatt="+consumoWatt+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Trapano t = (Trapano) o;
		return consumoWatt == t.consumoWatt;
	}
}

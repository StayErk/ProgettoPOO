package materiale;

public final class Viti extends MaterialeDaCostruzione {
	private String tipo;
	
	public Viti(String codiceProdotto, double valore, int peso, String tipo) {
		super(codiceProdotto, valore, peso);
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public String toString() {
		return super.toString()+"[tipo="+tipo+"]";
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Viti v = (Viti) o;
		return tipo.equals(v.tipo);
	}
	
	public Viti clone() {
		return (Viti) super.clone();
	}
}

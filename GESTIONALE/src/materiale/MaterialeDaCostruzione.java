package materiale;

public final class MaterialeDaCostruzione {
	private String codiceProdotto;
	private double valore;
	private int peso;
	
	public MaterialeDaCostruzione(String codiceProdotto, double valore, int peso) {
		this.codiceProdotto = codiceProdotto;
		this.valore = valore;
		this.peso = peso;
	}
	
	public String getCodiceProdotto() {
		return codiceProdotto;
	}
	
	public double getValoreProdotto() {
		return valore;
	}
	
	public int getPeso() {
		return peso;
	}
	
}

package materiale;

import java.io.Serializable;

/**
 * Questa classe rappresenta il concetto di astratto di MaterialeDaCostruzione che poi verrà raffinata in categorie di prodotto.
 * Il materiale da costruzione è composto da un peso, un valore e un codiceProdotto
 * @author Andrea Ercolino
 *
 */
public abstract class MaterialeDaCostruzione implements Cloneable, Serializable {
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
	
	public String toString() {
		return getClass().getName()+"[codiceProdotto="+codiceProdotto+", valore="+valore+", peso="+peso+"]";
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(getClass() == o.getClass()) {
			MaterialeDaCostruzione m = (MaterialeDaCostruzione) o;
			return codiceProdotto.equals(m.codiceProdotto) && valore == m.valore && peso == m.peso;
		}
		return false;
	}
	
	public MaterialeDaCostruzione clone() {
		try {
			return (MaterialeDaCostruzione) super.clone();
		}
		catch(CloneNotSupportedException e){
			return null;
		}
	}
}

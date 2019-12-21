package esterno;
import materiale.MaterialeDaCostruzione;
import java.util.ArrayList;
import java.util.Iterator;

import eccezioni.ProdottoNonPresenteException;

/**
 * Questa classe rappresenta il concetto di Fornitore. Un fornitore ha un nome ed una catalogo (insieme) di Materiali Da Costruzione. 
 * @author stayerk
 *
 */

public class Fornitore<T extends MaterialeDaCostruzione> {
	private String nome;
	private ArrayList<T> catalogo;
	
	public Fornitore(String nome) {
		this.nome = nome;
		catalogo = new ArrayList<T>();
	}
	
	public String getNome() {
		return nome;
	}
	

	public ArrayList<T> getCatalogo(){
		return catalogo;
	}
	
	/**
	 * Questo metodo permette di aggiungere un prodotto al catalogo.
	 * @param daAggiungere Materiale da Costruzione che deve essere aggiunto al Catalogo
	 */
	public void aggiungiProdotto(T daAggiungere) {
		catalogo.add(daAggiungere);
	}
	
	/**
	 * Questo metodo permette di eliminare un dato prodotto dal catalogo del Fornitore. Restituisce il prodotto eliminato dal catalogo,
	 * e nel caso questo non sia presente lancia l'eccezione non controllata ProdottoNonPresenteException.
	 * @param codiceProdotto
	 * @return il MatertialeDaCostruzione eliminato dal catalogo
	 */
	public T rimuoviProdotto(String codiceProdotto) {
		for(T m:catalogo) {
			if(m.getCodiceProdotto().equalsIgnoreCase(codiceProdotto))
				return catalogo.remove(catalogo.indexOf(m));
		}
		throw new ProdottoNonPresenteException();
	}
	
	
	public String toString() {
		return getClass().getName() + "[nome="+nome+", catalogo="+catalogo+"]";
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		if(getClass() == o.getClass()) {
			Fornitore f = (Fornitore) o;
			return f.nome.equals(nome) && f.catalogo.equals(catalogo);
		}
		return false;
	}
	
	public Fornitore clone() {
		try {
			Fornitore cloned = (Fornitore) super.clone();
			cloned.catalogo = (ArrayList<T>) catalogo.clone();
			return cloned;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}
}



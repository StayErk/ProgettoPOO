package esterno;
import materiale.MaterialeDaCostruzione;
import java.util.ArrayList;
import java.util.Iterator;


public class Fornitore {
	private String nome;
	private ArrayList<MaterialeDaCostruzione> catalogo;
	
	public Fornitore(String nome) {
		this.nome = nome;
		catalogo = new ArrayList<MaterialeDaCostruzione>();
	}
	
	public void aggiungiProdotto(MaterialeDaCostruzione m) {
		catalogo.add(m);
	}
	
	public String getNome() {
		return nome;
	}
	
	public ArrayList<MaterialeDaCostruzione> getCatalogo(){
		return catalogo;
	}
	
	public void rimuoviProdotto(String codiceProdotto) {
		for(MaterialeDaCostruzione m:catalogo) {
			if(m.getCodiceProdotto().equalsIgnoreCase(codiceProdotto))
				catalogo.remove(catalogo.indexOf(m));
		}
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
			cloned.catalogo = (ArrayList<MaterialeDaCostruzione>) catalogo.clone();
			return cloned;
		}
		catch(CloneNotSupportedException e) {
			return null;
		}
	}
}



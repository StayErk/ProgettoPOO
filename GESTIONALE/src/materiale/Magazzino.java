package materiale;

import java.util.ArrayList;
import eccezioni.CapacitaSuperataException;
import eccezioni.ProdottoNonPresenteException;

public class Magazzino {
	private int capacitaMax;
	private int caricoAttuale;
	private double valoreMagazzino;
	private ArrayList<MaterialeDaCostruzione> materiali;
	
	public Magazzino(int capacitaMax) {
		this.capacitaMax = capacitaMax;
		valoreMagazzino = 0;
	}
	
	public int getCapacitàMax() {
		return capacitaMax;
	}
	
	public double getValoreMagazzino() {
		return valoreMagazzino;
	}
	
	public ArrayList<MaterialeDaCostruzione> getMaterialiInMagazzino() {
		return materiali;
	}
	
	
	public void aggiungiMateriale(MaterialeDaCostruzione m) throws CapacitaSuperataException{
		if(m.getPeso() + caricoAttuale > capacitaMax) throw new CapacitaSuperataException();
		else {
			caricoAttuale += m.getPeso();
			materiali.add(m);
		}
		
	}
	
	public MaterialeDaCostruzione rimuoviMateriale(String codiceProdotto) {
		for (MaterialeDaCostruzione m:materiali) {
			if(m.getCodiceProdotto().equalsIgnoreCase(codiceProdotto)) {
				caricoAttuale -= m.getPeso();
				materiali.remove(materiali.indexOf(m));
				return m;
			}
		}
		throw new ProdottoNonPresenteException();
	}
}

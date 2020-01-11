package utils;

import java.util.ArrayList;

public class Ordinatore<T> {
	private Comparatore<T> criterio;
	public Ordinatore(Comparatore<T> c) {
		this.criterio = c;
	}
	
	public ArrayList<T> ordina(ArrayList<T> daOrdinare){
		for(int i = 0; i < daOrdinare.size(); i++) {
			for(int j = 0; j < daOrdinare.size()-1; j++) {
				if(criterio.compara(daOrdinare.get(j), daOrdinare.get(j+1))> 0) {
					T temp = daOrdinare.get(j);
					daOrdinare.set(j, daOrdinare.get(j+1));
					daOrdinare.set(j+1, temp);
				}
			}
		}
		return daOrdinare;
	}
}

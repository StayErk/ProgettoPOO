package utils;

import java.util.ArrayList;

public class Estrattore<T> {
	ArrayList<T> insiemeInput;
	Estraibile<T> criterio;
	
	public Estrattore () {
		insiemeInput = new ArrayList<T>();
		criterio = null;
	}
	
	public Estrattore (Estraibile<T> criterio) {
		insiemeInput = new ArrayList<T>();
		 this.criterio = criterio;
	}
	
	public Estrattore (ArrayList<T> insiemeInput) {
		this.insiemeInput = insiemeInput;
		criterio = null;
	}
	
	public Estrattore (ArrayList<T> insiemeInput, Estraibile<T> criterio) {
		this.insiemeInput = insiemeInput;
		this.criterio = criterio;
	}
	
	public void setInsiemeInput(ArrayList<T> nuovoInsieme) {
		insiemeInput = nuovoInsieme;
	}
	
	public void setCriterio(Estraibile<T> nuovoCriterio) {
		criterio = nuovoCriterio;
	}
	
	public ArrayList<T> estrai(){
		ArrayList<T> output = new ArrayList<T>();
		for(T t:insiemeInput) {
			if(criterio.estrai(t)) output.add(t); 
		}
		return output;
	}
}

package utils;

import java.io.Serializable;

import amministrativo.RepartoAmministrativo;
import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import operativo.RepartoOperativo;
import personale.Dipendente;

public class Impresa implements Serializable{
	private RisorseUmane<Dipendente> ru;
	private RisorseMateriali rm;
	private RepartoOperativo ro;
	
	public Impresa(RisorseMateriali rm, RisorseUmane<Dipendente> ru, RepartoOperativo ro) {
		this.ru = ru;
		this.rm = rm;
		this.ro = ro;
	}
	
	public RisorseMateriali getRisorseMateriali() {
		return rm;
	}
	
	public RisorseUmane<Dipendente> getRisorseUmane() {
		return ru;
	}
	
	public RepartoOperativo getRepartoOperativo() {
		return ro;
	}
	
	
	public String toString(){
		return getClass().getName() + "[ru= " + ru + ", rm=" + rm + ", ro= " + ro; 
	}
}

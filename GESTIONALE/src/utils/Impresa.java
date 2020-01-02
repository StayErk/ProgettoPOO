package utils;

import java.io.Serializable;

import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import operativo.RepartoOperativo;

public class Impresa implements Serializable{
	private RisorseUmane ru;
	private RisorseMateriali rm;
	private RepartoOperativo ro;
	
	public Impresa(RisorseMateriali rm, RisorseUmane ru, RepartoOperativo ro) {
		this.ru = ru;
		this.rm = rm;
		this.ro = ro;
	}
	
	public RisorseMateriali getRisorseMateriali() {
		return rm;
	}
	
	public RisorseUmane getRisorseUmane() {
		return ru;
	}
	
	public RepartoOperativo getRepartoOperativo() {
		return ro;
	}
}

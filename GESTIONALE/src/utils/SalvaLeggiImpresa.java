package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import operativo.RepartoOperativo;

public class SalvaLeggiImpresa implements Serializable {
	private Impresa i;
	
	public SalvaLeggiImpresa(Impresa i) {
		this.i = i;
	}
	
	public void SalvaImpresa(String nomeFile) {
		File file = new File(nomeFile);
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(i);
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void CaricaImpresa(String nomeFile) {
		File file = new File(nomeFile);
		if(file.exists()) {
			try {
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				i = (Impresa) in.readObject();
				in.close();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Impresa getImpresa() {
		return i;
	}
	
}

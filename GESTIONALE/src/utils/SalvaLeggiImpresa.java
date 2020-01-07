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

/**
 * Questa classe si occupa di salvare su file un'impresa e caricare da file un'impresa.
 * 
 * @author Andrea Ercolino
 *
 */
public class SalvaLeggiImpresa implements Serializable {
	private Impresa i;
	
	public SalvaLeggiImpresa(Impresa i) {
		this.i = i;
	}
	
	/**
	 * Dato il nome di un file salva l'impresa all'interno del file 
	 * @param nomeFile nome del file che verrà creato per conservare l'impresa
	 */
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
	
	
	/**
	 * Dato il nome di un file carica l'impresa dal dato file se questo esiste
	 * @param nomeFile nome del file dal quale verrà caricata l'impresa
	 */
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

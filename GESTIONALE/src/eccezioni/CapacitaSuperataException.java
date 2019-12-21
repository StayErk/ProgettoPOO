package eccezioni;
/**
 * Questa Eccezione controllata ha lo scopo di segnalare quando la capacità del magazzino viene superata.
 * @author Andrea Ercolino
 *
 */
public class CapacitaSuperataException extends Exception {
	public CapacitaSuperataException() {
		super();
	}
	
	public CapacitaSuperataException(String msg) {
		super(msg);
	}
}

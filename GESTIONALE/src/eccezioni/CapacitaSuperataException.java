package eccezioni;

public class CapacitaSuperataException extends Exception {
	public CapacitaSuperataException() {
		super();
	}
	
	public CapacitaSuperataException(String msg) {
		super(msg);
	}
}

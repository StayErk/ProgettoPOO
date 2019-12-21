package eccezioni;

/**
 * Questa eccezione non controllata ha lo scopo di segnalare quando un prodotto richiesto non è presente in magazzino
 * @author stayerk
 *
 */
public class ProdottoNonPresenteException extends RuntimeException {
	public ProdottoNonPresenteException() {
		super();
	}
	
	public ProdottoNonPresenteException(String msg) {
		super(msg);
	}
}

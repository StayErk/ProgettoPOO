package eccezioni;

public class ProdottoNonPresenteException extends RuntimeException {
	public ProdottoNonPresenteException() {
		super();
	}
	
	public ProdottoNonPresenteException(String msg) {
		super(msg);
	}
}

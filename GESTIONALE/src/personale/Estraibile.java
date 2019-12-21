package personale;

public interface Estraibile<T extends Dipendente> {
	boolean estrai(T d);
}

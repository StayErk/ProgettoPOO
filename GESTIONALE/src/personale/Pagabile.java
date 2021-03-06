package personale;

import java.io.Serializable;

public interface Pagabile extends Serializable{
	final static double STIPENDIO_DIRIGENTE = 2800;
	final static double STIPENDIO_QUADRO = 200;
	final static double STIPENDIO_IMPIEGATOFT = 1750;
	final static double STIPENDIO_IMPIEGATOPT = 875;
	final static double STIPENDIO_OPERAIO = 1250;
	final static double BONUS_OPERAIO = 7.00;
	final static double BONUS_IMPIEGATO = 20.00;
	
	static final int ORE_FULLTIME = 40;
	static final int ORE_PARTTIME = 24;
	
	double paga();
	void setStatoPagamento();
	void resetStatoPagamento();
	boolean getStatoPagamento(); 

}

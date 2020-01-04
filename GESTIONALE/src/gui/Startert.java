package gui;

import javax.swing.JFrame;

import utils.Impresa;
import utils.SalvaLeggiImpresa;

public class Startert {

	public static void main(String[] args) {
		Impresa i = null;
		SalvaLeggiImpresa loader = new SalvaLeggiImpresa(i);
		loader.CaricaImpresa("impresa.dat");
		JFrame frame = new GUIAmministrativo(loader.getImpresa());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

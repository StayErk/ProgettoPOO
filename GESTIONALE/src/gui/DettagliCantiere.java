package gui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import operativo.Cantiere;
import operativo.Squadra;
import personale.Dipendente;
import personale.Operaio;

public class DettagliCantiere extends JFrame {
	private JTextArea dettagli;
	private Cantiere ca;
	
	
	public DettagliCantiere(Cantiere ca) {
		this.ca = ca;
		dettagli = new JTextArea();
		dettagli.setEditable(false);
		JScrollPane pane = new JScrollPane(dettagli);
		Dipendente capocantiere = (Dipendente) ca.getCapocantiere();
		dettagli.append("Responsabile Cantiere: " + capocantiere.getNome() + " " +  capocantiere.getCognome() + "\n");
		dettagli.append("Committente: " + ca.getCliente() + "\n");
		dettagli.append("Valore del cantiere: " + ca.getValoreCantiere() + "\n");
		dettagli.append("Squadre al lavoro nel cantiere:\n");
		for(Squadra team :ca.getSquadre()) {
			dettagli.append("  -Caposquadra: " + team.getCaposquadra().getNome() + " " + team.getCaposquadra().getCognome() + "\n");
			for(Dipendente d:team.getGruppo()) {
				Operaio o = (Operaio) d;
				dettagli.append("   +" + o.getNome() + " " + o.getCognome() + " " + "ha lavorato precedentemente in: " + o.getNumeroCantieri() + " Cantieri(e)\n");
			}
		}
		add(pane);
		setSize(500, 750);
	}
}

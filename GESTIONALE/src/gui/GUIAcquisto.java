package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



import amministrativo.RisorseMateriali;
import eccezioni.CapacitaSuperataException;
import esterno.Fornitore;
import materiale.MaterialeDaCostruzione;

public class GUIAcquisto extends JFrame {
	RisorseMateriali rm;
	
	private JComboBox<String> fornitori;
	private JComboBox<String> catalogo;
	
	private JButton aggiungiAlCarrello;
	private JButton compra;
	
	private JLabel infoSulCarico;
	private JLabel infoCostoCarrello;
	
	private ArrayList<MaterialeDaCostruzione> carrello;
	private Fornitore<MaterialeDaCostruzione> fornitoreSelezionato;
	private double costoCarrello;
	private int caricoFuturo;
	
	private JTextArea vediCarrello;
	
	
	public GUIAcquisto(RisorseMateriali rm) {
		this.rm = rm;
		catalogo = new JComboBox<String>();
		fornitori = new JComboBox<String>();
		carrello = new ArrayList<MaterialeDaCostruzione>();
		fornitoreSelezionato = null;
		for(Fornitore<MaterialeDaCostruzione> f: rm.getFornitori()) {
			fornitori.addItem(f.getNome());
		}
		
		vediCarrello = new JTextArea();
		
		aggiungiAlCarrello = new JButton("Aggiungi al Carrello");
		compra = new JButton("Compra");
		
		infoSulCarico = new JLabel("Carico del magazzino dopo l'aquisto: "+ rm.getMagazzino().getCaricoAttuale()+"/"+rm.getMagazzino().getCapacitaMax());
		costoCarrello = 0;
		caricoFuturo = 0;
		infoCostoCarrello = new JLabel("€");
		
		add(mainPanel());
		
		setSize(650, 430);
	}
	
	private JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(leftPanel());
		p.add(rightPanel());
		
		return p;
	}
	
	private JPanel leftPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(fornitoreChoice());
		p.add(prodottoChoice());
		p.add(aggiuntaAlCarrello());
		return p;
	}
	
	private JPanel fornitoreChoice() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				for(Fornitore<MaterialeDaCostruzione> f: rm.getFornitori()) {
					if(fornitori.getSelectedItem().equals(f.getNome())) {
						fornitoreSelezionato = f;
						System.out.println(f);
						for(MaterialeDaCostruzione m: f.getCatalogo()) {
							catalogo.addItem(m.getClass().getSimpleName() + " - peso: " + m.getPeso() + " - valore: " + m.getValoreProdotto());
						}
					}
				}
				
			}
			
		}
		fornitori.addActionListener(new Click());
		p.add(new JLabel("Scegli Fornitore"));
		p.add(fornitori);
		return p;
	}
	
	private JPanel prodottoChoice() {
		JPanel p = new JPanel();
		p.add(new JLabel("Scegli prodotto"));
		p.add(catalogo);
		return p;
	}
	
	private JPanel aggiuntaAlCarrello() {
		JPanel p = new JPanel();
		aggiungiAlCarrello.addActionListener(new AggiuntaAlCarrello());
		p.add(aggiungiAlCarrello);
		return p;
	}
	
	private JPanel rightPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(infoSection());
		p.add(buySection());
		return p;
	}
	
	private JPanel infoSection() {
		JPanel  p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(infoSulCarico);
		JScrollPane pane = new JScrollPane(vediCarrello);
		p.add(pane);
		p.add(infoCostoCarrello);
		return p;
	}
	
	private JPanel buySection() {
		JPanel p = new JPanel();
		compra.addActionListener(new EffettuaAcquisto());
		p.add(compra);
		return p;
	}
	
	private class AggiuntaAlCarrello implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			for(MaterialeDaCostruzione m: fornitoreSelezionato.getCatalogo()) {
				if(catalogo.getSelectedItem().equals(m.getClass().getSimpleName() + " - peso: " + m.getPeso() + " - valore: " + m.getValoreProdotto())) {
					System.out.println(m);
					vediCarrello.append(m.getClass().getSimpleName() + " - peso: " + m.getPeso() + " - valore: " + m.getValoreProdotto()+"\n");
					caricoFuturo += m.getPeso();
					infoSulCarico.setText("Carico del magazzino dopo l'aquisto: "+ (rm.getMagazzino().getCaricoAttuale()+caricoFuturo)+"/"+rm.getMagazzino().getCapacitaMax());
					costoCarrello += m.getValoreProdotto();
					infoCostoCarrello.setText(costoCarrello + "€");
					carrello.add(m);
					System.out.println(carrello);
				}
			}
			
		}
		
	}
	
	private class EffettuaAcquisto implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			try {
				rm.acquistaMateriali(carrello);
				dispose();
			}
			catch (CapacitaSuperataException e) {
				vediCarrello.setText("Non è possibile acquistare i prodotti selezionati. Si è superata la capacità massima");
				carrello.clear();
				costoCarrello = 0;
				caricoFuturo = 0;
			}
		}
		
	}
	
}

package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
	private JTextArea risultatiRicerca;
	
	private JTextField input;
	private JButton cerca;
	
	
	
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
		risultatiRicerca = new JTextArea();
		
		input = new JTextField(8);
		cerca = new JButton("Cerca");
		aggiungiAlCarrello = new JButton("Aggiungi al Carrello");
		compra = new JButton("Compra");
		
		infoSulCarico = new JLabel("Carico del magazzino dopo l'aquisto: "+ rm.getMagazzino().getCaricoAttuale()+"/"+rm.getMagazzino().getCapacitaMax());
		costoCarrello = 0;
		caricoFuturo = 0;
		infoCostoCarrello = new JLabel("€");
		
		add(mainPanel());
		
		setSize(950, 430);
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
		p.setLayout(new GridLayout(2, 1));
		p.add(searchZone());
		p.add(buyZone());
		return p;
	}
	
	private JPanel buyZone() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Buy"));
		p.setLayout(new GridLayout(3, 1));
		p.add(fornitoreChoice());
		p.add(prodottoChoice());
		p.add(aggiuntaAlCarrello());
		return p;
	}
	
	private JPanel searchZone() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.setBorder(new TitledBorder(new EtchedBorder(), "Search"));
		p.add(upZone());
		p.add(downZone());
		return p;
	}
	
	private JPanel downZone() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1,1));
		JScrollPane pane = new JScrollPane(risultatiRicerca);
		p.add(pane);
		return p;
	}
	private JPanel upZone() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(inputZone());
		p.add(confirmZone());
		return p;
	}
	
	private JPanel inputZone() {
		JPanel p = new JPanel();
		p.add(new JLabel("Ricerca prodotto"));
		p.add(input);
		return p;
	}
	
	private JPanel confirmZone() {
		JPanel p = new JPanel();
		cerca.addActionListener(new effettuaRicerca());
		p.add(cerca);
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
						catalogo.removeAllItems();
						for(MaterialeDaCostruzione m: f.getCatalogo()) {
							catalogo.addItem(m.getClass().getSimpleName() + " codice: " +m.getCodiceProdotto() + " -peso: " + m.getPeso() + ", " + m.getValoreProdotto() + "€");
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
		p.setBorder(new TitledBorder(new EtchedBorder(), "Carrello"));
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
				if(catalogo.getSelectedItem().equals(m.getClass().getSimpleName() + " codice: " +m.getCodiceProdotto() + " -peso: " + m.getPeso() + ", "  + m.getValoreProdotto() + "€")) {
					System.out.println(m);
					vediCarrello.append(m.getClass().getSimpleName() + " codice: " +m.getCodiceProdotto() + " -peso: " + m.getPeso()  + ", "+ m.getValoreProdotto() + "€"+"\n");
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
				System.out.println("ACQUISTO BENI Capitale Azienda: " + rm.getCapitale());
				rm.acquistaMateriali(carrello);
				System.out.println("ACQUISTO BENI Capitale Azienda: " + rm.getCapitale());
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
	
	private class effettuaRicerca implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			boolean flag;
			risultatiRicerca.setText("");
			risultatiRicerca.setText("Il prodotto: " + input.getText() +" può essere trovato dai fornitori:\n");
			for(Fornitore<MaterialeDaCostruzione> f: rm.getFornitori()) {
				flag = false;
				for(MaterialeDaCostruzione m : f.getCatalogo()) {
					if(m.getCodiceProdotto().contains(input.getText())) {
						flag = true;
					}
				}
				if(flag) risultatiRicerca.append(" " + f.getNome()+"\n");
			}
		}
		
	}
	
}

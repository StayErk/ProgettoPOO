package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import materiale.MaterialeDaCostruzione;
import operativo.RepartoOperativo;
import personale.Dipendente;
import personale.Dirigente;
import personale.Operaio;
import personale.Quadro;
import personale.Responsabile;
import utils.Estraibile;

public class GUICreazioneCantiere extends JFrame {
	private ArrayList<MaterialeDaCostruzione> materiali;
	private JTextArea materialiAggiunti;
	private JButton conferma;
	private JButton aggiungiMateriale;
	private JComboBox<String> materialiDaScegliere;
	private JComboBox<String> responsabiliComboBox;
	private double valoreCantiere = 0;
	private String nomeCommittente;

	private RisorseUmane ru;
	private RisorseMateriali rm;
	private RepartoOperativo ro;
	private JScrollPane pane;
	private Estraibile<Dipendente> criterioResponsabile;
	private Estraibile<MaterialeDaCostruzione> criterioMateriale;
	private JRadioButton viti;
	private JRadioButton betoniere;
	private JRadioButton trapani;
	private JRadioButton tubiInnocenti;
	private JLabel valoreTotaleCantiere;
	
	
	public GUICreazioneCantiere(double valoreCantiere, String nomeCommittente,  RisorseUmane ru, RisorseMateriali rm, RepartoOperativo ro) {
		this.valoreCantiere += valoreCantiere;
		this.nomeCommittente = nomeCommittente;
		this.rm = rm;
		this.ru = ru;
		this.ro = ro;

		materialiDaScegliere = new JComboBox<String>();
		criterioResponsabile = (p)-> (p.getClass().getSimpleName().equals("Dirigente") && p.getStato() == false) || (p.getClass().getSimpleName().equals("Quadro") && p.getStato() == false);
		criterioMateriale = (p)->p.getClass().getSimpleName().equals("Betoniera");
		valoreTotaleCantiere = new JLabel("Valore totale Cantiere: " + valoreCantiere+"");
		
		responsabiliComboBox = new JComboBox<String>();
		for (Dipendente d: ru.scegliDipendenti(criterioResponsabile)) {
			if (valoreCantiere > 500000) {
				if(d instanceof Dirigente) responsabiliComboBox.addItem(d.getNome() + " " + d.getCognome());
			}
			responsabiliComboBox.addItem(d.getNome() + " " + d.getCognome());
		}
		materiali = new ArrayList<MaterialeDaCostruzione>();
		conferma = new JButton("Conferma Apertura Cantiere");
		materialiAggiunti = new JTextArea();
		materialiAggiunti.setEditable(false);
		pane = new JScrollPane(materialiAggiunti);
		aggiungiMateriale = new JButton("Aggiungi Materiale");
		
		viti = new JRadioButton("Viti");
		betoniere = new JRadioButton("Betoniere");
		trapani = new JRadioButton("Trapani");
		tubiInnocenti = new JRadioButton("Tubi Innocenti");
		add(mainPanel());
		setSize(500, 350);
	}
	
	private JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(createSelectionPanel());
		p.add(createConfirmPanel());
		return p;
	}
	
	private JPanel createSelectionPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		p.add(createResponsabileChoose());
		p.add(createCategorySelection());
		p.add(createAggiuntaMateriale());
		p.add(createAggiungiButton());
		return p;
	}
	
	private JPanel createResponsabileChoose() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("Scegli Responsabile: "));
		p.add(responsabiliComboBox);
		return p;
	}
	
	private JPanel createCategorySelection() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Categorie"));
		ButtonGroup group = new ButtonGroup();
		group.add(betoniere);
		group.add(tubiInnocenti);
		group.add(trapani);
		group.add(viti);
		betoniere.setSelected(true);
		
		class Click implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				materialiDaScegliere.removeAllItems();
				if (betoniere.isSelected()) {
					criterioMateriale = (p)->p.getClass().getSimpleName().equals("Betoniera");
					ArrayList<MaterialeDaCostruzione> materiali = rm.scegliMateriale(criterioMateriale);
					for (MaterialeDaCostruzione m:materiali) {
						materialiDaScegliere.addItem(m.getCodiceProdotto() + " " + m.getPeso() + " " + m.getValoreProdotto());
					}
				}
				else if (viti.isSelected()) {
					criterioMateriale = (p)->p.getClass().getSimpleName().equals("Viti");
					ArrayList<MaterialeDaCostruzione> materiali = rm.scegliMateriale(criterioMateriale);
					for (MaterialeDaCostruzione m:materiali) {
						materialiDaScegliere.addItem(m.getCodiceProdotto() + " " + m.getPeso() + " " + m.getValoreProdotto());
					}
				}
				else if(trapani.isSelected()) {
					criterioMateriale = (p)->p.getClass().getSimpleName().equals("Trapano");
					ArrayList<MaterialeDaCostruzione> materiali = rm.scegliMateriale(criterioMateriale);
					for (MaterialeDaCostruzione m:materiali) {
						materialiDaScegliere.addItem(m.getCodiceProdotto() + " " + m.getPeso() + " " + m.getValoreProdotto());
					}
				}
				else {
					criterioMateriale = (p)->p.getClass().getSimpleName().equals("TubiInnocenti");
					ArrayList<MaterialeDaCostruzione> materiali = rm.scegliMateriale(criterioMateriale);
					for (MaterialeDaCostruzione m:materiali) {
						materialiDaScegliere.addItem(m.getCodiceProdotto() + " " + m.getPeso() + " " + m.getValoreProdotto());
					}
				}
				
			}
			
		}
		betoniere.addActionListener(new Click());
		viti.addActionListener(new Click());
		trapani.addActionListener(new Click());
		tubiInnocenti.addActionListener(new Click());
		
		p.add(viti);
		p.add(trapani);
		p.add(tubiInnocenti);
		p.add(betoniere);
		
		return p;
	}
	
	private JPanel createAggiuntaMateriale() {
		JPanel p = new JPanel();

		p.add(valoreTotaleCantiere);
		p.add(materialiDaScegliere);
		
		return p;
	}
	
	private JPanel createAggiungiButton() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				int index = -1;
				boolean flag = false;
				for (MaterialeDaCostruzione d: rm.scegliMateriale(criterioMateriale)) {
					if(materialiDaScegliere.getSelectedItem().equals(d.getCodiceProdotto() + " " + d.getPeso() + " " + d.getValoreProdotto()) && !flag) {
						System.out.println(d+"\n");
						index = materialiDaScegliere.getSelectedIndex();
						materialiAggiunti.append(d.getCodiceProdotto() + " " + d.getPeso() + " " + d.getValoreProdotto()+"\n");
						materiali.add(d);
						valoreCantiere += d.getValoreProdotto();
						valoreTotaleCantiere.setText("Valore totale Cantiere: " + valoreCantiere);
						flag = true;
						rm.scaricaMateriale(d.getCodiceProdotto());
						System.out.println("array: " +materiali);
					}
				}
				if (index != -1) materialiDaScegliere.removeItemAt(index);
			}
			
		}
		aggiungiMateriale.addActionListener(new Click());
		p.add(aggiungiMateriale);
		
		return p;
	}
	
	private JPanel createConfirmPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(materialiSceltiPanel());
		p.add(buttonPanel());
		return p;
	}
	
	private JPanel materialiSceltiPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(new JLabel("Materiali scelti:"));
		p.add(pane);
		return p;
	}
	
	private JPanel buttonPanel() {
		JPanel p = new JPanel();
		
		class Click implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				Responsabile r = null;
				for (Dipendente d: ru.scegliDipendenti(criterioResponsabile)) {
					if (responsabiliComboBox.getSelectedItem().equals(d.getNome() + " " + d.getCognome())) {
						r = (Responsabile) d;
					}
				}
				ro.apriCantiere(valoreCantiere, materiali, r, nomeCommittente);
				System.out.println("GUI CREAZIONE: " + ro.getCantieriAperti());
				dispose();
			}
			
		}
		conferma.addActionListener(new Click());
		p.add(conferma);
		return p;
	}
}

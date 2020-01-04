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

import amministrativo.RisorseUmane;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import operativo.Squadra;
import personale.Dipendente;
import personale.Operaio;
import personale.Quadro;
import utils.Estraibile;

public class GUISquadra extends JFrame {
	private RepartoOperativo ro;
	private RisorseUmane ru;
	private Cantiere daModificare;
	private ArrayList<Dipendente> operai;
	private JTextArea dipendetiSelezionati;
	private JComboBox<String> quadriComboBox;
	private JComboBox<String> operaiComboBox;
	private JButton aggiungiOp;
	private JButton creaSquadra;
	
	private Estraibile<Dipendente> criterioOperai;
	private Estraibile<Dipendente> criterioQuadro;
	
	public GUISquadra(RepartoOperativo ro, RisorseUmane ru, Cantiere daModificare) {
		this.daModificare = daModificare;
		this.ro = ro;
		this.ru = ru;
		operai = new ArrayList<Dipendente>();
		aggiungiOp = new JButton("Aggiungi Operaio");
		creaSquadra = new JButton("Crea Squadra");
		dipendetiSelezionati = new JTextArea();
		quadriComboBox = new JComboBox<String>();
		operaiComboBox = new JComboBox<String>();
		criterioOperai = (p)->p.getClass().getSimpleName().equals("Operaio") && p.getStato() == false;
		for (Dipendente d: ru.scegliDipendenti(criterioOperai)) {
			Operaio o = (Operaio) d;
			operaiComboBox.addItem(o.getNome() + " " + o.getCognome());
		}
		criterioQuadro = (p)->p.getClass().getSimpleName().equals("Quadro") && p.getStato() == false;
		for (Dipendente d: ru.scegliDipendenti(criterioQuadro)) {
			Quadro q = (Quadro) d;
			quadriComboBox.addItem(q.getNome() + " " + q.getCognome());
		}
		setSize(500, 350);
		add(mainPanel());
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
		p.add(quadroPanel());
		p.add(operaiPanel());
		p.add(aggiungiOperaioPanel());
		return p;
	}
	
	private JPanel quadroPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("Scegli Quadro: "));
		p.add(quadriComboBox);
		return p;
	}
	
	private JPanel operaiPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("Scegli Oparai:"));
		p.add(operaiComboBox);
		return p;
	}
	
	private JPanel aggiungiOperaioPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				Operaio o = null;
				for (Dipendente d: ru.scegliDipendenti(criterioOperai)) {
					o = (Operaio) d;
					if(operaiComboBox.getSelectedItem().equals(o.getNome() + " " + o.getCognome())) {
						operaiComboBox.removeItemAt(operaiComboBox.getSelectedIndex());
						dipendetiSelezionati.append(operaiComboBox.getSelectedItem()+"\n");
						operai.add(o);
					}
				}
			}
			
		}
		aggiungiOp.addActionListener(new Click());
		p.add(aggiungiOp);
		return p;
	}
	
	private JPanel rightPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(textAreaPanel());
		p.add(buttonPanel());
		return p;
	}
	
	private JPanel textAreaPanel() {
		JPanel  p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(new JLabel("Operai Selezionati"));
		JScrollPane pane = new JScrollPane(dipendetiSelezionati);
		p.add(pane);
		return p;
	}
	
	private JPanel buttonPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				for (Dipendente d: ru.scegliDipendenti(criterioQuadro)) {
					Quadro q = (Quadro) d;
					if(quadriComboBox.getSelectedItem().equals(q.getNome() + " " + q.getCognome())) {
						Squadra team = new Squadra(q);
						team.aggiungiOperaio(operai);
						ro.aggiungiSquadraACantiere(daModificare, team);
						dispose();
					}
				}
					
				
			}

		}
		creaSquadra.addActionListener(new Click());
		p.add(creaSquadra);
		return p;
	
}
	
	
	
	
	
}

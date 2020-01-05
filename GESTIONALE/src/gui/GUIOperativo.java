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
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import operativo.Cantiere;
import operativo.RepartoOperativo;
import personale.Responsabile;
import utils.Impresa;
import personale.Dipendente;
import utils.Estraibile;

public class GUIOperativo extends JFrame{
	private JRadioButton chiudiCantiere;
	private JRadioButton aggiungiSquadra;
	private JRadioButton mostraDettagli;
	private JButton apriCantiere;
	private JButton esegui;
	private JButton refresh;
	private JComboBox<String> cantieriComboBox;

	private JTextField cliente;
	private JTextField valoreIniziale;

	private RisorseMateriali rm;
	private RisorseUmane ru;
	private RepartoOperativo ro;
	
	public GUIOperativo(Impresa i) {
		rm = i.getRisorseMateriali();
		ru = i.getRisorseUmane();
		ro = i.getRepartoOperativo();
		chiudiCantiere = new JRadioButton("chiudi cantiere");
		aggiungiSquadra = new JRadioButton("aggiungi squadra");
		mostraDettagli = new JRadioButton("mostra dettagli");
		apriCantiere = new JButton("Apri Cantiere");
		refresh = new JButton("Ricarica");
		cantieriComboBox = new JComboBox<String>();
		for(Cantiere ca:ro.getCantieriAperti()) {
			Dipendente d = (Dipendente) ca.getCapocantiere();
			cantieriComboBox.addItem("Dirigente: " + d.getCognome() + " di: " + ca.getCliente());
		}
		esegui = new JButton("Esegui");
	
		cliente = new JTextField();
		valoreIniziale = new JTextField();
		add(mainPanel());
		setSize(800, 400);
		setTitle("Gestione Cantieri");
	}
	
	public JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(editPanel());
		p.add(createPanel());
		return p;
	}
	
	public JPanel editPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(sceltaCantiereSection());
		p.add(buttonSection());
		p.setBorder(new TitledBorder(new EtchedBorder(), "tab gestione"));
		return p;
	}
	
	private JPanel sceltaCantiereSection() {
		JPanel p = new JPanel();
		p.add(new JLabel("Scegli Cantiere: "));
		p.add(cantieriComboBox);
		return p;
	}
	
	public JPanel buttonSection() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(selectionPanel());
		p.add(execPanel());
		return p;
	}
	
	public JPanel selectionPanel() {
		JPanel p = new JPanel();
		ButtonGroup group = new ButtonGroup();
		group.add(aggiungiSquadra);
		group.add(chiudiCantiere);
		group.add(mostraDettagli);
		p.setBorder(new TitledBorder(new EtchedBorder(), "scelta"));
		p.add(aggiungiSquadra);
		p.add(chiudiCantiere);
		p.add(mostraDettagli);
		chiudiCantiere.setSelected(true);
		return p; 
	}
	
	public JPanel execPanel() {
		JPanel p = new JPanel();
		class Click implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				if (chiudiCantiere.isSelected())
				{
					Cantiere daChiudere = null;
					for(Cantiere ca :ro.getCantieriAperti()) {
						Dipendente d = (Dipendente) ca.getCapocantiere();
						if(cantieriComboBox.getSelectedItem().equals("Dirigente: " + d.getCognome() + " di: " + ca.getCliente())) {
							daChiudere = ca;
							 cantieriComboBox.removeItemAt(cantieriComboBox.getSelectedIndex());
						}
					}
					if(daChiudere == null) System.out.println("NULL");
					System.out.println(daChiudere);
					if(daChiudere != null) rm.chiusuraContratto(ro.chiudiCantiere(daChiudere));
					
				}
				else if (aggiungiSquadra.isSelected()){
					Cantiere c = null;
					for(Cantiere ca :ro.getCantieriAperti()) {
						Dipendente d = (Dipendente) ca.getCapocantiere();
						if(cantieriComboBox.getSelectedItem().equals("Dirigente: " + d.getCognome() + " di: " + ca.getCliente())) {
							 c = ca;
							 JFrame squadraChooser = new GUISquadra(ro, ru, ca);
							 squadraChooser.setVisible(true);
						}
					}
					
				}
				else {
					Cantiere c = null;
					for(Cantiere ca :ro.getCantieriAperti()) {
						Dipendente d = (Dipendente) ca.getCapocantiere();
						if(cantieriComboBox.getSelectedItem().equals("Dirigente: " + d.getCognome() + " di: " + ca.getCliente())) {
							 c = ca;
							 JFrame details = new DettagliCantiere(ca);
							 details.setVisible(true);
							 details.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						}
					}
				}
			}
		}
		esegui.addActionListener(new Click());
		p.add(esegui);
		return p;
	}
	
	public JPanel createPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.setBorder(new TitledBorder(new EtchedBorder(), "tab creazione"));
		p.add(secondRow());
		p.add(thirdRow());
		p.add(fourthRow());
		
		return p;
	}
	
	private JPanel secondRow() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("Nominativo cliente:"));
		p.add(cliente);
		return p;
	}
	private JPanel thirdRow() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(new JLabel("Valore Iniziale:"));
		p.add(valoreIniziale);
		return p;
	}
	private JPanel fourthRow() {
		JPanel p = new JPanel();
		class Click implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {
				JFrame confermaCrezioneCantiere = new GUICreazioneCantiere(Double.parseDouble(valoreIniziale.getText()), cliente.getText(),  ru, rm, ro);
				confermaCrezioneCantiere.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				valoreIniziale.setText("");
				cliente.setText("");
				confermaCrezioneCantiere.setVisible(true);
			}
		}
		apriCantiere.addActionListener(new Click());
		p.add(apriCantiere);
		
		class Refresh implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				cantieriComboBox.removeAllItems();
				for(Cantiere ca:ro.getCantieriAperti()) {
					Dipendente d = (Dipendente) ca.getCapocantiere();
					cantieriComboBox.addItem("Dirigente: " + d.getCognome() + " di: " + ca.getCliente());
				}
				
			}
			
		}
		refresh.addActionListener(new Refresh());
		p.add(refresh);
		return p;
	}
	
	
	
	
	
	
}

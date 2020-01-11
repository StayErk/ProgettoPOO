package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.print.attribute.standard.PDLOverrideSupported;
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
import utils.Estrattore;

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
	
	private JTextArea infoCantieri;
	
	private JRadioButton nomeCliente;
	private JRadioButton nomeResponsabile;
	private JRadioButton rangeValore;
	private JLabel input1Label;
	private JLabel input2Label;
	
	private JButton report;
	
	private JTextField input1;
	private JTextField input2;

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
		input1Label = new JLabel("Valore minimo");
		input2Label = new JLabel("Valore massimo");
		report = new JButton("Genera Report");
		input1 = new JTextField(8);
		input2 = new JTextField(8);
		nomeCliente = new JRadioButton("Nome Cliente");
		nomeResponsabile = new JRadioButton("Nome Responsabile");
		rangeValore = new JRadioButton("Range Valore");
		infoCantieri = new JTextArea();
		infoCantieri.setEditable(false);
		cantieriComboBox = new JComboBox<String>();
		for(Cantiere ca:ro.getCantieriAperti()) {
			Dipendente d = (Dipendente) ca.getCapocantiere();
			cantieriComboBox.addItem("Dirigente: " + d.getCognome() + " di: " + ca.getCliente());
		}
		esegui = new JButton("Esegui");
	
		cliente = new JTextField();
		valoreIniziale = new JTextField();
		add(mainPanel());
		setSize(1200, 650);
		setTitle("Gestione Cantieri");
	}
	
	public JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(rightPanel());
		p.add(createPanel());
		return p;
	}
	
	public JPanel rightPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(editPanel());
		p.add(reportPanel());
		return p;
	}
	
	private JPanel reportPanel() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Report tab"));
		p.setLayout(new GridLayout(2,1));
		p.add(infoCantieriArea());
		p.add(sceltaCantieriArea());
		return p;
	}
	
	private JPanel sceltaCantieriArea() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(sceltaCriterio());
		p.add(inputZone());
		return p;
	}
	
	private JPanel sceltaCriterio() {
		JPanel p = new JPanel();
		ButtonGroup g = new ButtonGroup();
		p.setLayout(new GridLayout(3, 1));
		p.setBorder(new TitledBorder(new EtchedBorder(), "Scelta criterio"));
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(nomeCliente.isSelected()) {
					input2.setEditable(false);
					input2Label.setText("");
					input1.setEditable(true);
					input1Label.setText("Nome cliente");
				}
				else if (nomeResponsabile.isSelected()) {
					input2.setEditable(false);
					input2Label.setText("");
					input1.setEditable(true);
					input1Label.setText("Nome responsabile");
				}
				else {
					input2.setEditable(true);
					input2Label.setText("Valore minimo");
					input1.setEditable(true);
					input1Label.setText("Valore massimo");
				}
				
			}
			
		}
		rangeValore.setSelected(true);
		nomeCliente.addActionListener(new Click());
		nomeResponsabile.addActionListener(new Click());
		rangeValore.addActionListener(new Click());
		g.add(nomeCliente);
		g.add(nomeResponsabile);
		g.add(rangeValore);
		p.add(nomeCliente);
		p.add(nomeResponsabile);
		p.add(rangeValore);
		return p;
	}
	
	private JPanel inputZone() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(input1Zone());
		p.add(input2Zone());
		p.add(createButton());
		return p;
	}
	
	private JPanel input1Zone() {
		JPanel p = new JPanel();
		p.add(input1Label);
		p.add(input1);
		return p;
	}
	private JPanel input2Zone() {
		JPanel p = new JPanel();
		p.add(input2Label);
		p.add(input2);
		return p;
	}
	
	private JPanel createButton() {
		JPanel p = new JPanel();
		report.addActionListener(new GeneraReport());
		p.add(report);
		return p;
	}
	
	
	private JPanel infoCantieriArea() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 1));
		JScrollPane pane = new JScrollPane(infoCantieri);
		p.add(pane);
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
		p.setLayout(new GridLayout(3, 1));
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
	
	private class GeneraReport implements ActionListener{

		
		public void actionPerformed(ActionEvent arg0) {
			Estraibile<Cantiere> criterio;
			ArrayList<Cantiere> lista = new ArrayList<Cantiere>();
			Estrattore<Cantiere> estrattore = new Estrattore<Cantiere>(ro.getCantieriAperti());
			infoCantieri.setText("");
			if(nomeCliente.isSelected()) {
				criterio = (p) -> p.getCliente().contains(input1.getText());
				estrattore.setCriterio(criterio);
				lista = estrattore.estrai();
				for(Cantiere c : lista) {
					Dipendente p = (Dipendente) c.getCapocantiere();
					infoCantieri.append("Direttore del cantiere: " + p.getCognome() + " " + p.getNome() + " commissionato da: " + c.getCliente() + " del valore di: " + c.getValoreCantiere() +"\n" + "  Al Cantiere lavorano: " + c.getSquadre().size()+" squadre\n\n"); 
				}
			}
			else if (nomeResponsabile.isSelected()) {
				criterio =(p) -> {
						Dipendente d = (Dipendente) p.getCapocantiere();
						return (d.getNome().contains(input1.getText()) || d.getCognome().contains(input1.getText()));
				};
				estrattore.setCriterio(criterio);
				lista = estrattore.estrai();
				for(Cantiere c : lista) {
					Dipendente p = (Dipendente) c.getCapocantiere();
					infoCantieri.append("Direttore del cantiere: " + p.getCognome() + " " + p.getNome() + " commissionato da: " + c.getCliente() + " del valore di: " + c.getValoreCantiere() +"\n" + "  Al Cantiere lavorano: " + c.getSquadre().size()+" squadre\n\n"); 
				}
			}
			else {
				try {
					criterio = (p) -> p.getValoreCantiere() >= Double.parseDouble(input1.getText()) && p.getValoreCantiere() <= Double.parseDouble(input2.getText());
					estrattore.setCriterio(criterio);
					lista = estrattore.estrai();
					for(Cantiere c : lista) {
						infoCantieri.append("Direttore del cantiere: " + c.getCapocantiere() + " commissionato da: " + c.getCliente() + " del valore di: " + c.getValoreCantiere() +"\n" + "  Al Cantiere lavorano: " + c.getSquadre().size()+"\n\n"); 
					}
				}
				catch (NumberFormatException e) {
					input1.setText("0");
					input2.setText("0");
				}
				
			}
			input1.setText("");
			input2.setText("");
			
		}
		
	}
	
	
	
	
}

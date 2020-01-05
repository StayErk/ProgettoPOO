package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import amministrativo.RepartoAmministrativo;
import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import materiale.Magazzino;
import materiale.MaterialeDaCostruzione;
import operativo.RepartoOperativo;
import utils.Impresa;
import utils.SalvaLeggiImpresa;

public class InitFrame extends JFrame {
	private JButton amministrativo;
	private JButton operativo;
	
	private JButton esegui;
	
	private JRadioButton salva;
	private JRadioButton carica;
	private JRadioButton nuova;
	
	private JLabel capitaleAzienda;
	private JLabel prossimiProfitti;
	
	private Impresa i;
	
	private JTextField input;
	
	private JTextField capacitaMagazzino;
	private JTextField capitaleIniziale;
	
	
	private File corrente;
	
	public InitFrame() {
		amministrativo = new JButton("Reparto Amministrativo");
		operativo = new JButton("Reparto Operativo");
		esegui = new JButton("Esegui");
		
		salva = new JRadioButton("Salva");
		carica = new JRadioButton("Carica");
		nuova = new JRadioButton("Nuovo");
		
		
		i = null;
		
		input = new JTextField(8);
		capacitaMagazzino = new JTextField(8);
		capitaleIniziale = new JTextField(8);
		
		capitaleAzienda = new JLabel("capitale dell'azienda: ");
		prossimiProfitti = new JLabel("Profitti futuri: ");
		
		add(mainPanel());
		setTitle("Melenzanetti");
		setSize(500, 750);
		
	}
	
	private JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(bottomPanel());
		p.add(leftPanel());
		
		
		return p;
	}
	
	private JPanel leftPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(createSelectionPanel());
		p.add(createInputPanel());
		p.add(createButtonPanel());
		return p;
	}
	
	private JPanel createSelectionPanel() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Gestisci impresa"));
		p.setLayout(new GridLayout(3, 1));
		ButtonGroup g = new ButtonGroup();
		g.add(salva);
		g.add(carica);
		g.add(nuova);
		p.add(salva);
		p.add(carica);
		p.add(nuova);
		salva.addActionListener(new ChoiceUpgrade());
		carica.addActionListener(new ChoiceUpgrade());
		nuova.addActionListener(new ChoiceUpgrade());
		salva.setSelected(true);
		return p;
	}
	
	private JPanel createInputPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));
		p.add(new JLabel("Nome file: "));
		p.add(input);
		p.add(new JLabel("Capitale iniziale: "));
		p.add(capitaleIniziale);
		p.add(new JLabel("Capacità magazzino: "));
		p.add(capacitaMagazzino);
		return p;
	}
	
	private JPanel createButtonPanel() {
		JPanel p = new JPanel();
		esegui.addActionListener(new EseguiSalvaCaricaImpresa());
		p.add(esegui);
		return p;
	}
	
	private JPanel bottomPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(infoArea());
		p.add(buttonArea());
		return p;
	}
	
	private JPanel infoArea() {
		JPanel p = new JPanel();
		p.add(capitaleAzienda);
		p.add(prossimiProfitti);
		return p;
	}
	
	private JPanel buttonArea(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(createButton1());
		p.add(createButton2());
		return p;
	}
	
	private JPanel createButton1() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(i != null) {
					JFrame frame = new GUIAmministrativo(i);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				}
				
			}
			
		}
		amministrativo.addActionListener(new Click());
		p.add(amministrativo);
		return p;
	}
	
	private JPanel createButton2() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(i != null) {
					JFrame frame = new GUIOperativo(i);
					frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
					frame.setVisible(true);
				}
				
			}
			
		}
		operativo.addActionListener(new Click());
		p.add(operativo);
		return p;
	}
	
	
	private class EseguiSalvaCaricaImpresa implements ActionListener {

		
		public void actionPerformed(ActionEvent arg0) {
				if(salva.isSelected()) {
					SalvaLeggiImpresa salva = new SalvaLeggiImpresa(i);
					salva.SalvaImpresa(input.getText());
				}
				else if (carica.isSelected()) {
					SalvaLeggiImpresa salva = new SalvaLeggiImpresa(i);
					salva.CaricaImpresa(input.getText());
					i = salva.getImpresa();
				}
				else {
					try {
						Magazzino<MaterialeDaCostruzione> m = new Magazzino<MaterialeDaCostruzione>(Integer.parseInt(capacitaMagazzino.getText()));
						RisorseUmane ru = new RisorseUmane(Double.parseDouble(capitaleIniziale.getText()));
						RepartoOperativo ro = new RepartoOperativo();
						RisorseMateriali rm = new RisorseMateriali(ru.getCapitale(), m);
						i = new Impresa(rm, ru, ro);
					}
					catch (NumberFormatException e) {
						capitaleIniziale.setText("0");
						capacitaMagazzino.setText("0");
					}
					
				}
			
		}
		
	}
	
	private class ChoiceUpgrade implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if(nuova.isSelected()) {
				capacitaMagazzino.setEditable(true);
				capitaleIniziale.setEditable(true);
			}
			else {
				capacitaMagazzino.setEditable(false);
				capitaleIniziale.setEditable(false);
			}
			
		}
		
	}
	
}

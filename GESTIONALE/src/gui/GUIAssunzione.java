package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import amministrativo.RisorseUmane;
import personale.Dipendente;
import personale.Dirigente;
import personale.Impiegato;
import personale.Operaio;
import personale.Quadro;

public class GUIAssunzione extends JFrame {
	private RisorseUmane ru;
	
	private JComboBox<String> tipoComboBox;
	
	private JTextField inputNome;
	private JTextField inputCognome;
	private JTextField inputOpzionale;
	
	private JButton assumi;
	
	private JLabel opzionale;
	
	public GUIAssunzione(RisorseUmane ru) {
		this.ru = ru;
		tipoComboBox = new JComboBox<String>();
		String[] tipi = {"Dirigente", "Quadro", "Impiegato", "Operaio"};
		for(String s:tipi) {
			tipoComboBox.addItem(s);
		}
		
		inputNome = new JTextField(8);
		inputCognome = new JTextField(8);
		inputOpzionale = new JTextField(8);
		
		assumi = new JButton("Assumi");
		
		opzionale = new JLabel();
		add(mainPanel());
		setSize(300, 430);
	}
	
	private JPanel mainPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(createSelectionSection());
		p.add(createInputSection());
		p.add(createButtonSection());
		
		return p;
	}
	
	private JPanel createSelectionSection() {
		JPanel p = new JPanel();
		tipoComboBox.addActionListener(new TipiDipendenti());
		p.add(tipoComboBox);
		return p;
	}
	
	private JPanel createInputSection() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 2));
		p.add(new JLabel("Nome: "));
		p.add(inputNome);
		p.add(new JLabel("Cognome: "));
		p.add(inputCognome);
		p.add(opzionale);
		p.add(inputOpzionale);
		return p;
	}
	
	private JPanel createButtonSection() {
		JPanel p = new JPanel();
		assumi.addActionListener(new Assunzione());
		p.add(assumi);
		return p;
	}
	
	private class TipiDipendenti implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			if (tipoComboBox.getSelectedItem().equals("Dirigente")) {
				inputOpzionale.setEditable(true);
				opzionale.setText("Titolo di Studio");
			}
			else if(tipoComboBox.getSelectedItem().equals("Quadro")) {
				opzionale.setText("Numero giorni consulenza");
				inputOpzionale.setEditable(true);
			}
			else if(tipoComboBox.getSelectedItem().equals("Impiegato")) {
				opzionale.setText("Numero ore settimanali\n40 Full Time\n24 Part Time");
				inputOpzionale.setEditable(true);
			}
			else {
				inputOpzionale.setEditable(false);
				opzionale.setText("");
			}
		}
		
	}
	
	private class Assunzione implements ActionListener{


		public void actionPerformed(ActionEvent arg0) {
			if (tipoComboBox.getSelectedItem().equals("Dirigente")) {
				Dirigente d = new Dirigente(inputNome.getText(), inputCognome.getText(), inputOpzionale.getText());
				ru.assumi(d);
				dispose();
			}
			else if(tipoComboBox.getSelectedItem().equals("Quadro")) {
				try {
					Quadro q = new Quadro(inputNome.getText(), inputCognome.getText(), Integer.parseInt(inputOpzionale.getText()));
					ru.assumi(q);
					dispose();
				}
				catch (NumberFormatException e) {
					inputOpzionale.setText("0");
					opzionale.setText("Inserire Numero");
				}
				
			}
			else if(tipoComboBox.getSelectedItem().equals("Impiegato")) {
				try{
					Impiegato i = new Impiegato(inputNome.getText(), inputCognome.getText(), Integer.parseInt(inputOpzionale.getText()));
					ru.assumi(i);
					dispose();
				}
				catch (NumberFormatException e) {
					inputOpzionale.setText("0");
					opzionale.setText("Inserire Numero");
				}
				
			}
			else {
				Operaio o = new Operaio(inputNome.getText(), inputCognome.getText());
				ru.assumi(o);
				dispose();
			}
			
			
			
		}
		
	}
}

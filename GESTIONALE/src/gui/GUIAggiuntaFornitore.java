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
import esterno.Fornitore;
import materiale.Betoniera;
import materiale.MaterialeDaCostruzione;
import materiale.Trapano;
import materiale.TubiInnocenti;
import materiale.Viti;

public class GUIAggiuntaFornitore extends JFrame {
	private JTextArea vediCatalogo;
	private JTextField nomeFornitore;
	private JTextField codiceProdotto;
	private JTextField valoreProdotto;
	private JTextField pesoProdotto;
	private JTextField opzionale;
	
	private JComboBox<String> tipoProdotto;
	
	private JButton aggiungiACatalogo;
	private JButton crea;
	private JLabel testoOpzionale;
	
	private ArrayList<MaterialeDaCostruzione> catalogo;
	
	private RisorseMateriali rm;
	
	public GUIAggiuntaFornitore (RisorseMateriali rm) {
		this.rm = rm;
		String[] tipiProdotto = {"Viti", "Betoniera", "Trapano", "Tubi Innocenti"};
		
		vediCatalogo = new JTextArea();
		nomeFornitore = new JTextField(8);
		codiceProdotto = new JTextField(8);
		valoreProdotto = new JTextField(8);
		pesoProdotto = new JTextField(8);
		opzionale = new JTextField(8);
		
		tipoProdotto = new JComboBox<String>();
		for(String s:tipiProdotto) {
			tipoProdotto.addItem(s);
		}
		aggiungiACatalogo = new JButton("Aggiungi al Catalogo");
		crea = new JButton("Aggiungi Fornitore");
		
		testoOpzionale = new JLabel();
		
		catalogo = new ArrayList<MaterialeDaCostruzione>();
		
		add(mainPanel());
		setSize(850, 500);
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
		p.add(createSceltaProdotto());
		p.add(createInfoProdotto());
		p.add(createAggiungiProdotto());
		return p;
	}
	
	private JPanel createSceltaProdotto() {
		JPanel p = new JPanel();
		p.add(new JLabel("Scegli tipo prodotto:"));
		tipoProdotto.addActionListener(new SceltaProdotti());
		p.add(tipoProdotto);
		return p;
	}
	
	private JPanel createInfoProdotto() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		p.add(creaInput1());
		p.add(creaInput2());
		p.add(creaInput3());
		p.add(creaInput4());
		return p;
	}
	
	private JPanel creaInput1() {
		JPanel p = new JPanel();
		p.add(new JLabel("Codice Prodotto: "));
		p.add(codiceProdotto);
		return p;
	}
	private JPanel creaInput2() {
		JPanel p = new JPanel();
		p.add(new JLabel("Valore Prodotto: "));
		p.add(valoreProdotto);
		return p;
	}
	private JPanel creaInput3() {
		JPanel p = new JPanel();
		p.add(new JLabel("Peso Prodotto: "));
		p.add(pesoProdotto);
		return p;
	}
	private JPanel creaInput4() {
		JPanel p = new JPanel();
		p.add(testoOpzionale);
		p.add(opzionale);
		return p;
	}
	
	private JPanel createAggiungiProdotto() {
		JPanel p = new JPanel();
		aggiungiACatalogo.addActionListener(new AggiuntaProdotto());
		p.add(aggiungiACatalogo);
		return p;
	}
	
	private JPanel rightPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(createInfoCatalogo());
		p.add(createNomeFornitore());
		p.add(createCreaButton());
		return p;
	}
	
	private JPanel createInfoCatalogo() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2,1));
		p.add(new JLabel("Prodotti in catalogo"));
		JScrollPane pane = new JScrollPane(vediCatalogo);
		p.add(pane);
		return p;
	}
	
	private JPanel createNomeFornitore() {
		JPanel p = new JPanel();
		p.add(new JLabel("Nome Fornitore: "));
		p.add(nomeFornitore);
		return p;
	}
	
	private JPanel createCreaButton() {
		JPanel p = new JPanel();
		crea.addActionListener(new CreaFornitore());
		p.add(crea);
		return p;
		
	}
	
	private class SceltaProdotti implements ActionListener {

		
		public void actionPerformed(ActionEvent arg0) {
			if(tipoProdotto.getSelectedItem().equals("Tubi Innocenti")) {
				testoOpzionale.setText("Lunghezza: ");
				opzionale.setText("");
				codiceProdotto.setText("");
				valoreProdotto.setText("");
				pesoProdotto.setText("");
			}
			else if(tipoProdotto.getSelectedItem().equals("Viti")) {
				testoOpzionale.setText("Tipo: ");
				opzionale.setText("");
				codiceProdotto.setText("");
				valoreProdotto.setText("");
				pesoProdotto.setText("");
			}
			else if(tipoProdotto.getSelectedItem().equals("Trapano")) {
				testoOpzionale.setText("Consumo in watt: ");
				opzionale.setText("");
				codiceProdotto.setText("");
				valoreProdotto.setText("");
				pesoProdotto.setText("");
			}
			else {
				testoOpzionale.setText("Capienza: ");
				opzionale.setText("");
				codiceProdotto.setText("");
				valoreProdotto.setText("");
				pesoProdotto.setText("");
			}
			
		}
		
	}
	
	private class AggiuntaProdotto implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {
			boolean flag = true;
			if(tipoProdotto.getSelectedItem().equals("Tubi Innocenti")) {
				try {
					TubiInnocenti t = new TubiInnocenti(codiceProdotto.getText(), Double.parseDouble(valoreProdotto.getText()), Integer.parseInt(pesoProdotto.getText()), Integer.parseInt(opzionale.getText()));
					for(MaterialeDaCostruzione m:catalogo) {
						if (m.getCodiceProdotto().equals(t.getCodiceProdotto())) flag = false;
					}
					if (flag) {
						vediCatalogo.append(t.getClass().getSimpleName() + " -cod: " + t.getCodiceProdotto() + " -peso: " + t.getPeso() + " -valore: " + t.getValoreProdotto() + "\n");
						opzionale.setText("");
						codiceProdotto.setText("");
						valoreProdotto.setText("");
						pesoProdotto.setText("");
						catalogo.add(t);
					}
				}
				catch (NumberFormatException e) {
					valoreProdotto.setText("0");
					pesoProdotto.setText("0");
					opzionale.setText("0");
				}
			}
			else if(tipoProdotto.getSelectedItem().equals("Viti")) {
				
				try {
					Viti t = new Viti(codiceProdotto.getText(), Double.parseDouble(valoreProdotto.getText()), Integer.parseInt(pesoProdotto.getText()), opzionale.getText());
					for(MaterialeDaCostruzione m:catalogo) {
						if (m.getCodiceProdotto().equals(t.getCodiceProdotto())) flag = false;
					}
					if (flag) {
						vediCatalogo.append(t.getClass().getSimpleName() + " -cod: " + t.getCodiceProdotto() + " -peso: " + t.getPeso() + " -valore: " + t.getValoreProdotto() + "\n");
						opzionale.setText("");
						codiceProdotto.setText("");
						valoreProdotto.setText("");
						pesoProdotto.setText("");
						catalogo.add(t);
					}
				}
				catch (NumberFormatException e) {
					valoreProdotto.setText("0");
					pesoProdotto.setText("0");
				}
			}
			else if(tipoProdotto.getSelectedItem().equals("Trapano")) {
				try {
					Trapano t = new Trapano(codiceProdotto.getText(), Double.parseDouble(valoreProdotto.getText()), Integer.parseInt(pesoProdotto.getText()), Integer.parseInt(opzionale.getText()));
					for(MaterialeDaCostruzione m:catalogo) {
						if (m.getCodiceProdotto().equals(t.getCodiceProdotto())) flag = false;
					}
					if (flag) {
						vediCatalogo.append(t.getClass().getSimpleName() + " -cod: " + t.getCodiceProdotto() + " -peso: " + t.getPeso() + " -valore: " + t.getValoreProdotto() + "\n");
						opzionale.setText("");
						codiceProdotto.setText("");
						valoreProdotto.setText("");
						pesoProdotto.setText("");
						catalogo.add(t);
					}
				}
				catch (NumberFormatException e) {
					valoreProdotto.setText("0");
					pesoProdotto.setText("0");
					opzionale.setText("0");
				}
			}
			else {
				try {
					Betoniera t = new Betoniera(codiceProdotto.getText(), Double.parseDouble(valoreProdotto.getText()), Integer.parseInt(pesoProdotto.getText()), Integer.parseInt(opzionale.getText()));
					for(MaterialeDaCostruzione m:catalogo) {
						if (m.getCodiceProdotto().equals(t.getCodiceProdotto())) flag = false;
					}
					if (flag) {
						vediCatalogo.append(t.getClass().getSimpleName() + " -cod: " + t.getCodiceProdotto() + " -peso: " + t.getPeso() + " -valore: " + t.getValoreProdotto() + "\n");
						opzionale.setText("");
						codiceProdotto.setText("");
						valoreProdotto.setText("");
						pesoProdotto.setText("");
						catalogo.add(t);
					}
				}
				catch (NumberFormatException e) {
					valoreProdotto.setText("0");
					pesoProdotto.setText("0");
					opzionale.setText("0");
				}
			}
			
		}
		
	}
	
	private class CreaFornitore implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			Fornitore<MaterialeDaCostruzione> f = new Fornitore<MaterialeDaCostruzione>(nomeFornitore.getText());
			for(MaterialeDaCostruzione m:catalogo) {
				f.aggiungiProdotto(m);
			}
			rm.aggiungiFornitore(f);
			dispose();
			
		}
		
	}
}

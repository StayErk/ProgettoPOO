package gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import materiale.MaterialeDaCostruzione;
import operativo.RepartoOperativo;
import personale.Dipendente;
import utils.Estraibile;
import utils.Impresa;

public class GUIAmministrativo extends JFrame {

	private JCheckBox dirigenti;
	private JCheckBox operai;
	private JCheckBox quadri;
	private JCheckBox impiegati;
	
	private JRadioButton liberi;
	private JRadioButton impegniati;
	private JRadioButton pagati;
	private JRadioButton nonPagati;
	private JRadioButton rangePrezzo;
	private JRadioButton codiceArticolo;
	private JRadioButton rangePeso;

	
	private JButton report;
	private JButton refreshButton;
	private JButton gestioneDipendenti;
	private JButton acquistaMateriali;
	private JButton aggiungiFornitori;
	
	private JTextArea areaPersonale;
	private JTextArea areaMagazzino;
	
	private JTextField input1;
	private JTextField input2;
	
	private JLabel capitaleAzienda;
	private JLabel input1Label;
	
	private Impresa i;
	private RisorseUmane ru;
	private RisorseMateriali rm;
	private RepartoOperativo ro;
	
	Estraibile<Dipendente> criterioRU;
	Estraibile<MaterialeDaCostruzione> criterioRM;
	
	public GUIAmministrativo(Impresa i) {
		this.i = i;
		ro = i.getRepartoOperativo();
		rm = i.getRisorseMateriali();
		ru = i.getRisorseUmane();
		

		dirigenti = new JCheckBox("Dirigenti");
		operai = new JCheckBox("Operai");
		quadri = new JCheckBox("Quadri");
		impiegati = new JCheckBox("Impiegati");
		liberi = new JRadioButton("Liberi");
		impegniati = new JRadioButton("Impegniati");
		pagati = new JRadioButton("Pagati");
		nonPagati = new JRadioButton("Non Pagati");
		rangePeso = new JRadioButton("Range Peso");
		rangePrezzo = new JRadioButton("Range Prezzo");
		codiceArticolo = new JRadioButton("Codice Articolo");
		
		report = new JButton("Genera Report");
		refreshButton = new JButton("Ricarica");
		gestioneDipendenti = new JButton("Gestisci dipendenti");
		acquistaMateriali = new JButton("Acquista Materiali");
		aggiungiFornitori = new JButton("Aggiungi fornitori");
		
		areaPersonale = new JTextArea();
		areaPersonale.setEditable(false);
		
		areaMagazzino = new JTextArea();
		areaMagazzino.setEditable(false);
		
		input1 = new JTextField(8);
		input2 = new JTextField(8);
		
		capitaleAzienda = new JLabel("Capitale Azienda: " + rm.getCapitale());
		input1Label = new JLabel("Range iniziale: ");
		add(mainPanel());
		setSize(1185, 590);
		setTitle("Reparto Amministrativo");
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
		p.setBorder(new TitledBorder(new EtchedBorder(), "Risorse Umane"));
		p.add(infoAreaPersonale());
		p.add(buttonsAreaPersonale());
		return p;
	}
	
	private JPanel infoAreaPersonale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		JScrollPane pane = new JScrollPane(areaPersonale);
		p.add(new JLabel("Info personale: "));
		p.add(pane);
		return p;
	}
	
	private JPanel buttonsAreaPersonale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(createButtonsAreaPersonale());
		p.add(createCategoryAreaPersonale());
		return p;
	}
	
	private JPanel createButtonsAreaPersonale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(createButtonReport());
		p.add(createButtonGestioneDipendenti());
		return p;
	}
	
	private JPanel createButtonReport() {
		JPanel p = new JPanel();
		report.addActionListener(new ReportUmano());
		p.add(report);
		return p;
	}
	
	private JPanel createButtonGestioneDipendenti() {
		JPanel p = new JPanel();
		p.add(gestioneDipendenti);
		return p;
	}
	
	private JPanel createCategoryAreaPersonale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(categorieDipendenti());
		p.add(statoDipendenti());
		return p;
	}
	
	private JPanel categorieDipendenti() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Categorie Dipendenti"));
		p.setLayout(new GridLayout(2, 2));
		p.add(dirigenti);
		p.add(quadri);
		p.add(impiegati);
		p.add(operai);
		return p;
	}
	
	private JPanel statoDipendenti() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder(new EtchedBorder(), "Stato Dipendenti") );
		p.setLayout(new GridLayout(2, 2) );
		ButtonGroup g1 = new ButtonGroup();
		ButtonGroup g2 = new ButtonGroup();
		g1.add(liberi);
		g1.add(impegniati);
		g2.add(pagati);
		g2.add(nonPagati);
		
		p.add(liberi);
		p.add(impegniati);
		liberi.setSelected(true);
		
		p.add(pagati);
		p.add(nonPagati);
		nonPagati.setSelected(true);
		
		return p;
	}
	
	private JPanel rightPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.setBorder(new TitledBorder(new EtchedBorder(), "Risorse Materiali"));
		p.add(infoAreaMateriale());
		p.add(buttonsAreaMateriale());
		return p;
	}
	
	private JPanel infoAreaMateriale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(new JLabel("Info magazzino: "));
		JScrollPane pane = new JScrollPane(areaMagazzino);
		p.add(pane);
		return p;
	}
	
	private JPanel buttonsAreaMateriale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(createShoppingButtons());
		p.add(createReportButtons());
		return p;
	}
	
	private JPanel createShoppingButtons() {
		JPanel p = new JPanel();
		p.add(createAcquistaButton());
		p.add(createGestisciFornitoreButton());
		return p;
	}
	
	private JPanel createAcquistaButton() {
		JPanel p = new JPanel();
		p.add(acquistaMateriali);
		return p;
	}
	
	private JPanel createGestisciFornitoreButton() {
		JPanel p = new JPanel();
		p.add(aggiungiFornitori);
		return p;
	}
	
	private JPanel createReportButtons() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(typeSelection());
		p.add(inputSection());
		p.add(createRefreshButton());
		return p;
	}
	
	private JPanel typeSelection() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.setBorder(new TitledBorder(new EtchedBorder(), "Criterio di ricerca"));
		ButtonGroup group = new ButtonGroup();
		group.add(codiceArticolo);
		group.add(rangePeso);
		group.add(rangePrezzo);
		p.add(codiceArticolo);
		p.add(rangePeso);
		p.add(rangePrezzo);
		rangePrezzo.setSelected(true);
		return p;
		
	}
	
	private JPanel inputSection() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		p.add(createInput1());
		p.add(createInput2());
		return p;
	}
	
	private JPanel createInput1() {
		JPanel p = new JPanel();
		p.add(input1Label);
		p.add(input1);
		return p;
	}
	
	private JPanel createInput2() {
		JPanel p = new JPanel();
		p.add(new JLabel("Range finale: "));
		p.add(input2);
		return p;
	}
	
	private JPanel createRefreshButton() {
		JPanel p = new JPanel();
		p.add(refreshButton);
		return p;
	}
	
	private class ReportMateriale implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class ReportUmano implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (pagati.isSelected() && impegniati.isSelected()) {
				if(dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Dirigente") && p.getStato() && p.getStatoPagamento();
				}
				else if (quadri.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Quadro") && p.getStato() && p.getStatoPagamento();
				}
				else if (impiegati.isSelected() && !dirigenti.isSelected() && !quadri.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Impiegato") && p.getStato() && p.getStatoPagamento();
				}
				else if (operai.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !quadri.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Operaio") && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
			}
			else if (pagati.isSelected() && liberi.isSelected()) {
				if(dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Dirigente") && !p.getStato() && p.getStatoPagamento();
				}
				else if (quadri.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Quadro") && !p.getStato() && p.getStatoPagamento();
				}
				else if (impiegati.isSelected() && !dirigenti.isSelected() && !quadri.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Impiegato") && !p.getStato() && p.getStatoPagamento();
				}
				else if (operai.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !quadri.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Operaio") && !p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro")) && !p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato")) && !p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && !p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || !p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato") || !p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || !p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || !p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
				}
			}
			else if (nonPagati.isSelected() && impegniati.isSelected()) {
				if(dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Dirigente") && p.getStato() && !p.getStatoPagamento();
				}
				else if (quadri.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Quadro") && p.getStato() && !p.getStatoPagamento();
				}
				else if (impiegati.isSelected() && !dirigenti.isSelected() && !quadri.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Impiegato") && p.getStato() && !p.getStatoPagamento();
				}
				else if (operai.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !quadri.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Operaio") && p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && !p.getStatoPagamento();
				}
			}
			else if (nonPagati.isSelected() && liberi.isSelected()) {
				if(dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Dirigente") && !p.getStato() && !p.getStatoPagamento();
				}
				else if (quadri.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Quadro") && !p.getStato() && !p.getStatoPagamento();
				}
				else if (impiegati.isSelected() && !dirigenti.isSelected() && !quadri.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Impiegato") && !p.getStato() && !p.getStatoPagamento();
				}
				else if (operai.isSelected() && !dirigenti.isSelected() && !impiegati.isSelected() && !quadri.isSelected()) {
					criterioRU = (p)->p.getClass().getSimpleName().equals("Operaio") && !p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && !operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && !quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (!dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
				}
				else if (dirigenti.isSelected() && quadri.isSelected() && impiegati.isSelected() && operai.isSelected()) {
					criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Impiegato") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
				}
			}
			
			ArrayList<Dipendente> selezionati = ru.scegliDipendenti(criterioRU);
			areaPersonale.setText("");
			for(Dipendente d:selezionati) {
				areaPersonale.append(d.getClass().getSimpleName() + ": " + d.getCognome() + " " + d.getNome() + " con matricola: " + d.getMatricolaDipendente() + " impegnato: " + d.getStato() + " pagato: " + d.getStatoPagamento() +"\n");
			}
			
		}
		
	}
}

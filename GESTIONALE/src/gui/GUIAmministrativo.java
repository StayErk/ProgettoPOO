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
import personale.Pagabile;
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
	private JRadioButton perNome;
	private JRadioButton nonPerNome;
	private JRadioButton tutti;

	
	private JButton report;
	private JButton refreshButton;
	private JButton assumi;
	private JButton paga;
	private JButton resetPagamento;
	private JButton licenzia;
	private JButton acquistaMateriali;
	private JButton aggiungiFornitori;
	
	private JTextArea areaPersonale;
	private JTextArea areaMagazzino;
	
	private JTextField input1;
	private JTextField input2;
	private JTextField input3;
	
	private JLabel capitaleAzienda;
	private JLabel input1Label;
	
	private Impresa i;
	private RisorseUmane<Dipendente> ru;
	private RisorseMateriali rm;
	private RepartoOperativo ro;
	
	Estraibile<Dipendente> criterioRU;
	Estraibile<MaterialeDaCostruzione> criterioRM;
	ArrayList<Dipendente> selezionatiDipendenti;
	
	public GUIAmministrativo(Impresa i) {
		this.i = i;
		ro = i.getRepartoOperativo();
		rm = i.getRisorseMateriali();
		ru = i.getRisorseUmane();
		selezionatiDipendenti = new ArrayList<Dipendente>();
		

		dirigenti = new JCheckBox("Dirigenti");
		operai = new JCheckBox("Operai");
		quadri = new JCheckBox("Quadri");
		impiegati = new JCheckBox("Impiegati");
		liberi = new JRadioButton("Liberi");
		impegniati = new JRadioButton("Impegnati");
		pagati = new JRadioButton("Pagati");
		nonPagati = new JRadioButton("Non Pagati");
		rangePeso = new JRadioButton("Range Peso");
		rangePrezzo = new JRadioButton("Range Prezzo");
		codiceArticolo = new JRadioButton("Codice Articolo");
		perNome = new JRadioButton("ricerca per nome");
		nonPerNome = new JRadioButton("ricerca generica");
		tutti = new JRadioButton("tutti");
		
		report = new JButton("Genera Report");
		refreshButton = new JButton("Ricarica");
		assumi = new JButton("Assumi dipendenti");
		licenzia = new JButton("Licenzia Dipendenti");
		paga = new JButton("Paga Dipendenti");
		resetPagamento = new JButton("Reset stato pagamento");
		acquistaMateriali = new JButton("Acquista Materiali");
		aggiungiFornitori = new JButton("Aggiungi fornitori");
		
		areaPersonale = new JTextArea();
		areaPersonale.setEditable(false);
		
		areaMagazzino = new JTextArea();
		areaMagazzino.setEditable(false);
		
		input1 = new JTextField(8);
		input2 = new JTextField(8);
		input3 = new JTextField(8);
		input3.setEditable(false);
		
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
		p.setLayout(new GridLayout(5, 1));
		p.add(createButtonAssumi());
		p.add(createButtonLicenzia());
		p.add(createButtonPaga());
		p.add(createButtonResetPagamento());
		p.add(createButtonReport());
		return p;
	}
	
	private JPanel createButtonReport() {
		JPanel p = new JPanel();
		report.addActionListener(new GeneraReportUmano());
		p.add(report);
		return p;
	}
	
	private JPanel createButtonAssumi() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				JFrame assumi = new GUIAssunzione(ru);
				assumi.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				assumi.setVisible(true);
			}
			
		}
		assumi.addActionListener(new Click());
		p.add(assumi);
		return p;
	}
	
	private JPanel createButtonLicenzia() {
		JPanel p = new JPanel();
		p.add(licenzia);
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				for(Dipendente d : selezionatiDipendenti) {
					ru.licenzia(d.getMatricolaDipendente());
				}
			}
		}
		licenzia.addActionListener(new Click());
		return p;
	}
	
	private JPanel createButtonPaga() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				for(Dipendente d : selezionatiDipendenti) {
					System.out.println("PAGA STIPENDIO capitale: " + ru.getCapitale());
					double soldiPrima = ru.getCapitale();
					ru.pagaDipendenti(d);
					System.out.println("PAGA STIPENDIO capitale: " + ru.getCapitale());
					areaPersonale.append("Pagati al dipendente: "+ d.getCognome() + " " + d.getNome() + ": " + (soldiPrima - ru.getCapitale()) + "\n");
				}
				
			}
			
		}
		paga.addActionListener(new Click());
		p.add(paga);
		return p;
	}
	
	private JPanel createButtonResetPagamento() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				ru.nuovoMeseFiscale(criterioRU);
			}
			
		}
		resetPagamento.addActionListener(new Click());
		p.add(resetPagamento);
		return p;
	}
	
	
	private JPanel createCategoryAreaPersonale() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(3, 1));
		p.add(categorieDipendenti());
		p.add(statoDipendenti());
		p.add(ricercaPerNome());
		return p;
	}
	
	private JPanel ricercaPerNome() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(1, 2));
		p.add(createSwitch());
		p.add(createInput3());
		return p;
	}
	
	private JPanel createSwitch(){
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(2, 1));
		ButtonGroup g = new ButtonGroup();
		g.add(perNome);
		g.add(nonPerNome);
		
		
		class Click implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				if(perNome.isSelected()) {
					dirigenti.setEnabled(false);
					operai.setEnabled(false);
					impiegati.setEnabled(false);
					quadri.setEnabled(false);
					impegniati.setEnabled(false);
					liberi.setEnabled(false);
					pagati.setEnabled(false);
					nonPagati.setEnabled(false);
					input3.setEditable(true);
				}
				else {
					dirigenti.setEnabled(true);
					operai.setEnabled(true);
					impiegati.setEnabled(true);
					quadri.setEnabled(true);
					impegniati.setEnabled(true);
					liberi.setEnabled(true);
					pagati.setEnabled(true);
					nonPagati.setEnabled(true);
					input3.setEditable(false);
				}
				
			}
			
		}
		perNome.addActionListener(new Click());
		nonPerNome.addActionListener(new Click());
		nonPerNome.setSelected(true);
		p.add(perNome);
		p.add(nonPerNome);
		
		return p;
	}
	
	private JPanel createInput3() {
		JPanel p = new JPanel();
		p.add(input3);
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
		dirigenti.addActionListener(new ReportUmano());
		quadri.addActionListener(new ReportUmano());
		impiegati.addActionListener(new ReportUmano());
		operai.addActionListener(new ReportUmano());
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
		
		liberi.addActionListener(new ReportUmano());
		impegniati.addActionListener(new ReportUmano());
		pagati.addActionListener(new ReportUmano());
		nonPagati.addActionListener(new ReportUmano());
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
		class Click implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				JFrame acquista = new GUIAcquisto(rm);
				acquista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				acquista.setVisible(true);
			}
			
		}
		acquistaMateriali.addActionListener(new Click());
		p.add(acquistaMateriali);
		return p;
	}
	
	private JPanel createGestisciFornitoreButton() {
		JPanel p = new JPanel();
		class Click implements ActionListener{

			
			public void actionPerformed(ActionEvent arg0) {
				JFrame aggiuntaFornitore = new GUIAggiuntaFornitore(rm);
				aggiuntaFornitore.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
				aggiuntaFornitore.setVisible(true);
			}
			
		}
		aggiungiFornitori.addActionListener(new Click());
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
		p.setLayout(new GridLayout(4, 1));
		p.setBorder(new TitledBorder(new EtchedBorder(), "Criterio di ricerca"));
		ButtonGroup group = new ButtonGroup();
		group.add(codiceArticolo);
		group.add(rangePeso);
		group.add(rangePrezzo);
		group.add(tutti);
		codiceArticolo.addActionListener(new SceltaTipoMateriale());
		rangePeso.addActionListener(new SceltaTipoMateriale());
		rangePrezzo.addActionListener(new SceltaTipoMateriale());
		tutti.addActionListener(new SceltaTipoMateriale());
		p.add(tutti);
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
		refreshButton.addActionListener(new ReportMateriale());
		p.add(refreshButton);
		return p;
	}
	
	private class SceltaTipoMateriale implements ActionListener {


		public void actionPerformed(ActionEvent arg0) {
			if (codiceArticolo.isSelected()) {
				input2.setEditable(false);
				input1Label.setText("Inserisci codice articolo o parte di esso: ");
			}
			else if (tutti.isSelected()) {
				input1.setEditable(false);
				input2.setEditable(false);
			}
			else {
				input2.setEditable(true);
				input1.setEditable(true);
				input1Label.setText("Range iniziale: ");
			}
			
		}
		
	}
	
	private class ReportMateriale implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			if(codiceArticolo.isSelected()) {
				criterioRM = (p)->p.getCodiceProdotto().contains(input1.getText());
			}
			else if(rangePeso.isSelected()) {
				
				try {
					criterioRM = (p)->p.getPeso()>=Integer.parseInt(input1.getText()) && p.getPeso()<=Integer.parseInt(input2.getText());
				}
				catch (NumberFormatException e) {
					input1.setText("0");
					input2.setText("0");
				}
			}
			else if(tutti.isSelected()) {
				criterioRM = (p)->true;
			}
			else {
				try {
					criterioRM = (p)->p.getValoreProdotto()>=Integer.parseInt(input1.getText()) && p.getValoreProdotto()<=Integer.parseInt(input2.getText());
				}
				catch (NumberFormatException e) {
					input1.setText("0");
					input2.setText("0");
				}
			}
			
			ArrayList<MaterialeDaCostruzione> scelti = rm.scegliMateriale(criterioRM);
			areaMagazzino.setText("Carico in magazzino: " + rm.getMagazzino().getCaricoAttuale() + " su: " +rm.getMagazzino().getCapacitaMax() + "\n");
			for(MaterialeDaCostruzione m:scelti) {
				areaMagazzino.append("Prodotto: " + m.getClass().getSimpleName() + " con codice: " + m.getCodiceProdotto() + " valore: " + m.getValoreProdotto() + " peso: " +m.getPeso() + "\n");
			}
			
		}
		
	}
	
	private class ReportUmano implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if(nonPerNome.isSelected()) {
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
					else if (dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
						criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Operaio")) && p.getStato() && p.getStatoPagamento();
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
					else if (dirigenti.isSelected() && quadri.isSelected() && !impiegati.isSelected() && operai.isSelected()) {
						criterioRU = (p)->(p.getClass().getSimpleName().equals("Dirigente") || p.getClass().getSimpleName().equals("Quadro") || p.getClass().getSimpleName().equals("Operaio")) && !p.getStato() && !p.getStatoPagamento();
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
			}
				
			
			
		}
		
	}
	
	private class GeneraReportUmano implements ActionListener{

		public void actionPerformed(ActionEvent arg0) {
			if (perNome.isSelected()) {
				if(input3.getText().equals("")) criterioRU = (p) -> true;
				criterioRU = (p) -> p.getNome().contains(input3.getText()) || p.getCognome().contains(input3.getText());
				try{
					selezionatiDipendenti = ru.scegliDipendenti(criterioRU);
					areaPersonale.setText("Assunti in azienda: " + ru.getPersonale().size() + ", corrispondenti al report: " + selezionatiDipendenti.size()+"\n\n");
					for(Dipendente d : selezionatiDipendenti) {
						areaPersonale.append(d.getClass().getSimpleName() + " " + d.getCognome() + " " + d.getNome() + " impegnato: " + d.getStato() + " pagato: " + d.getStatoPagamento()+"\n");
					}
				}
				catch (NullPointerException e) {
					areaPersonale.setText("Assunti in azienda: " + ru.getPersonale().size() + ", corrispondenti al report: " + selezionatiDipendenti.size() +"\n" + "Il dipendente cercato non esiste. Prova ad assumerlo");
				}
			}
			else {
				try {
					selezionatiDipendenti = ru.scegliDipendenti(criterioRU);
					areaPersonale.setText("Assunti in azienda: " + ru.getPersonale().size() + ", corrispondenti al report: " + selezionatiDipendenti.size()+"\n\n");
					for(Dipendente d : selezionatiDipendenti) {
						areaPersonale.append(d.getClass().getSimpleName() + " " + d.getCognome() + " " + d.getNome() + " impegnato: " + d.getStato() + " pagato: " + d.getStatoPagamento()+"\n");
					}
				}
				catch (NullPointerException e) {
					areaPersonale.setText("Assunti in azienda: " + ru.getPersonale().size() + ", corrispondenti al report: " + selezionatiDipendenti.size() +"\n" + "Ehy perché non provi a selezionare una categoria?");
				}
			}
			
			
		}
		
	}
}

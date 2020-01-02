package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import amministrativo.RepartoAmministrativo;
import amministrativo.RisorseMateriali;
import amministrativo.RisorseUmane;
import materiale.Magazzino;
import materiale.MaterialeDaCostruzione;
import operativo.RepartoOperativo;
import utils.Impresa;

public class InitFrame extends JFrame {
	JButton bottoneReport;
	JButton bottonePersonale;
	JButton bottoneMagazzino;
	JButton bottoneCantieri;
	
	JLabel labelSaldo;
	
	Impresa i;
	
	
	public InitFrame() {
		bottoneReport = new JButton("Genera Report");
		bottonePersonale = new JButton("Gestisci Personale");
		bottoneMagazzino = new JButton("Gestisci Magazzino");
		bottoneCantieri = new JButton("Gestisci Cantieri");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createFileMenu());
		Magazzino<MaterialeDaCostruzione> magazzino = new Magazzino<MaterialeDaCostruzione>(800);
		RisorseMateriali rm = new RisorseMateriali(50000, magazzino);
		RisorseUmane ru = new RisorseUmane(rm.getCapitale());
		RepartoOperativo ro = new RepartoOperativo();
		i = new Impresa(rm, ru, ro);
		
		setVisible(true);
		setTitle("Melenzanetti");
		setSize(950, 550);
		
	}
	
	private JMenu createFileMenu() {
		JMenu file = new JMenu("File");
		file.add(createOpenFileItem());
		file.add(createSaveFileItem());
		file.add(createSaveAsFileItem());
		file.add(createExitItem());
		return file;
	}
	
	private JMenuItem createExitItem() {
		JMenuItem item = new JMenuItem("Exit");
		class MenuItemListner implements ActionListener
		{
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		}
		ActionListener listener = new MenuItemListner();
		item.addActionListener(listener);
		return item;
	}
	
	private JMenuItem createOpenFileItem() {
		JMenuItem item = new JMenuItem("Open");
		class MenuItemListner implements ActionListener{

			public void actionPerformed(ActionEvent arg0) {
				
			}
			
		}
	}
}

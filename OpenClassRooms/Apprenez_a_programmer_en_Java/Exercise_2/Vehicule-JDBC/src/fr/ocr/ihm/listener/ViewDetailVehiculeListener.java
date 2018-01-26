package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.ResultSet2JTableConverter;
import voiture.Vehicule;

public class ViewDetailVehiculeListener extends ButtonListener {
	
	public ViewDetailVehiculeListener() {		
	}
	
	@SuppressWarnings("serial")
	private class ZDialog extends JDialog {
		
		
		public ZDialog(JFrame parent, String title, boolean modal){			
			super(parent, title, modal);
			this.setSize(750, 360);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.initComponent();
		}
		
		private void initComponent() {
			
			Vehicule aCar = (Vehicule) DAOFactory.getVehiculeDAO().find(ResultSet2JTableConverter.dbID);
			
			// Car			
			JPanel vehicule = new JPanel();
			vehicule.setBackground(Color.white);
			vehicule.setPreferredSize(new Dimension(220, 60));
			JTextField nomDuVehicule = new JTextField();
			nomDuVehicule.setPreferredSize(new Dimension(150, 25));
			nomDuVehicule.setText(aCar.getNom().toString().trim());
			nomDuVehicule.setEditable(false);			
			vehicule.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));

			vehicule.add(nomDuVehicule);
			
			// Marque
			JPanel marqueDuVehicule = new JPanel();
			marqueDuVehicule.setBackground(Color.white);
			marqueDuVehicule.setPreferredSize(new Dimension(220, 60));
			JTextField marque = new JTextField();
			marque.setPreferredSize(new Dimension(150, 25));
			marque.setText(aCar.getMarque().getNom().toString().trim());
			marque.setEditable(false);			
			marqueDuVehicule.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
			marqueDuVehicule.add(marque);
			
			// Marque
			JPanel typeDeMoteur = new JPanel();
			typeDeMoteur.setBackground(Color.white);
			typeDeMoteur.setPreferredSize(new Dimension(440, 60));
			JTextField moteurType = new JTextField();
			moteurType.setPreferredSize(new Dimension(350, 25));
			moteurType.setText(aCar.getMoteur().getType().getNom().toString().trim()+" "+aCar.getMoteur().getCylindre().toString().trim());
			moteurType.setEditable(false);
			typeDeMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
			typeDeMoteur.add(moteurType);
			
			// Price without options
			JPanel voitureNetPrix = new JPanel();
			voitureNetPrix.setBackground(Color.white);
			voitureNetPrix.setPreferredSize(new Dimension(330, 60));
			JTextField prixSanOption = new JTextField();
			prixSanOption.setPreferredSize(new Dimension(200, 25));
			prixSanOption.setText("Prix sans option: "+aCar.getPrix().toString()+" €");
			prixSanOption.setEditable(false);
			voitureNetPrix.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));
			voitureNetPrix.add(prixSanOption);
			
			// list of options
			JPanel optionsPrix = new JPanel();
			optionsPrix.setBackground(Color.white);
			optionsPrix.setPreferredSize(new Dimension(720, 60));
			JTextField options = new JTextField();
			options.setPreferredSize(new Dimension(705, 25));
			options.setText(aCar.getDetailedOptionsList());
			options.setEditable(false);
			optionsPrix.setBorder(BorderFactory.createTitledBorder("Options Dinsponibles"));
			optionsPrix.add(options);
			
			// Price total
			JPanel voiturePrixTotale = new JPanel();
			voiturePrixTotale.setBackground(Color.white);
			voiturePrixTotale.setPreferredSize(new Dimension(400, 60));
			JTextField prixTotal = new JTextField();
			prixTotal.setPreferredSize(new Dimension(200, 25));
			prixTotal.setText(aCar.getPrixTotal().toString()+" €");
			prixTotal.setEditable(false);
			voiturePrixTotale.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));
			voiturePrixTotale.setBackground(Color.green);
			voiturePrixTotale.add(prixTotal);
			
			JPanel content = new JPanel();
			content.setBackground(Color.white);
			content.add(vehicule);
			content.add(marqueDuVehicule);
			content.add(typeDeMoteur);
			content.add(voitureNetPrix);
			content.add(optionsPrix);
			content.add(voiturePrixTotale);
			
			this.getContentPane().add(content, BorderLayout.CENTER);
		}
	}

	public void actionPerformed(ActionEvent e) {
		ZDialog zd = new ZDialog(null, "Détail d\'un véhicule", true);
		zd.setVisible(true);
	}
}

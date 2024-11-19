package IHM;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import DAO.IDAOImp;
import entite.Livre;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GestionLivre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_isbn;
	private JTextField txt_titre;
	private JTextField txt_nbexmp;
	private JTextField txt_auteur;
	private JTextField txt_isbn1;
	private JTextField txt_auteur1;
	private JTable booklistes;
	JComboBox choix_domaine;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestionLivre frame = new GestionLivre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	
	String [][] tab;
	private JTextField date_production;
	public void actualiser () {
		IDAOImp l = new IDAOImp();
		int i = 0;
		List<Livre> ll = l.select();
		tab = new String[ll.size()+1][6];
		for (Livre li : ll) {
			tab [i][0] = ""+li.getIsbn();
			tab [i][1] = ""+li.getTitre();
			tab [i][2] = ""+li.getDate_produ();
			tab [i][3] = ""+li.getNb_exemplaire();
			tab [i][4] = ""+li.getNom_auteur();
			tab [i][5] = ""+li.getDomaine();
			i++;
			
			String [] entet = new String [] {"isbn","titre","date_produ","Nb_exemplaire","nom_auteur","domaine"};
			booklistes.setModel(new javax.swing.table.DefaultTableModel(tab,entet));
		}
	}

	/**
	 * Create the frame.
	 */
	public GestionLivre() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ISBN");
		lblNewLabel.setBounds(38, 16, 61, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Titre");
		lblNewLabel_1.setBounds(38, 44, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Date de production");
		lblNewLabel_1_1.setBounds(38, 72, 128, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nbr exemplaire");
		lblNewLabel_1_1_1.setBounds(38, 100, 128, 16);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Nom auteur");
		lblNewLabel_1_1_1_1.setBounds(38, 128, 128, 16);
		contentPane.add(lblNewLabel_1_1_1_1);
		
		 choix_domaine = new JComboBox();
		choix_domaine.setModel(new DefaultComboBoxModel(new String[] {"Informatique ", "Industriel", "Comptabilité", "Mathématique", "Chimie", "Roman", "Management"}));
		choix_domaine.setBounds(195, 152, 201, 27);
		contentPane.add(choix_domaine);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Domaine");
		lblNewLabel_1_1_1_1_1.setBounds(38, 156, 128, 16);
		contentPane.add(lblNewLabel_1_1_1_1_1);
		
		txt_isbn = new JTextField();
		txt_isbn.setToolTipText("\n");
		txt_isbn.setBounds(193, 11, 203, 26);
		contentPane.add(txt_isbn);
		txt_isbn.setColumns(10);
		
		txt_titre = new JTextField();
		txt_titre.setColumns(10);
		txt_titre.setBounds(195, 39, 201, 26);
		contentPane.add(txt_titre);
		
		txt_nbexmp = new JTextField();
		txt_nbexmp.setColumns(10);
		txt_nbexmp.setBounds(193, 95, 203, 26);
		contentPane.add(txt_nbexmp);
		
		txt_auteur = new JTextField();
		txt_auteur.setColumns(10);
		txt_auteur.setBounds(195, 123, 201, 26);
		contentPane.add(txt_auteur);
		
		JButton btnNewButton = new JButton("Enregistrer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isbn = Integer.parseInt(txt_isbn.getText());
				int nbrExmp = Integer.parseInt(txt_nbexmp.getText());
				String titre = txt_titre.getText();
				String dateProduction = date_production.getText();
				String auteur = txt_auteur.getText();
				String domaine = choix_domaine.getSelectedItem().toString();
				IDAOImp dao = new IDAOImp();
				Livre l = new Livre (isbn,nbrExmp,titre,dateProduction,auteur,domaine);
				dao.insert(l);
			}
		});
		btnNewButton.setBounds(470, 11, 175, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Modifier");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isbn = Integer.parseInt(txt_isbn.getText());
				int nbrExmp = Integer.parseInt(txt_nbexmp.getText());
				String titre = txt_titre.getText();
				String dateParution = date_production.getText();
				String auteur = txt_auteur.getText();
				String domaine = choix_domaine.getSelectedItem().toString();
				IDAOImp dao = new IDAOImp();
				Livre l = new Livre (isbn,nbrExmp,titre,dateParution,auteur,domaine);
				dao.update(l);
				JOptionPane.showConfirmDialog(null, "Modifie avec succes", "modification", JOptionPane.INFORMATION_MESSAGE);
				booklistes.removeAll();
				actualiser();
				vider();
			}
		});
		btnNewButton_1.setBounds(470, 39, 175, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Supprimer");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isbn = Integer.parseInt(txt_isbn.getText());
				int test = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer", "supprimer", JOptionPane.YES_NO_OPTION);
				IDAOImp dao = new IDAOImp();
				Livre l = new Livre();
				if (test == 0) {
					dao.delete(isbn);
					JOptionPane.showConfirmDialog(null, "Supprime avec succes", "suppression", JOptionPane.INFORMATION_MESSAGE);
					booklistes.removeAll();
					actualiser();
					vider();
				}
			}
		});
		
		btnNewButton_1_1.setBounds(470, 67, 175, 29);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_2 = new JButton("Liste des exemplaires ");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
				vider();
			}
		});
		btnNewButton_2.setBounds(470, 115, 175, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Annuler");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vider();
			}
		});
		btnNewButton_2_1.setBounds(470, 140, 175, 29);
		contentPane.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_2 = new JLabel("Recherche des livres");
		lblNewLabel_2.setBounds(132, 197, 135, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ISBN");
		lblNewLabel_3.setBounds(38, 229, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		txt_isbn1 = new JTextField();
		txt_isbn1.setColumns(10);
		txt_isbn1.setBounds(195, 224, 201, 26);
		contentPane.add(txt_isbn1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Domaine");
		lblNewLabel_1_1_1_1_1_1.setBounds(38, 257, 128, 16);
		contentPane.add(lblNewLabel_1_1_1_1_1_1);
		
		JComboBox cbox_domaine1 = new JComboBox();
		cbox_domaine1.setModel(new DefaultComboBoxModel(new String[] {"Informatique ", "Industriel", "Comptabilité", "Mathématique", "Chimie", "Roman", "Management"}));
		cbox_domaine1.setBounds(195, 253, 201, 27);
		contentPane.add(cbox_domaine1);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Nom auteur");
		lblNewLabel_1_1_1_1_2.setBounds(38, 285, 128, 16);
		contentPane.add(lblNewLabel_1_1_1_1_2);
		
		txt_auteur1 = new JTextField();
		txt_auteur1.setColumns(10);
		txt_auteur1.setBounds(195, 280, 201, 26);
		contentPane.add(txt_auteur1);
		
		JButton btnNewButton_2_2 = new JButton("Afficher tous les livres");
		btnNewButton_2_2.setBounds(470, 216, 175, 29);
		contentPane.add(btnNewButton_2_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("Liste des livres");
		lblNewLabel_2_1.setBounds(132, 328, 135, 16);
		contentPane.add(lblNewLabel_2_1);
		
		booklistes = new JTable();
		booklistes.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int selectedRow = booklistes.getSelectedRow();
		        if (selectedRow != -1) {
		            String isbn = booklistes.getValueAt(selectedRow, 0).toString();
		            String titre = booklistes.getValueAt(selectedRow, 1).toString();
		            String date = booklistes.getValueAt(selectedRow, 2).toString();
		            String nbexmp = booklistes.getValueAt(selectedRow, 3).toString();
		            String auteur = booklistes.getValueAt(selectedRow, 4).toString();
		            String domaine = booklistes.getValueAt(selectedRow, 5).toString();
		            // Remplir les champs de texte avec les valeurs récupérées
		            txt_isbn.setText(isbn);
		            txt_titre.setText(titre);
		            date_production.setText(date);
		            txt_nbexmp.setText(nbexmp);
		            txt_auteur.setText(auteur);
		            choix_domaine.setSelectedItem(domaine);
		        }
		    }

		});

		booklistes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		booklistes.setBounds(38, 361, 627, 118);
		contentPane.add(booklistes);
		
		JButton btnNewButton_3 = new JButton("rechercher");
		btnNewButton_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String isbnSearch = txt_isbn1.getText().trim();
		        String domaineSearch = (String) cbox_domaine1.getSelectedItem();
		        String auteurSearch = txt_auteur1.getText().trim();

		        IDAOImp dao = new IDAOImp();
		        List<Livre> allBooks = dao.select();
		        List<Livre> filteredBooks = new ArrayList<>();

		        // Filtrer les livres en fonction des critères de recherche
		        for (Livre livre : allBooks) {
		            boolean match = true;

		            if (!isbnSearch.isEmpty() && !String.valueOf(livre.getIsbn()).contains(isbnSearch)) {
		                match = false;
		            }
		            if (!domaineSearch.isEmpty() && !livre.getDomaine().equalsIgnoreCase(domaineSearch)) {
		                match = false;
		            }
		            if (!auteurSearch.isEmpty() && !livre.getNom_auteur().toLowerCase().contains(auteurSearch.toLowerCase())) {
		                match = false;
		            }

		            if (match) {
		                filteredBooks.add(livre);
		            }
		        }

		        // Afficher un message si aucun livre n'est trouvé
		        if (filteredBooks.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Aucun livre trouvé avec les critères de recherche", "Recherche", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            // Afficher les résultats filtrés dans la JTable
		            String[][] tab = new String[filteredBooks.size()][6];
		            int i = 0;
		            for (Livre livre : filteredBooks) {
		                tab[i][0] = String.valueOf(livre.getIsbn());
		                tab[i][1] = livre.getTitre();
		                tab[i][2] = livre.getDate_produ();
		                tab[i][3] = String.valueOf(livre.getNb_exemplaire());
		                tab[i][4] = livre.getNom_auteur();
		                tab[i][5] = livre.getDomaine();
		                i++;
		            }
		            String[] entet = {"isbn", "titre", "date_produ", "Nb_exemplaire", "nom_auteur", "domaine"};
		            booklistes.setModel(new javax.swing.table.DefaultTableModel(tab, entet));
		        }
		    }
		});


		btnNewButton_3.setBounds(470, 246, 175, 27);
		contentPane.add(btnNewButton_3);
		
		date_production = new JTextField();
		date_production.setColumns(10);
		date_production.setBounds(195, 67, 203, 26);
		contentPane.add(date_production);
	}
	
	public void vider() {
		txt_isbn.setText("");
		txt_titre.setText("");
		txt_nbexmp.setText("");
		txt_auteur.setText("");
		date_production.setText("");
		choix_domaine.getSelectedItem();
	}
}

package entite;

public class Livre {
	private int isbn , Nb_exemplaire;
	private String titre , date_production , nom_auteur , domaine;
	
	public Livre() {}

	public Livre(int isbn, int nb_exemplaire, String titre, String date_production, String nom_auteur, String domaine) {
		this.isbn = isbn;
		this.Nb_exemplaire = nb_exemplaire;
		this.titre = titre;
		this.date_production = date_production;
		this.nom_auteur = nom_auteur;
		this.domaine = domaine;
	}
	
	public int getIsbn() { return isbn; }
	public void setIsbn(int isbn) { this.isbn = isbn; }

	public int getNb_exemplaire() { return Nb_exemplaire; }
	public void setNb_exemplaire(int nb_exemplaire) {this.Nb_exemplaire = nb_exemplaire; }

	public String getTitre() { return titre; }
	public void setTitre(String titre) { this.titre = titre; }

	public String getDate_produ() { return date_production;}
	public void setDate_produ(String date_production) { this.date_production = date_production; }

	public String getNom_auteur() { return nom_auteur; }
	public void setNom_auteur(String nom_auteur) { this.nom_auteur = nom_auteur; }

	public String getDomaine() { return domaine; }
	public void setDomaine(String domaine) { this.domaine = domaine; }
	
	
}

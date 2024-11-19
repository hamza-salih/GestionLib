package DAO;

import java.sql.*;
import java.util.ArrayList;
import entite.Livre;

public class IDAOImp implements IDAO {

    private Connection cnx;
    private Statement stm;
    private String sql = "";

    public IDAOImp() {
        cnx = ConnexionBD.seConnecter();
        if (cnx == null) {
            System.out.println("Erreur de connexion à la base de données");
        }
    }

    @Override
    public void insert(Livre l) {
        sql = "INSERT INTO livre VALUES (" + l.getIsbn() + ", '" + l.getTitre() + "', '" + l.getDate_produ() + "', " + l.getNb_exemplaire() + ", '" + l.getNom_auteur() + "', '" + l.getDomaine() + "')";
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout : " + e.getMessage());
        }
    }

    @Override
    public void update(Livre l) {
        sql = "UPDATE livre SET titre = '" + l.getTitre() + "', date_produ = '" + l.getDate_produ() + "', Nb_exemplaire = " + l.getNb_exemplaire() + ", nom_auteur = '" + l.getNom_auteur() + "', domaine = '" + l.getDomaine() + "' WHERE isbn = " + l.getIsbn();
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        sql = "DELETE FROM livre WHERE isbn = " + id;
        try {
            stm = cnx.createStatement();
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression : " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Livre> select() {
        ArrayList<Livre> L = new ArrayList<>();
        sql = "SELECT * FROM livre";
        try {
            stm = cnx.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                L.add(new Livre(res.getInt("isbn"),res.getInt("Nb_exemplaire"),res.getString("titre"),res.getString("date_produ"),res.getString("nom_auteur"),res.getString("domaine")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la liste : " + e.getMessage());
        }
        return L;
    }

    @Override
    public ArrayList<Livre> search(int id) {
        ArrayList<Livre> livres = new ArrayList<>();
        sql = "SELECT * FROM livre WHERE isbn = " + id;
        try {
            stm = cnx.createStatement();
            ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                livres.add(new Livre(res.getInt("isbn"),res.getInt("Nb_exemplaire"),res.getString("titre"),res.getString("date_produ"),res.getString("nom_auteur"),res.getString("domaine")));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche : " + e.getMessage());
        }
        return livres.isEmpty() ? null : livres;
    }
}

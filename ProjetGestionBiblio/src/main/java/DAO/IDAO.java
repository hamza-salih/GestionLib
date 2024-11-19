package DAO;

import java.util.ArrayList;
import entite.Livre;

public interface IDAO {
	public void insert (Livre l);
	public void update (Livre l);
	public void delete (int id);
	ArrayList<Livre> select ();
	ArrayList<Livre> search (int id);
}

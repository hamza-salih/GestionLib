package DAO;

import entite.Livre;

public class Main {
	public static void main(String[] args) {
		IDAOImp i = new IDAOImp();
		i.insert(new Livre(1,1,"hh","hhh","hh","hhh"));
	}
}

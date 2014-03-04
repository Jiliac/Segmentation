package testImage;

import java.util.ArrayList;

import comparaison.equivalence.ListeEqui;



public class Test2 {

	public static void main(String[] args) {
		//DiagMainClass dmc = new DiagMainClass("10.jpg", "10b.jpg");
		ListeEqui le = new ListeEqui();
		
		ArrayList<Integer> aAjouter = new ArrayList<Integer>();
		aAjouter.add(1);
		aAjouter.add(36);
		le.add(aAjouter);
		
		aAjouter = new ArrayList<Integer>();
		aAjouter.add(1);
		aAjouter.add(25);
		le.add(aAjouter);
		
		aAjouter = new ArrayList<Integer>();
		aAjouter.add(2);
		aAjouter.add(3);
		le.add(aAjouter);
		
		aAjouter = new ArrayList<Integer>();
		aAjouter.add(1);
		aAjouter.add(3);
		aAjouter.add(3);
		le.add(aAjouter);
		
		System.out.println("fini");
	}
}

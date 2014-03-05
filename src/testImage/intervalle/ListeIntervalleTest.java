package testImage.intervalle;

import java.util.ArrayList;

public class ListeIntervalleTest extends ArrayList<Intervalle> {
	public ListeIntervalleTest() {
		Intervalle inter = new Intervalle(0, 1);
		inter.setGroupe(1);

		this.add(inter);
	}
}

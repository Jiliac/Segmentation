package testImage;

import java.util.ArrayList;
import baseDApprentissage.*;
import testImage.distance.*;
import testImage.intervalle.Intervalle;
import testImage.intervalle.ListeIntervalleTest;

public class Global {
	public final static int isVoisin = 2;
	public final static boolean supprimer = false;
	public final static ArrayList<Pixel> baseDApprentissage = new BrightnessBase();
	public final static Distance distance = new DistanceHue();
	public final static boolean afficherCercle = false;

	public final static boolean compterZones = true;
	// cette variable pourrait etre egale à l'inverse de supprimer, non?
	
	public final static ArrayList<Intervalle> baseIntervalle = new ListeIntervalleTest();
	public final static Intervalle intervalleACompter = baseIntervalle.get(1);
}

package testImage;

import java.util.ArrayList;
import baseDApprentissage.BrightnessBase;
import testImage.distance.Distance;
import testImage.distance.DistanceBrightness;
import testImage.intervalle.Intervalle;

public class Global {
	public final static int isVoisin = 2;
	public final static boolean supprimer = true;
	public final static ArrayList<Pixel> baseDApprentissage = new BrightnessBase();
	public final static Distance distance = new DistanceBrightness();
	public final static boolean afficherCercle = true;

	public final static boolean compterZones = false;
	// cette variable pourrait etre egale à l'inverse de supprimer, non?
	
	public final static Intervalle intervalleACompter = new Intervalle(0, 1);
}

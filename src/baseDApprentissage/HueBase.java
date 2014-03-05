package baseDApprentissage;

import java.awt.Color;
import java.util.ArrayList;

import testImage.Pixel;

public class HueBase extends ArrayList<Pixel> {

	public HueBase() {
		this(10);
	}

	public HueBase(int parametre) {
		Pixel pp[] = new Pixel[parametre + 1];
		for (int i = 0; i < parametre; i++) {
			pp[i] = new Pixel((float) (i + 0.5) / parametre);
			pp[i].setGroupe(i + 1);
			this.add(pp[i]);
		}

		pp[parametre] = new Pixel(new Color(50, 50, 50));
		pp[parametre].setGroupe(parametre);
		this.add(pp[parametre]);
	}

}

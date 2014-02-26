package baseDApprentissage;

import java.awt.Color;
import java.util.ArrayList;

import testImage.Pixel;

public class HueBase extends ArrayList<Pixel>{
	
	public HueBase(){
		Pixel pp[] = new Pixel[37];
		for (int i = 0; i < 36; i++) {
			pp[i] = new Pixel((float) (i + 0.5) / 36);
			pp[i].setGroupe(i + 1);
			this.add(pp[i]);
		}

		pp[36] = new Pixel(new Color(50, 50, 50));
		pp[30].setGroupe(36);
		this.add(pp[36]);
	}
	
}

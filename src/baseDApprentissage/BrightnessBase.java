package baseDApprentissage;
import java.util.ArrayList;
import testImage.Pixel;

public class BrightnessBase extends ArrayList<Pixel>{
	public BrightnessBase(){
		Pixel p1,p2;
		p1 = new Pixel((float)0,(float)0,(float).2);
		p2 = new Pixel((float)0,(float)0,(float).8);
		p1.setGroupe(0);
		p2.setGroupe(1);
		this.add(p1);
		this.add(p2);
	}
}

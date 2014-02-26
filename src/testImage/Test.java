package testImage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;

public class Test {
/*
	public static void main(String[] args) {
		BufferedImage img = null;//Cr¨¦ation d'une image BufferedImage
		try {
			//la m¨¦thode statique  read de la classe javax.imageio.ImageIO renvoie 
			//une instance de la classe BufferedImage (qui ¨¦tend la classe abstraite Image).
			img = ImageIO.read(new File("1.jpg"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int i=0;
		float diff = 0;
		
		ArrayList<Float> list = new ArrayList<Float>();
		for(int x = 0; x < img.getWidth(); x++){
			for(int y = 0; y < img.getHeight(); y++){//parcourir l'image 
				Color c = new Color(img.getRGB(x, y));//prendre des valeurs RGB de chaque pixel 
				int r = c.getRed();
				int g = c.getGreen();
				int b = c.getBlue();
				
				
				float[] hsb = Color.RGBtoHSB(r, g, b, null); //convertir RGB en HSB
				float h = hsb[0]; //prendre la valeur teinte 
				list.add(h);
				//System.out.println("h changes"+list.get(i));	
				
				i=i+1;
				  
				if (i>3){
				diff = list.get(2)-h;
				
				}  
				  //if(h < 0.39 && h > 0.34){
				if((i>3) && h<(list.get(2)+0.1) && h>(list.get(2)-0.1))
				//if (diff<0.02)	
				 {
				
					int rgb = new Color(255, 255, 255).getRGB(); 
					img.setRGB(x, y, rgb); //remplir cette zone par une couleur homog¨¨ne 
					
				  }
			}
		}
		
		    File outputfile = new File("1a.jpg");  
	    try {
			ImageIO.write(img, "jpg" ,outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}
*/
}

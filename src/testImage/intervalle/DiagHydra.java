package testImage.intervalle;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import testImage.Kppv;
import testImage.Pixel;

import comparaison.Ensemble;
import comparaison.Point;

public class DiagHydra {
	public DiagHydra(String nomFichierEntree, String nomFichierSortie) {
		// Creation d'une image BufferedImage

		BufferedImage img = null;
		try {
			// la methode statique read de la classe javax.imageio.ImageIO
			// renvoie
			// une instance de la classe BufferedImage (qui ��tend la classe
			// abstraite Image).
			img = ImageIO.read(new File(nomFichierEntree));

		} catch (IOException e) {
			e.printStackTrace();
		}

		MethodeSeuil analyse = new MethodeSeuil();

		Point sortieATrier[][] = new Point[img.getWidth()][img.getHeight()];

		// segmentation
		ArrayList<Pixel> image = new ArrayList<Pixel>();
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				// analyse
				Color c = new Color(img.getRGB(x, y));
				Pixel pixel = new Pixel(c);
				analyse.setGroupe(pixel);

				// preparation de l'analyse
				sortieATrier[x][y] = new Point(x, y, pixel); 
			}
		}

		// analyse
		Ensemble ensembleAAnlyser = new Ensemble(img, sortieATrier);

		
		// ecriture en sortie

		File outputfile = new File(nomFichierSortie);
		try {
			ImageIO.write(img, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

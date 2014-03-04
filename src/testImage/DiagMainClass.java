package testImage;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import comparaison.Collection;
import comparaison.Ensemble;
import comparaison.Point;

public class DiagMainClass {
	public DiagMainClass(String nomFichierEntree, String nomFichierSortie) {
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

		Kppv analyse = new Kppv();
		ArrayList<Pixel> baseDApprentissage = analyse.getBase();

		Point sortieATrier[][] = new Point[img.getWidth()][img.getHeight()];

		// segmentation
		ArrayList<Pixel> image = new ArrayList<Pixel>();
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				// analyse
				Color c = new Color(img.getRGB(x, y));
				Pixel pixel = new Pixel(c);
				analyse.kppv(pixel);

				// on set la couleur
				System.out.println(pixel.getnumGroupe());
				if (pixel.getnumGroupe() > 0) {
					Color newColor = baseDApprentissage.get(
							pixel.getnumGroupe() - 1).getColor();
					int rgb = newColor.getRGB();
					img.setRGB(x, y, rgb);
				}

				sortieATrier[x][y] = new Point(x, y, pixel); // preparation de
																// l'analyse
			}
		}

		// analyse
		Ensemble ensembleAAnlyser = new Ensemble(img, sortieATrier);

		/******** affichage des collection */
		this.afficherCollec(ensembleAAnlyser, img);
		
		// ecriture en sortie

		File outputfile = new File(nomFichierSortie);
		try {
			ImageIO.write(img, "jpg", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void afficherCollec(Ensemble ensembleAAnlyser, BufferedImage img) {
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				img.setRGB(x, y, 0);
			}
		}
		ArrayList<Collection> ensemble = ensembleAAnlyser.getCollec();
		for (Collection collec : ensemble) {
			this.afficher(collec, img);
		}
	}

	public void afficher(Collection collec, BufferedImage img) {
		Point centre = collec.getCentre();
		double rayon = collec.getRayon();
		
		//debuggage
		if(rayon == 0)
			rayon = 1;
		
		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				Point p = new Point(x, y);
				if (collec.distance(p, centre) < rayon)
					img.setRGB(x, y, new Color(255, 255, 255).getRGB());
			}
		}
	}
}

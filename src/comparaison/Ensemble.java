package comparaison;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Ensemble {

	/*********** constructeur *************/

	private BufferedImage img;
	private Point[][] premiereEntree;
	private ArrayList<Point> premiereCollection;
	private int width, height;

	public Ensemble(BufferedImage img, Point[][] premiereEntree) {
		// initialisation des variables
		this.img = img;
		width = img.getWidth();
		height = img.getHeight();
		this.premiereEntree = premiereEntree;
		premiereCollection = new ArrayList<Point>();

		this.mainAlgo();
	}

	/**************** premiere etage de developpement *******************/

	private ArrayList<Collection> ensemble = new ArrayList<Collection>();
	private ArrayList<Collection> newEnsemble = new ArrayList<Collection>();

	public void mainAlgo() {
		// donne valeur a grOut de tous les points
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				if (x != 0)
					premiereEntree[x][y].setGrIn(premiereEntree[x - 1][y]
							.getGrOut());
				else if (y != 0)
					premiereEntree[x][y].setGrIn(premiereEntree[x][y - 1]
							.getGrOut());
				else
					premiereEntree[x][y].setGrIn(premiereEntree[x][y]
							.getGrOut());
			}
		}

		// il faut copier premiereEntree dans premiereCollection
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				premiereCollection.add(premiereEntree[x][y]);
			}
		}

		/*
		 * cette partie prend en entree l'ensemble des points d'une image et
		 * forme les collections de points d'en l'ensemble
		 */
		for (Point point : premiereCollection)
			this.collectionExistance(point); // rassemblement des categories

		for (Collection myCollec : ensemble)
			this.composantesConnexes(myCollec); // division des collections en
												// composantes connexes
		
		// 	ATTENTION MARCHE PAS

		/*
		 * on enleve les collections de rayon trop grand et les collection
		 * d'ecart type trop grand
		 */
		ArrayList<Collection> aSupprimer = new ArrayList<Collection>();
		int size = newEnsemble.size();
		for (int i = 0; i < size; i++) {
			Collection collec = newEnsemble.get(i);
			if (collec.getRayon() > width / 5 || collec.getRayon() > height / 5
					|| collec.getEcartType() > collec.getRayon())
				aSupprimer.add(collec);
		}
		for (Collection collec : aSupprimer)
			newEnsemble.remove(collec);
	}

	/********* deuxieme etage de developpement *************/

	private void composantesConnexes(Collection myCollec) {
		/***************** analyse ***************/
		
		//IL FAUT INITIALISER
		Collection pointPasses = new Collection();
		int maxEtiquette = 0;
		for (Point pointSetEtiquette : myCollec) {
			for (Point pointReference : pointPasses) {
				if (myCollec.isVoisin(pointSetEtiquette, pointReference)) {
					//ATTENTION
					// ca peut pas marcher: je compare meme pas les categorie
					pointSetEtiquette
							.setEtiquette(pointReference, maxEtiquette);
				}
				if (pointSetEtiquette.getEtiquette() == maxEtiquette)
					maxEtiquette++;
				pointPasses.add(pointSetEtiquette);
			}
			// MAX ETIQUETTE JAMAIS INCREMENTE
			// je pense que isVoisin n'est jamais appele
		}

		/******** scinder les collections *******/

		// initialise
		Collection myCollecScinde[] = new Collection[maxEtiquette + 1];
		for (Collection collec : myCollecScinde)
			collec = new Collection();

		// ajout
		for (Point point : myCollec) {
			myCollecScinde[point.getEtiquette()].add(point);
		}
		// ATTENTION ETIQUETAGE

		// on ajoute le resultat de l'analyse
		for (Collection collec : myCollecScinde)
			newEnsemble.add(collec);

	}

	private Collection collectionExistance(Point point) {
		for (Collection myCollec : ensemble) {
			if (myCollec.equal(point)){
				myCollec.add(point);
				return myCollec;
			}
		}

		Collection newCollec = new Collection();
		newCollec.add(point);
		ensemble.add(newCollec);
		return newCollec;
	}

}

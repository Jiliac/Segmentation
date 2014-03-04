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
				if (x != 0 && y != 0){
					int myGr = premiereEntree[x][y].getGrIn();
				}
				else if (x != 0)
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

		ArrayList<Collection> ensembleCopie = new ArrayList<Collection>();
		for (Collection collec : ensemble) {
			Point pivot = collec.get(0);
			if (pivot.getGrIn() != pivot.getGrOut())
				ensembleCopie.add(collec);
		}
		ensemble = ensembleCopie;

		for (Collection myCollec : ensemble)
			this.composantesConnexes2(myCollec); // division des collections en
													// composantes connexes

		/*
		 * on enleve les collections de rayon trop grand et les collection
		 * d'ecart type trop grand
		 */
		ArrayList<Collection> aSupprimer = new ArrayList<Collection>();
		int size = newEnsemble.size();
		for (int i = 0; i < size; i++) {
			Collection collec = newEnsemble.get(i);
			if (collec.getRayon() > width / 5 || collec.getRayon() > height / 5
					|| collec.size() < 10 /*
										 * || collec.getEcartType() >
										 * 3*collec.getRayon()
										 */)
				aSupprimer.add(collec);
		}
		/*
		 * for (Collection collec : aSupprimer) newEnsemble.remove(collec);
		 */
		for (Collection collec : newEnsemble)
			System.out.println(collec);
		System.out.println("fini");
	}

	/********* deuxieme etage de developpement *************/

	private void composantesConnexes2(Collection myCollec) {
		int maxEtiquette = 0;
		ArrayList<Point> pastPts = new ArrayList<Point>();

		for (Point ptSetEtiquette : myCollec) {
			ptSetEtiquette.setEtiquette(maxEtiquette);

			ArrayList<Point> voisins = new ArrayList<Point>();
			for (Point past : pastPts) {
				if (myCollec.isVoisin(ptSetEtiquette, past)) {
					voisins.add(past);
				}
			}

			if (!voisins.isEmpty())
				ptSetEtiquette.setEtiquette(voisins.get(0).getEtiquette());

			if (ptSetEtiquette.getEtiquette() == maxEtiquette)
				maxEtiquette++;
			pastPts.add(ptSetEtiquette);
		}

		Collection newCollec[] = new Collection[maxEtiquette];
		for (int i = 0; i < maxEtiquette; i++)
			newCollec[i] = new Collection();
		for (Point ptSetEtiquette : myCollec)
			newCollec[ptSetEtiquette.getEtiquette()].add(ptSetEtiquette);
		for (int i = 0; i < maxEtiquette; i++)
			newEnsemble.add(newCollec[i]);
	}

	private void composantesConnexes(Collection myCollec) {
		/***************** analyse ***************/

		Collection pointPasses = new Collection();
		int maxEtiquette = 0;
		for (Point pointSetEtiquette : myCollec) {
			pointSetEtiquette.setEtiquette(maxEtiquette);
			// les voisin, qui ont le meme groupe ont la meme etiquette
			for (Point pointRef : pointPasses) {
				if (myCollec.isVoisin(pointSetEtiquette, pointRef)
						&& pointRef.equal(pointSetEtiquette))
					pointSetEtiquette.setEtiquette(pointRef);
			}

			if (pointSetEtiquette.getEtiquette() == maxEtiquette)
				maxEtiquette++;
			pointPasses.add(pointSetEtiquette);
		}

		/******** scinder les collections *******/

		// initialise
		Collection myCollecScinde[] = new Collection[maxEtiquette + 1];
		for (int i = 0; i <= maxEtiquette; i++)
			myCollecScinde[i] = new Collection();

		// ajout
		for (Point point : myCollec) {
			myCollecScinde[point.getEtiquette()].add(point);
		}

		// on ajoute le resultat de l'analyse
		for (Collection collec : myCollecScinde)
			if (collec.size() > 5)
				newEnsemble.add(collec);

	}

	private Collection collectionExistance(Point point) {
		for (Collection myCollec : ensemble) {
			if (myCollec.equal(point)) {
				myCollec.add(point);
				return myCollec;
			}
		}

		Collection newCollec = new Collection();
		newCollec.add(point);
		ensemble.add(newCollec);
		return newCollec;
	}

	public ArrayList<Collection> getCollec() {
		return this.newEnsemble;
	}

}
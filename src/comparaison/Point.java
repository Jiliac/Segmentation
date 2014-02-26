package comparaison;

import testImage.Pixel;

public class Point {
	private int x, y;
	private int grIn, grOut;

	public Point(int x, int y, int grIn, int grOut) {
		this.x = x;
		this.y = y;
		this.grIn = grIn;
		this.grOut = grOut;
	}

	public Point(int x, int y, Pixel pixel) {
		this.x = x;
		this.y = y;
		this.grOut = pixel.getnumGroupe();
	}

	public boolean equal(Point p) {
		if (this.grIn == p.getGrIn() && this.grOut == p.getGrOut())
			return true;
		else
			return false;
	}

	/****** outil de determination de la connexitee *********/

	int etiquette = -1;

	public void setEtiquette(Point voisin, int maxEtiquette) {
		if (voisin != null) {
			if (this.equal(voisin)) {
				etiquette = voisin.getEtiquette();
				return;
			}
		} else {
			etiquette = maxEtiquette;
			return;
		}
	}

	public int getEtiquette() {
		return etiquette;
	}

	public void setEtiquette(int etiquette) {
		this.etiquette = etiquette;
	}

	/************* getter **********/

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public void setGrIn(int grIn) {
		this.grIn = grIn;
	}

	public int getGrIn() {
		return grIn;
	}

	public int getGrOut() {
		return grOut;
	}

}
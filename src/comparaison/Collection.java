package comparaison;

import java.util.ArrayList;

public class Collection extends ArrayList<Point> {

	/*********** analyse d'une collection *************/
	public double getEcartType() {
		int sommeCarreRayon = 0;
		Point centre = this.getCentre();
		for (Point point : this)
			sommeCarreRayon += this.distance(point, centre)
					* this.distance(point, centre);
		int size = this.size();
		int carreRayon = sommeCarreRayon / size;

		return Math.sqrt(carreRayon - this.getRayon() * this.getRayon());
	}

	public Point getCentre() {
		int sommeX = 0, sommeY = 0;
		for (Point point : this) {
			sommeX += point.getPosX();
			sommeY += point.getPosY();
		}
		int size = this.size();
		return new Point((int) sommeX / size, (int) sommeY / size,
				this.getGrIn(), this.getGrOut());
	}

	public double getRayon() {
		int sommeRayon = 0;
		Point centre = this.getCentre();
		for (Point point : this)
			sommeRayon += this.distance(point, centre);
		int size = this.size();
		return sommeRayon / size;
	}

	public double distance(Point p1, Point p2) {
		return Math.sqrt((p1.getPosX() - p2.getPosX())
				* (p1.getPosX() - p2.getPosX()) + (p1.getPosY() - p2.getPosY())
				* (p1.getPosY() - p2.getPosY()));
	}

	/************ traitement des collections **********/

	public boolean equal(Point p) {
		if (p.equal(this.get(0)))
			return true;
		else
			return false;
	}

	public boolean add(Point p) {
		if (super.isEmpty())
			return super.add(p);
		else {
			if (p.equal(super.get(0)))
				return super.add(p);
			else
				return false;
		}
	}

	public boolean isVoisin(Point moi, Point potentielVoisin) {
		int ecartX = moi.getPosX() - potentielVoisin.getPosX();
		int ecartY = moi.getPosY() - potentielVoisin.getPosY();
		if (Math.abs(ecartX) < 5 || Math.abs(ecartY) < 5)
			return true;
		else
			return false;
	}

	/******** getter ***********/

	public int getGrIn() {
		return this.get(0).getGrIn();
	}

	public int getGrOut() {
		return this.get(0).getGrOut();
	}
}
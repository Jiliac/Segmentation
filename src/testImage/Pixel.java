package testImage;

import java.awt.Color;

import testImage.distance.*;

public class Pixel {

	private int numgroupe = -1;
	private int r, g, b;
	private Color color;
	private float hue;

	public Pixel(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;

		this.color = new Color(r, g, b);

		this.hue = Color.RGBtoHSB(r, g, b, null)[0];
	}

	public Pixel(Color color) {
		this.color = color;

		this.r = color.getRed();
		this.g = color.getGreen();
		this.b = color.getBlue();

		this.hue = Color.RGBtoHSB(r, g, b, null)[0];
	}

	public Pixel(float hue) {
		color = new Color(Color.HSBtoRGB(hue, 1, 1));

		this.r = color.getRed();
		this.g = color.getGreen();
		this.b = color.getBlue();

		this.hue = Color.RGBtoHSB(r, g, b, null)[0];
	}

	public Pixel(float hue, float saturation, float brightness) {
		color = new Color(Color.HSBtoRGB(hue, saturation, brightness));

		this.r = color.getRed();
		this.g = color.getGreen();
		this.b = color.getBlue();

		this.hue = Color.RGBtoHSB(r, g, b, null)[0];
	}
	
	/*********** le calcul de distance *****************/

	private Distance distance = new DistanceHue();

	public double distance(Pixel p) {
		return distance.distance(this, p);
	}

	/*************** getters et setters **************/

	public void setDistance(Distance distance) {
		this.distance = distance;
	}

	public float getHue() {
		float hsb[] = Color.RGBtoHSB(color.getRed(), color.getGreen(),
				color.getBlue(), null);
		return hsb[0];
	}

	public float getSaturation() {
		float hsb[] = Color.RGBtoHSB(color.getRed(), color.getGreen(),
				color.getBlue(), null);
		return hsb[1];
	}

	public float getBrightness() {
		float hsb[] = Color.RGBtoHSB(color.getRed(), color.getGreen(),
				color.getBlue(), null);
		return hsb[2];
	}

	public void setGroupe(int newnum) {
		this.numgroupe = newnum;
	}

	public int getnumGroupe() {
		return this.numgroupe;
	}

	public Color getColor() {
		return this.color;
	}
}

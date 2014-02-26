package testImage.distance;

import testImage.Pixel;

public class DistanceBrightness implements Distance{

	public double distance(Pixel p1, Pixel p2) {
		return Math.abs(p1.getBrightness()-p2.getBrightness());
	}
	
}

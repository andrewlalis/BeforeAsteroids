package nl.andrewlalis.view.view_model;

import nl.andrewlalis.model.Ship;

import java.awt.*;
import java.awt.geom.Path2D;

public class ShipViewModel implements ViewModel {
	private Ship object;

	public ShipViewModel(Ship object) {
		this.object = object;
	}

	@Override
	public void draw(Graphics2D g2) {
		double s = this.object.getRadius();
		double bodyFront = -s / 2;
		double bodySide = s / 4;
		var body = new Path2D.Double();
		body.moveTo(0, bodyFront);
		body.lineTo(-bodySide, -bodyFront);
		body.lineTo(bodySide, -bodyFront);
		body.lineTo(0, bodyFront);
		g2.setColor(Color.YELLOW);
		g2.fill(body);
		g2.setColor(Color.GRAY);
		g2.draw(body);

		if (this.object.forwardThrusterEnabled) {
			var exhaust = new Path2D.Double();
			exhaust.moveTo(-bodySide, -bodyFront);
			exhaust.lineTo(bodySide, -bodyFront);
			exhaust.lineTo(0, -bodyFront + s / 4);
			exhaust.lineTo(-bodySide, -bodyFront);
			g2.setColor(Color.RED);
			g2.fill(exhaust);
		}
	}
}

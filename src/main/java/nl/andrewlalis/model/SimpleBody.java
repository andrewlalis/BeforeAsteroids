package nl.andrewlalis.model;

import nl.andrewlalis.view.view_model.ViewModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SimpleBody extends PhysicsObject implements ViewModelled {
	private double radius;
	private Color color;

	public SimpleBody(double mass, double positionX, double positionY, double velocityX, double velocityY, double orientation, double radius, Color color) {
		super(mass, positionX, positionY, velocityX, velocityY, orientation);
		this.radius = radius;
		this.color = color;
	}

	public double getRadius() {
		return radius;
	}

	public Color getColor() {
		return color;
	}

	@Override
	public ViewModel getViewModel() {
		return g2 -> {
			g2.setColor(this.color);
			AffineTransform pre = g2.getTransform();
			g2.rotate(this.orientation, this.positionX, this.positionY);
			g2.fill(new Ellipse2D.Double(this.positionX - this.radius, this.positionY - this.radius, this.radius * 2, this.radius * 2));
			g2.setColor(Color.WHITE);
			g2.draw(new Ellipse2D.Double(this.positionX - this.radius, this.positionY - this.radius, this.radius * 2, this.radius * 2));
			g2.draw(new Line2D.Double(this.positionX, this.positionY - this.radius, this.positionX, this.positionY + this.radius));
			g2.setTransform(pre);
		};
	}
}

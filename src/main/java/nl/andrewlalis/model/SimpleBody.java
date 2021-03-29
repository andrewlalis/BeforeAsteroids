package nl.andrewlalis.model;

import nl.andrewlalis.physics.Vec2;
import nl.andrewlalis.view.view_model.ViewModel;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SimpleBody extends PhysicsObject implements ViewModelled {
	private double radius;
	private Color color;

	public SimpleBody(double mass, double radius, Color color) {
		super(mass);
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

			AffineTransform pre = g2.getTransform();
			g2.translate(this.position.x, this.position.y);
			// Render velocity vector before rotating.
			g2.setColor(Color.RED);
			Vec2 vNorm = this.velocity;
			g2.draw(new Line2D.Double(0, 0, vNorm.x, vNorm.y));
			g2.rotate(this.orientation);

			g2.setColor(this.color);
			g2.fill(new Ellipse2D.Double(-this.radius, -this.radius, this.radius * 2, this.radius * 2));
			g2.setColor(Color.WHITE);
			g2.draw(new Ellipse2D.Double(-this.radius, -this.radius, this.radius * 2, this.radius * 2));
			g2.draw(new Line2D.Double(0, -this.radius, 0, this.radius));
			g2.setTransform(pre);
		};
	}
}

package nl.andrewlalis.view.view_model;

import nl.andrewlalis.model.SimpleBody;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class SimpleBodyViewModel extends TransformedViewModel<SimpleBody> {
	public SimpleBodyViewModel(SimpleBody simpleBody) {
		super(simpleBody);
	}

	@Override
	protected void drawLocal(Graphics2D g2) {
		double r = this.object.getRadius();

		g2.setColor(this.object.getColor());
		g2.fill(new Ellipse2D.Double(-r, -r, r * 2, r * 2));
		g2.setColor(Color.WHITE);
		g2.draw(new Ellipse2D.Double(-r, -r, r * 2, r * 2));
		g2.draw(new Line2D.Double(0, -r, 0, r));

		// Draw angular velocity vector tangent to body.
		g2.setColor(Color.MAGENTA);
		double v = this.object.getAngularVelocity() * r;
		g2.draw(new Line2D.Double(0, -r, v, -r));

		g2.rotate(-this.object.getOrientation());
		g2.setColor(Color.RED);
		g2.draw(new Line2D.Double(0, 0, this.object.getVelocity().x, this.object.getVelocity().y));
	}
}

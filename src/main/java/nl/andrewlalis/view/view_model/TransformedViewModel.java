package nl.andrewlalis.view.view_model;

import nl.andrewlalis.model.PhysicsObject;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class TransformedViewModel<T extends PhysicsObject> implements ViewModel {
	protected final T object;

	public TransformedViewModel(T object) {
		this.object = object;
	}

	@Override
	public void draw(Graphics2D g2) {
		AffineTransform originalTransform = g2.getTransform();
		g2.transform(this.object.getTransform());
		this.drawLocal(g2);
		g2.setTransform(originalTransform);
	}

	protected abstract void drawLocal(Graphics2D g2);
}

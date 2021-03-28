package nl.andrewlalis.model;

import nl.andrewlalis.view.GamePanel;

import java.util.Set;

public class GameModel {
	private Set<PhysicsObject> objects;
	private GamePanel observerPanel;

	public GameModel(Set<PhysicsObject> objects) {
		this.objects = objects;
	}

	public Set<PhysicsObject> getObjects() {
		return objects;
	}

	public void setObserverPanel(GamePanel observerPanel) {
		this.observerPanel = observerPanel;
	}

	public void tick(double deltaT) {
		for (PhysicsObject object : this.objects) {
			for (PhysicsObject other : this.objects) {
				if (object != other) {
					object.updateVelocityForObject(other, deltaT);
				}
			}
			object.updatePosition(deltaT);
			//System.out.println(object);
		}
		if (this.observerPanel != null) {
			this.observerPanel.repaint();
		}
	}
}

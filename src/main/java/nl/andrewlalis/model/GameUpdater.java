package nl.andrewlalis.model;

import nl.andrewlalis.view.GamePanel;

public class GameUpdater extends Thread {
	public static final double PHYSICS_FPS = 60.0;
	public static final double MILLISECONDS_PER_PHYSICS_TICK = 1000.0 / PHYSICS_FPS;
	public static final double PHYSICS_SPEED = 1.0;

	public static final double DISPLAY_FPS = 60.0;
	public static final double MILLISECONDS_PER_DISPLAY_FRAME = 1000.0 / DISPLAY_FPS;

	private final GameModel model;
	private final GamePanel gamePanel;
	private volatile boolean running = true;

	public GameUpdater(GameModel model, GamePanel gamePanel) {
		this.model = model;
		this.gamePanel = gamePanel;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	@Override
	public void run() {
		long lastPhysicsUpdate = System.currentTimeMillis();
		long lastDisplayUpdate = System.currentTimeMillis();
		while (this.running) {
			long currentTime = System.currentTimeMillis();
			long timeSinceLastPhysicsUpdate = currentTime - lastPhysicsUpdate;
			long timeSinceLastDisplayUpdate = currentTime - lastDisplayUpdate;
			if (timeSinceLastPhysicsUpdate >= MILLISECONDS_PER_PHYSICS_TICK) {
				double elapsedSeconds = timeSinceLastPhysicsUpdate / 1000.0;
				this.updateModelPhysics(elapsedSeconds * PHYSICS_SPEED);
				lastPhysicsUpdate = currentTime;
			}
			if (timeSinceLastDisplayUpdate >= MILLISECONDS_PER_DISPLAY_FRAME) {
				this.gamePanel.repaint();
				lastDisplayUpdate = currentTime;
			}
		}
	}

	private void updateModelPhysics(double deltaT) {
		for (PhysicsObject object : this.model.getObjects()) {
			for (PhysicsObject other : this.model.getObjects()) {
				if (object != other) {
					object.gravitateTowards(other, deltaT);
				}
			}
			object.updatePosition(deltaT);
		}
		this.updateShipPhysics(deltaT);
	}

	private void updateShipPhysics(double deltaT) {
		for (PhysicsObject object : this.model.getObjects()) {
			this.model.getPlayer().getShip().gravitateTowards(object, deltaT);
		}
		this.model.getPlayer().getShip().updateMovement(deltaT);
		this.model.getPlayer().getShip().updatePosition(deltaT);
	}
}

package nl.andrewlalis.model;

public class GameUpdater implements Runnable {
	public static final double PHYSICS_FPS = 60.0;
	public static final double MILLISECONDS_PER_TICK = 1000.0 / PHYSICS_FPS;
	public static final double PHYSICS_SPEED = 0.001;

	private GameModel model;

	public GameUpdater(GameModel model) {
		this.model = model;
	}

	@Override
	public void run() {
		long lastPhysicsUpdate = System.currentTimeMillis();

		while (true) {
			long currentTime = System.currentTimeMillis();
			long timeSinceLastPhysicsUpdate = currentTime - lastPhysicsUpdate;

			if (timeSinceLastPhysicsUpdate >= MILLISECONDS_PER_TICK) {
				double elapsedSeconds = timeSinceLastPhysicsUpdate / 1000.0;
				this.model.tick(elapsedSeconds * PHYSICS_SPEED);
				lastPhysicsUpdate = currentTime;
			}
		}
	}
}

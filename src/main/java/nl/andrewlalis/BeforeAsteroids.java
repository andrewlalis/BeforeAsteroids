package nl.andrewlalis;

import nl.andrewlalis.model.GameModel;
import nl.andrewlalis.model.GameUpdater;
import nl.andrewlalis.model.PhysicsObject;
import nl.andrewlalis.model.SimpleBody;
import nl.andrewlalis.view.GameFrame;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * The main entry point for the application. It's here where we receive any
 * command line arguments the user has given, and initialize the user interface.
 */
public class BeforeAsteroids {
	public static void main(String[] args) {
		Set<PhysicsObject> objects = new HashSet<>();
		SimpleBody earth = new SimpleBody(5.9722E24, 400, 400, 0, 0, 0, 10, Color.BLUE);
		earth.setAngularVelocity(2 * Math.PI * -1000);
		PhysicsObject ball = new SimpleBody(1.0, 400, 750, 1000000,  0, 0, 1, Color.GREEN);
		objects.add(earth);
		objects.add(ball);
		GameModel model = new GameModel(objects);
		final GameFrame gameFrame = new GameFrame(model);
		gameFrame.setVisible(true);
		new Thread(new GameUpdater(model)).start();
	}
}

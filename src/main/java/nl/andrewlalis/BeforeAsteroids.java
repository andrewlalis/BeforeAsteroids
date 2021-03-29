package nl.andrewlalis;

import nl.andrewlalis.model.GameModel;
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
		SimpleBody earth = new SimpleBody(5.9722E24 * 0.000000001, 100, Color.BLUE);
		earth.setPosition(400, 400);
		earth.setAngularVelocity(-0.001 * Math.PI);
		PhysicsObject ball = new SimpleBody(1.0, 5, Color.GREEN);
		ball.setPosition(400, 550);
		ball.setVelocity(50, 0);
		ball.setAngularVelocity(Math.PI * 4);
		objects.add(earth);
		objects.add(ball);

		GameModel model = new GameModel(objects);
		final GameFrame gameFrame = new GameFrame(model);
		gameFrame.setVisible(true);
	}
}

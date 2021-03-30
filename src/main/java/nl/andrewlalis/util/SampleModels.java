package nl.andrewlalis.util;

import nl.andrewlalis.model.*;

import java.awt.*;

public class SampleModels {
	public static GameModel getSimpleTwoBodySystem() {
		Player p = new Player(new Ship(500, 20));
		p.getShip().setPosition(200, 200);
		GameModel model = new GameModel(p);
		SimpleBody earth = new SimpleBody(5.9722E24 * 0.000000001, 100, Color.BLUE);
		earth.setPosition(400, 400);
		earth.setAngularVelocity(-0.001 * Math.PI);
		model.add(earth);
		PhysicsObject ball = new SimpleBody(1.0, 5, Color.GREEN);
		ball.setPosition(400, 550);
		ball.setVelocity(60, 0);
		ball.setAngularVelocity(Math.PI);
		model.add(ball);
		return model;
	}
}

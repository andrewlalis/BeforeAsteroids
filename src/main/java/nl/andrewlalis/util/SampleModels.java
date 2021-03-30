package nl.andrewlalis.util;

import nl.andrewlalis.model.*;

import java.awt.*;

public class SampleModels {
	public static GameModel getSimpleTwoBodySystem() {
		Player p = new Player(new Ship(50000, 20, 2000000));
		p.getShip().setPosition(-250, 0);
		p.getShip().setVelocity(0, -30);
		GameModel model = new GameModel(p);
		SimpleBody earth = new SimpleBody(5.9722E24 * 0.000000001, 100, Color.BLUE);
		earth.setPosition(0, 0);
		earth.setAngularVelocity(-0.001 * Math.PI);
		model.add(earth);
		PhysicsObject ball = new SimpleBody(1.0, 5, Color.GREEN);
		ball.setPosition(0, 150);
		ball.setVelocity(60, 0);
		ball.setAngularVelocity(Math.PI);
		model.add(ball);
		return model;
	}

	public static GameModel singleShip() {
		Player p = new Player(new Ship(1000, 30, 500));
		p.getShip().setPosition(100, 100);
		return new GameModel(p);
	}
}

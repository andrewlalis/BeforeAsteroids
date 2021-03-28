package nl.andrewlalis;

import nl.andrewlalis.view.GameFrame;

/**
 * The main entry point for the application. It's here where we receive any
 * command line arguments the user has given, and initialize the user interface.
 */
public class BeforeAsteroids {
	public static void main(String[] args) {
		final GameFrame gameFrame = new GameFrame();
		gameFrame.setVisible(true);
	}
}

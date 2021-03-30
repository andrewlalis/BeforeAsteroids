package nl.andrewlalis.control;

import nl.andrewlalis.model.Player;
import nl.andrewlalis.physics.Vec2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerListener implements KeyListener {
	private Player player;

	public PlayerListener(Player player) {
		this.player = player;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_W) {
			player.getShip().forwardThrusterEnabled = true;
		} else if (c == KeyEvent.VK_A) {
			player.getShip().turningLeft = true;
		} else if (c == KeyEvent.VK_D) {
			player.getShip().turningRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_W) {
			player.getShip().forwardThrusterEnabled = false;
		} else if (c == KeyEvent.VK_A) {
			player.getShip().turningLeft = false;
		} else if (c == KeyEvent.VK_D) {
			player.getShip().turningRight = false;
		}
	}
}

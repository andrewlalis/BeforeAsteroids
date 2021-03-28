package nl.andrewlalis.view;

import javax.swing.*;
import java.awt.*;

/**
 * The game panel is the component in which the entire game will be rendered. It
 * contains a reference to the current game model, which it uses as the source
 * of information when rendering.
 */
public class GamePanel extends JPanel {
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
}

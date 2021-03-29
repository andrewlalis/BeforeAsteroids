package nl.andrewlalis.view;

import nl.andrewlalis.model.GameModel;
import nl.andrewlalis.model.PhysicsObject;
import nl.andrewlalis.model.ViewModelled;

import javax.swing.*;
import java.awt.*;

/**
 * The game panel is the component in which the entire game will be rendered. It
 * contains a reference to the current game model, which it uses as the source
 * of information when rendering.
 */
public class GamePanel extends JPanel {
	private GameModel model;

	public GamePanel(GameModel model) {
		this.model = model;
		this.model.setObserverPanel(this);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		for (PhysicsObject object : this.model.getObjects()) {
			if (object instanceof ViewModelled) {
				((ViewModelled) object).getViewModel().draw(g2);
			}
		}
	}
}

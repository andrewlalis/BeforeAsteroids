package nl.andrewlalis.view;

import nl.andrewlalis.model.GameModel;
import nl.andrewlalis.model.PhysicsObject;
import nl.andrewlalis.model.ViewModelled;
import nl.andrewlalis.physics.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Deque;

/**
 * The game panel is the component in which the entire game will be rendered. It
 * contains a reference to the current game model, which it uses as the source
 * of information when rendering.
 */
public class GamePanel extends JPanel {
	private GameModel model;

	public GamePanel(GameModel model) {
		this.model = model;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, this.getWidth(), this.getHeight());

		//g2.translate(this.model.getPlayer().getShip().getPosition().x, this.model.getPlayer().getShip().getPosition().y);
		//g2.rotate(this.model.getPlayer().getShip().getOrientation());
		for (PhysicsObject object : this.model.getObjects()) {
			if (object instanceof ViewModelled) {
				((ViewModelled) object).getViewModel().draw(g2);
			}
		}

		Deque<Vec2> trail = this.model.getPlayer().getShip().getTrail();
		int i = 0;
		for (Vec2 p : trail) {
			int alpha = (int) (((trail.size() - i) / (double) trail.size()) * 128);
			g2.setColor(new Color(255, 255, 255, alpha));
			g2.fill(new Ellipse2D.Double(p.x - 1, p.y - 1, 2, 2));
			i++;
		}
		this.model.getPlayer().getShip().getViewModel().draw(g2);
	}
}

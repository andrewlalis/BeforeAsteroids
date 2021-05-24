package nl.andrewlalis.view;

import nl.andrewlalis.model.GameModel;
import nl.andrewlalis.model.PhysicsObject;
import nl.andrewlalis.model.Ship;
import nl.andrewlalis.model.ViewModelled;
import nl.andrewlalis.physics.Vec2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * The game panel is the component in which the entire game will be rendered. It
 * contains a reference to the current game model, which it uses as the source
 * of information when rendering.
 */
public class GamePanel extends JPanel {
	private final GameModel model;

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

		Ship pShip = this.model.getPlayer().getShip();
		g2.translate((this.getWidth() / 2.0), (this.getHeight() / 2.0));
		g2.scale(this.model.getPlayer().getScaleFactor(), this.model.getPlayer().getScaleFactor());
		pShip.getViewModel().draw(g2);
		g2.rotate(-pShip.getOrientation());
		g2.translate(-pShip.getPosition().x, -pShip.getPosition().y);

		Set<PhysicsObject> objectsToDraw = new HashSet<>(this.model.getObjects());
		objectsToDraw.remove(this.model.getFocusedObject());

		for (PhysicsObject object : objectsToDraw) {
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

	}
}

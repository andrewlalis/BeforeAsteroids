package nl.andrewlalis.view;

import nl.andrewlalis.control.PlayerListener;
import nl.andrewlalis.model.GameModel;
import nl.andrewlalis.model.GameUpdater;
import nl.andrewlalis.util.VersionReader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Properties;

/**
 * The frame which represents the entire game's display window. Its primary
 * purpose is to contain a {@link GamePanel} as its content pane.
 */
public class GameFrame extends JFrame {
	private GameUpdater updater;

	public GameFrame(GameModel model) {
		final Properties settings = this.loadSettings();
		this.setTitle(settings.get("title") + " V" + VersionReader.getVersion());
		this.setPreferredSize(new Dimension( // Set the frame's size according to settings.
				Integer.parseInt((String) settings.get("width")),
				Integer.parseInt((String) settings.get("height"))
		));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Set the frame to be disposed when the top-right 'X' is clicked.
		GamePanel gamePanel = new GamePanel(model);
		this.setContentPane(gamePanel);
		this.setLayout(new FlowLayout());
		this.pack();
		this.setLocationRelativeTo(null); // Start with the frame centered in the screen.

		this.updater = new GameUpdater(model, gamePanel);
		this.updater.start();
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				updater.setRunning(false);
			}
		});
		PlayerListener pl = new PlayerListener(model.getPlayer());
		this.addKeyListener(pl);
		this.addMouseWheelListener(pl);
	}

	private Properties loadSettings() {
		Properties settings = new Properties();
		try {
			settings.load(GameFrame.class.getClassLoader().getResourceAsStream("default_settings.properties"));
			return settings;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Could not load settings.", "Error", JOptionPane.ERROR_MESSAGE);
			throw new RuntimeException("Missing settings.", e);
		}
	}
}

package nl.andrewlalis.util;

import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;

import java.io.*;
import java.util.Objects;

/**
 * Simple utility for getting the current version of the project, according to
 * our own Maven POM file.
 */
public class VersionReader {
	/**
	 * @return The current version of the project, according to the POM file.
	 */
	public static String getVersion() {
		final MavenXpp3Reader modelReader = new MavenXpp3Reader();
		final File relativeFile = new File("pom.xml");
		try {
			Reader fileReader;
			if (relativeFile.exists()) {
				fileReader = new FileReader(relativeFile);
			} else {
				InputStream is = VersionReader.class.getClassLoader().getResourceAsStream("/META-INF/maven/nl.andrewlalis/BeforeAsteroids/pom.xml");
				fileReader = new InputStreamReader(Objects.requireNonNull(is));
			}
			return modelReader.read(fileReader).getVersion();
		} catch (IOException | XmlPullParserException e) {
			e.printStackTrace();
			return "Unknown";
		}
	}
}

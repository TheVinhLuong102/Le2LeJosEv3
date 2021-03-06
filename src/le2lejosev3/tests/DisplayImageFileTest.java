/**
 * 
 */
package le2lejosev3.tests;

import java.io.File;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;

import le2lejosev3.logging.Setup;
import le2lejosev3.pblocks.Display;
import lejos.hardware.Button;

/**
 * Test for displaying an image file.
 * 
 * @author Roland Blochberger
 */
public class DisplayImageFileTest {

	private static Class<?> clazz = DisplayImageFileTest.class;
	private static final Logger log = Logger.getLogger(clazz.getName());

	/**
	 * Main program entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// setup logging to file for all levels
		Setup.log2File(clazz, Level.ALL);
		log.fine("Starting ...");

		// determine which image files are there
		File imgDir = new File(Display.IMAGE_DIR);
		String[] imageFiles = imgDir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				log.fine("found file " + dir.getAbsolutePath() + File.separator + name);
				return name.toLowerCase().endsWith(".lni") || name.toLowerCase().endsWith(".rgf");
				// XXX LeJOS cannot display .rgf files !!
			}
		});
		if (imageFiles == null || imageFiles.length == 0) {
			log.fine("No image files found");

		} else {

			for (String imageFile : imageFiles) {

				if (imageFile.endsWith(".lni")) {
					// this is the standard extension, omit it
					imageFile = imageFile.substring(0, imageFile.lastIndexOf('.'));
				}
				// display image file on top left corner
				log.fine("Display image " + imageFile);
				Display.image(imageFile, true, 0, 0);
				log.fine("Display image done");

				// Wait until button press
				Button.waitForAnyPress();
			}
		}
		log.fine("The End");
	}

}

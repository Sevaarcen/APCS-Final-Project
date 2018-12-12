package base;

import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Loader {
	ClassLoader cl;

	public Loader() {
		cl = this.getClass().getClassLoader();
	}

	public Image loadImage(String name) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(cl.getResourceAsStream(name));
		return ImageIO.read(bis);
	}

	public Clip loadSound(String name) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
                System.out.print(cl.getClass().getName());
		AudioInputStream ais = AudioSystem.getAudioInputStream(new BufferedInputStream(cl.getResourceAsStream(name)));
		Clip sound = AudioSystem.getClip();
		sound.open(ais);
		return sound;
	}
}

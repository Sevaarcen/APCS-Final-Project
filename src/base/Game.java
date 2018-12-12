package base;

import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import GUI.EncounterGUI;
import GUI.GUIManager;
import GUI.GameOverWindow;
import GUI.Map;
import GUI.Tile.Terrain;
import GUI.GUIManager.State;

public class Game {
	public static Scanner in = new Scanner(System.in);
	public static Player player;
	public static Map map;
	public static GUIManager GUI;
	public static Encounter encounter;
	public static Loader loader;
	public static ThreadGroup sfxThreads;
	public static Thread mainGame;
	static Clip music;
	public static boolean simulating = false, soundEnabled = true, bananaEaten = false;
	public static final int ENCOUTNERCHANCE = 20;

	// POLISH balance monsters, player, and scaling
	public static void main(String[] args) {
		// EXTRA make every component passed as a parameter rather than by
		// direct reference
		// EXTRA make the game be able to be saved
		loader = new Loader();
		mainGame = new Thread() {
			@Override
			public void run() {
				player = new Player();
				map = new Map();
				GUI = new GUIManager(100, 50, map);
				player.lolBadCodeOrder(GUI);
				GUI.requestFocus();
			}
		};
		mainGame.start();

		reloadMusic();

		sfxThreads = new ThreadGroup("All the SFX");
		sfxThreads.setDaemon(true);

		if (!soundEnabled)
			ThreadController.stopSound();
	}

	// Even restarts the game without pausing the music :)
	public static void newGame() {
		Thread newGameThread = new Thread() {
			@Override
			public void run() {
				// simulates window closing
				GUI.dispatchEvent(new WindowEvent(GUI, WindowEvent.WINDOW_CLOSING));
				int posX = GUI.getX();
				int posY = GUI.getY();
				player = new Player();
				map = new Map();
				GUI = new GUIManager(posX, posY, map);
				player.lolBadCodeOrder(GUI);
				GUI.requestFocus();
			}
		};
		mainGame = newGameThread;
		mainGame.start();
	}

	// TODO I made the support for sound effect, get some
	public static void playSFX(String name) {
		if (soundEnabled) {
			Thread t = new Thread(sfxThreads, "SFX") {
				@SuppressWarnings("deprecation")
				@Override
				public void run() {
					Clip c = null;
					try {
						c = loader.loadSound(name);
						c.start();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (LineUnavailableException e) {
						e.printStackTrace();
					} catch (UnsupportedAudioFileException e) {
						e.printStackTrace();
					}
					while (c.isOpen()) {
						System.out.println("Testing: " + c.getFramePosition() + "/" + c.getFrameLength());
						// Stops when it reaches end also
						if (!soundEnabled || c.getFramePosition() == c.getFrameLength()) {
							c.stop();
							stop();
						}
					}
				}
			};
			t.start();
			t.setDaemon(true);
		}
	}
        

	public static void reloadMusic() {
		try {
			music = loader.loadSound("sounds/SSSSSky.wav");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		music.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public static void enterEncounter(Encounter e) {
		encounter = e;
		GUI.changeView(new EncounterGUI(encounter), true);
		GUI.changeState(State.COMBAT);
	}

	public static void gameOver() {
		GameOverWindow ggez = new GameOverWindow(GUI);

		// This VVVV is so it doesn't give me a warning
		ggez.repaint();
	}

	public static void victory() {
		System.out.println("You have killed the unicorn, GG murderer");
		map.terraform(4, 4, Terrain.PLAIN);
	}

	public static void encounterCheck() {
		if ((int) (Math.random() * 100 + 1) > 100 - ENCOUTNERCHANCE)
			enterEncounter(Encounter.createNewRandom());
	}
}

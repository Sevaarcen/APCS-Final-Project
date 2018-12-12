package base;

public class ThreadController {

	public static void resumeSound() {
		Game.reloadMusic();
		Game.music.start();
		Game.music.start();
	}
	
	public static void stopSound() {
		if(Game.music != null)
			Game.music.stop();
	}
}

package sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundManager {
    private static Clip musicClip;  // For background music
    private static Clip soundEffectClip;  // For short sound effects

    // Play background music (looping)
    public static void playMusic(String filePath) {
        try {
            stopMusic(); // Ensure previous music stops

            File musicFile = new File(filePath);
            if (!musicFile.exists()) {
                System.err.println("Music file not found: " + filePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            musicClip = (Clip) AudioSystem.getLine(info);
            musicClip.open(audioInputStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY);  // Loop music infinitely
            musicClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }

    // Stop background music
    public static void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            musicClip.close();
        }
    }

    // Play a short sound effect (one-time)
    public static void playSoundEffect(String filePath) {
        try {
            File soundFile = new File(filePath);
            if (!soundFile.exists()) {
                System.err.println("Sound effect file not found: " + filePath);
                return;
            }

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            soundEffectClip = (Clip) AudioSystem.getLine(info);
            soundEffectClip.open(audioInputStream);
            soundEffectClip.start(); // Play sound once


        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing sound effect: " + e.getMessage());
        }
    }
}

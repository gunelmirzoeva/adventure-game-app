package sounds;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SoundManager {
    private static Clip audioClip;

    public static void playSound(String filePath) {
        try {
            // Check if the file exists
            File soundFile = new File(filePath);
            if (!soundFile.exists()) {
                System.err.println("File not found: " + filePath);
                return;
            }

            // Load the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Get a data line to play the sound
            AudioFormat format = audioInputStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);

            // Open the audio clip
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioInputStream);

            // Start playing the sound
            audioClip.start();
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Unsupported audio file format: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Audio line unavailable: " + e.getMessage());
        }
    }

    public static void stopSound() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
            System.out.println("Music stopped.");
        } else {
            System.out.println("No music is playing.");
        }
    }

    public static void musicStopOrPlay() {
        System.out.println("Do you want to stop music or play? If yes music will stop playing...\n");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine().toLowerCase();
        if(answer.equalsIgnoreCase("yes")) {
            stopSound();
        }
    }
}

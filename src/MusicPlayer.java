import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class MusicPlayer {
    public static void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                System.out.println("Playing: " + filePath);
                // Wait for the clip to finish playing
                while (!clip.isRunning())
                    Thread.sleep(10);
                while (clip.isRunning())
                    Thread.sleep(10);

                clip.close();
            } else {
                System.out.println("Can't find file: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            System.out.println("Error with playing sound.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CircularLinkedList playlist = new CircularLinkedList();
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        playlist.add(currentPath + "/src/music/chillin39-20915.wav");
        playlist.add(currentPath + "/src/music/eterna-cancao-wav-12569.wav");
        playlist.add(currentPath + "/src/music/see-you-later-203103.wav");

        MusicNode currentNode = playlist.getHead();
        while (true) {  // Loop indefinitely
            playMusic(currentNode.file);
            currentNode = currentNode.next;  // Move to the next node in the list
        }
    }
}

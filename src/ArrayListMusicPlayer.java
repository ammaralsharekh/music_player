import java.nio.file.Paths;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class ArrayListMusicPlayer {
    private ArrayList<String> playlist = new ArrayList<>();

    public void addTrack(String filePath) {
        playlist.add(filePath);
    }

    public void removeTrack(String filePath) {
        playlist.remove(filePath);
    }

    public void playMusic() {
        // Loop indefinitely for continuous playback
        while (true) {
            for (String track : playlist) {
                playTrack(track);
            }
        }
    }

    private void playTrack(String filePath) {
        try {
            File musicPath = new File(filePath);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();

                System.out.println("Now Playing: " + filePath);
                // Wait for the clip to finish playing
                while (!clip.isRunning())
                    Thread.sleep(10);
                while (clip.isRunning())
                    Thread.sleep(10);

                clip.close();
            } else {
                System.out.println("File not found: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            System.out.println("Error in playing the track.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayListMusicPlayer player = new ArrayListMusicPlayer();
        String currentPath = Paths.get(".").toAbsolutePath().normalize().toString();
        // Add tracks to the playlist
        player.addTrack(currentPath + "/src/music/chillin39-20915.wav");
        player.addTrack(currentPath + "/src/music/eterna-cancao-wav-12569.wav");
        player.addTrack(currentPath + "/src/music/see-you-later-203103.wav");
        // Start playing music
        player.playMusic();
    }
}

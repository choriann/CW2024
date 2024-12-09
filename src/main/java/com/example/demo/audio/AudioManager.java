package com.example.demo.audio;

import java.util.Objects;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioManager {

    private static MediaPlayer backgroundMusicPlayer; // For BGM
    private static AudioClip soundEffectPlayer;       // For SFX
    private static double musicVolume = 0.5;          // Default music volume
    private static double sfxVolume = 0.5;            // Default SFX volume

    /**
     * Plays background music from the given file path.
     *
     * @param filePath the path to the music file
     */
    public static void playBackgroundMusic(String filePath) {
        if (backgroundMusicPlayer != null) return; // Already playing

        Media media = new Media(Objects.requireNonNull(AudioManager.class.getResource(filePath)).toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(media);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop music
        backgroundMusicPlayer.setVolume(musicVolume);
        backgroundMusicPlayer.play();
    }

    /**
     * Stops the background music if it is playing.
     */
    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer == null) return;
        backgroundMusicPlayer.stop();
        backgroundMusicPlayer = null;
    }

    /**
     * Plays a sound effect from the given file path.
     *
     * @param filePath the path to the sound effect file
     */
    public static void playSoundEffect(String filePath) {
        soundEffectPlayer = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource(filePath)).toExternalForm());
        soundEffectPlayer.setVolume(sfxVolume);
        soundEffectPlayer.play();
    }

    /**
     * Sets the volume for background music.
     *
     * @param volume the volume level (0.0 to 1.0)
     */
    public static void setMusicVolume(double volume) {
        musicVolume = volume;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
    }

    /**
     * Gets the current background music volume level.
     *
     * @return the music volume
     */
    public static double getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the volume for sound effects.
     *
     * @param volume the volume level (0.0 to 1.0)
     */
    public static void setSfxVolume(double volume) {
        sfxVolume = volume;
    }

    /**
     * Gets the current sound effect volume level.
     *
     * @return the SFX volume
     */
    public static double getSfxVolume() {
        return sfxVolume;
    }
}

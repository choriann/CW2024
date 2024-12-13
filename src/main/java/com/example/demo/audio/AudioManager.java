package com.example.demo.audio;

import java.util.Objects;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * AudioManager is a utility class that manages background music and sound effects in the game.
 * It allows playing, stopping, and adjusting the volume of audio.
 */
public class AudioManager {

    private static MediaPlayer backgroundMusicPlayer; // For background music
    private static AudioClip soundEffectPlayer;       // For sound effects
    private static double musicVolume = 0.5;          // Default background music volume
    private static double sfxVolume = 0.5;            // Default sound effect volume

    /**
     * Plays background music from the specified file path.
     * The music will loop indefinitely until stopped.
     *
     * @param filePath the path to the music file
     */
    public static void playBackgroundMusic(String filePath) {
        if (backgroundMusicPlayer != null) return; // If music is already playing, do nothing

        Media media = new Media(Objects.requireNonNull(AudioManager.class.getResource(filePath)).toExternalForm());
        backgroundMusicPlayer = new MediaPlayer(media);
        backgroundMusicPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop the music
        backgroundMusicPlayer.setVolume(musicVolume); // Set volume
        backgroundMusicPlayer.play();
    }

    /**
     * Stops the background music if it is currently playing.
     */
    public static void stopBackgroundMusic() {
        if (backgroundMusicPlayer == null) return; // If no music is playing, do nothing
        backgroundMusicPlayer.stop();
        backgroundMusicPlayer = null;
    }

    /**
     * Plays a sound effect from the specified file path.
     *
     * @param filePath the path to the sound effect file
     */
    public static void playSoundEffect(String filePath) {
        soundEffectPlayer = new AudioClip(Objects.requireNonNull(AudioManager.class.getResource(filePath)).toExternalForm());
        soundEffectPlayer.setVolume(sfxVolume); // Set volume for the sound effect
        soundEffectPlayer.play();
    }

    /**
     * Sets the volume for background music.
     *
     * @param volume the volume level, ranging from 0.0 (mute) to 1.0 (maximum)
     */
    public static void setMusicVolume(double volume) {
        musicVolume = volume;
        if (backgroundMusicPlayer != null) {
            backgroundMusicPlayer.setVolume(volume);
        }
    }

    /**
     * Retrieves the current background music volume level.
     *
     * @return the volume level of the background music
     */
    public static double getMusicVolume() {
        return musicVolume;
    }

    /**
     * Sets the volume for sound effects.
     *
     * @param volume the volume level, ranging from 0.0 (mute) to 1.0 (maximum)
     */
    public static void setSfxVolume(double volume) {
        sfxVolume = volume;
    }

    /**
     * Retrieves the current sound effect volume level.
     *
     * @return the volume level of the sound effects
     */
    public static double getSfxVolume() {
        return sfxVolume;
    }
}

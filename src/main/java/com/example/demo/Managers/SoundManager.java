package com.example.demo.Managers;

import com.example.demo.config.Config;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * Manages the playback of sound effects and background music in the application.
 * This class follows the Singleton design pattern to ensure there is only one instance of the sound manager.
 * It provides functionality for playing, pausing, muting, and unmuting both sound effects and background music.
 */
public class SoundManager {

    private static SoundManager instance; // Singleton instance
    private MediaPlayer mediaplayer; // Mediaplayer for background music
    // Sound effects
    private AudioClip shootingSound;
    private AudioClip crashSound;
    private AudioClip missilefiredSound;
    private AudioClip levelupSound;
    private AudioClip powerupSound;
    private AudioClip gameOverSound;
    private AudioClip bossSound;

    private boolean SoundMuted = false;
    private final boolean BackgroundMusicMuted = false;

    /**
     * Private constructor to initialize the sound effects and prevent instantiation.
     */
    private SoundManager() {
        initializeSoundEffects();
    }

    /**
     * Returns the background music file path for the specified level.
     *
     * @param levelNumber The level number (1-based).
     * @return The file path to the level's background music.
     */
    public String getLevelMusic(int levelNumber){
        return Config.Audio.LEVEL_MUSIC[levelNumber-1];
    }

    /**
     * Returns the background music file path for the main menu.
     *
     * @return The file path to the menu's background music.
     */
    public String getMenuMusic(){
        return Config.Audio.MENU_MUSIC;
    }

    /**
     * Retrieves the singleton instance of the SoundManager.
     *
     * @return The instance of SoundManager.
     */
    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    /**
     * Initializes the sound effects by loading the corresponding audio clips.
     */
    private void initializeSoundEffects() {
        shootingSound = loadAudioClip(Config.Audio.SHOOT_SOUND);
        crashSound = loadAudioClip(Config.Audio.CRASH_SOUND);
        if (crashSound != null) crashSound.setVolume(0.7);
        missilefiredSound = loadAudioClip(Config.Audio.MISSILE_FIRE);
        powerupSound = loadAudioClip(Config.Audio.POWER_UP);
        levelupSound = loadAudioClip(Config.Audio.LEVEL_UP);
        gameOverSound = loadAudioClip(Config.Audio.GAME_OVER_MUSIC);
        bossSound = loadAudioClip(Config.Audio.BOSS_SHOOT_SOUND);
    }

    /**
     * Plays the background music from the specified audio file.
     * The music will loop indefinitely.
     *
     * @param audioFilePath The file path of the audio file to be played.
     */
    public void playBackgroundMusic(String audioFilePath) {
        try {
            mediaplayer = createMediaPlayer(audioFilePath);
            if (mediaplayer != null) {
                mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaplayer.setVolume(BackgroundMusicMuted ? 0 : Config.Audio.VOLUME);
                if (!BackgroundMusicMuted) {
                    mediaplayer.play();
                }
            }
        } catch (Exception e) {
            System.err.println("Error playing background music: " + e.getMessage());
        }
    }

    /**
     * Stops the currently playing background music.
     */
    public void stopBackgroundMusic() {
        if (mediaplayer != null) {
            mediaplayer.stop();
            mediaplayer = null; // Clear the reference
        }
    }


    /**
     * Plays the shooting sound effect if sound effects are not muted.
     */
    public void playShootSound() {
        if (!SoundMuted && shootingSound != null) {
            shootingSound.play();
        }
    }

    /**
     * Plays the crash sound effect if sound effects are not muted.
     */
    public void playCrashSound() {
        if (!SoundMuted && crashSound != null) {
            crashSound.play();
        }
    }

    /**
     * Plays the boss sound effect if sound effects are not muted.
     */
    public void playBossSound() {
        if (!SoundMuted && bossSound != null) {
            bossSound.play();
        }
    }

    /**
     * Plays the game over sound effect if sound effects are not muted.
     */
    public void playGameOverSound() {
        if (!SoundMuted && gameOverSound != null) {
            gameOverSound.play();
        }
    }

    /**
     * Plays the level-up sound effect if sound effects are not muted.
     */
    public void playLevelUpSound() {
        if (!SoundMuted && levelupSound != null) {
            levelupSound.play();
        }
    }

    /**
     * Plays the missile fired sound effect if sound effects are not muted.
     */
    public void playMissileFiredSound() {
        if (!SoundMuted && missilefiredSound != null) {
            missilefiredSound.play();
        }
    }

    /**
     * Plays the power-up sound effect if sound effects are not muted.
     */
    public void playPowerupSound() {
        if (!SoundMuted && powerupSound != null) {
            powerupSound.play();
        }
    }

    /**
     * Loads an audio clip from the specified file path.
     *
     * @param filePath The file path of the audio file.
     * @return The loaded AudioClip, or null if loading fails.
     */
    private AudioClip loadAudioClip(String filePath) {
        try {
            URL resource = getClass().getResource(filePath);
            if (resource == null) {
                throw new IllegalArgumentException("Audio file not found: " + filePath);
            }
            AudioClip audioClip = new AudioClip(resource.toExternalForm());
            audioClip.setVolume(Config.Audio.VOLUME);
            return audioClip;
        } catch (Exception e) {
            System.err.println("Error loading audio: " + filePath + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Creates a MediaPlayer for playing a background music file.
     *
     * @param filePath The file path of the music file.
     * @return A MediaPlayer instance or null if creation fails.
     */
    private MediaPlayer createMediaPlayer(String filePath) {
        try {
            URL resource = getClass().getResource(filePath);
            if (resource == null) {
                throw new IllegalArgumentException("Audio file not found: " + filePath);
            }
            Media media = new Media(resource.toExternalForm());
            return new MediaPlayer(media);
        } catch (Exception e) {
            System.err.println("Error creating Mediaplayer: " + filePath + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Mutes all sound effects.
     */
    public void muteSoundEffects() {
        SoundMuted = true;
    }

    /**
     * Unmutes all sound effects.
     */
    public void unmuteSoundEffects() {
        SoundMuted = false;
    }

    /**
     * Returns whether the background music is muted.
     *
     * @return true if the background music is muted, false otherwise.
     */
    public boolean isBackgroundMusicMuted() {
        return BackgroundMusicMuted;
    }

    /**
     * Returns whether the sound effects are muted.
     *
     * @return true if the sound effects are muted, false otherwise.
     */
    public boolean isSoundEffectsMuted() {
        return SoundMuted;
    }
}

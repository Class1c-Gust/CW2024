package com.example.demo.Managers;

import com.example.demo.config.Config;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundManager {

    private static SoundManager instance; // Singleton instance
    private MediaPlayer mediaplayer;
    private AudioClip shootingSound;
    private AudioClip crashSound;
    private AudioClip missilefiredSound;
    private AudioClip levelupSound;
    private AudioClip powerupSound;
    private AudioClip gameOverSound;
    private AudioClip wonGameSound;
    private AudioClip bossSound;

    private boolean SoundMuted = false;
    private boolean BackgroundMusicMuted = false;


    private SoundManager() {
        initializeSoundEffects();
    }

    public String getLevelMusic(int levelNumber){
        return Config.Audio.LEVEL_MUSIC[levelNumber-1];
    }

    public String getMenuMusic(){
        return Config.Audio.MENU_MUSIC;
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private void initializeSoundEffects() {
        shootingSound = loadAudioClip(Config.Audio.SHOOT_SOUND);
        crashSound = loadAudioClip(Config.Audio.CRASH_SOUND);
        crashSound.setVolume(0.7);
        missilefiredSound = loadAudioClip(Config.Audio.MISSILE_FIRE);
        powerupSound = loadAudioClip(Config.Audio.POWER_UP);
        levelupSound = loadAudioClip(Config.Audio.LEVEL_UP);
        gameOverSound = loadAudioClip(Config.Audio.GAME_OVER_MUSIC);
        bossSound = loadAudioClip(Config.Audio.BOSS_SHOOT_SOUND);

    }

    public void playBackgroundMusic(String audioFilePath) {
        try {
//            stopBackgroundMusic(); //
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

    public void stopBackgroundMusic() {
        if (mediaplayer != null) {
            mediaplayer.stop();
            mediaplayer = null; // Clear the reference
        }
    }

    public void pauseBackgroundMusic() {
        if (mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaplayer.pause();
        }
    }

    public void resumeBackgroundMusic() {
        if (mediaplayer != null && mediaplayer.getStatus() == MediaPlayer.Status.PAUSED) {
            mediaplayer.play();
        }
    }

    public void playShootSound() {
        if (!SoundMuted && shootingSound != null) {
            shootingSound.play();
        }
    }

    public void playCrashSound() {
        if (!SoundMuted && crashSound != null) {
            crashSound.play();
        }
    }

    public void playBossSound() {
        if (!SoundMuted && bossSound != null) {
            bossSound.play();
        }
    }


    public void playGameOverSound() {
        if (!SoundMuted && gameOverSound != null) {
            gameOverSound.play();
        }
    }

    public void playLevelUpSound() {
        if (!SoundMuted && levelupSound != null) {
            levelupSound.play();
        }
    }

    public void playMissileFiredSound() {
        if (!SoundMuted && missilefiredSound != null) {
            missilefiredSound.play();
        }
    }

    public void playPowerupSound() {
        if (!SoundMuted && powerupSound != null) {
            powerupSound.play();
        }
    }

//    public static final String LEVEL_UP = "src/main/resources/Audio/levelup.mp3";
//    public static final String MISSILE_FIRE = "src/main/resources/Audio/missilefire.mp3";
//    public static final String MISSILE_IMPACT = "src/main/resources/Audio/missileImpact.mp3";
//    public static final String POWER_UP = "src/main/resources/Audio/powerup.mp3";

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

    public void muteBackgroundMusic() {
        BackgroundMusicMuted = true;
        if (mediaplayer != null) {
            mediaplayer.setVolume(0);
        }
    }

    public void unmuteBackgroundMusic(String currentMusicFile) {
        BackgroundMusicMuted = false;
        if (mediaplayer != null) {
            mediaplayer.setVolume(Config.Audio.VOLUME);
            if (mediaplayer.getStatus() != MediaPlayer.Status.PLAYING) {
                mediaplayer.play();
            }
        } else {
            playBackgroundMusic(currentMusicFile); // Ensure music starts if not already playing
        }
    }


    public void muteSoundEffects() {
        SoundMuted = true;
    }

    public void unmuteSoundEffects() {
        SoundMuted = false;
    }

    public boolean isBackgroundMusicMuted() {
        return BackgroundMusicMuted;
    }

    public boolean isSoundEffectsMuted() {
        return SoundMuted;
    }
}
package com.example.demo.Managers;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

public class SoundManager {
    // Audio file paths
    public static final String MENU_MUSIC = "/Audio/menu.mp3";
    public static final String LEVEL_ONE_MUSIC = "/Audio/level1music.mp3";
    public static final String LEVEL_TWO_MUSIC = "/Audio/level2music.mp3";
    public static final String LEVEL_THREE_MUSIC = "/Audio/level3music.mp3";
    public static final String LEVEL_FOUR_MUSIC = "/Audio/level4music.mp3";
    public static final String LEVEL_FIVE_MUSIC = "/Audio/level5music.mp3";
    public static final String LEVEL_SIX_MUSIC = "/Audio/level6music.mp3";
    public static final String LEVEL_SEVEN_MUSIC = "/Audio/level7music.mp3";
    public static final String LEVEL_EIGHT_MUSIC = "/Audio/level8music.mp3";
    public static final String LEVEL_NINE_MUSIC = "/Audio/level9music.mp3";
    public static final String GAME_OVER_MUSIC = "/Audio/gameOver.mp3";
    public static final String WIN_GAME_MUSIC = "/Audio/level9music.mp3";
    public static final String SHOOT_SOUND = "/Audio/gunshot.mp3";
    public static final String CRASH_SOUND = "/Audio/healthDecrease.mp3";
    public static final String LEVEL_UP = "/Audio/levelup.mp3";
    public static final String MISSILE_FIRE = "/Audio/missilefire.mp3";
    public static final String POWER_UP = "/Audio/powerup.mp3";
    public static final String KILL = "/Audio/kill.mp3";

    private static SoundManager instance; // Singleton instance
    private MediaPlayer mediaplayer;
    private AudioClip shootingSound;
    private AudioClip crashSound;
    private AudioClip missilefiredSound;
    private AudioClip levelupSound;
    private AudioClip powerupSound;
    private AudioClip killSound;
    private AudioClip gameOverSound;
    private AudioClip wonGameSound;

    private static final double VOLUME = 0.2;
    private boolean SoundMuted = false;
    private boolean BackgroundMusicMuted = false;

    public static final String[] LEVEL_MUSIC = {
            LEVEL_ONE_MUSIC, LEVEL_TWO_MUSIC, LEVEL_THREE_MUSIC, LEVEL_FOUR_MUSIC, LEVEL_FIVE_MUSIC,
            LEVEL_SIX_MUSIC, LEVEL_SEVEN_MUSIC, LEVEL_EIGHT_MUSIC, LEVEL_NINE_MUSIC
    };

    private SoundManager() {
        initializeSoundEffects();
    }

    public String getLevelMusic(int levelNumber){
        return SoundManager.LEVEL_MUSIC[levelNumber-1];
    }

    public String getMenuMusic(){
        return SoundManager.MENU_MUSIC;
    }

    public static SoundManager getInstance() {
        if (instance == null) {
            instance = new SoundManager();
        }
        return instance;
    }

    private void initializeSoundEffects() {
        shootingSound = loadAudioClip(SHOOT_SOUND);
        crashSound = loadAudioClip(CRASH_SOUND);
        crashSound.setVolume(0.7);
        missilefiredSound = loadAudioClip(MISSILE_FIRE);
        powerupSound = loadAudioClip(POWER_UP);
        levelupSound = loadAudioClip(LEVEL_UP);
        gameOverSound = loadAudioClip(GAME_OVER_MUSIC);
        wonGameSound = loadAudioClip(WIN_GAME_MUSIC);

    }

    public void playBackgroundMusic(String audioFilePath) {
        try {
//            stopBackgroundMusic(); //
            mediaplayer = createMediaPlayer(audioFilePath);
            if (mediaplayer != null) {
                mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaplayer.setVolume(BackgroundMusicMuted ? 0 : VOLUME);
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

    public void playkillSound() {
        if (!SoundMuted && killSound != null) {
            killSound.play();
        }
    }

    public void playWinSound() {
        if (!SoundMuted && wonGameSound != null) {
            wonGameSound.play();
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
            audioClip.setVolume(VOLUME);
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
            mediaplayer.setVolume(VOLUME);
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
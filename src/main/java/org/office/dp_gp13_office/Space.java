package org.office.dp_gp13_office;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;


public class Space extends StackPane {
    OfficeSpace office;
    // Media bgmusic;
    List<Media> bgMusicList;
    MediaPlayer player;
    private MediaView tvView;
    private MediaPlayer tvPlayer;
    private List<Media> videoFiles;
    private int currentVideoIndex = 0;
    int currentMusicIndex = 0;
    private BackgroundChangeStrategy backgroundChangeStrategy;


    // Lighting
    private LightingManager lightingManager;
    private LightingCommand lightOnCommand;
    private LightingCommand lightOffCommand;
    private LightingCommand setBrightnessCommand;
    private boolean isLightOn = false;
    private int brightness = 0;


    private String status = "STOPPED";


    public Space(OfficeSpace office) {
        bgMusicList = new ArrayList<>();
        bgMusicList.add(new Media(getClass().getResource("bgmusic/bgmusic1.mp3").toExternalForm()));
        bgMusicList.add(new Media(getClass().getResource("bgmusic/bgmusic2.mp3").toExternalForm()));
        bgMusicList.add(new Media(getClass().getResource("bgmusic/bgmusic3.mp3").toExternalForm()));
        bgMusicList.add(new Media(getClass().getResource("bgmusic/bgmusic4.mp3").toExternalForm()));
        bgMusicList.add(new Media(getClass().getResource("bgmusic/bgmusic5.mp3").toExternalForm()));

        player = new MediaPlayer(bgMusicList.get(currentMusicIndex));
        player.setAutoPlay(false);

        this.office = office;
        this.backgroundChangeStrategy = new DefaultOfficeBackgroundStrategy();
        this.initializeBackground();


        // Initialize LightingManager and commands
        this.lightingManager = LightingManager.getInstance();
        this.lightOnCommand = new LightOnCommand();
        this.lightOffCommand = new LightOffCommand();
        this.setBrightnessCommand = new SetBrightnessCommand(0); // Set default brightness
    

    

        this.tvView = new MediaView();
        this.videoFiles = new ArrayList<>();
        videoFiles.add(new Media(getClass().getResource("tv/Command.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/coffee.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/cookie.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/food.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/travel.mp4").toExternalForm()));

        tvPlayer = new MediaPlayer(videoFiles.get(currentVideoIndex));
        tvView.setMediaPlayer(tvPlayer);
        tvPlayer.setAutoPlay(false);
        tvView.setFitWidth(320);
        tvView.setFitHeight(100);
        tvView.setTranslateX(-300);
        tvView.setTranslateY(-78);

        // Add the MediaView to the Space
        this.getChildren().add(tvView);
        }


public void increaseBrightnessCommand() {
        updateLightingState();
        lightingManager.executeCommand(setBrightnessCommand);
    }

    public void decreaseBrightnessCommand() {
//        lightingManager.decreaseBrightness();
        lightingManager.undoLastCommand();
    }

    public void turnOnLight() {
        isLightOn = true;
        updateLightingState();
        lightingManager.executeCommand(lightOnCommand);
    }

    public void turnOffLight() {
        isLightOn = false;
        updateLightingState();
        lightingManager.executeCommand(lightOffCommand);
    }

    public void updateLightingState() {
        if (isLightOn) {

                // Apply a faint glow on the top side when brightness is 0
                double glowIntensity = 0.01; // Adjust the intensity of the glow

                Glow glowEffect = new Glow(glowIntensity);

                // Create a Rectangle representing the top portion with a gradient fill
                double topPortionHeight = getHeight() / 5; // Adjust the height of the top portion
                Rectangle topPortion = new Rectangle(0, 0, getWidth(), topPortionHeight);

                topPortion.setFill(new javafx.scene.paint.LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                        new javafx.scene.paint.Stop(0.01, Color.YELLOW), new javafx.scene.paint.Stop(1, Color.TRANSPARENT)));

                // Apply Glow effect directly to the top portion
                topPortion.setEffect(glowEffect);

                // Add the top portion to the StackPane and align it to the top
                getChildren().add(topPortion);
                setAlignment(topPortion, Pos.TOP_CENTER);

                // Clear any effects when lights are off
                setEffect(null);

        } else {
            // Clear any effects when lights are off
            getChildren().clear();
            setEffect(null);
        }
    }



    public void initializeBackground() {
        // Default background initialization
        Image image = new Image(App.class.getResource("images/office.png").toExternalForm());

        BackgroundImage backgroundImage = new BackgroundImage(
                image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));

        this.setBackground(new Background(backgroundImage));
    }

    private BackgroundChangeStrategy[] backgroundStrategies = {
            new DefaultOfficeBackgroundStrategy(),
            new ModernOfficeBackgroundStrategy(),
            new TraditionalOfficeBackgroundStrategy()
    };

    private int currentStrategyIndex = 0;

    public void changeBackground() {
        backgroundStrategies[currentStrategyIndex].changeBackground(this);
        // Move to the next strategy in a loop
        currentStrategyIndex = (currentStrategyIndex + 1) % backgroundStrategies.length;
    }

    // Add this method to set a new strategy
    public void setBackgroundChangeStrategy(BackgroundChangeStrategy strategy) {
        this.backgroundChangeStrategy = strategy;
    }

    public boolean toggleMusic() {
        if (!this.player.getStatus().equals(Status.PLAYING)) {
            executeCommand(new BGMusicToggle(player));
        } else {
            undoCommand(new BGMusicToggle(player));
        }
        return this.player.getStatus().equals(Status.PLAYING);
    }

    public void playNextMusic() {
        executeCommand(new NextMusicToggle(this));
    }

    public void playPreviousMusic() {
        undoCommand(new NextMusicToggle(this));
    }

    public boolean toggleTV() {
        if (!this.status.equals("PLAYING")) {
            executeCommand(new TVTurnOnCommand(this));
            this.status = "PLAYING";
        } else {
            undoCommand(new TVTurnOnCommand(this));
            this.status = "STOPPED";
        }
        return this.status.equals("PLAYING");
    }

    public void playNextVideo() {
        executeCommand(new TVPlayNextCommand(this));
    }

    public void playPreviousVideo() {
        undoCommand(new TVPlayNextCommand(this));
    }

    public void pauseVideo() {
        executeCommand(new TVPauseCommand(this));
    }

    public void resumeVideo() {
        undoCommand(new TVPauseCommand(this));
    }

    public void executeCommand(Command cmd) {
        cmd.execute();
    }

    public void undoCommand(Command cmd) {
        cmd.undo();
    }

    public void setMediaPlayer(MediaPlayer player) {
        this.player = player;
    }

    public MediaPlayer getMediaPlayer() {
        return this.player;
    }

    public int getMusicIndex() {
        return currentMusicIndex;
    }

    public void setMusicIndex(int musicIndex) {
        this.currentMusicIndex = musicIndex;
    }

    public List<Media> getBgMusicList() {
        return bgMusicList;
    }

    public int getVideoIndex() {
        return currentVideoIndex;
    }

    public void setVideoIndex(int videoIndex) {
        this.currentVideoIndex = videoIndex;
    }

    public List<Media> getVideoList() {
        return videoFiles;
    }

    public void setTVPlayer(MediaPlayer tvPlayer) {
        this.tvPlayer = tvPlayer;
    }

    public void setTVView(MediaView tvView) {
        this.tvView = tvView;
    }

    public MediaPlayer getTVPlayer() {
        return this.tvPlayer;
    }

    public MediaView getTVView() {
        return this.tvView;
    }

    public void stop() {
        office.stopOfficeSpace();
    }


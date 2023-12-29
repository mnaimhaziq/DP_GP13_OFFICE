package org.office.dp_gp13_office;

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

        this.tvView = new MediaView();
        this.videoFiles = new ArrayList<>();
        videoFiles.add(new Media(getClass().getResource("tv/Command.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/Facade.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/FactoryMethod.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/Singleton.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/Strategy.mp4").toExternalForm()));

        tvPlayer = new MediaPlayer(videoFiles.get(currentVideoIndex));
        tvView.setMediaPlayer(tvPlayer);
        tvPlayer.setAutoPlay(false);
        tvView.setFitWidth(350);
        tvView.setFitHeight(130);
        tvView.setTranslateX(-373);
        tvView.setTranslateY(-105);

        // Add the MediaView to the Space
        this.getChildren().add(tvView);

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
        this.status = "PLAYING";
    }

    public void playPreviousVideo() {
        undoCommand(new TVPlayNextCommand(this));
        this.status = "PLAYING";
    }

    public void pauseVideo() {
        executeCommand(new TVPauseCommand(this));
        this.status = "PLAYING";
    }

    public void resumeVideo() {
        undoCommand(new TVPauseCommand(this));
        this.status = "PLAYING";
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

}

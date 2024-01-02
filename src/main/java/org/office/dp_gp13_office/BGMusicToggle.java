package org.office.dp_gp13_office;

import javafx.scene.media.MediaPlayer;

public class BGMusicToggle implements Command{
    MediaPlayer mediaPlayer;

    public BGMusicToggle(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void execute() {
        mediaPlayer.play();
    }

    @Override
    public void undo() {
        mediaPlayer.pause();
    }
}

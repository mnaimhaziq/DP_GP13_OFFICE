package org.office.dp_gp13_office;

import javafx.scene.media.MediaPlayer;

public class TVTurnOnCommand implements Command {
    Space space;

    public TVTurnOnCommand(Space space) {
        this.space = space;
    }

    @Override
    public void execute() {

        // Stop previous player and start the new video
        space.getTVPlayer().stop();
        space.setTVPlayer(new MediaPlayer(space.getVideoList().get(space.getVideoIndex())));
        space.getTVView().setMediaPlayer(space.getTVPlayer());
        space.getTVView().setVisible(true);
        space.getTVPlayer().play();
    }

    @Override
    public void undo() {
        space.getTVView().setVisible(false);
        // space.setVideoIndex(0);
        // space.setTVPlayer(new MediaPlayer(space.getVideoList().get(space.getVideoIndex())));
        // space.getTVView().setMediaPlayer(space.getTVPlayer());
        space.getTVPlayer().stop();
    }
}
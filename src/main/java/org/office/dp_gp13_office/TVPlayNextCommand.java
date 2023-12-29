package org.office.dp_gp13_office;

import javafx.scene.media.MediaPlayer;

public class TVPlayNextCommand implements Command {
    Space space;

    public TVPlayNextCommand(Space space) {
        this.space = space;
    }

    @Override
    public void execute() {
        int currentVideoIndex = space.getVideoIndex();
        if (space.getVideoIndex() < space.getVideoList().size() - 1) {
            currentVideoIndex++;
            space.setVideoIndex(currentVideoIndex);
            space.getTVPlayer().stop();
            space.setTVPlayer(new MediaPlayer(space.getVideoList().get(space.getVideoIndex())));
            space.getTVView().setMediaPlayer(space.getTVPlayer());
            space.getTVPlayer().play();
        }
    }

    @Override
    public void undo() {
        int currentVideoIndex = space.getVideoIndex();
        if (space.getVideoIndex() > 0) {
            currentVideoIndex--;
            space.setVideoIndex(currentVideoIndex);
            space.getTVPlayer().stop();
            space.setTVPlayer(new MediaPlayer(space.getVideoList().get(space.getVideoIndex())));
            space.getTVView().setMediaPlayer(space.getTVPlayer());
            space.getTVPlayer().play();
        }
    }
}

package org.office.dp_gp13_office;

public class BGMusicToggle implements Command{
    Space space;

    public BGMusicToggle(Space space) {
        this.space = space;
    }

    @Override
    public void execute() {
        space.getMediaPlayer().play();
    }

    @Override
    public void undo() {
        space.getMediaPlayer().pause();
    }
}

package org.office.dp_gp13_office;
import javafx.scene.media.MediaPlayer;

public class NextMusicToggle implements Command {
    Space space;
   
    public NextMusicToggle(Space space) {
        this.space = space;
    }

    @Override
    public void execute() {     
        space.getMediaPlayer().stop();
        int currentMusicIndex = space.getMusicIndex();
        currentMusicIndex = (currentMusicIndex + 1)  % space.getBgMusicList().size();
        space.setMusicIndex(currentMusicIndex);
        space.setMediaPlayer(new MediaPlayer(space.getBgMusicList().get(currentMusicIndex)));
        space.getMediaPlayer().play();
    }

    @Override
    public void undo() {
        space.getMediaPlayer().stop();
        int currentMusicIndex = space.getMusicIndex();

        if (currentMusicIndex == 0) {
            currentMusicIndex = space.getBgMusicList().size() - 1;
        } else {
            currentMusicIndex = (currentMusicIndex - 1) % space.getBgMusicList().size();
        }

        space.setMusicIndex(currentMusicIndex);
        space.setMediaPlayer(new MediaPlayer(space.getBgMusicList().get(currentMusicIndex)));
        space.getMediaPlayer().play();
    }
}

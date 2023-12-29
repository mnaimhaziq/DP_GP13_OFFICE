package org.office.dp_gp13_office;

public class TVPauseCommand implements Command {
    Space space;

    public TVPauseCommand(Space space) {
        this.space = space;
    }

    @Override
    public void execute() {
        space.getTVPlayer().pause();
    }

    @Override
    public void undo() {
        space.getTVPlayer().play();
    }
}

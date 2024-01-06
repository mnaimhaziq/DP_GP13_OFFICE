package org.office.dp_gp13_office;

public class ToggleEntityVisibility implements Command {
    Toggle toggle;

    public ToggleEntityVisibility(Toggle toggle) {
        this.toggle = toggle;
    }

    @Override
    public void execute() {
        toggle.toggleEntity();
    }

    @Override
    public void undo() {
        toggle.untoggleEntity();
    }
}

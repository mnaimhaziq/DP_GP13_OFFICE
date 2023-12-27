package org.office.dp_gp13_office;

public interface EntityFactory {
    public abstract Entity createPeopleEntity();
    public abstract Entity createFileEntity();
    public abstract Entity createVaseEntity();
    public abstract Entity createFileCabinetEntity();
    public abstract Entity createTableLampEntity();
    public abstract Entity createPictureEntity();
}

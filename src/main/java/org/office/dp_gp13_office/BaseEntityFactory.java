package org.office.dp_gp13_office;

public class BaseEntityFactory implements EntityFactory {
    @Override
    public Entity createPeopleEntity(OfficeSpace office, Space space, int peopleRandom, int countTom, int countJohn, int countBen, int countAnne, int countSelina) {
        return new BasePeople(office, space, peopleRandom, countTom, countJohn, countBen, countAnne, countSelina);
    }

    @Override
    public Entity createFileEntity() {
        return new BaseFile();
    }

    @Override
    public Entity createVaseEntity() {
        return new BaseVase();
    }

    @Override
    public Entity createFileCabinetEntity() {
        return new BaseFileCabinet();
    }

    @Override
    public Entity createTableLampEntity() {
        return new BaseTableLamp();
    }

    @Override
    public Entity createPictureEntity() {
        return new BasePicture();
    }
}

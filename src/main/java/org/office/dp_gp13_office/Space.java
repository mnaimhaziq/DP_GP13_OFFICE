package org.office.dp_gp13_office;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Space extends StackPane {
    OfficeSpace office;
    private List<Media> bgMusicList;
    private MediaPlayer player;
    private MediaView tvView;
    private MediaPlayer tvPlayer;
    private List<Media> videoFiles;
    private int currentVideoIndex = 0;
    private int currentMusicIndex = 0;
    private ArrayList<EntityImageViewAdapter> entities = new ArrayList<EntityImageViewAdapter>();
    private ArrayList<ImageView> badges = new ArrayList<ImageView>();
    Popup badgeListPopup = new Popup();


    // Lighting
    private LightingManager lightingManager;
    private LightingCommand lightOnCommand;
    private LightingCommand lightOffCommand;
    private LightingCommand setBrightnessCommand;
    private boolean isLightOn = false;

    // People Entities
    private EntityImageViewAdapter person;
    int countTom = 0;
    int countJohn = 0;
    int countBen = 0;
    int countAnne = 0;
    int countSelina = 0;



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
        this.initializeBackground();
        this.initializeEntities();
        this.initializeBadges();
        this.initializeBadgeListPopup();

        // Initialize LightingManager and commands
        this.lightingManager = LightingManager.getInstance();
        this.lightOnCommand = new LightOnCommand();
        this.lightOffCommand = new LightOffCommand();
        this.setBrightnessCommand = new SetBrightnessCommand(0); // Set default brightness


        this.tvView = new MediaView();
        this.videoFiles = new ArrayList<>();
        videoFiles.add(new Media(getClass().getResource("tv/Command.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/coffee.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/cookie.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/food.mp4").toExternalForm()));
        videoFiles.add(new Media(getClass().getResource("tv/travel.mp4").toExternalForm()));

        tvPlayer = new MediaPlayer(videoFiles.get(currentVideoIndex));
        tvView.setMediaPlayer(tvPlayer);
        tvPlayer.setAutoPlay(false);
        tvView.setFitWidth(320);
        tvView.setFitHeight(100);
        tvView.setTranslateX(-300);
        tvView.setTranslateY(-78);
        tvView.setVisible(false);

        // Add the MediaView to the Space
        this.getChildren().add(tvView);
    }


    public void increaseBrightnessCommand() {
        updateLightingState();
        lightingManager.executeCommand(setBrightnessCommand);
    }

    public void decreaseBrightnessCommand() {
//        lightingManager.decreaseBrightness();
        lightingManager.undoLastCommand();
    }

    public void turnOnLight() {
        isLightOn = true;
        updateLightingState();
        lightingManager.executeCommand(lightOnCommand);
    }

    public void turnOffLight() {
        isLightOn = false;
        updateLightingState();
        lightingManager.executeCommand(lightOffCommand);
    }

    private Rectangle topPortion; // Declare the Rectangle as a class-level variable

    public void updateLightingState() {
        if (isLightOn) {
            // Apply a faint glow on the top side when brightness is 0
            double glowIntensity = 0.01; // Adjust the intensity of the glow

            Glow glowEffect = new Glow(glowIntensity);

            // Create a Rectangle representing the top portion with a gradient fill
            double topPortionHeight = getHeight() / 5; // Adjust the height of the top portion
            topPortion = new Rectangle(0, 0, getWidth(), topPortionHeight);

            topPortion.setFill(new javafx.scene.paint.LinearGradient(0, 0, 0, 1, true, javafx.scene.paint.CycleMethod.NO_CYCLE,
                    new javafx.scene.paint.Stop(0.01, Color.YELLOW), new javafx.scene.paint.Stop(1, Color.TRANSPARENT)));

            // Apply Glow effect directly to the top portion
            topPortion.setEffect(glowEffect);

            // Add the top portion to the StackPane and align it to the top
            getChildren().add(topPortion);
            setAlignment(topPortion, Pos.TOP_CENTER);

        } else {
            // Clear the top portion when lights are off
            getChildren().removeIf(node -> node instanceof Rectangle);
        }
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

    public void initializeEntities() {
        this.initializeEntities(new BaseEntityFactory());
    }

    public void initializeEntities(EntityFactory entityFactory) {
        EntityImageViewAdapter file = new EntityImageViewAdapter(entityFactory.createFileEntity()); // index 0
        EntityImageViewAdapter fileCabinet = new EntityImageViewAdapter(entityFactory.createFileCabinetEntity()); // index 1
        EntityImageViewAdapter picture = new EntityImageViewAdapter(entityFactory.createPictureEntity()); // index 2
        EntityImageViewAdapter tableLamp = new EntityImageViewAdapter(entityFactory.createTableLampEntity()); // index 3
        EntityImageViewAdapter vase = new EntityImageViewAdapter(entityFactory.createVaseEntity()); // index 4

        file.setTranslateX(250);
        file.setTranslateY(-37);
        file.setFitHeight(80);
        file.setFitWidth(80);
        fileCabinet.setTranslateX(620);
        fileCabinet.setTranslateY(150);
        fileCabinet.setFitHeight(200);
        fileCabinet.setFitWidth(250);
        picture.setTranslateX(550);
        picture.setTranslateY(-40);
        picture.setFitHeight(80);
        picture.setFitWidth(80);
        tableLamp.setTranslateX(380);
        tableLamp.setTranslateY(-20);
        tableLamp.setFitHeight(150);
        tableLamp.setFitWidth(150);
        vase.setTranslateX(-100);
        vase.setTranslateY(10);
        vase.setFitHeight(90);
        vase.setFitWidth(90);

        this.entities.add(file);
        this.entities.add(fileCabinet);
        this.entities.add(picture);
        this.entities.add(tableLamp);
        this.entities.add(vase);

        entities.forEach((entity) -> entity.setVisible(false));

        // Here got error resulting the control bar disappear
        this.getChildren().addAll(entities);
    }

    public void initializeBadges() {
        Image tom = new Image(App.class.getResourceAsStream("images/tom-badge.png"));
        ImageView tomView = new ImageView(tom);
        tomView.setFitHeight(150);
        tomView.setFitWidth(150);

        Image john = new Image(App.class.getResourceAsStream("images/john-badge.png"));
        ImageView johnView = new ImageView(john);
        johnView.setFitHeight(150);
        johnView.setFitWidth(150);

        Image ben = new Image(App.class.getResourceAsStream("images/ben-badge.png"));
        ImageView benView = new ImageView(ben);
        benView.setFitHeight(150);
        benView.setFitWidth(150);

        Image anne = new Image(App.class.getResourceAsStream("images/anne-badge.png"));
        ImageView anneView = new ImageView(anne);
        anneView.setFitHeight(150);
        anneView.setFitWidth(150);

        Image selina = new Image(App.class.getResourceAsStream("images/selina-badge.png"));
        ImageView selinaView = new ImageView(selina);
        selinaView.setFitHeight(150);
        selinaView.setFitWidth(150);

        this.badges.add(tomView);
        this.badges.add(johnView);
        this.badges.add(benView);
        this.badges.add(anneView);
        this.badges.add(selinaView);

        badges.forEach((badge) -> badge.setVisible(false));
    }

    public void initializeBadgeListPopup() {
        Label label = new Label();
        label.setStyle("-fx-font-size:25");
        label.setText("Badges List");
        label.setAlignment(Pos.CENTER);

        GridPane badgeList = new GridPane();
        badgeList.addRow(0, label);
        badgeList.addRow(1, badges.get(0), badges.get(1), badges.get(2));
        badgeList.addRow(2, badges.get(3), badges.get(4));
        badgeList.setBackground(
                new Background(new BackgroundFill(Color.web("#C2C5CC"), CornerRadii.EMPTY, Insets.EMPTY)));
        badgeList.setPadding(new Insets(5));
        badgeList.setHgap(10);
        badgeList.setVgap(5);
        badgeList.setAlignment(Pos.CENTER);

        badgeListPopup.getContent().add(badgeList);
    }

    public boolean toggleBadgeListPopup() {
        if (!badgeListPopup.isShowing()) {
            badgeListPopup.show(office.getStage());
        } else {
            badgeListPopup.hide();
        }
        return badgeListPopup.isShowing();
    }

    public boolean toggleEntity(Button button, int index, String entityName) {
        var entity = entities.get(index);
        if (!entity.isVisible()) {
            entity.setVisible(true);
            button.setText("Remove " + entityName);
        } else {
            entity.setVisible(false);
            button.setText("Add " + entityName);
        }
        return entity.isVisible();
    }

    public void addPeople() {
        this.addPeople(new BaseEntityFactory());
    }

    public void addPeople(EntityFactory entityFactory) {
        Random random = new Random();
        int peopleRandom = random.nextInt(5);
        switch(peopleRandom) {
            case 0:
                this.countTom++;
                break;
            case 1:
                this.countJohn++;
                break;
            case 2:
                this.countBen++;
                break;
            case 3:
                this.countAnne++;
                break;
            case 4:
                this.countSelina++;
                break;
        }
        person = new EntityImageViewAdapter(entityFactory.createPeopleEntity(office, this, peopleRandom, this.countTom, this.countJohn, this.countBen, this.countAnne, this.countSelina));
        person.setTranslateX(-430);
        person.setTranslateY(120);
        person.setFitHeight(350);
        person.setFitWidth(150);
        this.getChildren().add(person);
    }

    public void removePeople() {
        this.getChildren().remove(person);
        person = null;
    }

    public void togglePeople(Button button) {
        if (person == null) {
            addPeople();
            button.setText("Remove People");
        } else {
            removePeople();
            button.setText("Generate People");
        }
    }

    private int currentStrategyIndex = 0;

    public void changeBackground() {
        backgroundStrategies[currentStrategyIndex].changeBackground(this);
        // Move to the next strategy in a loop
        currentStrategyIndex = (currentStrategyIndex + 1) % backgroundStrategies.length;
    }

    public ArrayList<ImageView> getBadgeList() {
        return badges;
    }

 
    public boolean toggleMusic() {
        if (!this.player.getStatus().equals(Status.PLAYING)) {
            executeCommand(new BGMusicToggle(this));
        } else {
            undoCommand(new BGMusicToggle(this));
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
    }

    public void playPreviousVideo() {
        undoCommand(new TVPlayNextCommand(this));
    }

    public void pauseVideo() {
        executeCommand(new TVPauseCommand(this));
    }

    public void resumeVideo() {
        undoCommand(new TVPauseCommand(this));
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
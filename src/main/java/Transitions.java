import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class Transitions {
    public boolean defaultLook = true;
    public ImageView mainMenuKeno;
    public Button mainMenuNext;
    public Button betNext;
    public Button drawNext;
    public ImageView endKeno;
    // Images
    public final Image kenoMango = new Image("mango_fruit.gif", true);
//    public final Image kenoGrape = new Image("grape_fruit.png", true);
    public final Image nextMango = new Image("nextMango.png");
    public final Image nextGrape = new Image("nextGrape.png");
    public final Image nextGray = new Image("nextGray.png");

    public Transitions() {
        createMainMenuKeno();
        createMainMenuNext();
        createBetNext();
        createDrawNext();
    }

    public void createMainMenuKeno() {
        Image keno = new Image("kenoMango.png");
        this.mainMenuKeno = new ImageView();
        this.mainMenuKeno.setImage(keno);
        this.mainMenuKeno.setFitWidth(300);
        this.mainMenuKeno.setPreserveRatio(true);

        this.endKeno = new ImageView();
        this.endKeno.setImage(keno);
        this.endKeno.setFitWidth(300);
        this.endKeno.setPreserveRatio(true);
    }

    public void createMainMenuNext() {
        ImageView playView = new ImageView(nextMango);
        playView.setFitWidth(80);
        playView.setFitHeight(80);
        playView.setPreserveRatio(true);
        // Makes the orange next button become a button to be clicked
        this.mainMenuNext = new Button();
        this.mainMenuNext.setGraphic(playView);
        this.mainMenuNext.setStyle("-fx-background-color: transparent;");
    }

    public void createBetNext() {
        ImageView betView = new ImageView(nextMango);
        betView.setFitWidth(40);
        betView.setFitHeight(40);
        betView.setPreserveRatio(true);
        this.betNext = new Button();
        this.betNext.setGraphic(betView);
        this.betNext.setStyle("-fx-background-color: transparent;");
    }

    public void allowBetNext(boolean betsReady, boolean gridReady) {
        this.betNext.setDisable(!betsReady || !gridReady);
    }

    public void createDrawNext() {
        ImageView drawView = new ImageView(nextMango);
        drawView.setFitWidth(40);
        drawView.setFitHeight(40);
        drawView.setPreserveRatio(true);
        this.drawNext = new Button();
        this.drawNext.setGraphic(drawView);
        this.drawNext.setStyle("-fx-background-color: transparent;");
        this.drawNext.setDisable(true);
    }

    public void changeLookTransitions() {
        defaultLook = !defaultLook;

//        mainMenuKeno.setImage((defaultLook) ? kenoMango : kenoGrape);
//        endKeno.setImage((defaultLook) ? kenoGrape : kenoMango);

        ImageView playView = new ImageView((defaultLook) ? nextMango : nextGrape);
        playView.setFitWidth(80);
        playView.setFitHeight(80);
        playView.setPreserveRatio(true);
        this.mainMenuNext.setGraphic(playView);
        this.mainMenuNext.setStyle("-fx-background-color: transparent;");

        ImageView betView = new ImageView((defaultLook) ? nextMango : nextGrape);
        betView.setFitWidth(40);
        betView.setFitHeight(40);
        betView.setPreserveRatio(true);
        this.betNext.setGraphic(betView);
        this.betNext.setStyle("-fx-background-color: transparent;");

        ImageView drawView = new ImageView((defaultLook) ? nextMango : nextGrape);
        drawView.setFitWidth(40);
        drawView.setFitHeight(40);
        drawView.setPreserveRatio(true);
        this.drawNext.setGraphic(drawView);
        this.drawNext.setStyle("-fx-background-color: transparent;");
    }
}

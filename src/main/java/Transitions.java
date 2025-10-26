import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;

public class Transitions {
    public boolean defaultLook = true;
    public ImageView mainMenuKeno;
    public Button mainMenuNext;
    public Button betNext;
    public Button drawNext;
    // Images
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
        Image keno = new Image("/keno_y.png",true);
        this.mainMenuKeno = new ImageView();
        this.mainMenuKeno.setImage(keno);
        this.mainMenuKeno.setFitWidth(300);
        this.mainMenuKeno.setPreserveRatio(true);
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
}

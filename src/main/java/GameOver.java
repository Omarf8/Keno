import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class GameOver {
    public boolean defaultLook;
    public StackPane totEarnings;
    public StackPane retry;
    public StackPane exit;
    public StackPane mainmenu;
    // Constants
    public final String ellipseStyle = "-fx-stroke: black; -fx-stroke-width: 3; ";
    public final String mangoColor = "#ffd27e";
    public final String grapeColor = "#b27eff";
    public final String fontSize = "-fx-font-size: 25;";

    public GameOver() {
        this.defaultLook = true;
        initializeBoxes();
    }

    public void initializeBoxes() {
        // Create 4 total pill-looking boxes
        Rectangle totEarningsBox = new Rectangle(200,80);
        totEarningsBox.setArcHeight(80);
        totEarningsBox.setArcWidth(80);
        totEarningsBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);
        Rectangle retryBox = new Rectangle(200, 80);
        retryBox.setArcHeight(80);
        retryBox.setArcWidth(80);
        retryBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);
        Rectangle exitBox = new Rectangle(200, 80);
        exitBox.setArcHeight(80);
        exitBox.setArcWidth(80);
        exitBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);
        Rectangle mainmenuBox = new Rectangle(200, 80);
        mainmenuBox.setArcHeight(80);
        mainmenuBox.setArcWidth(80);
        mainmenuBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);

        // Text
        Text earningsText = new Text("Earnings");
        earningsText.setStyle(fontSize);
        Text numEarningsText = new Text("$");
        numEarningsText.setStyle(fontSize);
        Text retryText = new Text("Retry");
        retryText.setStyle(fontSize);
        Text exitText = new Text("Exit");
        exitText.setStyle(fontSize);
        Text mainmenuText = new Text("Main Menu");
        mainmenuText.setStyle(fontSize);

        VBox vb1 = new VBox(earningsText, numEarningsText);
        vb1.setAlignment(Pos.CENTER);
        vb1.setSpacing(2);
        VBox vb2 = new VBox(retryText);
        vb2.setAlignment(Pos.CENTER);
        VBox vb3 = new VBox(exitText);
        vb3.setAlignment(Pos.CENTER);
        VBox vb4 = new VBox(mainmenuText);
        vb4.setAlignment(Pos.CENTER);

        // Add every Rectangle to a StackPane in order to overlay each Rectangle with text
        this.totEarnings = new StackPane(totEarningsBox, vb1);
        this.retry = new StackPane(retryBox, vb2);
        this.exit = new StackPane(exitBox, vb3);
        this.mainmenu = new StackPane(mainmenuBox, vb4);
    }

    public void changeLookEnd() {
        defaultLook = !defaultLook;

        Rectangle totBox = (Rectangle) totEarnings.getChildren().get(0);
        totBox.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));

        Rectangle retryBox = (Rectangle) retry.getChildren().get(0);
        retryBox.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));

        Rectangle exitBox = (Rectangle) exit.getChildren().get(0);
        exitBox.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));

        Rectangle mainmenuBox = (Rectangle) mainmenu.getChildren().get(0);
        mainmenuBox.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));
    }
}

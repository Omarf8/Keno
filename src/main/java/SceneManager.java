import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class SceneManager {
    private HashMap<String, Scene> mapScenes;
    private boolean defaultLook;
    private MenuBar menuBar;
    private Menu menu;
//    public static final picHeight = ;
//    public static final picWidth = ;

    public SceneManager() {
        mapScenes = new HashMap<>();
        this.defaultLook = true;
        this.menuBar = new MenuBar();
        this.menu = new Menu("Menu");

        // Create all scenes and add them to the HashMap
        mainMenuScene();
    }

    public HashMap<String, Scene> getMapScenes() {
        return mapScenes;
    }

    public void mainMenuScene() {
        BorderPane pane = new BorderPane();

        // Creates a Menu button that is different from the Menu in other scenes
        MenuBar mb = new MenuBar();
        Menu m = new Menu("Menu");

        // Creating MenuItems
        MenuItem rules = new MenuItem("Rules");
        // Add setOnAction functionality
        MenuItem odds = new MenuItem("Odds");
        // Add setOnAction functionality
        MenuItem exit = new MenuItem("Exit");
        // Add setOnAction functionality

        // Add the items to the Menu and add the Menu to the MenuBar
        m.getItems().addAll(rules, odds, exit);
        mb.getMenus().addAll(m);

        Text title = new Text("Keno");
        title.setStyle("-fx-font-size: 80;");
        Text description = new Text("A short, fun and interactive game!");
        description.setStyle("-fx-font-size: 20;");

        // Create the orange play button
        Image orangeNext = new Image("next.png");
        ImageView playView = new ImageView(orangeNext);
        playView.setFitWidth(80);
        playView.setFitHeight(80);
        playView.setPreserveRatio(true);
        // Makes the orange next button become a button to be clicked
        Button nextButton = new Button();
        nextButton.setGraphic(playView);
        nextButton.setStyle("-fx-background-color: transparent;");
        // Make the button grow and shrink based on whether its being hovered
        nextButton.setOnMouseEntered(e -> {
            nextButton.setScaleX(1.05);
            nextButton.setScaleY(1.05);
        });
        nextButton.setOnMouseExited(e -> {
            nextButton.setScaleX(1);
            nextButton.setScaleY(1);
        });

        // Background circles for the main menu screen
        Circle c1 = new Circle(170, 150, 30);
//        Circle c2 = new Circle();
//        Circle c3 = new Circle();
//        Circle c4 = new Circle();
        c1.setFill(Color.web("#ffd344"));
//        c2.setFill(Color.web("#ffba39"));
//        c3.setFill(Color.web("#ffd344"));
//        c4.setFill(Color.web("#ffba39"));
        VBox leftCircleVBox = new VBox(c1);

        Circle c5 = new Circle(575, 150, 30);
        VBox rightCircleVBox = new VBox(c5);

        // Space used to separate the button and the text in the VBox mid
        Region space = new Region();
        space.setMinHeight(20);
        VBox mid = new VBox(10, title, description, space, nextButton);
        mid.setAlignment(Pos.CENTER); // Place the title and description VBox in the center of the pane

        // Place nodes in the BorderPane
        pane.setTop(mb);
        pane.setLeft(leftCircleVBox);
        pane.setRight(rightCircleVBox);
        pane.setCenter(mid);
        pane.setStyle("-fx-background-color: #ffd5a6;"); // Set background color to orange

        mapScenes.put("mainmenu", new Scene(pane, 700,700)); // Add to the HashMap
    }

//    public void betScene() {
//
//    }
//
//    public void drawScene() {
//
//    }
//
//    public void endScene() {
//
//    }
//
//    public void rulesPopupScene() {
//
//    }
//
//    public void oddsPopupScene() {
//
//    }
}

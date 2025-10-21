import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

        // Space used to separate the button and the text in the VBox mid
        Region space = new Region();
        space.setMinHeight(20);
        VBox mid = new VBox(10, title, description, space, nextButton);
        mid.setAlignment(Pos.CENTER); // Place the title and description VBox in the center of the pane

        // Background Pane used for circles on the main menu screen
        Pane circles = new Pane();
        // Left side circles
        Circle c1 = new Circle(195, 80, 15, Color.web("#ffd344"));
        Circle c2 = new Circle(70, 195, 25, Color.web("#ffba39"));
        Circle c3 = new Circle(150, 500, 30, Color.web("#ffd344"));
        Circle c4 = new Circle(190, 670,15, Color.web("#ffba39"));
        Circle c5 = new Circle(280, 400,8, Color.web("#ffd344"));
        // Right side circles
        Circle c6 = new Circle(575, 155, 25, Color.web("#ffba39"));
        Circle c7 = new Circle(460, 320, 10, Color.web("#ffd344"));
        Circle c8 = new Circle(525, 600, 20, Color.web("#ffba39"));
        circles.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8);

        // Place nodes in the BorderPane
        pane.setTop(mb);
        pane.setCenter(mid);
        circles.setStyle("-fx-background-color: #ffd5a6;"); // Set background color to orange

        StackPane root = new StackPane(circles, pane);
        mapScenes.put("mainmenu", new Scene(root, 700,700)); // Add to the HashMap
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

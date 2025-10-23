import java.util.HashMap;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
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
    public BetCard card;
    public Grid grid;
//    public DrawPhase draw;
    private boolean defaultLook;
    private MenuBar menuBar;
//    public static final picHeight = ;
//    public static final picWidth = ;

    public SceneManager() {
        this.card = new BetCard();
        this.grid = new Grid();
//        this.draw = new DrawPhase();
        mapScenes = new HashMap<>();
        this.defaultLook = true;
        initializeMenuBar();
        // Create all scenes and add them to the HashMap
        mainMenuScene();
        betScene();
    }

    public HashMap<String, Scene> getMapScenes() {
        return mapScenes;
    }

    private void initializeMenuBar() {
        // Create and add the Menu at the top
        this.menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem rules = new MenuItem("Rules");
        MenuItem odds = new MenuItem("Odds");
        MenuItem newLook = new MenuItem("New Look");
        MenuItem exit = new MenuItem("Exit");
        menu.getItems().addAll(rules, odds, newLook, exit);
        menuBar.getMenus().addAll(menu);
    }

    public Pane createBackgroundPane() {
        // Background Pane used for circles on the main menu screen
        Pane background = new Pane();
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
        background.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8);
        background.setStyle("-fx-background-color: #ffd5a6;"); // Set background color to orange

        return background;
    }

    public void mainMenuScene() {
        BorderPane pane = new BorderPane();

        // Creates a Menu button that is different from the Menu in other scenes
        MenuBar mb = new MenuBar();
        Menu m = new Menu("Menu");

        // Creating MenuItems
        MenuItem rule = new MenuItem("Rules");
        // Add setOnAction functionality
        MenuItem odd = new MenuItem("Odds");
        // Add setOnAction functionality
        MenuItem ex = new MenuItem("Exit");
        // Add setOnAction functionality

        // Add the items to the Menu and add the Menu to the MenuBar
        m.getItems().addAll(rule, odd, ex);
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

        Pane background = createBackgroundPane();

        // Place nodes in the BorderPane
        pane.setTop(mb);
        pane.setCenter(mid);

        StackPane root = new StackPane(background, pane);
        mapScenes.put("mainmenu", new Scene(root, 700,700)); // Add to the HashMap
    }

    public void betScene() {
        BorderPane pane = new BorderPane();
        Pane background = createBackgroundPane();

        VBox leftPanel = new VBox(10);
        leftPanel.setPrefWidth(250); // The left panel should take around 1/3 of the screen
        // #1
        Text nBets = new Text("How much do you want to play per draw?");
        GridPane nBetsGrid1 = new GridPane();
        nBetsGrid1.setHgap(10);
        // Add top half of the buttons
        for(int i = 0; i < 3; i++) {
            Button btn = new Button("$" + (i + 1));
            btn.setPrefSize(40, 40);
            nBetsGrid1.add(btn, i, 0);
        }
        // Add bottom half of the buttons
        GridPane nBetsGrid2 = new GridPane();
        nBetsGrid2.setHgap(10);
        Button dBtn = new Button("$5");
        Button dBtn2 = new Button("$10");
        dBtn.setPrefSize(40, 40);
        dBtn2.setPrefSize(40, 40);
        nBetsGrid2.add(dBtn, 0, 1);
        nBetsGrid2.add(dBtn2, 1, 1);
        // #2
        Text nDraws = new Text("Number of Draws");
        GridPane nDrawsGrid = new GridPane();
        nDrawsGrid.setHgap(10);
        for(int i = 0; i < 4; i++) {
            Button btn = new Button("$" + (i + 1));
            btn.setPrefSize(40, 40);
            nDrawsGrid.add(btn, i, 0);
        }
        // #3
        Text nSpots = new Text("Number of Spots");
        GridPane nSpotsGrid = new GridPane();
        nSpotsGrid.setHgap(10);
        Button sBtn = new Button("1");
        Button sBtn2 = new Button("4");
        Button sBtn3 = new Button("8");
        Button sBtn4 = new Button("10");
        sBtn.setPrefSize(40, 40);
        sBtn2.setPrefSize(40, 40);
        sBtn3.setPrefSize(40, 40);
        sBtn4.setPrefSize(40, 40);
        nSpotsGrid.add(sBtn, 0, 0);
        nSpotsGrid.add(sBtn2, 1, 0);
        nSpotsGrid.add(sBtn3, 2, 0);
        nSpotsGrid.add(sBtn4, 3, 0);
        // #4
        Text quickSel =  new Text("Quick Select");
        Button qsBtn = new Button("");
        qsBtn.setPrefSize(40, 40);
        leftPanel.getChildren().addAll(nBets, nBetsGrid1, nBetsGrid2, nDraws, nDrawsGrid, nSpots, nSpotsGrid, quickSel, qsBtn);
        leftPanel.setStyle("-fx-background-color: #f3c049;" + "-fx-border-color: transparent black transparent transparent;" + "-fx-border-width: 0 2 0 0;" + "fx-border-style: solid;");

        VBox rightPanel = new VBox(10);
        rightPanel.setPrefWidth(450); // The right panel will take around 2/3 of the screen
        Text instruction = new Text("Select Your Numbers OR Press Quick Select");
        Text remain =  new Text("Remaining: " );
        rightPanel.getChildren().addAll(instruction, remain);
        rightPanel.setStyle("-fx-background-color: #ffd27e;");

        // Place nodes in the BorderPane
        pane.setTop(menuBar);
        pane.setLeft(leftPanel);
        pane.setRight(rightPanel);

        StackPane root = new StackPane(background, pane);
        mapScenes.put("bet", new Scene(root, 700,700));
    }
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

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SceneManager {
    public Stage primaryStage;
    private final HashMap<String, Scene> mapScenes;
    public BetCard card;
    public BetCard.Grid grid;
//    public DrawPhase draw;
    private boolean defaultLook;
    private MenuBar menuBar;
//    public static final picHeight = ;
//    public static final picWidth = ;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.mapScenes = new HashMap<>();
        this.card = new BetCard();
        this.grid = card.new Grid();
//        this.draw = new DrawPhase();
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

    public Pane createBackgroundPane(String bgColor) {
        // Background Pane used for circles on the main menu screen
    	Image mango = new Image("/mango_fruit.gif", true);

    	ImageView iv1 = new ImageView();
        iv1.setImage(mango);
    	iv1.setX(650);
    	iv1.setY(270);
    	iv1.setFitWidth(300);
    	iv1.setPreserveRatio(true);




        Pane background = new Pane();
        // Left side circles
        Circle c1 = new Circle(195+50, 80, 15, Color.web("#ffd344"));
        Circle c2 = new Circle(70+50, 195, 25, Color.web("#ffba39"));
        Circle c3 = new Circle(150+50, 500, 30, Color.web("#ffd344"));
        Circle c4 = new Circle(190+50, 670,15, Color.web("#ffba39"));
        Circle c5 = new Circle(280+50, 400,8, Color.web("#ffd344"));
        // Right side circles
        Circle c6 = new Circle(575+250, 155, 20, Color.web("#ffba39"));
        Circle c7 = new Circle(460+250, 320, 10, Color.web("#ffd344"));
        Circle c8 = new Circle(525+250, 600, 20, Color.web("#ffba39"));
        background.getChildren().addAll(c1, c2, c3, c4, c5, c6, c7, c8,iv1);
        background.setStyle("-fx-background-color: " + bgColor + ";"); // Set background color to bgColor

        return background;
    }

    public Region createVerticalGap(int pixels) {
        Region verticalGap = new Region();
        verticalGap.setMinHeight(pixels);
        return verticalGap;
    }

    public Region createHorizontalGap(int pixels) {
        Region horizontalGap = new Region();
        horizontalGap.setMinWidth(pixels);
        return horizontalGap;
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
        //keno image
        Image keno_y = new Image("/keno_y.png",true);
        ImageView iv2 = new ImageView();
        iv2.setImage(keno_y);
        iv2.setFitWidth(300);
        iv2.setPreserveRatio(true);



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
        nextButton.setOnAction(e -> {
            primaryStage.setScene(mapScenes.get("bet"));
            grid.lockGrid();
        });

        // Space used to separate the button and the text in the VBox mid
        Region space = new Region();
        space.setMinHeight(20);
        VBox mid = new VBox(10, iv2, description, space, nextButton);
        mid.setAlignment(Pos.CENTER); // Place the title and description VBox in the center of the pane

        Pane background = createBackgroundPane("#ffd5a6");

        // Place nodes in the BorderPane
        pane.setTop(mb);
        pane.setCenter(mid);

        StackPane root = new StackPane(background, pane);
        mapScenes.put("mainmenu", new Scene(root, 1000,700)); // Add to the HashMap
    }

    public void betScene() {
        BorderPane pane = new BorderPane();
        Pane background = createBackgroundPane("ffd27e");
        Region verticalSpace = new Region(); // This is used to separate elements within VBoxes
        verticalSpace.setMinHeight(20);

        VBox leftPanel = new VBox(10);
        leftPanel.setPrefWidth(290); // The left panel should take around 1/3 of the screen
        // #1
        Text nBets = new Text("Bet Amount Per Draw");
        GridPane nBetsGrid1 = card.betsTopHalfGrid;
        GridPane nBetsGrid2 = card.betsBottomHalfGrid;
        // These HBoxes help align the GridPane's towards the center
        HBox topHalf = new HBox(10, nBetsGrid1);
        HBox bottomHalf = new HBox(10, nBetsGrid2);
        topHalf.setAlignment(Pos.CENTER);
        bottomHalf.setAlignment(Pos.CENTER);
        // #2
        Text nDraws = new Text("Number of Draws");
        GridPane nDrawsGrid = card.drawsGrid;
        HBox hbDrawsGrid = new HBox(10, nDrawsGrid); // Used to center the GridPane for the Draws boxes
        hbDrawsGrid.setAlignment(Pos.CENTER);
        // #3
        Text nSpots = new Text("Number of Spots");
        GridPane nSpotsGrid = grid.spotsGrid;
        HBox hbSpotsGrid = new HBox(10, nSpotsGrid); // Used to center the GridPane for the Spots boxes
        hbSpotsGrid.setAlignment(Pos.CENTER);
        // #4
        Text quickSel =  new Text("Quick Select");
        Button qsBtn = new Button("");
        qsBtn.setOnAction(e -> {
            // Only be able to call the function if the number of spots has been chosen
            if(grid.numSpots > 0) {
                grid.quickSelect();
            }
        });
        qsBtn.setPrefSize(card.gridButtonSize, card.gridButtonSize);
        qsBtn.setStyle(card.buttonOffStyle);
        leftPanel.getChildren().addAll(createVerticalGap(20), nBets, topHalf, bottomHalf, createVerticalGap(40), nDraws, hbDrawsGrid, createVerticalGap(40), nSpots, hbSpotsGrid, createVerticalGap(60), quickSel, qsBtn);
        leftPanel.setStyle("-fx-background-color: #f3c049; -fx-border-color: transparent black transparent transparent; -fx-border-width: 0 2 0 0; fx-border-style: solid;");
        leftPanel.setAlignment(Pos.TOP_CENTER); // Position the children horizontally
        leftPanel.setPadding(new Insets(30)); // Padding on the inside

        VBox rightPanel = new VBox(10);
        rightPanel.setPrefWidth(710); // The right panel will take around 2/3 of the screen
        Text instruction = new Text("Select Your Numbers OR Press Quick Select");
        Text remain =  new Text("Remaining: " + grid.spotsRemaining);
        HBox hbGrid = new HBox(10, grid.gridSpots); // Used to center the Grid
        hbGrid.setAlignment(Pos.CENTER);
        rightPanel.getChildren().addAll(instruction, createVerticalGap(20), hbGrid, createVerticalGap(20), remain);
        rightPanel.setAlignment(Pos.TOP_CENTER); // Position the children horizontally
        rightPanel.setPadding(new Insets(20)); // Padding on the inside

        // Place nodes in the BorderPane
        pane.setTop(menuBar);
        pane.setLeft(leftPanel);
        pane.setRight(rightPanel);

        StackPane root = new StackPane(background, pane);
        mapScenes.put("bet", new Scene(root, 1000,700));
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

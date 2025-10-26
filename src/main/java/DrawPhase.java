import java.util.HashMap;
import java.util.HashSet;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
// These are used for the playing animation to slow things down
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class DrawPhase {
    public HashMap<Integer, HashMap<Integer, Integer>> winningsLookupTable;
    public int numPhase;
    public int totalEarnings;
    public int drawEarnings;
    public int numMatched;
    public HashSet<Integer> randomSpots;
    public HashSet<Integer> matchedSpots;
    public GridPane drawGrid;
    public Button[][] drawGridLookup;
    public boolean defaultLook;
    public HBox bottomPanel;
    public StackPane pane1;
    public StackPane pane2;
    public StackPane pane3;
    public StackPane pane4;
    // Constants
    public final int gridButtonSize = 45;
    public final String buttonStyle = "-fx-border-color: black; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 5; " +
            "-fx-background-radius: 3; " +
            "-fx-background-insets: 2;";
    public final String mangoColor = "#ffd5a6";
    public final String grapeColor = "#ffd5fc";
    public final String bottomPanelMango = "-fx-background-color: #b1813d";
    public final String bottomPanelGrape = "-fx-background-color: #723299;";
    public final String ellipseStyle = "-fx-stroke: black; -fx-stroke-width: 3; ";
    public final String correctStyle = "-fx-background-color: #00fa36; " + buttonStyle;
    public final String incorrectStyle = "-fx-background-color: #ff0000;" + buttonStyle;
    public final String buttonOffStyle = "-fx-background-color: white;" + buttonStyle;

    public DrawPhase() {
        this.winningsLookupTable = new HashMap<>(); // Maps the spot game (1, 4, 8 or 10) to a HashMap that maps matches to earnings
        mapPrizes(); // Initializes prizes in the winningsLookupTable HashMap
        this.numPhase = 1;
        this.totalEarnings = 0;
        this.drawEarnings = 0;
        this.numMatched = 0;
        this.defaultLook = true;
        this.randomSpots = new HashSet<>();
        this.matchedSpots = new HashSet<>();
        this.drawGrid = new GridPane();
        this.drawGridLookup = new Button[8][10];
        initializeDrawGrid();
        createBottomPanel();
    }

    // Initializes prizes in the winningsLookupTable HashMap
    private void mapPrizes() {
        // 1 Spot
        winningsLookupTable.put(1, new HashMap<>());
        winningsLookupTable.get(1).put(1, 2);
        // 4 Spot
        winningsLookupTable.put(4, new HashMap<>());
        winningsLookupTable.get(4).put(2, 1);
        winningsLookupTable.get(4).put(3, 5);
        winningsLookupTable.get(4).put(4, 75);
        // 8 Spot
        winningsLookupTable.put(8, new HashMap<>());
        winningsLookupTable.get(8).put(4, 2);
        winningsLookupTable.get(8).put(5, 12);
        winningsLookupTable.get(8).put(6, 50);
        winningsLookupTable.get(8).put(7, 750);
        winningsLookupTable.get(8).put(8, 10000);
        // 10 Spot
        winningsLookupTable.put(10, new HashMap<>());
        winningsLookupTable.get(10).put(0, 5);
        winningsLookupTable.get(10).put(5, 2);
        winningsLookupTable.get(10).put(6, 15);
        winningsLookupTable.get(10).put(7, 40);
        winningsLookupTable.get(10).put(8, 450);
        winningsLookupTable.get(10).put(9, 4250);
        winningsLookupTable.get(10).put(10, 100000);
    }

    public void initializeDrawGrid() {
        // Space between each button
        this.drawGrid.setHgap(30);
        this.drawGrid.setVgap(25);

        int i = 1;
        for(int row = 0; row < 8; row++) {
            for (int col = 0; col < 10; col++) {
                Button btn = new Button(String.valueOf(i));
                btn.setPrefSize(gridButtonSize, gridButtonSize);
                btn.setStyle(buttonOffStyle);
                this.drawGrid.add(btn, col, row);
                this.drawGridLookup[row][col] = btn;
                i++;
            }
        }
    }

    public void createBottomPanel() {
        // Create the bottom panel background
        this.bottomPanel = new HBox(25);
        this.bottomPanel.setPrefWidth(500);
        this.bottomPanel.setPrefHeight(115);
        this.bottomPanel.setStyle(bottomPanelMango);

        // Create 4 total pill-looking boxes
        Rectangle drawBox = new Rectangle(120, 80);
        drawBox.setArcHeight(80);
        drawBox.setArcWidth(80);
        drawBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);
        Rectangle totalBox = new Rectangle(200, 80);
        totalBox.setArcHeight(80);
        totalBox.setArcWidth(80);
        totalBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);
        Rectangle earningsBox = new Rectangle(200, 80);
        earningsBox.setArcHeight(80);
        earningsBox.setArcWidth(80);
        earningsBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);
        Rectangle matchBox = new Rectangle(150, 80);
        matchBox.setArcHeight(80);
        matchBox.setArcWidth(80);
        matchBox.setStyle(ellipseStyle + "-fx-fill: " + mangoColor);

        // Text
        Text d = new Text("Draw");
        Text dNum = new Text(String.valueOf(numPhase));
        Text total = new Text("Total Earnings");
        Text totalNum = new Text("$" + String.valueOf(totalEarnings));
        Text earnings = new Text("Draw Earnings");
        Text earningsNum = new Text("$" + String.valueOf(drawEarnings));
        Text match = new Text("Matched");
        Text matchNum = new Text(String.valueOf(numMatched));

        // Place inside a VBox for organization
        VBox vb1 = new VBox(d, dNum);
        vb1.setAlignment(Pos.CENTER);
        vb1.setSpacing(2);
        VBox vb2 = new VBox(total, totalNum);
        vb2.setAlignment(Pos.CENTER);
        vb2.setSpacing(2);
        VBox vb3 = new VBox(earnings, earningsNum);
        vb3.setAlignment(Pos.CENTER);
        vb3.setSpacing(2);
        VBox vb4 = new VBox(match, matchNum);
        vb4.setAlignment(Pos.CENTER);
        vb4.setSpacing(2);

        // Add every ellipse to a StackPane in order to overlay each ellipse with text
        this.pane1 = new StackPane(drawBox, vb1);
        this.pane2 = new StackPane(totalBox, vb2);
        this.pane3 = new StackPane(earningsBox, vb3);
        this.pane4 = new StackPane(matchBox, vb4);

        this.bottomPanel.getChildren().addAll(pane1, pane2, pane3, pane4);
    }

    public void chooseRandomSpots() {
        while(this.randomSpots.size() < 20) {
            int rand = (int)(Math.random() * 80 + 1); // Select a random number
            // Add the number to the set
            if(!randomSpots.contains(rand)) {
                this.randomSpots.add(rand);
            }
        }
    }

    public void calculateMatched(HashSet<Integer> spotsSelected) {
        for(Integer spot: spotsSelected) {
            if(this.randomSpots.contains(spot)) {
                this.matchedSpots.add(spot);
                this.numMatched++;
            }
        }
    }

    public void playPhase(BetCard card, BetCard.Grid grid, Transitions ts) {
        chooseRandomSpots();
        calculateMatched(grid.spotsSelected);
        ts.drawNext.setDisable(true);
        Timeline tl = new Timeline();
        int i = 1; // Helps with the intervals
        for(Integer spot: this.randomSpots) {
            KeyFrame kf = new KeyFrame(Duration.seconds(i * 0.9), e -> {
                // If the user chose the spot, turn the Button green
                if(this.matchedSpots.contains(spot)) {
                    drawGridLookup[(spot - 1) / 10][(spot - 1) % 10].setStyle(correctStyle);
                }
                // Otherwise turn it red
                else {
                    drawGridLookup[(spot - 1) / 10][(spot - 1) % 10].setStyle(incorrectStyle);
                }
            });

            tl.getKeyFrames().add(kf);
            i++;
        }

        // We need another KeyFrame in order to only display the updated number only after the
        // grid is done displaying correct and incorrect matches
        KeyFrame kf = new KeyFrame(Duration.seconds(i * 0.9), e -> {
            int currEarnings = calculateEarnings(card.moneyPerBet, grid.numSpots);
            this.totalEarnings += currEarnings;

            // Update the value of the number of matches
            VBox vb4match = (VBox) pane4.getChildren().get(1);
            Text match = (Text) vb4match.getChildren().get(1);
            match.setText(String.valueOf(this.matchedSpots.size()));

            // Update current draw earnings
            VBox vb3draw = (VBox) pane3.getChildren().get(1);
            Text draw = (Text) vb3draw.getChildren().get(1);
            draw.setText("$" + String.valueOf(currEarnings));

            // Update total earnings
            VBox vb2Total = (VBox) pane2.getChildren().get(1);
            Text total = (Text) vb2Total.getChildren().get(1);
            total.setText("$" + String.valueOf(totalEarnings));

            ts.drawNext.setDisable(false);
        });
        tl.getKeyFrames().add(kf);

        tl.play();
    }

    public void updatePhase() {
        this.numPhase++;

        // Update the bottomPanel
        // Update the value of the number of matches
        VBox vb4match = (VBox) pane4.getChildren().get(1);
        Text match = (Text) vb4match.getChildren().get(1);
        match.setText("0");

        // Update current draw earnings
        VBox vb3draw = (VBox) pane3.getChildren().get(1);
        Text draw = (Text) vb3draw.getChildren().get(1);
        draw.setText("$0");

        // Update draw phase
        VBox vb2Total = (VBox) pane1.getChildren().get(1);
        Text total = (Text) vb2Total.getChildren().get(1);
        total.setText(String.valueOf(numPhase));
    }

    public void clear() {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 10; col++) {
                this.drawGridLookup[row][col].setStyle(buttonOffStyle); // Reset all button colors
            }
        }

        this.randomSpots.clear();
        this.matchedSpots.clear();
        this.drawEarnings = 0;
        this.numMatched = 0;
    }

    public void changeLookDrawPhase() {
        defaultLook = !defaultLook;

        // Change the bottomPanel first
        this.bottomPanel.setStyle(((defaultLook) ? bottomPanelMango : bottomPanelGrape));

        // Get every Rectangle object and change the color
        Rectangle r1 = (Rectangle) pane1.getChildren().get(0);
        Rectangle r2 = (Rectangle) pane2.getChildren().get(0);
        Rectangle r3 = (Rectangle) pane3.getChildren().get(0);
        Rectangle r4 = (Rectangle) pane4.getChildren().get(0);
        r1.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));
        r2.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));
        r3.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));
        r4.setStyle(ellipseStyle + "-fx-fill: " + ((defaultLook) ? mangoColor : grapeColor));
    }

    // Calculate the earnings won
    public int calculateEarnings(int moneyPerBet, int spots) {
        // If the number of matches does not exist for the spot, return 0
        if(winningsLookupTable.get(spots).get(this.numMatched) == null) { return 0;}
        // Else return the amount won
        else { return winningsLookupTable.get(spots).get(this.numMatched) * moneyPerBet;}
    }

    public void lockDrawGrid() {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 10; col++) {
                this.drawGridLookup[row][col].setDisable(true);
            }
        }
    }
}

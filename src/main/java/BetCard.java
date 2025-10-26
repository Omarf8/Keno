import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class BetCard {
    public int moneyPerBet;
    public int prevBet;
    public int numDraws;
    public int prevDraw;
    public GridPane betsTopHalfGrid;
    public GridPane betsBottomHalfGrid;
    public HashMap<Integer, Button> betLookup;
    public GridPane drawsGrid;
    public Button[] drawsLookup;
    public boolean defaultLook;
    public VBox leftPanel;
    // Constants
    public final int gridButtonSize = 45;
    public final String buttonStyle = "-fx-border-color: black; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 5; " +
            "-fx-background-radius: 3; " +
            "-fx-background-insets: 2;";
    public final String buttonOffMango = "-fx-background-color: #fed7bc;" + buttonStyle;
    public final String buttonOnMango= "-fx-background-color: #0073ff;" + buttonStyle;
    public final String buttonOffGrape = "-fx-background-color: #ffd9d9;" + buttonStyle;
    public final String buttonOnGrape = "-fx-background-color: #863ebd;" + buttonStyle;
    public final String leftPanelStyle = "-fx-border-color: transparent black transparent transparent; -fx-border-width: 0 2 0 0; fx-border-style: solid;";
    public final String leftPanelMango = "-fx-background-color: #fec049;";
    public final String leftPanelGrape = "-fx-background-color: #af5fd1;";

    public BetCard() {
        this.moneyPerBet = 0;
        this.prevBet = 0;
        this.numDraws = 0;
        this.prevDraw = 0;
        this.defaultLook = true;
        this.betLookup = new HashMap<>();
        createBetsTopHalfGrid();
        createBetsBottomHalfGrid();
        this.drawsLookup = new Button[4];
        createDrawsGrid();
        this.leftPanel = new VBox(10);
        leftPanel.setPrefWidth(290); // The left panel should take around 1/3 of the screen
        leftPanel.setStyle(leftPanelMango + leftPanelStyle);
    }

    public boolean isBetsReady() {
        return this.moneyPerBet != 0 && this.numDraws != 0;
    }

    public void createBetsTopHalfGrid() {
        this.betsTopHalfGrid = new GridPane();
        this.betsTopHalfGrid.setHgap(10);

        for(int i = 0; i < 3; i++) {
            Button btn = new Button("$" + (i + 1));
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
            this.betsTopHalfGrid.add(btn, i, 0);
            this.betLookup.put(i + 1, btn);
        }
    }

    public void createBetsBottomHalfGrid() {
        this.betsBottomHalfGrid = new GridPane();
        this.betsBottomHalfGrid.setHgap(10);

        for(int i = 0; i < 2; i++) {
            Button btn = new Button("$" + (5 + 5 * i));
            btn.setOnAction(e -> {
                // Turn off the previous Button chosen
                if(this.prevBet != 0) {
                    this.betLookup.get(prevBet).setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
                }

                this.moneyPerBet = Integer.parseInt(btn.getText().substring(1));
                this.betLookup.get(moneyPerBet).setStyle((defaultLook) ? buttonOnMango : buttonOnGrape); // Turn on the newly selected Button

                this.prevBet = moneyPerBet; // This will turn off a Button if another Button is selected the next time
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
            this.betsBottomHalfGrid.add(btn, i, 1);
            this.betLookup.put(5 + 5 * i, btn);
        }
    }

    public void createDrawsGrid() {
        this.drawsGrid = new GridPane();
        this.drawsGrid.setHgap(10);

        for(int i = 0; i < 4; i++) {
            Button btn = new Button(String.valueOf(i + 1));
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
            this.drawsGrid.add(btn, i, 0);
            this.drawsLookup[i] = btn;
        }
    }

    public class Grid {
        public int numSpots;
        public int prevSpot;
        public HashSet<Integer> spotsSelected;
        public int spotsRemaining;
        public GridPane spotsGrid;
        public HashMap<Integer, Button> spotsLookup;
        public GridPane gridSpots; // This is used for the grid that is displayed
        public Button[][] gridLookup; // If we need to access a specific button we can use the 2D array
        public boolean gridDisabled;

        public Grid() {
            this.numSpots = 0;
            this.prevSpot = 0;
            this.spotsSelected = new HashSet<>();
            this.spotsRemaining = 0;
            this.spotsLookup = new HashMap<>();
            createSpotsGrid();
            this.gridSpots = new GridPane();
            this.gridLookup = new Button[8][10];
            initializeGrid();
            this.gridDisabled = true;
        }

        public void initializeGrid() {
            // Space between each button
            gridSpots.setHgap(15);
            gridSpots.setVgap(20);

            int i = 1;
            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 10; col++) {
                    Button btn = new Button(String.valueOf(i));
                    btn.setPrefSize(gridButtonSize, gridButtonSize);
                    btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
                    gridSpots.add(btn, col, row);
                    gridLookup[row][col] = btn;
                    i++;
                }
            }
        }

        public void createSpotsGrid() {
            this.spotsGrid = new GridPane();

            this.spotsGrid.setHgap(10);

            ArrayList<Integer> spotsArray = new ArrayList<>(Arrays.asList(1, 4, 8, 10)); // Contains our 4 button contents in order to use a for loop
            for(int i = 0; i < spotsArray.size(); i++) {
                Button btn = new Button(String.valueOf(spotsArray.get(i)));
                btn.setPrefSize(gridButtonSize, gridButtonSize);
                btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
                this.spotsGrid.add(btn, i, 0);
                this.spotsLookup.put(spotsArray.get(i), btn);
            }
        }

        public void clear() {
            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 10; col++) {
                    gridLookup[row][col].setStyle((defaultLook) ? buttonOffMango : buttonOffGrape); // Reset all button colors
                }
            }

            this.spotsSelected.clear();
            this.spotsRemaining = numSpots;
        }

        public boolean selected(int spot) {
            // If the spot that was already selected is removed return false
            if (spotsSelected.contains(spot)) {
                this.spotsSelected.remove(spot);
                this.spotsRemaining++;
                return false;
            }

            // This can only execute as long as there are spots remaining
            // The function can still be called but a button must be deselected in order to select another number
            if(this.spotsRemaining > 0) {
                // Otherwise return true to indicate that a new spot was added
                this.spotsSelected.add(spot);
                this.spotsRemaining--;
                return true;
            }

            return false;
        }

        public void quickSelect() {
            clear(); // Clear everything before selecting
            // If the number of spots has not been selected yet then don't do anything
            if(gridDisabled) {
                return;
            }

            // Select x buttons randomly
            while(spotsRemaining > 0) {
                int rand = (int)(Math.random() * 80 + 1); // Select the random number
                // If the random chosen number is unique, display that it has been chosen on the grid
                if(!spotsSelected.contains(rand)) {
                    this.gridLookup[(rand - 1) / 10][(rand - 1) % 10].setStyle((defaultLook) ? buttonOnMango : buttonOnGrape); // Make the button appear as chosen
                    this.spotsSelected.add(rand);
                    this.spotsRemaining--;
                }
            }
        }

        public void changeLookBetCardButtons() {
            defaultLook = !defaultLook;

            // Change the leftPanel first
            leftPanel.setStyle(((defaultLook) ? leftPanelMango : leftPanelGrape) + leftPanelStyle);

            // First disable all buttons and then re-enable all buttons that should be on
            for(Button btn : betLookup.values()){
                btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
            }
            if(moneyPerBet != 0) {
                betLookup.get(moneyPerBet).setStyle((defaultLook) ? buttonOnMango : buttonOnGrape);
            }

            for(Button btn : drawsLookup) {
                btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
            }
            if(numDraws != 0) {
                drawsLookup[numDraws - 1].setStyle((defaultLook) ? buttonOnMango : buttonOnGrape);
            }

            for(Button btn : spotsLookup.values()) {
                btn.setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
            }
            if(numSpots != 0) {
                spotsLookup.get(numSpots).setStyle((defaultLook) ? buttonOnMango : buttonOnGrape);
            }

            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 10; col++) {
                    gridLookup[row][col].setStyle((defaultLook) ? buttonOffMango : buttonOffGrape);
                }
            }
            for(Integer i : spotsSelected) {
                gridLookup[(i - 1) / 10][(i - 1) % 10].setStyle((defaultLook) ? buttonOnMango : buttonOnGrape);
            }
        }

        public void lockGrid() {
            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 10; col++) {
                    gridLookup[row][col].setDisable(true);
                }
            }
        }

        public void unlockGrid() {
            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 10; col++) {
                    gridLookup[row][col].setDisable(false);
                }
            }
        }

        public boolean isGridReady() {
            return numSpots != 0 && spotsRemaining == 0;
        }
    }
}

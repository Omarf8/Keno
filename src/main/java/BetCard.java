import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

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
    // Constants
    public final int gridButtonSize = 45;
    public final String buttonOffColor = "#fed7bc"; // Cream color
    public final String buttonOnColor = "blue";
    public final String buttonStyle = "-fx-border-color: black; " +
            "-fx-border-width: 2px; " +
            "-fx-border-radius: 5; " +
            "-fx-background-radius: 3; " +
            "-fx-background-insets: 2;";
    public final String buttonOffStyle = "-fx-background-color: " + buttonOffColor + "; " + buttonStyle;
    public final String buttonOnStyle = "-fx-background-color: " + buttonOnColor + "; " + buttonStyle;

    public BetCard() {
        this.moneyPerBet = 0;
        this.prevBet = 0;
        this.numDraws = 0;
        this.prevDraw = 0;
        this.betLookup = new HashMap<>();
        createBetsTopHalfGrid();
        createBetsBottomHalfGrid();
        this.drawsLookup = new Button[4];
        createDrawsGrid();
    }

    public boolean isBetsReady() {
        return this.moneyPerBet != 0 && this.numDraws != 0;
    }

    public void createBetsTopHalfGrid() {
        this.betsTopHalfGrid = new GridPane();
        this.betsTopHalfGrid.setHgap(10);

        for(int i = 0; i < 3; i++) {
            Button btn = new Button("$" + (i + 1));
            btn.setOnAction(e -> {
                // Turn off the previous Button chosen
                if(this.prevBet != 0) {
                    this.betLookup.get(prevBet).setStyle(buttonOffStyle);
                }

                this.moneyPerBet = Integer.parseInt(btn.getText().substring(1));
                this.betLookup.get(moneyPerBet).setStyle(buttonOnStyle); // Turn on the newly selected Button

                this.prevBet = moneyPerBet; // This will turn off a Button if another Button is selected the next time
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            btn.setStyle(buttonOffStyle);
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
                    this.betLookup.get(prevBet).setStyle(buttonOffStyle);
                }

                this.moneyPerBet = Integer.parseInt(btn.getText().substring(1));
                this.betLookup.get(moneyPerBet).setStyle(buttonOnStyle); // Turn on the newly selected Button

                this.prevBet = moneyPerBet; // This will turn off a Button if another Button is selected the next time
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            btn.setStyle(buttonOffStyle);
            this.betsBottomHalfGrid.add(btn, i, 1);
            this.betLookup.put(5 + 5 * i, btn);
        }
    }

    public void createDrawsGrid() {
        this.drawsGrid = new GridPane();

        this.drawsGrid.setHgap(10);
        for(int i = 0; i < 4; i++) {
            Button btn = new Button("$" + (i + 1));
            btn.setOnAction(e -> {
                // Turn off the previous Button chosen
                if(this.prevDraw != 0) {
                    this.drawsLookup[prevDraw - 1].setStyle(buttonOffStyle);
                }

                this.numDraws = Integer.parseInt(btn.getText().substring(1));
                this.drawsLookup[numDraws - 1].setStyle(buttonOnStyle); // Turn on the newly selected Button

                this.prevDraw = numDraws; // This will turn off a Button if another Button is selected the next time
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            btn.setStyle(buttonOffStyle);
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
                    btn.setOnAction(e -> {
                        // If selected returns true then change the color of the button to indicate it is selected
                        // Otherwise we should change the color back to normal
                        if(selected(Integer.parseInt(btn.getText()))) {
                            btn.setStyle(buttonOnStyle);
                        }
                        else {
                            btn.setStyle(buttonOffStyle);
                        }
                    });
                    btn.setPrefSize(gridButtonSize, gridButtonSize);
                    btn.setStyle(buttonOffStyle);
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
                btn.setOnAction(e -> {
                    this.numSpots = Integer.parseInt(btn.getText()); // Converts a String into an int
                    this.spotsRemaining = Integer.parseInt(btn.getText()); // Converts a String into an int
                    clear(); // Clear selected buttons
                    unlockGrid(); // Unlock because the user should now choose their spots
                    this.gridDisabled = false;

                    // Turn off the previous Button chosen
                    if(this.prevSpot != 0) {
                        this.spotsLookup.get(prevSpot).setStyle(buttonOffStyle);
                    }

                    this.numSpots = Integer.parseInt(btn.getText());
                    this.spotsLookup.get(numSpots).setStyle(buttonOnStyle); // Turn on the newly selected Button

                    this.prevSpot = numSpots; // This will turn off a Button if another Button is selected the next time
                });
                btn.setPrefSize(gridButtonSize, gridButtonSize);
                btn.setStyle(buttonOffStyle);
                this.spotsGrid.add(btn, i, 0);
                this.spotsLookup.put(spotsArray.get(i), btn);
            }
        }

        public void clear() {
            for(int row = 0; row < 8; row++) {
                for(int col = 0; col < 10; col++) {
                    gridLookup[row][col].setStyle(buttonOffStyle); // Reset all button colors
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
                    this.gridLookup[(rand - 1) / 10][(rand - 1) % 10].setStyle(buttonOnStyle); // Make the button appear as chosen
                    this.spotsSelected.add(rand);
                    this.spotsRemaining--;
                }
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
    }
}

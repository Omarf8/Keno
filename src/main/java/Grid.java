import java.util.HashSet;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

public class Grid {
    public int numSpots;
    public HashSet<Integer> spotsSelected;
    public int spotsRemaining;
    public GridPane gridSpots; // This is used for the grid that is displayed
    public Button[][] gridLookup; // If we need to access a specific button we can use the 2D array
    public boolean gridDisabled;

    public Grid() {
        this.numSpots = 0;
        this.spotsSelected = new HashSet<>();
        this.spotsRemaining = 0;
        this.gridSpots = new GridPane();
        this.gridLookup = new Button[8][10];
        initializeGrid();
        this.gridDisabled = true;
    }

    public void initializeGrid() {
        // Space between each button
        gridSpots.setHgap(9);
        gridSpots.setVgap(23);

        int i = 1;
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 10; col++) {
                Button btn = new Button(String.valueOf(i));
                btn.setOnAction(e -> {
                    // If selected returns true then change the color of the button to indicate it is selected
                    // Otherwise we should change the color back to normal
                    if(selected(Integer.parseInt(btn.getText()))) {
                        btn.setStyle("-fx-background-color: blue;");
                    }
                    else {
                        btn.setStyle("-fx-background-color: gray;");
                    }
                });
                btn.setPrefSize(35, 35);
                gridSpots.add(btn, col, row);
                gridLookup[row][col] = btn;
                i++;
            }
        }
    }

    public void clear() {
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 10; col++) {
                gridLookup[row][col].setStyle("-fx-background-color: gray;"); // Reset all button colors
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
                this.gridLookup[(rand - 1) / 10][(rand - 1) % 10].setStyle("-fx-background-color: blue;"); // Make the button appear as chosen
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

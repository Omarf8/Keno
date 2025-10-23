import java.util.HashSet;

import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;

public class Grid {
    public int numSpots;
    public HashSet<Integer> spotsSelected;
    public int spotsRemaining;
    public GridPane gridSpots;
    public boolean gridDisabled;

    public Grid() {
        this.numSpots = 0;
        this.spotsSelected = new HashSet<>();
        this.spotsRemaining = 0;
        this.gridSpots = new GridPane();
        initializeGrid();
        this.gridDisabled = false;
    }

    public void initializeGrid() {
        // Space between each button
        gridSpots.setHgap(9);
        gridSpots.setVgap(23);

        int i = 1;
        for(int row = 0; row < 8; row++) {
            for(int col = 0; col < 10; col++) {
                Button btn = new Button(String.valueOf(i));
                btn.setPrefSize(35, 35);
                gridSpots.add(btn, col, row);
                i++;
            }
        }
    }

    public void selected(int spot) {
        if (spotsSelected.contains(spot)) {
            this.spotsSelected.remove(spot);
        }
        else {
            this.spotsSelected.add(spot);
        }
    }

    public void quickSelect() {
        // Use a clear function to clear the spotsSelected
        for(int i = 0; i < numSpots; i++) {
            this.spotsSelected.add((int)(Math.random() * 80 + 1));
        }
        this.spotsRemaining = 0;
    }

}

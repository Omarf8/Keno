import java.util.HashMap;

public class GameControl {
    public SceneManager scenes;
    private int totalEarnings;
    public HashMap<Integer, HashMap<Integer, Integer>> winningsLookupTable;

    public GameControl() {
        // Initialize all parts of the game
        this.scenes = new SceneManager();
        this.totalEarnings = 0; // Earnings initially 0
        this.winningsLookupTable = new HashMap<>(); // Maps the spot game (1, 4, 8 or 10) to a HashMap that maps matches to earnings
        mapPrizes(); // Initializes prizes in the winningsLookupTable HashMap
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

    // Calculate the earnings won
//    public int calculateEarnings(int spots) {
//        // If the number of matches does not exist for the spot, return 0
//        if(winningsLookupTable.get(grid.getNumSpots()).get(spots) == null) { return 0;}
//        // Else return the amount won
//        else { return winningsLookupTable.get(grid.getNumSpots()).get(spots);}
//    }
}

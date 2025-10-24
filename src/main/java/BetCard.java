import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BetCard {
    public int moneyPerBet;
    public int numDraws;
    public GridPane betsTopHalfGrid;
    public GridPane betsBottomHalfGrid;
    public Button[][] betLookup;
    public GridPane drawsGrid;
    public Button[] drawsLookup;
    public final int gridButtonSize = 40;

    public BetCard() {
        this.moneyPerBet = 0;
        this.numDraws = 0;
        this.betLookup = new Button[2][3];
        createBetsTopHalfGrid();
        createBetsBottomHalfGrid();
        drawsLookup = new Button[4];
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
                moneyPerBet = Integer.parseInt(btn.getText().substring(1));
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            this.betsTopHalfGrid.add(btn, i, 0);
            this.betLookup[0][i] = btn;
        }
    }

    public void createBetsBottomHalfGrid() {
        this.betsBottomHalfGrid = new GridPane();
        this.betsBottomHalfGrid.setHgap(10);

        for(int i = 0; i < 2; i++) {
            Button btn = new Button("$" + (5 + 5 * i));
            btn.setOnAction(e -> {
                moneyPerBet = Integer.parseInt(btn.getText().substring(1));
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            this.betsBottomHalfGrid.add(btn, i, 1);
            this.betLookup[1][i] = btn;
        }
    }

    public void createDrawsGrid() {
        this.drawsGrid = new GridPane();

        this.drawsGrid.setHgap(10);
        for(int i = 0; i < 4; i++) {
            Button btn = new Button("$" + (i + 1));
            btn.setOnAction(e -> {
                numDraws = Integer.parseInt(btn.getText().substring(1));
            });
            btn.setPrefSize(gridButtonSize, gridButtonSize);
            this.drawsGrid.add(btn, i, 0);
            this.drawsLookup[i] = btn;
        }
    }
}

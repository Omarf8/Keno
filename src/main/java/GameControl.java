import javafx.stage.Stage;

public class GameControl {
    public SceneManager scenes;

    public GameControl(Stage primaryStage) {
        // Initialize all parts of the game
        this.scenes = new SceneManager(primaryStage); // Pass in the instance of the Stage class to change scenes within the class
    }
}

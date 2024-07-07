package edu.sdccd.cisc191;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Presents the user with the game graphical user interface
 */
public class ViewGameBoard extends Application
{
    private ControllerGameBoard controller;
    private GameBoardLabel fishRemaining;
    private GameBoardLabel guessesRemaining;
    private GameBoardLabel message;

    /**
     * launches the JavaFX application
     * @param args command line input parameters
     */
    public static void main(String[] args)
    {
        // launch the app
        launch(args);
    }

    /**
     * This method updates the header on the application
     */

    public void updateHeader() {
        //update labels
        fishRemaining.setText("Fish: " + controller.modelGameBoard.getFishRemaining());
        guessesRemaining.setText("Bait: " + controller.modelGameBoard.getGuessesRemaining());
        if(controller.fishWin()) {
            message.setText("Fishes win!");
        } else if(controller.playerWins()) {
            message.setText("You win!");
        } else {
            message.setText("Find the fish!");
        }
    }
    @Override
    public void start(Stage stage) throws Exception {
        controller = new ControllerGameBoard();
        fishRemaining = new GameBoardLabel();
        guessesRemaining = new GameBoardLabel();
        message = new GameBoardLabel();

        BorderPane root = new BorderPane();

        updateHeader();

        HBox header = new HBox(fishRemaining, guessesRemaining, message);
        root.setTop(header);

        VBox vbox = new VBox(ModelGameBoard.DIMENSION);
        for (int row=0; row < ModelGameBoard.DIMENSION; row++) {
            HBox hbox = new HBox(ModelGameBoard.DIMENSION);
            for (int col=0; col < ModelGameBoard.DIMENSION; col++) {
                GameBoardButton button = new GameBoardButton(row, col, controller.modelGameBoard.fishAt(row,col));
                int finalRow = row;
                int finalCol = col;
                button.setOnAction(e -> {
                    controller.makeGuess(finalRow, finalCol);
                    if(!controller.isGameOver()) {
                        button.handleClick();
                        updateHeader();
                    }
                });
                hbox.getChildren().add(col, button);
            }
            vbox.getChildren().add(row, hbox);
        }

        // create scene, stage, set title, and show
        root.setCenter(vbox);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Gone Fishing");
        stage.setResizable(false);
        stage.show();
    }
}
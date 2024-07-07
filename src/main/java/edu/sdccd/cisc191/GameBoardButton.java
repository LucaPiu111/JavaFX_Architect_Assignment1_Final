package edu.sdccd.cisc191;

import javafx.scene.control.Button;

/**
 * Extends the basic JavaFX Button with game functionality
 */
public class GameBoardButton extends Button {
    private int row;
    private int col;
    private boolean hasFish;
    private boolean isGuessed;

    public GameBoardButton(int row, int col, boolean hasFish)
    {
        this.row = row;
        this.col = col;
        this.hasFish = hasFish;

        setMinWidth(50);
        setPrefWidth(50);
        setText("?");
        setDisabled(false);
    }

    public void handleClick() {
        if(hasFish) {
            setText("<><");
        } else {
            setText("X");
        }
        isGuessed = true;
        setDisabled(true);
    }
}
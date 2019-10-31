package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameCanvas {
        
    private final Canvas canvas;
    private final GraphicsContext gc;
    private final int width = 830;
    private final int height = 830;
    private int[][] gameboard;

    public GameCanvas() {
        this.canvas = new Canvas(this.width, this.height);
        this.gc = this.canvas.getGraphicsContext2D();

        // Draw white background
        this.gc.setFill(Color.LIGHTGRAY);
        this.gc.fillRect(0, 0, this.width, this.height);
        this.gc.setLineWidth(3);

        // Draw pieces for gameboard
        this.gc.setFont(new Font("Arial", 80));
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                if (x < 3 || y < 3) { // Leave the last square empty
                    this.gc.setFill(Color.DIMGREY);
                    this.gc.fillRect(
                        x * (this.width - 30) / 4 + (x * 10),
                        y * (this.height - 30) / 4 + (y * 10),
                        (this.width - 30) / 4,
                        (this.height - 30) / 4
                    );
                    this.gc.setFill(Color.WHITE);
                    int pieceNumber = y * 4 + x + 1;
                    this.gc.fillText(
                        Integer.toString(pieceNumber),
                        x * (this.width - 30) / 4 + (x * 10) + (pieceNumber < 10 ? 80 : 55),
                        y * (this.height - 30) / 4 + (y * 10) + 130
                    );
                }
            }
        }
    }

    public Canvas getCanvas() {
        return this.canvas;
    }
}

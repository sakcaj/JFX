package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Random;

import static javafx.scene.layout.AnchorPane.*;

public class Controller {

    @FXML
    Button button = new Button("Click!");

    @FXML
    public void btClose() {
        Platform.exit();
    }

    @FXML
    AnchorPane ap;


    @FXML
    public void btRun() {

        double screeny = ap.getWidth();
        double screenx = ap.getHeight();


        double btx = button.getLayoutX();
        double bty = button.getLayoutY();
        double z;

        Random rand = new Random();
        z = rand.nextInt(8);

        if (button.getLayoutX() >= screenx -25 || button.getLayoutX() <= 25 || button.getLayoutY() >= screeny -25|| button.getLayoutY() <= 25) {
            button.setLayoutY(screeny / 2);
            button.setLayoutX(screenx / 2);

            System.out.println(btx);
            System.out.println(bty);
            System.out.println(screenx);
            System.out.println(screeny);
        }

        if (z == 0) {
            button.setLayoutX(btx + 15);
            button.setLayoutY(bty - 15);

        } else if (z == 1) {
            button.setLayoutX(btx - 15);
            button.setLayoutY(bty + 15);

        } else if (z == 2) {
            button.setLayoutX(btx + 15);
            button.setLayoutY(bty + 15);

        } else if (z == 3) {
            button.setLayoutX(btx - 15);
            button.setLayoutY(bty + 15);
        }
    }
}


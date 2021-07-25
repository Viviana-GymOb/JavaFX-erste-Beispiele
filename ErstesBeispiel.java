package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;

public class ErstesBeispiel extends Application {

    private String title;

    @Override
    public void init(){
        title= new String("Kreis");
    }
    @Override
    public void start(Stage stage) {

        StackPane pane=new StackPane();

        Button button=new Button();     // create a button
        button.setText("Schliessen");
        button.setOnAction(e->Platform.exit());  //close the window

        //create a circle to be put under the button
        Circle circle = new Circle(300, 135, 100);
        circle.setFill(Color.DARKSLATEBLUE);
        circle.setStroke(Color.BLACK);

        pane.getChildren().add(circle);  // add the circle first
        pane.getChildren().add(button); // then the button

        stage.setTitle(title);
        stage.setScene(new Scene(pane));    // add the panel
        //do not close the window with the close-button
        stage.setOnCloseRequest(Event::consume);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

package sample;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.stream.IntStream;


public class ZweitesBeispiel extends Application {

    private TextField punkte;
    private TextField note;
    private ComboBox<Integer> totPunkte;
    private int[] values;

    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new AnchorPane();
        GridPane gridPane= new GridPane();

        // build the components
        punkte= new TextField(); // make a field for the points
        punkte.setOnMousePressed(e-> note.setText("")); //delete the note

        note= new TextField(); // make a field for the note
        note.setEditable(false);
        note.setId("note");

        Button button= new Button();
        button.setText("Berechnen");
        button.setOnAction((e-> compute()));

        totPunkte= new ComboBox<>();  // make range 10-50
        IntStream.rangeClosed(10,50).boxed().forEach(totPunkte.getItems()::add);

        totPunkte.getSelectionModel().select(0);  // select the first one
        totPunkte.setOnMousePressed(e-> note.setText("")); //delete the note

        // make the gridPane
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        // first column
        gridPane.add(new Label("Anzahl Punkte"), 0, 0);
        gridPane.add(new Label("Gesamte Punkte"),0,1);
        gridPane.add(new Label("Note"), 0, 2);
        // second column
        gridPane.add(punkte, 1,0);
        gridPane.add(totPunkte,1,1);
        gridPane.add(note, 1,2);
        gridPane.add(button, 1,3);

        anchorPane.getChildren().add(gridPane); // add the gridPane
        anchorPane.setPrefWidth(400); // make bigger
        anchorPane.setPrefHeight(250);
        AnchorPane.setLeftAnchor(gridPane,30.); // put to the right
        AnchorPane.setTopAnchor(gridPane, 30.); // put down

        stage.setTitle("Noten berechnen");
        Scene scene = new Scene(anchorPane);
        scene.getStylesheets().add(getClass().getResource("myStyleSheet.css").toExternalForm());
        stage.setScene(scene);    // add the anchorPane
        stage.show();
    }
    private void compute(){
        String inputText=punkte.getText();  //read the input
        double result;
        int input;

        try{ //should work only if you give a number
            input=Integer.parseInt(inputText);

        } catch(NumberFormatException e){
            punkte.setText(""); // make it empty
            punkte.requestFocus();
            return;
        }

        int tot=Integer.valueOf(totPunkte.getSelectionModel().getSelectedItem());
        if(input<0 || input> tot)
        {  // the points should be positiv but less then tot
            punkte.setText(""); // make it empty
            punkte.requestFocus();
            return;
        }
        result=input*5.0/tot+1.0;
        note.setText(String.format("%.2f", result));
    }
    public static void main(String[] args) {
        launch();

    }
}

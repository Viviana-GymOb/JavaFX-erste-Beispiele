package sample;

import com.sun.javafx.logging.PlatformLogger;
import javafx.application.Application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DrittesBeispiel extends Application {

    Stage window;
    private Desktop desktop = Desktop.getDesktop(); //I need it to open then the files
    public static final ObservableList data =
            FXCollections.observableArrayList();

        public static void main(String[] args) {
            launch(args);
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            window = primaryStage;
            window.setTitle("Dateien einlesen");

            final ListView listView = new ListView(data);
            listView.setPrefSize(200, 250);
            listView.setEditable(true);

            for (int i = 0; i < 18; i++) {
                data.add(String.format("Aufgabe %d", i));
            }

            listView.setItems(data);

            //Vbox - Hauptlayout
            VBox haupt = new VBox();
            haupt.setSpacing(10);
            haupt.setPadding(new Insets(8,8,8,8));

            //HBox top, obere Leiste mit Buttons
            HBox top = new HBox();
            top.setSpacing(10);

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Wähle die Datei");


            fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Java", "*.java"),
                    new FileChooser.ExtensionFilter("TXT", "*.txt")
            );


            //Buttons in top



          //  Image speichern = new Image(getClass().getResourceAsStream("saveIcon.png"));
         //  buttonSave.setGraphic(new ImageView(speichern));

            Button buttonShow = new Button("Zeigen");
            buttonShow.setPrefSize(100, 20);

            //buttom enhält alle Elemente unter top
            HBox bottom = new HBox();
            bottom.setPadding(new Insets(0,0,0,0));
            bottom.setSpacing(10);


            //Pane kann als Zeichenfläche genutzt werden
            Pane canvas = new Pane();
            canvas.setStyle("-fx-background-color: white;");
            canvas.setPrefSize(700,550);


            Button buttonOpen = new Button("Open");
            buttonOpen.setPrefSize(100, 20);


            buttonOpen.setOnAction(e ->{
                File file= fileChooser.showOpenDialog(primaryStage);
                if (file != null) {
                    //  primaryStage.show(file);
                    openFile(file);
                    Text text = new Text(file.getName());
                    text.setX(100);
                    text.setY(200);
                    canvas.getChildren().add(text);

                }
            });


            //TabPane mit Tabs einfügen
            TabPane tabPane = new TabPane();
            tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
            Tab tab1 = new Tab();
            tab1.setText("Content");
            Tab tab2 = new Tab();
            tab2.setText("Style");
            tabPane.getTabs().addAll(tab1, tab2);
            tabPane.setPrefHeight(600);
            tabPane.setPrefWidth(220);

            Pane contentTab1 = new Pane();
            contentTab1.setStyle("-fx-background-color: #eeeeee;");
            contentTab1.getChildren().add(listView);
            tab1.setContent(contentTab1);

            Pane contentTab2 = new Pane();
            contentTab2.setStyle("-fx-background-color: #eeeeee;");
            tab2.setContent(contentTab2);

            //Buttons und Textfeld unter Tabpane
            Button insertButton = new Button();
            insertButton.setText("Add word");
            insertButton.setPrefWidth(220);

            TextField wordText = new TextField();

            //Neue Vbox mit Tabs, Textfeld und Button
            //Insert VBOX
            VBox bottomLeft = new VBox();
            bottomLeft.setPadding(new Insets(0));
            bottomLeft.setSpacing(8);
            bottomLeft.setPrefHeight(800);
            bottomLeft.setAlignment(Pos.CENTER);


            //Alle Komponenten "zusammenfügen"
            bottomLeft.getChildren().addAll(tabPane, wordText, insertButton);
            bottom.getChildren().addAll(bottomLeft, canvas);
            haupt.getChildren().addAll(top, bottom);
            Scene scene = new Scene(haupt, 950,700);
            top.getChildren().addAll(buttonOpen, buttonShow);


            AtomicInteger i = new AtomicInteger();
            //Muss benutzt werden, da normaler Integer in Lamba-Expression nicht benutzt werden kann

            //Buttonlogik
            insertButton.setOnAction((e) ->{
                if(!wordText.getText().isEmpty()){
                    Text text = new Text(wordText.getText());
                    text.setX(100);
                    text.setY(200);
                    canvas.getChildren().add(text);

                    Text t2 = new Text();
                    t2.setText(wordText.getText());
                    t2.setX(10);
                    t2.setY(25 + 20 * i.getAndIncrement());
                    contentTab1.getChildren().add(t2);


                }
            });

       window.setScene(scene);

            //window.setResizable(false);
            window.show();
        }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    DrittesBeispiel.class.getName()).log(
                           Level.SEVERE, null, ex
            );
        }
    }

}

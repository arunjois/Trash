/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.collections.FXCollections;
import javafx.scene.control.SingleSelectionModel;
import javafx.stage.Stage;


import java.util.StringJoiner;
import java.util.ArrayList;
import java.util.List;
import java.lang.String;
import java.util.Arrays;
/**
 *
 * @author arun
 */
public class Demo extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        GridPane grid = new GridPane();
        VBox box = new VBox();
        StringJoiner sj = new StringJoiner(",");

        pane.setCenter(grid);
        pane.setBottom(box);
        grid.setVgap(10);
        grid.setHgap(5);
        grid.setPadding(new Insets(10,10,10,10));

        /* TextField Labels Of GridPane*/
        Label dd,mm,yy,time, dot = new Label(":");
        Label lmin,lsec;
        TextField date, mon, year, hrs, min, sec;
        grid.add(dd = new Label("Date:"), 0, 0);
        grid.add(date = new TextField(), 1, 0);
        grid.add(mm = new Label("Mon:"), 2, 0);
        grid.add(mon = new TextField(), 3, 0);
        grid.add(yy = new Label("Year:"), 4, 0);
        grid.add(year = new TextField(), 5, 0);
        grid.add(time = new Label("Hrs:"),0, 1);
        grid.add(hrs = new TextField(), 1, 1);
        grid.add(min = new TextField(), 3, 1);
        grid.add(new Label("Min:"),2,1);
        grid.add(sec = new TextField(), 5 ,1);
        grid.add(new Label("Sec:"),4,1);
        date.setStyle("-fx-pref-width: 3em");
        mon.setStyle("-fx-pref-width: 3em");
        year.setStyle("-fx-pref-width: 4em");
        hrs.setStyle("-fx-pref-width: 3em");
        min.setStyle("-fx-pref-width: 3em");
        sec.setStyle("-fx-pref-width: 3em");


        /*TextField,label for Hbox*/
        Label country = new Label("Country");
        Label Place = new Label("Place");
        ComboBox con = new ComboBox();
        ComboBox pla = new ComboBox();
        Button ok = new Button("OK");
        box.setPadding(new Insets(5,5,5,5));
        box.setSpacing(10);
        box.setPadding(new Insets(20));


        /* Program Logic */
        Query sql = new Query();
        String str = new String();
        List<String> textCountry,places = new ArrayList<String>();
        textCountry = sql.getCountryCode();
        String temp[] = new String[textCountry.size()];
        temp = textCountry.toArray(new String[textCountry.size()]);
        /*
         * Add textCountry to UI
         */
        for(int i=0;i<temp.length;i++)
            con.getItems().add(temp[i]);
        /*
         * On Selection Send Country to query
         */
        con.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue ov, Object t, Object t1) {
                String str = new String();
                List<String> places = new ArrayList<String>();
                Query sql = new Query();

                str = con.getValue().toString();
                //System.out.print(str);
                places=sql.getPlace(str);
                String tmp[] = new String[places.size()];
                places.toArray(tmp);			//Convert to array
                Arrays.sort(tmp);			//Sort Array
                for(int i=0;i<tmp.length;i++)
                    pla.getItems().add(tmp[i]);	//Add the array to UI
            }
        });
		/*str = con.getSelectionModel().selectedItemProperty().toString();
		places = sql.getPlace(con.getSelectionModel().selectedItemProperty().toString());
		String tmp[] = new String[places.size()];
		/*
		* Adding Places to UI
		*/
        //for(int i=0;i<tmp.length;i++)
        //	pla.getItems().add(tmp[i]);

        con.getItems().addAll();
        con.setVisibleRowCount(20);
        con.setEditable(true);
        pla.setEditable(true);
        con.setStyle("-fx-pref-width: 10em");
        pla.setStyle("-fx-pref-width: 10em");
        box.getChildren().addAll(country, con, Place, pla, ok);


        ok.setOnAction(t->process());


        Scene root = new Scene(pane, 400, 300);
        primaryStage.setScene(root);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void process() {





    }
}
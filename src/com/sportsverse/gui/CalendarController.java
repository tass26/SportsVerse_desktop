/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.entities.Seance;
import com.sportsverse.services.EmplacementService;
import com.sportsverse.services.SeanceService;
import com.sportsverse.services.UserService;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class CalendarController implements Initializable {

    ZonedDateTime dateFocus;
    ZonedDateTime today;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private FlowPane calendar;
    UserService us = new UserService();
    EmplacementService ps = new EmplacementService();
    SeanceService ss= new SeanceService();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateFocus = ZonedDateTime.now();
        today = ZonedDateTime.now();
        drawCalendar();
    }

    @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendar.getChildren().clear();
        drawCalendar();
    }

private void drawCalendar() {
    
    year.setText(String.valueOf(dateFocus.getYear()));
    month.setText(String.valueOf(dateFocus.getMonth()));

    double calendarWidth = calendar.getPrefWidth();
    double calendarHeight = calendar.getPrefHeight();
    double strokeWidth = 1;
    double spacingH = calendar.getHgap();
    double spacingV = calendar.getVgap();
    
    Map<Integer, List<Seance>> calendarActivityMap = getCalendarActivitiesMonth(dateFocus);

    int monthMaxDate = dateFocus.getMonth().maxLength();
    // Check for leap year
    if (dateFocus.getYear() % 4 != 0 && monthMaxDate == 29) {
        monthMaxDate = 28;
    }
    int dateOffset = ZonedDateTime.of(dateFocus.getYear(), dateFocus.getMonthValue(), 1, 0, 0, 0, 0, dateFocus.getZone()).getDayOfWeek().getValue();

    for (int i = 0; i < 6; i++) {
        for (int j = 0; j < 7; j++) {
            StackPane stackPane = new StackPane();

            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke(Color.BLACK);
            rectangle.setStrokeWidth(strokeWidth);
            double rectangleWidth = (calendarWidth / 7) - strokeWidth - spacingH;
            rectangle.setWidth(rectangleWidth);
            double rectangleHeight = (calendarHeight / 6) - strokeWidth - spacingV;
            rectangle.setHeight(rectangleHeight);
            stackPane.getChildren().add(rectangle);

            int calculatedDate = (j + 1) + (7 * i);
            if (calculatedDate > dateOffset) {
                int currentDate = calculatedDate - dateOffset;
                if (currentDate <= monthMaxDate) {
                    Text date = new Text(String.valueOf(currentDate));
                    double textTranslationY = -(rectangleHeight / 2) * 0.75;
                    date.setTranslateY(textTranslationY);
                    stackPane.getChildren().add(date);
                        List<Seance> calendarActivities = calendarActivityMap.get(currentDate);
                        if(calendarActivities != null){
                            createCalendarActivity(calendarActivities, rectangleHeight, rectangleWidth, stackPane);
                        }
                }
                if (today.getYear() == dateFocus.getYear() && today.getMonth() == dateFocus.getMonth() && today.getDayOfMonth() == currentDate) {
                    rectangle.setStroke(Color.BLUE);
                }
            }
            calendar.getChildren().add(stackPane);
        }
    }
}


    private void createCalendarActivity(List<Seance> calendarActivities, double rectangleHeight, double rectangleWidth, StackPane stackPane) {
        VBox calendarActivityBox = new VBox();
        for (int k = 0; k < calendarActivities.size(); k++) {
            if(k >= 2) {
                Text moreActivities = new Text("...");
                calendarActivityBox.getChildren().add(moreActivities);
                moreActivities.setOnMouseClicked(mouseEvent -> {
                    //On ... click print all activities for given date
                    System.out.println(calendarActivities);
                });
                break;
            }
        java.util.Date utilDate = new java.util.Date(calendarActivities.get(k).getDate().getTime());

       //create SimpleDateFormat object with pattern "HH" for hours
      SimpleDateFormat format = new SimpleDateFormat("HH");
      SimpleDateFormat formatM = new SimpleDateFormat("mm");
      String hours = format.format(utilDate);
      String min =format.format(utilDate);
            Emplacement E = ps.read(calendarActivities.get(k).getE().getId());
            Text text = new Text(E.getGovernorat()+" "+E.getDelegation()+ "\n " + hours + ":" + min);
            String nomClient=us.getUserByAdress(calendarActivities.get(k).getAdresse_client()).getNom();
            String Message = calendarActivities.get(k).getMessage();
            calendarActivityBox.getChildren().add(text);
            text.setOnMouseClicked(mouseEvent -> {
                //On Text clicked
                //System.out.println(text.getText());
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Details de reservation");
                alert.setHeaderText("Informations");
                alert.setContentText("Le client est " + nomClient + " \nCette séance se déroule dans " + E.getAdresse() + "\nIl a laisser ce message : " + Message);

        // Show the alert and wait for user response
        alert.showAndWait();
            });
        }
        calendarActivityBox.setTranslateY((rectangleHeight / 2) * 0.20);
        calendarActivityBox.setMaxWidth(rectangleWidth * 0.8);
        calendarActivityBox.setMaxHeight(rectangleHeight * 0.65);
        calendarActivityBox.setStyle("-fx-background-color:GRAY");
        stackPane.getChildren().add(calendarActivityBox);
    }

    private Map<Integer, List<Seance>> createCalendarMap(List<Seance> calendarActivities) {
        Map<Integer, List<Seance>> calendarActivityMap = new HashMap<>();

        for (Seance activity: calendarActivities) {
            int activityDate = activity.getDate().toLocalDate().getDayOfMonth();
            if(!calendarActivityMap.containsKey(activityDate)){
                calendarActivityMap.put(activityDate, new ArrayList<>(Arrays.asList(activity)));
            } else {
                List<Seance> OldListByDate = calendarActivityMap.get(activityDate);

                List<Seance> newList = new ArrayList<>(OldListByDate);
                newList.add(activity);
                calendarActivityMap.put(activityDate, newList);
            }
        }
        return  calendarActivityMap;
    }

    private Map<Integer, List<Seance>> getCalendarActivitiesMonth(ZonedDateTime dateFocus) {
        List<Seance> seances = new ArrayList<>();
        System.out.println(UserService.getCurrentUser().getRole().substring(2, 12).equals("ROLE_COACH"));
        if(UserService.getCurrentUser().getRole().substring(2, 12).equals("ROLE_COACH")){
            seances=ss.getSeancesCoach(UserService.getCurrentUser().getId());
        }
        else{
            seances=ss.getSeancesClient(UserService.getCurrentUser().getEmail());
        }
        List<Seance> calendarActivities = new ArrayList<>();
        int year = dateFocus.getYear();
        int month = dateFocus.getMonth().getValue();
        for (Seance activity: seances) {
            if ((activity.getDate().toLocalDate().getMonth().getValue()==month) && (activity.getDate().toLocalDate().getYear()==year) && (activity.getEtat().equals("Acceptee"))){
                calendarActivities.add(activity);
                
            }
        }
        System.out.println(seances);
        return createCalendarMap(calendarActivities);
    }

    @FXML
    private void backToAccueil(MouseEvent event) {
                        try {
            Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
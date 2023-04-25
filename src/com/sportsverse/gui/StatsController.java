/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Seance;
import com.sportsverse.service.SeanceService;
import com.sportsverse.service.UserService;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StatsController implements Initializable {

    @FXML
    private LineChart lineChart;
    SeanceService ss= new SeanceService();
    UserService us = new UserService();
    @FXML
    private PieChart pieChart;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int january = 0;
        int february = 0;
        int march = 0;
        int april = 0;
        int may = 0;
        int june = 0;
        int july = 0;
        int august = 0;
        int september = 0;
        int october = 0;
        int november = 0;
        int december = 0;
        List<Seance> seances = ss.afficher();
        
        for (Seance activity: seances) {
                if(activity.getDate().toLocalDate().getMonth().getValue()==1){
                    january++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==2){
                    february++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==3){
                    january++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==4){
                    april++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==5){
                    may++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==6){
                    june++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==7){
                    july++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==8){
                    august++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==9){
                    september++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==10){
                    october++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==11){
                    november++;
                }
                if(activity.getDate().toLocalDate().getMonth().getValue()==12){
                    december++;
                }
  
            }
        XYChart.Series series = new XYChart.Series();
        series.setName("Nombre des seances");
        if (january != 0){
            series.getData().add(new XYChart.Data("Jan",january));
        }
        if(february!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
        }
        if(march!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
        }
        if(april!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
        }
        if(may!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
        }
        if(june!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
            series.getData().add(new XYChart.Data("Jun",june));
        }
        if(july!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
            series.getData().add(new XYChart.Data("Jun",june));
            series.getData().add(new XYChart.Data("Jul",july));
        }
        if(august!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
            series.getData().add(new XYChart.Data("Jun",june));
            series.getData().add(new XYChart.Data("Jul",july));
            series.getData().add(new XYChart.Data("Aug",august));
        }
        if(october!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
            series.getData().add(new XYChart.Data("Jun",june));
            series.getData().add(new XYChart.Data("Jul",july));
            series.getData().add(new XYChart.Data("Aug",august));
            series.getData().add(new XYChart.Data("Oct",october));
        }
        if(november!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
            series.getData().add(new XYChart.Data("Jun",june));
            series.getData().add(new XYChart.Data("Jul",july));
            series.getData().add(new XYChart.Data("Aug",august));
            series.getData().add(new XYChart.Data("Oct",october));
            series.getData().add(new XYChart.Data("Nov",november));
        }
        if(december!=0){
            series.getData().add(new XYChart.Data("Jan",january));
            series.getData().add(new XYChart.Data("Feb",february));
            series.getData().add(new XYChart.Data("Mar",march));
            series.getData().add(new XYChart.Data("Apr",april));
            series.getData().add(new XYChart.Data("May",may));
            series.getData().add(new XYChart.Data("Jun",june));
            series.getData().add(new XYChart.Data("Jul",july));
            series.getData().add(new XYChart.Data("Aug",august));
            series.getData().add(new XYChart.Data("Oct",october));
            series.getData().add(new XYChart.Data("Nov",november));
            series.getData().add(new XYChart.Data("Dec",december));
        }
        
        lineChart.getData().add(series);
        Map<String, Integer> coachs = new HashMap<>();
        for (Seance activity: seances){
            if (coachs.containsKey(us.read(activity.getC().getId()).getNom()) == false){
                coachs.put(us.read(activity.getC().getId()).getNom(), 1);
            }
            else{
                int currentValue  = coachs.get(us.read(activity.getC().getId()).getNom());
                //System.out.println("currentValue = " + currentValue);
                int updatedValue = currentValue + 1;
                //System.out.println("updatedValue = " + updatedValue);
                coachs.put(us.read(activity.getC().getId()).getNom(), updatedValue);
            }
        }
        /*for (Map.Entry<String, Integer> entry : coachs.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println("Name: " + key + ", nbres: " + value);
        }*/
        ObservableList<PieChart.Data> pieChartData=
                FXCollections.observableArrayList(
                );
        for (Map.Entry<String, Integer> entry : coachs.entrySet()) {
        pieChartData.addAll(new PieChart.Data(entry.getKey(),entry.getValue()));
        }
        pieChart.setData(pieChartData);
    }    

    @FXML
    private void backToAccueil(MouseEvent event) {
                        try {
            Parent root = FXMLLoader.load(getClass().getResource("SuiviSeanceClient.fxml"));
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

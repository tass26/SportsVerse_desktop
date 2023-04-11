/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.gui;

import com.sportsverse.entities.Emplacement;
import com.sportsverse.service.EmplacementService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DemandeModificationController implements Initializable {

    @FXML
    private DatePicker Date;
    @FXML
    private TextField Duree;
    @FXML
    private ComboBox<String> Emplacement;
    @FXML
    private TextField Message;
        EmplacementService ep = new EmplacementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                List<Emplacement> emplacements = ep.afficher();
        String[] emplacementChoix = new String[emplacements.size()];
        for (int i = 0; i < emplacements.size(); i++) {
            emplacementChoix[i] = emplacements.get(i).toString();
        }
        Emplacement.getItems().addAll(emplacementChoix);
        
    }    

    public void setDate(DatePicker Date) {
        this.Date = Date;
    }

    public void setDuree(TextField Duree) {
        this.Duree = Duree;
    }

    public void setMessage(TextField Message) {
        this.Message = Message;
    }
    
}

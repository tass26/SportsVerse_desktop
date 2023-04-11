/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.CommandeService;
import java.io.IOException;
import static java.lang.Math.abs;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class CommandeController implements Initializable {

    @FXML
    private Label CommandeId;
    @FXML
    private Label EtatCommande;
    @FXML
    private Label Date;
    private int id;
    private Date date;
    private String etat;
    @FXML
    private AnchorPane anchor_commande;
    CommandeDetailsController cdc;
    @FXML
    private Button btnannuler;
    double days;
    Commande currentCommande;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private final String[] colors = {"e6d2b5", "9f7866"};

    public int SetData(Commande c) throws SQLException {
        id = c.getId();
        CommandeId.setText(String.valueOf(c.getId()));
        EtatCommande.setText(c.getEtat());
        Date.setText(String.valueOf(c.getDate()));
        
        
        long difference = c.getDate().getTime() - System.currentTimeMillis();
        days = TimeUnit.MILLISECONDS.toDays(difference);
        date = c.getDate();
        etat = c.getEtat();
        btnannuler.setVisible( (abs(days) < 1 && !c.getEtat().equals("ANNULER")));
        anchor_commande.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" + "-fx-background-radius: 15;" + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0),10,0,0,10);");
        return c.getId();

    }

    public Label getCommandeId() {
        return CommandeId;
    }

    public Label getEtatCommande() {
        return EtatCommande;
    }

    public Label getDate() {
        return Date;
    }

    public int getId() {
        return id;
    }

    public AnchorPane getAnchor_commande() {
        return anchor_commande;
    }

    public String[] getColors() {
        return colors;
    }

    public void setCommandeId(Label CommandeId) {
        this.CommandeId = CommandeId;
    }

    public void setEtatCommande(Label EtatCommande) {
        this.EtatCommande = EtatCommande;
    }

    public void setDate(Label Date) {
        this.Date = Date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnchor_commande(AnchorPane anchor_commande) {
        this.anchor_commande = anchor_commande;
    }

    public CommandeDetailsController getCdc() {
        return cdc;
    }

    public void setCdc(CommandeDetailsController cdc) {
        this.cdc = cdc;
    }

    @FXML
    private void AnnulerCommande(ActionEvent event) throws SQLException {
        CommandeService cs = new CommandeService();
        cs.Annuler(id);
    }

    @FXML
    public void select_one(MouseEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeDetails.fxml"));
        try {
            Parent root = loader.load();
            cdc = loader.getController();
            cdc.setId(id);
            cdc.afficher();
            CommandeId.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

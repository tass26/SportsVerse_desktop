/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.CommandeService;
import Services.LignedeCommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class CommandeAdminController implements Initializable {

    @FXML
    private TableView<Commande> tabc;
    @FXML
    private TableColumn<Commande, String> User;
    @FXML
    private TableColumn<Commande, String> CodePromo;
    @FXML
    private TableColumn<Commande, Date> Date;
    @FXML
    private TableColumn<Commande, String> Etat;
    @FXML
    private TableColumn<Commande, Integer> Id;
    CommandeDetailsController cdc;
    @FXML
    private Button livredbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        User.setCellValueFactory(new PropertyValueFactory<>("user_id_id"));
        CodePromo.setCellValueFactory(new PropertyValueFactory<>("code_id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        CommandeService cs = new CommandeService();
        List<Commande> c = cs.ShowC();
        ObservableList<Commande> list = FXCollections.observableArrayList(c);

        tabc.setItems(list);
    }

    @FXML
    private void getCategorie(MouseEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException {
        Commande selectedOrder = tabc.getSelectionModel().getSelectedItem();
        if (selectedOrder == null) {
            // Show an error message if no row is selected
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Veuillez sélectionner un test à supprimer !");
            alert.showAndWait();
            return;
        }

        // Show a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Voulez-vous vraiment supprimer la commande sélectionné ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            CommandeService cs = new CommandeService();
            tabc.getItems().forEach(System.out::println);
            LignedeCommandeService ldc = new LignedeCommandeService();
            ldc.deleteByIdC(selectedOrder.getId());
            cs.delete(selectedOrder.getId());
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setContentText("Commande supprimé!");
            alert2.showAndWait();
        }
        UpdateTable();
    }

    @FXML
    private void Livred(ActionEvent event) throws SQLException {
        CommandeService cs = new CommandeService();
        Commande selectedOrder = tabc.getSelectionModel().getSelectedItem();
        cs.Livred(selectedOrder.getId());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText("Commande est livré!");
        alert.showAndWait();
        UpdateTable();

    }

    @FXML
    private void Show(ActionEvent event) {
        Commande selectedOrder = tabc.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CommandeDetails.fxml"));
        try {
            Parent root = loader.load();
            cdc = loader.getController();
            cdc.setId(selectedOrder.getId());
            cdc.afficher();
            livredbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void UpdateTable() {
        Id.setCellValueFactory(new PropertyValueFactory<>("id"));
        User.setCellValueFactory(new PropertyValueFactory<>("user_id_id"));
        CodePromo.setCellValueFactory(new PropertyValueFactory<>("code_id"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Etat.setCellValueFactory(new PropertyValueFactory<>("Etat"));
        CommandeService cs = new CommandeService();
        List<Commande> c = cs.ShowC();
        ObservableList<Commande> list = FXCollections.observableArrayList(c);

        tabc.setItems(list);

    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Services.CommandeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class MesCommandesController implements Initializable {

    @FXML
    private ScrollPane product;
    @FXML
    private GridPane container;
    @FXML
    private TextField SearchBar;
    @FXML
    private Label ConnectedUsr;
    @FXML
    private ImageView ConnectedAvtr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        int column = 0;
        int row = 1;
        try {
            ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
            CommandeService cs = new CommandeService();
            ArrayList<Commande> commandes = (ArrayList<Commande>) cs.findByIdU(1);

            for (Commande c : commandes) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Commande.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.getMessage();
                }
                CommandeController controller = loader.getController();
                controller.SetData(c);

                AnchorPane p = new AnchorPane(new Node[]{controller.getAnchor_commande()});
                //Total.setText(String.valueOf(total));
                pubss.add(p);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                container.add(p, column++, row);
                GridPane.setMargin(p, new Insets(10, 10, 10, 50));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Home(MouseEvent event) {
    }

    @FXML
    private void MesCommandes(MouseEvent event) {
    }

    @FXML
    private void GoCart(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            product.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}

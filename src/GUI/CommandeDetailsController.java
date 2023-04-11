/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Commande;
import Entities.LignedeCommande;
import Entities.Product;
import Services.CodepromoService;
import Services.CommandeService;
import Services.LignedeCommandeService;
import Services.ProductService;
import java.io.FileNotFoundException;
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
public class CommandeDetailsController implements Initializable {

    @FXML
    private ScrollPane product;
    @FXML
    private GridPane container;
    private int id;
    @FXML
    private TextField SearchBar;
    @FXML
    private Label ConnectedUsr;
    @FXML
    private ImageView ConnectedAvtr;
    @FXML
    private Label Total;
    private double Totalp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void afficher() throws FileNotFoundException {
        int column = 0;
        int row = 1;
        LignedeCommandeService lcs = new LignedeCommandeService();
        ProductService ps = new ProductService();
        CodepromoService cps = new CodepromoService();
        CommandeService cs = new CommandeService();

        Commande c = cs.findById(id);

  
        try {
            ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
            ArrayList<LignedeCommande> lignedeCommandes = (ArrayList<LignedeCommande>) lcs.findByIdC(id);

            for (LignedeCommande lignedeCommande : lignedeCommandes) {
                Product p = ps.findById(lignedeCommande.getIdP());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.getMessage();
                }
                ProductController controller = loader.getController();
                    controller.SetDataForOrders(p, lignedeCommande.getQte(), 1);
                    Totalp += p.getPrix_ttc() * lignedeCommande.getQte();
                AnchorPane ap = new AnchorPane(new Node[]{controller.getAnchor_product()});
                //Total.setText(String.valueOf(total));
                pubss.add(ap);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                container.add(ap, column++, row);
                GridPane.setMargin(ap, new Insets(10, 10, 10, 50));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Total.setText(String.valueOf(Totalp));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    @FXML
    private void GoCart(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            ConnectedUsr.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void MesCommandes(MouseEvent event) {
    }

}

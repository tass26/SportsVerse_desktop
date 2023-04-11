/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Codepromo;
import Entities.Commande;
import Entities.LignedeCommande;
import Entities.Product;
import Services.CodepromoService;
import Services.CommandeService;
import Services.LignedeCommandeService;
import Services.ProductService;
import Tools.CartSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.control.Alert;
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
public class CartUserController implements Initializable {

    private int id;

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
    @FXML
    private Label Total;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ProductService ps = new ProductService();
        CommandeService cs = new CommandeService();
        CartSession cartSession = CartSession.getInstance(1);
        List<Product> cart = cartSession.getCart();
        int column = 0;
        int row = 1;
        System.out.println(cartSession.getCp());
        ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
        for (Product p : cart) {
            System.out.println(p);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
            try {
                loader.load();
            } catch (IOException ex) {
                ex.getMessage();
            }
            ProductController controller = loader.getController();
            try {
                controller.getBuybtn().setVisible(false);
                controller.SetDataForCart(p);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CartUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        Total.setText(String.valueOf(cartSession.getTotal()));
    }

    @FXML
    private void Commande(ActionEvent event) throws SQLException {
        CartSession cartSession = CartSession.getInstance(1);
        List<Product> cart = cartSession.getCart();
        CommandeService cs = new CommandeService();
        LignedeCommandeService lcs = new LignedeCommandeService();
        Commande c = new Commande(1, 1  , new java.sql.Date(System.currentTimeMillis()), "ENCOURS");
        cs.add(c);
        for (Product p : cart) {
            LignedeCommande lc = new LignedeCommande(p.getId(), c.getId(), p.getQuantiteincart());
            lcs.add(lc);
        }
        cartSession.clearCart();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @FXML
    private void MesCommandes(MouseEvent event) {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MesCommandes.fxml"));
        try {
            Parent root = loader.load();
            MesCommandesController ac = loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void Home(MouseEvent event) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        try {
            Parent root = loader.load();
            HomeController ac = loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void GoCart(ActionEvent event) {
    }

    @FXML
    private void Vider(ActionEvent event) {
        CartSession cartSession = CartSession.getInstance(1);
        cartSession.clearCart();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



}

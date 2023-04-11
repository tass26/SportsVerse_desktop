/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Product;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class HomeController implements Initializable {

    @FXML
    private ScrollPane Card;
    @FXML
    private GridPane ProductContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
               int column = 0;
        int row = 1;
        ProductService ps = new ProductService();

        try {
            ObservableList<AnchorPane> pubss = FXCollections.observableArrayList();
            ArrayList<Product> products = (ArrayList<Product>) ps.show();
            
            for (Product p : products) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Product.fxml"));
                try {
                    loader.load();
                } catch (IOException ex) {
                    ex.getMessage();
                }
                ProductController controller = loader.getController();
                //controller.getBuybtn().setVisible(true);
                controller.SetDataForCart(p);
                AnchorPane ap = new AnchorPane(new Node[]{controller.getAnchor_product()});
                //Total.setText(String.valueOf(total));
                pubss.add(ap);
                if (column == 1) {
                    column = 0;
                    ++row;
                }
                ProductContainer.add(ap, column++, row);
                GridPane.setMargin(ap, new Insets(10, 10, 10, 50));
            }

        } catch (SQLException ex) {
            Logger.getLogger(CommandeDetailsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }    

    @FXML
    private void GoCart(ActionEvent event) {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            ProductContainer.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Product;
import Tools.CartSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Chaima
 */
public class ProductController implements Initializable {

    @FXML
    private Label productName;
    @FXML
    private Label Total;
    @FXML
    private AnchorPane anchor_product;
    @FXML
    private Button btnmax;
    @FXML
    private Button btnmin;
    @FXML
    private Label qte;

    @FXML
    private Button Buybtn;
    

    @FXML
    private ImageView Attachement;
    
    
        Product currentProduct;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
                String path = "\"C:\\Users\\Chaima\\OneDrive\\Bureau\\ballon.png\"";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // Create an Image object with the InputStream
        Image image = new Image(fileInputStream);
        Attachement.setImage(image);

        
        //Buybtn.setVisible(false);

    }

    public AnchorPane getAnchor_product() {
        return anchor_product;
    }

    public Button getBuybtn() {
        return Buybtn;
    }

    public void setAnchor_product(AnchorPane anchor_product) {
        this.anchor_product = anchor_product;
    }

    public Label getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.setText(productName);
    }

    public void setTotal(Double Total) {
        this.Total.setText(String.valueOf(Total));
    }

    private final String[] colors = {"e6d2b5", "9f7866"};

    public int SetDataForCart(Product p) throws FileNotFoundException{
        currentProduct = p;
        productName.setText(p.getNom_produit());
        Total.setText(String.valueOf(p.getPrix_ttc() * p.getQuantiteincart()));
        qte.setText(String.valueOf(p.getQuantiteincart()));
        if (Integer.parseInt(qte.getText()) == 10) {
            btnmax.setVisible(false);
            btnmin.setVisible(true);
        }

        if (Integer.parseInt(qte.getText()) == 1) {
            btnmin.setVisible(false);
            btnmax.setVisible(true);
        }

        anchor_product.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" + "-fx-background-radius: 15;" + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0),10,0,0,10);");
        return p.getId();

    }

    public int SetDataForOrders(Product p, int Orderqte, int promovalue) throws SQLException {
        currentProduct = p;
        productName.setText(p.getNom_produit());
        Total.setText(String.valueOf((p.getPrix_ttc() - (p.getPrix_ttc() * promovalue) /100) * Orderqte ));
        qte.setText(String.valueOf(Orderqte));
        btnmax.setVisible(false);
        btnmin.setVisible(false);

        anchor_product.setStyle("-fx-background-color: #" + colors[(int) (Math.random() * colors.length)] + ";" + "-fx-background-radius: 15;" + "-fx-effect: dropShadow(three-pass-box,rgba(0,0,0,0),10,0,0,10);");
        return p.getId();

    }

    @FXML
    private void IncrementQte(ActionEvent event) throws SQLException {
        CartSession cartSession = CartSession.getInstance(1);
        cartSession.addToCart(currentProduct);
        for (Product p : cartSession.getCart()){
            System.out.println(p);
        }
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void DecrementQte(ActionEvent event) throws SQLException {
        
        CartSession cartSession = CartSession.getInstance(1);
        cartSession.removeFromCart(currentProduct);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("CartUser.fxml"));
        try {
            Parent root = loader.load();
            CartUserController ac = loader.getController();
            Total.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void Buy(ActionEvent event) {
        int userId = 1;
        currentProduct.setQuantiteincart(1);
        CartSession cartSession = CartSession.getInstance(userId);
        cartSession.addToCart(currentProduct);

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Entities.Codepromo;
import Entities.Product;

/**
 *
 * @author Chaima
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartSession {

    private int userId;
    private List<Product> cart;
    private Codepromo cp;

    private static Map<Integer, CartSession> instances = new HashMap<>();

    private CartSession(int userId) {
        this.userId = userId;
        this.cart = new ArrayList<>();
    }

    public static CartSession getInstance(int userId) {
        CartSession instance = instances.get(userId);
        if (instance == null) {
            instance = new CartSession(userId);
            instances.put(userId, instance);
        }
        return instance;
    }

    public void addToCart(Product product) {
        for (Product p : cart) {
            if (p.getNom_produit().equals(product.getNom_produit())) {
                // Product already exists, update its quantity and return
                p.setQuantiteincart(p.getQuantiteincart() + 1);

                return;
            }
        }
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        for (Product p : cart) {
            if (p.getNom_produit().equals(product.getNom_produit())) {
                int newQuantity = p.getQuantiteincart() - 1;
                if (newQuantity == 0) {
                    cart.remove(product);
                } else {
                    p.setQuantiteincart(newQuantity);
                    product.setQuantiteincart(newQuantity);
                }
                return;
            }
        }
    }

    public List<Product> getCart() {
        return cart;
    }

    public Codepromo getCp() {
        return cp;
    }

    public void setCp(Codepromo cp) {
        this.cp = cp;
    }


    public double getTotal() {
        double total = 0;
        for (Product product : cart) {
            total += product.getPrix_ttc() * product.getQuantiteincart();
        }
        return total;
    }


        public void clearCart() {
        cart.clear();
    }
}

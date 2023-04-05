/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sportsverse.service;

import java.util.List;

/**
 *
 * @author user
 */
public interface NewInterface <T>{
    public T read(int id);
    public void ajouter(T t);
    public List<T> afficher();
    public void supprimer(T t);
    public void update(T t);
}

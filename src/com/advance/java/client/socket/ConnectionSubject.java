/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.advance.java.client.socket;

/**
 *
 * @author User
 */
public interface ConnectionSubject {
    public void registerObserver(ConnectionObserver observer);
    public void removeObserver(ConnectionObserver observer);
    public void notifyObservers(String msg);
}

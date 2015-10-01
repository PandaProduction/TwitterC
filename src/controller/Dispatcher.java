/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.ihm.CreateAccountFrameInitializer;
import view.InscriptionCSFrame;


public class Dispatcher implements ActionListener {

    /**
     * Distribue les actions de l'utilsiateur à des traitements
     *
     * @param e Evénement décrivant l'action à réaliser
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionName = e.getActionCommand() + "Action";
        try {
            Method actionToPerform = Dispatcher.class.getDeclaredMethod(actionName);
            actionToPerform.invoke(this);
        } catch (InvocationTargetException 
                |IllegalArgumentException 
                |IllegalAccessException 
                |NoSuchMethodException 
                |SecurityException ex ) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, "Unknown action: " + actionName, ex);
        }
    }

    public void logAccountAction() {
        
    }

    public void updateAccountAction() {
        
    }

    
    public void forgottenPasswordAction() {
        
    }
    

    public void forgottenLoginAction() {
        
    }
    
    public void logoutAction() {
        
    }

    public void createAccountAction() { // compte cookie swipe a créé
                System.err.println("create");
    }
    
    public void inscriptionAction() {
        System.err.println("Inscription");
        new CreateAccountFrameInitializer(new InscriptionCSFrame()).execute();
        
    }
}
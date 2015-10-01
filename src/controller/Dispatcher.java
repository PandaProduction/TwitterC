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

import module.backoffice.ConnectAccountAction;
import module.backoffice.DisconectAccountAction;
import network.messageFramework.DeliverySystem;
import network.messageFramework.FrameworkMessage;
import view.component.CookieSwipeFrame;
import view.component.CookieSwipePasswordField;
import view.component.CookieSwipeTextField;
import cookie.swipe.application.CookieSwipeApplication;
import java.io.File;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import model.User;
import module.backoffice.CreateCSAccountAction;
import module.ihm.CreateAccountFrameInitializer;
import module.ihm.LoginForgottenFrameInitializer;
import module.ihm.PasswordForgottenFrameInitializer;
import view.LoginForgottenCSFrame;
import view.PasswordForgottenCSFrame;


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
        
    }
    
    public void inscriptionAction() {
        
    }
}
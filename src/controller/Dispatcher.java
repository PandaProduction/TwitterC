/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import panda.prod.application.PandaProdApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.backoffice.ConnectAccountAction;
import module.backoffice.CreateAccountAction;
import module.ihm.InscriptionFrameInitializer;
import view.InscriptionPPFrame;
import view.MainPPFrame;
import view.component.PandaProdPasswordField;
import view.component.PandaProdTextField;

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
        } catch (InvocationTargetException | IllegalArgumentException | IllegalAccessException | NoSuchMethodException | SecurityException ex) {
            Logger.getLogger(Dispatcher.class.getName()).log(Level.SEVERE, "Unknown action: " + actionName, ex);
        }
    }

    public void logAccountAction() {
        System.err.println("log");
        PandaProdApplication application = PandaProdApplication.getApplication();
        String login = ((PandaProdTextField) application.getMainFrameJComponent("pandaProdTextFieldLogin")).getText();
        String pwd = new String(((PandaProdPasswordField) application.getMainFrameJComponent("pandaProdPasswordFieldPassword")).getPassword());
        boolean connect = new ConnectAccountAction().execute(login, pwd);
        if (connect) {
            application.getMainFrame().dispose();
            application.setMainFrame(new MainPPFrame());
        }
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
        PandaProdApplication application = PandaProdApplication.getApplication();
        String login = ((PandaProdTextField) application.getFocusFrameJComponent("pandaProdTextFieldLoginAdressMail")).getText();
        String pwd = new String(((PandaProdPasswordField) application.getFocusFrameJComponent("pandaProdPasswordFieldPassword")).getPassword());
        String backup = ((PandaProdTextField) application.getFocusFrameJComponent("pandaProdTextFieldBackupMail")).getText();
        boolean created = new CreateAccountAction().execute(login, backup, pwd);
        if (created) {
            application.getFocusFrame().dispose();
        }
    }

    public void inscriptionAction() {
        System.err.println("Inscription");
        PandaProdApplication application = PandaProdApplication.getApplication();
        application.setFocusFrame(new InscriptionPPFrame());
        new InscriptionFrameInitializer(application.getFocusFrame()).execute();

    }
}

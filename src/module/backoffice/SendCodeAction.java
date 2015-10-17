/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import controller.Dispatcher;
import dao.twitter.ConnectionTwitter;
import errorMessage.CodeError;
import interfaces.IAction;
import javax.swing.JOptionPane;
import module.ihm.MainFrameInitializer;
import panda.prod.application.PandaProdApplication;
import view.component.PandaProdFrame;

/**
 *
 * @author Lucas
 */
public class SendCodeAction implements IAction {

    private static SendCodeAction instance = null;

    private SendCodeAction() {
        
    }

    public static SendCodeAction getSendCodeAction() {
        if (instance == null) {
            instance = new SendCodeAction();
        }

        return instance;
    }

    @Override
    public boolean execute(Object... object) {
        String code = (String) object[0];

        if (ConnectionTwitter.codeValide(PandaProdApplication.getApplication().getUser(), code) != CodeError.SUCESS) {
            new JOptionPane();
            JOptionPane.showMessageDialog(null, "Connexion impossible, merci de vérifier votre login",
                    "Connexion à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}

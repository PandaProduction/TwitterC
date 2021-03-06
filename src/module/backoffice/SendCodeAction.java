/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import dao.twitter.ConnectionTwitter;
import errorMessage.CodeError;
import interfaces.IAction;
import javax.swing.JOptionPane;
import panda.prod.application.PandaProdApplication;

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
            //new JOptionPane();
            JOptionPane.showMessageDialog(null, "Connexion impossible, merci de vérifier le code pin",
                    "Connexion à Twitter", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

}

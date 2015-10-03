/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import errorMessage.CodeError;
import interfaces.IAction;
import javax.swing.JOptionPane;
import model.User;
import utils.Util;

/**
 *
 * @author Yehouda
 */
public class CreateAccountAction implements IAction {

    @Override
    public boolean execute(Object... object) {
        String login = (String) object[0];
        String backup = (String) object[1];
        String password = (String) object[2];

        if (!Util.isWellFormedMail(backup) || !Util.isWellFormedMail(login)) {
            JOptionPane.showMessageDialog(null, "Votre mail est mal écrit",
                    "Création à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        User u = new User(login, backup, password);
        int code = u.create();
        JOptionPane jOptionPane = new JOptionPane();
        if (code == CodeError.SUCESS) {
            JOptionPane.showMessageDialog(null, "La création du compte à été effectué",
                    "Création à Cookie Swipe", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            if (code == CodeError.STATEMENT_INTEGRITY_CONSTRAINT_VIOLATION) {
                JOptionPane.showMessageDialog(null, "Cette adresse mail est déjà utilisé",
                        "Création à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "La création du compte a échoué",
                        "Création à Cookie Swipe", JOptionPane.ERROR_MESSAGE);
            }
            return false;
        }
    }

}

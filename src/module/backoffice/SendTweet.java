/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import interfaces.IAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import model.User;
import panda.prod.application.PandaProdApplication;
import twitter4j.TwitterException;

/**
 *
 * @author Lucas
 */
public class SendTweet implements IAction {

    private static SendTweet instance = null;

    private SendTweet() {

    }

    public static SendTweet getSendTweet() {
        if (instance == null) {
            instance = new SendTweet();
        }

        return instance;
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();
        try {
            if (((JTextArea) application.getMainFrameJComponent("jTextAreaNewTweet")).getText().length() > 140) {
                JOptionPane.showMessageDialog(null, "Nombre de carctère trop important !",
                        "Tweet impossible", JOptionPane.ERROR_MESSAGE);

                return false;
            } else {
                user.getTwitter().updateStatus(((JTextArea) application.getMainFrameJComponent("jTextAreaNewTweet")).getText());

            }
        } catch (TwitterException ex) {
            Logger.getLogger(SendTweet.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Impossible d'envoyer ce tweet.",
                    "Tweet impossible", JOptionPane.ERROR_MESSAGE);

            return false;
        }

        user.loadMyTweet();
        user.loadNumber();
        user.loadTimeLine();
        JOptionPane.showMessageDialog(null, "Le tweet c'est envolé ! ",
                "Tweet envoyé", JOptionPane.INFORMATION_MESSAGE);

        return true;
    }

}

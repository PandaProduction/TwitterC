/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import errorMessage.CodeError;
import interfaces.IAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JList;
import javax.swing.JTextArea;
import model.User;
import panda.prod.application.PandaProdApplication;
import twitter4j.Status;
import twitter4j.TwitterException;

/**
 *
 * @author Lucas
 */
public class Retweet implements IAction {

    private static Retweet instance;

    private Retweet() {

    }

    public static Retweet getRetweet() {
        if (instance == null) {
            instance = new Retweet();
        }

        return instance;
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();
        user.loadMyTweet();
        user.loadNumber();
        try {
            JList jlist = (JList) application.getMainFrameJComponent("jListTweet");
            Status status = (Status) jlist.getSelectedValue();
            user.getTwitter().retweetStatus(status.getId());
        } catch (TwitterException ex) {
            Logger.getLogger(SendTweet.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

        return true;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.backoffice;

import interfaces.IAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import model.User;
import panda.prod.application.PandaProdApplication;
import twitter4j.TwitterException;

/**
 *
 * @author Lucas
 */
public class SendTweet implements IAction{

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
            user.getTwitter().updateStatus(((JTextArea) application.getMainFrameJComponent("jTextAreaNewTweet")).getText());
        } catch (TwitterException ex) {
            Logger.getLogger(SendTweet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
}

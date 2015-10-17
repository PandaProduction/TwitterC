/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import module.backoffice.SendCodeAction;
import panda.prod.application.PandaProdApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.backoffice.SendTweet;
import module.ihm.MainFrameInitializer;
import module.ihm.MyFriendFrameInitializer;
import module.ihm.MyTweetFrameInitializer;
import view.MainPPFrame;
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

    public void sendValideCodeAction() {
        System.err.println("Send valide code");
        PandaProdApplication application = PandaProdApplication.getApplication();
        String code = ((PandaProdTextField) application.getMainFrameJComponent("pandaProdTextFieldCodeTwitter")).getText();
        boolean sendCode = new SendCodeAction().execute(code);
        if (sendCode) {
            application.getMainFrame().dispose();
            application.setMainFrame(new MainPPFrame());
            new MainFrameInitializer(application.getMainFrame()).execute();
        }
    }

    public void sendTweetAction() {
        System.err.println("Send tweet");
        new SendTweet().execute();
    }

    public void seeMyTweetsAction() {
        System.err.println("See my tweet");
        PandaProdApplication application = PandaProdApplication.getApplication();
        new MyTweetFrameInitializer(application.getMainFrame()).execute();
    }

    public void seeMyFollowersAction() {
        System.err.println("See my followers");
    }

    public void seeMyFriendsAction() {
        System.err.println("See my friends");
        PandaProdApplication application = PandaProdApplication.getApplication();
        new MyFriendFrameInitializer(application.getMainFrame()).execute();
    }

}

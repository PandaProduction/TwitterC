package controller;

import module.backoffice.SendCodeAction;
import panda.prod.application.PandaProdApplication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import module.backoffice.Retweet;
import module.backoffice.SendTweet;
import module.ihm.MainFrameInitializer;
import module.ihm.MyFollowerFrameInitializer;
import module.ihm.MyFriendFrameInitializer;
import module.ihm.MyTweetFrameInitializer;
import view.MainPPFrame;
import view.component.PandaProdTextField;

/**
 *
 * Listener des bouton permettant de lancer des traitement ou des mises à jours
 * graphique. Cette classe est un singleton.
 *
 * @author Mary
 */
public class Dispatcher implements ActionListener {

    private static Dispatcher instance = null;

    private Dispatcher() {

    }

    public static Dispatcher getDispatcher() {

        if (instance == null) {
            instance = new Dispatcher();
        }
        return instance;
    }

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

    /**
     * Envoie le code de validation et initialise la fenêtre principale.
     */
    public void sendValideCodeAction() {
        PandaProdApplication application = PandaProdApplication.getApplication();
        String code = ((PandaProdTextField) application.getMainFrameJComponent("pandaProdTextFieldCodeTwitter")).getText();
        boolean sendCode = SendCodeAction.getSendCodeAction().execute(code);
        if (sendCode) {
            application.getMainFrame().dispose();
            application.setMainFrame(new MainPPFrame());
            MainFrameInitializer.getMainFrameInitializer(application.getMainFrame()).execute();
        }
    }

    /**
     * Envoie un tweet.
     */
    public void sendTweetAction() {
        SendTweet.getSendTweet().execute();
    }

    /**
     * Envoie un retweet.
     */
    public void retweetAction() {
        //System.err.println("retweet");
        Retweet.getRetweet().execute();
    }

    /**
     * Affiche les tweets de l'utilisateur.
     */
    public void seeMyTweetsAction() {
        PandaProdApplication application = PandaProdApplication.getApplication();
        MyTweetFrameInitializer.getMyTweetFrameInitializer(application.getMainFrame()).execute();
    }

    /**
     * Affiche les abonnés de l'utilsiateur.
     */
    public void seeMyFollowersAction() {
        PandaProdApplication application = PandaProdApplication.getApplication();
        MyFollowerFrameInitializer.getMyFollowerFrameInitializer(application.getMainFrame()).execute();
    }

    /**
     * Affiche les abonnements de l'utilisateur.
     */
    public void seeMyFriendsAction() {
        PandaProdApplication application = PandaProdApplication.getApplication();
        MyFriendFrameInitializer.getMyFriendFrameInitializer(application.getMainFrame()).execute();
    }

    /**
     * Renvoie à la page d'acceuil.
     */
    public void backAction() {
        PandaProdApplication application = PandaProdApplication.getApplication();
        MainFrameInitializer.getMainFrameInitializer(application.getMainFrame()).execute();
    }
}

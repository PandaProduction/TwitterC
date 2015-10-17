/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.AbstractIHMAction;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import model.User;
import panda.prod.application.PandaProdApplication;
import twitter4j.Status;
import view.component.PandaProdButton;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;

/**
 *
 * @author Lucas
 */
public class MainFrameInitializer extends AbstractIHMAction {

    private static MainFrameInitializer instance = null;

    private MainFrameInitializer(PandaProdFrame csFrame) {
        super(csFrame);
    }

    public static MainFrameInitializer getMainFrameInitializer(PandaProdFrame csFrame) {
        if (instance == null) {
            instance = new MainFrameInitializer(csFrame);
        }

        return instance;
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();
        Dispatcher dispatcher = Dispatcher.getDispatcher();

        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelNickname");
        label.setText(user.getName());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTwitterName");
        label.setText("@" + user.getTwitterName());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelDescription");
        label.setText(user.getDescritpion());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelInscriptionDate");
        label.setText("inscrit le " + user.getInscription());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelLocation");
        label.setText(user.getLocation());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelWebSite");
        label.setText(user.getWebSite());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTitle");
        label.setText("Actualité");
        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonPictureProfil");
        try {
            button.setIcon(new ImageIcon(new URL(user.getProfile())));
            button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBackgroundPicture");

            button.setIcon(new ImageIcon(new URL(user.getBan())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFollowers");
        button.setText(Integer.toString(user.getNbFollowers()));
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.seeMyFollowers);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFriends");
        button.setText(Integer.toString(user.getNbFriends()));
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.seeMyFriends);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonTweets");
        button.setText(Integer.toString(user.getNbTweet()));
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.seeMyTweets);

        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();

        for (Status s : user.getListOfTweet()) {
            model.addElement(s.getText());
        }
        jList.setModel(model);

        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonSendTweet");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.sendTweet);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBack");
        button.setVisible(false);
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.back);

        return true;
    }

}

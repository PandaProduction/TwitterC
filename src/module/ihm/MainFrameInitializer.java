/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.AbstractIHMAction;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import model.User;
import panda.prod.application.PandaProdApplication;
import twitter4j.Status;
import view.component.PandaProdButton;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;
import view.component.PandaProdTextArea;

/**
 *
 * @author Lucas
 */
public class MainFrameInitializer extends AbstractIHMAction {

    public MainFrameInitializer(PandaProdFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();
        Dispatcher dispatcher = new Dispatcher();

        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelNickname");
        label.setText(user.getName());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTwitterName");
        label.setText(user.getTwitterName());
        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFollowers");
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

        return true;
    }

}

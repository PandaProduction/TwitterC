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
        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTwitterName");
        label.setText(user.getName());
        //label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTwitterName");
        //label.setText(user.);
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelDescription");
        label.setText(user.getDescritpion());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelFollowers");
        label.setText(Integer.toString(user.getNbFollowers()));
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelFriends");
        label.setText(Integer.toString(user.getNbFriends()));
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelInscription");
        label.setText(user.getInscription());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelLocation");
        label.setText(user.getLocation());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelNbTweet");
        label.setText(Integer.toString(user.getNbTweet()));
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelWebSite");
        label.setText(user.getWebSite());
        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();

        for (Status s : user.getListOfTweet()) {
            model.addElement(s.getText());
        }
        jList.setModel(model);

        Dispatcher dispatcher = new Dispatcher();
        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonSendTweet");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.sendTweet);
        return true;
    }

}

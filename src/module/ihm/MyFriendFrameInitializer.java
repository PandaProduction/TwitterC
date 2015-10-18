/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import interfaces.AbstractIHMAction;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import model.User;
import panda.prod.application.PandaProdApplication;
import view.component.PandaProdButton;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;

/**
 *
 * @author Lucas
 */
public class MyFriendFrameInitializer extends AbstractIHMAction {

    private static MyFriendFrameInitializer instance = null;

    private MyFriendFrameInitializer(PandaProdFrame csFrame) {
        super(csFrame);
    }

    public static MyFriendFrameInitializer getMyFriendFrameInitializer(PandaProdFrame csFrame) {
        if (instance == null) {
            instance = new MyFriendFrameInitializer(csFrame);
        }

        return instance;
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();
        user.loadMyFriends();
        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBack");
        button.setVisible(true);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonRetweet");
        button.setVisible(false);

        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTitle");
        label.setText("Mes abonnements");

        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();
        for (twitter4j.User u : user.getListOfFriends()) {
            model.addElement(u);
        }
        jList.setModel(model);
        return true;
    }

}

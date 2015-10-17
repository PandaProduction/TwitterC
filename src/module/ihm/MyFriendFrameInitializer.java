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
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;

/**
 *
 * @author Lucas
 */
public class MyFriendFrameInitializer extends AbstractIHMAction {

    public MyFriendFrameInitializer(PandaProdFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();

        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTitle");
        label.setText("Mes abonnements");
        
        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();
        for (twitter4j.User u : user.getListOfFriends()) {
            model.addElement(u.getName() + " @" + u.getScreenName());
        }
        jList.setModel(model);
        return true;
    }

}

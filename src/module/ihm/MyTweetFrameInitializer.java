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
import twitter4j.Status;
import view.component.PandaProdButton;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;

/**
 *
 * @author Lucas
 */
public class MyTweetFrameInitializer extends AbstractIHMAction {

    private static MyTweetFrameInitializer instance = null;
    
    private MyTweetFrameInitializer(PandaProdFrame csFrame){
        super(csFrame);
    }
    
    public static MyTweetFrameInitializer getMyTweetFrameInitializer(PandaProdFrame csFrame){
        if(instance == null){
            instance = new MyTweetFrameInitializer(csFrame);
        }
        
        return instance;
    }    

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();

        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBack");
        button.setVisible(true);

        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTitle");
        label.setText("Mes tweets");

        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();
        for (Status s : user.getListOfMyTweet()) {
            model.addElement(s.getText());
        }
        jList.setModel(model);
        return true;
    }

}

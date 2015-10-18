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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.CellRendererPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
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
        Dispatcher dispatcher = Dispatcher.getDispatcher();
        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFollowers");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.seeMyFollowers);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFriends");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.seeMyFriends);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonTweets");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.seeMyTweets);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonRetweet");
        button.addActionListener(Dispatcher.getDispatcher());
        button.setActionCommand(ActionName.retweet);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonSendTweet");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.sendTweet);
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBack");
        button.addActionListener(dispatcher);
        button.setActionCommand(ActionName.back);

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
        user.loadNumber();
        user.loadTimeLine();

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
            button.setDisabledIcon(new ImageIcon(new URL(user.getProfile())));
            button.setEnabled(false);
            button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBackgroundPicture");
            button.setDisabledIcon(new ImageIcon(new URL(user.getBan())));
            button.setEnabled(false);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFollowers");
        button.setText(Integer.toString(user.getNbFollowers()));
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonFriends");
        button.setText(Integer.toString(user.getNbFriends()));
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonTweets");
        button.setText(Integer.toString(user.getNbTweet()));
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonRetweet");
        button.setVisible(false);

        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();
        jList.setCellRenderer(new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
                if (value instanceof Status) {
                    Status s = (Status) value;
                    renderer.setText(s.getText());
                }
                if (value instanceof twitter4j.User) {
                    twitter4j.User u = (twitter4j.User) value;
                    renderer.setText(u.getName() + " @" + u.getScreenName());
                }
                return renderer;
            }
        });
        jList.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                JList jList = (JList) application.getMainFrameJComponent("jListTweet");
                if (jList.getSelectedValue() instanceof Status) {
                    Status s = (Status) jList.getSelectedValue();
                    System.out.println(s);
                    PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonRetweet");
                    button.setVisible(true);

                } else if (jList.getSelectedValue() instanceof twitter4j.User) {
                    twitter4j.User s = (twitter4j.User) jList.getSelectedValue();
                    System.out.println(s);
                    PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonRetweet");
                    button.setText("Unfollow");
                    button.addActionListener(null);
                    button.setVisible(true);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        for (Status s : user.getListOfTweet()) {
            model.addElement(s);
        }

        jList.setModel(model);

        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBack");
        button.setVisible(false);

        return true;
    }

}

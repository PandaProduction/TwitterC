/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.ActionName;
import controller.Dispatcher;
import interfaces.AbstractIHMAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
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
    private PandaProdApplication application;
    private User user;

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
        application = PandaProdApplication.getApplication();
        user = application.getUser();
        user.loadNumber();
        user.loadTimeLine();
        initLabel();
        initButton();
        initJTextArea();
        initJList();
        
        return true;
    }
    
    public void initLabel() {
        PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelNickname");
        label.setText(user.getName());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTwitterName");
        label.setText("@" + user.getTwitterName());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelDescription");
        label.setText(user.getDescritpion());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelInscriptionDate");
        label.setText("inscrit le " + user.getInscription());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelLocation");
        label.setText(" " + user.getLocation());
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelWebSite");
        label.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label.setForeground(Color.CYAN);
        label.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI(user.getWebSite()));
                } catch (URISyntaxException ex) {
                    Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
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

        label.setText("<html> <a href=\"" + user.getWebSite() + "\">" + user.getWebSite() + "</a>");
        label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelTitle");
        label.setText("Actualité");

    }

    public void initButton() {
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
        button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBack");
        button.setVisible(false);
    }

    public void initJTextArea(){
        JTextArea textArea = (JTextArea) application.getMainFrameJComponent("jTextAreaNewTweet");
        textArea.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                PandaProdLabel label = (PandaProdLabel) application.getMainFrameJComponent("pandaProdLabelNbCaractere");
                JTextArea textArea = (JTextArea) application.getMainFrameJComponent("jTextAreaNewTweet");
                label.setText(Integer.toString(textArea.getText().length() + 1));
                if (textArea.getText().length() < 140) {
                    label.setText(Integer.toString(textArea.getText().length() + 1));
                    label.setForeground(Color.WHITE);
                } else {
                    label.setText(Integer.toString(140 - textArea.getText().length() + 1));
                    label.setForeground(Color.RED);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
    }
    
    public void initJList(){
        JList jList = (JList) application.getMainFrameJComponent("jListTweet");
        DefaultListModel model = new DefaultListModel();
        jList.setCellRenderer(new ListCellRenderer() {

            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel renderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, value, index,
                        isSelected, cellHasFocus);
                if (value instanceof Status) {
                    Status s = (Status) value;
                    renderer.setForeground(new Color(0, 51, 102));
                    try {
                        renderer.setIcon(new ImageIcon(new URL(s.getUser().getProfileImageURL().toString())));
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    renderer.setText("<html><b>" + s.getUser().getName() + " @" + s.getUser().getScreenName() + "</b><br>"
                            + s.getText() + "<br>"
                            + "Ecrit le " + dateformat.format(s.getCreatedAt()) + "<br><br> </html>");
                }
                if (value instanceof twitter4j.User) {
                    twitter4j.User u = (twitter4j.User) value;
                    try {
                        renderer.setIcon(new ImageIcon(new URL(u.getProfileImageURL().toString())));
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
                    }

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
                    //                  System.out.println(s);
                    PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonRetweet");
                    button.setVisible(true);

                } else if (jList.getSelectedValue() instanceof twitter4j.User) {
                    twitter4j.User s = (twitter4j.User) jList.getSelectedValue();
//                    System.out.println(s);
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

    }
}

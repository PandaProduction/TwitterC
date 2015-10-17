/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package module.ihm;

import controller.Dispatcher;
import interfaces.AbstractIHMAction;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import model.User;
import panda.prod.application.PandaProdApplication;
import view.component.PandaProdButton;
import view.component.PandaProdFrame;
import view.component.PandaProdLabel;

/**
 *
 * @author Lucas
 */
public class ProfilFrameInitializer extends AbstractIHMAction {

    public ProfilFrameInitializer(PandaProdFrame csFrame) {
        super(csFrame);
    }

    @Override
    public boolean execute(Object... object) {
        PandaProdApplication application = PandaProdApplication.getApplication();
        User user = application.getUser();
        Dispatcher dispatcher = new Dispatcher();

        PandaProdLabel label = (PandaProdLabel) application.getFocusFrameJComponent("pandaProdLabelNickname");
        label.setText(user.getName());
        label = (PandaProdLabel) application.getFocusFrameJComponent("pandaProdLabelTwitterName");
        label.setText("@"+user.getTwitterName());
        label = (PandaProdLabel) application.getFocusFrameJComponent("pandaProdLabelDescription");
        label.setText(user.getDescritpion());
        label = (PandaProdLabel) application.getFocusFrameJComponent("pandaProdLabelInscriptionDate");
        label.setText("inscrit le " + user.getInscription());
        label = (PandaProdLabel) application.getFocusFrameJComponent("pandaProdLabelLocation");
        label.setText(user.getLocation());
        label = (PandaProdLabel) application.getFocusFrameJComponent("pandaProdLabelWebSite");
        label.setText(user.getWebSite());
        PandaProdButton button = (PandaProdButton) hsJcomponent.get("pandaProdButtonPictureProfil");
        try {
            button.setIcon(new ImageIcon( new URL(user.getProfile())));
            button = (PandaProdButton) hsJcomponent.get("pandaProdButtonBackgroundPicture");
            
            button.setIcon(new ImageIcon(new URL(user.getBan())));
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainFrameInitializer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}

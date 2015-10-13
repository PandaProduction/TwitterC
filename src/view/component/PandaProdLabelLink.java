package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;

public class PandaProdLabelLink extends JLabel implements MouseListener {

    private static final long serialVersionUID = 4296349391661181968L;

    public PandaProdLabelLink() {
        super();
        initComponent();
    }

    public PandaProdLabelLink(String text) {
        super(text);
        initComponent();
    }

    public void initComponent() {
        setForeground(new Color(255, 255, 255));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("click on cursor");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

}

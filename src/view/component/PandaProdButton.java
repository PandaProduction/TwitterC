package view.component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class PandaProdButton extends JButton{

	private static final long serialVersionUID = 1671314658637614873L;
	protected int inset = 5;
//	private Color buttonColor = new Color(12, 125, 175);
	protected Color buttonColor = PandaProdColor.BUTTON;
	protected int cell = 0;
	protected int height, width;
	protected boolean sizeUpdated = false;
 
	public PandaProdButton(String text){
		super(text);
		initComponent();
	}
 
	public PandaProdButton(){
	    super();
	    initComponent();
	}
	
	public PandaProdButton(Color background){
		super();
		buttonColor = background;
		initComponent();
	}
 
	protected void initComponent(){	
		setForeground(PandaProdColor.LETTER);
		
		setFocusPainted(false);
		
		Border thickBorder = new LineBorder(buttonColor, 2);
		setBorder(thickBorder);
	
		setContentAreaFilled(false);
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		setLayout(null);
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
            g.setColor(buttonColor.darker().darker());
        	
        } else if (getModel().isRollover()) {
            g.setColor(buttonColor.darker());
        } else {
            g.setColor(buttonColor);
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        
        if(!sizeUpdated){
        	height = (int) getSize().getHeight() + 20;
        	width = (int) getSize().getWidth() + 20;
        	sizeUpdated = true;
        }
        setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        
        super.paintComponent(g);
    }

}

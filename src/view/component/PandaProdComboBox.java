package view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;

public class PandaProdComboBox extends JComboBox<Object>{
	
	public PandaProdComboBox(String title, Object[] strings){
		
		
		
//		
//		get
		
		super(strings);
		
		setRenderer(new PandaProdComboBoxRenderer("Kae"));
		setSelectedIndex(-1);
		setEditor(new PandaProdComboBoxEditor("Kae"));
		setEditable(true);
//		setBackground(PandaProdColor.LETTER);
//		setForeground(PandaProdColor.BACKGROUND_FRAME);
//		
//		//setFocusPainted(false);
//		
		Border thickBorder = new LineBorder(PandaProdColor.LETTER, 2);
		setBorder(thickBorder);

		//set
//	
//		//setContentAreaFilled(false);
//		
//		setCursor(new Cursor(Cursor.HAND_CURSOR));
//		
//		setLayout(null);
		
		
		
		setPreferredSize(new Dimension(getWidth()+20, getHeight()+20));
		setSize(new Dimension(getWidth()+20, getHeight()+20));
	}
}

package view.component;

import java.awt.Color;

import javax.swing.JLabel;

public class PandaProdLabel extends JLabel {

	private static final long serialVersionUID = 4296349391661181968L;

	public PandaProdLabel() {
		super();
		initComponent();
	}

	public PandaProdLabel(String text) {
		super(text);
		initComponent();
	}
	
	public void initComponent(){
		setForeground(new Color(255, 255, 255));
	}

}

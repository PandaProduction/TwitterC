package view.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class PandaProdComboBoxRenderer extends JLabel implements ListCellRenderer{

	private String title;

    public PandaProdComboBoxRenderer(String title)
    {
    	this.title = title;
    	setBackground(PandaProdColor.LETTER);
    	setForeground(PandaProdColor.BUTTON);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
            int index, boolean isSelected, boolean hasFocus)
    {
        if (index == -1 && value == null) setText(title);
        else setText(value.toString());
        return this;
    }
}

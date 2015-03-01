package paint;


import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ColorTile extends JPanel {

	public ColorTile(){
		//this.setBackground(Color.gray);
		//this.setPreferredSize(new Dimension(20,20));
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
	}
	
	
}

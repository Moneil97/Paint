package paint;


import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorTile extends JPanel {

	public ColorTile(){
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
	}
	
	
}

package paint;


import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorTile extends JPanel {
	
	Color color;
	
	public ColorTile(Color c){
		color = c;
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	public Color getColor(){
		return color;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
	}
	
	
}

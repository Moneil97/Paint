package paint;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class ColorTile extends JPanel {
	
	Color color;
	boolean active;
	
	public ColorTile(Color c, ColorManager colorManager){
		color = c;
		this.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);
				
				if (active)
						colorManager.setColor(color);
			}
		});
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean b) {
		active = b;
		this.setBorder(BorderFactory.createLineBorder(b? Color.black:Color.gray));
	}
	
	
}

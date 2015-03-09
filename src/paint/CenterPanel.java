package paint;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CenterPanel extends JPanel {

	private Paint paint;
	private ColorManager colorManager;
	private LayerManager layerManager;
	private Rectangle Bounds;
	private Point p = new Point(0,0);

	public CenterPanel(Paint parent) {
		
		EventQueue.invokeLater(new Runnable() {
            
			@Override
            public void run() {
				
            	paint = parent;
            	colorManager = (ColorManager) ComponetFinder.getComponet("ColorManager", parent);
            	layerManager = (LayerManager) ComponetFinder.getComponet("LayerManager", parent);
            	say(layerManager.getSelectedLayer());
            	
            	Bounds = new Rectangle(0,0, CenterPanel.this.getWidth(), CenterPanel.this.getHeight());
            	say(Bounds);
            }
    	});
		
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {
				layerManager.getSelectedLayer().c = colorManager.getColor();
				layerManager.getSelectedLayer().p = e.getPoint();
				//p = e.getPoint();
				repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {}
			
			@Override
			public void mouseEntered(MouseEvent e) {}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
			
		});
	}
	
//	public Dimension getSize(){
//		return this.getSize();
//	}
	
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		
		//Background
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		
		for (Layer l : layerManager.getLayerList()){
			l.drawPoint(g);
		}
		
//		g.setColor(colorManager.getColor());
//		g.fillRect(p.x, p.y, 5, 5);
		
	}

	public void say(Object s){
		System.out.println(s);
	}
	

}

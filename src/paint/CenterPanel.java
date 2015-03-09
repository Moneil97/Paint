package paint;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CenterPanel extends JPanel {

	//private Paint paint;
	private ColorManager colorManager;
	private LayerManager layerManager;
//	private Rectangle Bounds;

	public CenterPanel(Paint parent) {
		
		EventQueue.invokeLater(new Runnable() {
            
			@Override
            public void run() {
				
            	//paint = parent;
            	colorManager = (ColorManager) ComponetFinder.getComponet("ColorManager", parent);
            	layerManager = (LayerManager) ComponetFinder.getComponet("LayerManager", parent);
            	say(layerManager.getSelectedLayer());
            	
//            	Bounds = new Rectangle(0,0, CenterPanel.this.getWidth(), CenterPanel.this.getHeight());
//            	say(Bounds);
            }
    	});
		
		
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				Layer selectedLayer = layerManager.getSelectedLayer();
				selectedLayer.addToSnapshotHistory(selectedLayer.getSnapshot());
				selectedLayer.drawFreeDraw((Graphics2D) selectedLayer.getSnapshot().getGraphics());
				selectedLayer.getFreeDrawPoints().clear();
				selectedLayer.updateThumbnail();
				colorManager.addRecentColor(colorManager.getColor());
				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Layer selectedLayer = layerManager.getSelectedLayer();
				selectedLayer.setSelectedColor(colorManager.getColor());
				selectedLayer.getFreeDrawPoints().add(e.getPoint());
				repaint();
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				layerManager.getSelectedLayer().getFreeDrawPoints().add(e.getPoint());
				repaint();
			}
		});
	}
	
	@Override
	protected void paintComponent(Graphics g1) {
		super.paintComponent(g1);
		Graphics2D g = (Graphics2D) g1;
		
		//Background
		g.setColor(Color.white);
		g.fillRect(0,0, this.getWidth(), this.getHeight());
		
		for (Layer l : layerManager.getLayerListReversed()){
			l.draw(g);
		}

	}

	public void say(Object s){
		System.out.println(s);
	}
	

}

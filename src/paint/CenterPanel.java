package paint;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.util.List;

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
				BufferedImage image = selectedLayer.getImageClone();
				
				if (parent.getPaintMode() == Modes.freeDraw)
					freeDraw(selectedLayer, image);
				else if (parent.getPaintMode() == Modes.rectangle)
					;
				else if (parent.getPaintMode() == Modes.oval)
					;
				
				
				selectedLayer.updateThumbnail();
				repaint();
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				Layer selectedLayer = layerManager.getSelectedLayer();
				selectedLayer.setSelectedColor(colorManager.getColor());
				colorManager.addRecentColor(colorManager.getColor());
				
				if (parent.getPaintMode() == Modes.freeDraw)
					selectedLayer.getFreeDrawPoints().add(e.getPoint());
				else if (parent.getPaintMode() == Modes.rectangle)
					;
				else if (parent.getPaintMode() == Modes.oval)
					;
				
				repaint();
			}
			
		});
		
		addMouseMotionListener(new MouseMotionAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				
				if (parent.getPaintMode() == Modes.freeDraw)
					layerManager.getSelectedLayer().getFreeDrawPoints().add(e.getPoint());
				else if (parent.getPaintMode() == Modes.rectangle)
					;
				else if (parent.getPaintMode() == Modes.oval)
					;
				
				repaint();
			}
		});
	}
	
	protected void freeDraw(Layer selectedLayer, BufferedImage image) {
		image = stretchImageToFitPoints(image, selectedLayer.getFreeDrawPoints());
		selectedLayer.drawFreeDraw(image.createGraphics());
		selectedLayer.addToSnapshotHistory(image);
		selectedLayer.getFreeDrawPoints().clear();
	}

	private BufferedImage stretchImageToFitPoints(BufferedImage image, List<Point> freeDrawPoints) {
		
		//Returns new instance of BufferedImage
		
		Point max = getLargestXandY(freeDrawPoints);
		BufferedImage newImage;
		
		if (max.x > image.getWidth() || max.y > image.getHeight()){
			say("resizing image");
			newImage = new BufferedImage(Math.max(max.x +1, image.getWidth()), Math.max(max.y+1, image.getHeight()), BufferedImage.TYPE_INT_ARGB);
			newImage.createGraphics().drawImage(image, 0, 0, null);
			image = newImage;
		}
		else{
			newImage = image;
		}
		
		return newImage;
		
	}

	private Point getLargestXandY(List<Point> freeDrawPoints) {
		
		int maxX = 0, maxY = 0;
		
		for (Point p : freeDrawPoints){
			if (p.x > maxX) maxX = p.x;
			if (p.y > maxY) maxY = p.y;
		}
		
		return new Point(maxX, maxY);
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

package paint;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {

	private LayerManager layersManager;

	public BottomPanel() {
		
		this.setLayout(new GridLayout(1,2));
		
		layersManager = new LayerManager();
		this.add(new ColorManager());
		this.add(layersManager);
		
		
		
		
	}
	
	public LayerManager getLayerManager(){
		return layersManager;
	}

	public void addLayerToManager(String string) {
		layersManager.addLayer(new Layer(layersManager, string));
	}



}

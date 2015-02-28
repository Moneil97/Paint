package paint;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {

	private LayerManager layersManager;

	public BottomPanel() {
		// TODO Auto-generated constructor stub
		
		layersManager = new LayerManager();
		//layersManager.addLayer(new Layer("Layer 1"));
		this.add(layersManager);
		
		
		
	}
	
	public LayerManager getLayerManager(){
		return layersManager;
	}



}

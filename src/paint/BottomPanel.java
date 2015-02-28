package paint;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BottomPanel extends JPanel {

	private LayerManager layersManager;

	public BottomPanel() {
		
		layersManager = new LayerManager();
		this.add(layersManager);
		
		
		
	}
	
	public LayerManager getLayerManager(){
		return layersManager;
	}

	public void addLayerToManager(String string) {
		layersManager.addLayer(new Layer(layersManager, string));
	}



}

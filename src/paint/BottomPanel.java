package paint;

import java.awt.Dimension;

import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public class BottomPanel extends JSplitPane {

	private LayerManager layersManager;

	public BottomPanel() {
		layersManager = new LayerManager();
		this.setLeftComponent(new ColorManager());
		this.setRightComponent(layersManager);
		this.setDividerLocation(900/2 - 5);
		this.setResizeWeight(.5);
		this.setPreferredSize(new Dimension(0,200));
	}
	
	public LayerManager getLayerManager(){
		return layersManager;
	}

	public void addLayerToManager(String string) {
		layersManager.addLayer(new Layer(layersManager, string));
	}



}

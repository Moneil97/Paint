package paint;

import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

public class Layer {

	private String name;
	private BufferedImage snapshot;
	private LayerLabel layerLabel;
	private LayerManager adult;

//	public Layer() {
//		
//	}

	public Layer(LayerManager parent, String name) {
		this.adult = parent;
		this.name = name;
		layerLabel = new LayerLabel(this, name);
	}

//	public JPanel getLayerLabelAsPanel() {
//		return layerLabel;
//	}
	
	public LayerLabel getLayerLabel() {
		return layerLabel;
	}

	public List<Layer> getLayerList() {
		return adult.getLayerList();
	}

}


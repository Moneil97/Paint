package paint;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LayerManager extends JPanel{

	List<Layer> layers;
	private JPanel layerPanel;
	
	public LayerManager(){
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		
		layerPanel = new JPanel();
		layerPanel.setLayout(new GridLayout(0, 1));
		scrollPane.setViewportView(layerPanel);
		
		JLabel lblTest = new JLabel("Test");
		layerPanel.add(lblTest);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNew = new JButton("New");
		panel_1.add(btnNew);
		
		JButton btnRename = new JButton("Rename");
		panel_1.add(btnRename);
		
		JButton btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		
		JButton btnMoveUp = new JButton("Up");
		panel_1.add(btnMoveUp);
		
		JButton btnMoveDown = new JButton("Down");
		panel_1.add(btnMoveDown);
		layers = new ArrayList<Layer>();
		
		
		layers.add(new Layer(this, "Default"));
		layers.add(new Layer(this, "Layer 2"));
		updateLayerPositions();
	}

	public List<Layer> getLayerList(){
		return layers;
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);
		updateLayerPositions();
	}

	private void updateLayerPositions() {
		
		layerPanel.removeAll();
		
		for (Layer l : layers){
			layerPanel.add(l.getLayerLabelAsPanel());
		}
	}
	
}

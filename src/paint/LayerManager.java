package paint;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LayerManager extends JPanel{

	private List<Layer> layers;
	private JPanel layerPanel;
	private JButton btnNew;
	private JButton btnRename;
	private JButton btnDelete;
	private JButton btnMoveUp;
	private JButton btnMoveDown;
	private int layerCount;
	
	public LayerManager(){
		this.setName("LayerManager");
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
//		JPanel header = new JPanel();
//		scrollPane.setColumnHeaderView(header);
		
		layerPanel = new JPanel();
		layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.Y_AXIS));
		scrollPane.setViewportView(layerPanel);
		
		JLabel lblTest = new JLabel("Test");
		layerPanel.add(lblTest);
		
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.SOUTH);
		
		btnNew = new JButton("New");
		panel_1.add(btnNew);
		
		btnRename = new JButton("Rename");
		panel_1.add(btnRename);
		
		btnDelete = new JButton("Delete");
		panel_1.add(btnDelete);
		
		btnMoveUp = new JButton("Up");
		panel_1.add(btnMoveUp);
		
		btnMoveDown = new JButton("Down");
		panel_1.add(btnMoveDown);
		actionListenersSetup();
		layers = new ArrayList<Layer>();
		addLayer("Default Layer");
		selectedLayer = layers.get(0);
		selectedLayer.setSelected(true);
	}
	
	private void actionListenersSetup(){
	
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//Place new layer above currently selected layer
				addLayer("Layer " + layerCount, getCurrentLayerNum());
			}
		});
		
		btnRename.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = JOptionPane.showInputDialog("Enter a new name for the layer: ");
				if (name != null)
					selectedLayer.changeName(name);
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int currentSpot = getCurrentLayerNum();
				deleteLayer(selectedLayer);
				selectedLayer = layers.get((currentSpot > 0 ? currentSpot-1 : 0));
				selectedLayer.setSelected(true);
			}
		});
		
		btnMoveUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveUp(selectedLayer);
			}
		});
		
		btnMoveDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown(selectedLayer);
			}
		});
		
	}
	
	public void addLayer(String s){
		addLayer(s, layers.size());
	}
	
	private void addLayer(String s, int spot) {
		layers.add(spot, new Layer(this, s){

			@Override
			void layerChanged(Layer currentLayer) {
				System.out.println(currentLayer + "is now the layer");
				LayerManager.this.selectedLayer = currentLayer;
			}
			
		});
		updateLayerPositions();
		layerCount++;
		revalidate();
	}
	
	protected void moveDown(Layer currentLayer) {
		int newSpot = layers.indexOf(currentLayer) + 1;
		layers.remove(currentLayer);
		layers.add(newSpot, currentLayer);
		updateLayerPositions();
	}

	protected void moveUp(Layer currentLayer) {
		int newSpot = layers.indexOf(currentLayer) - 1;
		layers.remove(currentLayer);
		layers.add(newSpot, currentLayer);
		updateLayerPositions();
	}

	private Layer selectedLayer;
	
	public Layer getSelectedLayer(){
		return selectedLayer;
	}
	
	public int getCurrentLayerNum(){
		return layers.indexOf(getSelectedLayer());
	}

	public List<Layer> getLayerList(){
		return layers;
	}
	
	public void deleteLayer(Layer layer){
		if (layers.size() > 1){
			layers.remove(layer);
			updateLayerPositions();
		}
		else{
			JOptionPane.showMessageDialog(null, "You must have at least one layer at all times.");
		}
	}

	private void updateLayerPositions() {
		layerPanel.removeAll();
		for (Layer l : layers)
			layerPanel.add(l);
		
		revalidate();
		repaint();
	}
	
}

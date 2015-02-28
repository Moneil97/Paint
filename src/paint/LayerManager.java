package paint;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class LayerManager extends JPanel{

	List<Layer> layers;
	private JPanel layerPanel;
	private JButton btnNew;
	private JButton btnRename;
	private JButton btnDelete;
	private JButton btnMoveUp;
	private JButton btnMoveDown;
	private int layerCount;
	private LayerManager me;
	
	public LayerManager(){
		this.setName("LayerManager");
		this.me = this;
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		add(scrollPane);
		
//		JPanel header = new JPanel();
//		scrollPane.setColumnHeaderView(header);
		
		layerPanel = new JPanel();
		layerPanel.setLayout(new GridLayout(0, 1));
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
		
	}
	
	private void actionListenersSetup(){
	
		btnNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addLayer(new Layer(me, "Layer " + layerCount));
			}
		});
		
		btnRename.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnMoveUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		btnMoveDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
	}
	
	public Layer getCurrentLayer(){
		
		for (Layer l : layers)
			if (l.isSelected())
				return l;
		
		return null;
	}

	public List<Layer> getLayerList(){
		return layers;
	}
	
	public void addLayer(Layer layer) {
		layers.add(layer);
		updateLayerPositions();
		layerCount++;
	}
	
	public void addLayer(Layer layer, int spot) {
		layers.add(spot, layer);
		updateLayerPositions();
		layerCount++;
	}

	private void updateLayerPositions() {
		layerPanel.removeAll();
		for (Layer l : layers)
			layerPanel.add(l);
	}
	
}

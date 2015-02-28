package paint;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public class LayerLabel extends JPanel implements MouseListener{
	
	private Image thumbnail;
	private int thumbSize = 50;
	private String name;
	//private int ratio = 1;
	private LayerLabel me;
	private Layer adult;
	private boolean selected = false;
	
	public LayerLabel(Layer parent, String name){
		this.me = this;
		this.adult = parent;
		this.name = name;
		setLayout(new BorderLayout(0, 0));
		
		try {
			thumbnail = ImageIO.read(new URL("http://upload.wikimedia.org/wikipedia/commons/0/07/Emperor_Penguin_Manchot_empereur.jpg").openStream());
			thumbnail = thumbnail.getScaledInstance(thumbSize, thumbSize, Image.SCALE_SMOOTH);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		JPanel thumbnailPanel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawImage(thumbnail, 0, 0, null);
			}
			
		};
		thumbnailPanel.setPreferredSize(new Dimension(thumbSize, thumbSize));
		add(thumbnailPanel, BorderLayout.WEST);
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new EmptyBorder(0, 6, 0, 0));
		namePanel.setLayout(new GridLayout(0, 1, 0, 0));
		JLabel label = new JLabel(name);
		namePanel.add(label);
		add(namePanel, BorderLayout.CENTER);
		
		JPanel isVisiblePanelOuter = new JPanel();
		add(isVisiblePanelOuter, BorderLayout.EAST);
		isVisiblePanelOuter.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel isVisiblePanelInner = new JPanel();
		isVisiblePanelOuter.add(isVisiblePanelInner);
		isVisiblePanelInner.setLayout(new BoxLayout(isVisiblePanelInner, BoxLayout.X_AXIS));
		
		JCheckBox isVisibleCheckBox = new JCheckBox("Visible");
		isVisibleCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		isVisiblePanelInner.add(isVisibleCheckBox);
		isVisibleCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		
		addMouseListener(this);
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {
		System.out.println("You've selected: " + name + " Label");
		
		//System.out.println(ComponetFinder.getComponentNames(ComponetFinder.getAllComponents(me)));
		
		for (Layer l : adult.getLayerList())
			l.getLayerLabel().setSelected(false);
		setSelected(true);
	}

	public void setSelected(boolean selected){
		this.selected  = selected;
		if (selected)
			me.setBorder(BorderFactory.createLineBorder(Color.blue));
		else
			me.setBorder(BorderFactory.createLineBorder(Color.gray));
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	public boolean isSelected() {
		return selected;
	}
	
//	public static void main(String args[]){
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(400, 100);
//		frame.setLocationRelativeTo(null);
//		frame.getContentPane().add(new LayerLabel("Layer 1"));
//		frame.pack();
//		frame.setVisible(true);
//	}
	
}

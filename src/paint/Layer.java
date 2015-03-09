package paint;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JCheckBox;
import javax.swing.SwingConstants;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public abstract class Layer extends JPanel implements MouseListener{
	
	private Image thumbnail/*, snapshot*/;
	private int thumbSize = 46;
	private String name;
	//private int ratio = 1;
	//private Layer me;
	private boolean selected = false;
	private LayerManager adult;
	private Border selectedBoder, defaultBorder;
	private Image blankThumbnail;
	private JLabel nameLabel;
	private JCheckBox isVisibleCheckBox;
	
	public Layer(LayerManager parent, String name){
		//this.me = this;
		this.adult = parent;
		this.name = name;
		setLayout(new BorderLayout(0, 0));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, thumbSize + 6));
		
		
		defaultBorder= BorderFactory.createLineBorder(Color.gray);
		selectedBoder = BorderFactory.createLineBorder(Color.blue);
		this.setBorder(defaultBorder);
		blankThumbnail = new BufferedImage(thumbSize, thumbSize, BufferedImage.TYPE_INT_RGB);
		blankThumbnail.getGraphics().setColor(Color.WHITE);
		blankThumbnail.getGraphics().fillRect(0, 0, thumbSize, thumbSize);
		thumbnail = blankThumbnail;
		
		JPanel thumbnailPanel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawImage(thumbnail, 2, 2, null);
				g.setColor(Color.gray);
				g.drawRect(2, 2, thumbSize, thumbSize);
			}
			
		};
		
		thumbnailPanel.setPreferredSize(new Dimension(thumbSize+4, thumbSize+4));
		add(thumbnailPanel, BorderLayout.WEST);
		
		JPanel namePanel = new JPanel();
		namePanel.setBorder(new EmptyBorder(0, 6, 0, 0));
		namePanel.setLayout(new GridLayout(0, 1, 0, 0));
		nameLabel = new JLabel(this.name);
		namePanel.add(nameLabel);
		add(namePanel, BorderLayout.CENTER);
		
		JPanel isVisiblePanelOuter = new JPanel();
		add(isVisiblePanelOuter, BorderLayout.EAST);
		isVisiblePanelOuter.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel isVisiblePanelInner = new JPanel();
		isVisiblePanelOuter.add(isVisiblePanelInner);
		isVisiblePanelInner.setLayout(new BoxLayout(isVisiblePanelInner, BoxLayout.X_AXIS));
		
		isVisibleCheckBox = new JCheckBox("Visible");
		isVisibleCheckBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.getRoot(Layer.this).repaint();
			}
		});
		isVisibleCheckBox.setSelected(true);
		isVisibleCheckBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		isVisiblePanelInner.add(isVisibleCheckBox);
		isVisibleCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
		
		addMouseListener(this);
		
	}
	
	abstract void layerChanged(Layer currentLayer);
	
	public void changeName(String name) {
		this.name = name;
		nameLabel.setText(this.name);
	}
	
	public void setSelected(boolean selected){
		this.selected  = selected;
		
		if (selected)
			this.setBorder(selectedBoder);
		else
			this.setBorder(defaultBorder);
	}
	
	public boolean isSelected() {
		return selected;
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
		
		for (Layer l : adult.getLayerList())
			l.setSelected(false);
		setSelected(true);
		
		layerChanged(this);
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

	Point p = new Point(-10,-10);
	Color c = new Color(0,0,0,0);
	
	public void drawPoint(Graphics2D g) {
		if (isVisibleCheckBox.isSelected()){
			g.setColor(c);
			g.fillRect(p.x, p.y, 5, 5);
		}
	}
	
	public void say(Object s){
		System.out.println(s);
	}

	

}

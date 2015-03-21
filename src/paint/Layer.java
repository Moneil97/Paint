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
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

@SuppressWarnings("serial")
public abstract class Layer extends JPanel implements MouseListener{
	
	private Image thumbnail;
	private int thumbSize = 46;
	private String name;
	//private int ratio = 1;
	private boolean selected = false;
	private LayerManager adult;
	private Border selectedBoder, defaultBorder;
	private JLabel nameLabel;
	private JCheckBox isVisibleCheckBox;
	private List<BufferedImage> snapshotHistory = new ArrayList<BufferedImage>();
	
	public Layer(LayerManager parent, String name){
		this.adult = parent;
		this.name = name;
		setLayout(new BorderLayout(0, 0));
		setMaximumSize(new Dimension(Integer.MAX_VALUE, thumbSize + 6));
		
		
		defaultBorder= BorderFactory.createLineBorder(Color.gray);
		selectedBoder = BorderFactory.createLineBorder(Color.blue);
		this.setBorder(defaultBorder);
		
		JPanel thumbnailPanel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.drawImage(thumbnail, 2, 2, null);
				//g.drawImage(snapshot.getScaledInstance(thumbSize, thumbSize, Image.SCALE_DEFAULT), 2, 2, null);
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
		
		addToSnapshotHistory(new BufferedImage(900, 450, BufferedImage.TYPE_INT_ARGB));
		Graphics g = getSnapshot().getGraphics();
		g.setColor(new Color(0f,0f,0f,0f));
		g.fillRect(0, 0, getSnapshot().getWidth(null), getSnapshot().getHeight(null));
		
		updateThumbnail();
		
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

	private Color selectedColor = new Color(0,0,0,0);
	
	List<Point> freeDrawPoints = new ArrayList<Point>();
	
	public List<Point> getFreeDrawPoints(){
		return freeDrawPoints;
	}
	
	public void say(Object s){
		System.out.println(s);
	}

	public void drawFreeDraw(Graphics2D g) {

		g.setColor(selectedColor);
		
		if (freeDrawPoints.size() > 1)
			for (int i =0; i < freeDrawPoints.size()-1; i++){
				Point p1 = freeDrawPoints.get(i);
				Point p2 = freeDrawPoints.get(i+1);
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
		else if (freeDrawPoints.size() == 1)
			g.fillRect(freeDrawPoints.get(0).x, freeDrawPoints.get(0).y, 5, 5);
	}

	public void draw(Graphics2D g) {
		if (isVisibleCheckBox.isSelected()){
			g.drawImage(getSnapshot(), 0, 0, null);
			drawFreeDraw(g);
		}
	}

	public void setSelectedColor(Color color) {
		selectedColor = color;
	}
	
	public BufferedImage getSnapshot(){
		return snapshotHistory.get(imageCounter);
	}
	
	public void addToSnapshotHistory(BufferedImage image){
		
		say("Snapshot size: " + snapshotHistory.size() + "  Counter: " + imageCounter);
		
		if (imageCounter < snapshotHistory.size()-1){
			
			for (int i=snapshotHistory.size()-1; i > imageCounter; i--){
				snapshotHistory.remove(i);
				say("removed image: " + i + " of " + snapshotHistory.size());
			}
		}
		
		say("Snapshot size: " + snapshotHistory.size() + "  Counter: " + imageCounter);
		snapshotHistory.add(image);
		imageCounter = snapshotHistory.size()-1;
	}
	
	private int imageCounter = 0;
	
	public void undo(){
		if (imageCounter > 0)
			imageCounter --;
		else 
			say("can't undo");
	}
	
	public void redo(){
		
		if (imageCounter < snapshotHistory.size()-1)
			imageCounter ++;
		else
			say("can't redo");
	}

	public void updateThumbnail(){
		thumbnail = getSnapshot().getScaledInstance(thumbSize, thumbSize, Image.SCALE_SMOOTH);
		repaint();
	}
	
	public BufferedImage getImageClone(){
		BufferedImage original = getSnapshot();
		
		BufferedImage copy = new BufferedImage(original.getHeight(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = copy.getGraphics();
		g.drawImage(original, 0, 0, null);
		
		return copy;
	}

}

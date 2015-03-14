package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorManager extends JPanel{

	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;
	final private Color defaultColor = new Color(60,120,180);
	private Color color = defaultColor;
	List <ColorTile> tiles;

	public ColorManager() {
		
		this.setName("ColorManager");
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		JPanel selectedColorPanel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g1) {
				super.paintComponent(g1);
				Graphics2D g = (Graphics2D) g1;
				
				g.setColor(getColor());
				int padding = 5;
				g.fillRect(padding, padding, getWidth()-padding*2, getHeight()-padding*2);
				g.setColor(Color.black);
				g.drawRect(padding, padding, getWidth()-padding*2, getHeight()-padding*2);
				g.setFont(new Font("Arial", Font.PLAIN, 16));
				g.setColor(getInverted(getColor()));
				String s = redSlider.getValue() + ", " +  greenSlider.getValue() + ", " + blueSlider.getValue();
				g.drawString(s, getWidth() - g.getFontMetrics().stringWidth(s) - 10, 40);
				
			}
			
		};
		
		selectedColorPanel.setMinimumSize(new Dimension(50,50));
		selectedColorPanel.setPreferredSize(new Dimension(50,50));
		
		JPanel slidersPanel = new JPanel();
		slidersPanel.setLayout(new GridLayout(0,1));
		
		redSlider = createColorSlider(Color.red, defaultColor.getRed());
		greenSlider = createColorSlider(Color.green, defaultColor.getGreen());
		blueSlider = createColorSlider(Color.blue, defaultColor.getBlue());
		
		slidersPanel.add(redSlider);
		slidersPanel.add(greenSlider);
		slidersPanel.add(blueSlider);
		
		
		JPanel recentAndMorePanel = new JPanel();
		recentAndMorePanel.setLayout(new BorderLayout());
		
		JPanel moreAndLabel = new JPanel();
		moreAndLabel.setLayout(new GridLayout(2,1));
		
		JButton moreButton = new JButton("More");
		
		moreButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				setColor(JColorChooser.showDialog(null, "Choose Color", getColor()));
			}
		});
		
		moreAndLabel.add(moreButton);
		moreAndLabel.add(new JLabel("   Recent:"));
		
		JPanel recent = new JPanel();
		final int recentRows = 2, recentColumns = 14;
		recent.setLayout(new GridLayout(recentRows, recentColumns));
		
		tiles = new ArrayList<ColorTile>();
		
		for (int i=0; i< recentRows * recentColumns; i++){
			tiles.add(new ColorTile(Color.white, this));
			recent.add(tiles.get(i));
		}
		
		
		recentAndMorePanel.add(moreAndLabel, "West");
		recentAndMorePanel.add(recent, "Center");
		
		centerPanel.add(selectedColorPanel, "North");
		centerPanel.add(slidersPanel);
		centerPanel.add(recentAndMorePanel, "South");
		
		setLayout(new GridLayout(1,1));
		add(centerPanel);
	}
	
	private JSlider createColorSlider(final Color color, int value){
		JSlider colorSlider = new JSlider(){
			@Override
			protected void paintComponent(Graphics g) {
				
				g.setColor(color);
				g.fillRect(0, 0, (int) (this.getWidth() * ((double) this.getValue()/(this.getMaximum()))), this.getHeight());
				super.paintComponent(g);
				
			}
		};
		
		colorSlider.setOpaque(false);
		colorSlider.setMinimum(0);
		colorSlider.setMaximum(255);
		colorSlider.setValue(value);
		colorSlider.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		colorSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent paramChangeEvent) {
				if (!lockSliders){
					ColorManager.this.setColor(getSliderColors());
					repaint();
				}
			}
			
			private Color getSliderColors(){
				return new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
			}
		});
		
		colorSlider.addMouseWheelListener(new MouseWheelListener() {
			
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				colorSlider.setValue(colorSlider.getValue() + 5 * e.getWheelRotation());
			}
			
		});
	
		return colorSlider;
	}
	
	boolean lockSliders = false;
	
	protected void setColor(Color c) {
		if (c != null){
			lockSliders = true;
			color = c;
			redSlider.setValue(color.getRed());
			greenSlider.setValue(color.getGreen());
			blueSlider.setValue(color.getBlue());
			lockSliders = false;
			revalidate();
			repaint();
		}
	}

	private Color getInverted(Color c){
		return new Color(255-c.getRed(), 255-c.getGreen(), 255-c.getBlue());
	}

	public Color getColor() {
		return color;
	}
	
	public void addRecentColor(Color color) {
		
		int usedColorPos = colorUsed(color);
		
		if (usedColorPos >= 0){
			
			for (int i = usedColorPos-1; i >= 0; i--)
				tiles.get(i+1).setColor(tiles.get(i).getColor());
			
			tiles.get(0).setColor(color);
		}
		else{
			int count = getActiveCount();
			
			for (int i = tiles.size()-2; i >= 0; i--)
				tiles.get(i+1).setColor(tiles.get(i).getColor());
			
			if (count < tiles.size()) tiles.get(count).setActive(true);
			tiles.get(0).setColor(color);
		}
		
		repaint();
	}
	
	private int colorUsed(Color c) {
		for (int i =0; i < tiles.size(); i++)
			if (tiles.get(i).getColor().equals(c))
				return i;
		return -1;
	}

	private int getActiveCount(){
		int count = 0;
		for (ColorTile t: tiles)
			count += (t.isActive() ? 1:0);
		return count;
	}

}

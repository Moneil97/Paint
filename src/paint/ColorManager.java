package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
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

	public ColorManager() {
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		JPanel selectedColorPanel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g1) {
				super.paintComponent(g1);
				Graphics2D g = (Graphics2D) g1;
				
//				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//						RenderingHints.VALUE_ANTIALIAS_ON);
//
//				g.setRenderingHint(RenderingHints.KEY_RENDERING,
//						RenderingHints.VALUE_RENDER_QUALITY);
				
				g.setColor(color);
				int padding = 5;
				g.fillRect(padding, padding, getWidth()-padding*2, getHeight()-padding*2);
				g.setColor(Color.black);
				g.drawRect(padding, padding, getWidth()-padding*2, getHeight()-padding*2);
				g.setFont(new Font("Arial", Font.PLAIN, 16));
				g.setColor(getInverted(color));
				String s = redSlider.getValue() + ", " +  greenSlider.getValue() + ", " + blueSlider.getValue();
				g.drawString(s, getWidth() - g.getFontMetrics().stringWidth(s) - 10, 40);
				
				//drawOutline(g,s);
			}

			@SuppressWarnings("unused")
			private void drawOutline(Graphics2D g, String s) {
				g.setColor(Color.black);
				g.translate(getWidth() - g.getFontMetrics().stringWidth(s) - 10, 40);
				g.draw(getTextOutline(g, s));
			}

			private Shape getTextOutline(Graphics2D g, String string) {
				return g.getFont().createGlyphVector(g.getFontRenderContext(), string).getOutline();
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
				updateColor(JColorChooser.showDialog(null, "Choose Color", color));
			}
		});
		
		moreAndLabel.add(moreButton);
		moreAndLabel.add(new JLabel("   Recent:"));
		
		JPanel recent = new JPanel();
		final int recentRows = 2, recentColumns = 14;
		recent.setLayout(new GridLayout(recentRows, recentColumns));
		
		List <ColorTile> tiles = new ArrayList<ColorTile>();
		
		for (int i=0; i< recentRows * recentColumns; i++){
			tiles.add(new ColorTile());
			recent.add(tiles.get(i));
		}
		
		
		recentAndMorePanel.add(moreAndLabel, "West");
		//recentAndMorePanel.add(recentScroller, "Center");
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
					ColorManager.this.color = getSliderColors();
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
	
	protected void updateColor(Color c) {
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

}

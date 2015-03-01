package paint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class ColorManager extends JPanel{

	private JSlider redSlider;
	private JSlider greenSlider;
	private JSlider blueSlider;

	public ColorManager() {
		
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout());
		
		
		
		JPanel selectedColorPanel = new JPanel(){
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				
				g.setColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
				int padding = 5;
				g.fillRect(padding, padding, getWidth()-padding*2, getHeight()-padding*2);
				g.setColor(Color.black);
				g.drawRect(padding, padding, getWidth()-padding*2, getHeight()-padding*2);
			}
			
		};
		
		selectedColorPanel.setMinimumSize(new Dimension(50,50));
		selectedColorPanel.setPreferredSize(new Dimension(50,50));
		
		JPanel slidersPanel = new JPanel();
		slidersPanel.setLayout(new GridLayout(0,1));
		
		redSlider = createColorSlider(Color.red);
		greenSlider = createColorSlider(Color.green);
		blueSlider = createColorSlider(Color.blue);
		
		slidersPanel.add(redSlider);
		slidersPanel.add(greenSlider);
		slidersPanel.add(blueSlider);
		
		
		
		centerPanel.add(selectedColorPanel, "North");
		centerPanel.add(slidersPanel);
		
		setLayout(new GridLayout(1,1));
		add(centerPanel);
	}
	
	private JSlider createColorSlider(Color color){
		JSlider colorSlider = new JSlider(){
			@Override
			protected void paintComponent(Graphics g) {
				
				g.setColor(color);
				g.fillRect(0, 0, (int) (this.getWidth() * ((double) this.getValue()/this.getMaximum())), this.getHeight());
				super.paintComponent(g);
				
			}
		};
		
		colorSlider.setOpaque(false);
		colorSlider.setMinimum(0);
		colorSlider.setMaximum(255);
		colorSlider.setValue(0);
		colorSlider.setBorder(BorderFactory.createLineBorder(Color.gray));
		
		colorSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent paramChangeEvent) {
				repaint();
			}
		});
	
		return colorSlider;
	}

}

package paint;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ToolBarPanel extends JPanel {
	
	private Paint parent;
	private LayerManager layerManager;

	public ToolBarPanel(Paint paint){
		
		this.parent = paint;
		this.layerManager = parent.getBottomPanel().getLayerManager();
		
		JButton btnFreeDraw = new JButton("Free Draw");
		btnFreeDraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setPaintMode(Modes.freeDraw);
			}
		});
		add(btnFreeDraw);
		
		JButton btnRectangle = new JButton("Rectangle");
		btnRectangle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setPaintMode(Modes.rectangle);
			}
		});
		add(btnRectangle);
		
		JButton btnOval = new JButton("Oval");
		btnOval.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.setPaintMode(Modes.oval);
			}
		});
		add(btnOval);
		
		JButton btnUndo = new JButton("Undo");
		btnUndo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layerManager.getSelectedLayer().undo();
				parent.repaint();
			}
		});
		add(btnUndo);
		
		JButton btnRedo = new JButton("Redo");
		btnRedo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layerManager.getSelectedLayer().redo();
				parent.repaint();
			}
		});
		add(btnRedo);
		
	}
	
}

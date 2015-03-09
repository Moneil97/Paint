package paint;

import javax.swing.JPanel;
import javax.swing.JButton;

public class ToolBarPanel extends JPanel {

	public ToolBarPanel(){
		
		JButton btnFreeDraw = new JButton("Free Draw");
		add(btnFreeDraw);
		
		JButton btnRectangle = new JButton("Rectangle");
		add(btnRectangle);
		
		JButton btnOval = new JButton("Oval");
		add(btnOval);
		
	}
	
}

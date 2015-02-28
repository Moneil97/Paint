package paint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Paint extends JFrame{

	public Paint() {
		
		setupFrame();
		addListeners();
		
	}

	

	private void setupFrame() {
		JPanel centerPanel = new CenterPanel();
		JPanel topPanel = new TopPanel();
		JPanel bottomPanel = new BottomPanel();
		JPanel leftPanel = new LeftPanel();
		JPanel rightPanel = new RightPanel();
		
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(topPanel, BorderLayout.NORTH);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		getContentPane().add(rightPanel, BorderLayout.EAST);
		
		this.setSize(400, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void addListeners() {
		
	}

	public static void main(String[] args) {
		new Paint();
	}

}

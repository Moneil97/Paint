package paint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

@SuppressWarnings("serial")
public class Paint extends JFrame{

	private BottomPanel bottomPanel;
	private TopMenuBar menuBar;
	private CenterPanel centerPanel;
	private Modes paintMode = Modes.freeDraw;

	public Paint() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Paint.this.setName("Paint");
            	setupFrame();
            }
    	});
	}

	private void setupFrame() {
		
		menuBar = new TopMenuBar();
		bottomPanel = new BottomPanel();
		ToolBarPanel toolbarPanel = new ToolBarPanel(this);
		centerPanel = new CenterPanel(this);
		JPanel leftPanel = new LeftPanel();
		JPanel rightPanel = new RightPanel();
		
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(toolbarPanel, BorderLayout.NORTH);
		setJMenuBar(menuBar);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		getContentPane().add(leftPanel, BorderLayout.WEST);
		getContentPane().add(rightPanel, BorderLayout.EAST);
		
		this.setTitle("Paint.Java (Cameron O'Neil)");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}
	
	public BottomPanel getBottomPanel(){
		return bottomPanel;
	}

	public Modes getPaintMode() {
		return paintMode;
	}

	public void setPaintMode(Modes paintMode) {
		this.paintMode = paintMode;
	}

	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e){};
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	new Paint();
            }
    	});
	}

}

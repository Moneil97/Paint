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


	public Paint() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	Paint.this.setName("Paint");
            	setupFrame();
        		addListeners();
        		
            }
    	});
	}



	private void setupFrame() {
		
		menuBar = new TopMenuBar();
		bottomPanel = new BottomPanel();
		ToolBarPanel toolbarPanel = new ToolBarPanel();
		JPanel centerPanel = new CenterPanel(this);
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
	
	private void addListeners() {
		
	}
	
	
//	private void printSize() {
//		new Thread(new Runnable() {
//			
//			@Override
//			public void run() {
//				while (true){
//					System.out.println(centerPanel.getSize());
//				}
//			}
//		}).start();
//	}

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

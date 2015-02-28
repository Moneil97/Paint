package paint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

@SuppressWarnings("serial")
public class Paint extends JFrame{

	public Paint() {
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
      
            	setupFrame();
        		addListeners();
//        		printSize();
        		

            }
    	});
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
		
		this.setTitle("Paint.Java (Cameron O'Neil)");
		this.setSize(400, 400);
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

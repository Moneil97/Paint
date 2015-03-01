package paint;

import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

@SuppressWarnings("serial")
public class TopMenuBar extends JMenuBar {

	public TopMenuBar(){
		
		JMenu file = new JMenu("File");
		
			JMenuItem save = new JMenuItem("Save");
			file.add(save);
			
			JMenu saveAs = new JMenu("Save as");
			
				JMenuItem png = new JMenuItem("PNG");
				JMenuItem jpg = new JMenuItem("JPG");
				saveAs.add(png);
				saveAs.add(jpg);
			file.add(saveAs);
		
		this.add(file);
		
		
//		JMenu file = new JMenu("File");
//		
//		JMenuItem news = new JMenuItem("New");
//		file.add(news);
//		
//		JMenuItem open = new JMenuItem("Open");
//		//open.add(new JMenuItem("Open File"));
//		//open.add(new JMenuItem("Open Folder"));
//		
//		open.addActionListener(new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				JPopupMenu openSub = new JPopupMenu("Open");
//				openSub.add(new JMenuItem("Open File"));
//				openSub.add(new JMenuItem("Open Folder"));
//				
//				openSub.show(TopMenuBar.this, getX(), getY());
//				
//			}
//		});
//		
//		file.add(open);
//		add(file);
	}
	
//	public static void main(String args[]){
//		JFrame f = new JFrame();
//		f.setSize(400, 400);
//		f.setLocationRelativeTo(null);
//		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		f.setVisible(true);
//		
//		f.setJMenuBar(new TopMenuBar());
//	}
}

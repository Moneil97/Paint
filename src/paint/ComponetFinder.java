package paint;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public class ComponetFinder {
	
	public static List<Component> getAllComponents(final Container c) {
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
	}
	
	public static Component findCompWithName(List<Component> allComps, String name){
		
		for (Component comp : allComps){
			if (comp.getName() != null && comp.getName().equals(name))
				return comp;
		}
		return null;
	}
	
	public static List<String> getComponentNames(List<Component> comps){
		
		List<String> names = new ArrayList<String>();
		
		for (Component c : comps)
			names.add(c.getName());
		
		return names;
		
	}

}

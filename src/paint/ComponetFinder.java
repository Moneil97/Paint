package paint;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

public class ComponetFinder {
	
	public static List<Component> getAllChildComponents(final Container c) {
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllChildComponents((Container) comp));
	    }
	    return compList;
	}
	
	public static Component getChildComponet(final Container c, String componetName){
		return getChildComponet(getAllChildComponents(c), componetName);
	}
	
	public static Component getChildComponet(List<Component> componetList, String componetName){
		
		for (Component comp : componetList){
			if (comp.getName() != null && comp.getName().equals(componetName))
				return comp;
		}
		return null;
	}
	
	public static List<String> getAllChildComponentNames(Container c){
		return getAllChildComponentNames(getAllChildComponents(c));
	}
	
	public static List<String> getAllChildComponentNames(List<Component> comps){
		
		List<String> names = new ArrayList<String>();
		
		for (Component c : comps)
			names.add(c.getName());
		
		return names;
		
	}
	
	public static Component getComponet(String name, Container c){
		return getChildComponet(getAllChildComponents(c), name);
	}

}

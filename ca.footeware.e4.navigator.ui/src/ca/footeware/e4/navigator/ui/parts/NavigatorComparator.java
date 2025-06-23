/**
 * 
 */
package ca.footeware.e4.navigator.ui.parts;

import java.io.File;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * Comparator for the Navigator view that sorts files and directories.
 * 
 * @author Footeware.ca
 */
public class NavigatorComparator extends ViewerComparator {

	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof File f1 && e2 instanceof File f2) {
			if ((f1.isDirectory() && f2.isDirectory()) || (!f1.isDirectory() && !f2.isDirectory())) {
				// if they're the same type, sort by name
				return f1.getName().compareTo(f2.getName());
			} else if (f1.isDirectory() && !f2.isDirectory()) {
				return -1;
			} else if (!f1.isDirectory() && f2.isDirectory()) {
				return 1;
			}
		}
		return super.compare(viewer, e1, e2);
	}
}
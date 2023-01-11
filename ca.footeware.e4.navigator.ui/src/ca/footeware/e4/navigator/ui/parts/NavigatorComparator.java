/**
 * 
 */
package ca.footeware.e4.navigator.ui.parts;

import java.io.File;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * @author Footeware.ca
 *
 */
public class NavigatorComparator extends ViewerComparator {
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		if (e1 instanceof File && e2 instanceof File) {
			File f1 = (File) e1;
			File f2 = (File) e2;
			if ((f1.isDirectory() && f2.isDirectory()) || (!f1.isDirectory() && !f2.isDirectory())) {
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
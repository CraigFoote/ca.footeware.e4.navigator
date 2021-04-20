/**
 * 
 */
package ca.footeware.e4.navigator.ui.providers;

import java.io.File;

import org.eclipse.jface.viewers.ITreeContentProvider;

/**
 * @author <a href="http://Footeware.ca">Footeware.ca</a>
 *
 */
public class NavigatorContentProvider implements ITreeContentProvider {

	@Override
	public Object[] getElements(Object inputElement) {
		return (File[]) inputElement;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		return ((File) parentElement).listFiles();
	}

	@Override
	public Object getParent(Object element) {
		return ((File) element).getParent();
	}

	@Override
	public boolean hasChildren(Object element) {
		return ((File) element).isDirectory() && ((File) element).listFiles() != null
				&& ((File) element).listFiles().length > 0;
	}

}

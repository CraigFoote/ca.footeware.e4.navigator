/**
 * 
 */
package ca.footeware.e4.navigator.ui.providers;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * @author <a href="http://Footeware.ca">Footeware.ca</a>
 *
 */
public class NavigatorLabelProvider extends LabelProvider {

	private ResourceManager resourceManager;

	@Override
	public String getText(Object element) {
		if (element instanceof File) {
			String name = ((File) element).getName();
			name = name.isEmpty() ? "/" : name;
			return name;
		}
		return super.getText(element);
	}

	public Image getImage(Object element) {
		if (element instanceof File) {
			File file = (File) element;
			if (file.isDirectory()) {
				return getResourceManager().createImage(createImageDescriptor("icons/folder.png"));
			}
			return getResourceManager().createImage(createImageDescriptor("icons/file_obj.png"));
		}
		return null;
	};

	protected ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}

	private ImageDescriptor createImageDescriptor(String path) {
		Bundle bundle = FrameworkUtil.getBundle(NavigatorLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path(path), null);
		return ImageDescriptor.createFromURL(url);
	}
}

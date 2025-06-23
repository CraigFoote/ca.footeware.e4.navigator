/**
 * 
 */
package ca.footeware.e4.navigator.ui.providers;

import java.io.File;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * @author Footeware.ca
 */
public class NavigatorLabelProvider extends LabelProvider {

	private ResourceManager resourceManager;

	@Override
	public String getText(Object element) {
		if (element instanceof File file) {
			String name = file.getName();
			name = name.isEmpty() ? "/" : name;
			return name;
		}
		return super.getText(element);
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof File file) {
			ImageDeviceResourceDescriptor descriptor = null;
			if (file.isDirectory()) {
				descriptor = new ImageDeviceResourceDescriptor("/icons/folder.png");
			} else {
				descriptor = new ImageDeviceResourceDescriptor("/icons/file_obj.png");
			}
			return getResourceManager().create(descriptor);
		}
		return super.getImage(element);
	}

	private ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}
}

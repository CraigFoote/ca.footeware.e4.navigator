/**
 * 
 */
package ca.footeware.e4.navigator.ui.parts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;

/**
 * @author Footeware.ca
 *
 */
public class NavigatorOpenListener implements IOpenListener {
	@Override
	public void open(OpenEvent event) {
		IStructuredSelection selection = (IStructuredSelection) event.getSelection();
		File file = (File) selection.getFirstElement();
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.OPEN)) {
				try {
					desktop.open(file);
				} catch (IOException _) {
					// DO NOTHING
				}
			}
		}
	}
}
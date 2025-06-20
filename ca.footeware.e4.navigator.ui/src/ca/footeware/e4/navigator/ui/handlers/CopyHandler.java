
package ca.footeware.e4.navigator.ui.handlers;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import jakarta.inject.Inject;

public class CopyHandler {

	@Execute
	@Inject
	public void execute(ESelectionService selectionService) {
		if (selectionService.getSelection() instanceof TreeSelection treeSelection) {
			List<String> filePaths = new ArrayList<>();
			Iterator<?> iterator = treeSelection.iterator();
			while (iterator.hasNext()) {
				File file = (File) iterator.next();
				filePaths.add(file.getAbsolutePath());
			}
			String[] data = filePaths.toArray(new String[filePaths.size()]);

			Clipboard cb = new Clipboard(Display.getDefault());
			cb.setContents(new String[][] { data }, new Transfer[] { FileTransfer.getInstance() });
			cb.dispose();
		}
	}

}
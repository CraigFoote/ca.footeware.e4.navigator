
package ca.footeware.e4.navigator.ui.handlers;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

public class CopyHandler {

	@Execute
	public void execute(ESelectionService selectionService) {
		Clipboard cb = new Clipboard(Display.getDefault());
		Object selection = selectionService.getSelection();
		TreeSelection treeSelection = (TreeSelection) selection;
		List<String> fileNameList = new LinkedList<>();
		Iterator<?> iterator = treeSelection.iterator();
		while (iterator.hasNext()) {
			File file = (File) iterator.next();
			fileNameList.add(file.getAbsolutePath());
		}
		String[] data = fileNameList.toArray(new String[fileNameList.size()]);

		cb.setContents(new String[][] { data }, new Transfer[] { FileTransfer.getInstance() });
		cb.dispose();
	}

}
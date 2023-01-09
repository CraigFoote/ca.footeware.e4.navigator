
package ca.footeware.e4.navigator.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

public class CopyHandler {
	@Execute
	public void execute(ESelectionService selectionService) {
		Clipboard cb = new Clipboard(Display.getDefault());
		Object selection = selectionService.getSelection();
		StructuredSelection sSelection = (StructuredSelection) selection;
		cb.setContents(new Object[] { sSelection.getFirstElement() }, new Transfer[] { FileTransfer.getInstance() });
	}

}
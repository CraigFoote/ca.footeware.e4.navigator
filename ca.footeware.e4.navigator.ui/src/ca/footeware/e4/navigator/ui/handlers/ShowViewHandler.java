
package ca.footeware.e4.navigator.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.advanced.MPlaceholder;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class ShowViewHandler {
	
	private String partId = "ca.footeware.e4.navigator.ui.partdescriptor.navigator";

	/**
	 * 
	 * @param app
	 * @param partService
	 */
	@Execute
	public void execute(MApplication app, EPartService partService) {
		MWindow mWindow = app.getChildren().get(0);
		MPerspectiveStack stack = (MPerspectiveStack) mWindow.getChildren().get(0);
		if (stack.getSelectedElement().getLabel().equals("Navigator")) {
			partService.showPart(partId, PartState.ACTIVATE);
		} else {
			MPlaceholder placeholder = partService.createSharedPart(partId);
			MPart part = (MPart) placeholder.getRef();
			partService.showPart(part, PartState.ACTIVATE);
		}
	}

}
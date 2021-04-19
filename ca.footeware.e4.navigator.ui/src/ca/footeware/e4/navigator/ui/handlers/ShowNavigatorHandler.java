
package ca.footeware.e4.navigator.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.e4.ui.workbench.modeling.EPartService.PartState;

public class ShowNavigatorHandler {

	/**
	 * 
	 * @param partService
	 */
	@Execute
	public void execute(EPartService partService) {
		partService.showPart("ca.footeware.e4.navigator.part", PartState.ACTIVATE);
	}

}
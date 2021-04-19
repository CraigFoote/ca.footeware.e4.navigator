 
package ca.footeware.e4.navigator.ui.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ShowPerspectiveHandler {
	
	/**
	 * 
	 * @param partService
	 */
	@Execute
	public void execute(EPartService partService) {
		partService.switchPerspective("ca.footeware.e4.navigator.perspective");
	}
		
}

package ca.footeware.e4.navigator.ui.parts;

import javax.annotation.PostConstruct;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class NavigatorPart {

	private TreeViewer viewer;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(GridLayoutFactory.swtDefaults().create());
		viewer = new TreeViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.getTree().setLayoutData(GridDataFactory.swtDefaults().grab(true, true).align(SWT.FILL, SWT.FILL));
		
		viewer.setContentProvider(ArrayContentProvider.getInstance());
		viewer.setLabelProvider(new LabelProvider());
		
		viewer.setInput(ResourcesPlugin.getWorkspace().getRoot());
	}

	@Focus
	public void onFocus() {
		if (viewer != null && !viewer.getTree().isDisposed()) {
			viewer.getTree().setFocus();
		}
	}

}

package ca.footeware.e4.navigator.ui.parts;

import java.io.File;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ca.footeware.e4.navigator.ui.providers.NavigatorContentProvider;
import ca.footeware.e4.navigator.ui.providers.NavigatorLabelProvider;

public class NavigatorPart {

	private TreeViewer viewer;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(GridLayoutFactory.swtDefaults().create());
		viewer = new TreeViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.getTree()
				.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).create());

		viewer.setContentProvider(new NavigatorContentProvider());
		viewer.setLabelProvider(new NavigatorLabelProvider());

		viewer.setInput(File.listRoots());
	}

	@Focus
	public void onFocus() {
		if (viewer != null && !viewer.getTree().isDisposed()) {
			viewer.getTree().setFocus();
		}
	}

}
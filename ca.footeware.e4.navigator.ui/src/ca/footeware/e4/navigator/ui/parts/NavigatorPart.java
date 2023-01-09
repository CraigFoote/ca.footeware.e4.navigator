
package ca.footeware.e4.navigator.ui.parts;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.IOpenListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.OpenEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import ca.footeware.e4.navigator.ui.providers.NavigatorContentProvider;
import ca.footeware.e4.navigator.ui.providers.NavigatorLabelProvider;

public class NavigatorPart {

	private TreeViewer viewer;

	@PostConstruct
	public void postConstruct(Composite parent, EMenuService menuService, ESelectionService selectionService) {
		parent.setLayout(GridLayoutFactory.swtDefaults().create());
		viewer = new TreeViewer(parent, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		viewer.getTree()
				.setLayoutData(GridDataFactory.swtDefaults().grab(true, true).align(SWT.FILL, SWT.FILL).create());

		viewer.setContentProvider(new NavigatorContentProvider());
		viewer.setLabelProvider(new NavigatorLabelProvider());

		viewer.addPostSelectionChangedListener(event -> selectionService.setPostSelection(event.getSelection()));

		viewer.setComparator(new ViewerComparator() {
			@Override
			public int compare(Viewer viewer, Object e1, Object e2) {
				if (e1 instanceof File && e2 instanceof File) {
					File f1 = (File) e1;
					File f2 = (File) e2;
					if ((f1.isDirectory() && f2.isDirectory()) || (!f1.isDirectory() && !f2.isDirectory())) {
						return f1.getName().compareTo(f2.getName());
					} else if (f1.isDirectory() && !f2.isDirectory()) {
						return -1;
					} else if (!f1.isDirectory() && f2.isDirectory()) {
						return 1;
					}
				}
				return super.compare(viewer, e1, e2);
			}
		});
		
		viewer.addOpenListener(new IOpenListener() {
			@Override
			public void open(OpenEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event
						.getSelection();
				File file = (File) selection.getFirstElement();
				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					if (desktop.isSupported(Desktop.Action.OPEN)) {
						try {
							desktop.open(file);
						} catch (IOException e) {
							// DO NOTHING
						}
					}
				}
			}
		});

		menuService.registerContextMenu(viewer.getTree(), "ca.footeware.e4.navigator.ui.popupmenu.0");

		viewer.setInput(File.listRoots());
	}

	@Focus
	public void onFocus() {
		if (viewer != null && !viewer.getTree().isDisposed()) {
			viewer.getTree().setFocus();
		}
	}

}
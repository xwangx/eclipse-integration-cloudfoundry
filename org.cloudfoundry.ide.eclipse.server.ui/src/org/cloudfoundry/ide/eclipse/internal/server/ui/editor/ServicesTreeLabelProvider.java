/*******************************************************************************
 * Copyright (c) 2012 VMware, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     VMware, Inc. - initial API and implementation
 *******************************************************************************/
package org.cloudfoundry.ide.eclipse.internal.server.ui.editor;

import org.cloudfoundry.client.lib.CloudService;
import org.cloudfoundry.ide.eclipse.internal.server.ui.CloudFoundryImages;
import org.cloudfoundry.ide.eclipse.internal.server.ui.editor.ServiceViewerConfigurator.ServiceViewColumn;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;


/**
 * @author Terry Denney
 * @author Christian Dupuis
 */
public class ServicesTreeLabelProvider extends LabelProvider implements ITableLabelProvider {

	private final TableViewer viewer;

	public ServicesTreeLabelProvider(TableViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public Image getImage(Object element) {
		if (element instanceof CloudService) {
			return CloudFoundryImages.getImage(CloudFoundryImages.OBJ_SERVICE);
		}
		return null;
	}

	@Override
	public String getText(Object element) {
		if (element instanceof CloudService) {
			CloudService service = (CloudService) element;
			return service.getName();
		}
		return super.getText(element);
	}

	public Image getColumnImage(Object element, int columnIndex) {

		TableColumn column = viewer.getTable().getColumn(columnIndex);
		if (column != null && column.getData() == ServiceViewColumn.Name) {
			return getImage(element);
		}

		return null;
	}

	public String getColumnText(Object element, int columnIndex) {
		String result = null;
		TableColumn column = viewer.getTable().getColumn(columnIndex);
		if (column != null) {
			ServiceViewColumn serviceColumn = (ServiceViewColumn) column.getData();
			if (serviceColumn != null) {
				switch (serviceColumn) {
				case Name:
					result = getText(element);
					break;
				case Type:
					result = ((CloudService) element).getType();
					break;
				case Vendor:
					result = ((CloudService) element).getVendor();
					break;
				}
			}
		}
		return result;
	}

}

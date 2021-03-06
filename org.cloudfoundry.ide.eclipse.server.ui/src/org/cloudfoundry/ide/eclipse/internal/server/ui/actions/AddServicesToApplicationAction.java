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
package org.cloudfoundry.ide.eclipse.internal.server.ui.actions;

import java.util.ArrayList;
import java.util.List;

import org.cloudfoundry.ide.eclipse.internal.server.core.ApplicationModule;
import org.cloudfoundry.ide.eclipse.internal.server.core.CloudFoundryServerBehaviour;
import org.cloudfoundry.ide.eclipse.internal.server.ui.editor.CloudFoundryApplicationsEditorPage;
import org.eclipse.jface.viewers.IStructuredSelection;



/**
 * @author Terry Denney
 * @author Steffen Pingel
 * @author Christian Dupuis
 */
public class AddServicesToApplicationAction extends ModifyServicesForApplicationAction {

	private final List<String> services;

	public AddServicesToApplicationAction(IStructuredSelection selection, ApplicationModule appModule,
			CloudFoundryServerBehaviour serverBehaviour, CloudFoundryApplicationsEditorPage editorPage) {
		super(appModule, serverBehaviour, editorPage);

		services = getServiceNames(selection);
	}

	@Override
	public String getJobName() {
		return "Adding services";
	}

	@Override
	public List<String> getServicesToAdd() {
		return services;
	}

	@Override
	public List<String> getServicesToRemove() {
		return new ArrayList<String>();
	}
}

/*******************************************************************************
 * Copyright (c) 2007, 2010 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.common.io.eclipse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.internal.qvt.oml.common.CommonPlugin;
import org.eclipse.m2m.internal.qvt.oml.emf.util.mmregistry.IMetamodelProvider;
import org.eclipse.m2m.internal.qvt.oml.emf.util.mmregistry.IMetamodelRegistryProvider;
import org.eclipse.m2m.internal.qvt.oml.emf.util.mmregistry.MetamodelRegistry;
import org.eclipse.m2m.internal.qvt.oml.emf.util.mmregistry.WorkspaceMetamodelProvider;
import org.eclipse.m2m.internal.qvt.oml.emf.util.urimap.MappingContainer;
import org.eclipse.m2m.internal.qvt.oml.emf.util.urimap.MetamodelURIMappingHelper;
import org.eclipse.m2m.internal.qvt.oml.emf.util.urimap.URIMapping;
import org.eclipse.osgi.util.NLS;

public class WorkspaceMetamodelRegistryProvider implements IMetamodelRegistryProvider {

	private ResourceSet resolutionRSet;
	private	Map<String, MetamodelRegistry> perProjectRegs;
	
	public WorkspaceMetamodelRegistryProvider() {
		this(new ResourceSetImpl());
	}
	
	public WorkspaceMetamodelRegistryProvider(ResourceSet resourceSet) {
		if(resourceSet == null) {
			throw new IllegalArgumentException();
		}
		
		this.resolutionRSet = resourceSet;
	}
		
	public ResourceSet getResolutionResourceSet() {
		return resolutionRSet;
	}
	
	public MetamodelRegistry getRegistry(IRepositoryContext context) {
		if(context == null) {
			throw new IllegalArgumentException("Null context"); //$NON-NLS-1$
		}
		
		URI uri = context.getURI();
		if(!uri.isPlatformResource()) {
			return getDelegateRegistry();
		}
		
		IPath wsLocation = new Path(uri.toPlatformString(true));		
		IResource wsResource = ResourcesPlugin.getWorkspace().getRoot().findMember(wsLocation);		
		if(wsResource == null) {
			// not a file, could be a folder
			wsResource = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(wsLocation);		
		}
		
		if(wsResource != null) {
			IProject project = wsResource.getProject();
			if(MetamodelURIMappingHelper.hasMappingResource(project)) {
				if(perProjectRegs == null) {
					perProjectRegs = new HashMap<String, MetamodelRegistry>();
				}
				
				String projectKey = project.getFullPath().toString();
				MetamodelRegistry reg = perProjectRegs.get(projectKey);
				if(reg == null) {
					try {
						reg = createRegistry(MetamodelURIMappingHelper.loadMappings(project));
						perProjectRegs.put(projectKey, reg);
						return reg;
					} catch (IOException e) {
						CommonPlugin.log(e);
					}
				} else {
					return reg;
				}
			}			
		}
		
		return getDelegateRegistry();
	}
	
	private MetamodelRegistry getDelegateRegistry() {
		return new MetamodelRegistry(createDelegateMetamodelProvider()); 
	}
	
	protected IMetamodelProvider createDelegateMetamodelProvider() {
		return MetamodelRegistry.getDefaultMetamodelProvider();
	}
	
	private MetamodelRegistry createRegistry(MappingContainer mappings) {
		WorkspaceMetamodelProvider metamodelProvider = new WorkspaceMetamodelProvider(createDelegateMetamodelProvider(), resolutionRSet);				
		
		for (URIMapping nextMapping : mappings.getMapping()) {
			URI uri = null;
			IllegalArgumentException error = null;
			try {
				uri = URI.createURI(nextMapping.getTargetURI());
			} catch (IllegalArgumentException e) {
				error = e;
			}
			
			if(uri != null && nextMapping.getSourceURI() != null) {
				metamodelProvider.addMetamodel(nextMapping.getSourceURI(), uri);				
			} else {
				String message = NLS.bind("Invalid metamodel uri mapping. nsUri:''{0}'' modelUri:''{1}''",  //$NON-NLS-1$
						nextMapping.getSourceURI(), nextMapping.getTargetURI());
				
				CommonPlugin.log(new Status(IStatus.ERROR, CommonPlugin.ID, message, error));
			}
		}
		
		return new MetamodelRegistry(metamodelProvider);
	}
}

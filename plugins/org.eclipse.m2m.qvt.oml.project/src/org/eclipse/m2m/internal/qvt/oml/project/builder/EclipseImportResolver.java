/*******************************************************************************
 * Copyright (c) 2007, 2009 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on 07.12.2004
 */
package org.eclipse.m2m.internal.qvt.oml.project.builder;

import java.util.Arrays;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.m2m.internal.qvt.oml.common.MDAConstants;
import org.eclipse.m2m.internal.qvt.oml.common.io.CFile;
import org.eclipse.m2m.internal.qvt.oml.common.io.CFolder;
import org.eclipse.m2m.internal.qvt.oml.common.io.eclipse.EclipseFile;
import org.eclipse.m2m.internal.qvt.oml.common.io.eclipse.EclipseFileUtil;
import org.eclipse.m2m.internal.qvt.oml.compiler.IImportResolver;
import org.eclipse.m2m.internal.qvt.oml.compiler.ResolverUtils;
import org.eclipse.m2m.internal.qvt.oml.emf.util.Logger;
import org.eclipse.m2m.internal.qvt.oml.emf.util.WorkspaceUtils;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.DeployedImportResolver;

/**
 * @author pfeldman
 * @deprecated
 */
public class EclipseImportResolver implements IImportResolver {
	/**
	 * The purpose of this interface is to support dynamic 
	 * set source source containers. 
	 */
	public interface SourceContainerProvider {
		/** 
		 * Note: The set of containers provided by the implementor
		 * may differ between calls.
		 * 
		 * @return non-null array of source containers
		 */
		IContainer[] getContainers();
	}
	
	private DeployedImportResolver deployedQvtFileResolver;
	
	public EclipseImportResolver(SourceContainerProvider sourceContainerProvider) {
		this.sourceProvider = sourceContainerProvider;
		this.deployedQvtFileResolver = DeployedImportResolver.INSTANCE;
	}
	
	public EclipseImportResolver(final IProject project) {
		this(new SourceContainerProvider() {
			public IContainer[] getContainers() {
				return getSourceContainers(project);
			}
		});
	}	
	
	/**
	 * Constructs import resolver from a fixed set of containers.
	 *  
	 * @param rootContainers
	 */
	public EclipseImportResolver(final IContainer[] rootContainers) {
		this(new SourceContainerProvider() {
			public IContainer[] getContainers() {
				return rootContainers;
			} 			
		});
	}	
	
	public CFile resolveImport(String importedUnitName) {
        String path = ResolverUtils.toNamespaceRelativeUnitFilePath(importedUnitName);
        
        IContainer[] roots = sourceProvider.getContainers();        
        for (int i = 0; i < roots.length; i++) {
            IContainer root = roots[i];
            
            IPath fullPath = root.getFullPath().append(new Path(path));
            final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(fullPath);
            if(file.exists()) {
                return new EclipseFile(file);
            }        
        }
        // lookup for QVT modules installed in bundles        
        return deployedQvtFileResolver.resolveImport(importedUnitName);
	}
    
    public CFile resolveImport(CFile parentFile, String importedUnitName) {
    	return resolveImport(importedUnitName);
    }
    
    public String getPackageName(CFolder folder) {
        IContainer container = WorkspaceUtils.getIContainer(folder.getFullPath());
        if(container == null || !container.exists()) {
        	String deployedPckgName = deployedQvtFileResolver.getPackageName(folder);
        	if(deployedPckgName != null) {
        		return deployedPckgName;
        	}
        }

        IContainer root = getBestMatchingRoot(container);
        if(root == null) {
            throw new IllegalArgumentException("Folder " + folder + " is not under source path " + Arrays.asList(sourceProvider)); //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        IPath relPath = EclipseFileUtil.makeRelative(new Path(folder.getFullPath()), new Path(root.getLocation().toOSString()));
        String packName = relPath.toOSString().replaceAll("[/\\\\]", "."); //$NON-NLS-1$ //$NON-NLS-2$
        return packName;
    }
    
    private IContainer getBestMatchingRoot(IContainer container) {
        IPath path = container.getFullPath();
        
        IContainer bestRoot = null;
        IContainer[] roots = sourceProvider.getContainers();                
        int bestCount = 0;
        for(int i = 0; i < roots.length; i++) {
            IContainer root = roots[i];
            IPath rootPath = root.getFullPath();
            if(path.getDevice() != null && !path.getDevice().equalsIgnoreCase(rootPath.getDevice())) {
                continue;
            }
    
            int count = rootPath.matchingFirstSegments(path);
            if(count > bestCount) {
                count = bestCount;
                bestRoot = root;
            }
        }
        
        return bestRoot;
    }
    
	private static IContainer[] getSourceContainers(IProject project) {
        try {
            QVTOBuilderConfig config = QVTOBuilderConfig.getConfig(project);
            return config.getQvtContainers();
        }
        catch(CoreException e) {
            Logger.getLogger().log(Logger.SEVERE, "Failed to get QVT containers for " + project, e); //$NON-NLS-1$
            return new IContainer[] {project};
        }
    }
    

	private SourceContainerProvider sourceProvider;
}

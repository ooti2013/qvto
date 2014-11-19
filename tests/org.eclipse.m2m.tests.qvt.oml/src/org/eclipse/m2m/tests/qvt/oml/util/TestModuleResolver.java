/*******************************************************************************
 * Copyright (c) 2008, 2014 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.tests.qvt.oml.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.internal.qvt.oml.common.MDAConstants;
import org.eclipse.m2m.internal.qvt.oml.common.io.CFile;
import org.eclipse.m2m.internal.qvt.oml.common.io.eclipse.BundleFile;
import org.eclipse.m2m.internal.qvt.oml.common.io.eclipse.BundleModuleRegistry;
import org.eclipse.m2m.internal.qvt.oml.compiler.BlackboxUnitResolver;
import org.eclipse.m2m.internal.qvt.oml.compiler.ResolverUtils;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitProxy;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitResolver;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.DeployedImportResolver;
import org.eclipse.m2m.tests.qvt.oml.AllTests;
import org.osgi.framework.Bundle;

public class TestModuleResolver implements UnitResolver {
	
	private UnitResolver fDeployedResolver;	
	private IPath fBasePath;
			
	/**
	 * Creates import resolver for this test plug-in.
	 * 
	 * @param sourceContainerPath
	 *            a path relative to the given bundle
	 * @return the resolver instance
	 */
	public static TestModuleResolver createdTestPluginResolver(String sourceContainerPath) {
		return new TestModuleResolver(AllTests.BUNDLE_ID, sourceContainerPath);
	}
	
	public TestModuleResolver(final String bundleSymbolicName, final String sourceContainerPath) {
		if(bundleSymbolicName == null || sourceContainerPath == null) {
			throw new IllegalArgumentException();
		}
		
		Bundle bundle =  Platform.getBundle(bundleSymbolicName);
		if(bundle == null) {
			throw new IllegalArgumentException("Not existinging bundle" + bundleSymbolicName); //$NON-NLS-1$
		}

		Enumeration<java.net.URL> qvtFiles = bundle.findEntries(sourceContainerPath, "*" + MDAConstants.QVTO_FILE_EXTENSION_WITH_DOT, true); //$NON-NLS-1$
		
		List<IPath> pathList = new ArrayList<IPath>();
		while(qvtFiles.hasMoreElements()) {
			pathList.add(new Path(qvtFiles.nextElement().getPath()));
		}
		
		BundleModuleRegistry registry = new BundleModuleRegistry(bundleSymbolicName, pathList);
		
		fDeployedResolver = new DeployedImportResolver(Arrays.asList(registry)) {			
			UnitResolver fBlackboxResolver = new BlackboxUnitResolver(URI.createPlatformPluginURI(bundleSymbolicName, false));

			@Override
			public UnitProxy doResolveUnit(String qualifiedName) {			
				UnitProxy unit = super.doResolveUnit(qualifiedName);
				if(unit == null) {
					unit = fBlackboxResolver.resolveUnit(qualifiedName);
				}
				return unit;
			}
			
			@Override
			public CFile resolveImport(String importedUnitName) {
				CFile result = null; 
				IPath fullPath = fBasePath.append(ResolverUtils.toNamespaceRelativeUnitFilePath(importedUnitName));
				for (BundleModuleRegistry nextRegistry : getBundleModules()) {
					if (nextRegistry.fileExists(fullPath)) {
						result = new BundleFile(fullPath, nextRegistry);
					}
				}
				
				return result;
			}
		};
		
		fBasePath = new Path(sourceContainerPath).makeAbsolute();
	}

	public UnitProxy resolveUnit(String qualifiedName) {
		return fDeployedResolver.resolveUnit(qualifiedName);		
//		CFile resolvedImport = fDeployedResolver.resolveUnit(qualifiedName);
//		if(resolvedImport == null) {
//			return fBlackboxResolver.resolveUnit(qualifiedName);			
//		}
//		
//		return DeployedImportResolver.UNIT_RESOLVER_INSTANCE.createUnit(qualifiedName, (BundleFile) resolvedImport);		
	}
}

/*******************************************************************************
 * Copyright (c) 2009 Borland Software Corporation and others.
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.compiler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.internal.qvt.oml.QvtPlugin;

public abstract class UnitResolverFactory {

	public abstract boolean accepts(URI uri);
	
	public abstract UnitResolver getResolver(URI uri);
	
	public abstract String getQualifiedName(URI uri);

	final UnitProxy findUnit(URI uri) {
		UnitResolver resolver = getResolver(uri);
		return resolver != null ? resolver.resolveUnit(getQualifiedName(uri)) : null;
	};
	
	
	public interface Registry {
		String POINT_ID = QvtPlugin.ID + ".unitResolverFactory"; //$NON-NLS-1$
		String CLASS_ATTR = "class"; //$NON-NLS-1$
		
		UnitResolverFactory getFactory(URI uri);		
		
		UnitProxy getUnit(URI uri);
		
		Registry INSTANCE = EMFPlugin.IS_ECLIPSE_RUNNING ? new EclipseRegistry() : new BasicRegistry();
	
	
		class EclipseRegistry extends BasicRegistry {
	
			public EclipseRegistry() {
				super(readFactories());		
			}
		
			private static List<UnitResolverFactory> readFactories() {
				ArrayList<UnitResolverFactory> factoryEntries = new ArrayList<UnitResolverFactory>();
				if(EMFPlugin.IS_ECLIPSE_RUNNING) {
					IExtensionRegistry pluginRegistry = Platform.getExtensionRegistry();
					IExtensionPoint extensionPoint = pluginRegistry.getExtensionPoint(POINT_ID);
					if(extensionPoint != null) {
						IExtension[] allExtensions = extensionPoint.getExtensions();
						for (IExtension nextExtension : allExtensions) {
							IConfigurationElement[] elements = nextExtension.getConfigurationElements();
							Object factoryObj = null;
							try {
								factoryObj = elements[0].createExecutableExtension(CLASS_ATTR);
								if(factoryObj instanceof UnitResolverFactory) {
									factoryEntries.add((UnitResolverFactory)factoryObj);
								}
							} catch (CoreException e) {								
								QvtPlugin.getDefault().log(e.getStatus());
							}
						}						
					}
				}
				return factoryEntries;
		    }
		}
		
		
		class BasicRegistry implements Registry {
			
				private List<UnitResolverFactory> fFactories;
				
				BasicRegistry() {
					this(Collections.<UnitResolverFactory>emptyList());
				}
				
				BasicRegistry(List<UnitResolverFactory> factories) {
					assert factories != null;
					
					factories = new ArrayList<UnitResolverFactory>(factories);
					factories.add(new URIUnitResolverFactory());
										
					this.fFactories = factories;
				}
				
				public UnitResolverFactory getFactory(URI uri) {
					for (UnitResolverFactory nextFactory : fFactories) {
						if(nextFactory.accepts(uri)) {
							return nextFactory;
						}
					}
					return null;
				}
				
				public UnitProxy getUnit(URI uri) {
					UnitResolverFactory factory = getFactory(uri);
					if(factory != null) {
						return factory.findUnit(uri);
					}
					return null;
				}				
		}
	
	}
		
}

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
package org.eclipse.m2m.internal.qvt.oml.emf.util.mmregistry;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;

public class EmfStandaloneMetamodelProvider implements IMetamodelProvider {
	
    private Registry fRegistry;	
	
    public EmfStandaloneMetamodelProvider() {
    	this(EPackage.Registry.INSTANCE);
    }
    
    public EmfStandaloneMetamodelProvider(EPackage.Registry packageRegistry) {
    	if(packageRegistry == null) {
    		throw new IllegalArgumentException();
    	}

    	fRegistry = packageRegistry;
    }
    
    public EPackage.Registry getPackageRegistry() {
    	return fRegistry;
    }
    
    public IMetamodelDesc[] getMetamodels() {
        List<IMetamodelDesc> descs = new ArrayList<IMetamodelDesc>();
        
        for (String uri : fRegistry.keySet()) {
        	Object pack = fRegistry.get(uri);
            if (pack instanceof EPackage.Descriptor) {
            	descs.add(new EmfMetamodelDesc((EPackage.Descriptor) pack, uri));
            } else if (pack instanceof EPackage) {
            	descs.add(new EmfMetamodelDesc((EPackage) pack, uri));
            }
        }
        
        return descs.toArray(new IMetamodelDesc[descs.size()]);
    }
		
	public IMetamodelDesc getMetamodel(String nsURI) {
		EPackage pack = getPackageRegistry().getEPackage(nsURI);
		return new EmfMetamodelDesc(pack, nsURI);
    }
    
}

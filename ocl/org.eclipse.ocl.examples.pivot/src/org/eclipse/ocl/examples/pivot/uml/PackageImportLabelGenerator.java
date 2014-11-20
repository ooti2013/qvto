/*******************************************************************************
 * Copyright (c) 2014 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.uml;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.common.label.AbstractLabelGenerator;
import org.eclipse.uml2.uml.PackageImport;

public final class PackageImportLabelGenerator extends AbstractLabelGenerator<PackageImport>
{
	public static void initialize(Registry registry) {
		registry.install(PackageImport.class, new PackageImportLabelGenerator());		
	}
	
	public PackageImportLabelGenerator() {
		super(PackageImport.class);
	}

	public void buildLabelFor(@NonNull Builder labelBuilder, @NonNull PackageImport object) {
		String name = object.getImportedPackage().getName();
		if (name != null)
			labelBuilder.appendString(name);
		else {
			labelBuilder.appendString("<null-packaged-");
			labelBuilder.appendString(object.getClass().getSimpleName());
			labelBuilder.appendString(">");
		}
	}
}
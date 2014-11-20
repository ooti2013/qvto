/*******************************************************************************
 * Copyright (c) 2014 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink (CEA LIST) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.utilities;

@SuppressWarnings("serial")
public class IllegalMetamodelException extends IllegalStateException
{
	public IllegalMetamodelException(String expectNsURI, String actualNsURI) {
		super("expected: " + expectNsURI + ", actual: " + actualNsURI);
	}
}
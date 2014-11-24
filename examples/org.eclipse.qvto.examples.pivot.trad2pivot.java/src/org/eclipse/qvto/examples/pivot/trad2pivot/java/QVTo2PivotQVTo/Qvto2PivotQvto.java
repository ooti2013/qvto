/*******************************************************************************
 * Copyright (c) 2008 Borland Software Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.qvto.examples.pivot.qvtoperational.Module;
import org.eclipse.qvto.examples.pivot.qvtoperational.OperationalTransformation;


public class Qvto2PivotQvto {
	
	private Ecore2Pivot ecoreToPivot;
	
	Qvto2PivotQvto ()
	{
		ecoreToPivot = new Ecore2Pivot ();
	}
	
	
	
	public void toOperationalTransformation (org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation input, OperationalTransformation output)
	{
		//output.getIntermediateClass().add(.......)
		//here all the attributs of OperationalTransformation
		toModule(input, output);
	}

	public void toModule (org.eclipse.m2m.internal.qvt.oml.expressions.Module input,  Module output)
	{
		//toEntryOperation (input.getEntry(),output.getEntry());  
		ecoreToPivot.toClass (input, output);
		
	}
	
	
}



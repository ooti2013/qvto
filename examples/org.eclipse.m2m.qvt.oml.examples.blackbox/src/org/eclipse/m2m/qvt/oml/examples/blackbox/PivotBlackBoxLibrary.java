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
package org.eclipse.m2m.qvt.oml.examples.blackbox;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.m2m.qvt.oml.blackbox.java.Operation;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.IntegerLiteralExp;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;

public class PivotBlackBoxLibrary {

	private static MetaModelManager metaModelManager;

	public PivotBlackBoxLibrary() {
		super();
		metaModelManager = new MetaModelManager();
	}

	@Operation(contextual = true)
	public static Object getClass(EClass self) throws ParserException {
		return metaModelManager.getPivotOf(Class.class, self);
	}

	@Operation(contextual = true)
	public static Object getType(EClassifier self) throws ParserException {
		return metaModelManager.getPivotOf(Type.class, self);
	}

	@Operation(contextual = true)
	public static Object getProperty(EStructuralFeature self)
			throws ParserException {
		return metaModelManager.getPivotOf(Property.class, self);
	}

	@Operation(contextual = true)
	public static Object getOperation(EOperation self) throws ParserException {
		return metaModelManager.getPivotOf(
				org.eclipse.ocl.examples.pivot.Operation.class, self);
	}

	@Operation(contextual = true)
	public static Object getParameter(EParameter self) throws ParserException {
		return metaModelManager.getPivotOf(Parameter.class, self);
	}

	@Operation(contextual = true)
	public static Object getIntegerType(EClassifier self) {
		return metaModelManager.getIntegerType();
	}

	@Operation(contextual = true)
	public static Object getResolvedOperationTest() throws Exception {
		// creating pivot operation
		org.eclipse.ocl.examples.pivot.Operation result = PivotFactory.eINSTANCE
				.createOperation();
		// creating a rightside
		IntegerLiteralExp rightside = PivotFactory.eINSTANCE
				.createIntegerLiteralExp();
		rightside.setType(PivotFactory.eINSTANCE.createType());
		// creating a leftside
		IntegerLiteralExp leftside = PivotFactory.eINSTANCE
				.createIntegerLiteralExp();
		leftside.setType(PivotFactory.eINSTANCE.createType());
		// calling the resolveOperation of the metaModelManager
		result = metaModelManager.resolveOperation(leftside.getType(), ">",
				rightside.getType());

		return result;
	}

}

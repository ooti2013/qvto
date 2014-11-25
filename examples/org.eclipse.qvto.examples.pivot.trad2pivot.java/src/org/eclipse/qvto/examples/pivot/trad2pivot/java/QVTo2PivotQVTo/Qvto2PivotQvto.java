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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.m2m.internal.qvt.oml.ast.parser.QvtOperationalValidationVisitor;
import org.eclipse.m2m.internal.qvt.oml.ast.parser.QvtOperationalVisitorCS;
import org.eclipse.m2m.internal.qvt.oml.compiler.QvtCompilerOptions;
import org.eclipse.m2m.internal.qvt.oml.cst.parser.AbstractQVTParser;
import org.eclipse.m2m.internal.qvt.oml.expressions.ContextualProperty;
import org.eclipse.qvto.examples.pivot.qvtoperational.EntryOperation;
import org.eclipse.qvto.examples.pivot.qvtoperational.Helper;
import org.eclipse.qvto.examples.pivot.qvtoperational.ImperativeCallExp;
import org.eclipse.qvto.examples.pivot.qvtoperational.ImperativeOperation;
import org.eclipse.qvto.examples.pivot.qvtoperational.Library;
import org.eclipse.qvto.examples.pivot.qvtoperational.MappingBody;
import org.eclipse.qvto.examples.pivot.qvtoperational.MappingCallExp;
import org.eclipse.qvto.examples.pivot.qvtoperational.MappingOperation;
import org.eclipse.qvto.examples.pivot.qvtoperational.MappingParameter;
import org.eclipse.qvto.examples.pivot.qvtoperational.ModelParameter;
import org.eclipse.qvto.examples.pivot.qvtoperational.ModelType;
import org.eclipse.qvto.examples.pivot.qvtoperational.Module;
import org.eclipse.qvto.examples.pivot.qvtoperational.ModuleImport;
import org.eclipse.qvto.examples.pivot.qvtoperational.ObjectExp;
import org.eclipse.qvto.examples.pivot.qvtoperational.OperationBody;
import org.eclipse.qvto.examples.pivot.qvtoperational.OperationalTransformation;
import org.eclipse.qvto.examples.pivot.qvtoperational.QVTOperationalFactory;
import org.eclipse.qvto.examples.pivot.qvtoperational.ResolveExp;
import org.eclipse.qvto.examples.pivot.qvtoperational.VarParameter;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.pivot.*;
import org.eclipse.ocl.examples.pivot.Class;

public class Qvto2PivotQvto {

	private Dispatcher dispatcher;
	private Ecore2Pivot ecoreToPivot;
	QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;

	Qvto2PivotQvto() {
		ecoreToPivot = new Ecore2Pivot();
	}
	
	public void toContextualProperty(
			ContextualProperty input,
			org.eclipse.qvto.examples.pivot.qvtoperational.ContextualProperty output) {
	}
	
	public void toOperationalTransformation(
			org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation input,
			OperationalTransformation output) {
		for (EClass element : input.getIntermediateClass()) {

			output.getIntermediateClass().add(dispatcher.classDispatcher(element));
		}
		
		for (EStructuralFeature element : input.getIntermediateProperty()) {

			output.getIntermediateProperty().add(dispatcher.propertyDispatcher(element));
		}
		
		for (org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter element : input.getModelParameter()) {

			//output.getIntermediateProperty().add(dispatcher.m(element));
		}
				
		toModule(input, output);
	}

	public void toModule(
			org.eclipse.m2m.internal.qvt.oml.expressions.Module input,
			Module output) {
		output.setEntry(factory.createEntryOperation());
		toEntryOperation(input.getEntry(), output.getEntry());
		ecoreToPivot.toClass(input, output);
		output.getOwnedOperation().add(output.getEntry());
	}

	public void toEntryOperation(
			org.eclipse.m2m.internal.qvt.oml.expressions.EntryOperation input,
			EntryOperation output) {
		output.setName(input.getName());
	}

	public void toHelper(
			org.eclipse.m2m.internal.qvt.oml.expressions.Helper input,
			Helper output) {
	}

	public void toImperativeCallExp(
			org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeCallExp input,
			ImperativeCallExp output) {
	}

	public void toImperativeOperation(
			org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation input,
			ImperativeOperation output) {
	}

	public void toLibrary(
			org.eclipse.m2m.internal.qvt.oml.expressions.Library input,
			Library output) {
	}

	public void toMappingBody(
			org.eclipse.m2m.internal.qvt.oml.expressions.MappingBody input,
			MappingBody output) {
		
		for (OCLExpression element : input.getInitSection()) {	
			org.eclipse.ocl.examples.pivot.OCLExpression res = null;
			res = dispatcher.oclExpDispatcher(element);
			output.getInitSection().add(res);
		}
		
		for (OCLExpression element : input.getEndSection()) {	
			org.eclipse.ocl.examples.pivot.OCLExpression res = null;
			res = dispatcher.oclExpDispatcher(element);
			output.getEndSection().add(res);
		}
		
		toOperationBody(input, output);
		
	}

	public void toMappingCallExp(
			org.eclipse.m2m.internal.qvt.oml.expressions.MappingCallExp input,
			MappingCallExp output) {
	}

	public void toMappingOperation(
			org.eclipse.m2m.internal.qvt.oml.expressions.MappingOperation input,
			MappingOperation output) {
		
		for (org.eclipse.m2m.internal.qvt.oml.expressions.MappingOperation element : input.getDisjunct()) {	
			MappingOperation res = null;
			toMappingOperation(element, res);
			output.getDisjunct().add(res);
		}

		for (org.eclipse.m2m.internal.qvt.oml.expressions.MappingOperation element : input.getInherited()) {	
			MappingOperation res = null;
			toMappingOperation(element, res);
			output.getInherited().add(res);
		}
		
		for (org.eclipse.m2m.internal.qvt.oml.expressions.MappingOperation element : input.getMerged()) {	
			MappingOperation res = null;
			toMappingOperation(element, res);
			output.getMerged().add(res);
		}
		
		//IMPORTANT NOTE: The input is a set but the result is a single value
		output.setWhen( dispatcher.oclExpDispatcher(input.getWhen().get(0)));
		
		output.setWhere( dispatcher.oclExpDispatcher(input.getWhere()) );
		
		toImperativeOperation(input, output);
	}

	public void toMappingParameter(
			org.eclipse.m2m.internal.qvt.oml.expressions.MappingParameter input,
			MappingParameter output) {
	}

	public void toModelParameter(
			org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter input,
			ModelParameter output) {
	}

	public void toModelType(
			org.eclipse.m2m.internal.qvt.oml.expressions.ModelType input,
			ModelType output) {
	}

	public void toModuleImport(
			org.eclipse.m2m.internal.qvt.oml.expressions.ModuleImport input,
			ModuleImport output) {
	}

	public void toObjectExp(
			org.eclipse.m2m.internal.qvt.oml.expressions.ObjectExp input,
			ObjectExp output) {
	}

	public void toOperationBody(
			org.eclipse.m2m.internal.qvt.oml.expressions.OperationBody input,
			OperationBody output) {
	}

	public void toResolveExp(
			org.eclipse.m2m.internal.qvt.oml.expressions.ResolveExp input,
			ResolveExp output) {
	}

	public void toVarParameter(
			org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter input,
			VarParameter output) {
	}
}
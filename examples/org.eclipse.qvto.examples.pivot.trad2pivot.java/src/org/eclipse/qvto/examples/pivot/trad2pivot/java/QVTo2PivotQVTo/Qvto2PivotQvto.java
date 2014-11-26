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


import javax.xml.ws.Dispatch;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.qvto.examples.pivot.qvtoperational.*;
import org.eclipse.qvto.examples.pivot.qvtoperational.impl.EntryOperationImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.DirectionKind;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.Variable;

public class Qvto2PivotQvto {

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
			output.getIntermediateClass().add(Dispatcher.classDispatcher(element));
		}
		
		for (EStructuralFeature element : input.getIntermediateProperty()) {
			output.getIntermediateProperty().add(Dispatcher.propertyDispatcher(element));
		}
		
		for (org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter element : input.getModelParameter()) {
			ModelParameter modelPar = factory.createModelParameter();
			toModelParameter(element, modelPar);
			output.getModelParameter().add(modelPar);
		}
				
		toModule(input, output);
	}

	public void toModule(org.eclipse.m2m.internal.qvt.oml.expressions.Module input, Module output) {
		ecoreToPivot.toClass(input, output);		
		for (Operation op :output.getOwnedOperation() ) {
			if (op instanceof EntryOperationImpl)
				output.setEntry((EntryOperation)op);
		}
		
		for (org.eclipse.ocl.ecore.Variable element : input.getOwnedVariable()) {
			Variable res = Dispatcher.variableDispatcher(element);
			output.getOwnedVariable().add(res);
		}
			
	}

	public void toEntryOperation(
			org.eclipse.m2m.internal.qvt.oml.expressions.EntryOperation input,
			EntryOperation output) {

		toImperativeOperation(input, output);
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
		
		ecoreToPivot.toOperation(input, output);
		
		OperationBody opbody = Dispatcher.opBodyDispatcher(input.getBody());
		output.setBody(opbody);
		
		for (org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter element : input.getResult()) {	
			VarParameter varparam = Dispatcher.varParameterDispatcher(element);
			output.getResult().add(varparam);
		}

		ImperativeOperation imop = Dispatcher.imperativeOpDispatcher(input.getOverridden());
		output.setOverridden(imop);
		
		output.setIsBlackbox(input.isIsBlackbox());
		
		VarParameter varparam = Dispatcher.varParameterDispatcher(input.getContext());
		output.setContext(varparam);
		
	}

	public void toLibrary(
			org.eclipse.m2m.internal.qvt.oml.expressions.Library input,
			Library output) {
	}

	public void toMappingBody(
			org.eclipse.m2m.internal.qvt.oml.expressions.MappingBody input,
			MappingBody output) {
		
		for (org.eclipse.ocl.ecore.OCLExpression element : input.getInitSection()) {	
			OCLExpression res = Dispatcher.oclExpDispatcher(element);
			output.getInitSection().add(res);
		}
		
		for (org.eclipse.ocl.ecore.OCLExpression element : input.getEndSection()) {	
			OCLExpression res = Dispatcher.oclExpDispatcher(element);
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
		
		//toImperativeOperation(input, output);

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
		if (!input.getWhen().isEmpty())
			output.setWhen( Dispatcher.oclExpDispatcher(input.getWhen().get(0)));
		
		output.setWhere( Dispatcher.oclExpDispatcher(input.getWhere()));

	}

	public void toMappingParameter(
			org.eclipse.m2m.internal.qvt.oml.expressions.MappingParameter input,
			MappingParameter output) {

		//TODO: What to do with circular calls?
		//toVarParameter(input, output);

		//ModelParameter mp = null;
		//toModelParameter(input.getExtent(), mp);
		//output.setExtent(mp);
	}

	public void toModelParameter(org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter input, ModelParameter output) 
	{
		toVarParameter(input, output);
	}

	public org.eclipse.qvto.examples.pivot.qvtoperational.DirectionKind toDirectionKing (DirectionKind input)
	{
		org.eclipse.qvto.examples.pivot.qvtoperational.DirectionKind res;
		if (input.getValue() == DirectionKind.IN_VALUE){
			res = org.eclipse.qvto.examples.pivot.qvtoperational.DirectionKind.IN;
		}else if (input.getValue() == DirectionKind.OUT_VALUE){
			res = org.eclipse.qvto.examples.pivot.qvtoperational.DirectionKind.OUT;
		}else{
			res = org.eclipse.qvto.examples.pivot.qvtoperational.DirectionKind.INOUT;
		}
		return res;
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
		
		for (org.eclipse.ocl.ecore.OCLExpression element : input.getContent()) {	
			OCLExpression expRes = Dispatcher.oclExpDispatcher(element);
			output.getContent().add(expRes);
		}
		
		//TODO What to do with circular calls?
		//ImperativeOperation res = Dispatcher.imperativeOpDispatcher(input.getOperation());
		//output.setOperation(res);
		
		
		for (org.eclipse.ocl.ecore.Variable element : input.getVariable()) {	
			Variable varRes = Dispatcher.variableDispatcher(element);
			output.getVariable().add(varRes);
		}
		
		ecoreToPivot.toElement(input, output);
			
	}

	public void toResolveExp(
			org.eclipse.m2m.internal.qvt.oml.expressions.ResolveExp input,
			ResolveExp output) {
	}
	
	public void toResolveInExp(
			org.eclipse.m2m.internal.qvt.oml.expressions.ResolveInExp input,
			ResolveInExp output) {
	}

	public void toVarParameter( org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter input, VarParameter output) {
		ecoreToPivot.toVariable(input, output);
		ecoreToPivot.toParameter(input, output);
		output.setKind(toDirectionKing(input.getKind()));
		output.setCtxOwner(Dispatcher.imperativeOpDispatcher(input.getCtxOwner()));
		output.setResOwner(Dispatcher.imperativeOpDispatcher(input.getResOwner()));
	}

	public void toConstructorBody(
			org.eclipse.m2m.internal.qvt.oml.expressions.ConstructorBody input,
			ConstructorBody output) {
		
	}
}

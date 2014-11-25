package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EClassImpl;
import org.eclipse.emf.ecore.impl.EOperationImpl;
import org.eclipse.emf.ecore.impl.EParameterImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.impl.*;
import org.eclipse.ocl.ecore.impl.VariableImpl;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.OCLExpression;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.Parameter;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.qvto.examples.pivot.qvtoperational.*;

public class Dispatcher {

	private static Qvto2PivotQvto qvto2pivot = new Qvto2PivotQvto();
	private static Ecore2Pivot ecore2pivot = new Ecore2Pivot();
	private static QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;
	private static PivotFactory pfactory = PivotFactory.eINSTANCE;

	public static Parameter parameterDispatcher(EParameter input) {
		
		if (input instanceof MappingParameterImpl) {
			MappingParameter res = factory.createMappingParameter();
			qvto2pivot.toMappingParameter((org.eclipse.m2m.internal.qvt.oml.expressions.MappingParameter)input, res);
			return res;
		}
		
		if (input instanceof ModelParameterImpl) {
			ModelParameter res = factory.createModelParameter();
			qvto2pivot.toModelParameter((org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter)input, res);
			return res;
		}
		
		if (input instanceof VarParameterImpl) {
			VarParameter res = factory.createVarParameter();
			qvto2pivot.toVarParameter((org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter)input, res);
			return res;
		}
		
		if (input instanceof EParameterImpl) {
			Parameter res = pfactory.createParameter();
			//TODO
			//qvto2pivot.toParameter((org.eclipse.m2m.internal.qvt.oml.expressions.Parameter)input, res);
			return res;
		}

		
		return null;
	}
	
	public static VarParameter varParameterDispatcher(org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter input) {
		
		if (input instanceof MappingParameterImpl) {
			MappingParameter res = factory.createMappingParameter();
			qvto2pivot.toMappingParameter((org.eclipse.m2m.internal.qvt.oml.expressions.MappingParameter)input, res);
			return res;
		}
		
		if (input instanceof ModelParameterImpl) {
			ModelParameter res = factory.createModelParameter();
			qvto2pivot.toModelParameter((org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter)input, res);
			return res;
		}
		
		if (input instanceof VarParameterImpl) {
			VarParameter res = factory.createVarParameter();
			qvto2pivot.toVarParameter((org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter)input, res);
			return res;
		}
		
		return null;
	}
	
	public static OperationBody opBodyDispatcher(org.eclipse.m2m.internal.qvt.oml.expressions.OperationBody input) {
		
		if (input instanceof MappingBodyImpl) {
			MappingBody res = factory.createMappingBody();
			qvto2pivot.toMappingBody((org.eclipse.m2m.internal.qvt.oml.expressions.MappingBody)input, res);
			return res;
		}
		
		if (input instanceof ConstructorBodyImpl) {
			ConstructorBody res = factory.createConstructorBody();
			qvto2pivot.toConstructorBody((org.eclipse.m2m.internal.qvt.oml.expressions.ConstructorBody)input, res);
			return res;
		}
		
		if (input instanceof OperationBodyImpl) {
			OperationBody res = factory.createOperationBody();
			qvto2pivot.toOperationBody((org.eclipse.m2m.internal.qvt.oml.expressions.OperationBody)input, res);
			return res;
		}
		
		return null;
	}
	
	public static Variable variableDispatcher(org.eclipse.ocl.ecore.Variable input) {
		
		if (input instanceof MappingParameterImpl) {
			MappingParameter res = factory.createMappingParameter();
			qvto2pivot.toMappingParameter((org.eclipse.m2m.internal.qvt.oml.expressions.MappingParameter)input, res);
			return res;
		}
		
		if (input instanceof ModelParameterImpl) {
			ModelParameter res = factory.createModelParameter();
			qvto2pivot.toModelParameter((org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter)input, res);
			return res;
		}
		
		if (input instanceof VarParameterImpl) {
			VarParameter res = factory.createVarParameter();
			qvto2pivot.toVarParameter((org.eclipse.m2m.internal.qvt.oml.expressions.VarParameter)input, res);
			return res;
		}
		
		if (input instanceof VariableImpl) {
			Variable res = pfactory.createVariable();
			ecore2pivot.toVariable((org.eclipse.ocl.ecore.Variable)input, res);
			return res;
		}
		
		return null;
	}
	
	public static ImperativeOperation imperativeOpDispatcher(org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation input) {
		
		if (input instanceof MappingOperationImpl) {
			MappingOperation res = factory.createMappingOperation();
			qvto2pivot.toMappingOperation((org.eclipse.m2m.internal.qvt.oml.expressions.MappingOperation)input, res);
			return res;
		}
		
		if (input instanceof HelperImpl) {
			Helper res = factory.createHelper();
			qvto2pivot.toHelper((org.eclipse.m2m.internal.qvt.oml.expressions.Helper)input, res);
			return res;
		}
		
		if (input instanceof EntryOperationImpl) {
			EntryOperation res = factory.createEntryOperation();
			qvto2pivot.toEntryOperation((org.eclipse.m2m.internal.qvt.oml.expressions.EntryOperation)input, res);
			return res;
		}
		
		if (input instanceof ImperativeOperationImpl) {
			ImperativeOperation res = factory.createImperativeOperation();
			qvto2pivot.toImperativeOperation((org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation)input, res);
			return res;
		}
		
		return null;
	}
	
	public static OCLExpression oclExpDispatcher(org.eclipse.ocl.ecore.OCLExpression input) {

		if (input instanceof ObjectExpImpl) {
			ObjectExp res = factory.createObjectExp();
			qvto2pivot.toObjectExp((org.eclipse.m2m.internal.qvt.oml.expressions.ObjectExp)input, res);
			return res;
		}
		
		if (input instanceof MappingCallExpImpl) {
			MappingCallExp res = factory.createMappingCallExp();
			qvto2pivot.toMappingCallExp((org.eclipse.m2m.internal.qvt.oml.expressions.MappingCallExp)input, res);
			return res;
		}
		
		if (input instanceof ImperativeCallExpImpl) {
			ImperativeCallExp res = factory.createImperativeCallExp();
			qvto2pivot.toImperativeCallExp((org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeCallExp)input, res);
			return res;
		}
		
		if (input instanceof ResolveExpImpl) {
			ResolveExp res = factory.createResolveExp();
			qvto2pivot.toResolveExp((org.eclipse.m2m.internal.qvt.oml.expressions.ResolveExp)input, res);
			return res;
		}
		
		if (input instanceof ResolveInExpImpl) {
			ResolveInExp res = factory.createResolveInExp();
			qvto2pivot.toResolveInExp((org.eclipse.m2m.internal.qvt.oml.expressions.ResolveInExp)input, res);
			return res;
		}
		
		/* TODO:
		 * Dispatch to OCLImperative and OCL concepts.
		 */
		
		return null;
	}
	
	public static Class classDispatcher(EClass input) {

		if (input instanceof ModelTypeImpl) {
			ModelType res = factory
					.createModelType();
			qvto2pivot.toModelType((org.eclipse.m2m.internal.qvt.oml.expressions.ModelType)input, res);
			return res;
		}
		else if (input instanceof LibraryImpl) {
			Library res = factory
					.createLibrary();
			qvto2pivot.toLibrary((org.eclipse.m2m.internal.qvt.oml.expressions.Library)input, res);
		return res;
		}
		else if (input instanceof OperationalTransformationImpl) {
			OperationalTransformation res = factory
					.createOperationalTransformation();
			qvto2pivot.toOperationalTransformation(
					(org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation) input,
					res);
			return res;
		}
		else if (input instanceof ModuleImpl) {
			Module res = factory
					.createModule();
			qvto2pivot.toModule((org.eclipse.m2m.internal.qvt.oml.expressions.Module)input, res);
			return res;
		}
		else if (input instanceof EClassImpl) {
			Class res = pfactory
					.createClass();
			ecore2pivot.toClass(input, res);
			return res;
		}		
		
		return null;
	}
	
	
	public static Property propertyDispatcher(EStructuralFeature input) {

		if (input instanceof ContextualProperty) {
			org.eclipse.qvto.examples.pivot.qvtoperational.ContextualProperty res = factory
					.createContextualProperty();
			qvto2pivot.toContextualProperty((ContextualProperty)input, res);
			return res;
		}
		
		else if (input instanceof EStructuralFeature) {
			Property res = pfactory
					.createProperty();
			ecore2pivot.toProperty(input, res);
		return res;
		}
		
	    //TODO: One possible inheritance is "EAttribute" but we dont know how to map it
	    //TODO::Here Ereference should be also disjuncted, but there is no equivalent class in pivot yet. 
		return null;
	}
	
	
	public static org.eclipse.ocl.examples.pivot.Operation operationDispatcher(EOperation op) {
		if (op instanceof EntryOperationImpl){
			org.eclipse.qvto.examples.pivot.qvtoperational.EntryOperation entry = factory.createEntryOperation();
			qvto2pivot.toEntryOperation((org.eclipse.m2m.internal.qvt.oml.expressions.EntryOperation)op, entry);
			return entry;
		}
		if (op instanceof MappingOperationImpl){
			org.eclipse.qvto.examples.pivot.qvtoperational.MappingOperation mapp = factory.createMappingOperation();
			qvto2pivot.toMappingOperation((org.eclipse.m2m.internal.qvt.oml.expressions.MappingOperation)op, mapp);
			return mapp;
		}
		if (op instanceof ConstructorImpl){
			org.eclipse.qvto.examples.pivot.qvtoperational.Constructor constr = factory.createConstructor();
			//qvto2pivot.toConstructor((org.eclipse.m2m.internal.qvt.oml.expressions.Constructor)op, constr);
			return constr;
		}
		if (op instanceof HelperImpl){
			org.eclipse.qvto.examples.pivot.qvtoperational.Helper helper= factory.createHelper();
			qvto2pivot.toHelper((org.eclipse.m2m.internal.qvt.oml.expressions.Helper)op, helper);
			return helper;
		}
		if (op instanceof ImperativeOperationImpl){
			org.eclipse.qvto.examples.pivot.qvtoperational.ImperativeOperation imperative= factory.createImperativeOperation();
			qvto2pivot.toImperativeOperation((org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation)op, imperative);
			return imperative;
		}
		if (op instanceof EOperationImpl){
			Operation operation= pfactory.createOperation();
			ecore2pivot.toOperation((EOperation)op, operation);
			return operation;
		}
		
		return null;
	}

	public static org.eclipse.ocl.examples.pivot.Type typeDispatcher(EClassifier eClassifier) {
		return null;
	}
	
	public static org.eclipse.ocl.examples.pivot.Property propertyDispatcher(EAttribute attr) {
		return null;
	}

	public static org.eclipse.ocl.examples.pivot.Package packageDispatcher(EPackage ePackage) {
		return null;
	}
}


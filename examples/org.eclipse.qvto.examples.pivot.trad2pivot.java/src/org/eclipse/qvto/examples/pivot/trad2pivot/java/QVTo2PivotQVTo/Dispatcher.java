package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.internal.qvt.oml.expressions.impl.EntryOperationImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.impl.HelperImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.impl.ImperativeOperationImpl;
import org.eclipse.m2m.internal.qvt.oml.expressions.impl.MappingOperationImpl;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.qvto.examples.pivot.qvtoperational.*;
import org.eclipse.qvto.examples.pivot.qvtoperational.impl.ConstructorImpl;

public class Dispatcher {

	private static Qvto2PivotQvto qvto2pivot = new Qvto2PivotQvto();
	private static Ecore2Pivot ecore2pivot = new Ecore2Pivot();
	private static QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;
	private static PivotFactory pfactory = PivotFactory.eINSTANCE;

	public static Variable variableDispatcher(org.eclipse.ocl.ecore.Variable input) {
		
		return null;
	}
	
	public static ImperativeOperation imperativeOpDispatcher(org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation input) {
		
		return null;
	}
	
	public static org.eclipse.ocl.examples.pivot.OCLExpression oclExpDispatcher(OCLExpression input) {

		return null;
	}
	
	public static Class classDispatcher(EClass input) {

		if (input instanceof org.eclipse.m2m.internal.qvt.oml.expressions.ModelType) {
			ModelType res = factory
					.createModelType();
			qvto2pivot.toModelType((org.eclipse.m2m.internal.qvt.oml.expressions.ModelType)input, res);
			return res;
		}
		else if (input instanceof org.eclipse.m2m.internal.qvt.oml.expressions.Library) {
			Library res = factory
					.createLibrary();
			qvto2pivot.toLibrary((org.eclipse.m2m.internal.qvt.oml.expressions.Library)input, res);
		return res;
		}
		else if (input instanceof org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation) {
			OperationalTransformation res = factory
					.createOperationalTransformation();
			qvto2pivot.toOperationalTransformation(
					(org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation) input,
					res);
			return res;
		}
		else if (input instanceof Module) {
			Module res = factory
					.createModule();
			qvto2pivot.toModule((org.eclipse.m2m.internal.qvt.oml.expressions.Module)input, res);
			return res;
		}
		else if (input instanceof EClass) {
			Class res = pfactory
					.createClass();
			ecore2pivot.toClass(input, res);
			return res;
		}		
		
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
		
		return null;
	}
	
	public static org.eclipse.ocl.examples.pivot.Property propertyDispatcher(EAttribute attr) {
		return null;
	}

	public static org.eclipse.ocl.examples.pivot.Package packageDispatcher(EPackage ePackage) {
		return null;
	}
}


package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.qvto.examples.pivot.qvtoperational.*;

public class Dispatcher {

	private Qvto2PivotQvto qvto2pivot;
	private Ecore2Pivot ecore2pivot;
	private QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;
	private PivotFactory pfactory = PivotFactory.eINSTANCE;
	
	Dispatcher ()
	{
		qvto2pivot = new Qvto2PivotQvto();
		ecore2pivot = new Ecore2Pivot();
	}
	/*
 * 
 * 
 * 
 * */

	public Variable variableDispatcher(org.eclipse.ocl.ecore.Variable input) {
		
		return null;
	}
	
	public ImperativeOperation imperativeOpDispatcher(org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation input) {
		
		return null;
	}
	
	public org.eclipse.ocl.examples.pivot.OCLExpression oclExpDispatcher(OCLExpression input) {

		return null;
	}
	
	public Class classDispatcher(EClass input) {

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
	
	
	public org.eclipse.ocl.examples.pivot.Operation operationDispatcher(EOperation op) {
		return null;
	}
	
	public org.eclipse.ocl.examples.pivot.Property propertyDispatcher(EAttribute attr) {
		return null;
	}

	public org.eclipse.ocl.examples.pivot.Package packageDispatcher(EPackage ePackage) {
		return null;
	}
}


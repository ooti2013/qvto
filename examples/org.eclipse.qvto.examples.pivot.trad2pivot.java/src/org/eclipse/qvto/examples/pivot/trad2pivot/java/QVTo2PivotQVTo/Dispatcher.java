package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation;
import org.eclipse.m2m.internal.qvt.oml.expressions.Module;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.qvto.examples.pivot.qvtoperational.Library;
import org.eclipse.qvto.examples.pivot.qvtoperational.ModelType;
import org.eclipse.qvto.examples.pivot.qvtoperational.OperationalTransformation;
import org.eclipse.qvto.examples.pivot.qvtoperational.QVTOperationalFactory;
import org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo.Qvto2PivotQvto;

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
	
	public org.eclipse.qvto.examples.pivot.qvtoperational.ImperativeOperation imperativeOpDispatcher(ImperativeOperation input) {
		
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
			org.eclipse.qvto.examples.pivot.qvtoperational.Module res = factory
					.createModule();
			qvto2pivot.toModule((Module)input, res);
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
}


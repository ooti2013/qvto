package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.qvto.examples.pivot.qvtoperational.OperationalTransformation;
import org.eclipse.qvto.examples.pivot.qvtoperational.QVTOperationalFactory;
import org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo.Qvto2PivotQvto;

public class Dispatcher {

	private Qvto2PivotQvto qvto2pivot;
	private QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;
	
	Dispatcher ()
	{

	}
	
	public org.eclipse.ocl.examples.pivot.OCLExpression oclDispatcher(OCLExpression input) {

		return null;
	}
	
	private Class classDispatcher(EClass input) {

		if (input instanceof org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation) {
			OperationalTransformation res = factory
					.createOperationalTransformation();
			qvto2pivot.toOperationalTransformation(
					(org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation) input,
					res);
			return res;
		}

		return null;
	}

}


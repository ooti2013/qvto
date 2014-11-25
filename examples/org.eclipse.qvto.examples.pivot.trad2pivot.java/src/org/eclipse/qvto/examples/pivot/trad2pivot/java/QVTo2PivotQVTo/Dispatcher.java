package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.m2m.internal.qvt.oml.expressions.Module;
import org.eclipse.ocl.ecore.OCLExpression;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.Operation;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.qvto.examples.pivot.qvtoperational.Library;
import org.eclipse.qvto.examples.pivot.qvtoperational.ModelType;
import org.eclipse.qvto.examples.pivot.qvtoperational.OperationalTransformation;
import org.eclipse.qvto.examples.pivot.qvtoperational.QVTOperationalFactory;

public class Dispatcher {

	private static Qvto2PivotQvto qvto2pivot = new Qvto2PivotQvto();
	private static Ecore2Pivot ecore2pivot = new Ecore2Pivot();
	private static QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;
	private static PivotFactory pfactory = PivotFactory.eINSTANCE;
	
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
	
	
	public static org.eclipse.ocl.examples.pivot.Operation operationDispatcher(EOperation op) {
		return null;
	}
	
	public static org.eclipse.ocl.examples.pivot.Property propertyDispatcher(EAttribute attr) {
		return null;
	}

	public static org.eclipse.ocl.examples.pivot.Package packageDispatcher(EPackage ePackage) {
		return null;
	}
}


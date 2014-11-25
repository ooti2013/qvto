package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;



import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.ocl.examples.pivot.Variable;
import org.eclipse.ocl.examples.pivot.Annotation;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.PivotFactory;
import org.eclipse.ocl.examples.pivot.Type;


public class Ecore2Pivot {

	//private MetaModelManager metamodelManager;
	//private static Dispatcher dispatcher = new Dispatcher();
	private static PivotFactory pivotFactory;
	
	
	Ecore2Pivot ()
	{
		pivotFactory = PivotFactory.eINSTANCE;
	}
	
	void toClass (EClass input, Class output)
	{
		output.setIsAbstract(input.isAbstract());
		output.setIsInterface(input.isInterface());

		for (EOperation op : input.getEOperations()) {
			output.getOwnedOperation().add(Dispatcher.operationDispatcher (op));
		}
		
		/*for (EClass cl: input.getEAllSuperTypes()) {
			output.getNestedType().add(Dispatcher.classDispatcher(cl));
		}
		
		for (EAttribute attr : input.getEAllAttributes()) {
			output.getOwnedAttribute().add(Dispatcher.propertyDispatcher (attr));
		}*/

		toType(input, output);
	}
	
	
	void toType (EClassifier input, Type output)
	{
		output.setInstanceClassName(input.getInstanceClassName());
		output.setPackage(Dispatcher.packageDispatcher (input.getEPackage()));
		
		toNamedElement(input, output);
	}
	
	void toNamedElement (ENamedElement input, NamedElement output)
	{
		output.setName(input.getName());
		toElement(input, output);
	}
	
	void toElement (EModelElement input, Element output)
	{
		for (EAnnotation eAnnotation : input.getEAnnotations()) {
			Annotation pivotAnnotation = pivotFactory.createAnnotation();
			toAnnotation (eAnnotation, pivotAnnotation);
			output.getOwnedAnnotation().add(pivotAnnotation);
		}
	}
	
	void toAnnotation (EAnnotation input, Annotation outout)
	{
		//TODO
	}
	
	void toVariable (org.eclipse.ocl.ecore.Variable input, Variable outout)
	{
		//TODO
	}
	
}

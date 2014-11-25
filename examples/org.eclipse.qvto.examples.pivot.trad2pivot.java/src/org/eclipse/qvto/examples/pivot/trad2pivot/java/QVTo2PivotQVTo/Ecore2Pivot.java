package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;



import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.ocl.examples.pivot.Annotation;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.PivotFactory;


public class Ecore2Pivot {

	private MetaModelManager metamodelManager;
	private Dispatcher dispatcher;
	private PivotFactory pivotFactory;
	
	
	Ecore2Pivot ()
	{
		metamodelManager = new MetaModelManager();
		dispatcher = new Dispatcher();
		pivotFactory = PivotFactory.eINSTANCE;
	}
	
	void toClass (EClass input, Class output)
	{
		output.setIsAbstract(input.isAbstract());
		output.setIsInterface(input.isInterface());

		for (EOperation op : input.getEAllOperations()) {
			output.getOwnedOperation().add(dispatcher.operationDispatcher (op));
		}
		
		for (EClass cl: input.getEAllSuperTypes()) {
			output.getNestedType().add(dispatcher.classDispatcher(cl));
		}
		
		for (EAttribute attr : input.getEAllAttributes()) {
			output.getOwnedAttribute().add(dispatcher.propertyDispatcher (attr));
		}

		toType(input, output);
	}
	
	
	void toType (EClassifier input, Type output)
	{
		output.setInstanceClassName(input.getInstanceClassName());
		output.setPackage(dispatcher.packageDispatcher (input.getEPackage()));
		
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
	
}

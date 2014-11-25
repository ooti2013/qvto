package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ocl.examples.pivot.Class;
import org.eclipse.ocl.examples.pivot.Property;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;



public class Ecore2Pivot {

	private MetaModelManager metamodelManager;
	
	Ecore2Pivot ()
	{
		metamodelManager = new MetaModelManager();
	}
	
	void toClass (EClass input, Class output)
	{
		//Class pivotsClass =  (Class) metamodelManager.getClass(input);
		
		output.setName(input.getName());
		//..so on
	}
	
	void toProperty (EStructuralFeature input, Property output)
	{
		//TODO may call metaModelManager
		output.setName(input.getName());
		//..so on
	}
}
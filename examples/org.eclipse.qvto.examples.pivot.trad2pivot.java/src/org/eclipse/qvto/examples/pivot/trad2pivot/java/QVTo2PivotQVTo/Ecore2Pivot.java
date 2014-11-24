package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.ocl.examples.pivot.Class;
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
		
		//output.setName(pivotsClass.getName());
		//..so on
	}
}

package org.eclipse.qvto.examples.pivot.trad2pivot.java.QVTo2PivotQVTo;

import java.net.URL;
import java.util.Collections;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.internal.qvt.oml.InternalTransformationExecutor;
import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.eclipse.qvto.examples.pivot.qvtoperational.QVTOperationalFactory;

public class Main {
	
public static void main(String[] args) throws Exception {

		Qvto2PivotQvto transformations = new Qvto2PivotQvto();
		OperationalTransformation input = loadInputTransformation();
		QVTOperationalFactory factory = QVTOperationalFactory.eINSTANCE;
		//call the transformation
		org.eclipse.qvto.examples.pivot.qvtoperational.OperationalTransformation res = factory.createOperationalTransformation();// = lib.toOperationalTransformation (input);
		
		Resource resource = input.eResource();
		EList <EObject> content= resource.getContents();
		
		for (EObject obj : content) {
			if ( obj instanceof OperationalTransformation)
			{
				transformations.toOperationalTransformation((OperationalTransformation)obj, res);
			}
			//Other possible root objects
		}
		
		// save the output
		ResourceSet resourceSet2 = new ResourceSetImpl();
		URI uri = URI.createFileURI("./result.xmi");
		Resource outResource = resourceSet2.createResource(uri);
		outResource.getContents().add(res);
		outResource.save(Collections.emptyMap());
	}

	static OperationalTransformation loadInputTransformation () 
	{
		Main m = new Main();
		final URI transformUri = m.getUri("./qvto-input.qvto");
		InternalTransformationExecutor executor = new InternalTransformationExecutor(transformUri);
		executor.getUnit();
		OperationalTransformation t = executor.getTransformation();
		return t;
		
	}
	
	private URI getUri(final String filename) {
        final URL url = this.getClass().getResource(filename);
        return URI.createURI(url.toString());
    }
}
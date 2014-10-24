package org.eclipse.m2m.tests.qvt.oml.callapi;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.m2m.qvt.oml.BasicModelExtent;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;


public class Bug427237 extends TestCase {

	URI modelUri;
	Resource resource;
	EObject object;
	BasicModelExtent inputExtent;
	BasicModelExtent outputExtent;
	TransformationExecutor executor;

	@Override
	protected void setUp() {
		URI modelUri = URI.createPlatformPluginURI("org.eclipse.m2m.tests.qvt.oml/parserTestData/models/bug427237/in.ecore", //$NON-NLS-1$
				true);
		ResourceSet resSet = new ResourceSetImpl();
		resource = resSet.getResource(modelUri, true);
		object = resource.getContents().get(0);
		inputExtent = new BasicModelExtent();
		inputExtent.add(object);
		outputExtent = new BasicModelExtent();
		URI uri = URI.createPlatformPluginURI("org.eclipse.m2m.tests.qvt.oml/parserTestData/models/bug427237/bug427237.qvto", //$NON-NLS-1$
				true);
		executor = new TransformationExecutor(uri);
	}

	/**
	 * Test if blackbox transformations can be successfully executed when running via TransformationExecutor.
	 */
	public void testBlackboxTransformation() {
		ExecutionDiagnostic diagnostic = executor.execute(new ExecutionContextImpl(), inputExtent, outputExtent);
		assertEquals(Diagnostic.OK, diagnostic.getSeverity());
		assertEquals(0, diagnostic.getCode());
	}
}

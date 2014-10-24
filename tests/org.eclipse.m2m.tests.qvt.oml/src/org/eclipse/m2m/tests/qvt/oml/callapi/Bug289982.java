package org.eclipse.m2m.tests.qvt.oml.callapi;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.qvt.oml.ExecutionContextImpl;
import org.eclipse.m2m.qvt.oml.ExecutionDiagnostic;
import org.eclipse.m2m.qvt.oml.TransformationExecutor;


public class Bug289982 extends TestCase {

	TransformationExecutor executor;

	@Override
	protected void setUp() {
		URI uri = URI.createPlatformPluginURI("org.eclipse.m2m.tests.qvt.oml/parserTestData/models/bug289982/bug289982.qvto", //$NON-NLS-1$
				true);
		executor = new TransformationExecutor(uri);
	}

	/**
	 * Test if blackbox operations can be successfully executed when running via TransformationExecutor.
	 */
	public void testBlackboxOperation() {
		ExecutionDiagnostic diagnostic = executor.execute(new ExecutionContextImpl());
		assertEquals(Diagnostic.OK, diagnostic.getSeverity());
		assertEquals(0, diagnostic.getCode());
	}
}

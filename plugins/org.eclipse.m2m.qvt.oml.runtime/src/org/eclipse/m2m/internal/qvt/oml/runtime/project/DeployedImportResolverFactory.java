package org.eclipse.m2m.internal.qvt.oml.runtime.project;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitResolver;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitResolverFactory;

public class DeployedImportResolverFactory extends UnitResolverFactory {

	@Override
	public boolean accepts(URI uri) {
		return EMFPlugin.IS_ECLIPSE_RUNNING && isDeployedByID(uri);
	}

	@Override
	public UnitResolver getResolver(URI uri) {
		return DeployedImportResolver.INSTANCE;
	}

	@Override
	public String getQualifiedName(URI uri) {
		return uri.path();
	}
	
	private static boolean isDeployedByID(URI uri) {
		return uri.scheme() == null && !uri.hasDevice() && !uri.hasAuthority()
				&& !uri.hasEmptyPath() && !uri.hasQuery() && !uri.hasFragment()
				&& !uri.hasAbsolutePath();
	}

}

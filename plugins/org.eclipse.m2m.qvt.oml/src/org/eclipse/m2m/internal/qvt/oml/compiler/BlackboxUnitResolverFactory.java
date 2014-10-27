package org.eclipse.m2m.internal.qvt.oml.compiler;

import org.eclipse.emf.common.util.URI;

public class BlackboxUnitResolverFactory extends UnitResolverFactory {

	@Override
	public boolean accepts(URI uri) {
		return BlackboxUnitResolver.isBlackboxUnitURI(uri);
	}

	@Override
	public UnitResolver getResolver(URI uri) {
		return BlackboxUnitResolver.DEFAULT;
	}

	@Override
	public String getQualifiedName(URI uri) {
		return uri.lastSegment(); 
	}

}

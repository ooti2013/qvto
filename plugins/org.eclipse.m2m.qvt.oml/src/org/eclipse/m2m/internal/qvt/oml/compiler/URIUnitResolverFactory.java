package org.eclipse.m2m.internal.qvt.oml.compiler;

import org.eclipse.emf.common.util.URI;

public class URIUnitResolverFactory extends UnitResolverFactory {

	@Override
	public boolean accepts(URI uri) {
		return true;
	}

	@Override
	public UnitResolver getResolver(URI uri) {
		
		URI baseURI;
		
		if(uri.segmentCount() > 1) {
			baseURI = uri.trimSegments(1);
		} else {
			baseURI = URI.createURI("/"); //$NON-NLS-1$
		}
		
		return new URIUnitResolver(baseURI);
	}

	@Override
	public String getQualifiedName(URI uri) {
		return uri.trimFileExtension().lastSegment();
	}

}

package org.eclipse.m2m.internal.qvt.oml.project.builder;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.m2m.internal.qvt.oml.compiler.ResolverUtils;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitResolver;
import org.eclipse.m2m.internal.qvt.oml.compiler.UnitResolverFactory;
import org.eclipse.m2m.internal.qvt.oml.emf.util.URIUtils;
import org.eclipse.m2m.internal.qvt.oml.project.QVTOProjectPlugin;

public class WorkspaceUnitResolverFactory extends UnitResolverFactory {

	@Override
	public boolean accepts(URI uri) {
		return EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE && (uri.isPlatformResource() || isWorkspacePath(uri));
	}

	@Override
	public UnitResolver getResolver(URI uri) {
		
		if(isWorkspacePath(uri)) {
			uri = URI.createPlatformResourceURI(uri.path(), false);
		}
		
		IFile file = URIUtils.getFile(uri);
		
		try {	
			return WorkspaceUnitResolver.getResolver(file.getProject());
		}
		catch (CoreException e) {
			QVTOProjectPlugin.log(e.getStatus());
		}
		
		return null;
	}

	@Override
	public String getQualifiedName(URI uri) {
		
		if(isWorkspacePath(uri)) {
			uri = URI.createPlatformResourceURI(uri.path(), false);
		}
		
		IResource resource = URIUtils.getResource(uri);
		
		try {
			IContainer sourceContainer = QVTOBuilderConfig.getConfig(resource.getProject()).getSourceContainer();
			URI sourceContainerUri = URIUtils.getResourceURI(sourceContainer);
			URI relativeUri = uri.deresolve(sourceContainerUri).trimFileExtension();
			assert (relativeUri.isRelative());
			
			// exclude first segment which is the source container itself
			return ResolverUtils.toQualifiedName(relativeUri.segments(), 1, relativeUri.segmentCount()-1);
		}
		catch(CoreException e) {
			QVTOProjectPlugin.log(e.getStatus());
		}
		
		return null;
	}
	
	private static boolean isWorkspacePath(URI uri) {
		return uri.scheme() == null && !uri.hasDevice() && !uri.hasAuthority()
				&& !uri.hasEmptyPath() && !uri.hasQuery() && !uri.hasFragment()
				&& uri.hasAbsolutePath();
	}
		
}
/*******************************************************************************
 * Copyright (c) 2007 Borland Software Corporation
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.runtime.launch;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.model.LaunchConfigurationDelegate;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.m2m.internal.qvt.oml.runtime.generator.TraceSerializer;
import org.eclipse.m2m.internal.qvt.oml.runtime.generator.TransformationRunner;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtTransformation;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtTransformation.TransformationParameter;
import org.eclipse.m2m.internal.qvt.oml.runtime.project.QvtTransformation.TransformationParameter.DirectionKind;
import org.eclipse.m2m.internal.qvt.oml.runtime.util.MiscUtil;
import org.eclipse.m2m.qvt.oml.ast.environment.QvtOperationalStdLibrary;
import org.eclipse.m2m.qvt.oml.common.Logger;
import org.eclipse.m2m.qvt.oml.common.MdaException;
import org.eclipse.m2m.qvt.oml.common.io.eclipse.EclipseFile;
import org.eclipse.m2m.qvt.oml.common.launch.BaseProcess;
import org.eclipse.m2m.qvt.oml.common.launch.SafeRunner;
import org.eclipse.m2m.qvt.oml.common.launch.TargetUriData;
import org.eclipse.m2m.qvt.oml.common.launch.BaseProcess.IRunnable;
import org.eclipse.m2m.qvt.oml.emf.util.EmfException;
import org.eclipse.m2m.qvt.oml.emf.util.EmfUtil;
import org.eclipse.m2m.qvt.oml.emf.util.WorkspaceUtils;
import org.eclipse.m2m.qvt.oml.library.Context;
import org.eclipse.m2m.qvt.oml.library.IConfiguration;
import org.eclipse.m2m.qvt.oml.library.IContext;
import org.eclipse.osgi.util.NLS;

public abstract class QvtLaunchConfigurationDelegateBase extends LaunchConfigurationDelegate {
    @Override
	protected IProject[] getProjectsForProblemSearch(ILaunchConfiguration configuration, String mode) throws CoreException {
        return new IProject[] { getModuleFile(configuration).getProject() };
    }
 
/*  
 * Commented out as unused code when separating UI specific code 
 * TODO - to revisited
    public boolean finalLaunchCheck_(ILaunchConfiguration configuration, String mode, IProgressMonitor monitor) throws CoreException {
        IProject[] projects = getProjectsForProblemSearch(configuration, mode);
        if (projects == null) {
            return true; //continue launch
        }
        
        monitor.subTask(Messages.LaunchConfigurationDelegate_SearchingForErrors);
        List<IProject> errors = new ArrayList<IProject>();
        for (int i = 0; i < projects.length; i++) {
            monitor.subTask(NLS.bind(Messages.LaunchConfigurationDelegate_SearchingForErrorsIn, projects[i].getName());
            if (existsProblems(projects[i])) {
                errors.add(projects[i]);
            }   
        }   
        
        if (errors.isEmpty()) {
            return true;
        }
        else {
            displayHasErrorsDialog();
            return false;
        }
    }
        
    private void displayHasErrorsDialog() {
        QvtLaunchUIUtil.runInUiThread(new Runnable() {
            public void run() {
                Shell shell = QvtLaunchUIUtil.getShell();
                String title = Messages.QvtLaunchConfigurationDelegate_ErrorsInProject;
                String message = Messages.QvtLaunchConfigurationDelegate_ErrorsInQVTModule;
                MessageDialog.openError(shell, title, message);
            }
        });
    }
*/
    protected static IFile getModuleFile(ILaunchConfiguration configuration) throws CoreException {
        String moduleFileName = configuration.getAttribute(IQvtLaunchConstants.MODULE, ""); //$NON-NLS-1$
        IFile moduleFile = WorkspaceUtils.getWorkspaceFile(moduleFileName);
        if(moduleFile == null) {
        	IStatus errorStatus = MiscUtil.makeErrorStatus( 
        			NLS.bind(Messages.QvtLaunchConfigurationDelegate_transformationFileNotFound, moduleFileName));
        	throw new CoreException(errorStatus);
        }
        
        return moduleFile;
    }

    public static IStatus validate(QvtTransformation transformation, ILaunchConfiguration configuration) throws CoreException {
        String sourceModelUri = configuration.getAttribute(IQvtLaunchConstants.SOURCE_MODEL, ""); //$NON-NLS-1$
        TargetUriData targetData = QvtLaunchUtil.getTargetUriData(configuration);
        String traceFile = configuration.getAttribute(IQvtLaunchConstants.TRACE_FILE, ""); //$NON-NLS-1$
        boolean useTraceFile = configuration.getAttribute(IQvtLaunchConstants.USE_TRACE_FILE, false); 
        
        IStatus status = QvtValidator.validateTransformation(transformation, sourceModelUri, targetData, traceFile, useTraceFile);
        return status;
    }
    
    public static BaseProcess.IRunnable getSafeRunnable(QvtTransformation transformation, IRunnable r) throws CoreException {
        try {
            return SafeRunner.getSafeRunnable(new EClass[] {transformation.getIn(), transformation.getOut()}, r);
        } 
        catch (MdaException e) {
            throw new CoreException(MiscUtil.makeErrorStatus(e));
        }
    }
    
    public static URI doLaunch(QvtTransformation transformation, ILaunchConfiguration configuration, PrintWriter printWriter) throws Exception {
    	List<EObject> inObjects;
    	List<TargetUriData> targetData;
    	
    	if (configuration.getAttributes().containsKey(IQvtLaunchConstants.SOURCE_MODEL)) {
    		// TODO old type configuration, should not be used at all
	        URI inUri = getUri(configuration, IQvtLaunchConstants.SOURCE_MODEL);
	        EObject inObj = transformation.loadInput(inUri);
	        inObjects = Collections.singletonList(inObj);
	        
	        targetData = Collections.singletonList(QvtLaunchUtil.getTargetUriData(configuration));
    	}
    	else {
    		targetData = new ArrayList<TargetUriData>();
    		inObjects = new ArrayList<EObject>();
    		List<TargetUriData> targetUris = QvtLaunchUtil.getTargetUris(configuration);
    		
    		int i = 0;
    		for (TransformationParameter transfParam : transformation.getParameters()) {
    			if (i >= targetUris.size()) {
    	            throw new MdaException("Invalid launch configuration"); //$NON-NLS-1$
    			}
    			if (transfParam.getDirectionKind() == DirectionKind.IN || transfParam.getDirectionKind() == DirectionKind.INOUT) {
    		        URI inUri = toUri(targetUris.get(i).getUriString());
    		        EObject inObj = transformation.loadInput(inUri);
    		        inObjects.add(inObj);
    			}
    			if (transfParam.getDirectionKind() == DirectionKind.OUT || transfParam.getDirectionKind() == DirectionKind.INOUT) {
    				targetData.add(targetUris.get(i));
    			}
    			i++;
    		}
    	}

        IConfiguration qvtConfiguration = QvtLaunchUtil.getConfiguration(configuration);
        boolean saveTrace = configuration.getAttribute(IQvtLaunchConstants.USE_TRACE_FILE, false);
        String traceFileName = saveTrace ? configuration.getAttribute(IQvtLaunchConstants.TRACE_FILE, "") : null; //$NON-NLS-1$
        
        return doLaunch(transformation, inObjects, targetData, qvtConfiguration, traceFileName, printWriter);
    }
    
    public static URI doLaunch(final QvtTransformation transformation, final EObject inObj, TargetUriData targetData, IConfiguration configuration, final String traceFileName) throws Exception {
    	return doLaunch(transformation, Collections.singletonList(inObj), Collections.singletonList(targetData), configuration, traceFileName, null);
    }
    
    public static URI doLaunch(final QvtTransformation transformation, final List<EObject> inObjs, List<TargetUriData> targetData, IConfiguration configuration, final String traceFileName, PrintWriter printWriter) throws Exception {

    	IContext context = new Context(configuration);
        context.put(QvtOperationalStdLibrary.OUT_PRINT_WRITER, printWriter);
        
        context.launch();
    	
        TransformationRunner.In in = new TransformationRunner.In(inObjs.toArray(new EObject[inObjs.size()]), context);
        TransformationRunner.Out out = transformation.run(in);
        
        URI result = saveTransformationResult(inObjs.get(0), out.getExtents(), targetData.get(0));
        
        if(traceFileName != null && out.getTrace() != null) {
            IFile traceFile = WorkspaceUtils.getWorkspaceFile(traceFileName);
            if(traceFile != null) {
                TraceSerializer.saveTraceModel(out.getTrace(), new EclipseFile(traceFile));
            }
        }
        
        context.release();
        
        return result;
    }
    
    /**
     * @return URI of the element where the transformation result has been saved
     */
    @SuppressWarnings("unchecked")
	public static URI saveTransformationResult(EObject in, List<Resource> outExtents, TargetUriData targetData) throws MdaException {
    	URI outUri = toUri(targetData.getUriString());
    	URI result = outUri;
        switch(targetData.getTargetType()) {
        	case NEW_MODEL: {
        		if (outExtents.isEmpty()) {
        			throw new MdaException("No object to save"); //$NON-NLS-1$
        		}
        		try {
        			EmfUtil.saveModel(outExtents.get(0), outUri, EmfUtil.DEFAULT_SAVE_OPTIONS);
        		}
        		catch(EmfException e) {
        			throw new MdaException(e);
        		}
        		break;
        	}
        	
        	case EXISTING_CONTAINER: {
	        	EObject cont = EmfUtil.loadModel(outUri);
	            if(cont == null) {
	                throw new RuntimeException("No object at " + outUri); //$NON-NLS-1$
	            }
	            
		        EStructuralFeature feature = cont.eClass().getEStructuralFeature(targetData.getFeature());
		        if(feature instanceof EReference == false) {
	                throw new RuntimeException("Reference " + targetData.getFeature() + " not found in " + cont); //$NON-NLS-1$ //$NON-NLS-2$
		        }

        		if (outExtents.isEmpty()) {
        			throw new MdaException("No object to save"); //$NON-NLS-1$
        		}
		        
        		for (EObject out : outExtents.get(0).getContents()) {
			        EReference ref = (EReference)feature;
			        if(!ref.isMany()) {
			        	cont.eSet(ref, out);
			        }
			        else {
			        	EList<EObject> value = (EList<EObject>)cont.eGet(feature);
			        	if(targetData.isClearContents()) {
			        		value.clear();
			        	}
			        	
			        	value.add(out);
			        }
        		}
        		
		        try {
		        	saveResource(cont);
                    //IMetamodelHandler handler = MetamodelHandlerManager.getInstance().getHandler(out.eClass());
                    //result = handler.getSaver().getUri(out);
		        }
		        catch(IOException e) {
		        	throw new MdaException(e.getMessage(), e);
		        }
		        
		        break;
        	}
        	
        	case INPLACE: {
        		if (outExtents.isEmpty() || outExtents.get(0).getContents().isEmpty()) {
        			throw new MdaException("No object to save"); //$NON-NLS-1$
        		}
        		EObject out = outExtents.get(0).getContents().get(0);
        		if(in != out) {
        			throw new MdaException(NLS.bind(Messages.QvtLaunchConfigurationDelegateBase_InplaceFailure, in, out));
        		}
        		
		        try {
		        	saveResource(out);
		        }
		        catch(IOException e) {
		        	throw new MdaException(e.getMessage(), e);
		        }
        		
        		break;
        	}
        }
        
        org.eclipse.m2m.qvt.oml.emf.util.URIUtils.refresh(outUri);
        return result;
    }
    
    private static void saveResource(EObject obj) throws IOException {
    	Resource resource = obj.eResource();
    	if(resource == null) {
    		Logger.getLogger().warning("Object has no resource: " + obj); //$NON-NLS-1$
    		return;
    	}

		resource.save(EmfUtil.DEFAULT_SAVE_OPTIONS);
	}

	private static URI getUri(ILaunchConfiguration configuration, String propName) throws Exception {
        String uriString = configuration.getAttribute(propName, "");  //$NON-NLS-1$
        return toUri(uriString);
    }
    
    private static URI toUri(String uriString) throws MdaException {
        URI uri = URI.createURI(uriString);  
        if(uri == null) {
            throw new MdaException(NLS.bind(Messages.QvtLauncherTab_InvalidUri, uriString));
        }
        
        return uri;
    }
}

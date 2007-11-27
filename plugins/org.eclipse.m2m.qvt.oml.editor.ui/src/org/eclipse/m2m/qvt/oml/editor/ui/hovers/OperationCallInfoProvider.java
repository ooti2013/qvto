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
package org.eclipse.m2m.qvt.oml.editor.ui.hovers;

import org.eclipse.emf.ecore.EOperation;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.m2m.qvt.oml.editor.ui.CSTHelper;
import org.eclipse.m2m.qvt.oml.editor.ui.hyperlinks.OperationHyperlinkDetector;
import org.eclipse.ocl.ecore.EcoreEnvironment;
import org.eclipse.ocl.cst.SimpleNameCS;

public class OperationCallInfoProvider implements IElementInfoProvider {

	public String getElementInfo(final Object element, ITextViewer textViewer) {
		if (element instanceof SimpleNameCS) {
			SimpleNameCS nameCS = (SimpleNameCS)element;
			EOperation operation = OperationHyperlinkDetector.resolveOperationDecl(nameCS);
			
			if(operation != null) {
				EcoreEnvironment env = CSTHelper.getEnvironment(nameCS);
				if(env != null) {
					return SignatureUtil.getOperationSignature(env, operation);
				}
			}
		}
		return null;
	}
}

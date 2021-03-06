/**
 * <copyright>
 *
 * Copyright (c) 2013 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.qvto.examples.pivot.imperativeocl.utilities;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.utilities.ASSaver;
import org.eclipse.qvto.examples.pivot.imperativeocl.util.AbstractImperativeOCLASSaverLocateVisitor;

public class ImperativeOCLASSaverLocateVisitor extends AbstractImperativeOCLASSaverLocateVisitor
{
	protected ImperativeOCLASSaverLocateVisitor(@NonNull ASSaver context) {
		super(context);
	}
}

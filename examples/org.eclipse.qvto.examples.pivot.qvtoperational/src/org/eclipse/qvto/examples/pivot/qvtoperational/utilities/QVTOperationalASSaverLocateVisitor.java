package org.eclipse.qvto.examples.pivot.qvtoperational.utilities;
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


import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.pivot.utilities.ASSaver;
import org.eclipse.qvto.examples.pivot.qvtoperational.util.AbstractQVTOperationalASSaverLocateVisitor;

public class QVTOperationalASSaverLocateVisitor extends AbstractQVTOperationalASSaverLocateVisitor
{
	protected QVTOperationalASSaverLocateVisitor(@NonNull ASSaver context) {
		super(context);
	}
}

/*******************************************************************************
 * Copyright (c) 2011, 2013 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.domain.elements;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.domain.ids.ParametersId;

public interface DomainLambdaType extends DomainType
{
	DomainType getContextType();
	@NonNull ParametersId getParametersId();
	@NonNull List<? extends DomainType> getParameterTypes();
	DomainType getResultType();
}

/*******************************************************************************
 * Copyright (c) 2012, 2013 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.domain.ids;

import org.eclipse.ocl.examples.domain.utilities.IndexableIterable;

/**
 * BindingsId provides a hashable list of elementIds suitable for use when indexing specializations.
 */
public interface BindingsId extends IndexableIterable<ElementId>
{
}

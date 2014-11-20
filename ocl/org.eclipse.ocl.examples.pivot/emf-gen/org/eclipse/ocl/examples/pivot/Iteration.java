/*******************************************************************************
 * Copyright (c) 2010, 2013 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Iteration</b></em>'.
 * @extends org.eclipse.ocl.examples.domain.elements.DomainIteration
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.ocl.examples.pivot.Iteration#getOwnedAccumulator <em>Owned Accumulator</em>}</li>
 *   <li>{@link org.eclipse.ocl.examples.pivot.Iteration#getOwnedIterator <em>Owned Iterator</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.ocl.examples.pivot.PivotPackage#getIteration()
 * @generated
 */
public interface Iteration extends Operation, org.eclipse.ocl.examples.domain.elements.DomainIteration
{
	/**
	 * Returns the value of the '<em><b>Owned Iterator</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.pivot.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Iterator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Iterator</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.pivot.PivotPackage#getIteration_OwnedIterator()
	 * @generated
	 */
	@NonNull List<Parameter> getOwnedIterator();

	/**
	 * Returns the value of the '<em><b>Owned Accumulator</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.ocl.examples.pivot.Parameter}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Owned Accumulator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Owned Accumulator</em>' containment reference list.
	 * @see org.eclipse.ocl.examples.pivot.PivotPackage#getIteration_OwnedAccumulator()
	 * @generated
	 */
	@NonNull List<Parameter> getOwnedAccumulator();

} // Iteration

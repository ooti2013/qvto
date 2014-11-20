/*******************************************************************************
 * Copyright (c) 2012, 2013 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.domain.ids.impl;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.ids.ElementId;
import org.eclipse.ocl.examples.domain.ids.IdHash;
import org.eclipse.ocl.examples.domain.ids.OperationId;
import org.eclipse.ocl.examples.domain.ids.ParametersId;
import org.eclipse.ocl.examples.domain.ids.TypeId;

public abstract class AbstractElementId implements ElementId
{
	protected static final class OperationIdsMap extends WeakHashMapOfListOfWeakReference4<Integer, Integer, String, ParametersId, GeneralizedOperationIdImpl>
	{
		protected final @NonNull TypeId parentId;
		
		public OperationIdsMap(@NonNull TypeId parentId) {
			this.parentId = parentId;
		}
		
		@Override
		protected @NonNull GeneralizedOperationIdImpl newId(@NonNull Integer hashCode, @NonNull Integer templateParameters, @NonNull String name, @NonNull ParametersId parametersId) {
//			System.out.println("new OperationId " + name + " " + DomainUtil.debugFullName(parametersId) + " with " + DomainUtil.debugFullName(templateParameters));		
			return new GeneralizedOperationIdImpl(hashCode, parentId, templateParameters, name, parametersId);
		}

		public @NonNull OperationId getId(int templateParameters, @NonNull String name, @NonNull ParametersId parametersId) {
			int hashCode = IdHash.createChildHash(parentId, name) + parametersId.hashCode();
			return getId(hashCode, templateParameters, name, parametersId);
		}
	}
	
	protected static final class PropertyIdsMap extends WeakHashMapOfWeakReference<String, PropertyIdImpl>
	{
		protected final @NonNull TypeId parentId;
		
		public PropertyIdsMap(@NonNull TypeId parentId) {
			this.parentId = parentId;
		}

		@Override
		protected @NonNull PropertyIdImpl newId(@NonNull String name) {
			return new PropertyIdImpl(parentId, name);
		}
	}

	@Override
	public final boolean equals(Object that) {
		return this == that;
	}

	public @Nullable String getLiteralName() {
		return null;
	}

	@Override
	public abstract int hashCode();
	
	@Override
	public String toString() {
		return getDisplayName();
	}
}
/*******************************************************************************
 * Copyright (c) 2010, 2013 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.utilities;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;

public abstract class AbstractConversion
{
	public static interface Predicate<T extends EObject>
	{
		boolean filter(@NonNull T element);
	}	

	protected static <T> T basicGet(@NonNull EObject eObject, @NonNull EAttribute eFeature, @NonNull Class<T> resultClass) {
		if (!eObject.eIsSet(eFeature)) {
			return null;
		}
		@SuppressWarnings("unchecked")
		T result = (T) eObject.eGet(eFeature);
		return result;
	}

	public static @Nullable EPackage getEPackage(@Nullable EObject eObject) {
		for (; eObject != null; eObject = eObject.eContainer()) {
			if (eObject instanceof EPackage) {
				return (EPackage)eObject;
			}
		}
		return null;
	}

	public static boolean isId(@NonNull String name) {
		int n = name.length();
		if (n == 0)
			return false;
		if (!Character.isJavaIdentifierStart(name.charAt(0)))
			return false;
		for (int i = 1; i < n; i++)
			if (!Character.isJavaIdentifierPart(name.charAt(i)))
				return false;
		return true;
	}
	
	protected final @NonNull MetaModelManager metaModelManager;

	protected AbstractConversion(@NonNull MetaModelManager metaModelManager) {
		this.metaModelManager = metaModelManager;
	}
	
	public @NonNull MetaModelManager getMetaModelManager() {
		return metaModelManager;
	}

	public <T extends EObject> void refreshList(@Nullable List<? super T> oldElements, @Nullable List<? extends T> newElements) {
		PivotUtil.refreshList(oldElements, newElements);
	}

	protected <T extends EObject> void refreshSet(@Nullable List<? super T> oldElements, @Nullable Collection<? extends T> newElements) {
		PivotUtil.refreshSet(oldElements, newElements);
	}
}
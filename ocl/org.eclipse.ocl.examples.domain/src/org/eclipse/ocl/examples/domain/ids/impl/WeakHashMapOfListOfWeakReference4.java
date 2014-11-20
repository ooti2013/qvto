/*******************************************************************************
 * Copyright (c) 2012 E.D.Willink and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.domain.ids.impl;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

import org.eclipse.jdt.annotation.NonNull;

public abstract class WeakHashMapOfListOfWeakReference4<K1, K2, K3, K4, V extends WeakHashMapOfListOfWeakReference4.MatchableId<K2, K3, K4>> extends WeakHashMap<K1,List<WeakReference<V>>> 
{
	public static interface MatchableId<K2, K3, K4>
	{
		boolean matches(@NonNull K2 key2, @NonNull K3 key3, @NonNull K4 key4);
	}
	
	public synchronized @NonNull V getId(@NonNull K1 key1, @NonNull K2 key2, @NonNull K3 key3, @NonNull K4 key4) {
		List<WeakReference<V>> ids = get(key1);
		if (ids == null) {
			ids = new ArrayList<WeakReference<V>>();
			put(key1, ids);
		}
		for (WeakReference<V> ref : ids) {
			V oldId = ref.get();
			if (oldId != null) {
				if (oldId.matches(key2, key3, key4)) {
					return oldId;
				}
			}
		}
		V newId = newId(key1, key2, key3, key4);
		ids.add(new WeakReference<V>(newId));
		return newId;
	}

	protected abstract @NonNull V newId(@NonNull K1 key1, @NonNull K2 key2, @NonNull K3 key3, @NonNull K4 key4);
}
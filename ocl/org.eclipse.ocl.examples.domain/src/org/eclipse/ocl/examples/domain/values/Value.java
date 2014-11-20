/*******************************************************************************
 * Copyright (c) 2010, 2012 E.D.Willink and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   E.D.Willink - Initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.domain.values;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.ocl.examples.domain.elements.DomainElement;
import org.eclipse.ocl.examples.domain.ids.TypeId;
import org.eclipse.ocl.examples.domain.types.IdResolver;

/**
 * A value support wrapping/boxing a value whose Java implementation does not comply with OCL semantics, primarily
 * that equal values return true from Object.equals(Object) but also for variant metamodel elements.
 * <p>
 * A boxed value is not needed for Boolean and String that are well-behaved.
 * <p>
 * A boxed value is needed for Integer and Double, since in OCL 4 is equal to 4.0 and since multiple implementation
 * classes exist to support growth between unlimited numeric ranges.
 * <p>
 * A boxed value is needed for EnumerationLiterals since distinct Pivot, Ecore, UML variants may exist.
 * <p>
 * A boxed value is needed for types since distinct Pivot, Ecore, UML variants may exist.
 * <p>
 * A boxed value is useful/needed for collections to provide OCL polymorphism.
 * <p>
 * A boxed value is not needed for the large number of ordinary EObjects not in the above list. 
 * <p>
 * asXXX returns a non-null XXX if self is convertible to an XXX and is not NullValue/InvalidValue
 * throws an InvalidValueException for a NullValue/InvalidValue. A Value object may be converted
 * if the conversion to XXX is exact and type conformant.
 * <p>
 * isXXX returns an XXX-related value if self is an XXX and is not a NullValue/InvalidValue, returns null otherwise.
 */
public interface Value
{	
	/**
	 * @generated NOT
	 */
	public static final String INVALID_NAME = "invalid";

	/**
	 * @generated NOT
	 */
	@NonNull BagValue asBagValue();

	/**
	 * @generated NOT
	 */
	@NonNull CollectionValue asCollectionValue();

	/**
	 * @generated NOT
	 */
	@NonNull Double asDouble();
	
	/**
	 * Return the Ecore representation of this value.
	 * <p>
	 * An thrown exception for an invalid OCL value.
	 * <p>
	 * Java-null for a null OCL value
	 * <p>
	 * Objects for other things
	 * 
	 * @generated NOT
	 * @deprecated Use IdResolver.unboxedValueOf.
	 */
	@Deprecated  // Since 12-July-2013; because in general we need an IdResolver argument.
	Object asEcoreObject();
	Object asEcoreObject(@NonNull IdResolver idResolver);

	/**
	 * @generated NOT
	 */
	DomainElement asElement(); 

	/**
	 * @generated NOT
	 */
	@NonNull Integer asInteger();

	/**
	 * @generated NOT
	 */
	@NonNull IntegerValue asIntegerValue();

	/**
	 * @generated NOT
	 */
	@NonNull EObject asNavigableObject();

	/**
	 * @generated NOT
	 */
	@NonNull Object asObject();

	/**
	 * @generated NOT
	 */
	@NonNull ObjectValue asObjectValue();

	/**
	 * @generated NOT
	 */
	@NonNull OrderedCollectionValue asOrderedCollectionValue();

	/**
	 * @generated NOT
	 */
	@NonNull OrderedSetValue asOrderedSetValue();

	/**
	 * @generated NOT
	 */
	@NonNull RealValue asRealValue();

	/**
	 * @generated NOT
	 */
	@NonNull SequenceValue asSequenceValue();

	/**
	 * @generated NOT
	 */
	@NonNull SetValue asSetValue();

	/**
	 * @generated NOT
	 */
	@NonNull TupleValue asTupleValue();

	/**
	 * @generated NOT
	 */
	@NonNull UniqueCollectionValue asUniqueCollectionValue();

	/**
	 * @generated NOT
	 */
	@NonNull Value asUnlimitedNaturalValue();
	
	/**
	 * Return the type of this value determined from its content. In the case of collections
	 * this may differ from the constructed type. The actual type is used for validating
	 * oclAsType conversions.
	 * @throws InvalidValueException 
	 * @generated NOT
	 */
//	@NonNull DomainType getActualType(@NonNull DomainStandardLibrary standardLibrary);

	/**
	 * 
	 * Return the type of this value determined from its construction context. In the case of collections
	 * this may differ from the actual type.
	 * @generated NOT
	 */
//	@NonNull DomainType getType(@NonNull DomainStandardLibrary standardLibrary);
	@NonNull TypeId getTypeId();

	/**
	 * @generated NOT
	 */
	boolean isInvalid();

	/**
	 * @generated NOT
	 */
	boolean isUndefined();

	/**
	 * @generated NOT
	 */
	void toString(@NonNull StringBuilder s, int sizeLimit);
}

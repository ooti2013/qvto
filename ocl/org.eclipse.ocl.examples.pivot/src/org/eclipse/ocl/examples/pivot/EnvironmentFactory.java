/*******************************************************************************
 * Copyright (c) 2005, 2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   E.D.Willink - Refactoring to support extensibility and flexible error handling 
 *******************************************************************************/

package org.eclipse.ocl.examples.pivot;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.domain.elements.DomainStandardLibrary;
import org.eclipse.ocl.examples.domain.evaluation.DomainModelManager;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationEnvironment;
import org.eclipse.ocl.examples.pivot.evaluation.EvaluationVisitor;

/**
 * A factory for creating OCL parser {@link Environment}s.  Clients of the OCL
 * parser that wish to use OCL with their metamodels can provide the parser
 * a factory that creates the suitable environments.  The environment provides
 * mappings from the client's metamodel to the UML concepts required by the
 * parser (corresponding to the generic type parameters, below).  Many of these
 * mappings are optional (e.g., state machines, signals, and association
 * classes aren't supported by all metamodels).
 * <p>
 * This interface is <b>not</b> intended to be implemented to be implemented
 * "directly" by providers of metamodel bindings.
 * It is highly recommended to extend the {@link AbstractEnvironmentFactory}
 * class, instead.
 * </p>
 *
 * @author Christian W. Damus (cdamus)
 */
public interface EnvironmentFactory {
	
	/**
	 * Creates a root environment, in which package contexts and/or classifier
     * contexts will be created as nested environments.  All operation body
     * constraints, attribute initial/derived value constraints, and definitions
     * of additional attributes and operations should be maintained by the root
     * environment, so that they will be accessible from constraints parsed in
     * any nested environment.
	 * 
	 * @return a new root environment
	 */
	@NonNull Environment createEnvironment();
	
	/**
	 * Creates an environment suitable for parsing OCL expressions in the
	 * specified package context.  This context will become a classifier context
	 * when the {@linkplain Environment#setSelfVariable "self" variable}
	 * is defined.
	 * 
	 * @param pathname the qualified package name (the "::"-separated parts)
	 * @return the environment or null if lookup fails to locate a package
	 * 
	 * @see #createClassifierContext
	 * @see #createOperationContext
     * @see #createAttributeContext
	 */
//	Environment createPackageContext(Environment parent, List<String> pathname);
	
	/**
	 * Loads an environment from the specified <tt>resource</tt>.  If not
	 * already loaded, this method will load the resource.  This resource will
	 * subsequently be used to persist new OCL constraints, so supplying a new,
	 * empty resource will allow the client to determine where the environment
	 * is persisted.
	 * 
	 * @param resource a resource containing the persisted environment
	 */
	@NonNull Environment loadEnvironment(@NonNull Resource resource);
	
	/**
	 * Creates an environment suitable for parsing OCL expressions in the
	 * specified <code>context</code>, which is some classifier
	 * in the client's model.
	 * 
	 * @param context the context classifier
	 * @return the environment
	 * 
	 * @see #createOperationContext(Environment, Operation)
     * @see #createPropertyContext(Environment, Property)
     * @see #createInstanceContext(Environment, Object)
	 */
	@NonNull Environment createClassifierContext(@NonNull Environment parent, @NonNull Type context);
	
    /**
     * Creates an environment suitable for parsing OCL expressions on the
     * specified <code>context</code> object, which is an instance of some
     * classifier in the client's model.
     * <p>
     * The context may be an instance of a model class or a data type value
     * on which an OCL expression would be evaluated.  Note that the actual OCL
     * context classifier (as an OCL type or classifier) will be
     * inferred from the context instance according to the metamodel that the
     * environment factory supports, if possible.  If not possible, then the
     * {@link DomainStandardLibrary#getOclAnyType() OclAny} type is assumed.
     * </p>
     * 
     * @param context the context object or value
     * @return the environment
     * 
     * @see #createClassifierContext(Environment, Type)
     * @see DomainStandardLibrary#getOclAnyType()
     */
	@NonNull Environment createInstanceContext(@NonNull Environment parent, @NonNull Object context);
    
	/**
	 * Creates an environment suitable for parsing OCL expressions on the
	 * specified <code>operation</code>, which is some operation
	 * in the client's metamodel.  Note that operation contexts can be defined
	 * in the context of any classifier to which that operation is applicable.
	 * 
	 * @param parent the parent environment, defining the classifier context
	 * @param operation an operation in the client's metamodel
	 * @return the environment
	 * 
	 * @see #createClassifierContext(Environment, Type)
	 */
	@NonNull Environment createOperationContext(@NonNull Environment parent, @NonNull Operation operation);
	
	/**
	 * Creates an environment suitable for parsing OCL expressions on the
	 * specified <code>property</code>, which is some attribute
	 * in the client's metamodel.  Note that attribute contexts can be defined
	 * in the context of any classifier in which that attribute is available.
	 * 
     * @param parent the parent environment, defining the classifier context
	 * @param property an attribute in the client's metamodel
	 * @return the environment
	 * 
	 * @see #createClassifierContext(Environment, Type)
	 */
	@NonNull Environment createPropertyContext(@NonNull Environment parent, @NonNull Property property);
	
	/**
	 * Creates a child environment of a specified <code>parent</code>, for
	 * definition of nested scopes.
	 * 
	 * @param parent the parent environment
	 * @return the child environment
	 */
	@NonNull Environment createEnvironment(@NonNull Environment parent);
	
	/**
	 * Creates a new evaluation environment to track the values of variables in
	 * an OCL expression as it is evaluated.
	 * 
	 * @return a new evaluation environment
	 */
	@NonNull EvaluationEnvironment createEvaluationEnvironment();
	
	/**
	 * Creates a new evaluation environment as a nested environment of the
	 * specified <tt>parent</tt>.
	 * 
	 * @param parent a nesting evaluation environment
	 * @return a new nested evaluation environment
	 */
	@NonNull EvaluationEnvironment createEvaluationEnvironment(@NonNull EvaluationEnvironment parent);

    /**
     * Creates a new evaluation visitor, for the evaluation of an OCL expression on a context using an environment and a modelManager.
     * If environment is null, a root environment is created and used.
     * If context is null and the expression uses self subsequent evaluations will give invalid as the result.
     * If modelManager is null, the context object's ResoutceSet is analyzed to create one.
     */
	@NonNull EvaluationVisitor createEvaluationVisitor(@Nullable Environment environment, @Nullable Object context, @NonNull ExpressionInOCL expression, @Nullable DomainModelManager modelManager);
	
    /**
     * Creates a new evaluation visitor, for the evaluation of OCL expressions.
     * 
     * @param env the environment in which the expression was originally parsed
     *    (or some compatible environment)
     * @param evalEnv the evaluation environment that the visitor is to use
     *    for tracking variables, navigating properties, etc.
     * @param modelManager the map of <tt>Class</tt>es to their extends
     * @return the new evaluation visitor
     */
	@NonNull EvaluationVisitor createEvaluationVisitor(@NonNull Environment env, @NonNull EvaluationEnvironment evalEnv, @NonNull DomainModelManager modelManager);
}

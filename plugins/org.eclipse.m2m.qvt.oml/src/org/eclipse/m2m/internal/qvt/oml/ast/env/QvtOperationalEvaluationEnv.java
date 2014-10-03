/*******************************************************************************
 * Copyright (c) 2008, 2014 Borland Software Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *   
 * Contributors:
 *     Borland Software Corporation - initial API and implementation
 *     Christopher Gerking - bugs 392153, 425069
 *     Alex Paperno - bugs 400720, 415029
 *     Christine Gerpheide - bug 432969
 *******************************************************************************/
package org.eclipse.m2m.internal.qvt.oml.ast.env;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.m2m.internal.qvt.oml.NLS;
import org.eclipse.m2m.internal.qvt.oml.QvtPlugin;
import org.eclipse.m2m.internal.qvt.oml.ast.parser.IntermediateClassFactory;
import org.eclipse.m2m.internal.qvt.oml.ast.parser.QvtOperationalParserUtil;
import org.eclipse.m2m.internal.qvt.oml.ast.parser.QvtOperationalTypesUtil;
import org.eclipse.m2m.internal.qvt.oml.ast.parser.QvtOperationalUtil;
import org.eclipse.m2m.internal.qvt.oml.evaluator.EvaluationMessages;
import org.eclipse.m2m.internal.qvt.oml.evaluator.EvaluationUtil;
import org.eclipse.m2m.internal.qvt.oml.evaluator.IntermediatePropertyModelAdapter;
import org.eclipse.m2m.internal.qvt.oml.evaluator.ModelInstance;
import org.eclipse.m2m.internal.qvt.oml.evaluator.ModuleInstance;
import org.eclipse.m2m.internal.qvt.oml.evaluator.NumberConversions;
import org.eclipse.m2m.internal.qvt.oml.evaluator.QVTEvaluationOptions;
import org.eclipse.m2m.internal.qvt.oml.evaluator.QVTStackTraceElement;
import org.eclipse.m2m.internal.qvt.oml.evaluator.QvtGenericVisitorDecorator;
import org.eclipse.m2m.internal.qvt.oml.evaluator.QvtRuntimeException;
import org.eclipse.m2m.internal.qvt.oml.evaluator.QvtStackTraceBuilder;
import org.eclipse.m2m.internal.qvt.oml.evaluator.ThisInstanceResolver;
import org.eclipse.m2m.internal.qvt.oml.evaluator.TransformationInstance;
import org.eclipse.m2m.internal.qvt.oml.evaluator.TransformationInstance.InternalTransformation;
import org.eclipse.m2m.internal.qvt.oml.expressions.ContextualProperty;
import org.eclipse.m2m.internal.qvt.oml.expressions.DirectionKind;
import org.eclipse.m2m.internal.qvt.oml.expressions.ImperativeOperation;
import org.eclipse.m2m.internal.qvt.oml.expressions.ModelParameter;
import org.eclipse.m2m.internal.qvt.oml.expressions.Module;
import org.eclipse.m2m.internal.qvt.oml.expressions.OperationalTransformation;
import org.eclipse.m2m.internal.qvt.oml.library.EObjectEStructuralFeaturePair;
import org.eclipse.m2m.internal.qvt.oml.stdlib.CallHandler;
import org.eclipse.m2m.internal.qvt.oml.stdlib.QVTUMLReflection;
import org.eclipse.m2m.internal.qvt.oml.trace.Trace;
import org.eclipse.m2m.internal.qvt.oml.trace.TraceFactory;
import org.eclipse.m2m.qvt.oml.util.Dictionary;
import org.eclipse.m2m.qvt.oml.util.IContext;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.ecore.EcoreEvaluationEnvironment;
import org.eclipse.ocl.ecore.EcorePackage;
import org.eclipse.ocl.types.AnyType;
import org.eclipse.ocl.types.CollectionType;
import org.eclipse.ocl.util.CollectionUtil;
import org.eclipse.ocl.util.Tuple;


public class QvtOperationalEvaluationEnv extends EcoreEvaluationEnvironment {
	
	protected QvtOperationalEvaluationEnv(IContext context, QvtOperationalEvaluationEnv parent) {
		super(parent == null ? new EcoreEvaluationEnvironment((EcoreEnvironmentFactory)null) : parent);
		if(parent == null) {
			myRootEnv = this;
			myInternal = new RootInternal(context);
			myStackDepth = 1;
		} else {
			myRootEnv = parent.myRootEnv;
			myInternal = new Internal();
			myStackDepth = parent.myStackDepth + 1;
		}
		
	    myBindings = new HashMap<String, Object>();
		myOperationArgs = new ArrayList<Object>();
		myExtents = new ArrayList<ModelParameterExtent>();
	}
	
	public QvtOperationalEvaluationEnv getRoot() {
		return myRootEnv;
	}
	
	public int getDepth() {
		return myStackDepth;
	}
	
    public ModuleInstance getThisOfType(Module module) {
    	ThisInstanceResolver thisResolver = internalEnv().getThisResolver();
    	assert thisResolver != null;    	
		return thisResolver.getThisInstanceOf(module);
    }	
	
	@Override
	public <T> T getAdapter(Class<T> adapterType) {
		if(InternalEvaluationEnv.class == adapterType) {
			return adapterType.cast(internalEnv());
		}
		
		return super.getAdapter(adapterType);
	}

	private Internal internalEnv() {
		return myInternal;
	}

	@Override
	public Map<EClass, Set<EObject>> createExtentMap(Object object) {
		return new QVTExtentMap(this);
	}
	
	public List<Object> getOperationArgs() {
		return myOperationArgs;
	}

	public void setOperationSelf(Object source) {
		myOperationSelf = source;
	}
	
	public Object getOperationSelf() {
		return myOperationSelf;
	}
	
	public IContext getContext() {
		return internalEnv().getContext();
	}
	
	public void addModelExtent(ModelParameterExtent extent) {
		internalEnv().addModelExtent(extent);
	}
	
	public void cleanup() {
		internalEnv().cleanup();
	}
		
	@Override
	public boolean overrides(EOperation operation, int opcode) {
		if (CallHandler.Access.hasHandler(operation)) {
			return true;
		} 
		// Remark: Prone to cause SOE if running in a deep stack as 
		// the super implementation calls recursively upto the root
		// evaluation environment
		return false;//super.overrides(operation, opcode);
	}

	@Override
	public Object callOperation(EOperation operation, int opcode, Object source, Object[] args) throws IllegalArgumentException {
		CallHandler callHandler = CallHandler.Access.getHandler(operation);
		if(callHandler != null) {
			if(source == null || source == getInvalidResult()) {
				return getInvalidResult();
			}
			
			Module targetModule = QvtOperationalParserUtil.getOwningModule(operation);
			ModuleInstance targetModuleInstance = getThisOfType(targetModule);
			assert targetModuleInstance != null;
			return callHandler.invoke(targetModuleInstance, source, args, this);
		}
			
		return super.callOperation(operation, opcode, source, args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object navigateProperty(EStructuralFeature property, List<?> qualifiers, Object target) throws IllegalArgumentException {
		if(target instanceof ModuleInstance) {
			ModuleInstance moduleTarget = (ModuleInstance) target;
			EClassifier owningClassifier = QvtOperationalStdLibrary.INSTANCE.getEnvironment().getUMLReflection().getOwningClassifier(property);			
			if (owningClassifier instanceof Module) {
				target = moduleTarget.getThisInstanceOf((Module) owningClassifier);
			}
			else {
				target = moduleTarget.getThisInstanceOf(moduleTarget.getModule());
			}
		}

		EStructuralFeature resolvedProperty = property;		

		if (property instanceof ContextualProperty) {
			IntermediatePropertyModelAdapter.ShadowEntry shadow = IntermediatePropertyModelAdapter.getPropertyHolder(
														property.getEContainingClass(), (ContextualProperty)property, target);
			target = shadow.getPropertyRuntimeOwner(target, this);
			resolvedProperty = shadow.getProperty();
		}
		
		// FIXME - workaround for a issue of multiple typle type instances, possibly coming from 
		// imported modules. The super impl. looks for the property by feature instance, do it
		// by name here to avoid lookup failure, IllegalArgExc...
		if(target instanceof Tuple<?, ?>) {
			if (target instanceof EObject) {
	            EObject etarget = (EObject) target;
	            resolvedProperty = etarget.eClass().getEStructuralFeature(property.getName());
	            if(resolvedProperty == null) { 
	            	return null;
	            }
			}
			else {
				resolvedProperty = null;
				for (EStructuralFeature feature : ((Tuple<EOperation, EStructuralFeature>) target).getTupleType().oclProperties()) {
					if (property.getName().equals(feature.getName())) {
						resolvedProperty = feature;
						break;
					}
				}
	            if(resolvedProperty == null) { 
	            	return null;
	            }
			}
		}
		else if(property.getEType() instanceof CollectionType<?, ?> && target instanceof EObject) {
			// module property of direct OCL collection type => override the super impl which coerce the result value 
			// and takes only the first element and returns from navigate call
            EObject eTarget = (EObject) target;
            if (eTarget.eClass().getEAllStructuralFeatures().contains(resolvedProperty)) {
                return eTarget.eGet(resolvedProperty, true);
            }
		}
		
		try {
			return super.navigateProperty(resolvedProperty, qualifiers, target);
		}
		catch (IllegalArgumentException e) {
            internalEnv().throwQVTException(
                	new QvtRuntimeException("Unknown property '" + property.getName() + "'", e)); //$NON-NLS-1$ //$NON-NLS-2$
			return getInvalidResult();
		}
	}
	
	@Override
	public QvtOperationalEvaluationEnv getParent() {
		return super.getParent() instanceof QvtOperationalEvaluationEnv ? 
				(QvtOperationalEvaluationEnv) super.getParent() : null;
	}

    /**
     * Returns the value associated with the supplied name
     * 
     * @param name
     *            the name whose value is to be returned
     * @return the value associated with the name
     */
	@Override
    public Object getValueOf(String name) {
        Object result = myBindings.get(name);        
        if(result instanceof TypedBinding) {
        	return ((TypedBinding)result).value;
        }

        return result;
    }

	/**
	 * Gets the type bound to the variable of the given name.
	 * 
	 * @param the
	 *            name of the variable
	 * @return the type the referenced variable previously bound by
	 *         {@link #add(String, Object, EClassifier)} or <code>null</code>
	 *         if none is available.
	 * @see #add(String, Object, EClassifier)
	 */
    public EClassifier getTypeOf(String name) {
        Object result = myBindings.get(name);
        
        if(result instanceof TypedBinding) {
        	return ((TypedBinding)result).type;
        }
        
        return null;
    }	
	
    /**
	 * Test whether the give object is OclInvalid retrieved from this
	 * environment.
	 * 
	 * @param value
	 *            the object to test
	 * @return <code>true</code> if the passed object is
	 *         <code>OclInvalid</code> from this environment;
	 *         <code>false</code> otherwise
	 */
    // FIXME - rename to is isInvalid
    public boolean isOclInvalid(Object value) {
    	return getInvalidResult() == value;
    }

    public void copyVariableValueFrom(QvtOperationalEvaluationEnv fromEnv, String varName, String targetVarName) {
    	Object sourceValue = fromEnv.getValueOf(varName);
    	this.replace(targetVarName, sourceValue);
    }
    
    /**
     * Replaces the current value of the supplied name with the supplied value.
     * 
     * @param name
     *            the name
     * @param value
     *            the new value
     */
	@Override
    public void replace(String name, Object value) {
    	myBindings.put(name, value);
    }

    /**
	 * Replaces the current value of the supplied name with the supplied value
	 * and type.
	 * 
	 * @param name
	 *            the name
	 * @param declaredType
	 *            the type of the value known at declaration time
	 * @param value
	 *            the new value
	 */
    public void replace(String name, Object value, EClassifier declaredType) {
    	if(declaredType != null) {
    		replace(name, new TypedBinding(value, declaredType));
    	} else {
			replace(name, value);
		}
    }

    /**
     * Adds the supplied name and value binding to the environment
     * 
     * @param name
     *            the name to add
     * @param value
     *            the associated binding
     */
	@Override
    public void add(String name, Object value) {
		if(QvtOperationalEnv.THIS.equals(name)) {
			Object thisValue = value;
			if(thisValue != null && thisValue.getClass() == TypedBinding.class) {
				thisValue = ((TypedBinding)thisValue).value;
			}
			if(thisValue instanceof ModuleInstance) {
				internalEnv().setThisResolver((ModuleInstance) thisValue);
			}
		}

        if (myBindings.containsKey(name)) {
        	String message = NLS.bind("The name: ({0})  already has a binding: ({1})", name, myBindings.get(name));  //$NON-NLS-1$
            throw new IllegalArgumentException(message);
        }
        myBindings.put(name, value);
    }
	
    /**
     * Adds the supplied name and typed value binding to the environment
     * 
     * @param name
     *            the name to add
	 * @param declaredType
	 *            the type of the value known at declaration time            
     * @param value
     *            the associated binding
     */	
    public void add(String name, Object value, EClassifier declaredType) {
    	if(declaredType != null) {
    		add(name, new TypedBinding(value, declaredType));
    	} else {
    		add(name, value);
    	}
    }

    /**
     * Removes the supplied name and binding from the environment (if it exists)
     * and returns it.
     * 
     * @param name
     *            the name to remove
     * @return the value associated with the removed name
     */
	@Override
    public Object remove(String name) {
        Object result = myBindings.remove(name);
        if(result instanceof TypedBinding) {
        	return ((TypedBinding)result).value;
        }

        return result;
    }

    /**
     * Clears the environment of variables.
     */
	@Override
    public void clear() {
    	myBindings.clear();
    }

    /**
     * Returns a string representation of the bindings
     */
	@Override
    public String toString() {
        return myBindings.toString();
    }
	
	public Set<String> getNames() {
		return myBindings.keySet();
	}
	
	@Override
	public boolean isKindOf(Object object, EClassifier classifier) {
		if (classifier instanceof AnyType<?>) {
			// [Spec 11.2.1] All types in the UML model and the primitive types in the OCL standard library
			// comply with the type OclAny.
			// OclAny behaves as a supertype for all the types except for the OCL pre-defined collection types.
			// OclAny is itself an instance of the metatype AnyType.
			return false == object instanceof Collection<?>;
		} 
		else if(classifier == QvtOperationalStdLibrary.INSTANCE.getElementType()) {
			if(object instanceof EObject) {
				return QVTUMLReflection.isUserModelElement(((EObject)object).eClass());
			}
			
		}
		else if(object instanceof DynamicEObjectImpl) {
			for (EClass objType : ((EObject) object).eClass().getEAllSuperTypes()) {
				if (objType.getEPackage() == classifier.getEPackage() 
						&& objType.getClassifierID() == classifier.getClassifierID())
					return true;
			}
		}
		
		if (classifier instanceof CollectionType<?, ?> && object instanceof java.util.Collection<?>) {
			if (!classifier.isInstance(object)) {
				if (classifier.eClass().getEPackage() != EcorePackage.eINSTANCE
						|| classifier.eClass().getClassifierID() != EcorePackage.eINSTANCE.getCollectionType().getClassifierID()) {
					return false;
				}
			}

			if (((java.util.Collection<?>) object).isEmpty()) {
				return true;
			}
			return isKindOf(((java.util.Collection<?>) object).iterator().next(),
					((org.eclipse.ocl.ecore.CollectionType) classifier).getElementType());
		}
		
		return super.isKindOf(object, classifier);
	}
	
	@Override
	public EClassifier getType(Object object) {
		if (object == null) {
			return QvtOperationalStdLibrary.INSTANCE.getOCLStdLib().getOclVoid();
		}
		return super.getType(object);
	}
	
	@Override
	protected Object coerceValue(ETypedElement element, Object value, boolean copy) {
		@SuppressWarnings("restriction")
		EClassifier oclType = org.eclipse.ocl.ecore.internal.UMLReflectionImpl.INSTANCE.getOCLType(element);
		if (oclType instanceof AnyType<?>) {
			if (value instanceof Collection<?>) {
				return copy
					? CollectionUtil.createNewCollection((Collection<?>) value)
					: value;
			} else {
				return value;
			}
		}
		return super.coerceValue(element, value, copy);
	}
	
	public EObject createInstance(EClassifier type, ModelParameter modelParam) {
        if (type instanceof EClass == false) {
            internalEnv().throwQVTException(
                	new QvtRuntimeException("Expected EClass, got " + type)); //$NON-NLS-1$
        }
		EClass impl = (EClass) type;
		if (!QvtOperationalUtil.isInstantiable(impl)) {
            internalEnv().throwQVTException(
            	new QvtRuntimeException("Cannot instantiate type " + impl.getName())); //$NON-NLS-1$
		}
		
		EObject newObject = impl.getEPackage().getEFactoryInstance().create(impl);
		
		TransformationInstance mainTransfInstance = internalEnv().getCurrentTransformation();
		if(mainTransfInstance == null) {
			assert modelParam == null;
			// not running in a transformation, ignore extent
			return newObject;
		}
		
		ModelParameterExtent targetExtent;
		if(modelParam == null) {
			targetExtent = getDefaultInstantiationExtent(impl);
		} else {
			OperationalTransformation targetTransf = (OperationalTransformation)modelParam.eContainer();
			assert targetTransf != null;

			TransformationInstance targetThis = mainTransfInstance;
			if(mainTransfInstance.getTransformation() != targetTransf) {
				targetThis = (TransformationInstance)mainTransfInstance.getThisInstanceOf(targetTransf);				
			}

			ModelInstance model = targetThis.getModel(modelParam);
			assert model != null;
			targetExtent = model.getExtent();			
		}
		
		if (isReadonlyGuardEnabled() && targetExtent.isReadonly()) {
			internalEnv().throwQVTException(new QvtRuntimeException(
					NLS.bind(EvaluationMessages.ExtendedOclEvaluatorVisitorImpl_ReadOnlyInputModel, modelParam.getName() + 
					" : " + QvtOperationalTypesUtil.getTypeFullName(modelParam.getEType())))); //$NON-NLS-1$
		}

		targetExtent.addObject(newObject);

		return newObject;
	}

	public ModelParameterExtent getDefaultInstantiationExtent(EClassifier type) {
		TransformationInstance mainTransfInstance = internalEnv().getCurrentTransformation();
		if(mainTransfInstance != null) {
			if(IntermediateClassFactory.isIntermediateClass(type)) {
				InternalTransformation internTransf = mainTransfInstance.getAdapter(InternalTransformation.class);
				ModelInstance intermExtent = internTransf.getIntermediateExtent();
				if(intermExtent != null) {
					return intermExtent.getExtent();
				}
			} else if(QVTUMLReflection.isUserModelElement(type)) {			
				EList<ModelParameter> modelParameters = mainTransfInstance.getTransformation().getModelParameter();
				ModelParameter modelParam = QvtOperationalModuleEnv.findModelParameter(type, DirectionKind.OUT, modelParameters);						
				ModelInstance model = mainTransfInstance.getModel(modelParam);
			
				if (model != null) {
					return model.getExtent();
				}
			}
		}
		
		return internalEnv().getUnboundExtent();
	}

	@SuppressWarnings("unchecked")
	public void callSetter(EObject target, EStructuralFeature eStructuralFeature, Object exprValue, boolean valueIsUndefined, boolean isReset) {
		if(getInvalidResult() == target) {
			// call performed on OclInvalid, can not continue
			return;
		}
		
		EObject owner = target;
		if(target instanceof ModuleInstance) {
			ModuleInstance moduleTarget = (ModuleInstance) target;
			owner = moduleTarget.getThisInstanceOf(moduleTarget.getModule());
		}
		
		if (eStructuralFeature instanceof ContextualProperty) {
			IntermediatePropertyModelAdapter.ShadowEntry shadow = IntermediatePropertyModelAdapter.getPropertyHolder(
										eStructuralFeature.getEContainingClass(), (ContextualProperty)eStructuralFeature, owner);
			owner = shadow.getPropertyRuntimeOwner(owner, this);
			eStructuralFeature = shadow.getProperty();
		}

		if (isReadonlyGuardEnabled()) {
			checkReadonlyGuard(eStructuralFeature, exprValue, owner);
		}
		
        if(eStructuralFeature.getEType() instanceof CollectionType) {
        	// OCL collection type used directly, set in module properties
        	Collection<Object> currentValues = (Collection<Object>) owner.eGet(eStructuralFeature);        	
    		if(currentValues == null) {
            	CollectionType<EClassifier, EOperation> collectionType = (CollectionType<EClassifier, EOperation>) eStructuralFeature.getEType();    			
				currentValues = EvaluationUtil.createNewCollection(collectionType);
    			owner.eSet(eStructuralFeature, currentValues);        			
    		}

        	if(isReset) {
        		currentValues.clear();
        	}
        	
        	if (exprValue instanceof Dictionary<?, ?> && currentValues instanceof Dictionary<?, ?>) {
        		Dictionary<Object, Object> newVal = (Dictionary<Object, Object>) exprValue;
        		for (Object nextKey : newVal.keys()) {
        			Object nextValue = newVal.get(nextKey);
        			//if (nextValue != getInvalidResult() && nextValue != null) {
        				((Dictionary<Object, Object>) currentValues).put(nextKey, nextValue);
        			//}
				}
        	}
        	else if (exprValue instanceof Collection) {
        		Collection<Object> newVal = (Collection<Object>) exprValue;
        		for (Object nextElement : newVal) {
        			if (nextElement != getInvalidResult() && nextElement != null) {
        				currentValues.add(nextElement);
        			}
				}
        	}
        	else if (exprValue != getInvalidResult() && exprValue != null) {
        		currentValues.add(exprValue);
        	}
        	
        	return;
        }
        
        Class<?> expectedType = eStructuralFeature.getEType().getInstanceClass();

        if (FeatureMapUtil.isMany(owner, eStructuralFeature))  {
			List<Object> featureValues = (List<Object>) owner.eGet(eStructuralFeature);
			if (isReset) {
				featureValues.clear();
			}
			if (exprValue instanceof Collection) {
                for (Object element : (Collection<Object>) exprValue) {
                    if (element != null) {
                        featureValues.add(ensureTypeCompatibility(element, expectedType, this));
                    }
                }
			} else if (!valueIsUndefined) {
				featureValues.add(ensureTypeCompatibility(exprValue, expectedType, this));
			}
        } else if (!valueIsUndefined || acceptsNullValue(expectedType)) {
			if (exprValue instanceof Collection) {
                for (Object element : (Collection<Object>) exprValue) {
                    if (element != null) {
                    	owner.eSet(eStructuralFeature, ensureTypeCompatibility(element, expectedType, this));
        				break;
                    }
                }
			}
			else {
				owner.eSet(eStructuralFeature, ensureTypeCompatibility(exprValue, expectedType, this));
			}
        } else {
        	owner.eUnset(eStructuralFeature);
        }
	}

	private boolean isReadonlyGuardEnabled() {
		return getContext().getSessionData().getValue(QVTEvaluationOptions.FLAG_READONLY_GUARD_ENABLED) == Boolean.TRUE;
	}

	public int getMaxStackDepth() {
		return getContext().getSessionData().getValue(QVTEvaluationOptions.EVALUATION_MAX_STACK_DEPTH);
	}
	
	public List<Class<? extends QvtGenericVisitorDecorator>> getVisitorDecoratorClasses() {
		return getContext().getSessionData().getValue(QVTEvaluationOptions.VISITOR_DECORATORS);
	}

	private void checkReadonlyGuard(EStructuralFeature eStructuralFeature, Object exprValue, EObject owner) {
		ModelParameter violatedReadonlyParam = ModelParameterExtent.getReadonlyModelParameter(owner);
		
		if(violatedReadonlyParam == null && eStructuralFeature instanceof EReference) {
			EReference eReference = (EReference) eStructuralFeature;
			if (eReference.isContainment()) {
				if (exprValue instanceof EObject) {
					violatedReadonlyParam = ModelParameterExtent.getReadonlyModelParameter((EObject) exprValue);
				} else if (exprValue instanceof Collection<?>) {
					for (Object element : (Collection<?>) exprValue) {
						if (element instanceof EObject) {
							violatedReadonlyParam = ModelParameterExtent.getReadonlyModelParameter((EObject) exprValue);							
							if(violatedReadonlyParam != null) {
								break;
							}
						}
					}
				}
			}
		}

		if(violatedReadonlyParam != null) {
			internalEnv().throwQVTException(new QvtRuntimeException(
					NLS.bind(EvaluationMessages.ExtendedOclEvaluatorVisitorImpl_ReadOnlyInputModel, violatedReadonlyParam.getName() + 
					" : " + QvtOperationalTypesUtil.getTypeFullName(violatedReadonlyParam.getEType())))); //$NON-NLS-1$
		}
	}


//	private boolean isMany(EObject ownerObj, EStructuralFeature eStructuralFeature) {
//		if (eStructuralFeature.isMany()) {
//			return true;
//		}
//		if (eStructuralFeature.getLowerBound() == 0 && eStructuralFeature.getUpperBound() == ETypedElement.UNSPECIFIED_MULTIPLICITY) {
//			return ownerObj.eGet(eStructuralFeature) instanceof List;
//		}
//		return false;
//	}
	
	/**
	 * Ensures that the value has a type compatible with then expected type
	 * converting it if necessary.
	 * 
	 * @param value
	 *            the value (may be <code>null</code>)
	 * @param expectedType
	 *            the expected type (may be <code>null</code>)
	 * @return the converted value
	 */
	private Object ensureTypeCompatibility(Object value, Class<?> expectedType, QvtOperationalEvaluationEnv evalEnv) {
		if ((expectedType == Double.class || expectedType == double.class) && value instanceof Integer) {
			// In OCL Integer conforms to Real, in Java Integer doesn't conform to Double.
			return Double.valueOf(((Integer) value).doubleValue());
		}
		if (value == evalEnv.getInvalidResult()) {
			return null;
		}
		
		if(expectedType != null) {
			// perform the type conversion only the expected type is available 
			return NumberConversions.convertNumber(value, expectedType);
		}
		
		return value;
	}

	/**
	 * Returns <code>true</code> if <code>null</code> is a valid value of
	 * the type.
	 * 
	 * @param type
	 *            the type (may be <code>null</code>)
	 * @return <code>true</code> if <code>null</code> is a valid value of
	 *         the type
	 */
	private boolean acceptsNullValue(Class<?> type) {
		if (type == null) {
			return true;
		}
		return !type.isPrimitive();
	}

	public QvtOperationalEvaluationEnv cloneEvaluationEnv() {
		QvtOperationalEvaluationEnv env = new QvtOperationalEvaluationEnv(getContext(), getParent());
		return copyEnv(env);
	}

	// just running under the root transformation execution environment,
	// so cut me off the whole execution stack hierarchy
	public QvtOperationalEvaluationEnv createDeferredExecutionEnvironment() {
		QvtOperationalEvaluationEnv parent = (getRoot() == this) ? parent = null : getRoot();
		QvtOperationalEvaluationEnv result = new QvtOperationalEvaluationEnv(getContext(), parent);		
		return copyEnv(result);
	}	
    
	protected QvtOperationalEvaluationEnv copyEnv(QvtOperationalEvaluationEnv env) {
        env.myInternal = internalEnv().clone();
        
        env.myOperationArgs.addAll(myOperationArgs);
        env.myOperationSelf = myOperationSelf;
        env.myOperation = myOperation; 
        env.myBindings.putAll(myBindings);
        return env;
    }
        
    /**
     * Sets the operation being currently executed.
     */
    public void setOperation(ImperativeOperation myOperation) {
		this.myOperation = myOperation;
	}
    
    /**
	 * Gets the operation being currently executed.
	 * 
	 * @return the operation of <code>null</code> if no operation context is
	 *         available
	 */    
    public ImperativeOperation getOperation() {
		return myOperation;
	}
        
    /**
     * The root evaluation environment, refers to <code>this</code> if this is the root environment
     */
    private QvtOperationalEvaluationEnv myRootEnv;
    private Internal myInternal;
    private ImperativeOperation myOperation;    
	private final List<Object> myOperationArgs;
	private Object myOperationSelf;
    private final Map<String, Object> myBindings;
    private final int myStackDepth;
    private final List<ModelParameterExtent> myExtents;
	
	private static class TypedBinding {
		final Object value;
		final EClassifier type;
		
		private TypedBinding(Object value, EClassifier type) {
			this.value = value;
			this.type = type;
		}
		
		@Override
		public String toString() {
			StringBuilder buf = new StringBuilder();
			if(type != null) {
				buf.append(type).append(" : "); //$NON-NLS-1$
			}
			buf.append(value);
			return buf.toString();
		}
	}
	
	private class RootInternal extends Internal {
		private IContext myContext;
		private List<Runnable> myDeferredTasks;
	    private EObjectEStructuralFeaturePair myLastAssignLvalue;	  
	    private ModelParameterExtent myUnboundExtent;
	    private TransformationInstance myThisTransformation;
	    private boolean myIsDefferedExecution;
	    private Trace myTraces;

	    RootInternal(IContext context) {
	    	assert context != null;
	    	myContext = context;
	    	myIsDefferedExecution = false;
	    	myTraces = TraceFactory.eINSTANCE.createTrace();
	    }

	    RootInternal(RootInternal another) {
	    	super(another);
			myLastAssignLvalue = another.myLastAssignLvalue;
			myDeferredTasks = another.myDeferredTasks;
			myContext = another.myContext;
			myUnboundExtent = another.myUnboundExtent;
			myThisTransformation = another.myThisTransformation;
			myIsDefferedExecution = another.myIsDefferedExecution;
		}

	    @Override
	    IContext getContext() {
	    	return myContext;
	    }
	    
	    @Override
	    public TransformationInstance getCurrentTransformation() {	    
	    	return myThisTransformation;
	    }
	    
	    @Override
	    public void setThisResolver(ThisInstanceResolver thisResolver) {
	    	if(thisResolver instanceof TransformationInstance) {
	    		myThisTransformation = (TransformationInstance) thisResolver; 
	    	}

	    	super.setThisResolver(thisResolver);
	    }
	    	    	    
	    @Override
	    public Internal clone() {
	    	return new RootInternal(this);
	    }
	    
	    @Override
	    public ModelParameterExtent getUnboundExtent() {
	    	if(myUnboundExtent == null) {
	    		myUnboundExtent = new ModelParameterExtent();
	    	}
	    	return myUnboundExtent;
	    }
	    	    
	    @Override
	    public void addDeferredTask(Runnable task) {
	    	if (myDeferredTasks == null) {
	    		myDeferredTasks = new ArrayList<Runnable>();
	    	}
	    	myDeferredTasks.add(task);
	    }
	    
	    @Override
	    public EObjectEStructuralFeaturePair getLastAssignmentLvalueEval() {
	        return myLastAssignLvalue;
	    }

	    @Override
	    public void setLastAssignmentLvalueEval(EObjectEStructuralFeaturePair lvalue) {
	        myLastAssignLvalue = lvalue;
	    }
	    	    
	    @Override
	    public void processDeferredTasks() {
	    	if (myDeferredTasks != null) {
	    		try {
		    		myIsDefferedExecution = true;	    			
		    		// make me re-entrant in case of errorenous call to #addDeferredTask() 
		    		// from running the task => concurrent modification exception
		    		// This error condition should be handled elsewhere
		    		List<Runnable> tasksCopy = new ArrayList<Runnable>(myDeferredTasks);
		    	    for (Runnable task : tasksCopy) {
		                task.run();
		            }
	    		} finally {
		    		myIsDefferedExecution = false;	    			
	    		}
	    	}
	    }

	    @Override
		public boolean isDeferredExecution() {
			return myIsDefferedExecution;
		}
	    
	    @Override
	    public Trace getTraces() {
	    	return myTraces; 
	    }
	    
	    @Override
	    public void setTraces(Trace trace) {
	    	myTraces = trace; 
	    }
	}
	
	private class Internal implements InternalEvaluationEnv {
	    private ThisInstanceResolver myThisResolver;		
	    private EObject myCurrentIP;
	    
		Internal() {
		}		
		
		Internal(Internal another) {
			this();
			myThisResolver = another.myThisResolver;
			myCurrentIP = another.myCurrentIP;
		}
		
	    IContext getContext() {
	    	return getRoot().internalEnv().getContext();
	    }			    
	    
	    @Override
	    public Internal clone() {
	    	return new Internal(this);
	    }
	    	    
		public TransformationInstance getCurrentTransformation() {
			return getRoot().internalEnv().getCurrentTransformation();
		}
	    
		public ModuleInstance getCurrentModule() {		
			return (myThisResolver instanceof ModuleInstance) ? (ModuleInstance)myThisResolver : null;
		}
		
		public ModelParameterExtent getUnboundExtent() {
			return getRoot().internalEnv().getUnboundExtent();
		}	    
 		
		public void addModelExtent(ModelParameterExtent extent) {
			getRoot().myExtents.add(extent);
		}

		public void cleanup() {
			for (ModelParameterExtent extent : getRoot().myExtents) {
				extent.cleanup();
			}
		}
		
		public void setThisResolver(ThisInstanceResolver thisResolver) {
			this.myThisResolver = thisResolver;
		}
		
		public ThisInstanceResolver getThisResolver() {
			return myThisResolver;
		}
		
		public Object getInvalid() {
			return getInvalidResult();
		}
			    
	    public EObjectEStructuralFeaturePair getLastAssignmentLvalueEval() {	    
	    	return getRoot().internalEnv().getLastAssignmentLvalueEval();
	    }
	    
	    public void setLastAssignmentLvalueEval(EObjectEStructuralFeaturePair lvalue) {
	    	getRoot().internalEnv().setLastAssignmentLvalueEval(lvalue);
	    }
	    
	    public void processDeferredTasks() {
	    	getRoot().internalEnv().processDeferredTasks();
	    }
	    
		public boolean isDeferredExecution() {
			return getRoot().internalEnv().isDeferredExecution();
		}

	    public void addDeferredTask(Runnable task) {
	    	getRoot().internalEnv().addDeferredTask(task);
	    }

	    public void setTraces(Trace trace) {
	    	getRoot().internalEnv().setTraces(trace);
	    }
	    
	    public Trace getTraces() {
	    	return getRoot().internalEnv().getTraces();
	    }
	    
	    public EObject setCurrentIP(EObject currentIPObject) {
	    	EObject prevValue = myCurrentIP;
	    	myCurrentIP = currentIPObject;
	    	return prevValue;
		}
	    
	    public EObject getCurrentIP() {
			return myCurrentIP;
		}
	    
		public void throwQVTException(QvtRuntimeException exception) throws QvtRuntimeException {
			try {
				exception.setStackQvtTrace(getStackTraceElements());
			} catch (Exception e) {
				QvtPlugin.error("Failed to build QVT stack trace", e); //$NON-NLS-1$
			}
			
			throw exception;
		}
		
	    public List<QVTStackTraceElement> getStackTraceElements() {
	    	return new QvtStackTraceBuilder(QvtOperationalEvaluationEnv.this).buildStackTrace();
	    }		
	}
}

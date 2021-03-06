/**
 */
package org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.examples.pivot.util.Pivotable;
import org.eclipse.ocl.examples.xtext.base.basecs.ElementCS;
import org.eclipse.ocl.examples.xtext.base.basecs.ElementRefCS;
import org.eclipse.ocl.examples.xtext.base.basecs.ModelElementCS;
import org.eclipse.ocl.examples.xtext.base.basecs.PivotableElementCS;
import org.eclipse.ocl.examples.xtext.base.basecs.TypeRefCS;
import org.eclipse.ocl.examples.xtext.base.basecs.TypedRefCS;
import org.eclipse.ocl.examples.xtext.base.basecs.util.VisitableCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.AbstractNameExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.ExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.InvocationExpCS;
import org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.NamedExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.AssertExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.AssignStatementCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.BlockExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ComputeExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralPartCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictTypeCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DoExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExceptCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExpressionBlockCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExpressionStatementCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ForExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeIterateExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeLoopExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeOCLCSPackage;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.InstantiationExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListLiteralExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListTypeCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.LogExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.QuitExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.RaiseExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ReturnExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.StatementCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.SwitchAltCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.SwitchExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.TryExpCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.VariableInitializationCS;
import org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.WhileExpCS;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeOCLCSPackage
 * @generated
 */
public class ImperativeOCLCSAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ImperativeOCLCSPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImperativeOCLCSAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ImperativeOCLCSPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ImperativeOCLCSSwitch<Adapter> modelSwitch =
		new ImperativeOCLCSSwitch<Adapter>() {
			@Override
			public Adapter caseAssertExpCS(AssertExpCS object) {
				return createAssertExpCSAdapter();
			}
			@Override
			public Adapter caseAssignStatementCS(AssignStatementCS object) {
				return createAssignStatementCSAdapter();
			}
			@Override
			public Adapter caseBlockExpCS(BlockExpCS object) {
				return createBlockExpCSAdapter();
			}
			@Override
			public Adapter caseDictLiteralExpCS(DictLiteralExpCS object) {
				return createDictLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseDictLiteralPartCS(DictLiteralPartCS object) {
				return createDictLiteralPartCSAdapter();
			}
			@Override
			public Adapter caseDictTypeCS(DictTypeCS object) {
				return createDictTypeCSAdapter();
			}
			@Override
			public Adapter caseComputeExpCS(ComputeExpCS object) {
				return createComputeExpCSAdapter();
			}
			@Override
			public Adapter caseImperativeIterateExpCS(ImperativeIterateExpCS object) {
				return createImperativeIterateExpCSAdapter();
			}
			@Override
			public Adapter caseImperativeLoopExpCS(ImperativeLoopExpCS object) {
				return createImperativeLoopExpCSAdapter();
			}
			@Override
			public Adapter caseInstantiationExpCS(InstantiationExpCS object) {
				return createInstantiationExpCSAdapter();
			}
			@Override
			public Adapter caseDoExpCS(DoExpCS object) {
				return createDoExpCSAdapter();
			}
			@Override
			public Adapter caseExceptCS(ExceptCS object) {
				return createExceptCSAdapter();
			}
			@Override
			public Adapter caseExpressionBlockCS(ExpressionBlockCS object) {
				return createExpressionBlockCSAdapter();
			}
			@Override
			public Adapter caseExpressionStatementCS(ExpressionStatementCS object) {
				return createExpressionStatementCSAdapter();
			}
			@Override
			public Adapter caseForExpCS(ForExpCS object) {
				return createForExpCSAdapter();
			}
			@Override
			public Adapter caseListTypeCS(ListTypeCS object) {
				return createListTypeCSAdapter();
			}
			@Override
			public Adapter caseListLiteralExpCS(ListLiteralExpCS object) {
				return createListLiteralExpCSAdapter();
			}
			@Override
			public Adapter caseLogExpCS(LogExpCS object) {
				return createLogExpCSAdapter();
			}
			@Override
			public Adapter caseQuitExpCS(QuitExpCS object) {
				return createQuitExpCSAdapter();
			}
			@Override
			public Adapter caseRaiseExpCS(RaiseExpCS object) {
				return createRaiseExpCSAdapter();
			}
			@Override
			public Adapter caseReturnExpCS(ReturnExpCS object) {
				return createReturnExpCSAdapter();
			}
			@Override
			public Adapter caseStatementCS(StatementCS object) {
				return createStatementCSAdapter();
			}
			@Override
			public Adapter caseSwitchAltCS(SwitchAltCS object) {
				return createSwitchAltCSAdapter();
			}
			@Override
			public Adapter caseSwitchExpCS(SwitchExpCS object) {
				return createSwitchExpCSAdapter();
			}
			@Override
			public Adapter caseTryExpCS(TryExpCS object) {
				return createTryExpCSAdapter();
			}
			@Override
			public Adapter caseVariableInitializationCS(VariableInitializationCS object) {
				return createVariableInitializationCSAdapter();
			}
			@Override
			public Adapter caseWhileExpCS(WhileExpCS object) {
				return createWhileExpCSAdapter();
			}
			@Override
			public Adapter caseVisitableCS(VisitableCS object) {
				return createVisitableCSAdapter();
			}
			@Override
			public Adapter caseElementCS(ElementCS object) {
				return createElementCSAdapter();
			}
			@Override
			public Adapter casePivotable(Pivotable object) {
				return createPivotableAdapter();
			}
			@Override
			public Adapter casePivotableElementCS(PivotableElementCS object) {
				return createPivotableElementCSAdapter();
			}
			@Override
			public Adapter caseModelElementCS(ModelElementCS object) {
				return createModelElementCSAdapter();
			}
			@Override
			public Adapter caseExpCS(ExpCS object) {
				return createExpCSAdapter();
			}
			@Override
			public Adapter caseElementRefCS(ElementRefCS object) {
				return createElementRefCSAdapter();
			}
			@Override
			public Adapter caseTypeRefCS(TypeRefCS object) {
				return createTypeRefCSAdapter();
			}
			@Override
			public Adapter caseTypedRefCS(TypedRefCS object) {
				return createTypedRefCSAdapter();
			}
			@Override
			public Adapter caseAbstractNameExpCS(AbstractNameExpCS object) {
				return createAbstractNameExpCSAdapter();
			}
			@Override
			public Adapter caseNamedExpCS(NamedExpCS object) {
				return createNamedExpCSAdapter();
			}
			@Override
			public Adapter caseInvocationExpCS(InvocationExpCS object) {
				return createInvocationExpCSAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.AssertExpCS <em>Assert Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.AssertExpCS
	 * @generated
	 */
	public Adapter createAssertExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.AssignStatementCS <em>Assign Statement CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.AssignStatementCS
	 * @generated
	 */
	public Adapter createAssignStatementCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.BlockExpCS <em>Block Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.BlockExpCS
	 * @generated
	 */
	public Adapter createBlockExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralExpCS <em>Dict Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralExpCS
	 * @generated
	 */
	public Adapter createDictLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralPartCS <em>Dict Literal Part CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictLiteralPartCS
	 * @generated
	 */
	public Adapter createDictLiteralPartCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictTypeCS <em>Dict Type CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DictTypeCS
	 * @generated
	 */
	public Adapter createDictTypeCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ComputeExpCS <em>Compute Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ComputeExpCS
	 * @generated
	 */
	public Adapter createComputeExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeIterateExpCS <em>Imperative Iterate Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeIterateExpCS
	 * @generated
	 */
	public Adapter createImperativeIterateExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeLoopExpCS <em>Imperative Loop Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ImperativeLoopExpCS
	 * @generated
	 */
	public Adapter createImperativeLoopExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.InstantiationExpCS <em>Instantiation Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.InstantiationExpCS
	 * @generated
	 */
	public Adapter createInstantiationExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DoExpCS <em>Do Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.DoExpCS
	 * @generated
	 */
	public Adapter createDoExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExceptCS <em>Except CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExceptCS
	 * @generated
	 */
	public Adapter createExceptCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExpressionBlockCS <em>Expression Block CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExpressionBlockCS
	 * @generated
	 */
	public Adapter createExpressionBlockCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExpressionStatementCS <em>Expression Statement CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ExpressionStatementCS
	 * @generated
	 */
	public Adapter createExpressionStatementCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ForExpCS <em>For Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ForExpCS
	 * @generated
	 */
	public Adapter createForExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListTypeCS <em>List Type CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListTypeCS
	 * @generated
	 */
	public Adapter createListTypeCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListLiteralExpCS <em>List Literal Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ListLiteralExpCS
	 * @generated
	 */
	public Adapter createListLiteralExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.LogExpCS <em>Log Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.LogExpCS
	 * @generated
	 */
	public Adapter createLogExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.QuitExpCS <em>Quit Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.QuitExpCS
	 * @generated
	 */
	public Adapter createQuitExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.RaiseExpCS <em>Raise Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.RaiseExpCS
	 * @generated
	 */
	public Adapter createRaiseExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ReturnExpCS <em>Return Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.ReturnExpCS
	 * @generated
	 */
	public Adapter createReturnExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.StatementCS <em>Statement CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.StatementCS
	 * @generated
	 */
	public Adapter createStatementCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.SwitchAltCS <em>Switch Alt CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.SwitchAltCS
	 * @generated
	 */
	public Adapter createSwitchAltCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.SwitchExpCS <em>Switch Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.SwitchExpCS
	 * @generated
	 */
	public Adapter createSwitchExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.TryExpCS <em>Try Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.TryExpCS
	 * @generated
	 */
	public Adapter createTryExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.VariableInitializationCS <em>Variable Initialization CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.VariableInitializationCS
	 * @generated
	 */
	public Adapter createVariableInitializationCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.WhileExpCS <em>While Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.qvto.examples.xtext.imperativeocl.imperativeoclcs.WhileExpCS
	 * @generated
	 */
	public Adapter createWhileExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.util.VisitableCS <em>Visitable CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.util.VisitableCS
	 * @generated
	 */
	public Adapter createVisitableCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.ElementCS <em>Element CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.ElementCS
	 * @generated
	 */
	public Adapter createElementCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.pivot.util.Pivotable <em>Pivotable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.pivot.util.Pivotable
	 * @generated
	 */
	public Adapter createPivotableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.PivotableElementCS <em>Pivotable Element CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.PivotableElementCS
	 * @generated
	 */
	public Adapter createPivotableElementCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.ModelElementCS <em>Model Element CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.ModelElementCS
	 * @generated
	 */
	public Adapter createModelElementCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.ExpCS <em>Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.ExpCS
	 * @generated
	 */
	public Adapter createExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.ElementRefCS <em>Element Ref CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.ElementRefCS
	 * @generated
	 */
	public Adapter createElementRefCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.TypeRefCS <em>Type Ref CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.TypeRefCS
	 * @generated
	 */
	public Adapter createTypeRefCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.base.basecs.TypedRefCS <em>Typed Ref CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.base.basecs.TypedRefCS
	 * @generated
	 */
	public Adapter createTypedRefCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.AbstractNameExpCS <em>Abstract Name Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.AbstractNameExpCS
	 * @generated
	 */
	public Adapter createAbstractNameExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.NamedExpCS <em>Named Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.NamedExpCS
	 * @generated
	 */
	public Adapter createNamedExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.InvocationExpCS <em>Invocation Exp CS</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.eclipse.ocl.examples.xtext.essentialocl.essentialoclcs.InvocationExpCS
	 * @generated
	 */
	public Adapter createInvocationExpCSAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ImperativeOCLCSAdapterFactory

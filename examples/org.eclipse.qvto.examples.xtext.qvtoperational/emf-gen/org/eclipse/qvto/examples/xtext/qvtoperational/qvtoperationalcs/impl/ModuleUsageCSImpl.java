/**
 */
package org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.ocl.examples.xtext.base.basecs.impl.ElementCSImpl;
import org.eclipse.ocl.examples.xtext.base.basecs.util.BaseCSVisitor;
import org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.ImportKindEnum;
import org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.ModuleKindCS;
import org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.ModuleRefCS;
import org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.ModuleUsageCS;
import org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.QVTOperationalCSPackage;
import org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.util.QVTOperationalCSVisitor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Module Usage CS</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.impl.ModuleUsageCSImpl#getImportKind <em>Import Kind</em>}</li>
 *   <li>{@link org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.impl.ModuleUsageCSImpl#getModuleKindCS <em>Module Kind CS</em>}</li>
 *   <li>{@link org.eclipse.qvto.examples.xtext.qvtoperational.qvtoperationalcs.impl.ModuleUsageCSImpl#getModuleRefs <em>Module Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModuleUsageCSImpl
		extends ElementCSImpl
		implements ModuleUsageCS {

	/**
	 * The default value of the '{@link #getImportKind() <em>Import Kind</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getImportKind()
	 * @generated
	 * @ordered
	 */
	protected static final ImportKindEnum IMPORT_KIND_EDEFAULT = ImportKindEnum.EXTENSION;

	/**
	 * The cached value of the '{@link #getImportKind() <em>Import Kind</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getImportKind()
	 * @generated
	 * @ordered
	 */
	protected ImportKindEnum importKind = IMPORT_KIND_EDEFAULT;

	/**
	 * The cached value of the '{@link #getModuleKindCS() <em>Module Kind CS</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleKindCS()
	 * @generated
	 * @ordered
	 */
	protected ModuleKindCS moduleKindCS;

	/**
	 * The cached value of the '{@link #getModuleRefs() <em>Module Refs</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getModuleRefs()
	 * @generated
	 * @ordered
	 */
	protected EList<ModuleRefCS> moduleRefs;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ModuleUsageCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return QVTOperationalCSPackage.Literals.MODULE_USAGE_CS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ImportKindEnum getImportKind() {
		return importKind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportKind(ImportKindEnum newImportKind) {
		ImportKindEnum oldImportKind = importKind;
		importKind = newImportKind == null ? IMPORT_KIND_EDEFAULT : newImportKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QVTOperationalCSPackage.MODULE_USAGE_CS__IMPORT_KIND, oldImportKind, importKind));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ModuleKindCS getModuleKindCS() {
		return moduleKindCS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetModuleKindCS(ModuleKindCS newModuleKindCS,
			NotificationChain msgs) {
		ModuleKindCS oldModuleKindCS = moduleKindCS;
		moduleKindCS = newModuleKindCS;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS, oldModuleKindCS, newModuleKindCS);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setModuleKindCS(ModuleKindCS newModuleKindCS) {
		if (newModuleKindCS != moduleKindCS) {
			NotificationChain msgs = null;
			if (moduleKindCS != null)
				msgs = ((InternalEObject)moduleKindCS).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS, null, msgs);
			if (newModuleKindCS != null)
				msgs = ((InternalEObject)newModuleKindCS).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS, null, msgs);
			msgs = basicSetModuleKindCS(newModuleKindCS, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS, newModuleKindCS, newModuleKindCS));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ModuleRefCS> getModuleRefs() {
		if (moduleRefs == null) {
			moduleRefs = new EObjectContainmentEList<ModuleRefCS>(ModuleRefCS.class, this, QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_REFS);
		}
		return moduleRefs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * @added
	 */
	public <R> R accept(final QVTOperationalCSVisitor<R> v) {
		return v.visitModuleUsageCS(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 * @added
	 */
	public <R> R accept(final BaseCSVisitor<R> v) {
		return ((QVTOperationalCSVisitor<R>)v).visitModuleUsageCS(this);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS:
				return basicSetModuleKindCS(null, msgs);
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_REFS:
				return ((InternalEList<?>)getModuleRefs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case QVTOperationalCSPackage.MODULE_USAGE_CS__IMPORT_KIND:
				return getImportKind();
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS:
				return getModuleKindCS();
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_REFS:
				return getModuleRefs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case QVTOperationalCSPackage.MODULE_USAGE_CS__IMPORT_KIND:
				setImportKind((ImportKindEnum)newValue);
				return;
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS:
				setModuleKindCS((ModuleKindCS)newValue);
				return;
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_REFS:
				getModuleRefs().clear();
				getModuleRefs().addAll((Collection<? extends ModuleRefCS>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case QVTOperationalCSPackage.MODULE_USAGE_CS__IMPORT_KIND:
				setImportKind(IMPORT_KIND_EDEFAULT);
				return;
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS:
				setModuleKindCS((ModuleKindCS)null);
				return;
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_REFS:
				getModuleRefs().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case QVTOperationalCSPackage.MODULE_USAGE_CS__IMPORT_KIND:
				return importKind != IMPORT_KIND_EDEFAULT;
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_KIND_CS:
				return moduleKindCS != null;
			case QVTOperationalCSPackage.MODULE_USAGE_CS__MODULE_REFS:
				return moduleRefs != null && !moduleRefs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return super.toString();
	}

} // ModuleUsageCSImpl

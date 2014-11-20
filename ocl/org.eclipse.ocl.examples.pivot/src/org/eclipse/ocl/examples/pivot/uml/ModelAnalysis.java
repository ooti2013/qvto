/*******************************************************************************
 * Copyright (c) 2014 CEA LIST and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     E.D.Willink (CEA List) - initial API and implementation
 *******************************************************************************/
package org.eclipse.ocl.examples.pivot.uml;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.DynamicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.ocl.examples.common.utils.EcoreUtils;
import org.eclipse.ocl.examples.domain.utilities.DomainUtil;
import org.eclipse.ocl.examples.pivot.Element;
import org.eclipse.ocl.examples.pivot.ElementExtension;
import org.eclipse.ocl.examples.pivot.NamedElement;
import org.eclipse.ocl.examples.pivot.Profile;
import org.eclipse.ocl.examples.pivot.ProfileApplication;
import org.eclipse.ocl.examples.pivot.Stereotype;
import org.eclipse.ocl.examples.pivot.Type;
import org.eclipse.ocl.examples.pivot.TypeExtension;
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager;
import org.eclipse.ocl.examples.pivot.uml.UML2Pivot.Outer;
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;

/**
 * The ModelAnalysis captures the overall analysis of the UML M1 ProfileApplication and ElementExtensions.
 */
public class ModelAnalysis
{
	public static class ElementComparator implements Comparator<Element>
	{
		public static final @NonNull ElementComparator INSTANCE = new ElementComparator();
		
		public int compare(Element o1, Element o2) {
			if (o1 instanceof NamedElement) {
				if (o1 instanceof NamedElement) {
					String n1 = ((NamedElement)o1).getName();
					String n2 = ((NamedElement)o2).getName();
					return DomainUtil.safeCompareTo(n1, n2);
				}
				else {
					return 1;
				}
			}
			else {
				if (o1 instanceof NamedElement) {
					return -1;
				}
				else {
					return o1.hashCode() - o2.hashCode();
				}
			}
		}
	}

	protected final @NonNull Outer converter;
	protected final @NonNull ProfileAnalysis profileAnalysis;
	protected final @NonNull MetaModelManager metaModelManager;

	/**
	 *	Map of all Profiles Applied to each Package, populated initially by the explicit ProfileApplications and expanded to cover the
	 *transitive applications.
	 */
	/**
	 * All the ProfileApplication elements.
	 */
	private final @NonNull List<ProfileApplication> asProfileApplications = new ArrayList<ProfileApplication>();
//	private @Nullable Map<Profile, Set<Profile>> profile2allProfiles = new HashMap<Profile, Set<Profile>>();

	/**
	 * All the Profiles that are applied to something.
	 */
	private final @NonNull Set<Profile> appliedProfiles = new HashSet<Profile>();

	/**
	 * Map from the each applied Profiles to all the profiles that are applied by the application of the profile.
	 */
	private final @NonNull Map<Profile, Set<Profile>> appliedProfile2appliedProfileClosure = new HashMap<Profile, Set<Profile>>();

	/**
	 * Map from the each package to all the profiles aplied to the package.
	 */
	private final @NonNull Map<org.eclipse.ocl.examples.pivot.Package, Set<Profile>> package2appliedProfileClosure = new HashMap<org.eclipse.ocl.examples.pivot.Package, Set<Profile>>();

	private final @NonNull Map<EClass, Type> eClass2metatype = new HashMap<EClass, Type>();

	/**
	 *	List of UML elements stereotyped by each UML stereotype application.
	 *	<p>
	 *	Note that the UML stereotype application object is an EDynamicObject unless the Profile has been genmodelled as
	 *	is the case for the standard UML profile(s).
	 */
	private @Nullable List<EObject> umlStereotypeApplications = null;

	public ModelAnalysis(@NonNull Outer converter, @NonNull ProfileAnalysis profileAnalysis) {
		this.converter = converter;
		this.profileAnalysis = profileAnalysis;
		this.metaModelManager = converter.getMetaModelManager();
	}

	public void addProfile(@NonNull Profile asProfile) {
		appliedProfiles.add(asProfile);
	}

	public void addProfileApplication(@NonNull ProfileApplication asProfileApplication) {
		asProfileApplications.add(asProfileApplication);
		Profile asProfile = asProfileApplication.getAppliedProfile();
		if (asProfile != null) {
			appliedProfiles.add(asProfile);
		}
	}

	public void addStereotypeApplication(@NonNull EObject umlStereotypeApplication) {
		@SuppressWarnings("null")@NonNull EClass eClass = umlStereotypeApplication.eClass();
		if (UML2Pivot.ADD_STEREOTYPE_APPLICATION.isActive()) {
			if (umlStereotypeApplication instanceof DynamicEObjectImpl) {
				UML2Pivot.ADD_STEREOTYPE_APPLICATION.println(EcoreUtils.qualifiedNameFor(eClass));
			}
			else {
				UML2Pivot.ADD_STEREOTYPE_APPLICATION.println(EcoreUtils.qualifiedNameFor(eClass));
//					ADD_STEREOTYPE_APPLICATION.println(umlStereotypeApplication.toString());
			}
		}
		List<EObject> umlStereotypeApplications2 = umlStereotypeApplications;
		if (umlStereotypeApplications2 == null) {
			umlStereotypeApplications = umlStereotypeApplications2 = new ArrayList<EObject>();
		}
		umlStereotypeApplications2.add(umlStereotypeApplication);
		EPackage ePackage = eClass.getEPackage();
		Resource eResource = ePackage.eResource();
		if (eResource != null) {
			converter.addImportedResource(eResource);
		}
	}

	private void computeAppliedProfile2profileClosure() {
		//
		//	Determine the closure of all profiles for each actually applied profile.
		//
		for (@SuppressWarnings("null")@NonNull Profile asProfile : appliedProfiles) {
			Set<Profile> asProfiles = new HashSet<Profile>();
			computeProfileClosure(asProfiles, asProfile);
			appliedProfile2appliedProfileClosure.put(asProfile, asProfiles);
		}
	}

	/**
	 * Update element2elementExtension with an ElementExtension for each of the stereotypeApplications.
	 */
	private void computeExplicitElementExtensions(@NonNull Map<Element, Map<Stereotype, ElementExtension>> element2stereotype2extension,
			@NonNull Map<EObject, List<org.eclipse.uml2.uml.Element>> umlStereotypeApplication2umlStereotypedElements,
			@NonNull Map<Element, List<EObject>> asElement2umlStereotypeApplications) {
		for (@SuppressWarnings("null")@NonNull Element asStereotypedElement : asElement2umlStereotypeApplications.keySet()) {
			List<EObject> umlStereotypeApplications = asElement2umlStereotypeApplications.get(asStereotypedElement);
			Map<Stereotype, ElementExtension> stereotype2extension = element2stereotype2extension.get(asStereotypedElement);
			if (stereotype2extension == null) {
				stereotype2extension = new HashMap<Stereotype, ElementExtension>();
				element2stereotype2extension.put(asStereotypedElement, stereotype2extension);
			}
			for (@SuppressWarnings("null")@NonNull EObject umlStereotypeApplication : umlStereotypeApplications) {
//					EClass eClass = umlStereotypeApplication.eClass();
				@SuppressWarnings("null")@NonNull List<org.eclipse.uml2.uml.Element> umlStereotypedElements = umlStereotypeApplication2umlStereotypedElements.get(umlStereotypeApplication);
//					assert umlStereotypedElements != null;
				Stereotype asStereotype = converter.resolveStereotype(umlStereotypeApplication, umlStereotypedElements);
				if (asStereotype == null) {
					asStereotype = converter.resolveStereotype(umlStereotypeApplication, umlStereotypedElements);		// FIXME debugging
				}
				if (asStereotype != null) {
					ElementExtension elementExtension = metaModelManager.getElementExtension(asStereotypedElement, asStereotype);
					converter.setOriginalMapping(elementExtension, umlStereotypeApplication);
					elementExtension.setIsApplied(true);
					stereotype2extension.put(asStereotype, elementExtension);
					if (UML2Pivot.ADD_ELEMENT_EXTENSION.isActive()) {
						UML2Pivot.ADD_ELEMENT_EXTENSION.println(elementExtension.toString());
					}
				}
			}
		}
	}

	/**
	 * Update element2elementExtension with an ElementExtension for each of the required ExtensionEnds for each of
	 * the profile applied to each of the packages using package2allProfiles to identiofuy the packages and their
	 * applied profiles and profile2requiredExtensionEnds to idenmtiofy the required ExtensionEnds for each profile.
	 *
	private void computeImplicitElementExtensions(@NonNull Map<Element, List<ElementExtension>> element2elementExtension,
			@NonNull Map<org.eclipse.ocl.examples.pivot.Package, Set<Profile>> package2allProfiles,
			@NonNull Map<org.eclipse.uml2.uml.Profile, List<ExtensionEnd>> profile2requiredExtensionEnds) {
		for (org.eclipse.ocl.examples.pivot.Package asPackage : package2allProfiles.keySet()) {
			Set<Profile> asPackageProfiles = package2allProfiles.get(asPackage);
			for (Profile asProfile : asPackageProfiles) {
				org.eclipse.uml2.uml.Profile umlProfile = (org.eclipse.uml2.uml.Profile) asProfile.getETarget();
				List<org.eclipse.uml2.uml.ExtensionEnd> requiredExtensionEnds = profile2requiredExtensionEnds.get(umlProfile);
				if (requiredExtensionEnds != null) {
					for (org.eclipse.uml2.uml.ExtensionEnd umlExtensionEnd : requiredExtensionEnds) {
						if (umlExtensionEnd != null) {
							org.eclipse.uml2.uml.Type umlStereotype = umlExtensionEnd.getType();
							if (umlStereotype != null) {
								Stereotype asStereotype = converter.getCreated(Stereotype.class, umlStereotype);
								if (asStereotype != null) {
									org.eclipse.uml2.uml.Property umlOtherEnd = umlExtensionEnd.getOtherEnd();
									if (umlOtherEnd != null) {
										org.eclipse.uml2.uml.Type umlStereotypedElement = umlOtherEnd.getType();
										if (umlStereotypedElement != null) {
											try {
												Type asStereotypedElement = metaModelManager.getPivotOf(Type.class, umlStereotypedElement);
												if (asStereotypedElement != null) {
													ElementExtension asElementExtension = metaModelManager.getElementExtension(asStereotypedElement, asStereotype);
	//												setOriginalMapping(asElementExtension, umlStereotype);
													asElementExtension.setIsApplied(true);
													List<ElementExtension> asElementExtensions = element2elementExtension.get(asStereotypedElement);
													if (asElementExtensions == null) {
														asElementExtensions = new ArrayList<ElementExtension>();
														element2elementExtension.put(asStereotypedElement, asElementExtensions);
													}
													asElementExtensions.add(asElementExtension);
													if (UML2Pivot.ADD_ELEMENT_EXTENSION.isActive()) {
														UML2Pivot.ADD_ELEMENT_EXTENSION.println(asElementExtension.toString());
													}
												}
											} catch (ParserException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	} */

	private void computePackage2AppliedProfileClosure() {
		//
		//	Create the closure of all profiles to each package for which any profile is applied
		//
		for (ProfileApplication asProfileApplication : asProfileApplications) {
			org.eclipse.ocl.examples.pivot.Package asPackage = asProfileApplication.getApplyingPackage();
			if (asPackage != null) {
				Profile asProfile = asProfileApplication.getAppliedProfile();
				if (asProfile != null) {
					Set<Profile> appliedProfileClosure1 = appliedProfile2appliedProfileClosure.get(asProfile);
					if (appliedProfileClosure1 != null) {
						Set<Profile> appliedProfileClosure2 = package2appliedProfileClosure.get(asPackage);
						if (appliedProfileClosure2 == null) {
							appliedProfileClosure2 = new HashSet<Profile>();
							package2appliedProfileClosure.put(asPackage, appliedProfileClosure2);
						}	
						appliedProfileClosure2.addAll(appliedProfileClosure1);
					}	
				}
			}
		}
	}

	private void computeProfileClosure(@NonNull Set<Profile> allProfiles, @NonNull Profile asProfile) {
		if (allProfiles.add(asProfile)) {
			for (org.eclipse.ocl.examples.pivot.Package asNestedPackage : asProfile.getNestedPackage()) {
				if (asNestedPackage instanceof Profile) {
					computeProfileClosure(allProfiles, (Profile) asNestedPackage);
				}
			}
			for (org.eclipse.ocl.examples.pivot.Package asImportedPackage : asProfile.getImportedPackage()) {
				if (asImportedPackage instanceof Profile) {
					computeProfileClosure(allProfiles, (Profile) asImportedPackage);
				}
			}
		}
	}

	/**
	 * Install implicit ElementExtensions for all the required ExtensionEnds in all the applied Profiles to the all the packages,
	 * and explicit ElementsExtenmsions for all the applied Stereotype applications.
	 * @param umlStereotypeApplications 
	 */
	private void installElementExtensionPropertyValues(@NonNull Map<Element, Map<Stereotype, ElementExtension>> element2stereotype2extension,
			@NonNull Map<EObject, List<org.eclipse.uml2.uml.Element>> umlStereotypeApplication2umlStereotypedElements) {
		//
		// Compute the list of UML stereotype application for each stereotyped pivot element.
		//
		Map<Element, List<EObject>> asElement2umlStereotypeApplications = resolveStereotypeApplications(umlStereotypeApplication2umlStereotypedElements);
		//
		//	Compute and install the ElementExtensions from required ExtensionEnds and Stereotype applications
		//
//		Map<Element, List<ElementExtension>> element2elementExtension = new HashMap<Element, List<ElementExtension>>();
//			Map<org.eclipse.uml2.uml.Profile, List<ExtensionEnd>> profile2requiredExtensionEnds2 = profile2requiredExtensionEnds;
//			if (profile2requiredExtensionEnds2 != null) {
//				computeImplicitElementExtensions(element2elementExtension, package2allProfiles, profile2requiredExtensionEnds2);
//			}
		//
		// Compute an explicit ElementExtension for each stereotype application.
		//
		computeExplicitElementExtensions(element2stereotype2extension, umlStereotypeApplication2umlStereotypedElements, asElement2umlStereotypeApplications);
		//
		//	Install all the ElementExtensions
		//
		for (@SuppressWarnings("null")@NonNull Element asElement : element2stereotype2extension.keySet()) {
			Map<Stereotype, ElementExtension> stereotype2extension = element2stereotype2extension.get(asElement);
			List<ElementExtension> newElementExtensions = new ArrayList<ElementExtension>(stereotype2extension.values());
			List<ElementExtension> oldElementExtensions = asElement.getExtension();
			converter.refreshList(oldElementExtensions, newElementExtensions);
		}
/*		if (UML2Pivot.ADD_ELEMENT_EXTENSION.isActive()) {
			StringBuffer s = new StringBuffer();
			List<Element> asElements = new ArrayList<Element>(element2elementExtension.keySet());
			Collections.sort(asElements, ElementComparator.INSTANCE);
			for (@SuppressWarnings("null")@NonNull Element asElement : asElements) {
				s.append("\n\t" + EcoreUtils.qualifiedNameFor(asElement));
				s.append(" " + DomainUtil.debugSimpleName(asElement));
				org.eclipse.ocl.examples.pivot.Package asPackage = PivotUtil.getContainingPackage(asElement);
				if (asPackage != null) {
					s.append(" - " + asPackage.getNsURI());
				}
				for (ElementExtension asElementExtension : asElement.getExtensions()) {
					s.append("\n\t\t" + asElementExtension);
					s.append(" " + DomainUtil.debugSimpleName(asElementExtension));
				}
			}
			UML2Pivot.ADD_ELEMENT_EXTENSION.println("Extensions per Type" + s.toString());
		} */
	}

	public void installStereotypes() {
		profileAnalysis.analyze();
		computeAppliedProfile2profileClosure();
		computePackage2AppliedProfileClosure();
////				@SuppressWarnings("null")@NonNull Set<Profile> appliedProfileClosure = package2appliedProfileClosure.get(asPackage);
//				Map<Type, Set<TypeExtension>> metatype2typeExtensions = profileAnalysis.computeMetatypes2typeExtensions(/*appliedProfileClosure*/);
//				Map<Element, Map<Stereotype, ElementExtension>> element2stereotype2extension = new HashMap<Element, Map<Stereotype, ElementExtension>>();
//		for (org.eclipse.ocl.examples.pivot.Package asPackage : package2appliedProfileClosure.keySet()) {
//			if ((asPackage != null) /*&& !(asPackage instanceof Profile)*/) {
		Map<Element, Map<Stereotype, ElementExtension>> element2stereotype2extension = new HashMap<Element, Map<Stereotype, ElementExtension>>();
		for (org.eclipse.ocl.examples.pivot.Package asPackage : package2appliedProfileClosure.keySet()) {
			if ((asPackage != null) && !(asPackage instanceof Profile)) {
				@SuppressWarnings("null")@NonNull Set<Profile> appliedProfileClosure = package2appliedProfileClosure.get(asPackage);
				Map<Type, Set<TypeExtension>> metatype2typeExtensions = profileAnalysis.computeMetatypes2typeExtensions(appliedProfileClosure);
				printMetatypes2StereotypeExtensions(asPackage, metatype2typeExtensions);
				for (TreeIterator<EObject> tit = asPackage.eAllContents(); tit.hasNext(); ) {
					EObject eObject = tit.next();
					if (eObject instanceof Element) {
						Element asElement = (Element)eObject;
						EClass eClass = asElement.eClass();
						Type metatype = eClass2metatype.get(eClass);
						if ((metatype == null) && !eClass2metatype.containsKey(eClass)) {
							EPackage ePackage = eClass.getEPackage();
							String ePackageName = ePackage.getName();
							String eClassName = eClass.getName();
							if ((ePackageName != null) && (eClassName != null)) {
								metatype = profileAnalysis.getMetatype(ePackageName, eClassName);
								eClass2metatype.put(eClass, metatype);
							}
						}
						if (metatype != null) {
							Set<TypeExtension> typeExtensions = metatype2typeExtensions.get(metatype);
							if (typeExtensions != null) {
								Map<Stereotype, ElementExtension> stereotype2extension = installExtensions(asElement, typeExtensions);
								element2stereotype2extension.put(asElement, stereotype2extension);
							}
						}
					}
				}
			}
		}
		List<EObject> umlStereotypeApplications2 = umlStereotypeApplications;
		if (umlStereotypeApplications2 != null) {
			Map<EObject, List<org.eclipse.uml2.uml.Element>> umlStereotypeApplication2umlStereotypedElements = UML2PivotUtil.computeAppliedStereotypes(umlStereotypeApplications2);
			installElementExtensionPropertyValues(element2stereotype2extension, umlStereotypeApplication2umlStereotypedElements);
		}
//			Map<Metaclass<?>, List<Property>> metaclass2properties = new HashMap<Metaclass<?>, List<Property>>();
		//
		//	Install all the metaclass properties.
		//
//			for (Metaclass<?> metaclass : metaclass2properties.keySet()) {
//				List<Property> newProperties = metaclass2properties.get(metaclass);
//				List<Property> oldProperties = metaclass.getOwnedAttribute();
//				assert oldProperties != null;
//				refreshList(oldProperties, newProperties);
//			}
	}

	private @NonNull Map<Stereotype, ElementExtension> installExtensions(@NonNull Element asElement, @NonNull Set<TypeExtension> typeExtensions) {
		Map<Stereotype, ElementExtension> stereotype2extension = new HashMap<Stereotype, ElementExtension>();
		for (TypeExtension typeExtension : typeExtensions) {
			Stereotype stereotype = typeExtension.getStereotype();
			if (stereotype != null) {
				ElementExtension elementExtension = metaModelManager.getElementExtension(asElement, stereotype);
				elementExtension.setIsRequired(true);
				stereotype2extension.put(stereotype, elementExtension);
				if (UML2Pivot.ADD_ELEMENT_EXTENSION.isActive()) {
					UML2Pivot.ADD_ELEMENT_EXTENSION.println(asElement.toString() + " + " + elementExtension.toString());
				}
			}
		}
		return stereotype2extension;
	}

	protected void printMetatypes2StereotypeExtensions(@NonNull org.eclipse.ocl.examples.pivot.Package asPackage,
			@NonNull Map<Type, Set<TypeExtension>> metatype2typeExtensions) {
		if (UML2Pivot.TYPE_EXTENSIONS.isActive()) {
			StringBuffer s = new StringBuffer();
			s.append(EcoreUtils.qualifiedNameFor(asPackage) + " : " + asPackage.getNsURI());
			List<Type> metatypes = new ArrayList<Type>(metatype2typeExtensions.keySet());
			Collections.sort(metatypes, PivotUtil.NAMEABLE_COMPARATOR);
			for (Type metatype : metatypes) {
				if (metatype != null) {
					s.append("\n\t" + EcoreUtils.qualifiedNameFor(metatype) + " ++"); //+ " : " + asProfile.getNsURI());
					Set<TypeExtension> typeExtensions = metatype2typeExtensions.get(metatype);
					List<Stereotype> stereotypes = new ArrayList<Stereotype>();
					for (TypeExtension typeExtension : typeExtensions) {
						stereotypes.add(typeExtension.getStereotype());
					}
					Collections.sort(stereotypes, PivotUtil.NAMEABLE_COMPARATOR);
					for (Stereotype stereotype : stereotypes) {
						if (stereotype != null) {
							s.append(" " + EcoreUtils.qualifiedNameFor(stereotype) + ","); //+ " : " + asProfile.getNsURI());
						}
					}
				}
			}
			UML2Pivot.TYPE_EXTENSIONS.println(s.toString());
		}
	}

	/**
	 * Determine the UML stereotype applications for each stereotyped pivot element, from the pre-computed mapping
	 * of stereotyped UML elements for each UML stereotype application.
	 * @param umlStereotypeApplications 
	 */
	private @NonNull Map<Element, List<EObject>> resolveStereotypeApplications(@NonNull Map<EObject, List<org.eclipse.uml2.uml.Element>> umlStereotypeApplication2umlStereotypedElements) {
		Map<Element, List<EObject>> asElement2umlStereotypeApplications = new HashMap<Element, List<EObject>>();
		for (@SuppressWarnings("null")@NonNull EObject umlStereotypeApplication : umlStereotypeApplication2umlStereotypedElements.keySet()) {
			@SuppressWarnings("null")@NonNull List<org.eclipse.uml2.uml.Element> umlStereotypedElements = umlStereotypeApplication2umlStereotypedElements.get(umlStereotypeApplication);
			for (@SuppressWarnings("null")@NonNull org.eclipse.uml2.uml.Element umlStereotypedElement : umlStereotypedElements) {
				Element asStereotypedElement = converter.getCreated(Element.class, umlStereotypedElement);
				if (asStereotypedElement != null) {
					List<EObject> umlPerElementStereotypeApplications = asElement2umlStereotypeApplications.get(asStereotypedElement);
					if (umlPerElementStereotypeApplications == null) {
						umlPerElementStereotypeApplications = new ArrayList<EObject>();
						asElement2umlStereotypeApplications.put(asStereotypedElement, umlPerElementStereotypeApplications);
					}
					umlPerElementStereotypeApplications.add(umlStereotypeApplication);
				}
			}
		}
		return asElement2umlStereotypeApplications;
	}
}

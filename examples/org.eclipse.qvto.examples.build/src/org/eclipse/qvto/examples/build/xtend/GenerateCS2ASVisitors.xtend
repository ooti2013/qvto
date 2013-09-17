/**
 * <copyright>
 *
 * Copyright (c) 2013 Willink Transformations Ltd., University of York and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Adolfo Sanchez-Barbudo Herrera (University of York) - initial API and implementation
 *
 * </copyright>
 */
package org.eclipse.qvto.examples.build.xtend

import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EOperation
import org.eclipse.emf.ecore.EPackage
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.jdt.annotation.NonNull
import org.eclipse.ocl.examples.build.xtend.MergeWriter
import org.eclipse.ocl.examples.pivot.Class
import org.eclipse.ocl.examples.pivot.ExpressionInOCL
import org.eclipse.ocl.examples.pivot.OpaqueExpression
import org.eclipse.ocl.examples.pivot.Operation
import org.eclipse.ocl.examples.pivot.ecore.Ecore2Pivot
import org.eclipse.ocl.examples.pivot.manager.MetaModelManager
import org.eclipse.ocl.examples.pivot.model.OCLstdlib
import org.eclipse.ocl.examples.pivot.utilities.PivotUtil
import org.eclipse.ocl.examples.xtext.base.cs2as.Continuation
import org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup
import org.eclipse.qvto.examples.build.utlities.ContainmentVisitsGeneratorCtx
import org.eclipse.emf.ecore.EObject
import org.eclipse.qvto.examples.build.utlities.CS2ASGeneratorUtil
import org.eclipse.ocl.examples.autogen.java.AutoCodeGenerator
import org.eclipse.qvto.examples.xtext.imperativeocl.ImperativeOCLStandaloneSetup

// TODO non-derived visitor is not supported, since currently 
// the root CS2AS are not generated but manually coded. 
public class GenerateCS2ASVisitors extends org.eclipse.ocl.examples.build.xtend.GenerateCS2ASVisitors
{
//	private String asGenModelURI;
	 
	override void generateVisitors(EPackage csPackage) {
		OCLstdlib.install();
		EssentialOCLStandaloneSetup.doSetup();
		ImperativeOCLStandaloneSetup.doSetup();
		super.generateVisitors(csPackage);
		if (isDerived()) {
			// We do some required initializations	
				
		}
		generateAbstractExtendingDelegatingVisitor(csPackage); // FIXME remove this when bugXXX is solved
	}
	
	override def void generateContainmentVisitor(@NonNull EPackage csPackage) {
//		var String visitorVariantName = "Containment";
//		var String resultTypeName =  "Continuation<?>";
//		var String visitorVariantClassName = projectPrefix + visitorVariantName + "Visitor";
//		var String variantExtendedClass = superProjectPrefix + visitorVariantName + "Visitor";
//		//		var String extendedClass = if (superVisitorClassName.length() == 0) {
////				"AbstractExtending" + visitableClassName;
////			} else {
////				visitorPrefix + superVisitorClassName;
////			}
//		var String interfaceName =  visitorClassName +'<'+resultTypeName+'>';
//		var List<String> additionalImports additionalImports = new ArrayList();
//		additionalImports.add(typeof(Continuation).name);
//		csPackage.generateContainmentVisitor(visitorVariantClassName, variantExtendedClass, interfaceName, resultTypeName, additionalImports);
		if (isDerived()) {
			AutoCodeGenerator.generate(csPackage, projectPrefix, visitorPackageName, visitorClassName, 
				superProjectPrefix, superVisitorPackageName, superVisitorClassName
			);	
		} else {
			AutoCodeGenerator.generate(csPackage, projectPrefix, visitorPackageName, visitorClassName, 
				null, null, null);
		}
	}

	

	
	/**
	 * TODO When fully generation is achieved, visitAbstractConcept method needs to be qualified as follows:
	 *     visitAbstractElement(Abstract object) {
	 *       throw new UnsupportedOperationException();
	 *     }  
	 */
	protected def void generateContainmentVisitor(@NonNull EPackage ePackage, 
		@NonNull String className, @NonNull String extendedClassName, @NonNull String interfaceName, @NonNull String resultTypeName, 
		@NonNull List<String> additionalImports) {
		
		var MergeWriter writer = new MergeWriter(outputFolder + className + ".java");
		writer.append('''
			«ePackage.generateHeader(visitorPackageName)»
			
			import java.util.ArrayList;
			import java.util.List;

			import org.eclipse.jdt.annotation.NonNull;
			import org.eclipse.jdt.annotation.Nullable;
			import org.eclipse.ocl.examples.xtext.base.cs2as.CS2PivotConversion;
			import org.eclipse.ocl.examples.xtext.base.cs2as.CS2Pivot;
			import org.eclipse.ocl.examples.pivot.utilities.PivotUtil;
			import «superProjectName».cs2as.«extendedClassName»;
			«FOR additionalImport : additionalImports»
			import «additionalImport»;
			«ENDFOR»
			
			/**
			 *
			 */
			public class «className»
				extends «extendedClassName»
				implements «interfaceName»
			{
				/**
				 * Initializes me with an initial value for my result.
				 * 
				 * @param context my initial result value
				 */
				public «className»(@NonNull CS2PivotConversion context) {
					super(context);
				}
				«FOR eClass : getSortedEClasses(ePackage)»
				public @Nullable «resultTypeName» visit«eClass.name»(@NonNull «modelPackageName».«eClass.name» csElement) {
					«IF (eClass.hasAstOperation())»
					«eClass.generateContainmentVisit()»
					«ELSE»
					throw new UnsupportedOperationException("visit«eClass.name» not supported in «className»");
					«ENDIF»
				}
				
				«ENDFOR»
			}
		''');
		writer.close();
	}
	
	private def boolean hasAstOperation(EClass eclass)	{
		for (EOperation operation : eclass.EOperations) {
			if (CS2ASGeneratorUtil.isAstOperation(operation)) {
				return true;
			}
		}
		return false;
	}
	
	protected def String generateContainmentVisit(EClass eClass) {
		
		var MetaModelManager metaModelManager = MetaModelManager.getAdapter(resourceSet);
		var Resource ecoreResource = eClass.eResource(); 
		var Ecore2Pivot ecore2Pivot = Ecore2Pivot.getAdapter(ecoreResource, metaModelManager);
		var Class pClass = ecore2Pivot.getPivotElement(typeof(Class), eClass);
		if (pClass != null) {
			for (Operation operation :  pClass.ownedOperation) {
				var EObject target = operation.ETarget;
				if (target instanceof EOperation
					&& CS2ASGeneratorUtil.isAstOperation(target as EOperation)){
					// We obtain the expression to visit
					var OpaqueExpression opaqueExp = operation.bodyExpression;
					var ExpressionInOCL expInOcl = PivotUtil.getExpressionInOCL(operation, opaqueExp);
					// We compute the context

					return expInOcl.bodyExpression.accept(new ContainmentVisitsGenerator(new ContainmentVisitsGeneratorCtx(metaModelManager, outputFolder, visitorPackageName)));
				}
			}
		}
		return "return null;"; // TODO case in which no pClass or no ast operation has been found
	}
}

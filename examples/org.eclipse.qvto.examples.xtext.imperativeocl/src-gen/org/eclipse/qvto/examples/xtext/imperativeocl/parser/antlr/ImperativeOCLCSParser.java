/*
* generated by Xtext
*/
package org.eclipse.qvto.examples.xtext.imperativeocl.parser.antlr;

import com.google.inject.Inject;

import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.qvto.examples.xtext.imperativeocl.services.ImperativeOCLCSGrammarAccess;

public class ImperativeOCLCSParser extends org.eclipse.xtext.parser.antlr.AbstractAntlrParser {
	
	@Inject
	private ImperativeOCLCSGrammarAccess grammarAccess;
	
	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	
	@Override
	protected org.eclipse.qvto.examples.xtext.imperativeocl.parser.antlr.internal.InternalImperativeOCLCSParser createParser(XtextTokenStream stream) {
		return new org.eclipse.qvto.examples.xtext.imperativeocl.parser.antlr.internal.InternalImperativeOCLCSParser(stream, getGrammarAccess());
	}
	
	@Override 
	protected String getDefaultRuleName() {
		return "GrammmarCS";
	}
	
	public ImperativeOCLCSGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}
	
	public void setGrammarAccess(ImperativeOCLCSGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
	
}
package org.scriptkitty.perlipse.ui;

import org.eclipse.dltk.ui.CodeFormatterConstants;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;


/**
 */
public class PerlPreferenceConstants extends PreferenceConstants
{
    public static final String INTERNAL_EDITOR_INTERPRETER = "editorInterpreter";
    
    public static void initializeFoldingDefaults(IPreferenceStore store)
    {
        store.setDefault(PreferenceConstants.EDITOR_FOLDING_ENABLED, true);        
        store.setDefault(PreferenceConstants.EDITOR_FOLDING_LINES_LIMIT, 2);
        
        store.setDefault(PreferenceConstants.EDITOR_DOCS_FOLDING_ENABLED, true);
        store.setDefault(PreferenceConstants.EDITOR_COMMENTS_FOLDING_ENABLED, true);
    }
    
    public static void intializeDefaultValues(IPreferenceStore store)
    {
        // folding defaults
        initializeFoldingDefaults(store);
        
        // editor interpreter
        store.setDefault(INTERNAL_EDITOR_INTERPRETER, "");
        
        // formatting
        store.setDefault(CodeFormatterConstants.FORMATTER_TAB_CHAR, CodeFormatterConstants.SPACE);
		store.setDefault(CodeFormatterConstants.FORMATTER_TAB_SIZE, "4");
		store.setDefault(CodeFormatterConstants.FORMATTER_INDENTATION_SIZE, "4");

		// XXX: what does this do?
		store.setDefault(PreferenceConstants.SHOW_SOURCE_MODULE_CHILDREN, true);
		
		// TODO: add support for these < (pod) $ @ % (variables) 
		store.setDefault(PreferenceConstants.CODEASSIST_AUTOACTIVATION_TRIGGERS, ">:");
        
        // TODO: set defaults for other preferences - tabs, etc
    }
}

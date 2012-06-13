package org.scriptkitty.perlipse.ui;

import org.scriptkitty.perlipse.core.PerlCoreConstants;

public final class PerlUIConstants
{
    public static final String[] EDITOR_PREFERNEC_PAGE_IDS = {
    	"org.scriptkitty.perlipse.ui.preferences.PerlipseEditorPreferencePage",
        "org.scriptkitty.perlipse.ui.preferences.PerlipseEditorFoldingPreferencePage",
        "org.scriptkitty.perlipse.ui.preferences.PerlipseEditorHoversPreferencePage", 
    	"org.scriptkitty.perlipse.ui.preferences.PerlipseEditorSyntaxColoringPreferencePage",
    	"org.scriptkitty.perlipse.ui.preferences.PerlCodeTemplatesPreferencePage"
    };
	
    public static String EDITOR_ID = PerlCoreConstants.EDITOR_ID;

    public static String NATURE_ID = PerlCoreConstants.NATURE_ID;

    public static String EDITOR_CONTEXT = "#PerlipseEditorContext";

    public static String ACTION_SET_ID = "org.scriptkitty.perlipse.ui.perlipseActionSet";    
    
    private PerlUIConstants()
    {
        // empty impl
    }
}

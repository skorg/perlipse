package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class PerlPreferenceMessages extends NLS
{
    private static String BUNDLE_NAME = "org.scriptkitty.perlipse.internal.ui.preferences.perlPreferenceMessages";
    
    static {
        NLS.initializeMessages(BUNDLE_NAME, PerlPreferenceMessages.class);
    }
        
    public static String preferencePage_editor;
    public static String preferencePage_parser;
    
    public static String todoTaskPage_description;

    public static String syntaxColorBackticks;
    
    public static String syntaxColorBarewordKeywords;
    public static String syntaxColorFileHandleKeywords;
    public static String syntaxColorFunctionKeywords;

//    public static String syntaxColorArrayKeywords;
//    public static String syntaxColorHashKeywords;
//    public static String syntaxColorScalarKeywords;

    public static String syntaxColorArrayVariable;
    public static String syntaxColorHashVariable;
    public static String syntaxColorScalarVariable;
        
    public static String syntaxColorPackageName;
    public static String syntaxColorSubroutineName;
    public static String syntaxColorQuoteAndRegExp;
    public static String syntaxColorHereDoc;
    
    public static String syntaxColorPod;
    public static String syntaxColorPodTag;
    
    public static String initiallyFoldPod;
    public static String initiallyFoldPkgs;
    public static String initiallyFoldSubs;
}

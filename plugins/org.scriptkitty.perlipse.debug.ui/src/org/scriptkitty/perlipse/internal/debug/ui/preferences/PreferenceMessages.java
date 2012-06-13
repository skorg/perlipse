package org.scriptkitty.perlipse.internal.debug.ui.preferences;

import org.eclipse.osgi.util.NLS;

public class PreferenceMessages extends NLS
{
   private static String BUNDLE_NAME = "org.scriptkitty.perlipse.internal.debug.ui.preferences.PreferenceMessages";
    
    static {
        NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
    }

    public static String PerlDebugPreferencePage_description;
    public static String PerlDebugEnginePreferencePage_description;
}

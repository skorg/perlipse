package org.scriptkitty.perlipse.internal.activestate.ui.preferences;

import org.eclipse.osgi.util.NLS;


public class PreferenceMessages extends NLS
{
    //~ Static fields/initializers

    private static String BUNDLE_NAME = "org.scriptkitty.perlipse.internal.activestate.ui.preferences.PreferenceMessages";

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, PreferenceMessages.class);
    }

    public static String ActiveStatePreferencePage_description;
    public static String ActiveStateDownload_name;
    public static String ActiveStateDownload_link;

}

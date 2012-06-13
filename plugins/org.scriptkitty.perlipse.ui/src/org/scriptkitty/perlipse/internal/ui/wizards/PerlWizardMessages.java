package org.scriptkitty.perlipse.internal.ui.wizards;

import org.eclipse.osgi.util.NLS;

public final class PerlWizardMessages extends NLS
{
    private static final String BUNDLE_NAME = "org.scriptkitty.perlipse.internal.ui.wizards.perlWizardMessages";

    static
    {
        NLS.initializeMessages(BUNDLE_NAME, PerlWizardMessages.class);
    }
    
    private PerlWizardMessages()
    {
        // empty constructor
    }

    public static String NewProjectWizard_title;
    public static String NewProjectFirstPage_title;
    public static String NewProjectFirstPage_description;
}

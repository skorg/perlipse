package org.scriptkitty.perlipse.core;

import org.eclipse.dltk.core.AbstractLanguageToolkit;

import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;


public class PerlLanguageToolkit extends AbstractLanguageToolkit
{
    private static final String NAME = "Perl";

    private static PerlLanguageToolkit instance = new PerlLanguageToolkit();

    public static PerlLanguageToolkit getInstance()
    {
        return instance;
    }

    /*
     * @see org.eclipse.dltk.core.IDLTKLanguageToolkit#getLanguageContentType()
     */
    public String getLanguageContentType()
    {
        return "org.scriptkitty.perlipse.core.perlipseContentType";
    }

    /*
     * @see org.eclipse.dltk.core.IDLTKLanguageToolkit#getLanguageName()
     */
    public String getLanguageName()
    {
        return NAME;
    }

    /*
     * @see org.eclipse.dltk.core.IDLTKLanguageToolkit#getNatureID()
     */
    public String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    @Override public String getPreferenceQualifier()
    {
        return PerlCorePlugin.PLUGIN_ID;
    }
}

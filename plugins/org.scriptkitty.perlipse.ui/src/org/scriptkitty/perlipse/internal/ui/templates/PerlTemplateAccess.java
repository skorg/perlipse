package org.scriptkitty.perlipse.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;

import org.eclipse.jface.preference.IPreferenceStore;

import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 * provides access to the perl template store
 */
public class PerlTemplateAccess extends ScriptTemplateAccess
{
    //~ Static fields/initializers

    private static PerlTemplateAccess self;

    public static String CUSTOM_TEMPLATE_KEY = "org.scriptkitty.perlipse.ui.templates";

    //~ Methods

    public static PerlTemplateAccess getInstance()
    {
        if (self == null)
        {
            self = new PerlTemplateAccess();
        }

        return self;
    }

    /*
     * @see org.scriptkitty.perlipse.internal.ui.templates.ScriptTemplateAccess#getContextTypeId()
     */
    @Override protected String getContextTypeId()
    {
        return PerlUniversalTemplateContextType.TPL_CONTEXT_TYPE_ID;
    }

    /*
     * @see org.scriptkitty.perlipse.internal.ui.templates.ScriptTemplateAccess#getCustomTemplatesKey()
     */
    @Override protected String getCustomTemplatesKey()
    {
        return PerlTemplateAccess.CUSTOM_TEMPLATE_KEY;
    }

    /*
     * @see org.scriptkitty.perlipse.internal.ui.templates.ScriptTemplateAccess#getPreferenceStore()
     */
    @Override protected IPreferenceStore getPreferenceStore()
    {
        return PerlUIPlugin.getPlugin().getPreferenceStore();
    }
}

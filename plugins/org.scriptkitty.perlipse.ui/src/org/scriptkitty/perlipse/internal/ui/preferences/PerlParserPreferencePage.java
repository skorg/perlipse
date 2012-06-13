package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.core.resources.IProject;

import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractSourceParserOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 * preference/property page for perlipse parsers
 */
public class PerlParserPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage
{
    static PreferenceKey PARSER_KEY = new PreferenceKey(PerlCoreConstants.PLUGIN_ID, PerlCoreConstants.SOURCE_PARSER);

    private static final String PREFERENCE_PAGE_ID = "org.scriptkitty.perlipse.ui.preferences.PerlipseParserPreferencePage";

    private static final String PROPERTY_PAGE_ID = null;

    // private static final String PROPERTY_PAGE_ID = "org.scriptkitty.perlipse.ui.propertyPage.PerlipseParserPreferencePage";

    /*
     * @see
     * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#createOptionsBlock(org.eclipse.dltk.ui.util.IStatusChangeListener,
     * org.eclipse.core.resources.IProject, org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
     */
    @Override protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener context, IProject project,
        IWorkbenchPreferenceContainer container)
    {
        return new AbstractSourceParserOptionsBlock(context, project, getKeys(), container)
        {
            @Override protected String getNatureId()
            {
                return PerlCoreConstants.NATURE_ID;
            }

            @Override protected PreferenceKey getSavedContributionKey()
            {
                return PARSER_KEY;
            }
        };
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
     */
    @Override protected String getHelpId()
    {
        // TOOD: project help
        return "";
    }

    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    /*
     * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
     */
    @Override protected String getPreferencePageId()
    {
        return PREFERENCE_PAGE_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getProjectHelpId()
     */
    @Override protected String getProjectHelpId()
    {
        // TODO: project help id
        return "";
    }

    /*
     * @see org.eclipse.dltk.internal.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
     */
    @Override protected String getPropertyPageId()
    {
        return PROPERTY_PAGE_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setDescription()
     */
    @Override protected void setDescription()
    {
        setDescription(PerlPreferenceMessages.preferencePage_parser);
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
     */
    @Override protected void setPreferenceStore()
    {
        /*
         * NOTE: want the preferences stored in the launching plugin - but this may not be the best way with the new
         * preferences service
         */
        setPreferenceStore(PerlUIPlugin.getCorePreferences());
    }

    private static PreferenceKey[] getKeys()
    {
        return new PreferenceKey[] { PARSER_KEY };
    }
}

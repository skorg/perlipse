package org.scriptkitty.perlipse.internal.debug.ui.preferences;

import org.eclipse.core.resources.IProject;

import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingEngineOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.debug.ui.PerlDebugUIPlugin;


/**
 * preference page for perlipse debugging engines
 */
public class PerlDebugEnginePreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage
{
    static PreferenceKey DEBUGGING_ENGINE_KEY = new PreferenceKey(PerlCoreConstants.PLUGIN_ID,
        PerlCoreConstants.DEBUGGING_ENGINE);

    private static final String PREFERENCE_PAGE_ID =
        "org.scriptkitty.perlipse.debug.ui.preferences.debug.PerlDebugEnginePreferencePage";
    private static final String PROPERTY_PAGE_ID =
        "org.scriptkitty.perlipse.debug.ui.propertyPage.debug.PerlDebugEnginePreferencePage";

    // ~Methods

    /*
     * @see
     * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#createOptionsBlock(org.eclipse.dltk.ui.util.IStatusChangeListener,
     * org.eclipse.core.resources.IProject, org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
     */
    @Override protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
        IProject project, IWorkbenchPreferenceContainer container)
    {
        return new AbstractDebuggingEngineOptionsBlock(newStatusChangedListener, project, getKeys(),
            container)
        {
            @Override protected String getNatureId()
            {
                return PerlCoreConstants.NATURE_ID;
            }

            @Override protected PreferenceKey getSavedContributionKey()
            {
                return DEBUGGING_ENGINE_KEY;
            }
        };
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
     */
    @Override protected String getHelpId()
    {
        // TODO: help id
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
        setDescription(PreferenceMessages.PerlDebugEnginePreferencePage_description);
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
     */
    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlDebugUIPlugin.getCorePreferences());
    }

    private PreferenceKey[] getKeys()
    {
        return new PreferenceKey[] { DEBUGGING_ENGINE_KEY };
    }
}

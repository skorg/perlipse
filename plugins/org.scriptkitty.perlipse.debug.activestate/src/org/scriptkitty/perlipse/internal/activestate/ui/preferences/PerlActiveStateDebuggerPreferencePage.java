package org.scriptkitty.perlipse.internal.activestate.ui.preferences;

import org.eclipse.core.resources.IProject;

import org.eclipse.dltk.debug.ui.preferences.ExternalDebuggingEngineOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;

import org.eclipse.swt.widgets.Composite;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.activestate.PerlActiveStateDebuggerConstants;
import org.scriptkitty.perlipse.internal.activestate.PerlActiveStateDebuggerPlugin;


/**
 * preference page for active state debugging engine
 */
public class PerlActiveStateDebuggerPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage
{
    //~ Static fields/initializers

    private static String PREFERENCE_PAGE_ID =
        "org.scriptkitty.perlipse.activestate.ui.preferences.PerlActiveStateDebuggerPreferencePage";
    private static String PROPERTY_PAGE_ID =
        "org.scriptkitty.perlipse.activestate.ui.preferences.PerlActiveStateDebuggerPreferencePage";

    static PreferenceKey ENGINE_PATH = new PreferenceKey(PerlActiveStateDebuggerPlugin.PLUGIN_ID,
        PerlActiveStateDebuggerConstants.ENGINE_PATH);

    static PreferenceKey LOG_FILE_NAME = new PreferenceKey(PerlActiveStateDebuggerPlugin.PLUGIN_ID,
        PerlActiveStateDebuggerConstants.LOG_FILE_NAME);

    //~ Methods

    /*
     * @see
     * org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#createOptionsBlock(org.eclipse.dltk.ui.util.IStatusChangeListener,
     * org.eclipse.core.resources.IProject, org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
     */
    @Override protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
        IProject project, IWorkbenchPreferenceContainer container)
    {
        return new ExternalDebuggingEngineOptionsBlock(newStatusChangedListener, project, getKeys(),
            container)
        {
            @Override protected void createOtherBlock(Composite composite)
            {
                addDownloadLink(composite, PreferenceMessages.ActiveStateDownload_name,
                    PreferenceMessages.ActiveStateDownload_link);
            }

            @Override protected PreferenceKey getDebuggingEnginePathKey()
            {
                return ENGINE_PATH;
            }

            @Override protected PreferenceKey getLogFileNamePreferenceKey()
            {
                return LOG_FILE_NAME;
            }
        };
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#getHelpId()
     */
    @Override protected String getHelpId()
    {
        // TODO: add help
        return "";
    }

    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.PropertyAndPreferencePage#getPreferencePageId()
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
        // TODO: add project help
        return "";
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.PropertyAndPreferencePage#getPropertyPageId()
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
        setDescription(PreferenceMessages.ActiveStatePreferencePage_description);
    }

    /*
     * @see org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage#setPreferenceStore()
     */
    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlActiveStateDebuggerPlugin.getDefault().getPreferenceStore());
    }

    private static PreferenceKey[] getKeys()
    {
        return new PreferenceKey[] { ENGINE_PATH, LOG_FILE_NAME };
    }
}

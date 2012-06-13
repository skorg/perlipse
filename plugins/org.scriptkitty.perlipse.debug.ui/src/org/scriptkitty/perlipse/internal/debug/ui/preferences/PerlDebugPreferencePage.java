package org.scriptkitty.perlipse.internal.debug.ui.preferences;

import org.eclipse.core.resources.IProject;

import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.preferences.AbstractDebuggingOptionsBlock;
import org.eclipse.dltk.ui.preferences.AbstractConfigurationBlockPropertyAndPreferencePage;
import org.eclipse.dltk.ui.preferences.AbstractOptionsBlock;
import org.eclipse.dltk.ui.preferences.PreferenceKey;
import org.eclipse.dltk.ui.util.IStatusChangeListener;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


/**
 */
public class PerlDebugPreferencePage extends AbstractConfigurationBlockPropertyAndPreferencePage
{
    private static PreferenceKey BREAK_ON_FIRST_LINE = new PreferenceKey(PerlCoreConstants.PLUGIN_ID,
        DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);

    private static PreferenceKey ENABLE_DBGP_LOGGING = new PreferenceKey(PerlCoreConstants.PLUGIN_ID,
        DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);

    private static PreferenceKey SHOW_GLOBAL_VARS = new PreferenceKey(PerlCoreConstants.PLUGIN_ID,
        DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL);

    private static PreferenceKey SHOW_CLASS_VARS = new PreferenceKey(PerlCoreConstants.PLUGIN_ID,
        DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS);

    private static PreferenceKey SHOW_LOCAL_VARS = new PreferenceKey(PerlCoreConstants.PLUGIN_ID,
        DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL);

    private static final String PREFERENCE_PAGE_ID = PerlCoreConstants.DEBUG_PREFERENCE_PAGE_ID;
    private static final String PROPERTY_PAGE_ID = "org.scriptkitty.perlipse.debug.ui.propertyPage.PerlDebugPreferencePage";

    @Override protected AbstractOptionsBlock createOptionsBlock(IStatusChangeListener newStatusChangedListener,
        IProject project, IWorkbenchPreferenceContainer container)
    {
        return new AbstractDebuggingOptionsBlock(newStatusChangedListener, project, getKeys(),
            container)
        {
            @Override protected PreferenceKey getBreakOnFirstLineKey()
            {
                return BREAK_ON_FIRST_LINE;
            }

            @Override protected PreferenceKey getDbgpLoggingEnabledKey()
            {
                return ENABLE_DBGP_LOGGING;
            }

            protected PreferenceKey getShowClassVarsKey()
            {
                return SHOW_CLASS_VARS;
            }

            protected PreferenceKey getShowGlobalVarsKey()
            {
                return SHOW_GLOBAL_VARS;
            }

            protected PreferenceKey getShowLocalVarsKey()
            {
                return SHOW_LOCAL_VARS;
            }
        };
    }

    @Override protected String getHelpId()
    {
        return null;
    }

    protected PreferenceKey[] getKeys()
    {
        return new PreferenceKey[]
            {
                BREAK_ON_FIRST_LINE, ENABLE_DBGP_LOGGING, SHOW_GLOBAL_VARS, SHOW_CLASS_VARS, SHOW_LOCAL_VARS
            };
    }

    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    @Override protected String getPreferencePageId()
    {
        return PREFERENCE_PAGE_ID;
    }

    @Override protected String getProjectHelpId()
    {
        return null;
    }

    @Override protected String getPropertyPageId()
    {
        return PROPERTY_PAGE_ID;
    }

    @Override protected void setDescription()
    {
        setDescription(PreferenceMessages.PerlDebugPreferencePage_description);
    }

    @Override protected void setPreferenceStore()
    {
        setPreferenceStore(PerlUIPlugin.getPlugin().getPreferenceStore());
    }
}

package org.scriptkitty.perlipse.internal.debug.ui.launchConfigurations;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;
import org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab;
import org.scriptkitty.perlipse.core.PerlCoreConstants;

/**
 * main launch configuration tab for perl scripts
 */
public class PerlLaunchConfigurationTab extends MainLaunchConfigurationTab
{
    //~ Constructors

    public PerlLaunchConfigurationTab(String mode)
    {
        super(mode);
    }

    //~ Methods

    /*
     * @see
     * org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#breakOnFirstLinePrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
     */
    @Override
    protected boolean breakOnFirstLinePrefEnabled(PreferencesLookupDelegate delegate)
    {
        return delegate.getBoolean(PerlCoreConstants.PLUGIN_ID,
                DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE);
    }

    /*
     * @see
     * org.eclipse.dltk.debug.ui.launchConfigurations.ScriptLaunchConfigurationTab#dbpgLoggingPrefEnabled(org.eclipse.dltk.core.PreferencesLookupDelegate)
     */
    @Override
    protected boolean dbpgLoggingPrefEnabled(PreferencesLookupDelegate delegate)
    {
        return delegate.getBoolean(PerlCoreConstants.PLUGIN_ID,
                DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING);
    }

    /*
     * @see org.eclipse.dltk.debug.ui.launchConfigurations.MainLaunchConfigurationTab#getNatureID()
     */
    @Override
    public String getNatureID()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

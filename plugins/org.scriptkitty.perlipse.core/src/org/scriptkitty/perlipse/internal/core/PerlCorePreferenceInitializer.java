package org.scriptkitty.perlipse.internal.core;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;

import org.eclipse.dltk.compiler.task.TaskTagUtils;
import org.eclipse.dltk.debug.core.DLTKDebugPreferenceConstants;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlCorePreferenceInitializer extends AbstractPreferenceInitializer
{
    /*
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override public void initializeDefaultPreferences()
    {
        IEclipsePreferences preferences = DefaultScope.INSTANCE.getNode(PerlCoreConstants.PLUGIN_ID);

        preferences.put(PerlCoreConstants.DEBUGGING_ENGINE, "");

        preferences.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_BREAK_ON_FIRST_LINE, false);
        preferences.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_ENABLE_LOGGING, false);

        preferences.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_GLOBAL, true);
        preferences.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_CLASS, true);
        preferences.putBoolean(DLTKDebugPreferenceConstants.PREF_DBGP_SHOW_SCOPE_LOCAL, true);

        TaskTagUtils.initializeDefaultValues(preferences);
    }
}

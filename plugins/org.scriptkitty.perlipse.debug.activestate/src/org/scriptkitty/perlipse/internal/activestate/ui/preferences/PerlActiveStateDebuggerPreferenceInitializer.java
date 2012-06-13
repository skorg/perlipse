package org.scriptkitty.perlipse.internal.activestate.ui.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;

import org.eclipse.jface.preference.IPreferenceStore;

import org.scriptkitty.perlipse.internal.activestate.PerlActiveStateDebuggerConstants;
import org.scriptkitty.perlipse.internal.activestate.PerlActiveStateDebuggerPlugin;


public class PerlActiveStateDebuggerPreferenceInitializer extends AbstractPreferenceInitializer
{
    //~ Methods

    /*
     * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
     */
    @Override public void initializeDefaultPreferences()
    {
        IPreferenceStore store = PerlActiveStateDebuggerPlugin.getDefault().getPreferenceStore();

        store.setDefault(PerlActiveStateDebuggerConstants.ENGINE_PATH, "");
        store.setDefault(PerlActiveStateDebuggerConstants.LOG_FILE_NAME, "perlDebug_{0}.log");
    }
}

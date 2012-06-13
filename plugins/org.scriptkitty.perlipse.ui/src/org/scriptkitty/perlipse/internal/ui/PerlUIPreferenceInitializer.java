package org.scriptkitty.perlipse.internal.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.dltk.ui.PreferenceConstants;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.editors.text.EditorsUI;
import org.scriptkitty.perlipse.internal.ui.text.PerlColorConstants;
import org.scriptkitty.perlipse.ui.PerlPreferenceConstants;


/**
 * perl ui plugin preference initializer
 */
public class PerlUIPreferenceInitializer extends AbstractPreferenceInitializer
{

    @Override public void initializeDefaultPreferences()
    {
        IPreferenceStore store = PerlUIPlugin.getPlugin().getPreferenceStore();

        EditorsUI.useAnnotationsPreferencePage(store);
        EditorsUI.useQuickDiffPreferencePage(store);

        PerlColorConstants.intializeDefaultValues(store);
        PerlPreferenceConstants.intializeDefaultValues(store);

        PreferenceConstants.initializeDefaultValues(store);
    }
}

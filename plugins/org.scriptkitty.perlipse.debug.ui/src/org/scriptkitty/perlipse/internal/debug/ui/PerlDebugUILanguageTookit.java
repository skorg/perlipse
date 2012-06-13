package org.scriptkitty.perlipse.internal.debug.ui;

import org.eclipse.dltk.debug.ui.AbstractDebugUILanguageToolkit;

import org.eclipse.jface.preference.IPreferenceStore;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlDebugUILanguageTookit extends AbstractDebugUILanguageToolkit
{
    public String getDebugModelId()
    {
        return PerlCoreConstants.DEBUG_MODEL_ID;
    }

    public IPreferenceStore getPreferenceStore()
    {
        return PerlDebugUIPlugin.getPlugin().getPreferenceStore();
    }

    @Override public String[] getVariablesViewPreferencePages()
    {
        return new String[] { "org.scriptkitty.perlipse.debug.ui.preferences.debug.PerlDetailFormattersPreferencePage" };
    }

}

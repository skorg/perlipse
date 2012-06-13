package org.scriptkitty.perlipse.internal.debug.ui;

import org.eclipse.dltk.debug.ui.DLTKDebugUIPluginPreferenceInitializer;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlDebugUIPreferenceInitializer extends DLTKDebugUIPluginPreferenceInitializer
{
    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

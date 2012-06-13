package org.scriptkitty.perlipse.internal.debug.ui.handlers;

import org.eclipse.dltk.debug.ui.handlers.AbstractToggleGlobalVariableHandler;

import org.eclipse.jface.preference.IPreferenceStore;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.debug.ui.PerlDebugUIPlugin;


/**
 * toggle 'global' variable display in the 'Variables' view
 */
public class ToggleGlobalVariablesHandler extends AbstractToggleGlobalVariableHandler
{
    //~ Methods

    /*
     * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getModelId()
     */
    @Override protected String getModelId()
    {
        return PerlCoreConstants.DEBUG_MODEL_ID;
    }

    /*
     * @see org.eclipse.dltk.debug.ui.handlers.AbstractToggleVariableHandler#getPreferenceStore()
     */
    @Override protected IPreferenceStore getPreferenceStore()
    {
        return PerlDebugUIPlugin.getCorePreferences();
    }
}

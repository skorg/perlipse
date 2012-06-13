package org.scriptkitty.perlipse.internal.debug.ui.handlers;

import org.eclipse.dltk.debug.ui.handlers.AbstractToggleLocalVariableHandler;

import org.eclipse.jface.preference.IPreferenceStore;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.debug.ui.PerlDebugUIPlugin;


/**
 * toggle 'local' variable display in the 'Variables' view
 */
public class ToggleLocalVariablesHandler extends AbstractToggleLocalVariableHandler
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

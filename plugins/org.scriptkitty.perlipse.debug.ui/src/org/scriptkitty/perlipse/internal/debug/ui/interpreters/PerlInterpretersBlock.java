package org.scriptkitty.perlipse.internal.debug.ui.interpreters;

import org.eclipse.dltk.core.environment.IEnvironment;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.IScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.ScriptRuntime;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


/**
 */
public class PerlInterpretersBlock extends InterpretersBlock
{
    //~ Methods

    /*
     * @see
     * org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock#createInterpreterDialog(org.eclipse.dltk.core.environment.IEnvironment,
     * org.eclipse.dltk.launching.IInterpreterInstall)
     */
    @Override protected IScriptInterpreterDialog createInterpreterDialog(IEnvironment environment,
        IInterpreterInstall standin)
    {
        // backwards compatible implementation
        AddScriptInterpreterDialog dialog = new AddPerlInterpreterDialog(this, getShell(),
            ScriptRuntime.getInterpreterInstallTypes(getCurrentNature()), standin);

        dialog.setEnvironment(environment);

        return dialog;
    }

    /*
     * @see org.eclipse.dltk.internal.debug.ui.interpreters.InterpretersBlock#getCurrentNature()
     */
    @Override protected String getCurrentNature()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

package org.scriptkitty.perlipse.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.IAddInterpreterDialogRequestor;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;

import org.eclipse.swt.widgets.Shell;


/**
 */
class AddPerlInterpreterDialog extends AddScriptInterpreterDialog
{
    //~ Constructors

    public AddPerlInterpreterDialog(IAddInterpreterDialogRequestor requestor, Shell shell,
        IInterpreterInstallType[] interpreterInstallTypes, IInterpreterInstall editedInterpreter)
    {
        super(requestor, shell, interpreterInstallTypes, editedInterpreter);
    }

    //~ Methods

    @Override protected AbstractInterpreterLibraryBlock createLibraryBlock(AddScriptInterpreterDialog dialog)
    {
        return new PerlInterpreterLibraryBlock(dialog);
    }

}

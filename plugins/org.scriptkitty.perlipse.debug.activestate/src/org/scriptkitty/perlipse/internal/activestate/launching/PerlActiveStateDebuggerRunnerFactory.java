package org.scriptkitty.perlipse.internal.activestate.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterRunnerFactory;


public class PerlActiveStateDebuggerRunnerFactory implements IInterpreterRunnerFactory
{
    //~ Methods

    /*
     * @see
     * org.eclipse.dltk.launching.IInterpreterRunnerFactory#createRunner(org.eclipse.dltk.launching.IInterpreterInstall)
     */
    public IInterpreterRunner createRunner(IInterpreterInstall install)
    {
        return new PerlActiveStateDebuggerRunner(install);
    }
}

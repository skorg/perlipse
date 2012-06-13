package org.scriptkitty.perlipse.launching;

import org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.launching.PerlRemoteDebuggerRunner;


public class PerlRemoteLaunchConfigurationDelegate extends AbstractRemoteLaunchConfigurationDelegate
{
    /*
     * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getLanguageId()
     */
    @Override public String getLanguageId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    /*
     * @see
     * org.eclipse.dltk.launching.AbstractRemoteLaunchConfigurationDelegate#getRemoteRunner(org.eclipse.dltk.launching.IInterpreterInstall)
     */
    @Override protected RemoteDebuggingEngineRunner getDebuggingRunner(IInterpreterInstall install)
    {
        return new PerlRemoteDebuggerRunner(install);
    }
}

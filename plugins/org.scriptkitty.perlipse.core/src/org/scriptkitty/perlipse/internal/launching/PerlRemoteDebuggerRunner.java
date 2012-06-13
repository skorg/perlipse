package org.scriptkitty.perlipse.internal.launching;

import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.RemoteDebuggingEngineRunner;

import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;


public class PerlRemoteDebuggerRunner extends RemoteDebuggingEngineRunner
{
    public PerlRemoteDebuggerRunner(IInterpreterInstall install)
    {
        super(install);
    }

    /*
     * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
     */
    @Override protected String getDebugPreferenceQualifier()
    {
        return PerlCorePlugin.PLUGIN_ID;
    }
}

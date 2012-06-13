package org.scriptkitty.perlipse.internal.activestate.launching;

import org.eclipse.dltk.core.PreferencesLookupDelegate;
import org.eclipse.dltk.launching.ExternalDebuggingEngineRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.InterpreterConfig;
import org.eclipse.dltk.launching.debug.DbgpConnectionConfig;

import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.activestate.PerlActiveStateDebuggerConstants;
import org.scriptkitty.perlipse.internal.activestate.PerlActiveStateDebuggerPlugin;

import java.io.File;


public class PerlActiveStateDebuggerRunner extends ExternalDebuggingEngineRunner
{
    //~ Static fields/initializers

    private static final String ENGINE_ID =
        "org.scriptkitty.perlipse.activestate.launching.PerlActiveStateDebuggerRunnerFactory";

    //~ Constructors

    public PerlActiveStateDebuggerRunner(IInterpreterInstall install)
    {
        super(install);
    }

    //~ Methods

    @Override protected InterpreterConfig alterConfig(InterpreterConfig config, PreferencesLookupDelegate delegate)
    {
        DbgpConnectionConfig dbgpConfig = DbgpConnectionConfig.load(config);

        String host = dbgpConfig.getHost();
        int port = dbgpConfig.getPort();
        String sessionId = dbgpConfig.getSessionId();

        String debugEnginePath = getDebuggingEnginePath(delegate).toString();

        int index = debugEnginePath.lastIndexOf(File.separatorChar);
        String dir = debugEnginePath.substring(0, index);
        String perl5db = debugEnginePath.substring(index + 1);

        InterpreterConfig newConfig = (InterpreterConfig) config.clone();

        newConfig.setWorkingDirectory(config.getWorkingDirectoryPath());

        newConfig.addEnvVars(config.getEnvVars());

        StringBuffer perlOpts = new StringBuffer();
        perlOpts.append("RemotePort=" + host + ":" + port);

        String logFile = getLogFileName(delegate, sessionId);

        if (logFile != null)
        {
            // spaces seperate the options
            perlOpts.append(" ");
            perlOpts.append("LogFile=" + logFile);
        }

        newConfig.addEnvVar("PERLDB_OPTS", perlOpts.toString());
        newConfig.addEnvVar("PERL5DB", "BEGIN { require \"" + perl5db + "\"; }");
        newConfig.addEnvVar("PERL5LIB", dir);
        newConfig.addEnvVar("DBGP_IDEKEY", sessionId);

        newConfig.addInterpreterArg("-d");
        newConfig.addScriptArgs(config.getScriptArgs());
        newConfig.setScriptFile(config.getScriptFilePath());

        return newConfig;
    }

    /*
     * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebuggingEngineId()
     */
    @Override protected String getDebuggingEngineId()
    {
        return ENGINE_ID;
    }

    /*
     * @see org.eclipse.dltk.launching.ExternalDebuggingEngineRunner#getDebuggingEnginePreferenceKey()
     */
    @Override protected String getDebuggingEnginePreferenceKey()
    {
        return PerlActiveStateDebuggerConstants.ENGINE_PATH;
    }

    /*
     * @see org.eclipse.dltk.launching.ExternalDebuggingEngineRunner#getDebuggingEnginePreferenceQualifier()
     */
    @Override protected String getDebuggingEnginePreferenceQualifier()
    {
        return PerlActiveStateDebuggerPlugin.PLUGIN_ID;
    }

    /*
     * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getDebugPreferenceQualifier()
     */
    @Override protected String getDebugPreferenceQualifier()
    {
        return PerlCoreConstants.PLUGIN_ID;
    }

    /*
     * @see org.eclipse.dltk.launching.DebuggingEngineRunner#getLogFileNamePreferenceKey()
     */
    @Override protected String getLogFileNamePreferenceKey()
    {
        return PerlActiveStateDebuggerConstants.LOG_FILE_NAME;
    }
}

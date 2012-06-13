package org.scriptkitty.perlipse.internal.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.IBuildParticipantFactory;
import org.eclipse.dltk.launching.IInterpreterInstall;
import org.eclipse.dltk.launching.IScriptProcessHandler;
import org.eclipse.dltk.launching.InternalScriptExecutor;
import org.eclipse.dltk.launching.ScriptRuntime;
import org.scriptkitty.perl.exec.ProcessHandler;
import org.scriptkitty.perlipse.core.util.PerlEnvironmentUtils;



public class PerlBuildParticipantFactory implements IBuildParticipantFactory
{
    //~ Methods

    @Override public IBuildParticipant createBuildParticipant(IScriptProject project) throws CoreException
    {
        IInterpreterInstall interpreter = ScriptRuntime.getInterpreterInstall(project);
        InternalScriptExecutor executor = new InternalScriptExecutor(interpreter, new PerlProcessHandler());

        String incPath = PerlEnvironmentUtils.createBuildPath(project);

        return new PerlBuildParticipant(incPath, executor);
    }

    //~ Inner Classes

    private class PerlProcessHandler implements IScriptProcessHandler
    {
        @Override public ScriptResult handle(Process process, char[] stdin)
        {
            ProcessHandler handler = ProcessHandler.getInstance(process, new String(stdin));
            ProcessHandler.ProcessResult result = handler.getResult();

            ScriptResult scriptResult = new ScriptResult();

            scriptResult.exitValue = result.exitValue;

            scriptResult.stderr = result.stderr;
            scriptResult.stdout = result.stdout;

            scriptResult.stdoutLines = result.stdoutLines();
            scriptResult.stderrLines = result.stderrLines();

            return scriptResult;
        }
    }
}

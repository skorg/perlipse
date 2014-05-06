package org.scriptkitty.perlipse.internal.builder;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.builder.IBuildContext;
import org.eclipse.dltk.core.builder.IBuildParticipant;
import org.eclipse.dltk.core.builder.ISourceLineTracker;
import org.eclipse.dltk.launching.IScriptProcessHandler.ScriptResult;
import org.eclipse.dltk.launching.InternalScriptExecutor;
import org.eclipse.dltk.utils.TextUtils;
import org.scriptkitty.perl.errorwarn.CompilerErrorOrWarn;
import org.scriptkitty.perl.errorwarn.CompilerOutput;


public class PerlBuildParticipant implements IBuildParticipant
{
    private InternalScriptExecutor executor;
    private String incPath;

    PerlBuildParticipant(String incPath, InternalScriptExecutor executor)
    {
        this.incPath = incPath;
        this.executor = executor;
    }

    @Override public void build(IBuildContext context) throws CoreException
    {
        // String path = context.getFile().getLocation().toOSString();
        String[] args = new String[] { "-c", "-w", incPath };

        IProblemReporter reporter = context.getProblemReporter();
        ISourceLineTracker tracker = context.getLineTracker();

        ScriptResult result = executor.execute(args, context.getContentsAsCharArray());

        for (CompilerOutput output : CompilerOutput.parse(result.stderrLines))
        {
            if (!output.isLocal() || output.isCompilationAborted())
            {
                // TODO: this needs to be logged in some manner, potentially...
                continue;
            }

            CompilerErrorOrWarn eow = output.getErrorOrWarning();

            int lineNumber = output.getLineNumber() - 1;
            ProblemSeverity severity = (eow.isWarning()) ? ProblemSeverity.WARNING : ProblemSeverity.ERROR;

            ISourceRange range = TextUtils.trimWhitespace(context.getSourceContents(), tracker.getLineInformation(lineNumber));
            PerlBuildProblem problem = new PerlBuildProblem(output.getMessage(), severity, range, lineNumber);

            reporter.reportProblem(problem);
        }
    }

    public ISourceRange trimWhitespace(String source, ISourceRange range)
    {
        int sOffset = range.getOffset();
        int eOffset = sOffset + range.getLength();

        String line = source.substring(sOffset, eOffset);
        char[] bytes = line.toCharArray();

        int start = 0;
        while (start < bytes.length)
        {
            if ((bytes[start] != '\t') && (bytes[start] != ' '))
            {
                break;
            }

            start++;
        }

        sOffset += start;

        return createSourceRange(sOffset, sOffset + line.trim().length());
    }

    private ISourceRange createSourceRange(final int start, final int end)
    {
        return new ISourceRange()
        {
            @Override public int getLength()
            {
                return end - start;
            }

            @Override public int getOffset()
            {
                return start;
            }
        };
    }
}

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

import org.scriptkitty.perl.compiler.CompilerOutput;
import org.scriptkitty.perl.compiler.CompilerOutputParser;
import org.scriptkitty.perl.compiler.ErrorsAndWarnings;


public class PerlBuildParticipant implements IBuildParticipant
{
    //~ Instance fields

    private InternalScriptExecutor executor;
    private String incPath;

    private CompilerOutputParser parser;

    //~ Constructors

    PerlBuildParticipant(String incPath, InternalScriptExecutor executor)
    {
        this.incPath = incPath;
        this.executor = executor;

        this.parser = new CompilerOutputParser();
    }

    //~ Methods

    @Override public void build(IBuildContext context) throws CoreException
    {
        // String path = context.getFile().getLocation().toOSString();
        String[] args = new String[] { "-c", "-w", incPath };

        IProblemReporter reporter = context.getProblemReporter();
        ISourceLineTracker tracker = context.getLineTracker();

        ScriptResult result = executor.execute(args, context.getContentsAsCharArray());

        for (CompilerOutput output : parser.parse(result.stderrLines))
        {
            if (!output.isLocal() || output.isCompilationAborted())
            {
                continue;
            }

            ErrorsAndWarnings.ErrorOrWarning eom = output.getErrorOrWarning();

            ProblemSeverity severity = getSeverity(eom);

            /*
             * TODO: replace when avaialble in dtlk-core
             */
            // ISourceRange range = TextUtils.trimWhitespace(context.getSourceContents(),
            // tracker.getLineInformation(output.lineNo - 1));

            ISourceRange range = trimWhitespace(context.getSourceContents(),
                tracker.getLineInformation(output.lineNo - 1));

            PerlBuildProblem problem = new PerlBuildProblem(output.message, severity, range, output.lineNo - 1);

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

    private ProblemSeverity getSeverity(ErrorsAndWarnings.ErrorOrWarning eom)
    {
        return (eom.isWarning()) ? ProblemSeverity.WARNING : ProblemSeverity.ERROR;
    }

}

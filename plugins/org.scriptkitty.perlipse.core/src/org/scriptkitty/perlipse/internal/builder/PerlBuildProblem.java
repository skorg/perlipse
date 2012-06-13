package org.scriptkitty.perlipse.internal.builder;

import org.eclipse.dltk.compiler.problem.DefaultProblem;
import org.eclipse.dltk.compiler.problem.ProblemSeverity;
import org.eclipse.dltk.core.ISourceRange;
import org.scriptkitty.perlipse.internal.core.PerlProblemIdentifier;

public class PerlBuildProblem extends DefaultProblem
{
    public PerlBuildProblem(String message, ProblemSeverity severity, ISourceRange range, int line)
    {
        super(message, PerlProblemIdentifier.BUILD, null, severity, -1, -1, line);
        
        setSourceStart(range.getOffset());
        setSourceEnd(range.getOffset() + range.getLength());
    }
}

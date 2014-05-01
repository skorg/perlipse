package org.scriptkitty.perlipse.internal.core;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.IProblemIdentifierExtension;
import org.scriptkitty.perlipse.core.PerlCoreConstants;

public enum PerlProblemIdentifier implements IProblemIdentifier, IProblemIdentifierExtension
{
    BUILD;
    
    @Override public String contributor()
    {
        return PerlCoreConstants.PLUGIN_ID;
    }

    @Override public String getMarkerType()
    {
        return PerlProblemFactory.PROBLEM;
    }
}

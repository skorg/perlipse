package org.scriptkitty.perlipse.internal.core;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.scriptkitty.perlipse.core.PerlCoreConstants;

public enum PerlProblemIdentifier implements IProblemIdentifier
{
    BUILD;
    
    @Override public String contributor()
    {
        return PerlCoreConstants.PLUGIN_ID;
    }
}

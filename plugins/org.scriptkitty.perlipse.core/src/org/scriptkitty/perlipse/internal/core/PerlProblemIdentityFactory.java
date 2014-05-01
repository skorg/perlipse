package org.scriptkitty.perlipse.internal.core;

import org.eclipse.dltk.compiler.problem.IProblemIdentifier;
import org.eclipse.dltk.compiler.problem.IProblemIdentifierFactory;

public class PerlProblemIdentityFactory implements IProblemIdentifierFactory 
{
    /*
     * @see org.eclipse.dltk.compiler.problem.IProblemIdentifierFactory#valueOf(java.lang.String)
     */
    @Override public IProblemIdentifier valueOf(String localName) throws IllegalArgumentException
    {
        return PerlProblemIdentifier.valueOf(localName);
    }

    /*
     * @see org.eclipse.dltk.compiler.problem.IProblemIdentifierFactory#values()
     */
    @Override public IProblemIdentifier[] values()
    {
        return PerlProblemIdentifier.values();
    }
}

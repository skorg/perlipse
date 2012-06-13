package org.scriptkitty.perlipse.internal.parser;

import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.eclipse.dltk.core.AbstractSourceElementParser;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlSourceElementParser extends AbstractSourceElementParser
{
    /*
     * @see org.eclipse.dltk.core.AbstractSourceElementParser#createVisitor()
     */
    @Override protected SourceElementRequestVisitor createVisitor()
    {
        return new PerlSourceElementRequestVisitor(getRequestor());
    }

    /*
     * @see org.eclipse.dltk.core.AbstractSourceElementParser#getNatureId()
     */
    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

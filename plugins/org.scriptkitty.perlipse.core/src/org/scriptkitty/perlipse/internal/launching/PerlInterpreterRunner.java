package org.scriptkitty.perlipse.internal.launching;

import org.eclipse.dltk.launching.AbstractInterpreterRunner;
import org.eclipse.dltk.launching.IInterpreterInstall;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlInterpreterRunner extends AbstractInterpreterRunner
{
    public PerlInterpreterRunner(IInterpreterInstall install)
    {
        super(install);
    }

    /*
     * @see org.eclipse.dltk.launching.AbstractInterpreterRunner#getProcessType()
     */
    @Override protected String getProcessType()
    {
        return PerlCoreConstants.PERLIPSE_PROCESS_TYPE;
    }
}

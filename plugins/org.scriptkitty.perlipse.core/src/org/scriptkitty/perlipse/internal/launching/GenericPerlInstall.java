package org.scriptkitty.perlipse.internal.launching;

import org.eclipse.debug.core.ILaunchManager;

import org.eclipse.dltk.launching.AbstractInterpreterInstall;
import org.eclipse.dltk.launching.IInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterRunner;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class GenericPerlInstall extends AbstractInterpreterInstall
{
    public GenericPerlInstall(IInterpreterInstallType type, String id)
    {
        super(type, id);
    }

    /*
     * @see org.eclipse.dltk.launching.IInterpreterInstall#getInterpreterRunner(java.lang.String,
     * org.eclipse.core.resources.IProject)
     */
    @Override public IInterpreterRunner getInterpreterRunner(String mode)
    {
        if (ILaunchManager.RUN_MODE.equals(mode))
        {
            return new PerlInterpreterRunner(this);
        }

        /*
         * TODO: profiling?
         *
         * this is relatively easy - Devel::DProf needs to be installed, and perl invoked w/ '-d:DProf'
         */

        return super.getInterpreterRunner(mode);
    }

    /*
     * @see org.eclipse.dltk.launching.IInterpreterInstall#getNatureId()
     */
    @Override public String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

package org.scriptkitty.perlipse.internal.launching;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IPath;

import org.eclipse.dltk.core.environment.IDeployment;
import org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType;
import org.eclipse.dltk.launching.IInterpreterInstall;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;

import java.io.IOException;


public class GenericPerlInstallType extends AbstractInterpreterInstallType
{
    private static String[] interpreterNames = { "perl" };

    /*
     * @see org.eclipse.dltk.launching.IInterpreterInstallType#getName()
     */
    public String getName()
    {
        return "Generic Perl Install";
    }

    /*
     * @see org.eclipse.dltk.launching.IInterpreterInstallType#getNatureId()
     */
    public String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    /*
     * @see
     * org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType#createPathFile(org.eclipse.dltk.core.environment.IDeployment)
     */
    @Override protected IPath createPathFile(IDeployment deployment) throws IOException
    {
        return deployment.add(PerlCorePlugin.getPlugin().getBundle(), "scripts/path.pl");
    }

    /*
     * @see
     * org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType#doCreateInterpreterInstall(java.lang.String)
     */
    @Override protected IInterpreterInstall doCreateInterpreterInstall(String id)
    {
        return new GenericPerlInstall(this, id);
    }

    /*
     * @see org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType#getLog()
     */
    @Override protected ILog getLog()
    {
        return PerlCorePlugin.getPlugin().getLog();
    }

    /*
     * @see org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType#getPluginId()
     */
    @Override protected String getPluginId()
    {
        return PerlCoreConstants.PLUGIN_ID;
    }

    /*
     * @see org.eclipse.dltk.internal.launching.AbstractInterpreterInstallType#getPossibleInterpreterNames()
     */
    @Override protected String[] getPossibleInterpreterNames()
    {
        return interpreterNames;
    }
}

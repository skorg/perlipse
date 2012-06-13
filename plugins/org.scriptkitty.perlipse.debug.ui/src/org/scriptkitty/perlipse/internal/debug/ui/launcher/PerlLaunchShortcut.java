package org.scriptkitty.perlipse.internal.debug.ui.launcher;

import org.eclipse.debug.core.ILaunchConfigurationType;

import org.eclipse.dltk.internal.debug.ui.launcher.AbstractScriptLaunchShortcut;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


public class PerlLaunchShortcut extends AbstractScriptLaunchShortcut
{
    //~ Methods

    @Override protected ILaunchConfigurationType getConfigurationType()
    {
        return getLaunchManager().getLaunchConfigurationType(PerlCoreConstants.PERLIPSE_SCRIPT);
    }

    @Override protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

package org.scriptkitty.perlipse.launching;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;

import org.eclipse.debug.core.ILaunch;
import org.eclipse.debug.core.ILaunchConfiguration;

import org.eclipse.dltk.core.IScriptProject;
import org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate;
import org.eclipse.dltk.launching.InterpreterConfig;

import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.core.util.PerlEnvironmentUtils;


public class PerlLaunchConfigurationDelegate extends AbstractScriptLaunchConfigurationDelegate
{
    /*
     * @see org.eclipse.dltk.launching.AbstractScriptLaunchConfigurationDelegate#getLanguageId()
     */
    @Override public String getLanguageId()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    @Override protected InterpreterConfig createInterpreterConfig(ILaunchConfiguration configuration, ILaunch launch)
        throws CoreException
    {
        InterpreterConfig config = super.createInterpreterConfig(configuration, launch);

        IPath[] paths = createBuildPath(configuration);

        IScriptProject project = getScriptProject(configuration);
        String incPath = PerlEnvironmentUtils.createBuildPath(paths, project.getProject());

        config.addInterpreterArg(incPath);

        return config;
    }
}

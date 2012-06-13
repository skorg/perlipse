package org.scriptkitty.perlipse.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;


/**
 */
public class PerlRemoteTabGroup extends AbstractLaunchConfigurationTabGroup
{
    //~ Methods

    /*
     * @see
     * org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
     * java.lang.String)
     */
    public void createTabs(ILaunchConfigurationDialog dialog, String mode)
    {
        ILaunchConfigurationTab[] tabs =
            new ILaunchConfigurationTab[]
            {
                new PerlRemoteLaunchConfigurationTab(mode),
                new CommonTab()
            };

        setTabs(tabs);
    }

}

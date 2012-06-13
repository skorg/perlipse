package org.scriptkitty.perlipse.internal.debug.ui.launchConfigurations;

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup;
import org.eclipse.debug.ui.CommonTab;
import org.eclipse.debug.ui.EnvironmentTab;
import org.eclipse.debug.ui.ILaunchConfigurationDialog;
import org.eclipse.debug.ui.ILaunchConfigurationTab;

import org.eclipse.dltk.debug.ui.launchConfigurations.ScriptArgumentsTab;

import org.scriptkitty.perlipse.internal.debug.ui.interpreters.PerlInterpreterTab;


/**
 */
public class PerlTabGroup extends AbstractLaunchConfigurationTabGroup
{
    //~ Methods

    /*
     * @see
     * org.eclipse.debug.ui.ILaunchConfigurationTabGroup#createTabs(org.eclipse.debug.ui.ILaunchConfigurationDialog,
     * java.lang.String)
     */
    public void createTabs(ILaunchConfigurationDialog dialog, String mode)
    {
    	PerlLaunchConfigurationTab main = new PerlLaunchConfigurationTab(mode);
    	
        ILaunchConfigurationTab[] tabs =
            new ILaunchConfigurationTab[]
            {
                main,
                new ScriptArgumentsTab(),
                new PerlInterpreterTab(main),
                new EnvironmentTab(),
                new CommonTab()
            };

        setTabs(tabs);
    }
}

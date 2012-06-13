package org.scriptkitty.perlipse.internal.ui.wizards;

import org.eclipse.dltk.ui.wizards.NewPackageCreationWizard;
import org.eclipse.dltk.ui.wizards.NewPackageWizardPage;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


/**
 * perl package wizard
 */
public class PerlPackageWizard extends NewPackageCreationWizard
{
    public static final String WIZARD_ID = "org.scriptkitty.perlipse.ui.wizards.NewPerlPackageCreationWizard";
    
    /*
     * @see org.eclipse.dltk.ui.wizards.NewPackageCreationWizard#createNewPackageWizardPage()
     */
    @Override
    protected NewPackageWizardPage createNewPackageWizardPage()
    {
        return new NewPackageWizardPage()
        {
            /*
             * @see org.eclipse.dltk.ui.wizards.NewContainerWizardPage#getRequiredNature()
             */
            @Override
            protected String getRequiredNature()
            {
                return PerlCoreConstants.NATURE_ID;
            }
        };
    }
}

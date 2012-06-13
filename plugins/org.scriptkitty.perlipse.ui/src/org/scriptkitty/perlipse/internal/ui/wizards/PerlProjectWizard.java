package org.scriptkitty.perlipse.internal.ui.wizards;

import org.eclipse.dltk.ui.DLTKUIPlugin;
import org.eclipse.dltk.ui.wizards.ProjectWizard;
import org.eclipse.dltk.ui.wizards.ProjectWizardFirstPage;
import org.eclipse.dltk.ui.wizards.ProjectWizardSecondPage;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.ui.PerlImages;


public class PerlProjectWizard extends ProjectWizard
{
    public static final String WIZARD_ID = "org.scriptkitty.perlipse.ui.wizards.PerlipseProjectWizard";

    public PerlProjectWizard()
    {
        setDialogSettings(DLTKUIPlugin.getDefault().getDialogSettings());
        setWindowTitle(PerlWizardMessages.NewProjectWizard_title);
        setDefaultPageImageDescriptor(PerlImages.DESC_WIZBAN_PROJECT_CREATION);
        setDefaultPageImageDescriptor(PerlImages.DESC_WIZBAN_PROJECT_CREATION);
    }

    /*
     * @see org.eclipse.jface.wizard.Wizard#addPages()
     */
    @Override public void addPages()
    {
        super.addPages();

        ProjectWizardFirstPage firstPage = new ProjectWizardFirstPage()
        {
            @Override protected boolean interpeterRequired()
            {
                return true;
            }
        };

        firstPage.setTitle(PerlWizardMessages.NewProjectFirstPage_title);
        firstPage.setDescription(PerlWizardMessages.NewProjectFirstPage_description);

        addPage(firstPage);

        ProjectWizardSecondPage secondPage = new ProjectWizardSecondPage(firstPage);
        addPage(secondPage);
    }

    /*
     * @see org.eclipse.dltk.ui.wizards.ProjectWizard#getScriptNature()
     */
    @Override public String getScriptNature()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

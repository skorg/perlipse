package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage;
import org.eclipse.dltk.ui.util.BusyIndicatorRunnableContext;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;

import org.eclipse.ui.IWorkbenchPropertyPage;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

import org.scriptkitty.perlipse.core.PerlLanguageToolkit;


public class PerlBuildPathPropertyPage extends BuildPathsPropertyPage implements IWorkbenchPropertyPage
{
    public IDLTKLanguageToolkit getLanguageToolkit()
    {
        return PerlLanguageToolkit.getInstance();
    }

    /*
     * @see
     * org.eclipse.dltk.ui.preferences.BuildPathsPropertyPage#createBuildPathBlock(org.eclipse.ui.preferences.IWorkbenchPreferenceContainer)
     */
    @Override protected BuildpathsBlock createBuildPathBlock(IWorkbenchPreferenceContainer pageContainer)
    {
        return new PerlBuildPathBlock(new BusyIndicatorRunnableContext(),
            this, getSettings().getInt(INDEX), false, pageContainer);
    }
}

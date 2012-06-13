package org.scriptkitty.perlipse.internal.ui.preferences;

import org.eclipse.dltk.ui.util.IStatusChangeListener;
import org.eclipse.dltk.ui.wizards.BuildpathsBlock;

import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.preference.IPreferenceStore;

import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


public class PerlBuildPathBlock extends BuildpathsBlock
{
    public PerlBuildPathBlock(IRunnableContext runnableContext, IStatusChangeListener context, int pageToShow,
        boolean useNewPage, IWorkbenchPreferenceContainer pageContainer)
    {
        super(runnableContext, context, pageToShow, useNewPage, pageContainer);
    }

    @Override protected IPreferenceStore getPreferenceStore()
    {
        return PerlUIPlugin.getPlugin().getPreferenceStore();
    }

    @Override protected boolean supportZips()
    {
        return false;
    }
}

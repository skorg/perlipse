package org.scriptkitty.perlipse.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterComboBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterContainerWizardPage;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.internal.debug.ui.preferences.PerlInterpreterPreferencePage;


/**
 */
public class PerlInterpreterContainerWizardPage extends AbstractInterpreterContainerWizardPage
{
    //~ Methods

    @Override public String getScriptNature()
    {
        return PerlCoreConstants.NATURE_ID;
    }

    /*
     * @see org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterContainerWizardPage#getInterpreterBlock()
     */
    protected AbstractInterpreterComboBlock getInterpreterBlock()
    {
        return new PerlInterpreterPreferencePage.PerlInterpreterComboBlock(null);
    }
}

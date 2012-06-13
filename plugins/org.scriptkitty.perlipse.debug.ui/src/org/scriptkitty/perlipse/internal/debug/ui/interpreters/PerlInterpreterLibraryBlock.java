package org.scriptkitty.perlipse.internal.debug.ui.interpreters;

import org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock;
import org.eclipse.dltk.internal.debug.ui.interpreters.AddScriptInterpreterDialog;
import org.eclipse.dltk.internal.debug.ui.interpreters.LibraryLabelProvider;

import org.eclipse.jface.viewers.IBaseLabelProvider;


public class PerlInterpreterLibraryBlock extends AbstractInterpreterLibraryBlock
{
    //~ Constructors

    public PerlInterpreterLibraryBlock(AddScriptInterpreterDialog dialog)
    {
        super(dialog);
    }

    //~ Methods

    /*
     * @see org.eclipse.dltk.internal.debug.ui.interpreters.AbstractInterpreterLibraryBlock#getLabelProvider()
     */
    @Override protected IBaseLabelProvider getLabelProvider()
    {
        return new LibraryLabelProvider();
    }
}

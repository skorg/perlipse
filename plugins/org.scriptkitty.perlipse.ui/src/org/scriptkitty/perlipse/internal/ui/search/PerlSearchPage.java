package org.scriptkitty.perlipse.internal.ui.search;

import org.eclipse.dltk.core.IDLTKLanguageToolkit;
import org.eclipse.dltk.ui.search.ScriptSearchPage;

import org.scriptkitty.perlipse.core.PerlLanguageToolkit;


/**
 * perl search page
 */
public class PerlSearchPage extends ScriptSearchPage
{
    //~ Methods

    /*
     * @see org.eclipse.dltk.ui.search.ScriptSearchPage#getLanguageToolkit()
     */
    @Override protected IDLTKLanguageToolkit getLanguageToolkit()
    {
        return PerlLanguageToolkit.getInstance();
    }
}

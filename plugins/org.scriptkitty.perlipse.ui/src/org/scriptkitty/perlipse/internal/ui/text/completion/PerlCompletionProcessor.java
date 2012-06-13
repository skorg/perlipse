package org.scriptkitty.perlipse.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.ui.IEditorPart;
import org.scriptkitty.perlipse.core.PerlCoreConstants;


/**
 * perl completion processor
 */
public class PerlCompletionProcessor extends ScriptCompletionProcessor
{
    //~ Constructors

    public PerlCompletionProcessor(IEditorPart editor, ContentAssistant assistant, String partition)
    {
        super(editor, assistant, partition);
    }

    //~ Methods

    /*
     * @see org.eclipse.dltk.ui.text.completion.ScriptCompletionProcessor#getNatureId()
     */
    @Override
    protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

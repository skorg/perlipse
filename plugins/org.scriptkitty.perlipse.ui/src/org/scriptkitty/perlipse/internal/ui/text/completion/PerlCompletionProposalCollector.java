package org.scriptkitty.perlipse.internal.ui.text.completion;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.swt.graphics.Image;
import org.scriptkitty.perlipse.core.PerlCoreConstants;

public class PerlCompletionProposalCollector extends ScriptCompletionProposalCollector
{
    private static final char[] TRIGGERS = new char[] {};

    public PerlCompletionProposalCollector(ISourceModule module)
    {
        super(module);
    }

    @Override
    protected ScriptCompletionProposal createScriptCompletionProposal(String completion,
            int replaceStart, int length, Image image, String displayString, int i, boolean isInDoc)
    {
        return new PerlCompletionProposal(completion, replaceStart, length, image, displayString,
                i, isInDoc);
    }

    @Override
    protected char[] getVarTrigger()
    {
        return TRIGGERS;
    }

    @Override
    protected String getNatureId()
    {
        return PerlCoreConstants.NATURE_ID;
    }
}

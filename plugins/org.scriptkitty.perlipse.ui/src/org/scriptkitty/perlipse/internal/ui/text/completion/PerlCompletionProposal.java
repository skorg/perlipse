package org.scriptkitty.perlipse.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposal;

import org.eclipse.swt.graphics.Image;


public class PerlCompletionProposal extends ScriptCompletionProposal
{
    //~ Constructors

    public PerlCompletionProposal(String replacementString, int replacementOffset, int replacementLength, Image image,
        String displayString, int relevance)
    {
        super(replacementString, replacementOffset, replacementLength, image,
            displayString, relevance);
    }

    public PerlCompletionProposal(String replacementString, int replacementOffset, int replacementLength, Image image,
        String displayString, int relevance, boolean isInDoc)
    {
        super(replacementString, replacementOffset, replacementLength, image,
            displayString, relevance, isInDoc);
    }

    //~ Methods

    @Override protected boolean isSmartTrigger(char trigger)
    {
        // TODO Auto-generated method stub
        return false;
    }
}

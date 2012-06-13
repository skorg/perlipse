package org.scriptkitty.perlipse.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalCollector;
import org.eclipse.dltk.ui.text.completion.ScriptCompletionProposalComputer;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;

import org.eclipse.jface.text.templates.TemplateCompletionProcessor;

import org.scriptkitty.perlipse.internal.ui.templates.PerlTemplateCompletionProcessor;


public class PerlTypeCompletionProposalComputer extends ScriptCompletionProposalComputer
{
    //~ Methods

    @Override protected ScriptCompletionProposalCollector createCollector(ScriptContentAssistInvocationContext context)
    {
        return new PerlCompletionProposalCollector(context.getSourceModule());
    }

    @Override protected TemplateCompletionProcessor createTemplateProposalComputer(
        ScriptContentAssistInvocationContext context)
    {
        return new PerlTemplateCompletionProcessor(context);
    }
}

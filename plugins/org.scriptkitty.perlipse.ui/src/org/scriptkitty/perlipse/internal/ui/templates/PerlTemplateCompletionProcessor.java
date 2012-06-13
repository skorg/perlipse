package org.scriptkitty.perlipse.internal.ui.templates;

import org.eclipse.dltk.ui.templates.ScriptTemplateAccess;
import org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor;
import org.eclipse.dltk.ui.text.completion.ScriptContentAssistInvocationContext;


public class PerlTemplateCompletionProcessor extends ScriptTemplateCompletionProcessor
{
    //~ Static fields/initializers

    // TODO: addd additional ignore chars
    private static char[] IGNORE = new char[] { '>', ':' };

    //~ Constructors

    public PerlTemplateCompletionProcessor(ScriptContentAssistInvocationContext context)
    {
        super(context);
    }

    //~ Methods

    /*
     * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getContextTypeId()
     */
    @Override protected String getContextTypeId()
    {
        return PerlUniversalTemplateContextType.TPL_CONTEXT_TYPE_ID;
    }

    @Override protected char[] getIgnore()
    {
        return IGNORE;
    }

    /*
     * @see org.eclipse.dltk.ui.templates.ScriptTemplateCompletionProcessor#getTemplateAccess()
     */
    @Override protected ScriptTemplateAccess getTemplateAccess()
    {
        return PerlTemplateAccess.getInstance();
    }
}

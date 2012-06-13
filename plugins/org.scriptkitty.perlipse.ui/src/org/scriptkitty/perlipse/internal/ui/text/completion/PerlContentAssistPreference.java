package org.scriptkitty.perlipse.internal.ui.text.completion;

import org.eclipse.dltk.ui.text.ScriptTextTools;
import org.eclipse.dltk.ui.text.completion.ContentAssistPreference;

import org.scriptkitty.perlipse.internal.ui.PerlUIPlugin;


public class PerlContentAssistPreference extends ContentAssistPreference
{
    //~ Static fields/initializers

    private static PerlContentAssistPreference self;

    //~ Methods

    public static ContentAssistPreference getInstance()
    {
        if (self == null)
        {
            self = new PerlContentAssistPreference();
        }

        return self;
    }

    @Override protected ScriptTextTools getTextTools()
    {
        return PerlUIPlugin.getPlugin().getTextTools();
    }
}

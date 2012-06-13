package org.scriptkitty.perlipse.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.templates.TemplateContextType;


/**
 * perl template context
 */
public class PerlTemplateContext extends ScriptTemplateContext
{
    //~ Constructors

    protected PerlTemplateContext(TemplateContextType type, IDocument document, int completionOffset,
        int completionLength, ISourceModule sourceModule)
    {
        super(type, document, completionOffset, completionLength, sourceModule);
    }
}

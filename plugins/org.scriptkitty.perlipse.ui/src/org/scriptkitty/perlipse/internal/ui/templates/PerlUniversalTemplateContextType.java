package org.scriptkitty.perlipse.internal.ui.templates;

import org.eclipse.dltk.core.ISourceModule;
import org.eclipse.dltk.ui.templates.ScriptTemplateContext;
import org.eclipse.dltk.ui.templates.ScriptTemplateContextType;

import org.eclipse.jface.text.IDocument;


public class PerlUniversalTemplateContextType extends ScriptTemplateContextType
{
    //~ Static fields/initializers

    public static String TPL_CONTEXT_TYPE_ID = "perlUniversalTemplateContextType";

    //~ Constructors

    public PerlUniversalTemplateContextType()
    {
        // empty constructor
    }

    public PerlUniversalTemplateContextType(String id)
    {
        super(id);
    }

    public PerlUniversalTemplateContextType(String id, String name)
    {
        super(id, name);
    }

    //~ Methods

    @Override public ScriptTemplateContext createContext(IDocument document, int completionPosition, int length,
        ISourceModule sourceModule)
    {
        return new PerlTemplateContext(this, document, completionPosition, length, sourceModule);
    }
}

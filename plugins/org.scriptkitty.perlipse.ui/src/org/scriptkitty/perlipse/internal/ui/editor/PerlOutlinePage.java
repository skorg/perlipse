package org.scriptkitty.perlipse.internal.ui.editor;

import org.eclipse.dltk.internal.ui.editor.ScriptEditor;
import org.eclipse.dltk.internal.ui.editor.ScriptOutlinePage;
import org.eclipse.jface.preference.IPreferenceStore;

public class PerlOutlinePage extends ScriptOutlinePage
{
    public PerlOutlinePage(ScriptEditor editor, IPreferenceStore store)
    {
        super(editor, store);
    }
}

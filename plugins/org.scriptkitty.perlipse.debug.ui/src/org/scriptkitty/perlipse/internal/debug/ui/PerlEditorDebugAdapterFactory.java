package org.scriptkitty.perlipse.internal.debug.ui;

import org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;


/**
 * editor debug adapter factory
 */
public class PerlEditorDebugAdapterFactory extends ScriptEditorDebugAdapterFactory
{
    /*
     * @see org.eclipse.dltk.debug.ui.ScriptEditorDebugAdapterFactory#getBreakointAdapter()
     */
    @Override protected ScriptToggleBreakpointAdapter getBreakpointAdapter()
    {
        return new PerlToggleBreakpointAdapter();
    }
}

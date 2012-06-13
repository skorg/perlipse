package org.scriptkitty.perlipse.internal.debug.ui;

import org.eclipse.core.runtime.CoreException;

import org.eclipse.dltk.debug.ui.breakpoints.IScriptBreakpointLineValidator;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptBreakpointLineValidatorFactory;
import org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter;

import org.eclipse.jface.viewers.ISelection;

import org.eclipse.ui.IWorkbenchPart;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


/**
 * toggle breakpoints
 */
public class PerlToggleBreakpointAdapter extends ScriptToggleBreakpointAdapter
{
    private static final IScriptBreakpointLineValidator validator = ScriptBreakpointLineValidatorFactory
        .createNonEmptyNoCommentValidator("#");

    /*
     * @see
     * org.eclipse.debug.ui.actions.IToggleBreakpointsTargetExtension#canToggleBreakpoints(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    public boolean canToggleBreakpoints(IWorkbenchPart part, ISelection selection)
    {
        return canToggleLineBreakpoints(part, selection);
    }

    /*
     * @see
     * org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#canToggleMethodBreakpoints(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    public boolean canToggleMethodBreakpoints(IWorkbenchPart part, ISelection selection)
    {
        return false;
    }

    /*
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#canToggleWatchpoints(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    public boolean canToggleWatchpoints(IWorkbenchPart part, ISelection selection)
    {
        return false;
    }

    /*
     * @see
     * org.eclipse.debug.ui.actions.IToggleBreakpointsTargetExtension#toggleBreakpoints(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    public void toggleBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException
    {
        toggleLineBreakpoints(part, selection);
    }

    /*
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#toggleMethodBreakpoints(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    public void toggleMethodBreakpoints(IWorkbenchPart part, ISelection selection) throws CoreException
    {
        // TODO: toggle method breakpoints?
    }

    /*
     * @see org.eclipse.debug.ui.actions.IToggleBreakpointsTarget#toggleWatchpoints(org.eclipse.ui.IWorkbenchPart,
     * org.eclipse.jface.viewers.ISelection)
     */
    public void toggleWatchpoints(IWorkbenchPart part, ISelection selection) throws CoreException
    {
        // TODO: toggle watchpoints?
    }

    /*
     * @see org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter#getDebugModelId()
     */
    @Override protected String getDebugModelId()
    {
        return PerlCoreConstants.DEBUG_MODEL_ID;
    }

    /*
     * @see org.eclipse.dltk.debug.ui.breakpoints.ScriptToggleBreakpointAdapter#getValidator()
     */
    @Override protected IScriptBreakpointLineValidator getValidator()
    {
        return validator;
    }
}

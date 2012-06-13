package org.scriptkitty.perlipse.internal.debug.ui;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;

import org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation;

import org.eclipse.ui.IEditorInput;


import org.scriptkitty.perlipse.core.PerlCoreConstants;
import org.scriptkitty.perlipse.debug.PerlVariableFormatter;


public class PerlDebugModelPresentation extends ScriptDebugModelPresentation
{
    /*
     * @see org.eclipse.dltk.debug.ui.ScriptDebugModelPresentation#getEditorId(org.eclipse.ui.IEditorInput,
     * java.lang.Object)
     */
    @Override public String getEditorId(IEditorInput input, Object element)
    {
        return PerlCoreConstants.EDITOR_ID;
    }

    @Override public String getVariableName(IVariable variable) throws DebugException
    {
        String name = super.getVariableName(variable);

        name = PerlVariableFormatter.stripDashArrow(name);
        name = PerlVariableFormatter.stripCurlies(name);

        return name;
    }
}

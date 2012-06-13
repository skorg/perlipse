package org.scriptkitty.perlipse.internal.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;

import org.eclipse.dltk.debug.core.model.CollectionScriptType;
import org.eclipse.dltk.debug.core.model.IScriptValue;


import org.scriptkitty.perlipse.core.util.syntax.PerlVarSyntaxUtils;
import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;


public abstract class PerlCollectionType extends CollectionScriptType
{
    private boolean isReference;

    protected PerlCollectionType(String name)
    {
        super(name);
    }

    /*
     * @see
     * org.eclipse.dltk.debug.core.model.CollectionScriptType#formatDetails(org.eclipse.dltk.debug.core.model.IScriptValue)
     */
    @Override public String formatDetails(IScriptValue value)
    {
        isReference = PerlVarSyntaxUtils.isScalarVariableStartCharacter(value.getEvalName().charAt(0));

        StringBuffer buffer = new StringBuffer();

        try
        {
            IVariable[] variables = value.getVariables();

            if (variables.length > 0)
            {
                buffer.append(getOpenBrace());

                for (IVariable variable : variables)
                {
                    buffer.append(buildDetailString(variable));
                    buffer.append(", ");
                }

                if (buffer.length() > 1)
                {
                    // chop off the last ', '
                    buffer.setLength(buffer.length() - 2);
                }

                buffer.append(getCloseBrace());
            }
        }
        catch (DebugException e)
        {
            buffer.setLength(0);
            PerlCorePlugin.error("unable to format details for variable [" + getName() + "]", e);
        }

        return buffer.toString();
    }

    @Override protected char getCloseBrace()
    {
        if (isReference)
        {
            return super.getCloseBrace();
        }

        return PerlVarSyntaxUtils.CLOSE_PAREN;
    }

    @Override protected char getOpenBrace()
    {
        if (isReference)
        {
            return super.getOpenBrace();
        }

        return PerlVarSyntaxUtils.OPEN_PAREN;
    }

}

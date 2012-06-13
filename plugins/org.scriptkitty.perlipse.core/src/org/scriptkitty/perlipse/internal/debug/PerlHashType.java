package org.scriptkitty.perlipse.internal.debug;

import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IVariable;
import org.scriptkitty.perlipse.debug.PerlVariableFormatter;


public class PerlHashType extends PerlCollectionType
{
    public PerlHashType(String type)
    {
        super(type);
    }
    
    /*
     * @see org.eclipse.dltk.debug.core.model.HashScriptType#buildDetailString(org.eclipse.debug.core.model.IVariable)
     */
    @Override protected String buildDetailString(IVariable variable) throws DebugException
    {
        StringBuffer buffer = new StringBuffer();

        buffer.append(getVariableName(variable));
        buffer.append(" => ");
        buffer.append(variable.getValue().getValueString());

        return buffer.toString();
    }
    
    private String getVariableName(IVariable variable) throws DebugException
    {
        return PerlVariableFormatter.stripCurlies(variable.getName());
    }
}
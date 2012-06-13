package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.SimpleReference;


public class PerlBuiltinCallStatement extends CallExpression
{
    public PerlBuiltinCallStatement(int start, SimpleReference method, CallArgumentsList args)
    {
        super(start, 0, null, method, args);
    }
}

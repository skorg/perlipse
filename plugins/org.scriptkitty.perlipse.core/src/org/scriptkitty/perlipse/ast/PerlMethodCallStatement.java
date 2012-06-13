package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.expressions.CallArgumentsList;
import org.eclipse.dltk.ast.expressions.CallExpression;
import org.eclipse.dltk.ast.references.SimpleReference;

public class PerlMethodCallStatement extends CallExpression
{
    public PerlMethodCallStatement(int start, ASTNode clazz, SimpleReference method, CallArgumentsList args)
    {
        super(start, 0, clazz, method, args);
    }    
}

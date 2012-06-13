package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;

public class PerlTerminatorStatement extends Statement
{
    public PerlTerminatorStatement(int declStart)
    {
        super(declStart, 0);
    }

    /*
     * @see org.eclipse.dltk.ast.statements.Statement#getKind()
     */
    @Override public int getKind()
    {
        return 0;
    }
    
    @Override public void printNode(CorePrinter output)
    {
        output.formatPrintLn("Terminator" + getSourceRange() + " 1;");
    }
    
    /*
     * @see org.eclipse.dltk.ast.statements.Statement#traverse(org.eclipse.dltk.ast.ASTVisitor)
     */
    @Override public void traverse(ASTVisitor visitor) throws Exception 
    {
        visitor.visit(this);
        visitor.endvisit(this);
    }
}

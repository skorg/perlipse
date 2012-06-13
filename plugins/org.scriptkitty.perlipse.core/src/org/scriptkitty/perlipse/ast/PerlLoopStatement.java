package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.utils.CorePrinter;


public abstract class PerlLoopStatement extends Statement
{
    private Block body;
    private Block cont;

    // TODO: add 'conditional'

    protected PerlLoopStatement(int start, int end)
    {
        super(start, end);
    }

    @Override public void printNode(CorePrinter output)
    {
        output.formatPrintLn(getKeyword() + getSourceRange());

        if (body != null)
        {
            body.printNode(output);
            output.formatPrint("");
        }
        
        if (cont != null)
        {
            output.formatPrintLn("continue");
            cont.printNode(output);
        }
        
        output.formatPrint("");
    }

    public void setBody(Block body)
    {
        this.body = body;
    }

    public void setConditional(Statement stmt)
    {
        // TODO: implement this...
    }

    public void setContinue(Block cont)
    {
        this.cont = cont;
    }

    @Override public void traverse(ASTVisitor visitor) throws Exception
    {
        if (visitor.visit(this))
        {
            if (body != null)
            {
                body.traverse(visitor);
            }

            if (cont != null)
            {
                cont.traverse(visitor);
            }

            visitor.endvisit(this);
        }
    }

    protected abstract String getKeyword();
}

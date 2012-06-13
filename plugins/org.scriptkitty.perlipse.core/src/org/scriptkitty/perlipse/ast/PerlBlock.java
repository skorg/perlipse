package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.statements.Block;
import org.eclipse.dltk.utils.CorePrinter;

public class PerlBlock extends Block
{
    public PerlBlock(int start, int end)
    {
        super(start, end);
    }

    @Override public void printNode(CorePrinter output)
    {
        output.print("\n\t\t");
        super.printNode(output);
    }
}

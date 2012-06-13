package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.declarations.MethodDeclaration;

public class PerlSubDeclaration extends MethodDeclaration
{
    public PerlSubDeclaration(int declStart, String name, int nameStart, int nameEnd)
    {
        super(name, nameStart, nameEnd, declStart, 0);
    }
    
    public boolean isNaked()
    {
        return (getName() == null);
    }
}

package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.declarations.FieldDeclaration;


public class PerlPkgVarDeclaration extends FieldDeclaration
{
    public PerlPkgVarDeclaration(int declStart, String name, int nameStart)
    {
        super(name, nameStart, nameStart + ((name == null) ? 0 : name.length()), declStart, 0);
    }
    
    public boolean isNaked()
    {
        return (getName() == null);
    }
}

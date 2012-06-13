package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.TypeDeclaration;
import org.eclipse.dltk.ast.statements.Block;

public class PerlPackageDeclaration extends TypeDeclaration
{
    private static final String main = "main";
        
    public PerlPackageDeclaration(int declStart, String name, int nameStart, int nameEnd)
    {
        super(name, nameStart, nameEnd, declStart, 0);

        // not sure why this isn't implicitly done...
        setBody(new Block());        
        setEnclosingTypeName(name);
    }
    
    public void addStatement(ASTNode stmt)
    {
        getBody().addStatement(stmt);
    }
    
    public boolean isMainPackage()
    {
        return getName().equals(main);
    }
    
    public static PerlPackageDeclaration createMainPackage()
    {
        return new PerlPackageDeclaration(0, main, 0, 0);
    }    
}

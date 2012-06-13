package org.scriptkitty.perlipse.ast;

import org.eclipse.dltk.ast.ASTVisitor;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.core.ISourceRange;
import org.eclipse.dltk.core.SourceRange;
import org.eclipse.dltk.utils.CorePrinter;

public class PerlIncludeStatement extends Statement
{
    public static final int USE = 1;
    public static final int NO = 2;
    public static final int REQUIRE = 3;
    
    private String name;
    private int nameStart;
    
    private String version;
    
    private int kind;
    
    public PerlIncludeStatement(int declStart, String name, int nameStart)
    {
        super(declStart, 0);
        
        this.name = name;
        this.nameStart = nameStart;
    }
    
    /*
     * @see org.eclipse.dltk.ast.statements.Statement#getKind()
     */
    @Override public int getKind()
    {
        return kind;
    }
    
    public void setKind(int kind)
    {
        // meh, before java 1.5 was a requirement...
        this.kind = kind;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getNameEnd()
    {
        return nameStart + name.length();
    }
    
    public int getNameStart()
    {
        return nameStart;
    }
    
    public String getVersion()
    {
        return version;
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    protected ISourceRange getNameSourceRange() 
    {
        return new SourceRange(getNameStart(), getNameEnd());
    }
    
    /*
     * @see org.eclipse.dltk.ast.ASTNode#printNode(org.eclipse.dltk.utils.CorePrinter)
     */
    @Override public void printNode(CorePrinter output)
    {
        output.formatPrintLn(getType() + getSourceRange() + getNameSourceRange() + ": " + name);
    }
    
    @Override public void traverse(ASTVisitor visitor) throws Exception 
    {
        visitor.visit(this);
        visitor.endvisit(this);
    }

    public boolean isNaked()
    {
        return (getName() == null);
    }
    
    private String getType()
    {
        int kind = getKind();
        
        if (kind == USE)
        {
            return "use";
        }
        
        if (kind == NO)
        {
            return "no";
        }
        
        return "require";
    }
}

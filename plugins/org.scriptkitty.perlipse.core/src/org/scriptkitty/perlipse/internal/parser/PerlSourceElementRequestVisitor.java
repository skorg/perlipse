package org.scriptkitty.perlipse.internal.parser;

import org.eclipse.dltk.ast.declarations.MethodDeclaration;
import org.eclipse.dltk.ast.statements.Statement;
import org.eclipse.dltk.compiler.IElementRequestor.FieldInfo;
import org.eclipse.dltk.compiler.IElementRequestor.ImportInfo;
import org.eclipse.dltk.compiler.ISourceElementRequestor;
import org.eclipse.dltk.compiler.SourceElementRequestVisitor;
import org.scriptkitty.perlipse.ast.PerlIncludeStatement;
import org.scriptkitty.perlipse.ast.PerlPkgVarDeclaration;
import org.scriptkitty.perlipse.ast.PerlSubDeclaration;

public class PerlSourceElementRequestVisitor extends SourceElementRequestVisitor
{
    public PerlSourceElementRequestVisitor(ISourceElementRequestor requestor)
    {
        super(requestor);
    }

    @Override
    public boolean visit(Statement statement) throws Exception
    {
        if (statement instanceof PerlPkgVarDeclaration)
        {
            addVariable((PerlPkgVarDeclaration) statement);
        }
        
        if (statement instanceof PerlIncludeStatement)
        {
            addInclude((PerlIncludeStatement) statement);
        }

        return super.visit(statement);
    }

    // TODO: look at the ruby source element requestor
    
    private void addInclude(PerlIncludeStatement statement)
    {
        /*
         * TODO: add support for 'no' and 'require' statements
         * 
         * issues: 
         *   1) how to indicate? label - [u] strict; [n] warnings, image? 
         *   2) how to tell label provider what to do? leverage 'containerName'? subclass?
         */
        if (statement.isNaked() || statement.getKind() != PerlIncludeStatement.USE)
        {
            return;
        }
        
        ImportInfo importInfo = new ISourceElementRequestor.ImportInfo();
        
        importInfo.name = statement.getName();
        importInfo.sourceStart = statement.getNameStart();
        importInfo.sourceEnd = statement.getNameEnd() - 1;
        importInfo.version = statement.getVersion();

        importInfo.containerName = null;

        fRequestor.acceptImport(importInfo);
    }

    @Override public boolean visit(MethodDeclaration method) throws Exception
    {
        if (((PerlSubDeclaration) method).isNaked())
        {
            return false;
        }

        return super.visit(method);
    }

    private void addVariable(PerlPkgVarDeclaration statement)
    {
        FieldInfo fieldInfo = new ISourceElementRequestor.FieldInfo();

        fieldInfo.name = statement.getName();
        fieldInfo.declarationStart = statement.sourceStart();
        fieldInfo.nameSourceStart = statement.getNameStart();
        fieldInfo.nameSourceEnd = statement.getNameEnd() - 1;
        // TODO: add 'modifiers'

        // XXX: is this even correct?
        fRequestor.enterField(fieldInfo);
        fRequestor.exitField(statement.sourceEnd());
    }
}

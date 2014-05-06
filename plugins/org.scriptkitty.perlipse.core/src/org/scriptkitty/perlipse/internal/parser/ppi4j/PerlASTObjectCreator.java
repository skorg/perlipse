package org.scriptkitty.perlipse.internal.parser.ppi4j;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.references.SimpleReference;
import org.eclipse.dltk.ast.statements.Block;
import org.scriptkitty.perlipse.ast.PerlBlock;
import org.scriptkitty.perlipse.ast.PerlBuiltinCallStatement;
import org.scriptkitty.perlipse.ast.PerlForStatement;
import org.scriptkitty.perlipse.ast.PerlForeachStatement;
import org.scriptkitty.perlipse.ast.PerlIncludeStatement;
import org.scriptkitty.perlipse.ast.PerlLoopStatement;
import org.scriptkitty.perlipse.ast.PerlMethodCallStatement;
import org.scriptkitty.perlipse.ast.PerlPackageDeclaration;
import org.scriptkitty.perlipse.ast.PerlSubDeclaration;
import org.scriptkitty.perlipse.ast.PerlTerminatorStatement;
import org.scriptkitty.perlipse.ast.PerlUntilStatement;
import org.scriptkitty.perlipse.ast.PerlWhileStatement;
import org.scriptkitty.ppi4j.Token;
import org.scriptkitty.ppi4j.ast.IASTObjectCreator;
import org.scriptkitty.ppi4j.ast.container.BlockContainer;
import org.scriptkitty.ppi4j.ast.container.IncludeContainer;
import org.scriptkitty.ppi4j.ast.container.LoopContainer;
import org.scriptkitty.ppi4j.ast.container.ModuleContainer;
import org.scriptkitty.ppi4j.ast.container.PackageContainer;
import org.scriptkitty.ppi4j.ast.container.StatementContainer;
import org.scriptkitty.ppi4j.ast.container.SubContainer;
import org.scriptkitty.ppi4j.ast.container.TerminatorContainer;


public class PerlASTObjectCreator implements IASTObjectCreator
{
    private ModuleDeclaration declaration;

    public PerlASTObjectCreator(ModuleDeclaration declaration)
    {
        this.declaration = declaration;
    }

    @Override public BlockContainer<Block, ASTNode> createBlock(int start, boolean isBody)
    {
        if (isBody)
        {
            return new Containers.BlockContainer(new Block(start, 0));
        }

        return new Containers.BlockContainer(new PerlBlock(start, 0));
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#convertBuiltinCall(int, org.scriptkitty.ppi4j.Token)
     */
    @Override public StatementContainer<ASTNode, Void> createBuiltinCall(int start, Token bToken)
    {
        SimpleReference method = createSimpleReference(bToken);

        return new Containers.StatementContainer(new PerlBuiltinCallStatement(start, method, null));
    }

    @Override public LoopContainer<PerlLoopStatement, Block> createFor(int start)
    {
        return new Containers.LoopContainer(new PerlForStatement(start));
    }

    @Override public LoopContainer<PerlLoopStatement, Block> createForeach(int start)
    {
        return new Containers.LoopContainer(new PerlForeachStatement(start));
    }

    @Override public IncludeContainer<PerlIncludeStatement, Void> createInclude(int start, Token mToken)
    {
        return new Containers.IncludeContainer(new PerlIncludeStatement(start, mToken.getContent(),
                mToken.getStartOffset()));
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#createMainPackage()
     */
    @Override public PackageContainer<PerlPackageDeclaration, ASTNode> createMainPackage()
    {
        return new Containers.PackageContainer(PerlPackageDeclaration.createMainPackage());
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#createMethodCall(int, org.scriptkitty.ppi4j.Token, org.scriptkitty.ppi4j.Token)
     */
    @Override public StatementContainer<ASTNode, Void> createMethodCall(int start, Token cToken, Token mToken)
    {
        SimpleReference clazz = createSimpleReference(cToken);
        SimpleReference method = createSimpleReference(mToken);

        PerlMethodCallStatement stmt = new PerlMethodCallStatement(start, clazz, method, null);

        return new Containers.StatementContainer(stmt);
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#createModule()
     */
    @Override public ModuleContainer<ModuleDeclaration, PerlPackageDeclaration> createModule()
    {
        return new Containers.ModuleContainer(declaration);
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#createPackage(int, org.scriptkitty.ppi4j.Token)
     */
    @Override public PackageContainer<PerlPackageDeclaration, ASTNode> createPackage(int start, Token pName)
    {
        return new Containers.PackageContainer(new PerlPackageDeclaration(start, pName.getContent(),
                pName.getStartOffset(), pName.getEndOffset()));
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#createScheduled(int, org.scriptkitty.ppi4j.Token)
     */
    @Override public SubContainer<PerlSubDeclaration, Block> createScheduled(int start, Token sName)
    {
        // default here for now...
        return createSubroutine(start, sName);
    }

    /*
     * @see org.scriptkitty.ppi4j.ast.IASTObjectCreator#createSubroutine(int, org.scriptkitty.ppi4j.Token)
     */
    @Override public  SubContainer<PerlSubDeclaration, Block> createSubroutine(int start, Token sName)
    {
        return new Containers.SubContainer(new PerlSubDeclaration(start, sName.getContent(), sName.getStartOffset(),
                sName.getEndOffset()));
    }

    @Override public TerminatorContainer<PerlTerminatorStatement, Void> createTerminator(int start)
    {
        return new Containers.TerminatorContainer(new PerlTerminatorStatement(start));
    }

    @Override public LoopContainer<PerlLoopStatement, Block> createUntil(int start)
    {
        return new Containers.LoopContainer(new PerlUntilStatement(start));
    }

    @Override public LoopContainer<PerlLoopStatement, Block> createWhile(int start)
    {
        return new Containers.LoopContainer(new PerlWhileStatement(start));
    }

    private SimpleReference createSimpleReference(Token token)
    {
        return new SimpleReference(token.getStartOffset(), token.getEndOffset(), token.getContent());
    }
}

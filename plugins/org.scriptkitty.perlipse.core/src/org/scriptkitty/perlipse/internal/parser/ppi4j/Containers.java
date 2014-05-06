package org.scriptkitty.perlipse.internal.parser.ppi4j;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.statements.Block;
import org.scriptkitty.perlipse.ast.PerlIncludeStatement;
import org.scriptkitty.perlipse.ast.PerlLoopStatement;
import org.scriptkitty.perlipse.ast.PerlPackageDeclaration;
import org.scriptkitty.perlipse.ast.PerlSubDeclaration;
import org.scriptkitty.perlipse.ast.PerlTerminatorStatement;
import org.scriptkitty.ppi4j.Statement.Type;

final class Containers
{
    static class BlockContainer extends org.scriptkitty.ppi4j.ast.container.BlockContainer<Block, ASTNode>
    {
        protected BlockContainer(Block block)
        {
            super(block);
        }

        @Override public void add(ASTNode stmt)
        {
            get().addStatement(stmt);
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }
    }

    static class IncludeContainer extends org.scriptkitty.ppi4j.ast.container.IncludeContainer<PerlIncludeStatement, Void>
    {
        protected IncludeContainer(PerlIncludeStatement include)
        {
            super(include);
        }

        @Override public void add(Void stmt)
        {
            // TODO: implement me when 'use constant { FOO => 1 } is supported
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }

        @Override public void setModuleVersion(String moduleVersion)
        {
            get().setVersion(moduleVersion);
        }

        @Override public void setType(Type type)
        {
            PerlIncludeStatement stmt = get();
            
            if (type == Type.USE)
            {
                stmt.setKind(PerlIncludeStatement.USE);
            }
            else if (type == Type.NO)
            {
                stmt.setKind(PerlIncludeStatement.NO);
            }
            else if (type == Type.REQUIRE)
            {
                stmt.setKind(PerlIncludeStatement.REQUIRE);
            }
        }
    }

    static class LoopContainer extends org.scriptkitty.ppi4j.ast.container.LoopContainer<PerlLoopStatement, Block>
    {
        protected LoopContainer(PerlLoopStatement stmt)
        {
            super(stmt);
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }

        @Override protected void addBody(Block stmt)
        {
            get().setBody(stmt);
        }

        @Override protected void addConditional(Block stmt)
        {
            // TODO: get().setConditional(????)
        }

        @Override protected void addContinue(Block stmt)
        {
            get().setContinue(stmt);
        }
    }

    static class ModuleContainer extends org.scriptkitty.ppi4j.ast.container.ModuleContainer<ModuleDeclaration, PerlPackageDeclaration>
    {
        protected ModuleContainer(ModuleDeclaration declaration)
        {
            super(declaration);
        }

        @Override public void add(PerlPackageDeclaration stmt)
        {
            get().addStatement(stmt);
        }
    }

    static class PackageContainer extends org.scriptkitty.ppi4j.ast.container.PackageContainer<PerlPackageDeclaration, ASTNode>
    {
        protected PackageContainer(PerlPackageDeclaration pkg)
        {
            super(pkg);
        }

        @Override public void add(ASTNode stmt)
        {
            get().addStatement(stmt);
        }

        @Override public String getPackageName()
        {
            return get().getName();
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }
    }

    static class StatementContainer extends org.scriptkitty.ppi4j.ast.container.StatementContainer<ASTNode, Void>
    {
        protected StatementContainer(ASTNode stmt)
        {
            super(stmt);
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }
    }

    static class SubContainer extends org.scriptkitty.ppi4j.ast.container.SubContainer<PerlSubDeclaration, Block>
    {
        protected SubContainer(PerlSubDeclaration subroutine)
        {
            super(subroutine);
        }

        @Override public void add(Block stmt)
        {
            get().acceptBody(stmt);
        }

        @Override public void setDeclaringPackage(String pkgName)
        {
            get().setDeclaringTypeName(pkgName);
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }
    }

    static class TerminatorContainer extends org.scriptkitty.ppi4j.ast.container.TerminatorContainer<PerlTerminatorStatement, Void>
    {
        protected TerminatorContainer(PerlTerminatorStatement terminator)
        {
            super(terminator);
        }

        @Override public void setEnd(int offset)
        {
            get().setEnd(offset);
        }
    }
}

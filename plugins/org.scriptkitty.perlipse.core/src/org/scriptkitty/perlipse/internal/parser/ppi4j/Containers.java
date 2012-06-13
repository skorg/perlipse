package org.scriptkitty.perlipse.internal.parser.ppi4j;

import org.eclipse.dltk.ast.ASTNode;
import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.statements.Block;
import org.scriptkitty.ppi4j.Statement.Type;
import org.scriptkitty.perlipse.ast.PerlIncludeStatement;
import org.scriptkitty.perlipse.ast.PerlLoopStatement;
import org.scriptkitty.perlipse.ast.PerlPackageDeclaration;
import org.scriptkitty.perlipse.ast.PerlSubDeclaration;
import org.scriptkitty.perlipse.ast.PerlTerminatorStatement;

final class Containers
{
    static class BlockContainer extends org.scriptkitty.ppi4j.ast.state.BlockContainer
    {
        protected BlockContainer(Block block)
        {
            super(block);
        }

        @Override public void add(Object stmt)
        {
            ((Block) get()).addStatement((ASTNode) stmt);
        }

        @Override public void setEnd(int offset)
        {
            ((Block) get()).setEnd(offset);
        }
    }

    static class IncludeContainer extends org.scriptkitty.ppi4j.ast.state.IncludeContainer
    {
        protected IncludeContainer(PerlIncludeStatement include)
        {
            super(include);
        }

        @Override public void add(Object e)
        {
            // TODO: implement me when 'use constant { FOO => 1 } is supported
        }

        @Override public void setEnd(int offset)
        {
            ((PerlIncludeStatement) get()).setEnd(offset);
        }

        @Override public void setModuleVersion(String moduleVersion)
        {
            ((PerlIncludeStatement) get()).setVersion(moduleVersion);
        }

        @Override public void setType(Type type)
        {
            PerlIncludeStatement stmt = (PerlIncludeStatement) get();
            
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

    static class LoopContainer extends org.scriptkitty.ppi4j.ast.state.LoopContainer
    {
        protected LoopContainer(PerlLoopStatement stmt)
        {
            super(stmt);
        }

        @Override public void setEnd(int offset)
        {
            ((PerlLoopStatement) get()).setEnd(offset);
        }

        @Override protected void addBody(Object stmt)
        {
            ((PerlLoopStatement) get()).setBody((Block) stmt);
        }

        @Override protected void addConditional(Object stmt)
        {
            // TODO: ((PerlLoopStatement) get()).setConditional(????)
        }

        @Override protected void addContinue(Object stmt)
        {
            ((PerlLoopStatement) get()).setContinue((Block) stmt);
        }
    }

    static class ModuleContainer extends org.scriptkitty.ppi4j.ast.state.ModuleContainer
    {
        protected ModuleContainer(ModuleDeclaration declaration)
        {
            super(declaration);
        }

        @Override public void add(Object stmt)
        {
            ((ModuleDeclaration) get()).addStatement((ASTNode) stmt);
        }
    }

    static class PackageContainer extends org.scriptkitty.ppi4j.ast.state.PackageContainer
    {
        protected PackageContainer(PerlPackageDeclaration pkg)
        {
            super(pkg);
        }

        @Override public void add(Object stmt)
        {
            ((PerlPackageDeclaration) get()).addStatement((ASTNode) stmt);
        }

        @Override public String getPackageName()
        {
            return ((PerlPackageDeclaration) get()).getName();
        }

        @Override public void setEnd(int offset)
        {
            ((PerlPackageDeclaration) get()).setEnd(offset);
        }
    }

    static class StatementContainer extends org.scriptkitty.ppi4j.ast.state.StatementContainer
    {
        protected StatementContainer(ASTNode stmt)
        {
            super(stmt);
        }

        @Override public void setEnd(int offset)
        {
            ((ASTNode) get()).setEnd(offset);
        }
    }

    static class SubContainer extends org.scriptkitty.ppi4j.ast.state.SubContainer
    {
        protected SubContainer(PerlSubDeclaration subroutine)
        {
            super(subroutine);
        }

        @Override public void add(Object stmt)
        {
            ((PerlSubDeclaration) get()).acceptBody((Block) stmt);
        }

        @Override public void setDeclaringPackage(String pkgName)
        {
            ((PerlSubDeclaration) get()).setDeclaringTypeName(pkgName);
        }

        @Override public void setEnd(int offset)
        {
            ((PerlSubDeclaration) get()).setEnd(offset);
        }
    }

    static class TerminatorContainer extends org.scriptkitty.ppi4j.ast.state.TerminatorContainer
    {
        protected TerminatorContainer(PerlTerminatorStatement terminator)
        {
            super(terminator);
        }

        @Override public void setEnd(int offset)
        {
            ((ASTNode) get()).setEnd(offset);
        }
    }
}

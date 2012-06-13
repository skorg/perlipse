package org.scriptkitty.perlipse.internal.parser;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;

public class NullSourceParserFactory implements ISourceParserFactory
{
    public ISourceParser createSourceParser()
    {
        return new AbstractSourceParser()
        {
            public IModuleDeclaration parse(IModuleSource input, IProblemReporter reporter)
            {
                return new ModuleDeclaration(input.getSourceContents().length());
            }
        };
    }
}

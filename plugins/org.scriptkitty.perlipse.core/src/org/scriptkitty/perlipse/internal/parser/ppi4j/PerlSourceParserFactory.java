package org.scriptkitty.perlipse.internal.parser.ppi4j;

import org.eclipse.dltk.ast.declarations.ModuleDeclaration;
import org.eclipse.dltk.ast.parser.AbstractSourceParser;
import org.eclipse.dltk.ast.parser.IModuleDeclaration;
import org.eclipse.dltk.ast.parser.ISourceParser;
import org.eclipse.dltk.ast.parser.ISourceParserFactory;
import org.eclipse.dltk.compiler.env.IModuleSource;
import org.eclipse.dltk.compiler.problem.IProblemReporter;
import org.scriptkitty.perlipse.internal.core.PerlCorePlugin;
import org.scriptkitty.ppi4j.Document;
import org.scriptkitty.ppi4j.Token;
import org.scriptkitty.ppi4j.ast.ASTBuildVisitor;
import org.scriptkitty.ppi4j.exception.ParserException;
import org.scriptkitty.ppi4j.exception.TokenizerException;
import org.scriptkitty.ppi4j.parser.DefaultTokenProvider;
import org.scriptkitty.ppi4j.parser.Parser;
import org.scriptkitty.ppi4j.parser.ParserFactory;
import org.scriptkitty.ppi4j.util.IErrorProxy;


public class PerlSourceParserFactory implements ISourceParserFactory
{
    //~ Methods

    @Override public ISourceParser createSourceParser()
    {
        return new SourceParser();
    }

    //~ Inner Classes

    class ProblemReporterProxy implements IErrorProxy
    {
        private IProblemReporter reporter;

        public ProblemReporterProxy(IProblemReporter reporter)
        {
            this.reporter = reporter;
        }

        @Override public void reportASTVisitorError(Throwable cause)
        {
            // TODO Auto-generated method stub

        }

        @Override public void reportTokenizerError(Throwable cause)
        {
            System.out.println("error: " + cause);
        }

        @Override public void reportUnmatchedBrace(Token token)
        {
            // TODO Auto-generated method stub
        }
    }

    class SourceParser extends AbstractSourceParser
    {
        @Override public IModuleDeclaration parse(IModuleSource input, IProblemReporter reporter)
        {
            boolean thrown = false;

            String source = input.getSourceContents();
            ModuleDeclaration module = new ModuleDeclaration(source.length());

            IErrorProxy proxy = new ProblemReporterProxy(reporter);
            ASTBuildVisitor visitor = new ASTBuildVisitor(new PerlASTObjectCreator(module), proxy);

            DefaultTokenProvider provider = new DefaultTokenProvider(source);
            Parser parser = ParserFactory.createParser(provider, new ProblemReporterProxy(reporter));

            try
            {
                Document document = parser.parse();

                document.accept(visitor);
                document.destroy();
            }
            catch (TokenizerException e)
            {
                thrown = true;

                String error = String.format("error tokenizing [%s - %d]", input.getFileName(), e.getLineNumber());
                PerlCorePlugin.error(error, e.getCause());
            }
            catch (ParserException e)
            {
                thrown = true;

                String error = String.format("error parsing [%s - %d]", input.getFileName(), e.getLineNumber());
                PerlCorePlugin.error(error, e.getCause());
            }
            finally
            {
                if (thrown && !PerlCorePlugin.ALLOW_HALF_PARSED)
                {
                    // don't return a half parsed module
                    module = new ModuleDeclaration(source.length());
                }
            }

            return module;
        }
    }
}

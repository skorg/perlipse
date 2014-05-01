package org.scriptkitty.perlipse.internal.ui.text.rules;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.dltk.ui.text.rules.ScriptWordRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWhitespaceDetector;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.PatternRule;
import org.eclipse.jface.text.rules.WhitespaceRule;
import org.eclipse.jface.text.rules.WordRule;
import org.scriptkitty.perl.lang.Keyword;
import org.scriptkitty.perl.lang.Language;
import org.scriptkitty.perl.lang.Symbol;



/**
 */
public class PerlSyntaxRulesFactory
{
    public static IRule createArrayKeywordSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getArrayVariableKeyworDetector();
        Collection<Symbol> symbols = Symbol.getArrayBuiltins();

        return createSymbolRule(detector, symbols, token);
    }

    public static IRule createArrayVariableSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getArrayVariableDetector();
        return new PerlVarSyntaxRule(detector, token);
    }

    public static IRule createBarewordSyntaxRule(IToken token, Map<String, IToken> nextTokens)
    {
        Assert.isNotNull(nextTokens);
        
        IWordDetector detector = PerlSyntaxDetectorFactory.getBarewordDetector();
        Collection<Keyword> barewords = Keyword.getBarewords();

        ScriptWordRule rule = createKeywordRule(detector, barewords, token);

        Iterator<String> iter = nextTokens.keySet().iterator();
        while (iter.hasNext())
        {
            String word = iter.next();
            rule.addNextTokenAfterSeen(word, nextTokens.get(word));
        }

        return rule;
    }

    public static IRule createFileHandleSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getFileHandleDetector();
        Collection<Symbol> symbols = Symbol.getFileHandleBuiltins();

        return createSymbolRule(detector, symbols, token);
    }

    public static IRule createFunctionSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getFunctionDetector();
        Collection<Keyword> keywords = Keyword.getFunctions();

        return createKeywordRule(detector, keywords, token);
    }

    public static IRule createHashKeywordSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getHashVariableDetector();
        Collection<Symbol> symbols = Symbol.getHashBuiltins();

        return createSymbolRule(detector, symbols, token);
    }

    public static IRule createHashVariableSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getHashVariableDetector();
        return new PerlVarSyntaxRule(detector, token);
    }
    
    public static IRule createPodTagSyntaxRule(IToken token)
    {
        String podStart = String.valueOf(Language.getPodStartChar());
        // break on EOL and EOF
        PatternRule rule = new PatternRule(podStart, null, token, (char) 0, true, true);
        // make sure we only match pod at the start of the line
        rule.setColumnConstraint(0);
        
        return rule;
    }

    public static IRule createReturnSyntaxRule(IToken token)
    {
        WordRule rule = new WordRule(PerlSyntaxDetectorFactory.getFunctionDetector());
        rule.addWord(Language.getReturnKeyword().toString(), token);

        return rule;
    }

    public static IRule createScalarKeywordSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getScalarVariableDetector();
        Collection<Symbol> symbols = Symbol.getScalarBuiltins();

        return createSymbolRule(detector, symbols, token);
    }

    public static IRule createScalarVariableSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getScalarVariableDetector();
        return new PerlVarSyntaxRule(detector, token);
    }

    public static IRule getWhiteSpaceRule()
    {
        IWhitespaceDetector detector = new IWhitespaceDetector()
        {
            @Override public boolean isWhitespace(char c)
            {
                return Character.isWhitespace(c);
            }
        };
        return new WhitespaceRule(detector);
    }

    private static ScriptWordRule createSymbolRule(IWordDetector detector, Collection<Symbol> symbols, IToken token)
    {
        ScriptWordRule rule = new ScriptWordRule(detector);
        
        for (Symbol symbol : symbols)
        {
            rule.addWord(symbol.toString(), token);
        }

        return rule;
    }
    
    private static ScriptWordRule createKeywordRule(IWordDetector detector, Collection<Keyword> keywords, IToken token)
    {
        ScriptWordRule rule = new ScriptWordRule(detector);
        
        for (Keyword keyword : keywords)
        {
            // skip the return keyword, it's a special case...
            if (!keyword.isReturnKeyword())
            {
                rule.addWord(keyword.toString(), token);
            }
        }

        return rule;
    }
}

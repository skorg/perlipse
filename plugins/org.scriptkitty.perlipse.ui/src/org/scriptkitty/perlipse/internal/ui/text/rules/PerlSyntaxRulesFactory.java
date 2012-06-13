package org.scriptkitty.perlipse.internal.ui.text.rules;

import java.util.Enumeration;
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
import org.scriptkitty.perlipse.core.util.PerlWordUtils;


/**
 */
public class PerlSyntaxRulesFactory
{
    private static final String RETURN = PerlWordUtils.getReturnKeyword();

    public static IRule createArrayKeywordSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getArrayVariableKeyworDetector();
        Enumeration<String> keywords = PerlWordUtils.getArrayKeywords();

        return createKeywordRule(detector, keywords, token);
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
        Enumeration<String> keywords = PerlWordUtils.getBarewordKeywords();

        ScriptWordRule rule = createKeywordRule(detector, keywords, token);

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
        Enumeration<String> keywords = PerlWordUtils.getFileHandleKeywords();

        return createKeywordRule(detector, keywords, token);
    }

    public static IRule createFunctionSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getFunctionDetector();
        Enumeration<String> keywords = PerlWordUtils.getFunctionKeywords();

        return createKeywordRule(detector, keywords, token);
    }

    public static IRule createHashKeywordSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getHashVariableDetector();
        Enumeration<String> keywords = PerlWordUtils.getHashKeywords();

        return createKeywordRule(detector, keywords, token);
    }

    public static IRule createHashVariableSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getHashVariableDetector();
        return new PerlVarSyntaxRule(detector, token);
    }
    
    public static IRule createPodTagSyntaxRule(IToken token)
    {
        String podStart = String.valueOf(PerlWordUtils.getPodStartChar());
        // break on EOL and EOF
        PatternRule rule = new PatternRule(podStart, null, token, (char) 0, true, true);
        // make sure we only match pod at the start of the line
        rule.setColumnConstraint(0);
        
        return rule;
    }

    public static IRule createReturnSyntaxRule(IToken token)
    {
        WordRule rule = new WordRule(PerlSyntaxDetectorFactory.getFunctionDetector());
        rule.addWord(RETURN, token);

        return rule;
    }

    public static IRule createScalarKeywordSyntaxRule(IToken token)
    {
        IWordDetector detector = PerlSyntaxDetectorFactory.getScalarVariableDetector();
        Enumeration<String> keywords = PerlWordUtils.getScalarKeywords();

        return createKeywordRule(detector, keywords, token);
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
            public boolean isWhitespace(char c)
            {
                return Character.isWhitespace(c);
            }
        };
        return new WhitespaceRule(detector);
    }

    private static ScriptWordRule createKeywordRule(IWordDetector detector,
        Enumeration<String> keywords, IToken token)
    {
        ScriptWordRule rule = new ScriptWordRule(detector);
        while (keywords.hasMoreElements())
        {
            String keyword = keywords.nextElement();

            // skip the return keyword, it's a special case...
            if (! RETURN.equals(keyword))
            {
                rule.addWord(keyword, token);
            }
        }

        return rule;
    }
}

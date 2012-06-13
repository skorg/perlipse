package org.scriptkitty.perlipse.internal.ui.text.rules;

import org.eclipse.dltk.ui.text.heredoc.HereDocPartitionRule;

import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IPredicateRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.MultiLineRule;
import org.eclipse.jface.text.rules.Token;

import org.scriptkitty.perl.lang.HereDoc;

import org.scriptkitty.perlipse.core.util.syntax.PerlVarSyntaxUtils;
import org.scriptkitty.perlipse.internal.ui.text.PerlPartitionTokenFactory;

import java.util.ArrayList;
import java.util.List;


public class PerlPartitionRulesFactory
{
    //~ Methods

    public static IPredicateRule createQuoteAndRegExpRule(IToken token)
    {
        return new PerlQuoteAndRegExpPartitionRule(token);
    }

    public static IPredicateRule createStringRule(String string, IToken token)
    {
        // always break on end of file
        return new MultiLineRule(string, string, token, '\\', true);
    }

    public static IPredicateRule getCommentRule(IToken token)
    {
        // override evaluate to allow $#
        return new EndOfLineRule("#", token)
        {
            @Override public IToken evaluate(ICharacterScanner scanner, boolean resume)
            {
                if (scanner.getColumn() > 0)
                {
                    scanner.unread();

                    int c = scanner.read();

                    if (PerlVarSyntaxUtils.isScalarVariableStartCharacter((char) c))
                    {
                        scanner.read();
                        return Token.UNDEFINED;
                    }
                }

                return super.evaluate(scanner, resume);
            }
        };
    }

    public static HereDocPartitionRule getHereDocRule()
    {
        return new HereDocPartitionRule("<<", PerlPartitionTokenFactory.createHereDocToken())
        {
            /*
             * @see org.eclipse.dltk.ui.text.heredoc.HereDocPartitionRule#parseIdentifier(java.lang.String)
             */
            @Override protected String extractIdentifier(String str)
            {
                return HereDoc.getIdentifier(str);
            }

            /*@see org.eclipse.dltk.ui.text.heredoc.HereDocPartitionRule#reparseIdentifier(java.lang.String)
             */
            @Override protected String parseIdentifier(String str)
            {
                return HereDoc.parseTerminator(str);
            }
        };
    }

    public static List<IPredicateRule> getPartitionRules()
    {
        List<IPredicateRule> rules = new ArrayList<IPredicateRule>();

        IToken comment = PerlPartitionTokenFactory.createCommentToken();
        rules.add(PerlPartitionRulesFactory.getCommentRule(comment));

        IToken pod = PerlPartitionTokenFactory.createPodToken();
        rules.add(PerlPartitionRulesFactory.getPodRule(pod));

        IToken string = PerlPartitionTokenFactory.createStringToken();
        rules.add(PerlPartitionRulesFactory.createStringRule("\"", string));
        rules.add(PerlPartitionRulesFactory.createStringRule("'", string));

        IToken backtick = PerlPartitionTokenFactory.createBacktickToken();
        rules.add(PerlPartitionRulesFactory.createStringRule("`", backtick));

        IToken quoteRegExp = PerlPartitionTokenFactory.createQuoteAndRegExpToken();
        rules.add(PerlPartitionRulesFactory.createQuoteAndRegExpRule(quoteRegExp));

        return rules;
    }

    public static IPredicateRule getPodRule(IToken token)
    {
        MultiLineRule podRule = new MultiLineRule("=", "=cut", token, (char) 0, true); // $NON-NLS-1$ //$NON-NLS-2$
        // make sure we only match pod at the start of the line
        podRule.setColumnConstraint(0);

        return podRule;
    }
}

package org.scriptkitty.perlipse.internal.ui.text;

import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.Token;

public class PerlPartitionTokenFactory
{
    public static IToken createBacktickToken()
    {
        return new Token(PerlPartitionConstants.PERL_BACKTICK);
    }
    
    public static IToken createCommentToken()
    {
        return new Token(PerlPartitionConstants.PERL_COMMENT);
    }
    
    public static IToken createStringToken()
    {
        return new Token(PerlPartitionConstants.PERL_STRING);
    }
    
    public static IToken createPodToken()
    {
        return new Token(PerlPartitionConstants.PERL_PERLDOC);
    }
    
    public static IToken createQuoteAndRegExpToken()
    {
        return new Token(PerlPartitionConstants.PERL_QUOTE_AND_REGEXP);
    }
    
    public static IToken createHereDocToken()
    {
        return new Token(PerlPartitionConstants.PERL_HEREDOC);
    }
    
    public static IToken createVariableToken()
    {
        return new Token(PerlPartitionConstants.PERL_VARIABLE);
    }
}

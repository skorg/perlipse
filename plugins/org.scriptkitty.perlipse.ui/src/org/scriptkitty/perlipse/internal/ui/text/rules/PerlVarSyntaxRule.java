package org.scriptkitty.perlipse.internal.ui.text.rules;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.Token;

/**
 */
public class PerlVarSyntaxRule implements IRule
{
    private final IWordDetector detector;
    private final IToken defaultToken;

    public PerlVarSyntaxRule(IWordDetector detector, IToken defaultToken)
    {
        this.detector = detector;
        this.defaultToken = defaultToken;
    }

    public IToken evaluate(ICharacterScanner scanner)
    {
        IToken token = Token.UNDEFINED;

        int c = scanner.read();
        if (detector.isWordStart((char) c))
        {
            c = scanner.read();
            if (c != '{' && detector.isWordPart((char) c))
            {
                do
                {
                    c = scanner.read();
                }
                while (detector.isWordPart((char) c));               
                token = defaultToken;
            }
        }

        if (c != ICharacterScanner.EOF)
        {
            scanner.unread();
        }

        return token;
    }

}

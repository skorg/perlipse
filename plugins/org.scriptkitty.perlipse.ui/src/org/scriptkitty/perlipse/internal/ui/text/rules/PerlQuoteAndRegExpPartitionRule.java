package org.scriptkitty.perlipse.internal.ui.text.rules;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

import org.eclipse.jface.text.rules.ICharacterScanner;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.PatternRule;
import org.eclipse.jface.text.rules.Token;
import org.scriptkitty.perl.lang.Operators;
import org.scriptkitty.perlipse.core.util.syntax.PerlOpSyntaxUtils;


/**
 */
public class PerlQuoteAndRegExpPartitionRule extends PatternRule
{
    private static Collection<String> KEYWORDS = Operators.getQuoteAndRegExpOperators();

    private StringBuffer buffer = new StringBuffer();

    private Comparator<char[]> fLineDelimiterComparator =
        new DecreasingCharArrayLengthComparator<char[]>();

    private char[][] fLineDelimiters;

    private char[][] fSortedLineDelimiters;

    private boolean multiPartDelim;
    private boolean openBraceDelim;

    public PerlQuoteAndRegExpPartitionRule(IToken token)
    {
        super(" ", " ", token, '\\', false, false);
    }

    @Override public IToken evaluate(ICharacterScanner scanner, boolean resume)
    {
        IToken token = super.evaluate(scanner, resume);

        if (! token.isUndefined())
        {
            processModifiers(scanner);
        }

        return token;
    }

    @Override protected IToken doEvaluate(ICharacterScanner scanner, boolean resume)
    {
        if (resume && endSequenceDetected(scanner))
        {
            return fToken;
        }

        /*
         * match against the start of the quote-like operator instead of the starting delimiter so
         * the operator can be considered as part of the partition.
         *
         * perl supports 's  /a/b/' and as a valid syntax *ugh* so it seems easier and more efficent to
         * just consume the whitespace, rather then stepping back, checking for whitespace and then
         * performing a double unread to get the next previous character.
         *
         * we still need to grab the last character before the start of the potential quote-like
         * operator to make sure it's valid. we don't want to match things like $self->abcqw($var)
         * and my($var);
         */
        int lastSeen = getLastSeenChar(scanner);

        int c = scanner.read();
        int read = 1;

        if (isStartSequence(c, lastSeen))
        {
            buffer.setLength(0);
            buffer.append((char) c);

            if ((char) c != '/')
            {
                c = scanner.read();
                read++;
            }

            while (Character.isWhitespace((char) c))
            {
                c = scanner.read();
                read++;
            }

            if (isQuoteLikePart(c))
            {
                IToken token = evaluate(c, scanner);
                if (! token.isUndefined())
                {
                    return token;
                }
            }
        }

        unwind(scanner, read);
        return Token.UNDEFINED;
    }

    @Override protected boolean endSequenceDetected(ICharacterScanner scanner)
    {
        setupLineDelimiters(scanner);

        int readCount = 1;
        int c;
        while ((c = scanner.read()) != ICharacterScanner.EOF)
        {
            if (c == fEscapeCharacter)
            {
                // Skip escaped character(s)
                if (fEscapeContinuesLine)
                {
                    c = scanner.read();
                    for (int i = 0; i < fSortedLineDelimiters.length; i++)
                    {
                        if ((c == fSortedLineDelimiters[i][0])
                            && sequenceDetected(scanner, fSortedLineDelimiters[i], true))
                        {
                            break;
                        }
                    }
                }
                else
                {
                    scanner.read();
                }

            }
            else if (isEndSequence(c, scanner))
            {
                // Check if the specified end sequence has been found.
                if (sequenceDetected(scanner, fEndSequence, true))
                {
                    return true;
                }
            }
            else if (fBreaksOnEOL)
            {
                // Check for end of line since it can be used to terminate the pattern.
                for (int i = 0; i < fSortedLineDelimiters.length; i++)
                {
                    if ((c == fSortedLineDelimiters[i][0])
                        && sequenceDetected(scanner, fSortedLineDelimiters[i], true))
                    {
                        return true;
                    }
                }
            }

            readCount++;
        }

        if (fBreaksOnEOF)
        {
            return true;
        }

        for (; readCount > 0; readCount--)
        {
            scanner.unread();
        }

        return false;
    }

    private IToken evaluate(int c, ICharacterScanner scanner)
    {
        IToken token = Token.UNDEFINED;

        int read = 0;
        if (! isQuoteLikeDelimiter(c))
        {
            buffer.append((char) c);
            c = scanner.read();
            read++;
        }

        while (Character.isWhitespace((char) c))
        {
            c = scanner.read();
            read++;
        }

        String opKeyword = buffer.toString();
        if (isQuoteLikeSequence(opKeyword, (char) c))
        {
            multiPartDelim = hasThreePartDelimiter(opKeyword);
            openBraceDelim = isQuoteLikeOpenBrace(c);

            char close = getQuoteLikeCloseDelimiter(c);
            fEndSequence = new char[] { close };

            if (endSequenceDetected(scanner))
            {
                token = fToken;
            }
        }
        else
        {
            unwind(scanner, read);
        }

        return token;
    }

    private int getLastSeenChar(ICharacterScanner scanner)
    {
        int last = -1;
        if (scanner.getColumn() > 0)
        {
            scanner.unread();
            last = scanner.read();
        }

        return last;
    }

    private char getQuoteLikeCloseDelimiter(int c)
    {
        return PerlOpSyntaxUtils.getQuoteLikeCloseCharacter((char) c);
    }

    private boolean hasThreePartDelimiter(String keyword)
    {
        return PerlOpSyntaxUtils.hasThreePartDelimiter(keyword);
    }

    private boolean isEndOfPartition(int c)
    {
        return (((char) c == ';') || (c == ICharacterScanner.EOF));
    }

    private boolean isEndSequence(int c, ICharacterScanner scanner)
    {
        if ((fEndSequence.length > 0) && (c == fEndSequence[0]))
        {
            if (multiPartDelim)
            {
                multiPartDelim = false;

                if (openBraceDelim)
                {
                    int read = 0;
                    openBraceDelim = false;
                    
                    c = scanner.read();
                    read++;
                    
                    while (Character.isWhitespace((char) c))
                    {
                        c = scanner.read();
                        read++;
                    }
                    
                    if (isQuoteLikeDelimiter(c))
                    {
                        char close = getQuoteLikeCloseDelimiter(c);
                        fEndSequence = new char[] { close };
                    }
                    else
                    {
                        unwind(scanner, read);
                    }
                }

                return false;
            }

            return true;
        }

        return false;
    }

    private boolean isModifier(String keyword, int c)
    {
        return PerlOpSyntaxUtils.isValidModifier(keyword, (char) c);
    }

    private boolean isQuoteLikeDelimiter(int c)
    {
        return PerlOpSyntaxUtils.isQuoteLikeDelimiter((char) c);
    }

    private boolean isQuoteLikeOpenBrace(int c)
    {
        return PerlOpSyntaxUtils.isOpenBrace((char) c);
    }

    private boolean isQuoteLikePart(int c)
    {
        return (PerlOpSyntaxUtils.isQuoteCharacter((char) c)
                || PerlOpSyntaxUtils.isQuoteLikeDelimiter((char) c));
    }

    private boolean isQuoteLikeSequence(String keyword, char delim)
    {
        return (KEYWORDS.contains(keyword) && isQuoteLikeDelimiter(delim));
    }

    private boolean isStartSequence(int c, int last)
    {
        if ((last != -1) && Character.isLetterOrDigit(last))
        {
            return false;
        }

        return (PerlOpSyntaxUtils.isQuoteStartCharacter((char) c)
                || PerlOpSyntaxUtils.isRegExpStartCharacter((char) c));
    }

    private void processModifiers(ICharacterScanner scanner)
    {
        int c;
        String keyword = buffer.toString();
        do
        {
            c = scanner.read();
        }
        while (! isEndOfPartition(c) && isModifier(keyword, c));

        scanner.unread();
    }

    private void setupLineDelimiters(ICharacterScanner scanner)
    {
        char[][] originalDelimiters = scanner.getLegalLineDelimiters();
        int count = originalDelimiters.length;
        if ((fLineDelimiters == null) || (originalDelimiters.length != count))
        {
            fSortedLineDelimiters = new char[count][];
        }
        else
        {
            while ((count > 0) && (fLineDelimiters[count - 1] == originalDelimiters[count - 1]))
            {
                count--;
            }
        }

        if (count != 0)
        {
            fLineDelimiters = originalDelimiters;
            System.arraycopy(fLineDelimiters, 0, fSortedLineDelimiters, 0, fLineDelimiters.length);
            Arrays.sort(fSortedLineDelimiters, fLineDelimiterComparator);
        }
    }

    private void unwind(ICharacterScanner scanner, int count)
    {
        for (int i = 0; i < count; i++)
        {
            scanner.unread();
        }
    }

    /**
     */
    private static class DecreasingCharArrayLengthComparator<T> implements Comparator<T>
    {
        @Override public int compare(Object o1, Object o2)
        {
            return ((char[]) o2).length - ((char[]) o1).length;
        }
    }
}

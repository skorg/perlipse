package org.scriptkitty.perlipse.core.util.syntax;

/**
 */
public class PerlOpSyntaxUtils
{

    public static char getQuoteLikeCloseCharacter(char c)
    {
        char close = 0;
        switch (c)
        {
            case '(':
            {
                close = ')';
                break;
            }
            case '{':
            {
                close = '}';
                break;
            }
            case '<':
            {
                close = '>';
                break;
            }
            case '[':
            {
                close = ']';
                break;
            }
            default:
            {
                close = c;
                break;
            }
        }

        return close;
    }

    public static boolean hasThreePartDelimiter(String op)
    {
        if ("s".equals(op) || "tr".equals(op) || "y".equals(op))
        {
            return true;
        }

        return false;
    }

    public static boolean isMatchModifier(char c)
    {
        return (isQRModifier(c) || (c == 'g') || (c == 'c'));
    }

    public static boolean isOpenBrace(char c)
    {
        return ((c == '(') || (c == '{') || (c == '<') || (c == '['));
    }

    public static boolean isQRModifier(char c)
    {
        switch (c)
        {
            case 'm':
            case 's':
            case 'i':
            case 'x':
            case 'p':
            case 'o':
            {
                return true;
            }
            default:
            {
                return false;
            }
        }
    }

    public static boolean isQuoteCharacter(char c)
    {
        return ((c == 'q') || (c == 'x') || (c == 'w') || (c == 'r'));
    }

    public static boolean isQuoteLikeDelimiter(char c)
    {
        return PerlSyntaxUtils.isPunctCharactacter(c);
    }

    public static boolean isQuoteStartCharacter(char c)
    {
        return ((c == 'q') || (c == 't') || (c == 'y'));
    }

    public static boolean isRegExpStartCharacter(char c)
    {
        return ((c == 'q') || (c == 'm') || (c == '/') || (c == 's') || (c == '?'));
    }

    public static boolean isReplaceModifier(char c)
    {
        return (isMatchModifier(c) || (c == 'e'));
    }

    public static boolean isTRModifier(char c)
    {
        return ((c == 'c') || (c == 'd') || (c == 's'));
    }

    public static boolean isValidModifier(String keyword, char c)
    {
        boolean isValid = false;

        if ("qr".equals(keyword))
        {
            isValid = isQRModifier(c);
        }
        else if ("m".equals(keyword) || "/".equals(keyword))
        {
            isValid = isMatchModifier(c);
        }
        else if ("s".equals(keyword))
        {
            isValid = isReplaceModifier(c);
        }
        else if ("tr".equals(keyword))
        {
            isValid = isTRModifier(c);
        }
        else if ("y".equals(keyword))
        {
            isValid = isYModifier(c);
        }

        return isValid;
    }

    public static boolean isYModifier(char c)
    {
        // these are the same
        return isTRModifier(c);
    }
}

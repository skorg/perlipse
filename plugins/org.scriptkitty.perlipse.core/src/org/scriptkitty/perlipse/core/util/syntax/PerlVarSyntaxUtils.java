package org.scriptkitty.perlipse.core.util.syntax;


/**
 */
public class PerlVarSyntaxUtils
{
    public static final char CLOSE_PAREN = ')';
    
    public static final char OPEN_PAREN = '(';

    public static boolean isArrayKeywordVariableCharacter(char c)
    {
        /*
         * - '+','_','-' are keyword chars
         */
        return (isArrayVariableCharacter(c) || c == '+' || c == '-' || c == '_');
    }
    
    public static boolean isArrayKeywordVariableStartCharacter(char c)
    {
        /*
         * - '$' is for @$ref;
         */
        return (isArrayVariableStartCharacter(c) || c == '$');
    }
    
    /**
     * returns <code>true</code> if the character is valid for an array name
     */
    public static boolean isArrayVariableCharacter(char c)
    {
        if (Character.isLetterOrDigit(c))
        {
            return true;
        }
        
        /*
         * - ':' is for @Package::array
         * - '_' is for %_h;
         * - '$' is for @$array
         */
        if (c == '_' || c == ':' || c == '$')
        {
            return true;
        }
        
        return false;
    }

    public static boolean isArrayVariableStartCharacter(char c)
    {
        return (c == '@');
    }

    public static boolean isFileHandleCharacter(char c)
    {
        // allow *ARGV
        return (Character.isUpperCase(c) || (c == '*'));
    }

    public static boolean isFunctionCharacter(char c)
    {
        return Character.isLetter(c);
    }

    /**
     * returns <code>true</code>if the character is valid for a hash name
     */
    public static boolean isHashVariableCharacter(char c)
    {
        if (Character.isLetterOrDigit(c))
        {
            return true;
        }

        /*
         * - '!','^' are for special hash keywords
         * - ':' is for %Package::hash;
         * - '_' is for @_, @_a
         * - '$' is for %$hash
         */
        if (c == '!' || c == '^' || c == '_' || c == ':' || c == '$')
        {
            return true;
        }

        return false;
    }

    public static boolean isHashVariableStartCharacter(char c)
    {
        return (c == '%');
    }

    public static boolean isScalarKeywordVariableCharacter(char c)
    {
        return (PerlSyntaxUtils.isPunctCharactacter(c) || c == 'a' || c == 'b');
    }
            
    public static boolean isScalarVariableCharacter(char c)
    {
        /*
         * - ':' is for $Package::VERSION
         */
        return (Character.isJavaIdentifierPart(c) || c == ':');
    }

    public static boolean isScalarVariableStartCharacter(char c)
    {
        return (c == '$');
    }

    public static boolean isBarewordChar(char c)
    {
        // include _ and : so we can match against pkg/sub names
        if (Character.isLetterOrDigit(c) || (c == '_') || (c == ':'))
        {
            return true;
        }

        return false;
    }

    public static boolean isPrevBarewordChar(char c)
    {
        if (Character.isWhitespace(c) || (c == '=') || (c == ';'))
        {
            return true;
        }

        return false;
    }

    public static boolean isPrevFunctionChar(char c)
    {
        return (isPrevBarewordChar(c) || (c == '(') || (c == '!'));
    }

    public static boolean isVariableStartCharacter(char c)
    {
        return (isArrayVariableStartCharacter(c) || isHashVariableCharacter(c)
                || isScalarVariableCharacter(c));
    }
}

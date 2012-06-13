package org.scriptkitty.perlipse.core.util.syntax;

import java.nio.CharBuffer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PerlSyntaxUtils
{
    private static Pattern PUNCT_PATTERN = Pattern.compile("\\p{Punct}"); 
    
    public static boolean isPunctCharactacter(char c)
    {
        CharBuffer wrap = CharBuffer.wrap(new char[] { c });
        Matcher matcher = PUNCT_PATTERN.matcher(wrap);
    
        return (matcher.matches());
    }    
}

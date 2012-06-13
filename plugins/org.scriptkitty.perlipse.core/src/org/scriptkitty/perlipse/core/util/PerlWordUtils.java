package org.scriptkitty.perlipse.core.util;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;


/**
 * perl keyword utilities
 */
public final class PerlWordUtils
{
    private static final String ARRAY_KEYWORDS = "org.scriptkitty.perlipse.core.util.arrayKeywords";

    private static final String BAREWORD_KEYWORDS = "org.scriptkitty.perlipse.core.util.barewordKeywords";

    private static final String FILEHANDLE_KEYWORDS = "org.scriptkitty.perlipse.core.util.fileHandleKeywords";

    private static final String FUNCTION_KEYWORDS = "org.scriptkitty.perlipse.core.util.functionKeywords";

    private static final String HASH_KEYWORDS = "org.scriptkitty.perlipse.core.util.hashKeywords";

    private static final String SCALAR_KEYWORDS = "org.scriptkitty.perlipse.core.util.scalarKeywords";

    private static ResourceBundle arrayKeywords = ResourceBundle.getBundle(ARRAY_KEYWORDS);

    private static ResourceBundle barewordKeywords = ResourceBundle.getBundle(BAREWORD_KEYWORDS);

    private static ResourceBundle fileHandleKeywords =
        ResourceBundle.getBundle(FILEHANDLE_KEYWORDS);

    private static ResourceBundle functionKeywords = ResourceBundle.getBundle(FUNCTION_KEYWORDS);

    private static ResourceBundle hashKeywords = ResourceBundle.getBundle(HASH_KEYWORDS);

    private static ResourceBundle scalarKeywords = ResourceBundle.getBundle(SCALAR_KEYWORDS);

    private PerlWordUtils()
    {
        // private constructor
    }

    public static Enumeration<String> getArrayKeywords()
    {
        return arrayKeywords.getKeys();
    }

    public static Enumeration<String> getBarewordKeywords()
    {
        /*
         * XXX: quote and quote like operators currently considered a bareword
         *
         * this may need to change, for partitioning/syntax highlighting...
         */
        return barewordKeywords.getKeys();
    }

    public static Enumeration<String> getFileHandleKeywords()
    {
        return fileHandleKeywords.getKeys();
    }

    public static Enumeration<String> getFunctionKeywords()
    {
        return functionKeywords.getKeys();
    }

    public static Enumeration<String> getHashKeywords()
    {
        return hashKeywords.getKeys();
    }

    public static String getPackageKeyword()
    {
        return "package";
    }

    public static char getPodStartChar()
    {
        return '=';
    }

    public static Set<String> getQuoteAndRegExpOperators()
    {
        String[] elements = { "q", "qq", "qx", "qw", "tr", "y", "qr", "m", "/", "s", "?" };
        return new HashSet<String>(Arrays.asList(elements));
    }

    public static String getReturnKeyword()
    {
        return "return";
    }

    public static Enumeration<String> getScalarKeywords()
    {
        return scalarKeywords.getKeys();
    }

    public static String getSubKeyword()
    {
        return "sub";
    }
}

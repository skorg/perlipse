package org.scriptkitty.perlipse.internal.ui.text;

import org.eclipse.jface.text.IDocument;

import org.scriptkitty.perlipse.core.PerlCoreConstants;


/**
 * avaiable perl document partitions
 */
public final class PerlPartitionConstants
{
    //~ Static fields/initializers

    public static String PERL_BACKTICK = "__perl_backtick";

    /** defines a perl comment partition */
    public static String PERL_COMMENT = "__perl_comment";

    public static String PERL_HEREDOC = "__perl_heredoc";

    /** @see org.scriptkitty.perlipse.core.PerlCoreConstants#PARTITIONING_ID */
    public static String PERL_PARTITIONING = PerlCoreConstants.PARTITIONING_ID;

    /** defines a perl perldoc partition */
    public static String PERL_PERLDOC = "__perl_perlddoc";

    public static String PERL_QUOTE_AND_REGEXP = "__perl_quoteRegExp";

    /** defined a perl string partition */
    public static String PERL_STRING = "__perl_string";

//    public static String PERL_ARRAY_VAR = "__perlArrayPartition";
//
//    public static String PERL_HASH_VAR = "_perlHashPartition";
//
//    public static String PERL_SCALAR_VAR = "__perlScalarPartition";

    public static String PERL_VARIABLE = "__perlVariable";

    public static String[] PERL_PARTITION_TYPES = new String[]
    {
        // PERL_ARRAY_VAR, PERL_HASH_VAR, PERL_SCALAR_VAR,
        PERL_VARIABLE, PERL_COMMENT, PERL_STRING, PERL_BACKTICK, PERL_PERLDOC, PERL_QUOTE_AND_REGEXP, PERL_HEREDOC,
        IDocument.DEFAULT_CONTENT_TYPE
    };    
    
    public static String[] REPARSE_PARTITION_TYPES = new String[]
    {
        PERL_HEREDOC
    };
        
    //~ Constructors

    private PerlPartitionConstants()
    {
        // empty impl
    }
}

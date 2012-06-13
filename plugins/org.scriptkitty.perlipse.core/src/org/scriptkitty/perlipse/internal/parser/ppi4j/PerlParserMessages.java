package org.scriptkitty.perlipse.internal.parser.ppi4j;

import org.eclipse.osgi.util.NLS;

public final class PerlParserMessages extends NLS 
{
	private static String BUNDLE_NAME = "org.scriptkitty.perlipse.internal.parser.ppi4j.parserMessages";
    
    static {
        NLS.initializeMessages(BUNDLE_NAME, PerlParserMessages.class);
    }
    
    public static String parserError;
}

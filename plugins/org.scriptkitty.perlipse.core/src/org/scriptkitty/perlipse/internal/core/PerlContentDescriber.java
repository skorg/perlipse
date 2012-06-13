package org.scriptkitty.perlipse.internal.core;

import org.eclipse.dltk.core.ScriptContentDescriber;

import java.util.regex.Pattern;


public class PerlContentDescriber extends ScriptContentDescriber
{
    private static Pattern[] headerPatterns = { Pattern.compile("#!\\s*.*perl", Pattern.MULTILINE) };

    /*
     * @see org.eclipse.dltk.core.ScriptContentDescriber#getHeaderPatterns()
     */
    @Override protected Pattern[] getHeaderPatterns()
    {
        return headerPatterns;
    }
}
